package Deque;

public class EmptyQueueException extends Exception{

    public EmptyQueueException(String errorMessage){
        super(errorMessage);
    }

    public EmptyQueueException() {
        super();
    }
}
