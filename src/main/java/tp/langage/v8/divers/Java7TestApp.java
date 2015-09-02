package tp.langage.v8.divers;

public class Java7TestApp {
	
	private String message="Hello World";

	public static void main(String[] args) {
		(new Java7TestApp()).testRun();
	}
	
	public void testRun(){
		//classe anonyme imbriquée pouvant acceder à la variable d'instance "message" du niveau englobant
		Runnable r1 = new Runnable(){
			@Override
			public void run() {
				System.out.println(message);				
			}			
		};
		Thread t1 = new Thread(r1); t1.start();
	}

}
