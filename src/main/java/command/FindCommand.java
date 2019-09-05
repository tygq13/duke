package command;

import java.util.ArrayList;

import ui.Ui;
import util.Storage;
import task.TaskList;
import task.Task;

public class FindCommand implements Command{
	private String keyword;
	public FindCommand(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public boolean isExit() {
		return false;
	}

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) {
		TaskList tasksFoundAt = new TaskList();
		for (Task task : tasks.list()) {
			for (String description : task.getTask()) {
				if (description.contains(keyword)) {
					tasksFoundAt.add(task);
					break;
				}
			}
		}
		ui.showFind(tasksFoundAt);
	}
}