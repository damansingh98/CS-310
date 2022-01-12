
/*
Name: Damandeep Singh
Class account; cssc3255
Course: CS 310
Section: 2
 */

package data_structures;

import java.util.Iterator;


public class BinaryHeapPriorityQueue<E extends  Comparable<E>> implements PriorityQueue<E> {
    private final int maxCapacity;
    private int currentSize;
    private final E[] heap;

    public BinaryHeapPriorityQueue() {
        this(DEFAULT_MAX_CAPACITY);
    }

    public BinaryHeapPriorityQueue(int size) {
        currentSize = 0;
        maxCapacity = size;
        heap = (E[]) new Comparable[maxCapacity];
    }

//returns parent index
    private int parent(int index) {
        if (index <= 0) {
            return index;
        }
        return (index-1) / 2;
    }

//swaps two elements given the indices
    private void swap(int a, int b) {
        E data = heap[a];
        heap[a] = heap[b];
        heap[b] = data;
    }

    @Override
    public boolean insert(E object) {
        if (isFull()) {
            return false;
        }
        // if PQ is empty it adds the element in as first element
        if (isEmpty()) {
            heap[parent(0)] = object;
            currentSize++;
        }
        else {
            //otherwise we add the element and increase size
                heap[currentSize++] = object;
                int index = currentSize-1;
                //adjust elements
                while (index != 0) {
                    // get the parent element and child element as per index
                    E newParent = heap[parent(index)];
                    E newChild = heap[index];
                    // swap if parent is greater than child
                    if (newParent.compareTo(newChild) > 0) {
                        swap(parent(index), index);
                    }
                    //move to next index
                    index--;
                }
        }
        return true;
    }


    @Override
    public E remove() {
        if (isEmpty()) {
            return null;
        }
        //grab the first element
        E data = heap[0];
        //last element becomes the first element
        heap[0] = heap[currentSize-1];
        //decrement size to eliminate the element
        currentSize--;
        int index = currentSize-1;
        //adjust elements again
        while (index > 0) {
            E newParent = heap[parent(index)];
            E newChild = heap[index];

            if (newParent.compareTo(newChild) > 0) {
                swap(parent(index), index);
            }
            index--;
        }

        return data;
    }

    @Override
    public boolean delete(E obj) {

        if (!contains(obj)) {
            return false;
        }
        else {
            //uses linear search to locate all instances and swaps it with last element
           for (int i = 0; i < currentSize; i++){
               if(heap[i].compareTo(obj) == 0){
                   heap[i] = heap[currentSize-1];
                   currentSize--;
               }
           }
           //adjust elements again
            int index = currentSize-1;
            while (index > 0) {
                E newParent = heap[parent(index)];
                E newChild = heap[index];

                if (newParent.compareTo(newChild) > 0) {
                    swap(index, parent(index));
                }
                index--;
            }

        }
        return true;
        }


    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        //returns root element
        return heap[parent(0)];
    }

    @Override
    public boolean contains(E obj) {
        //performs a linear search and compares each element and returns true if present
        for (int i = 0; i < currentSize; i++) {
            if (heap[i].compareTo(obj) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        //returns the current size
        return currentSize;
    }

    @Override
    public void clear() {
        //removes everything in the priority Queue
        currentSize = 0;

    }

    @Override
    public boolean isEmpty() {
        //returns true if current size is 0 otherwise false
        return (currentSize == 0);
    }

    @Override
    public boolean isFull() {
        //returns true if current size equals the max capacity otherwise false
        return (currentSize == maxCapacity);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            //initialize index
            int index = 0;

            @Override
            public boolean hasNext() {
                //returns false if we have not reached the end of the heap
                return (index != size());
            }

            @Override
            public E next() {
                //returns elements with index incrementation
                return heap[index++];
            }
        };
    }


}


