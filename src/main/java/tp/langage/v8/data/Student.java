package tp.langage.v8.data;


public class Student extends Person {
	
	private String level;

	public Student(String firstname, String lastname, int age) {
		super(firstname, lastname, age);
		}
	
	public Student(){
		super();
	}
	
	public Student(Person p){
		this(p.firstname, p.lastname, p.age);
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Student [level=" + level + " , " + super.toString()
				+ "]";
	}
	
	

}
