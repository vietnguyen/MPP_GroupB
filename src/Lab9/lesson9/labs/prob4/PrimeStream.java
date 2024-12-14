package lesson9.labs.prob4;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class PrimeStream {
  private final Supplier<Stream<Integer>> primes = () -> Stream.iterate(1, n-> {
    n = n + 1;
    if (isPrime(n)) return n;
    return n+1;
  });
  
  public void printFirstNPrimes(int n) {
   primes.get().limit(n).forEach(System.out::println);
  }

  private boolean isPrime(int num) {
    if (num < 1) return false;
    for (int i=2; i<num-1; i++) {
      if (num % i == 0) {
        return false;
      }
    }
    return true;
  }
  
  
}
