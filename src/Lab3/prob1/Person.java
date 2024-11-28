package Lab3.prob1;


public class Person {

	private String name;
	Person(String n) {
		name = n;
	}
	public String getName() {
		return name;
	}
	public void setName(String n){name = n;}
	@Override
	public boolean equals(Object aPerson) {
		if(aPerson == null) return false; 
		if(!(aPerson instanceof Person)) return false;

		Person person = (Person) aPerson;
		return name != null ? name.equals(person.name) : person.name == null;
	}
	public static void main(String[] args) {
		
	}

}
