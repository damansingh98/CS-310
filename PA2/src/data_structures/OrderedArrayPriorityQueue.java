/*
Name: Damandeep Singh
Class account; cssc3255
Course: CS 310
Section: 2
 */

package data_structures;


import java.util.Iterator;


public class OrderedArrayPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
//Declare the instance variables such as array, max capacity and current size
    private final int maxCapacity;
    private int currentSize;
    private  E[] array;

    public OrderedArrayPriorityQueue() {
        this(DEFAULT_MAX_CAPACITY);
    }

    public OrderedArrayPriorityQueue(int size) {
        currentSize = 0;
        maxCapacity = size;
        array = (E[]) new Comparable[maxCapacity];

    }
 //uses binary search to find the next insertion index (iterative)
private int BinarySearchforInsertion(E[] array, E object, int low , int high){
     while(low <= high){
         int mid = low + (high-low)/2;
         if(object.compareTo(array[mid]) < 0){
             high = mid-1;
         }
         else if(object.compareTo(array[mid]) > 0){
             low = mid+1;
         }
         else {
             return mid;
         }
     }
     return low;
        }

    @Override
    public boolean insert(E object) {
        if(isFull()) {return false;}
        //grabs the insertion point
        int insertObj = BinarySearchforInsertion(array, object, 0, currentSize-1);
        //shift right
        for (int i = currentSize; i > insertObj; i--){
            array[i] = array[i-1];
        }
        //insert object at that insertion point and increment size
        array[insertObj] = object;
        currentSize++;
        return true;


    }


    @Override
    public E remove() {
        if (isEmpty()) {return null;}
        //hold the data of first element
        int i = 0;
     E data = array[i];
     //shift left  and decrement size
        for (int j = i; j < currentSize-1; j++){
          array[j] = array[j+1];

        }
        --currentSize;

     return data;
    }
// binary search for delete operation uses recursive approach
   private int BSforDeletion(E[] array, E object, int low, int high){
        if(low > high) return -1;
        else{
            int mid = (low+high)/2;
            if(object.compareTo(array[mid]) == 0)
                return mid;
            else if(object.compareTo(array[mid]) < 0){
                return BSforDeletion(array, object, low, mid-1);

            }else{
                return BSforDeletion(array, object, mid+1, high);
            }
        }
   }
    @Override
    public boolean delete(E obj) {
        // we continuously search for all elements that are the same
     for (int i = 0; i < currentSize; i++){
         int deletePoint = BSforDeletion(array, obj, 0, currentSize-1);
         if(deletePoint == -1) {
             return false;
         }
         //we shift elements to left and decrement size
         for (int j = deletePoint; j < currentSize-1; j++){
             array[j] = array[j+1];
         }
         currentSize--;
     }
        return true;
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
       //returns first element in the array (highest priority)
        return array[0];
    }

    @Override
    public boolean contains(E obj) {
        //uses binary search (iterative)
     int low = 0;
     int high  = currentSize-1;
     if (low > high) return false;
    while (low <= high){
        int mid = low + (high-low)/2;
        if(obj.compareTo(array[mid]) == 0)
            return true;
        else if(obj.compareTo(array[mid]) < 0)
            high = mid-1;
        else {
            low = mid+1;
        }
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
        return (currentSize == maxCapacity);
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








