import java.util.Scanner;

public class Duke {
	private static void printLine() {
		System.out.println("---------------------------------------------------");
	}
	
	public static void greet() { 
		printLine();
		System.out.println("Hello! I'm Duke");
		System.out.println("What can I do for you?");
		printLine();
	}

	public static void echo(String input) {
		printLine();
		System.out.println(input);
		printLine();
	}

	public static void exit() {
		printLine();
		System.out.println("Bye. Hope to see you again soon!");
		printLine();
	}

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        Scanner in = new Scanner(System.in);
        while (true) {
        	String input = in.nextLine();
        	if (input.equals("bye")) {
        		exit();
        		break;
        	} else {
        		echo(input);
        	}

        }
    }
}
