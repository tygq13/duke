/**
 * Parser class to parse object. Methods are used in class level.
 *
 * @author tygq13
 */
package util;

import java.util.Hashtable;
import java.util.Date;
import java.util.TimeZone;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import command.*;
import ui.Message;
import exception.DukeException;

public class Parser {
	/**
	 * Enumerator for the different parts of a command.
	 */
	public enum Parts {
	    COMMAND, DESCRIPTION, DATE
	}

	/**
	 * Returns a command with relevant arguments. <br>
	 *
	 * This method recognize the command intended to call, and parse the full command into different parts. Then
	 * pass relevant arguments to the command.
	 *
	 * @param fullCommand string of the full command
	 * @return a command
	 * @throws DukeException exception happens when unable to pass string to date.
	 */
	public static Command parse(String fullCommand) throws DukeException {
		Hashtable<Parts, String> dict= parseToDict(fullCommand);
		String command = dict.get(Parts.COMMAND);
		String description = dict.get(Parts.DESCRIPTION);
		Date date = parseStringToDate(dict.get(Parts.DATE));
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
			case "delete":
				return new DeleteCommand(description);
			case "help":
				return new HelpCommand();
			case "find":
				return new FindCommand(description);
			case "bye":
			case "exit":
			case "quit":
			case "fuck":
				return new ExitCommand();
			default:
				throw new DukeException(Message.INVALID_COMMAND);
		} 
	}

	/**
	 * Returns a hashtable of relevant parts to the string description of the parts. <br>
	 *
	 * The first word is parsed as COMMAND. The following words before /at or /by is parsed as DESCRIPTION.
	 * The remaining is parsed as DATE.
	 *
	 * @param fullCommand string of the full command.
	 * @return a hashtable of Parts enumerator to string description of the part.
	 */
	private static Hashtable<Parts, String> parseToDict(String fullCommand) {
		Hashtable<Parts, String> dict = new Hashtable<>();
		String[] inputs = fullCommand.split(" ");
		String command = inputs[0];
		dict.put(Parts.COMMAND, command.trim().toLowerCase());
		if (inputs.length >= 2) {
			// have description
			int descriptionIndex = fullCommand.indexOf(" ");
		    int dateIndex = fullCommand.indexOf("/at ") > fullCommand.indexOf("/by ") ? 
		    	fullCommand.indexOf("/at ") : fullCommand.indexOf("/by ");
		    if (dateIndex != -1) {
		    	// have date
		    	String description = fullCommand.substring(descriptionIndex, dateIndex -1);
		    	dict.put(Parts.DESCRIPTION, description);
		    	String date = fullCommand.substring(dateIndex + 3).trim();            
		    	dict.put(Parts.DATE, date);
		    } else {
				String description = fullCommand.substring(descriptionIndex).trim();
				dict.put(Parts.DESCRIPTION, description);
			}
		}
		return dict;
	}

	/**
	 * Returns a Date object by parsing the date String.
	 * Time zone is set as Singapore time by default.
	 *
	 * @param dateString the String describing the date.
	 * @return the date
	 * @throws DukeException exception occurs when unable to parse.
	 */
	public static Date parseStringToDate(String dateString) throws DukeException {
		if (dateString == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT-8:00"));
		try {
			Date date = formatter.parse(dateString);
			return date;
		} catch (ParseException e) {
			throw new DukeException(Message.INVALID_DATE_FORMAT);
		}
	}

	/**
	 * Returns the string of date by parsing a date.
	 * @param date the date to be parsed.
	 * @return the string of date.
	 */
	public static String parseDateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT-8:00"));
		return formatter.format(date);
	}
}