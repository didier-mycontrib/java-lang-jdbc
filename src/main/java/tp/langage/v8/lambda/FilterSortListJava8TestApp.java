package tp.langage.v8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tp.langage.v8.data.Person;
import tp.langage.v8.data.StaticPersonList;
import tp.langage.v8.data.Student;

//import tp.langage.v8.sam.SamPredicate;

public class FilterSortListJava8TestApp {

	public static void main(String[] args) {

		System.out.println("****\n with Lambda expression : ");
		mainWithLambdaExpressions();
		System.out.println("****");
		sortListWithFunctionReference();
		sortListWithLambdaExpression();
		
	}
		
		
		public static void mainWithLambdaExpressions() {
			List<Person> pList = StaticPersonList.initList();
			
			System.out.println("person sublist with age from 20 to 25:");
			List<Person> subList1 = extractSubListByJava8Predicate(pList,
					                                             (person) -> person.getAge() >= 14 && person.getAge() <= 25
					                                             );
			System.out.println(subList1);
			System.out.println("person sublist with lastName starting with letter p");
			List<Person> subList2 = extractSubListByJava8Predicate(pList,
																(person) -> person.getLastName().startsWith("p")
																);
			System.out.println(subList2);
		}
	/*
	//tp.langage.v8.sam.SamPredicate is already predifined in jdk 1.8 (java.util.function.Predicate)
	public static List<Person> extractSubListBySamPredicate(List<Person> pList,final SamPredicate<Person> samPredicate) {
        final List<Person> sublist = new ArrayList<Person>();
        for (Person p : pList) {
            if (samPredicate.test(p)) {
            	sublist.add(p);
            }
        }
        return sublist;
    }*/
	
	public static List<Person> extractSubListByJava8Predicate(List<Person> pList,final Predicate<Person> predicate) {
        final List<Person> sublist = new ArrayList<Person>();
        for (Person p : pList) {
            if (predicate.test(p)) {
            	sublist.add(p);
            }
        }
        return sublist;
    }
	
	public static void sortListWithFunctionReference() {
		System.out.println("liste triée par age (with function reference):");
		List<Person> pList = StaticPersonList.initList();
		Collections.sort(pList, Person::sortByAge); //Person::sortByAge() have same code as java.util.Comparator.compare()
		                                            //but with different name and without explicit interface implementation 
		System.out.println(pList);
		System.out.println("liste triée par nom puis par prenom (with function reference):");
		pList.sort(Comparator.comparing(Person::getLastName)
				       .thenComparing(Person::getFirstName));
		System.out.println(pList);
	}
	
	public static void sortListWithLambdaExpression() {
		System.out.println("liste triée par age (with lambda):");
		List<Person> pList = StaticPersonList.initList();
		Collections.sort(pList, 
				        (Person p1, Person p2) -> p1.getAge() == p2.getAge() ? 0 : (p1.getAge() < p2.getAge() ? -1 : 1 ) 
						);
		System.out.println(pList);
	}
	
	
	


}
