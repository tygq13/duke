package command;

import ui.Ui;
import util.Storage;
import task.TaskList;
import exception.DukeException;

public class ExitCommand implements Command{

	@Override
	public boolean isExit() {
		return true;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		ui.showExit();
	}
}