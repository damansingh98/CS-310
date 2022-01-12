package data_structures;

/*
 * SortableArray interface for use in Programming Assignment PA1
 * Concrete class should have an integer array as an instance variable
 * Throw exceptions when user attempts to access indexes less than zero or greater
 * than the number of elements.
 */
public interface SortableArrayInterface{
    int DEFAULT_MAX_CAPACITY = 1024;

    // Returns true if the element was added to the first available position of the array
    boolean add(int element);

    // Inserts the specified element at the specified position in this list
    // Shifts the element currently at that position and any subsequent elements to the right
    // Returns true if element added correctly.
    // Must observe 0 <= index <= size.
    boolean add(int index, int element);

    // Returns the element at the specified position in the array
    // Throw exception if this array element has not been initialized
    int get(int index);

    // Removes and returns the element at the specified position in the array
    int remove(int index);

    // Returns the number of elements in the array
    int size();

    // Returns true if able to read and store specificed file contents into array
    boolean readFile(String filename);

    // Returns true if able to store array contents into specified file
    boolean saveFile(String filename);

    // Sort the elements of the array using insertion sort
    // Return the time in ms for finishing the insertSort
    long insertionSort();

}
