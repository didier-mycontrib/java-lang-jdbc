package tp.langage.v8.data;

import java.util.ArrayList;
import java.util.List;

public class StaticPersonList {
	
	public static List<Person> initList(){
		 List<Person> pList = new ArrayList<Person>();
		
		 pList.add(new Person("alain" , "posteur" , 31));
		 pList.add(new Person("alain" , "proviste" , 32));
		 pList.add(new Person("anna" , "conda" , 30));
		 pList.add(new Person("annie" , "versaire" , 30));
		 pList.add(new Person("alex" , "therieur" , 25));
		 pList.add(new Person("alain" , "therieur" , 30));
		 pList.add(new Person("olie" , "condor" , 20 ));
		 pList.add(new Person("didier" , "defrance" , 46 ));
		 pList.add(new Person("candy" , "raton" , 34)); 
		 pList.add(new Person("daisy" , "rable" , 34)); 
		 return pList;
	}
}
