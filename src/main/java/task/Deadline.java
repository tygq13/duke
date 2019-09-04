package task;

public class Deadline extends Task {
	protected String by;

	public Deadline() {
		this(null, null);
	}

	public Deadline(String task, String by) {
		this(false, task, by);
	}

	public Deadline(boolean done, String task, String by) {
		super(task);
		this.by = by;
		this.isDone = done;
		this.type = 'D';
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + by + ")";
	}

	@Override
	public String[] getTask() {
		return new String[] {task, by};
	}
}