package lesson9.labs.prob9;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        printSquares(5);

        printSquares2(10);
    }

    public static void printSquares(int num) {
        System.out.println(
            Stream.iterate(new int[]{1,1}, n -> new int[]{n[0] + 1, (n[0] + 1) * (n[0] + 1)})
                .limit(num)
                .map(arr -> String.valueOf(arr[1]))
                .collect(Collectors.joining(", "))
        );
        
    }

    public static void printSquares2(int num) {
        Stream.iterate(1,Main::nextSquare).limit(num).forEach(System.out::println);
    }

    private static int nextSquare(int n){
        int sq = (int)Math.floor( Math.sqrt(n));

        return (sq+1) * (sq+1);
    }
}
