package Deque;

public interface DequeInterface <T> {

    public T getData();

    public void setData(T data);

    public LinkedDeque.DLNode getNextNode();

    public void setNextNode(LinkedDeque.DLNode nextNode);

    public LinkedDeque.DLNode getPrevNode();

    public void setPrevNode(LinkedDeque.DLNode prevNode);


}
