package axk190189;

import java.util.Iterator;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> extends SinglyLinkedList<T> {
    static class Entry<E> extends SinglyLinkedList.Entry<E> {
        Entry<E> prev;

        Entry(E x, Entry<E> next, Entry<E> prev) {
            super(x, next);
            this.prev = prev;
        }
    }
//    Entry<T> head, tail;
    DoublyLinkedList() {
        head = new Entry<T>(null, null, null);
        tail = head;
        size = 0;
    }

    public DLLIterator iterator() {
        return new DLLIterator();
    }

    protected class DLLIterator extends SinglyLinkedList.SLLIterator {

        DLLIterator() {
            super();
            cursor = head;
        }

        public boolean hasPrev() {
            return prev != null;
        }

        public T prev() {
            if (prev == null)
                throw new NoSuchElementException();
            ready = true;
            cursor = prev;
            prev = ((Entry<T>) cursor).prev;
            T prevElement = (T) cursor.element;
            return prevElement;
        }

        public void add(T x) {
            if (cursor.next == null)
                DoublyLinkedList.this.add(x);
            else {
                Entry<T> nextElement = (Entry<T>) cursor.next;
                Entry<T> prevElement = (Entry<T>) cursor;
                Entry<T> newElement = new Entry<T>(x, nextElement, prevElement);
                nextElement.prev = newElement;
                prevElement.next = newElement;
                size++;
            }
        }
       public void remove()
       {  if(!ready)
       {
           throw new NoSuchElementException();
       }
       Entry<T> nextElement = (Entry<T>)cursor.next;
       Entry<T> prevElement = (Entry<T>) prev;
       if(nextElement==null)
       {
           prevElement.next= null;
           tail = prevElement;
           cursor= prev;
           prev= prevElement.prev;
           size--;
           ready = false;
           return;
       }
       prevElement.next= nextElement;
       nextElement.prev= prevElement;
       cursor= prev;
       prev = prevElement.prev;
       ready=false;
       size--;
       }
    }

    public void add(T x) {
        add(new Entry<>(x, null, null));
    }

    public void add(Entry<T> ent) {
        tail.next = ent;
        ent.prev = (Entry<T>) tail;
        tail = ent;
        size++;
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> db = new DoublyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            db.add(Integer.valueOf(i));
        }
        DoublyLinkedList<Integer>.DLLIterator it = db.iterator();
        Scanner in = new Scanner(System.in);
        System.out.println("Press 1-for next  2-for prev  3-for remove 4-to add and any other number to exit");
        whileloop:
        while(in.hasNext()) {
            int com = in.nextInt();
            switch(com) {
                case 1:  // Move to next element and print it
                    if (it.hasNext()) {
                        System.out.println(it.next());
                    } else {
                        break whileloop;
                    }
                    break;
                case 2:   // Previous element and print it
                    if (it.hasPrev()) {
                        System.out.println(it.prev());
                    } else {
                        break whileloop;
                    }
                 break;
                case 3: // Remove element
                    it.remove();
                    System.out.println("Removed Element");
                    break;
                case 4: // Add an element
                    Scanner numInput = new Scanner(System.in);
                    System.out.println("Enter the number to be added");
                    int num = numInput.nextInt();
                    it.add(Integer.valueOf(num));
                    System.out.println("Added element "+num);
                    break;
                default:  // Exit loop
                    break whileloop;
            }
        }
        db.printList();

    }
}