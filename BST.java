/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastru;

class BSTNode<T> {

    public String Key;
    public T data;
    public BSTNode<T> left, right;

    public BSTNode(String Key, T val) {
        this.Key = Key;
        data = val;
        left = right = null;

    }

}

public class BST<T> {

    private BSTNode<T> root, current;

    public BST() {
        root = current = null;

    }

    public boolean empty() {
        return root == null;
    }

    public boolean full() {
        return false;
    }

    public T retrieve() {
        return current.data;
    }

    public boolean findKey(String keyToFind) {
    BSTNode<T> currentNode = root; 

    while (currentNode != null) {
        current = currentNode; 
        int comparison = keyToFind.compareToIgnoreCase(currentNode.Key); 

        if (comparison == 0) {
            return true; 
        } else if (comparison < 0) {
            currentNode = currentNode.left; 
        } else {
            currentNode = currentNode.right; 
        }
    }
    return false; 
}


    public boolean insert(String k, T val) {
        if (root == null) {
            current = root = new BSTNode<T>(k, val);
            return true;
        }
        BSTNode<T> p = current;
        if (findKey(k)) {
            current = p;
            return false;
        }
        BSTNode<T> temp = new BSTNode<T>(k, val);
        if (k.compareToIgnoreCase(current.Key) < 0) {
            current.left = temp;
        } else {
            current.right = temp;
        }
        current = temp;
        return true;
    }

    public void inOrder() {
        if (empty()) {
            System.out.println("Tree is empty ");
        } else {
            inOrder(root);
        }
    }

    private void inOrder(BSTNode p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        ((Text) p.data).display();
        inOrder(p.right);
    }

    public void preOrder() {
        if (empty()) {
            System.out.println("empty tree ");
        } else {
            preOrder(root);
        }
    }

    private void preOrder(BSTNode p) {
        if (p == null) {
            return;
        }
        ((Text) p.data).display();
        preOrder(p.left);
        preOrder(p.right);
    }
}
