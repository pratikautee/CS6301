package idsa;
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

    Entry<T> head, tail;

    DoublyLinkedList(){
        head = new Entry(null,null,null);
        tail = head;
        size = 0;
    }

    public DLLIterator iterator() { return new DLLIterator(); }
    protected class DLLIterator extends SinglyLinkedList.SLLIterator {

        DLLIterator()
        {super();
         cursor = head;

        }
        public boolean hasPrev()
        {return prev!=null;
        }
        public T prev() {
            if(prev==null)
                throw new NoSuchElementException();
            cursor= prev;
           prev = ((Entry) cursor).prev;
           return (T)cursor.element;
        }

       public void add (T x)
       {  if(cursor.next==null)
            DoublyLinkedList.this.add(x);
          else
        {
         Entry<T> newElement = new Entry<T>(x,(Entry<T>)cursor.next,(Entry<T>)cursor );
            ((Entry<T>)newElement.next).prev = newElement;
            cursor.next= newElement;
         size++;
        }
       }
    }

    public void add(T x) {
        add(new Entry<>(x, null,null));
    }

    public void add(Entry<T> ent) {
        tail.next= ent;
        ent.prev= tail;
        tail = ent;
        size++;
    }
    public void printList() {
        System.out.print(this.size + ": ");
        for(T item: this) {
            System.out.print(item + " ");
        }

        System.out.println();
    }


   public static void main(String[]args)
   {
       DoublyLinkedList<Integer> db = new DoublyLinkedList<>();

       for(int i =0;i<10;i++)
       {
           db.add(Integer.valueOf(i));
       }

       DoublyLinkedList<Integer>.DLLIterator it = db.iterator();
       it.next();
       it.next();
       it.next();
       it.next();
       it.next();
       it.next();
       it.next();
       it.next();
       it.next();
       it.next();
       it.add(Integer.valueOf(69));

       db.printList();



   }
}
