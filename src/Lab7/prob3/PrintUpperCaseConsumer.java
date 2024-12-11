package Lab7.prob3;

import java.util.function.Consumer;

public class PrintUpperCaseConsumer implements Consumer<String> {
    @Override
    public void accept(String s) {
        System.out.println(s.toUpperCase());
    }
}
