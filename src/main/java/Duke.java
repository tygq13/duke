import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        UI user = new UI();
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        int index = 0;
        while (true) {
        	String input = in.nextLine();
        	if (input.equals("bye")) {
        		user.exit();
        		break;
        	} else if (input.substring(0,4).equals("done")) {
        		String[] s = input.split(" "); 
        		Task finished= list[Integer.parseInt(s[1]) - 1];
        		finished.markAsDone();
        		user.printDone(finished);
        	} else if (input.equals("list")) {
        		user.printList(list, index);
        	} else {
        		list[index] = new Task(input);
        		index++;
        		user.printAdd(input);
        	}

        }
    }
}
