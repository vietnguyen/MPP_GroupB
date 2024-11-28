package Lab3.prob1;

public class PersonWithJob {
	private Person person;
	private double salary;
	
	public double getSalary() {
		return salary;
	}
	PersonWithJob(String n, double s) {
		person = new Person(n);
		salary = s;
	}
	public String getName(){return person.getName();}
	public void setName(String n){person.setName(n);}
	
	@Override
	public boolean equals(Object aPerson) {
		if(aPerson == null) return false; 
		if(!(aPerson instanceof PersonWithJob)) return false;

		PersonWithJob p = (PersonWithJob)aPerson;
		return this.getName().equals(p.getName()) && this.getSalary()==p.getSalary();
	}
	public static void main(String[] args) {

		// Create a Person object
		Person p1 = new Person("Joe");

		// Create an PersonWithJob object
		PersonWithJob p2 = new PersonWithJob("Joe", 50000);

		// Comparing PersonWithJob and Person
		System.out.println("p1.equals(p2)? "+ p1.equals(p2)); // false, as Person does not know about PersonWithJob
		System.out.println("p2.equals(p1)? " + p2.equals(p1)); // false, PersonWithJob compares Person within its equals

		// Comparing two PersonWithJob objects
		PersonWithJob p3 = new PersonWithJob("Joe", 50000);
		System.out.println("p3.equals(p2)? " + p3.equals(p2)); // true, name and salary match


	}


}
