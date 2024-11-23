package datastru;

public class Text {

    String w;
    LinkedList<Integer> DId;

    public Text(String w) {
        this.w = w;
        DId = new LinkedList<Integer>();
    }

    public void addId(int id) {
        if (!SearchDoc(id)) {
            DId.insert(id);
        }
    }

    public boolean SearchDoc(Integer id) {
        if (DId.empty()) {
            return false;
        }

        DId.findFirst();

        while (!DId.last()) {
            if (DId.retrieve().equals(id)) {
                return true;
            }

            DId.findNext();
        }
        if (DId.retrieve().equals(id)) {
            return true;
        }
        return false;
    }

    public void display() {
        System.out.println("\n----------------------------");
        System.out.print("word: " + w);
        System.out.print(" [");
        DId.display();
        System.out.println("]");
    }

}
