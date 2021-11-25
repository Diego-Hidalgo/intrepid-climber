package structures.queue;

import node.Node;

public class Queue<E> implements QueueInterface<E> {

    private int size;
    private Node<E> front;
    private Node<E> rear;

    public Queue() {
        size = 0;
        front = null;
        rear = null;
    }//End Constructor

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }//End isEmpty

    @Override
    public int size() {
        return size;
    }//End size

    @Override
    public void enqueue(E e) {
        Node<E> toEnqueue = new Node<E>(e);
        if(rear == null)
            rear = toEnqueue;
        rear.setPrevious(toEnqueue);
        rear = rear.getPrevious();
        size ++;
        if(size == 1)
            front = rear;
    }//End enqueue

    @Override
    public E dequeue() throws QueueException {
        if(front == null)
            throw new QueueException("Unable to dequeue the front element because the queue is empty");
        Node<E> toDequeue = front;
        front = front.getPrevious();
        size--;
        return toDequeue.getItem();
    }//End dequeue

    @Override
    public E front() throws QueueException {
        if(front == null)
            throw new QueueException("Unable to get the front element because the queue is empty");
        return front.getItem();
    }//End front

    @Override
    public E rear() throws QueueException {
        if(rear == null)
            throw new QueueException("Unable to get the rear element because the queue is empty");
        return rear.getItem();
    }//End rear

    public Queue<E> reverse() {
        Queue<E> reversed = new Queue<E>();
        Node<E> aux = front;
        while(aux != null) {
            reversed.enqueue(aux.getItem());
            aux = aux.getPrevious();
        }//End while
        return reversed;
    }//End reverse

}//End Node class
