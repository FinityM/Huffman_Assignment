package algorithm;

public class Node {
    private Object item;
    private Node next;

    public Node(Object newItem) {
        item = newItem;
        next = null;
    } // end constructor

    public Node(Object newItem, Node nextNode) {
        item = newItem;
        next = nextNode;
    } // end constructor

    public Object getItem() {
        return item;
    } // end getItem

    public void setItem(Object newItem) {
        item = newItem;
    } // end setItem

    public Node getNext() {
        return next;
    } // end getNext

    public void setNext(Node nextNode) {
        next = nextNode;
    } // end setNext

} // end class Node

