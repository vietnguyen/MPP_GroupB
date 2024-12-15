## 10. Short Answer:

### a. What happens when the following code is executed?

```java
public static void main(String[] args) {
    IntStream ones = IntStream.generate(() -> 1).distinct();
    ones.forEach(System.out::println);
}
```

**Explain:** The `IntStream.generate()` method generates an infinite stream of 1s. The `distinct()` method is used to remove duplicates from the stream. Since the stream is infinite, the `distinct()` method prints first `1` and then runs indefinitely to find next distinct element which is not possible. So, the code will run indefinitely.

**Quick fix:** Use `limit()` to restrict the number of elements in the stream.

```java
public static void main(String[] args) {
    IntStream
        .generate(() -> 1).limit(1).distinct()
        .forEach(System.out::println);
}
```

### b. Print the stream of Strings to the console

You have a Stream of Strings called `stringStream` consisting of the values “Bill”, “Thomas”, and “Mary”. Write the one line of code necessary to print this stream to the console so that the output looks like this:

**Output:** `Bill, Thomas, Mary`

```java
Stream<String> stringStream = Stream.of("Bill", "Thomas", "Mary");
System.out.println(
    stringStream
    .collect(Collectors.joining(", "))
);
```

### c. Output both the maximum and minimum values from a Stream of Integers

You have a Stream of Integers called `myIntStream` and you need to output both the maximum and minimum values somehow, making use of this stream only once. Write compact code that efficiently accomplishes this.

```java
Random r = new Random();
IntSummaryStatistics stats = IntStream.generate(r :: nextInt)
    .limit(100)
    .summaryStatistics();
System.out.println("Max: " + stats.getMax() + ", Min: " + stats.getMin());
```