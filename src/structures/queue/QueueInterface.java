package structures.queue;

public interface QueueInterface<E> {

    public boolean isEmpty();

    public int size();

    public void enqueue(E e);

    public E dequeue() throws QueueException;

    public E front() throws QueueException;

    public E rear() throws QueueException;

}//End QueueInterface
