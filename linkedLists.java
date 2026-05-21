import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic singly linked list that supports iteration.
 *
 * @param <T> the type of elements stored in the list
 */
public class linkedLists<T> implements Iterable<T> {

    /**
     * Node class representing a single element in the list.
     */
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;   // First element
    private Node<T> tail;   // Last element
    private int size;       // Number of elements

    // INSERTION METHODS
    /** Insert at the head (front). O(1) */
    public void insertAtHead(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;

        if (tail == null) {
            tail = newNode;
        }
        size++;
    }

    /** Insert at the tail (end). O(1) */
    public void insertAtTail(T data) {
        Node<T> newNode = new Node<>(data);

        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

 
    // REMOVAL METHODS
    /** Remove from head. O(1) */
    public T removeFromHead() {
        if (head == null) return null;

        T value = head.data;
        head = head.next;

        if (head == null) {
            tail = null;
        }
        size--;
        return value;
    }

    /** Remove from tail. O(n) */
    public T removeFromTail() {
        if (head == null) return null;

        if (head == tail) {
            T value = head.data;
            head = tail = null;
            size--;
            return value;
        }

        Node<T> current = head;
        while (current.next != tail) {
            current = current.next;
        }

        T value = tail.data;
        tail = current;
        tail.next = null;
        size--;
        return value;
    }

   
    // SEARCH & REMOVE
    /** Find first occurrence. O(n) */
    public boolean find(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) return true;
            current = current.next;
        }
        return false;
    }

    /** Remove first occurrence. O(n) */
    public boolean removeFirstOccurrence(T data) {
        if (head == null) return false;

        if (head.data.equals(data)) {
            removeFromHead();
            return true;
        }

        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                if (current.next == tail) {
                    tail = current;
                }
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }


    /* REVERSE IN-PLACE */ 
    /** Reverse the list in-place. O(n) */
    public void reverse() {
        Node<T> prev = null;
        Node<T> current = head;
        tail = head;

        while (current != null) {
            Node<T> nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        head = prev;
    }

    
    // ITERATOR IMPLEMENTATION
    /**
     * Returns an iterator that walks through the list from head to tail.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Node<T> current = head;

            /** Returns true if another element exists */
            @Override
            public boolean hasNext() {
                return current != null;
            }

            /** Returns next element and advances pointer */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = current.data;
                current = current.next;
                return value;
            }
        };
    }

    /** Utility method for printing */
    public void printList() {
        for (T item : this) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public int size() {
        return size;
    }
}
