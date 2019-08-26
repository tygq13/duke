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
		System.out.println("Here are the tasks in your list: ");
		for(int i = 0; i < index; i++) {
			if (!list[i].getStatus()) {
				System.out.println((i+1) + ". [" + list[i].getStatusIcon() + "]" + list[i].getTask());
			}
		}
		printLine();
	}

	public void printDone(Task finished) {
		printLine();
		System.out.println("Nice! I've marked this task as done:");
		System.out.println("[" + finished.getStatusIcon() + "]" + finished.getTask());
		printLine();
	}
}