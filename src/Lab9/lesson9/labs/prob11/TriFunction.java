package lesson9.labs.prob11;

@FunctionalInterface
public interface TriFunction<S,T,U,R> {
	R apply(S s, T t, U u);
}
