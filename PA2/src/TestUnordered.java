import data_structures.PriorityQueue;
import data_structures.UnorderedArrayPriorityQueue;

import java.util.Arrays;

public class TestUnordered {
    private final PriorityQueue<Integer> unorderedAPQ;
    private final PriorityQueue<String> unorderedDefAPQ;
    private final int iSize = 10;

    // this array is used for default constructor tests
    String[] mostVisitedCities = {"Hong Kong", "BangKok", "London", "Macau", "Singapore", "Paris", "Dubai",
            "New York", "Kuala Lumpur","Istanbul","Delhi","Antalya", "Shenzhen","Mumbai","Phuket",
            "Rome", "Tokyo", "Pattaya","Taipei","Mecca"};

    // this array is used for parameterized constructor tests
    int[] mostCommonNumbers = {1,23,8,12,3,4,21,22,10,2};

    public TestUnordered(){

                unorderedAPQ = new UnorderedArrayPriorityQueue<>(iSize);
        unorderedDefAPQ = new UnorderedArrayPriorityQueue<>();
        runTests();
    }
    private void runTests() {
        System.out.println("-------------------------------------------");
        System.out.println("--------- UnorderedArrayPQ TESTS ----------");
        System.out.println("-------------------------------------------");
        testInsertUnorderedAPQ();
        testFillUnorderedAPQ();
        testPeekUnorderedAPQ();
        testRemoveUnorderedAPQ();
        testDeleteUnorderedAPQ();
        testClearUnorderedAPQ();
        testDefaultConstructorUnorderedAPQ();
    }
        private void testClearUnorderedAPQ() {
        System.out.println("\n------ TESTING: clear() -----");
        try {
            unorderedAPQ.clear();
            if (!unorderedAPQ.isEmpty())
                throw new RuntimeException("FAILED -> list should be empty");

            if(unorderedAPQ.isFull())
                throw new RuntimeException("FAILED -> list should not be full");

            System.out.println("Expected size: " +0);
            System.out.println("Returned size: " +unorderedAPQ.size());
            if(unorderedAPQ.size() != 0){
                throw new RuntimeException("FAILED -> list should be of size " +0);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void testDeleteUnorderedAPQ() {
        System.out.println("------ TESTING: delete(E obj) -----");
        try{
            System.out.print("Array: ");
            for(int i = 0; i < iSize; i++){
                if(!unorderedAPQ.insert(i%2)){
                    throw new RuntimeException("FAILED -> failed to add value to list");
                }
                System.out.print(i%2 +" ");
            }
            System.out.println("\nApplying delete(0)");
            unorderedAPQ.delete(0);

            System.out.print("Expected: ");
            for(int i =0; i < 5; i++) {
                System.out.print("1 ");
            }
            System.out.println();

            if(unorderedAPQ.contains(0))
                throw new RuntimeException("FAILED -> list should not contain '0' anymore");

            System.out.print("Returned: ");
            for(Integer num : unorderedAPQ){
                System.out.print(num +" ");
            }
        }catch(RuntimeException e){
            System.out.print(e.getMessage());
        }
    }

    private void testPeekUnorderedAPQ() {
        System.out.println("\n------ TESTING: peek() -----");
        try {
            if(unorderedAPQ.peek() != mostCommonNumbers[0])
                throw new RuntimeException("FAILED -> error in peek method");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void testFillUnorderedAPQ() {
        System.out.println("\n------ TESTING: isEmpty() -----");
        try {
            if (unorderedAPQ.isEmpty())
                throw new RuntimeException("FAILED -> list should not be empty");

            System.out.println("\n------ TESTING: isFull() -----");
            if(!unorderedAPQ.isFull())
                throw new RuntimeException("FAILED -> list should be full");

            System.out.println("\n------ TESTING: size() -----");
            System.out.println("Expected size: " +iSize);
            System.out.println("Returned size: " +unorderedAPQ.size());
            if(unorderedAPQ.size() != iSize){
                throw new RuntimeException("FAILED -> list should be of size " +iSize);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void testRemoveUnorderedAPQ() {
        System.out.println("\n------ TESTING: remove() -----");
        int[] copy;
        copy = Arrays.copyOf(mostCommonNumbers,iSize);
        Arrays.sort(copy);
        System.out.println("Values expected to be removed in this order");
        System.out.print("Expected: ");
        for(Integer num : copy)
            System.out.print(num +" ");
        System.out.println();
        try{
            System.out.print("Returned: ");
            for(int i = 0; i < copy.length; i++){
                int valReturned = unorderedAPQ.remove();
                if(valReturned != copy[i])
                    throw new RuntimeException("FAILED -> item not removed correctly");
                System.out.print(valReturned +" ");
            }
            System.out.println();
            if(!unorderedAPQ.isEmpty())
                throw new RuntimeException("FAILED -> list is not empty");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void testInsertUnorderedAPQ() {
        System.out.println("------ TESTING: insert(E obj) -----");
        try{
            int[] copy;
            copy = Arrays.copyOf(mostCommonNumbers,iSize);

            for(int i = 0; i < copy.length; i++){
                if(!unorderedAPQ.insert(copy[i]))
                    throw new RuntimeException("FAILED -> failed to add value to list");
            }

            System.out.print("Expected: ");
            for(int i = 0; i < copy.length; i++)
                System.out.print(copy[i] +" ");

            System.out.print("\nReturned: ");
            for(Integer num : unorderedAPQ)
                System.out.print(num +" ");

        }catch(RuntimeException e){
            System.out.print(e.getMessage());
        }
    }
        private void testDefaultConstructorUnorderedAPQ() {
        System.out.println("------ TESTING: default constructor -----");
        try{
            for(int i = 0; i < mostVisitedCities.length; i++)
                if(!unorderedDefAPQ.insert(mostVisitedCities[i]))
                    throw new RuntimeException("FAILED -> Default constructor instance not inserting correctly");

            for(String city: unorderedDefAPQ)
                System.out.print(city +" ,");
            System.out.println("\nPerforming: contains(\"Tokyo\")");
            if(!unorderedDefAPQ.contains("Tokyo"))
                throw new RuntimeException("FAILED -> error in contains method");

            System.out.println("Performing remove()");
            for(int i = 0; i < mostVisitedCities.length;i++){
                unorderedDefAPQ.remove();
            }

            for(int i = 0; i < mostVisitedCities.length; i++)
                if(!unorderedDefAPQ.insert(mostVisitedCities[i]))
                    throw new RuntimeException("FAILED -> Default constructor instance not inserting correctly");

            unorderedDefAPQ.clear();
            if(unorderedDefAPQ.size() != 0)
                throw new RuntimeException("FAILED -> Size using default constructor should be 0 ");
        }catch(Exception e){
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        new TestUnordered();
    }


}
