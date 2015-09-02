package tp.langage.v8.data;

public class Person {

    String firstname;
    String lastname;
    int age;

    public Person(String firstname, String lastname, int age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }
    
    public Person(){
    	
    }
    

    @Override
	public String toString() {
		/*return "Person [firstname=" + firstname + ", lastname=" + lastname
				+ ", age=" + age + "]";*/
		return "Person ("+  firstname + "," + lastname	+ ", age=" + age + ")";
	}



	public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public static int sortByAge(Person p1, Person p2) {
        if (p1.getAge() > p2.getAge()) {
            return 1;
        } else if (p1.getAge() < p2.getAge()) {
            return -1;
        } else {
            return 0;
        }
    }
}
