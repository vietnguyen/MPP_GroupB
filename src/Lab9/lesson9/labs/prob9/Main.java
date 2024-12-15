package lesson9.labs.prob9;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        printSquares(5);
    }

    public static void printSquares(int num) {
        System.out.println(
            Stream.iterate(new int[]{1,1}, n -> new int[]{n[0] + 1, (n[0] + 1) * (n[0] + 1)})
                .limit(num)
                .map(arr -> String.valueOf(arr[1]))
                .collect(Collectors.joining(", "))
        );
        
    }
}
