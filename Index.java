package datastru;

public class Index {

    LinkedList<Document> documents;

    public Index() {
        documents = new LinkedList<Document>();
    }

    public void addDocument(Document doc) {
        documents.insert(doc);
    }

    public void showDocuments() {
        if (documents == null) {
            System.out.println("No documents available.");
            return;
        } else if (documents.empty()) {
            System.out.println("No documents to display.");
            return;
        }

        documents.findFirst();
        while (!documents.last()) {
            Document currentDoc = documents.retrieve();
            System.out.println("\n--------------------------------------");
            System.out.println("Document ID: " + currentDoc.id);
            currentDoc.wordsList.display();
            documents.findNext();
        }

        Document lastDoc = documents.retrieve();
        System.out.println("\n--------------------------------------");
        System.out.println("Document ID: " + lastDoc.id);
        lastDoc.wordsList.display();
        System.out.println();
    }

    public LinkedList<Integer> show_document_term(String term) {
        LinkedList<Integer> t = new LinkedList<Integer>();
        if (documents.empty()) {
            System.out.println("NO Document found");
            return t;
        }
        documents.findFirst();
        while (!documents.last()) {
            if (documents.retrieve().wordsList.search(term.toLowerCase().trim())) {
                t.insert(documents.retrieve().id);
            }
            documents.findNext();
        }
        if (documents.retrieve().wordsList.search(term.toLowerCase().trim())) {
            t.insert(documents.retrieve().id);
        }
        return t;
    }

    public Document getDocuments(int id) {
        if (documents.empty()) {
            System.out.println("NO Document found");
            return null;
        }
        documents.findFirst();
        while (!documents.last()) {
            if (documents.retrieve().id == id) {
                return documents.retrieve();
            }
            documents.findNext();
        }
        if (documents.retrieve().id == id) {
            return documents.retrieve();
        }
        return null;
    }
}
