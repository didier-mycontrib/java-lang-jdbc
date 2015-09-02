package tp.langage.v8.divers;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import tp.langage.v8.data.Person;

public class TestParameterNameApp {

	public static void main(String[] args) {
		try {
			Class<Person> c = Person.class;
			Method m = c.getDeclaredMethod("sortByAge", Person.class,Person.class);
			System.out.println("parameters of Person.sortByAge():");
			for(Parameter p : m.getParameters()){
				System.out.println(p.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
