package lesson9.labs.prob14;

import java.util.Optional;

public class MySingletonLazy {
    private static MySingletonLazy INSTANCE = null;
    private MySingletonLazy(){
        System.out.println("Calling constructor once");
        INSTANCE = this;
    }
    public static MySingletonLazy getInstance(){
        return Optional.ofNullable(INSTANCE).orElseGet(() -> new MySingletonLazy());
    }
}
