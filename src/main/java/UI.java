import java.util.*;

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

	public void printTask(ArrayList<Task>list) {
		printLine();
		System.out.println("Got it. I've added this task:");
		Task t = list.get(list.size() -1);
		System.out.println("  " + t);
		System.out.println("Now you have " + list.size() + " tasks in the list.");
		printLine();
	}	

	public void printList(ArrayList<Task>list) {
		printLine();
		System.out.println("Here are the tasks in your list: ");
		for(int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + "." + list.get(i));
		}
		printLine();
	}

	public void printDone(Task finished) {
		printLine();
		System.out.println("Nice! I've marked this task as done:");
		System.out.println(finished);
		printLine();
	}
}