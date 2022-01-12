/*
Name: Damandeep Singh
Class account; cssc3255
Course: CS 310
Section: 2
 */

package data_structures;


import java.util.Iterator;

public class UnorderedArrayPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
    //instance variables
    private final int maxCapacity;
    private int currentSize;
    private final E[] array;

    public UnorderedArrayPriorityQueue() {

        this(DEFAULT_MAX_CAPACITY);
    }

    public UnorderedArrayPriorityQueue(int size) {
        currentSize = 0;
        maxCapacity = size;
        array = (E[]) new Comparable[maxCapacity];
    }

    @Override
    public boolean insert(E object) {
        if (isFull()) {return false;}
       //adds in the end of the array
        array[currentSize++] = object;
        return true;
    }

    @Override
    public E remove() {
        if (isEmpty()) {return null;}
       //holds the element with max priority
        E element = array[MaxPriority()];
        //shift elements left and decrement size
        for (int i = MaxPriority(); i < currentSize - 1; i++) {
            array[i] = array[i + 1];
        }
        currentSize--;
        return element;
    }
//method to return the index of the element with max priority
    private int MaxPriority() {
        int priority = 0;
        for (int i = 1; i < currentSize; i++) {
            // if the element on the left is greater than right we get the priority
            if (array[i].compareTo(array[priority]) < 0) {
                priority = i;
            }

        }
        return priority;
    }


    @Override
    public boolean delete(E obj) {
        if (!contains(obj)) {
            return false;
        } else {
            //traverses through the array
            for (int i = 0; i < currentSize; i++) {
                //if it encounters the element we shift elements to left and decrement size
                if (array[i].compareTo(obj) == 0) {
                    for (int j = i; j < currentSize - 1; j++) {
                        array[j] = array[j + 1];
                    }
                        currentSize--;

                }
            }
            }
            return true;
        }

    @Override
    public E peek() {
        if (isEmpty()) {return null;}
       //returns the element with maximum priority
        return array[MaxPriority()];
    }

    @Override
    public boolean contains(E obj) {
        //performs linear search and compares each element and returns true if found
        for (int i = 0; i < currentSize; i++) {
            if (obj.compareTo(array[i]) == 0) {return true;}
        }
        return false;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public void clear() {
        currentSize = 0;
    }

    @Override
    public boolean isEmpty() {
        return (currentSize == 0);
    }

    @Override
    public boolean isFull() {
        return (size() == maxCapacity);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return (index != currentSize);
            }

            @Override
            public E next() {
                return array[index++];
            }
        };
    }

}
