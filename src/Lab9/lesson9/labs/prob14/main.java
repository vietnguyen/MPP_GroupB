package lesson9.labs.prob14;

public class main {
    public static void main(String[] args){
        MySingletonLazy.getInstance(); // will print to console
        MySingletonLazy.getInstance(); // will not print
        MySingletonLazy.getInstance(); // will not print
    }
}
