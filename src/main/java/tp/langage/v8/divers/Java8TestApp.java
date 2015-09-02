package tp.langage.v8.divers;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collector;

public class Java8TestApp {

	
	private String message="Hello World (V8)";

	public static void main(String[] args) {
		testForEach();
		testFunctional();
		testLocalTimeAndLocalDate();
		testBase64();
		testOptional();
		(new Java8TestApp()).testRun();
	}
	
	public void testRun(){
		//lambda expression (compatible run()) utilisant "message" du scope parent:
		Runnable r1 = () -> System.out.println(message);	
		
		Thread t1 = new Thread(r1); t1.start();

	}
	
	public static  List<String> initListOfString(){
		List<String> liste = new ArrayList<String>();
			liste.add("abcdef");
			liste.add("xyz");
		return liste;
	}
	
	public static void testFunctional(){
		/*
		Function<T,R> - takes an object of type T and returns R.
		Supplier<T> - just returns an object of type T.
		Predicate<T> - returns a boolean value based on input of type T.
		Consumer<T> - performs an action with given object of type T (no return)
		BiFunction - like Function but with two parameters.
		BiConsumer - like Consumer but with two parameters
		 */
		System.out.println(">>test apply length function ");
		List<String> liste = initListOfString();
		
		Function<String, String> atrFct = (name) -> {return "@" + name;};
		Function<String, Integer> lengthFct = (name) -> name.length();
		Function<String, Integer> lengthFct2 = String::length;
		
		//for (String s : liste) System.out.println( lengthFct.apply(s));
		//for (String s : liste) System.out.println( lengthFct2.apply(s));
		for (String s : liste) System.out.println( atrFct.apply(s) + " , length=" + lengthFct.apply(s));
		//for (String s : liste) System.out.println(String.join(" "  /*delimiter*/,  atrFct.apply(s) ,",length=" , String.valueOf(lengthFct.apply(s))));
	}
	
	public static void testForEach(){
		System.out.println(">>test forEach on println");
		List<String> liste = initListOfString();
		//liste.forEach(System.out::println);	
		
		//liste.stream().map((s)->s.toUpperCase()).forEach(System.out::println);
		liste.stream().map(String::toUpperCase).forEach(System.out::println);
		
		//System.out.println("as an reduced single string : " 
		//           + liste.stream()
		//                  .map((s)->s.toUpperCase())
		//                  .reduce("" /*initial*/,(s1,s2)->String.join(","/*delimiter*/, s1,s2)));
		
		Collector<String, StringJoiner, String> stringCollector =
			    Collector.of(
			        () -> new StringJoiner(" | "),          // supplier
			        (j, s) -> j.add(s.toUpperCase()),       // accumulator
			        (j1, j2) -> j1.merge(j2),               // combiner
			        StringJoiner::toString);                // finisher

		String names = liste
			    .stream()
			    .collect(stringCollector);

		System.out.println(names); 
	}
	
	public static void testBase64(){
		//java.util.Base64
		
		try {
			// Encode using basic encoder
			String base64encodedString = Base64.getEncoder().encodeToString("Myjava8String".getBytes("utf-8"));
			System.out.println("Base64 Encoded String (Basic) :" + base64encodedString);
			
			// Decode
			byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
			
			System.out.println("Original String: " + new String(base64decodedBytes, "utf-8"));
		} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
		}
	}
	
	public static void testLocalTimeAndLocalDate(){
		//java.time.LocalTime , LocalDate (d'inspiration JodaTime) sont plus simples à utiliser que Date et Calendar
		LocalTime now = LocalTime.now();
		System.out.println("now is " + now);
		LocalTime later = now.plus(8, ChronoUnit.HOURS);
		System.out.println("later (now+8h) is " + later);
		
		LocalDate today = LocalDate.now();
		System.out.println("today is " + today);
		LocalDate thirtyDaysFromNow = today.plusDays(30);
		System.out.println("thirtyDaysFromNow is " + thirtyDaysFromNow);
		LocalDate nextMonth = today.plusMonths(1);
		System.out.println("nextMonth is " + nextMonth);
		LocalDate aMonthAgo = today.minusMonths(1);
		System.out.println("aMonthAgo is " + aMonthAgo);
		
		//LocalDate date14July2015 = LocalDate.of(2015, 7, 14);
		LocalDate date14July2015 = LocalDate.of(2015, Month.JULY, 14);
		
		LocalTime time = LocalTime.of(14 /*h*/, 15 /*m*/ , 0 /*s*/);
		LocalDateTime datetime = date14July2015.atTime(time);
		System.out.println("le 14 juillet 2015 à 14h15 : " + datetime);
		
		LocalDate date1=today , date2=nextMonth;
		Period p1 = Period.between(date1, date2);
		System.out.println("periode p1:" + p1);
		
		LocalTime time1= time;
		LocalTime time2= LocalTime.of(14 /*h*/, 30 /*m*/ , 0 /*s*/);
		Duration d = Duration.between(time1, time2);
		System.out.println("durée d:" + d);
		
		Duration twoHours = Duration.ofHours(2); System.out.println("durée de 2 heures:" + twoHours);
		Duration tenMinutes = Duration.ofMinutes(10);System.out.println("durée de 10 minutes:" + tenMinutes);
		Duration thirtySecs = Duration.ofSeconds(30);System.out.println("durée de 30 secondes:" + thirtySecs);
		
		LocalTime t2 = time.plus(twoHours); 	System.out.println("14h15 plus 2 heures:" + t2);
		
		//.with(temporalAdjuster)
		LocalDate premierJourDeCetteAnnee=  LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
		System.out.println("premierJourDeCetteAnnee="+premierJourDeCetteAnnee);
		LocalDate dernierJourDuMois=  LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
		System.out.println("dernierJourDuMois="+dernierJourDuMois);
		
		ZoneId myZone = ZoneId.systemDefault();
		System.out.println("my (local) zoneId is:" + myZone);
		
		//lien entre java.util.Date et java.time...
		 Date date = new Date();
		 Instant nowInstant = date.toInstant();
		 LocalDateTime dateTime = LocalDateTime.ofInstant(nowInstant, myZone);
		 System.out.println("today/now from Date:" + dateTime);
		
	}
	
	public static List<String> initWinnerList(){
		String[] winnerArray = {"bob", "anna", "alice"};
		List<String> winnerList = Arrays.asList(winnerArray);
		return winnerList;
	}
	
	public static Optional<String> getWinnerByName(String name){
		for(String s : initWinnerList()){
			if(s.equals(name))
				return Optional.of(s);
		}
		/*else*/
		return Optional.empty(); //instead of return null
	}
	
	public static void testOptional(){
	
		Optional<String>  opStr = getWinnerByName("alice");
		System.out.println("Optional<String> opStr = " + opStr );
		if(opStr.isPresent())
		    System.out.println(opStr.get());
		//with lambda expression:
		opStr.ifPresent((s)->System.out.println(s));
		
		opStr = getWinnerByName("looser");
		System.out.println("Optional<String> opStr = " + opStr );
		//System.out.println(opStr.get());//java.util.NoSuchElementException instead of nullPointerException
		opStr.ifPresent((s)->System.out.println(s));
		
		//String str = "abc";
		String str = null;
		Optional<String> opS = Optional.ofNullable(str); //build .empty() if null
		System.out.println("Optional<String> opS = " + opS );
		
		String msg = opS.map((notNullStr)-> "not null string value : " + notNullStr)
		   .orElse("empty optional");
	    System.out.println(msg);
		
	}
}
