/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastru;

class Doc_Rank {
    
    int id;
    int rank;
    
    public Doc_Rank(int i, int r) {
        id = i;
        rank = r;
    }
    
    public void display() {
        System.out.printf("%-8d%-8d\n", id, rank);
    }
}

public class Ranking {
    
    static String Query;
    static invertedIndexBST inverted;
    static Index index1;
    static LinkedList<Integer> ALLdoc_query;
    static LinkedList<Doc_Rank> ALLdoc_rank;
    
    public Ranking(invertedIndexBST inverted, Index index1, String Query) {
        this.inverted = inverted;
        this.index1 = index1;
        this.Query = Query;
        ALLdoc_query = new LinkedList<Integer>();
        ALLdoc_rank = new LinkedList<Doc_Rank>();
    }
    
    public void display_doc_with_score_usinglist() {
        if (ALLdoc_rank.empty()) {
            System.out.println("empty");
            return;
        }
        System.out.printf("%-8s%-8s\n", "DOCID", "Score");
        ALLdoc_rank.findFirst();
        while (!ALLdoc_rank.last()) {
            ALLdoc_rank.retrieve().display();
            ALLdoc_rank.findNext();
        }
        ALLdoc_rank.retrieve().display();
    }
    
    public static Document get_doc_given_id(int id) {
        return index1.getDocuments(id);
    }
    
    public static int term_frequency_doc(Document d, String term) {
        int count = 0;
        LinkedList<String> words = d.wordsList;
        if (words.empty()) {
            return 0;
        }
        words.findFirst();
        while (!words.last()) {
            if (words.retrieve().equalsIgnoreCase(term)) {
                count++;
                
            }
            words.findNext();
        }
        if (words.retrieve().equalsIgnoreCase(term)) {
            count++;
        }
        return count;
    }
    
    public static int get_doc_rankScore(Document d, String Q) {
        if (Q.length() == 0) {
            return 0;
        }
        String terms[] = Q.split(" ");
        int sum = 0;
        for(int i = 0; i < terms.length; i++) {
            sum += term_frequency_doc(d, terms[i].trim().toLowerCase());
        }
        
        return sum;
    }
    
    public static void RankQuery(String Query) {
        LinkedList<Integer> x = new LinkedList<Integer>();
        if (Query.length() == 0) {
            return;
        }
        String terms[] = Query.split("\\s+");
        boolean found = false;
        for (int i = 0; i < terms.length; i++) {
            found = inverted.search_invertedBST(terms[i].trim().toLowerCase());
            if (found) {
                x = inverted.iverindex.retrieve().DId;
            }
            add_in_1list_sorted(x);
        }
    }
    
    public static void add_in_1list_sorted(LinkedList<Integer> x) {
        if (x.empty()) {
            return;
        }
        x.findFirst();
        while (!x.empty()) {
            boolean found = existsI_result(ALLdoc_query, x.retrieve());
            if (!found) {
                insert_sorted_id(x.retrieve());
            }
            if (!x.last()) {
                x.findNext();
            } else {
                break;
            }
        }
    }
    
    public static boolean existsI_result(LinkedList<Integer> r, Integer id) {
        if (r.empty()) {
            return false;
        }
        r.findFirst();
        while (!r.last()) {
            if (r.retrieve().equals(id)) {
                return true;
            }
            r.findNext();
        }
        if (r.retrieve().equals(id)) {
            return true;
        }
        return false;
    }
    
    public static void insert_sorted_id(Integer id) {
        if (ALLdoc_query.empty()) {
            ALLdoc_query.insert(id);
            return;
        }
        ALLdoc_query.findFirst();
        while (!ALLdoc_query.last()) {
            if (id < ALLdoc_query.retrieve()) {
                Integer id1 = ALLdoc_query.retrieve();
                ALLdoc_query.update(id);
                ALLdoc_query.update(id1);
                return;
            } else {
                ALLdoc_query.findNext();
            }
        }
        if (id < ALLdoc_query.retrieve()) {
            Integer id1 = ALLdoc_query.retrieve();
            ALLdoc_query.update(id);
            ALLdoc_query.update(id1);
            return;
        } else {
            ALLdoc_query.insert(id);
        }
    }

    public void insert_sortedLIST() {
        RankQuery(Query);
        if (ALLdoc_query.empty()) {
            System.out.println("Query is empty");
            return;            
        }
        ALLdoc_query.findFirst();
        while (!ALLdoc_query.last()) {
            Document dec = get_doc_given_id(ALLdoc_query.retrieve());
            int Rank = get_doc_rankScore(dec, Query);
            insert_sorted_list(new Doc_Rank(ALLdoc_query.retrieve(), Rank));
            ALLdoc_query.findNext();
        }
        Document dec = get_doc_given_id(ALLdoc_query.retrieve());
        int Rank = get_doc_rankScore(dec, Query);
        insert_sorted_list(new Doc_Rank(ALLdoc_query.retrieve(), Rank));
    }

    public static void insert_sorted_list(Doc_Rank dk) {
        if (ALLdoc_rank.empty()) {
            ALLdoc_rank.insert(dk);
            return;
        }
        ALLdoc_rank.findFirst();
        while (!ALLdoc_rank.last()) {
            if (dk.rank > ALLdoc_rank.retrieve().rank) {
                Doc_Rank dk1 = ALLdoc_rank.retrieve();
                ALLdoc_rank.update(dk);
                ALLdoc_rank.insert(dk1);
                return;
            } else {
                ALLdoc_rank.findNext();
            }
        }
        if (dk.rank > ALLdoc_rank.retrieve().rank) {
            Doc_Rank dk1 = ALLdoc_rank.retrieve();
            ALLdoc_rank.update(dk);
            ALLdoc_rank.insert(dk1);
            return;
        } else {
            ALLdoc_rank.insert(dk);
        }
    }
}
