package util;

import java.util.Hashtable;
import java.io.IOException;

import command.*;
import ui.Message;
import exception.DukeException;
import task.Task;

public class Parser {
	public enum Parts {
	    COMMAND, DESCRIPTION, DATE
	}

	public static Command parse(String fullCommand) throws DukeException {
		Hashtable<Parts, String> dict= parseToDict(fullCommand);
		String command = dict.get(Parts.COMMAND);
		String description = dict.get(Parts.DESCRIPTION);
		String date = parseDate(dict.get(Parts.DATE));
		switch (command) {
			case "todo":
				return new TodoCommand(description);
			case "event":
				return new EventCommand(description, date);
			case "deadline":
				return new DeadlineCommand(description, date);
			case "list":
				return new ListCommand();
			case "done":
				return new DoneCommand(description);
			case "bye":
			case "exit":
			case "quit":
			case "fuck":
				return new ExitCommand();
			//todo: case "delete":
			default:
				throw new DukeException(Message.INVALID_COMMAND);
		} 
	}

	private static Hashtable<Parts, String> parseToDict(String fullCommand) {
		Hashtable<Parts, String> dict = new Hashtable<>();
		String[] inputs = fullCommand.split(" ");
		String command = inputs[0];
		dict.put(Parts.COMMAND, command.toLowerCase());
		if (inputs.length >= 2) {
			// have description
			int descriptionIndex = fullCommand.indexOf(" ") + 1;
		    int dateIndex = fullCommand.indexOf('/');
		    if (dateIndex != -1) {
		    	// have date
		    	String description = fullCommand.substring(descriptionIndex, dateIndex -1);
		    	dict.put(Parts.DESCRIPTION, description);
		    	String date = fullCommand.substring(dateIndex + 1);            
		    	dict.put(Parts.DATE, date);
		    } else {
				String description = fullCommand.substring(descriptionIndex);
				dict.put(Parts.DESCRIPTION, description);
			}
		}
		return dict;
	}

	private static String parseDate(String date) {
		return date;
	}
}