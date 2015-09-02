package tp.langage.v8.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tp.langage.v8.data.Person;
import tp.langage.v8.data.StaticPersonList;
import tp.langage.v8.sam.SamPredicate;

public class FilterSortListJava7TestApp {
	

	public static void main(String[] args) {
		System.out.println("with several specific filtering methods  : ");
		withExplicitAndSpecificFilterMethods();
		
		System.out.println("with Inner Anonymous SamPredicate Implementations : ");
		mainWithInnerAnonymousSamPredicateImplementations(); //pour un passage progressif V7 vers V8
		System.out.println("****");
		sortListComparatorInnerAnonymousImplementation();
	}
	
	public static void withExplicitAndSpecificFilterMethods() {
		List<Person> pList = StaticPersonList.initList();
		System.out.println("person sublist with age from 20 to 25:");
		List<Person> subList1 = extractSubListByMinMaxAge(pList,20 , 25);
		System.out.println(subList1);
		System.out.println("person sublist with lastName starting with letter p");
		List<Person> subList2 = extractSubListByLastNameFirstLetter(pList,'p');
		System.out.println(subList2);
	}
		
	
	public static void mainWithInnerAnonymousSamPredicateImplementations() {
		List<Person> pList = StaticPersonList.initList();
		
		System.out.println("person sublist with age from 20 to 25:");
		List<Person> subList1 = extractSubListBySamPredicate(pList,new SamPredicate<Person>(){
			@Override
			public boolean test(Person p) {
				 return p.getAge() >= 20 && p.getAge() <= 25;		            
			}
		});
		System.out.println(subList1);
		System.out.println("person sublist with lastName starting with letter p");
		List<Person> subList2 = extractSubListBySamPredicate(pList,new SamPredicate<Person>(){
			@Override
			public boolean test(Person p) {
				 return p.getLastName().startsWith("p");		            
			}
		});
		System.out.println(subList2);
	}
	
	public static List<Person> extractSubListBySamPredicate(List<Person> pList,final SamPredicate<Person> samPredicate) {
        final List<Person> sublist = new ArrayList<Person>();
        for (Person p : pList) {
            if (samPredicate.test(p)) {
            	sublist.add(p);
            }
        }
        return sublist;
    }
	
	public static List<Person> extractSubListByMinMaxAge(List<Person> pList,final int minAge, final int maxAge) {
        final List<Person> sublist = new ArrayList<Person>();
        for (Person p : pList) {
            if (p.getAge() >= minAge && p.getAge() <= maxAge) {
            	sublist.add(p);
            }
        }
        return sublist;
    }
	
	public static List<Person> extractSubListByLastNameFirstLetter(List<Person> pList,final char c) {
        final List<Person> sublist = new ArrayList<Person>();
        for (Person p : pList) {
            if (p.getLastName().startsWith(String.valueOf(c))) {
            	sublist.add(p);
            }
        }
        return sublist;
    }
	
	public static void sortListComparatorInnerAnonymousImplementation() {
		System.out.println("liste triée par age (with Inner Anonymous Implementation of interface java.util.Comparator ):");
		List<Person> pList = StaticPersonList.initList();
		Collections.sort(pList, new java.util.Comparator<Person>(){
			@Override
			public int compare(Person p1, Person p2) {
				if (p1.getAge() > p2.getAge()) {
		            return 1;
		        } else if (p1.getAge() < p2.getAge()) {
		            return -1;
		        } else {
		            return 0;
		        }
			}
		});
		System.out.println(pList);
	}

}
