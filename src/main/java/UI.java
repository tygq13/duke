public class UI {
	private void printLine() {
		System.out.println("---------------------------------------------------");
	}
	
	public void greet() { 
		printLine();
		System.out.println("Hello! I'm Duke");
		System.out.println("What can I do for you?");
		printLine();
	}

	public void echo(String input) {
		printLine();
		System.out.println(input);
		printLine();
	}

	public void exit() {
		printLine();
		System.out.println("Bye. Hope to see you again soon!");
		printLine();
	}

	public void printAdd(String input) {
		printLine();
		System.out.println("added: " + input);
		printLine();
	}	

	public void printList(Task[] list, int index) {
		printLine();
		for(int i = 0; i < index; i++) {
			System.out.println(i + ". " + list[i].get());
		}
		printLine();
	}
}