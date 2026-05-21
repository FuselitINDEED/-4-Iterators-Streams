import java.util.*;
import java.util.stream.Collectors;

public class iteratorsDemo {

    public static void main(String[] args) {

        // Build a linked list of integers
        linkedLists<Integer> list = new linkedLists<>();
        list.insertAtTail(1);
        list.insertAtTail(2);
        list.insertAtTail(3);
        list.insertAtTail(4);
        list.insertAtTail(5);
        list.insertAtTail(6);

        // Convert to a Java Stream by first converting to a List
        List<Integer> javaList = new ArrayList<>();
        for (int x : list) {
            javaList.add(x);
        }
        
        // 1. FILTER → MAP → COLLECT
        List<Integer> evensSquared =
                javaList.stream()
                        .filter(x -> x % 2 == 0)   // keep evens
                        .map(x -> x * x)           // square them
                        .collect(Collectors.toList());

        System.out.println("Evens squared: " + evensSquared);

        // 2. REDUCE (sum, count, max)
        int sum = javaList.stream().reduce(0, Integer::sum);
        long count = javaList.stream().count();
        int max = javaList.stream().reduce(Integer.MIN_VALUE, Integer::max);

        System.out.println("Sum = " + sum);
        System.out.println("Count = " + count);
        System.out.println("Max = " + max);

     
        // 3. GROUP / COUNT BY KEY
        Map<String, Long> grouped =
                javaList.stream()
                        .collect(Collectors.groupingBy(
                                x -> (x % 2 == 0 ? "Even" : "Odd"),
                                Collectors.counting()
                        ));

        System.out.println("Grouped counts: " + grouped);
    }
}
