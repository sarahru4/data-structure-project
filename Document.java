package datastru;

public class Document{
    LinkedList<String>wordsList=new LinkedList<>();
    int id;
    String inDoc;
    Document(int id,LinkedList<String>wordsList,String content){
        this.id=id;
        this.wordsList=wordsList;
        inDoc=content;
    }
}