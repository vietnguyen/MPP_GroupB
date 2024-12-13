package Lab8.Prob1;

import java.util.function.Supplier;

public class RandSupplierTest {
        public static double supplyRand(){
            class RandSupplier implements Supplier<Double>{
                @Override
                public Double get() {
                    return Math.random();
                }
            }

            return new RandSupplier().get();
        }

    public static void main(String[] args){
        // Question 1b
        Supplier<Double> s1 = Math::random;
        System.out.println(s1.get());
        //i
        Supplier<Double> s2 = () -> Math.random();
        //ii
        System.out.println(s2.get());
        //iii
        System.out.println(supplyRand());
    }
}
