package tp.langage.thread;

public class EssaiBasicForkJoin {

	public static void main(String[] args) {
		
		System.out.println("debut - main");
		Thread t = new Thread(new MyRunnableCode());
		t.start();
		System.out.println(("suite - main / avant join"));
		try {
			t.join(); //attente de la fin de l'execution du thread
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		System.out.println("fin main - apres join");

	}

}
