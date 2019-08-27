import java.util.*;

public class Duke {
    private static UI user = new UI();
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Task>list = new ArrayList<>();

    public static void exit() {
        user.exit();
    }

    public static void todo (String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty");
        }
        list.add(new Todo(String.join(" ", input).substring(5)));
        user.printTask(list);
    }

    public static void deadline (String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty");
        } else {
            String line = String.join(" ", input).substring(9);
            int index = line.indexOf('/');
            if (index == -1) {
                throw new DukeException("OOPS!!! The date of a deadline cannot be empty");                
            }
            list.add(new Deadline(line.substring(0, index -1), line.substring(index + 1)));
            user.printTask(list);
        }
    }

    public static void event (String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty");
        } else {
            String line = String.join(" ", input).substring(6);
            int index = line.indexOf('/');
            if (index == -1) {
                throw new DukeException("OOPS!!! The date of an event cannot be empty");                
            }
            list.add(new Event(line.substring(0, index -1), line.substring(index + 1)));
            user.printTask(list);
        }
    }
    public static void done(String input[]) throws DukeException{
        if (input.length < 2) {
            throw new DukeException("OOPS!!! The task number cannot be empty");
        } else {
            int taskNumber = Integer.parseInt(input[1]);
            if (taskNumber > list.size() || taskNumber < 0) {
                throw new DukeException("OOPS!!! invalid task number");
            }
        }
        Task finished = list.get(Integer.parseInt(input[1]) - 1);
        finished.markAsDone();
        user.printDone(finished);
    }
    public static void list() {
        user.printList(list);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        user.greet();

        while (true) {
            String [] input = in.nextLine().split(" ");
        	String command = input[0];
        	if (command.equals("bye")) {
        		break;
        	} else if (command.equals("todo")) {
                try{
                    todo(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("deadline")) {
                try{
                    deadline(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("event")) {
                try{
                    event(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.equals("done")) {
                try{
                    done(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
        	} else if (command.equals("list")) {
        		list();
        	} else {
        		System.out.println("invalid command");
        	}

        }
    }
}
