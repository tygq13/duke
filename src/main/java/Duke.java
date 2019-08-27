import java.util.*;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        UI user = new UI();
        user.greet();
        Scanner in = new Scanner(System.in);
        ArrayList<Task>list = new ArrayList<>();

        while (true) {
        	String input[] = in.nextLine().split(" ");
        	if (input[0].equals("bye")) {
        		user.exit();
        		break;
        	} else if (input[0].equals("todo")) {
                list.add(new Todo(String.join(" ", input).substring(5)));
                user.printTask(list);
            } else if (input[0].equals("deadline")) {
                String s = String.join(" ", input).substring(9);
                int index = s.indexOf('/');
                if (index == -1) {
                    System.out.println("please specify a deadline");
                } else {
                    list.add(new Deadline(s.substring(0, index - 1), s.substring(index+1)));
                    user.printTask(list);
                }
            } else if (input[0].equals("event")) {
                String s = String.join(" ", input).substring(6);
                int index = s.indexOf('/');
                if (index == -1) {
                    System.out.println("please specify a time");
                } else {
                    list.add(new Event(s.substring(0, index - 1), s.substring(index+1)));
                    user.printTask(list);
                }
            } else if (input[0].equals("done")) {
        		Task finished = list.get(Integer.parseInt(input[1]) - 1);
        		finished.markAsDone();
        		user.printDone(finished);
        	} else if (input[0].equals("list")) {
        		user.printList(list);
        	} else {
        		System.out.println("wrong command");
        	}

        }
    }
}
