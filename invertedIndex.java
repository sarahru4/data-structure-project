/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastru;

public class invertedIndex {

    LinkedList<Text> invertIndex;

    public invertedIndex() {
        invertIndex = new LinkedList<Text>();
    }

    public void add(String w, int id) {
        if (!search_inverted(w)) {

            Text t = new Text(w);
            t.DId.insert(id);
            invertIndex.insert(t);

        } else {
            Text t = invertIndex.retrieve();
            t.addId(id);
        }
    }

    public boolean search_inverted(String word) {
        if (invertIndex == null || invertIndex.empty()) {
            return false;
        }
        invertIndex.findFirst();
        while (!invertIndex.last()) {
            if (invertIndex.retrieve().w.equals(word)) {
                return true;
            }

            invertIndex.findNext();
        }
        return invertIndex.retrieve().equals(word);
    }

    public void display_invertIndex() {
        if (invertIndex == null) {
            System.out.println("inverted index null");
            return;
        } else if (invertIndex.empty()) {
            System.out.println("inverted index empty");
            return;
        }
        invertIndex.findFirst();
        while (!invertIndex.last()) {
            Text t = invertIndex.retrieve();
            t.display();
            invertIndex.findNext();
        }
        Text t = invertIndex.retrieve();
        t.display();
    }
}
