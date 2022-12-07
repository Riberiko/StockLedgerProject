package Deque;

import java.util.Iterator;

public class LinkedDeque <T> implements Iterable<T> {

    private int size;

    DLNode frontNode;
    DLNode backNode;

    /**
     * Creates a linkedDeque with empty values
     */
    public LinkedDeque(){
        frontNode = new DLNode();
        backNode = frontNode.getBackNode();
        size = 0;
    }

    /**
     * Retrieves the size of the LinkedDeque
     * @return
     */
    public int size(){
        return size;
    }


    /**
     * Adds the data in the correct spot in order of least to greatest.
     * @param data  to be added
     */
    public void add(T data){
        addFront(data);
    }


    /**
     * Adds the data values to the front
     *
     * @param data  to be added
     */
    public void addFront(T data) {
        if(frontNode.getData() == null){    //checks to see if the first node is empty
            size += 1;
            frontNode.setData(data);
            return;
        }
        size += 1;  //continues here only when first node has value
        DLNode front = frontNode.getFrontNode();
        front.setNextNode(new DLNode(data, null, front));
        frontNode = front.nextNode;
    }

    /**
     * Adds the data values to the back
     *
     * @param data to be added
     */
    public void addBack(T data) {
        if(backNode.getData() == null){ //ensures the back node has a value
            size += 1;
            backNode.setData(data);
            return;
        }
        size += 1;  //continues here only if the back node has a value
        DLNode back = backNode.getBackNode();
        back.setPrevNode(new DLNode(data, back, null));
        backNode = back.getBackNode();
    }

    /**
     * Essentially peekBack
     *
     * @return  the value in the back
     * @throws EmptyQueueException
     */
    public T peek() throws EmptyQueueException {
        return peekBack();
    }

    /**
     * Looks at the value at the back
     *
     * @return  the value
     * @throws EmptyQueueException
     */
    public T peekBack() throws EmptyQueueException {
        if(isEmpty()) throw new EmptyQueueException();
        return (T) backNode.getData();
    }

    /**
     * Looks at the value at the front
     *
     * @return  the value
     * @throws EmptyQueueException
     */
    public T peekFront() throws EmptyQueueException {
        if(isEmpty()) throw new EmptyQueueException();
        return (T) frontNode.getData();
    }

    /**
     * Essentially popBack
     *
     * @return the removed value from the back
     * @throws EmptyQueueException
     */
    public T pop() throws EmptyQueueException {
        return popBack();
    }

    /**
     * Removes what ever is at thr Back
     *
     * @return  the removed value
     * @throws EmptyQueueException
     */
    public T popBack() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException();
        T temp = (T) backNode.getData();
        backNode.remove();
        return temp;
    }

    /**
     * Removes what ever is at the front
     *
     * @return  The removed value
     * @throws EmptyQueueException
     */
    public T popFront() throws EmptyQueueException {
        if(isEmpty()) throw new EmptyQueueException();
        T temp = (T) frontNode.getData();
        frontNode.remove();
        return temp;

    }

    /**
     * Whether empty or not
     *
     * @return  true when empty
     */
    public boolean isEmpty(){
        return (size<1 && frontNode.prevNode == null && backNode.nextNode == null && frontNode.getData() == null);  //ensure actually empty
    }


    public Iterator<T> iterator(){
        return new IteratorForLinkedList<>(this);
    }

    /**
     * Removes from the front
     */
    public void removeFront() throws EmptyQueueException {

        if (size == 0) throw new EmptyQueueException();

        if(frontNode.getPrevNode() != null){
            frontNode = frontNode.getPrevNode();
            frontNode.nextNode = null;
        }else frontNode.setData(null);

        size -= 1;

    }

    /**
     * Removes from the back
     */
    public void removeBack() throws EmptyQueueException {

        if (size == 0) throw new EmptyQueueException();

        if(backNode.getNextNode() != null){
            backNode = backNode.getNextNode();
            backNode.prevNode = null;
        }else backNode.setData(null);

        size -= 1;
    }


    /**
     * String representation of object
     *
     * @return the string
     */
    public String toString(){
        String str = "";
        DLNode fb = frontNode;
        DLNode bf = backNode;

        str += "\nFront -> Back\n{ ";       //deals with the forward print

        if(!isEmpty() && fb != null) {
            do {
                str += fb.getData() + " -> ";
                fb = (fb.hasPrev()) ? fb.getPrevNode() : null;
            } while (fb != null);
        }

        str += "}\n";


        str += "Back -> Front\n{ ";     //deals with the backwards print

        if(!isEmpty() && bf != null){
            do {
                str += bf.getData() + " -> ";
                bf = (bf.hasNext()) ? bf.getNextNode() : null;
            } while (bf != null);
        }

        str += "}\n";

        return str;
    }

    public class DLNode<T> implements DequeInterface<T>{

        private T data;
        private DLNode nextNode;
        private DLNode prevNode;

        /**
         * Creates a node with null data and null next and prev
         */
        private DLNode(){
            this(null);
        }

        /**
         * Creates a node with data as data and next and pre as null
         * @param data
         */
        private DLNode(T data){
            this(data, null, null);
        }

        /**
         * Creates a node with data as data, nextNode as the next node, and prevNode as the previous node
         * @param data
         * @param nextNode
         * @param prevNode
         */
        private DLNode(T data, DLNode nextNode, DLNode prevNode){
            this.data = data;
            this.nextNode = nextNode;
            this.prevNode = prevNode;
        }


        /**
         * Removes the node from the list
         */
        public void remove(){
            if(isEmpty()) return;

            setData(null);

            if(hasPrev()){
                prevNode.nextNode = nextNode;
                resolveFront(prevNode);
            }

            if(hasNext()){
                nextNode.prevNode = prevNode;
                resolveBack(nextNode);
            }

            size -= 1;

        }

        /**
         * Ensure the back and front node point to the right places
         */
        private void resolveFront(DLNode node){
            DLNode temp = node;

            while(temp.hasNext()){
                temp = temp.getNextNode();
            }

            backNode = temp;
        }

        private void resolveBack(DLNode node){

            DLNode temp = node;

            while(temp.hasPrev()){
                temp = temp.getPrevNode();
            }

            backNode = temp;
        }

        /**
         * Find out if there is a node ahead of this one
         *
         * @return  true when there is a node
         */
        public boolean hasNext(){
            return (this.nextNode == null) ? false : true;
        }

        /**
         * Find out if there is a node behind this one
         *
         * @return  true when there is a node
         */
        public boolean hasPrev(){
            return (this.prevNode == null) ? false : true;
        }

        /**
         * Retrieves the back node
         *
         * @return  back node
         */
        public DLNode getBackNode(){

            DLNode temp = this;

            while(temp.hasPrev()){
                temp = temp.getPrevNode();
            }
            return temp;
        }

        /**
         * Retrieves the front node
         *
         * @return  front node
         */
        public DLNode getFrontNode(){

            DLNode temp = this;

            while(temp.hasNext()){
                temp = temp.getNextNode();
            }
            return temp;

        }

        public String toString(){
            String str = "Next = " + ((getPrevNode() != null) ? getPrevNode().getData() : "Null");
            str += "    Previous = " + ((getNextNode() != null) ? getNextNode().getData() : "Null");

            return str;
        }


        /**
         * {@inheritDoc}
         */
        @Override
        public T getData(){
            return data;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setData(T data){
            this.data = data;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LinkedDeque.DLNode getNextNode(){
            return nextNode;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setNextNode(LinkedDeque.DLNode nextNode) {
            this.nextNode = nextNode;
            nextNode.prevNode = this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LinkedDeque.DLNode getPrevNode(){
            return prevNode;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setPrevNode(LinkedDeque.DLNode prevNode) {
            this.prevNode = prevNode;
            prevNode.nextNode = this;
        }

    }

    public class IteratorForLinkedList<T> implements Iterator<T>{

        private DLNode cursor;
        private boolean firstViewed;

        public IteratorForLinkedList(LinkedDeque linkedDeque){
            this.cursor = linkedDeque.backNode;
            firstViewed = false;
        }

        /**
         * Validates if there is a next value
         *
         * @return  true when there is a next node
         */
        @Override
        public boolean hasNext() {
            if(!firstViewed) return cursor.data != null;
            return cursor.hasNext();
        }

        /**
         * Retrieves the value of the next node
         *
         * @return  the data
         */
        @Override
        public T next() {
            if(!firstViewed){
                firstViewed = true;
                return (T) cursor.data;
            }
            cursor = cursor.getNextNode();
            return (T) cursor.getData();
        }

        /**
         * Removes from the deque
         */
        @Override
        public void remove(){
            if(size() < 1) return;

            if(cursor.prevNode != null && cursor.prevNode.nextNode != cursor) return;

            if(cursor.nextNode != null && cursor.nextNode.prevNode != cursor) return;

            cursor.remove();

            if(!firstViewed && cursor.hasNext()) cursor = cursor.nextNode;
        }

        public String toString(){
            return cursor.toString();
        }
    }
}
