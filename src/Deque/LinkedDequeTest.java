package Deque;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Iterator;

class LinkedDequeTest {

    @Test
    public void addTest() {
        LinkedDeque<String> temp = new LinkedDeque<>();

        temp.add("Hey");
        Assertions.assertTrue(temp.toString().equals("\nFront -> Back\n{ Hey -> }\nBack -> Front\n{ Hey -> }\n"));

        temp.addFront("Never");
        Assertions.assertTrue(temp.toString().equals("\nFront -> Back\n{ Never -> Hey -> }\nBack -> Front\n{ Hey -> Never -> }\n"));


        temp.addBack("Wanna");
        temp.toString().equals("\nFront -> Back\n{ Never -> Hey -> Wanna -> }\nBack -> Front\n{ Wanna -> Hey -> Never -> }\n");

        temp.add("Riko");
        temp.toString().equals("\nFront -> Back\n{ Never -> Hey -> Wanna -> Riko -> }\nBack -> Front\n{ Riko -> Wanna -> Hey -> Never -> }\n");
    }

    @Test
    public void peekTest() throws EmptyQueueException {
        LinkedDeque<Integer> temp = new LinkedDeque<>();

        temp.add(1);
        Assertions.assertEquals(1, temp.peek());

        temp.addBack(2);
        Assertions.assertEquals(2, temp.peek());

        temp.addFront(3);
        Assertions.assertEquals(2, temp.peek());
    }

    @Test
    public void popTest() throws EmptyQueueException {
        LinkedDeque<Boolean> temp = new LinkedDeque<>();

        for(int i = 0; i < 5; i++){
            temp.add(i%2 == 0);
        }

        Assertions.assertEquals(true, temp.pop());
        Assertions.assertEquals(false, temp.pop());
        Assertions.assertEquals(true, temp.pop());
        Assertions.assertEquals(false, temp.pop());
        Assertions.assertEquals(true, temp.pop());

        Assertions.assertEquals(0, temp.size());
    }

    @Test
    public void emptyTest(){
        LinkedDeque<String> temp = new LinkedDeque<>();

        Assertions.assertThrows(EmptyQueueException.class ,() -> temp.peek());
        Assertions.assertThrows(EmptyQueueException.class, () -> temp.peekBack());
        Assertions.assertThrows(EmptyQueueException.class, () -> temp.popFront());
        Assertions.assertThrows(EmptyQueueException.class, () -> temp.pop());
        Assertions.assertThrows(EmptyQueueException.class, () -> temp.popFront());
        Assertions.assertThrows(EmptyQueueException.class, () -> temp.popBack());
    }

    @Test
    public void iteratorTest(){

        LinkedDeque<String> temp = new LinkedDeque<>();

        temp.add("now");
        temp.add("you");
        temp.add("feel");
        temp.add("like");
        temp.add("number");
        temp.add("one");

        Iterator<String> iter = temp.iterator();

    }

    @Test
    public void iteratorRemoveTest(){

        LinkedDeque<Character> temp = new LinkedDeque<Character>();

        int val = 65;

        for(int i = 0; i < 26; i++){
            temp.add((char) (val+i));
        }

        Assertions.assertTrue(temp.toString().equals("\nFront -> Back\n" +
                "{ Z -> Y -> X -> W -> V -> U -> T -> S -> R -> Q -> P -> O -> N -> M -> L -> K -> J -> I -> H -> G -> F -> E -> D -> C -> B -> A -> }\n" +
                "Back -> Front\n" +
                "{ A -> B -> C -> D -> E -> F -> G -> H -> I -> J -> K -> L -> M -> N -> O -> P -> Q -> R -> S -> T -> U -> V -> W -> X -> Y -> Z -> }\n"));

        Iterator<Character> iter = temp.iterator();

        while(iter.hasNext()){
            iter.next();
            iter.remove();
        }

        Assertions.assertTrue(temp.isEmpty());

        for(int i = 0; i < 26; i++){
            temp.add((char) (val+i));
        }

        iter = temp.iterator();

        while(iter.hasNext()){
            iter.remove();
            iter.next();
        }

        Assertions.assertTrue(temp.toString().equals("\nFront -> Back\n" +
                "{ Z -> }\n" +
                "Back -> Front\n" +
                "{ Z -> }\n"));
    }

    @Test
    public void testEmpty() throws EmptyQueueException {
        LinkedDeque<Integer> temp = new LinkedDeque<>();

        Assertions.assertTrue(temp.isEmpty());

        temp.add(0);

        temp.removeBack();

        Assertions.assertTrue(temp.isEmpty());
    }

}