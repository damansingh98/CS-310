import data_structures.BinaryHeapPriorityQueue;
import data_structures.PriorityQueue;

import java.util.Arrays;

public class HeapTest {
        private final PriorityQueue<Integer> binaryHeapPQ;
    private final PriorityQueue<String> binaryHeapDefPQ;
    private final int iSize = 10;

    // this array is used for default constructor tests
    String[] mostVisitedCities = {"Hong Kong", "BangKok", "London", "Macau", "Singapore", "Paris", "Dubai",
            "New York", "Kuala Lumpur","Istanbul","Delhi","Antalya", "Shenzhen","Mumbai","Phuket",
            "Rome", "Tokyo", "Pattaya","Taipei","Mecca"};

    // this array is used for parameterized constructor tests
    int[] mostCommonNumbers = {1,23,8,12,3,4,21,22,10,2};

    public HeapTest() {
                binaryHeapPQ = new BinaryHeapPriorityQueue<>(iSize);
        binaryHeapDefPQ = new BinaryHeapPriorityQueue<>();
        runTests();

    }
    private void runTests() {
                System.out.println("-------------------------------------------");
        System.out.println("----------- BinaryHeapPQ TESTS ------------");
        System.out.println("-------------------------------------------");
        testInsertBinaryHeapPQ();
        testFillBinaryHeapPQ();
        testPeekBinaryHeapPQ();
        testRemoveBinaryHeapPQ();
        testDeleteBinaryHeapPQ();
        testClearBinaryHeapPQ();
        testDefaultConstructorBinaryHeapPQ();

    }
        private void testDeleteBinaryHeapPQ() {
        System.out.println("------ TESTING: delete(E obj) -----");
        try{
            int[] copy;
            copy = Arrays.copyOf(mostCommonNumbers,iSize);

            System.out.print("Inserting from left to right: ");
            for(int i = 0; i < copy.length; i++){
                if(!binaryHeapPQ.insert(copy[i]))
                    throw new RuntimeException("FAILED -> insert in BinaryHeapPQ not working correctly");
                System.out.print(copy[i] +" ");
            }
            System.out.println("\nApplying delete(3)");
            if(!binaryHeapPQ.delete(3))
                throw new RuntimeException("FAILED -> delete() in BinaryHeapPQ not working correctly");

            System.out.println("\nApplying delete(12)");
            if(!binaryHeapPQ.delete(12))
                throw new RuntimeException("FAILED -> delete() in BinaryHeapPQ not working correctly");

            System.out.println("\nApplying delete(1)");
            if(!binaryHeapPQ.delete(1))
                throw new RuntimeException("FAILED -> delete() in BinaryHeapPQ not working correctly");

            if(binaryHeapPQ.contains(3))
                throw new RuntimeException("FAILED -> the list should not contain '3' anymore");

            if(binaryHeapPQ.contains(12))
                throw new RuntimeException("FAILED -> the list should not contain '12' anymore");

            if(binaryHeapPQ.contains(1))
                throw new RuntimeException("FAILED -> the list should not contain '1' anymore");

            System.out.print("Returned: ");
            for(Integer num : binaryHeapPQ)
                System.out.print(num +" ");
        }catch(RuntimeException e){
            System.out.print(e.getMessage());
        }
    }

    private void testRemoveBinaryHeapPQ() {
        System.out.println("------ TESTING: remove(E obj) -----");
        int[] copy = Arrays.copyOf(mostCommonNumbers, iSize);
        Arrays.sort(copy);
        try{
            System.out.print("Expecting: ");
            for(int num : copy)
                System.out.print(num +" ");
            System.out.println();

            for(int i = 0; i < copy.length; i++){
                if(binaryHeapPQ.remove() != copy[i])
                    throw new RuntimeException("FAILED -> item not removed correctly");
            }

            if(!binaryHeapPQ.isEmpty())
                throw new RuntimeException("FAILED -> list is not empty");

        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    private void testClearBinaryHeapPQ() {
        System.out.println("\n------ TESTING: clear() -----");
        try {
            binaryHeapPQ.clear();
            if (!binaryHeapPQ.isEmpty())
                throw new RuntimeException("FAILED -> list should be empty");

            if(binaryHeapPQ.isFull())
                throw new RuntimeException("FAILED -> list should not be full");

            System.out.println("Expected size: " +0);
            System.out.println("Returned size: " +binaryHeapPQ.size());
            if(binaryHeapPQ.size() != 0){
                throw new RuntimeException("FAILED -> list should be of size " +0);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void testPeekBinaryHeapPQ() {
        System.out.println("\n------ TESTING: peek() -----");
        try {
            if(binaryHeapPQ.peek() != 1)
                throw new RuntimeException("FAILED -> error in peek method");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void testFillBinaryHeapPQ() {
        System.out.println("\n------ TESTING: isEmpty() -----");
        try {
            if (binaryHeapPQ.isEmpty())
                throw new RuntimeException("FAILED -> list should not be empty");

            System.out.println("\n------ TESTING: isFull() -----");
            if(!binaryHeapPQ.isFull())
                throw new RuntimeException("FAILED -> list should be full");

            System.out.println("\n------ TESTING: size() -----");
            System.out.println("Expected size: " +iSize);
            System.out.println("Returned size: " +binaryHeapPQ.size());
            if(binaryHeapPQ.size() != iSize){
                throw new RuntimeException("FAILED -> list should be of size " +iSize);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void testInsertBinaryHeapPQ() {
        System.out.println("\n------ TESTING: insert(E obj) -----");
        try{
            int[] copy;
            int[] heap = {1,2,4,10,3,8,21,23,22,12};
            copy = Arrays.copyOf(mostCommonNumbers,iSize);

            System.out.print("Inserting from left to right: ");
            for(int i = 0; i < copy.length; i++){
                if(!binaryHeapPQ.insert(copy[i]))
                    throw new RuntimeException("FAILED -> insert in BinaryHeapPQ not working correctly");
                System.out.print(copy[i] +" ");
            }

            System.out.print("\nExpected: ");
            for(int num : heap)
                System.out.print(num +" ");

            System.out.print("\nReturned: ");
            for(Integer num : binaryHeapPQ)
                System.out.print(num +" ");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
        private void testDefaultConstructorBinaryHeapPQ() {
        System.out.println("------ TESTING: default constructor -----");
        try{
            for(int i = 0; i < mostVisitedCities.length; i++)
                if(!binaryHeapDefPQ.insert(mostVisitedCities[i]))
                    throw new RuntimeException("FAILED -> Default constructor instance not inserting correctly");

            for(String city: binaryHeapDefPQ)
                System.out.print(city +" ,");

            System.out.println("\nPerforming: contains(\"Tokyo\")");
            if(!binaryHeapDefPQ.contains("Tokyo"))
                throw new RuntimeException("FAILED -> error in contains method");

            System.out.println("Performing remove()");
            for(int i = 0; i < mostVisitedCities.length; i++)
                if(binaryHeapDefPQ.remove() != mostVisitedCities[i])
                    throw new RuntimeException("FAILED -> Default constructor instance not removing correctly");


            for(int i = 0; i < mostVisitedCities.length; i++)
                if(!binaryHeapDefPQ.insert(mostVisitedCities[i]))
                    throw new RuntimeException("FAILED -> Default constructor instance not inserting correctly");

            binaryHeapPQ.clear();
            if(binaryHeapPQ.size() != 0)
                throw new RuntimeException("FAILED -> Size using default constructor should be 0 ");
        }catch(Exception e){
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        new HeapTest();
    }
}
