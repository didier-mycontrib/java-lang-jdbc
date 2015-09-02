package tp.langage.v8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tp.langage.v8.data.Person;
import tp.langage.v8.data.StaticPersonList;
import tp.langage.v8.data.Student;

//import tp.langage.v8.sam.SamPredicate;

public class Java8StreamsTestApp {

	public static void main(String[] args) {

		
		filteringWithStreamFilterAndLambda();
		mapWithStream();
		skipAndLimitOnStream();
		reduceStreamToASingleResult();
	}
		
		
		
	
	
	
	public static void filteringWithStreamFilterAndLambda(){
		System.out.println("liste filtrée selon age >=32 (with stream().filter() and lambda):");
		List<Person> pList = StaticPersonList.initList();
		pList.stream().filter( (p) -> p.getAge() >= 32  ).forEach( (p) -> System.out.println(p) );
		//NB: pList.stream() is of type java.util.stream.Stream<Person>
		// and .filter() is of the same type
		
		//Stream interface is for "bulk data operations" : operation sur flux de données en vrac
		
		//.stream() for sequential operations
		//.parallelStream() for parallel operations
		
		//source of stream can be:
		//Stream.of(val1,val2,val3…) , Stream.of(array) and list.stream(). 
		//or final Stream<String> splitOf = Stream.of("A,B,C".split(","));
	}
	
	public static void mapWithStream(){
		// map() is for "transformation"
		List<Person> persons = StaticPersonList.initList();
		Stream<Student> studentsStream = null;
		/*
		//v1 (explicit):
		studentsStream = persons.stream()
			      .filter(p -> p.getAge() <= 30)
			      .map(new Function<Person, Student>() {
			                  @Override
			                  public Student apply(Person person) {
			                     return new Student(person);
			                  }
			              });
	     */
		/*
		//v2 (with lambda expression):
		studentsStream = persons.stream()
		        .filter(p -> p.getAge() <=30 )
		        .map(person -> new Student(person));
		*/
		//v3 (with function/constructor reference):
		studentsStream = persons.stream()
		        .filter(p -> p.getAge() <=30 )
		        .map(Student::new);
		
		System.out.println("liste of  students:");
		studentsStream.forEach(System.out::println);
		
		//with collect() terminal operation (at end of operation stream) :
		List<Student> students =  persons.stream()
		        .filter(p -> p.getAge() <=25 )
		        .map(Student::new)
		        .collect(Collectors.toList()); //or .collect(Collectors.toCollection(ArrayList::new));
		System.out.println("liste of (youngs) students:" + students);
		}
	
	public static void skipAndLimitOnStream(){
		final List<Integer> demoValues  = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //limit the input -> [1, 2, 3, 4]
        System.out.println(demoValues.stream()
        		.limit(4) //.distinct()
                .collect(Collectors.toList()));
        //jumping over the first 4 elements -> [5, 6, 7, 8, 9, 10]
        System.out.println(demoValues.stream()
        		.skip(4)
    			.collect(Collectors.toList()));
	}
	public static void reduceStreamToASingleResult(){
		final List<String> demoValues  = Arrays.asList("1_2_3","-4_5_6","-7_8_9");
      
        System.out.println("stream reduced to as single optional result: " + demoValues.stream()
        			.reduce(String::concat));
        
        final List<Integer> demoValues2  = Arrays.asList(5,8,12);
        System.out.println("stream reduced to as single optional result (somme des valeurs): " + demoValues2.stream()
    			.reduce(0 , (x,y) -> x+y)); //.reduce(identity, accumulator) where identity is initialValue (or default result if stream is empty)
	}
}
