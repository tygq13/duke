package ui;

import task.TaskList;
import task.Task;
import java.util.Scanner;

public class Ui {
	private Scanner in = new Scanner(System.in);

	public String readCommand() {
		return in.nextLine();
	}

	public void showWelcome(){
	    String logo = " ____        _        \n"
	            + "|  _ \\ _   _| | _____ \n"
	            + "| | | | | | | |/ / _ \\\n"
	            + "| |_| | |_| |   <  __/\n"
	            + "|____/ \\__,_|_|\\_\\___|\n";
	    System.out.println("Hello from\n" + logo);
	    System.out.println("What can I do for you?");
	}

	public void showLine() {
		System.out.println("------------------------------------------------------------------------------------------------------");
	}

	public void showExit() {
		System.out.println("Bye. Hope to see you again soon!");
	}

	public void showAdd(TaskList list) {
		System.out.println("Got it. I've added this task:");
		Task t = list.get(list.size() -1);
		System.out.println("  " + t);
		System.out.println("Now you have " + list.size() + " tasks in the list.");
	}	

	public void showList(TaskList list) {
		System.out.println("Here are the tasks in your list: ");
		for(int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + "." + list.get(i));
		}
	}

	public void showDone(Task finished) {
		System.out.println("Nice! I've marked this task as done:");
		System.out.println(finished);
	}

	public void showError(String error) {
		System.out.println(error);
	}
}