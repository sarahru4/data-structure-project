package datastru;

import java.io.File;
import java.util.Scanner;

public class readFile {

    LinkedList<String> stopW;
    static Index in;
    invertedIndex inv;
    invertedIndexBST invBST;
    int totalTokens = 0;
    LinkedList<String> uniqueWORD = new LinkedList<>();
    int numberQniqueWORD = 0;

    public readFile() {
        stopW = new LinkedList<>();
        in = new Index();
        inv = new invertedIndex();
        invBST = new invertedIndexBST();
    }

    public void processFile(String filePath) {
        String currentLine = null;

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();

                if (currentLine.trim().length() < 3) {
          
                    break;
                }

               
                String f = currentLine.substring(0, currentLine.indexOf(','));
                int id = Integer.parseInt(f.trim());

                String content = currentLine.substring(currentLine.indexOf(',') + 1).trim();

                LinkedList<String> word = createWordList(content, id);
                in.addDocument(new Document(id, word, content));
            }
        } catch (Exception e) {
            System.out.println("End of file or error occurred");
        }
    }

   
    private LinkedList<String> createWordList(String s, int id) {
        LinkedList<String> words = new LinkedList<>();
        indexAndInvert(s, words, id);
        return words;
    }

    private void indexAndInvert(String content, LinkedList<String> words, int id) {
    String normalizedContent = normalizeContent(content);
    String[] tokens = normalizedContent.split("\\s+");
    totalTokens += tokens.length;

    for (String token : tokens) {
        processToken(token, words, id);
    }
}

private void processToken(String token, LinkedList<String> words, int id) {
    if (isUniqueWord(token)) {
        addUniqueWord(token);
    }

    if (!searchStopWord(token)) {
        addWordToCollections(token, words, id);
    }
}

private boolean isUniqueWord(String token) {
    return !uniqueWORD.search(token);
}

private void addUniqueWord(String token) {
    uniqueWORD.insert(token);
    numberQniqueWORD++;
}

private void addWordToCollections(String token, LinkedList<String> words, int id) {
    words.insert(token);
    inv.add(token, id);
    invBST.add(token, id);
}
        
    private String normalizeContent(String content) {
    // Convert to lower case
    content = content.toLowerCase();

    // Replace unwanted characters but allow hyphens within words
    content = content.replaceAll("[^a-zA-Z0-9\\s-]", " ");

    // Remove leading and trailing spaces, and normalize internal spaces
    content = content.trim().replaceAll("\\s+", " ");

    //  ensure that hyphens are not at the start or end of the string
    content = content.replaceAll("^-|-$", "");

    return content;
}


   

  
    public boolean searchStopWord(String word) {
        if (stopW == null || stopW.empty()) {
            return false;
        }
        stopW.findFirst();
        while (!stopW.last()) {
            if (stopW.retrieve().equals(word)) {
                return true;
            }
            stopW.findNext();
        }
        return stopW.retrieve().equals(word);

    }

    public void readStopFile(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                stopW.insert(currentLine);
            }

        } catch (Exception e) {
            System.out.println("End of file or error occurred");
        }

    }

    public void displaydocuments(LinkedList<Integer> ids) {
        if (ids.empty()) {
            System.out.println("NO Document found");
            return;
        }
        ids.findFirst();
        while (!ids.last()) {
            Document d = in.getDocuments(ids.retrieve());
            if (d != null) {
                System.out.println("Document " + d.id + ":" + d.inDoc);
            }
            ids.findNext();
        }
        Document d = in.getDocuments(ids.retrieve());
        if (d != null) {
            System.out.println("Document " + d.id + ":" + d.inDoc);
        }
        System.out.println();
    }
}
