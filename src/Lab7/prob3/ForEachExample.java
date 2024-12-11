package Lab7.prob3;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


public class ForEachExample{
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<String> list = Arrays.asList("Hello there", "Goodbye", "Back soon", 
				"Away", "On Vacation", "Everywhere you want to be");
		
		//print each element of the list in upper case format
		Consumer<String> upperCaseName = name -> System.out.println(toUpper(name));
		list.forEach(upperCaseName);

		//implements a consumer class
		list.forEach(new PrintUpperCaseConsumer());

		// Inline Consumer implementation
		Consumer<String> printUpperCase = new Consumer<>() {
			@Override
			public void accept(String s) {
				System.out.println(s.toUpperCase());
			}
		};

		// Use forEach with the inline Consumer
		list.forEach(printUpperCase);
	}
	
	public static String toUpper(String s) {
		return s.toUpperCase();
	}
	
}