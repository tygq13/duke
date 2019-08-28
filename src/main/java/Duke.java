import java.util.*;
import java.io.*;

public class Duke {
    private static UI user = new UI();
    private static Scanner in = new Scanner(System.in);
    private static final String FILEPATH = "D:\\codes\\java\\duke\\data\\duke.txt";
    private static ArrayList<Task> list = new ArrayList<>();

    public static void loadList() throws FileNotFoundException{
        File data = new File(FILEPATH);
        Scanner read = new Scanner(data);
        while (read.hasNext()) {
            String [] tasks = read.nextLine().split(" \\| ");
            //System.out.println(Arrays.toString(tasks));
            if (tasks[0].trim().equals("T")) {
                list.add(new Todo(Boolean.parseBoolean(tasks[1]), tasks[2].trim()));
            } else if (tasks[0].trim().equals("D")) {
                list.add(new Deadline(Boolean.parseBoolean(tasks[1]), tasks[2].trim(), tasks[3].trim()));
            } else if (tasks[0].trim().equals("E")) {
                list.add(new Event(Boolean.parseBoolean(tasks[1]), tasks[2].trim(), tasks[3].trim()));
            }
        }
    }

    public static void start(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        user.greet();
        try {
            loadList();
        } catch (FileNotFoundException e) {
            File directory = new File("D:\\codes\\java\\duke\\data");
            if (!directory.exists()) {
                System.out.println("OOPS!!! missing 'data' directory at project root. Trying to create one for you."); 
            }
            directory.mkdir();
        }
    }

    public static void exit() {
        try {
            save();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        user.exit();
    }

    public static void todo (String[] input) throws DukeException {
        if (input.length < 2) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty");
        }
        String description = String.join(" ", input).substring(5);
        list.add(new Todo(description));
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
            String description = line.substring(0, index -1);
            String date = line.substring(index + 1);
            list.add(new Deadline(description, date));
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
            String description = line.substring(0, index -1);
            String date = line.substring(index + 1);
            list.add(new Event(description, date));
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
            } else {
                Task finished = list.get(taskNumber - 1);
                finished.markAsDone();
                user.printDone(finished);
            }
        }
    }

    public static void list() {
        user.printList(list);
    }

    public static void save() throws IOException{
        /* write to a specific line:
        List<String> lines = Files.readAllLines(Paths.get(FILEPATH));
        lines.set(taskNumber - 1, finished.getType() + " | 1 | " + finished.getTask());
        Files.write(file.toPath(), lines);
        */

        FileWriter fw = new FileWriter(FILEPATH);
        BufferedWriter fileUpdate = new BufferedWriter(fw);
        for (Task t : list) {
            fileUpdate.write(t.getType() + " | " + (t.getStatus()? "1" : "0"));
            for (String s : t.getTask()) {
                fileUpdate.write(" | " + s);
            }
            fileUpdate.write(System.lineSeparator());
        }
        fileUpdate.close();
    }

    public static void main(String[] args) {
        start();

        while (true) {
            String [] input = in.nextLine().split(" ");
        	String command = input[0];
        	if (command.equals("bye")) {
                exit();
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
