/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastru;

public class QueryProcessing {

    static invertedIndex inverted;
    static invertedIndexBST invertedBST;

    public QueryProcessing(invertedIndex inverted, invertedIndexBST invertedBST) {
        this.inverted = inverted;
        this.invertedBST = invertedBST;
    }

    public static LinkedList<Integer> BooleanQuery(String Query) {
        if (!Query.contains("AND") && !Query.contains("OR")) {
            return AndQuery(Query);
        } else if (Query.contains("AND") && !Query.contains("OR")) {
            return AndQuery(Query);
        } else if (!Query.contains("AND") && Query.contains("OR")) {
            return ORQuery(Query);
        } else {
            return MixedQuery(Query);
        }
    }

    public static LinkedList<Integer> MixedQuery(String Query) {
        LinkedList<Integer> x = new LinkedList<Integer>();
        LinkedList<Integer> y = new LinkedList<Integer>();
        if (Query.length() == 0) {
            return x;
        }
        String ors[] = Query.split("OR");
        x = AndQuery(ors[0]);
        for (int i = 1; i < ors.length; i++) {
            y= AndQuery(ors[i]);
            x= ORQuery(x, y);
        }
        return x;
    }

    public static LinkedList<Integer> AndQuery(String Query) {
        LinkedList<Integer> x = new LinkedList<Integer>();
        LinkedList<Integer> y = new LinkedList<Integer>();
        String terms[] = Query.split("AND");
        if (terms.length == 0) {
            return x;
        }

        boolean found = inverted.search_inverted(terms[0].trim().toLowerCase());
        if (found) {
            x = inverted.invertIndex.retrieve().DId;
        }
        for (int i = 1; i < terms.length; i++) {
            found = inverted.search_inverted(terms[i].trim().toLowerCase());
            if (found) {
                y = inverted.invertIndex.retrieve().DId;
            }
            x= AndQuery(x, y);
        }
        return x;
    }

    public static LinkedList<Integer> AndQuery(LinkedList<Integer> x, LinkedList<Integer> y) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        if (x.empty() || y.empty()) {
            return result;
        }
        x.findFirst();
        while (true) {
            boolean found = existsIn_result(result, x.retrieve());
            if (!found) {
                y.findFirst();
                while (true) {
                    if (y.retrieve().equals(x.retrieve())) {
                        result.insert(x.retrieve());
                        break;
                    }
                    if (!y.last()) {
                        y.findNext();
                    } else {
                        break;
                    }
                }
            }
            if (!x.last()) {
                x.findNext();
            } else {
                break;
            }
        }
        return result;
    }

    public static LinkedList<Integer> ORQuery(String Query) {
        LinkedList<Integer> x = new LinkedList<Integer>();
        LinkedList<Integer> y = new LinkedList<Integer>();
        String terms[] = Query.split("OR");
        if (terms.length == 0) {
            return x;
        }
        boolean found = inverted.search_inverted(terms[0].trim().toLowerCase());
        if (found) {
            x = inverted.invertIndex.retrieve().DId;
        }
        for (int i = 1; i < terms.length; i++) {
            found = inverted.search_inverted(terms[i].trim().toLowerCase());
            if (found) {
                y = inverted.invertIndex.retrieve().DId;
            }
            x = ORQuery(x, y);
        }
        return x;
    }

    public static LinkedList<Integer> ORQueryBST(String Query) {
        LinkedList<Integer> x = new LinkedList<Integer>();
        LinkedList<Integer> y= new LinkedList<Integer>();
        String terms[] = Query.split("OR");
        if (terms.length == 0) {
            return x;
        }
        boolean found = invertedBST.search_invertedBST(terms[0].trim().toLowerCase());
        if (found) {
            x= invertedBST.iverindex.retrieve().DId;
        }
        for (int i = 1; i < terms.length; i++) {
            found = invertedBST.search_invertedBST(terms[i].trim().toLowerCase());
            if (found) {
                y= invertedBST.iverindex.retrieve().DId;
            }
            x= ORQuery(x, y);
        }
        return x;
    }

    public static LinkedList<Integer> ORQuery(LinkedList<Integer> x, LinkedList<Integer> y) {
        LinkedList<Integer> result = new LinkedList<Integer>();
        if (x.empty() && y.empty()) {
            return result;
        }
        x.findFirst();
        while (!x.empty()) {
            boolean found = existsIn_result(result, x.retrieve());
            if (!found) {
                result.insert(x.retrieve());
            }
            if (!x.last()) {
                x.findNext();
            } else {
                break;
            }
        }
        y.findFirst();
        while (!y.empty()) {
            boolean found = existsIn_result(result, y.retrieve());
            if (!found) {
                result.insert(y.retrieve());
            }
            if (!y.last()) {
                y.findNext();
            } else {
                break;
            }
        }
        return result;
    }

    public static boolean existsIn_result(LinkedList<Integer> result, Integer id) {
        if (result.empty()) {
            return false;
        }
        result.findFirst();
        while (!result.last()) {
            if (result.retrieve().equals(id)) {
                return true;
            }
            result.findNext();
        }
        if (result.retrieve().equals(id)) {
            return true;
        }
        return false;
    }

    public static LinkedList<Integer> AndQueryBST(String Query) {
        LinkedList<Integer> x = new LinkedList<Integer>();
        LinkedList<Integer> y = new LinkedList<Integer>();
        String terms[] = Query.split("AND");
        if (terms.length == 0) {
            return x;
        }

        boolean found = invertedBST.search_invertedBST(terms[0].trim().toLowerCase());
        if (found) {
            x= invertedBST.iverindex.retrieve().DId;
        }
        for (int i = 1; i < terms.length; i++) {
            found = invertedBST.search_invertedBST(terms[i].trim().toLowerCase());
            if (found) {
                y= invertedBST.iverindex.retrieve().DId;
            }
            x= AndQuery(x, y);
        }
        return x;
    }

    public static LinkedList<Integer> MixedQueryBST(String Query) {
        LinkedList<Integer> x = new LinkedList<Integer>();
        LinkedList<Integer> y = new LinkedList<Integer>();
        if (Query.length() == 0) {
            return x;
        }
        String ors[] = Query.split("OR");
        x = AndQueryBST(ors[0]);
        for (int i = 1; i < ors.length; i++) {
            y= AndQueryBST(ors[i]);
            x = ORQuery(x, y);
        }
        return x;
    }
    public static LinkedList<Integer> BooleanQueryBST(String Query) {
        if (!Query.contains("AND") && !Query.contains("OR")) {
            return AndQueryBST(Query);
        } else if (Query.contains("AND") && !Query.contains("OR")) {
            return AndQueryBST(Query);
        } else if (!Query.contains("AND") && Query.contains("OR")) {
            return ORQueryBST(Query);
        } else {
            return MixedQueryBST(Query);
        }
    }
}
