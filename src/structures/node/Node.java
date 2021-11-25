package structures.node;

public class Node<T> {

    private T item;
    private Node<T> next;
    private Node<T> previous;
    private int position;

    public Node(T item) {
        this.item = item;
        next = null;
        previous = null;
        position = 0;
    }//End Constructor

    public T getItem() {
        return item;
    }//End getItem

    public void setItem(T item) {
        this.item = item;
    }//End setItem

    public Node<T> getNext() {
        return next;
    }//End getNext

    public void setNext(Node<T> next) {
        this.next = next;
    }//End setNext

    public Node<T> getPrevious() {
        return previous;
    }//End getPrevious

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }//End setPrevious

    public int getPosition() {
        return position;
    }//End getPosition

    public void setPosition(int position) {
        this.position = position;
    }//End setPosition

}//End Node class
