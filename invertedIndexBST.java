/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastru;

public class invertedIndexBST {

    BST<Text> iverindex;

    public invertedIndexBST() {
        iverindex = new BST<Text>();
    }

    public void add_invertedList(invertedIndex inver) {
        if (inver.invertIndex.empty()) {
            return;
        }
        inver.invertIndex.findFirst();
        while (!inver.invertIndex.last()) {
            iverindex.insert(inver.invertIndex.retrieve().w, inver.invertIndex.retrieve());
            inver.invertIndex.findNext();
        }
        iverindex.insert(inver.invertIndex.retrieve().w, inver.invertIndex.retrieve());
    }

    public void add(String w, int id) {
        if (!iverindex.findKey(w)) {
            Text te = new Text(w);
            te.DId.insert(id);
            iverindex.insert(w, te);
        } else {
            Text t = iverindex.retrieve();
            t.addId(id);
        }
    }

   public boolean search_invertedBST(String word) {
        return iverindex.findKey(word);
    }
    public void display_InverBST(){
        if(iverindex==null){
            System.out.println("null inverted index");
        return;}
         else if (iverindex.empty()) {
            System.out.println("inverted index empty");
            return;
        }
        iverindex.inOrder();
        
        
    }

}
