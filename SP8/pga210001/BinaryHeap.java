// Starter code for SP8

// Change to your netid
package pga210001;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class BinaryHeap<T extends Comparable<? super T>> {
    Comparable[] pq;
    int size;

    // Constructor for building an empty priority queue using natural ordering of T
    public BinaryHeap(int maxCapacity) {
        pq = new Comparable[maxCapacity];
        size = 0;
    }

    // add method: resize pq if needed
    public boolean add(T x) {
        if (size() == pq.length - 1) {
            resize();
        }

        int position = size++;
        move(position, x);
        percolateUp(position);
        return true;
    }

    public boolean offer(T x) {
        return add(x);
    }

    // throw exception if pq is empty
    public T remove() throws NoSuchElementException {
        T result = poll();
        if (result == null) {
            throw new NoSuchElementException("Priority queue is empty");
        } else {
            return result;
        }
    }

    // return null if pq is empty
    public T poll() {
        if (!isEmpty()) {
            Comparable root = peek();
            move(0, pq[size() - 1]);
            move(size - 1, null);
            size--;
            percolateDown(0);
            return (T) root;
        }
        return null;
    }

    public T min() {
        return peek();
    }

    // return null if pq is empty
    public T peek() {
        return isEmpty() ? null : (T) pq[0];
    }

    int parent(int i) {
        return (i - 1) / 2;
    }

    int leftChild(int i) {
        return 2 * i + 1;
    }

    /** pq[index] may violate heap order with parent */
    void percolateUp(int index) {
        Comparable inserted = pq[index];
        while (index > 1 && compare(inserted, pq[index / 2]) < 0) {
            move(index, pq[index / 2]);
            index = index / 2;
        }
        move(index, inserted);
    }

    /** pq[index] may violate heap order with children */
    void percolateDown(int index) {
        int left_child = leftChild(index);
        while (left_child < size()) {
            if (left_child < size() - 1 && compare(pq[left_child], pq[left_child + 1]) > 0) {
                left_child++;
            }
            if (compare(pq[index], pq[left_child]) > 0) {
                Comparable temp = pq[index];
                move(index, pq[left_child]);
                move(left_child, temp);
                index = left_child;
                left_child *= 2;
            } else {
                break;
            }
            left_child++;
        }
    }

    /**
     * use this whenever an element moved/stored in heap. Will be overridden by
     * IndexedHeap
     */
    void move(int dest, Comparable x) {
        pq[dest] = x;
    }

    int compare(Comparable a, Comparable b) {
        return ((T) a).compareTo((T) b);
    }

    /** Create a heap. Precondition: none. */
    void buildHeap() {
        for (int i = parent(size - 1); i >= 0; i--) {
            percolateDown(i);
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    // Resize array to double the current size
    void resize() {
        Comparable[] old_pq = pq;
        pq = new Comparable[pq.length * 2];
        System.arraycopy(old_pq, 0, pq, 0, size);
    }

    public interface Index {
        public void putIndex(int index);

        public int getIndex();
    }

    // IndexedHeap is useful to implement algorithms, such as Prim's MST, that
    // requires
    // decreaseKey() operation. You can implement this now or later when you
    // implement MST algorithms
    public static class IndexedHeap<T extends Index & Comparable<? super T>> extends BinaryHeap<T> {
        /** Build a priority queue with a given array */
        IndexedHeap(int capacity) {
            super(capacity);
        }

        /** restore heap order property after the priority of x has decreased */
        void decreaseKey(T x) {
        }

        @Override
        void move(int i, Comparable x) {
            super.move(i, x);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = { 4, 3, 5, 3, 6, 23, 1, 0, 89 };
        BinaryHeap<Integer> h = new BinaryHeap(arr.length);

        System.out.print("Before:");
        for (Integer x : arr) {
            h.offer(x);
            System.out.print(" " + x);
        }
        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = h.poll();
        }

        System.out.print("After :");
        for (Integer x : arr) {
            System.out.print(" " + x);
        }
        System.out.println();
    }
}
