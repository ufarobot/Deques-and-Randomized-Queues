import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by spec on 20.04.2017.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int n;

    public Deque() {
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException("input must be not null");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (last == null) last = first;
        else first.next.prev = first;
        n++;
    }

    public void addLast(Item item) {
        if (item == null) throw new NullPointerException("input must be not null");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        if (first == null) first = last;
        else last.prev.next = last;
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = first.item;
        n--;
        if (isEmpty()) {
            last = null;
            first = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
        Item item = last.item;
        n--;
        if (isEmpty()) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class Node {

        private Item item;
        private Node next;
        private Node prev;
    }
    private class ListIterator implements Iterator<Item> {

        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.removeLast();
        System.out.println(deque.isEmpty());
/*        for (int i = 0; i < 10; i++) {
            deque.addFirst(i);
            deque.addLast(i * 10);
        }
        System.out.println(deque.removeLast());
        System.out.println(deque.size());
        while (!deque.isEmpty()) {
            System.out.println(deque.removeFirst());
        }
        deque.addFirst(1);
        System.out.println(deque.removeFirst());
        deque.addFirst(2);
        System.out.println(deque.removeFirst());
        deque.addLast(0);
        deque.removeFirst();
        deque.addLast(3);
        deque.addLast(4);
        deque.removeFirst();
        deque.removeLast();
        deque.addFirst(8);*/
    }
}
