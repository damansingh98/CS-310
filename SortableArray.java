/*
Name- Damandeep Singh
class account: cssc3255
Course: CS310
Section: 2
 */

package data_structures;
import java.io.*;


public class SortableArray implements SortableArrayInterface {
    //array instance
   private final int[] arr;
   //size instance
   private int size;
    //Default constructor
    public SortableArray() {
        this(DEFAULT_MAX_CAPACITY);
    }
  //Constructor with single parameter
    public SortableArray(int size) {
        arr = new int[size];
    }

    @Override
    public boolean add(int element) {
        //uses the add(index, element) method to add the element according to the current size
     return add(size(), element);
    }

    @Override
    public boolean add(int index, int element) {
        if (index < 0 || index > size()){
            throw new IndexOutOfBoundsException("Invalid Index");
        }
        // shift the element right in worst case
        for (int i = size; i > index; i--){
            arr[i] = arr[i-1];
        }
        arr[index] = element;
        size++;

        return true;
    }

    @Override
    public int get(int index) {
        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return arr[index] ;
    }

    @Override
    public int remove(int index) {
         //if input is less than 0 or greater than size
        if(index < 0 || index > size()){
            throw new IndexOutOfBoundsException("Invalid index");

        }
        // if size is 0 throw exception
        if(size() == 0){
            throw new IndexOutOfBoundsException("Array is empty");
        }
        //store the data at index
        int val = arr[index];
        //shift elements left in worst case
       for (int i = index; i < size-1; i++){
           arr[i] = arr[i+1];
       }
        //decrement size
        size--;
        return val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean readFile(String filename) {
        //flag for boolean values
        boolean flag = true;
        //Buffereader to read the text file data
        BufferedReader br = null;
        try {
            //initialize the buffer reader and file reader
            br = new BufferedReader(new FileReader(filename));

            String s = br.readLine(); //store the data in a string
            while (s != null){ //as long as string is not null (did not reach the end)
                //store the data in the array using
                arr[size] = Integer.parseInt(s);
                //increment size each time;
                size++;
                //move to next line each time
                s = br.readLine();
            }
        }catch (IOException e){
            //in case of exception flag is false
            flag =false;
        }
        //lastly close the buffer reader
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                flag = false;
            }

        return flag;
    }

    @Override
    public boolean saveFile(String filename) {
        //boolean value for return
        boolean flag = true;
        //printwriter to write data
        PrintWriter pw = null;
       try {

           pw = new PrintWriter(filename);
           //print each element in the array into the file
           for (int i = 0; i < size; i++) {
               pw.println(get(i));
           }
       }catch (IOException e){
           // In case of exception, boolean value is false
           flag = false;
       }

       if(pw != null){ //close the print writer
           pw.close();
       }

        return flag;

    }

    @Override
    public long insertionSort() {
        //declare and initialize time
        long start = System.nanoTime();

         int i, j, temp;
         //starting from the second element in the list iterate until the end
        for (i = 1; i < size(); i++){
            // save the data in a temp variable
            temp = get(i);
            //j pointer is used to keep track of previous data location
            j = i;
            while (j > 0 && arr[j-1] > temp){ //as long as j is not 0 and left element is greater than right element
             //swap
                arr[j] = arr[j-1];
                //decrement j for next swap
                j--;
            }
            //otherwise don't swap
            arr[j] = temp;

        }
       long end = System.nanoTime();
        //returns time in milliseconds
        return (end-start)/1000000L;
    }

}
