public class Event extends Task{
	protected String at;

	public Event() {
		this(null, null);
	}

	public Event(String task, String at) {
		this(false, task, at);
	}

	public Event(boolean done, String task, String at) {
		super(task);
		this.at = at;
		this.isDone = done;
		this.type = 'E';
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at: " + at + ")";
	}

	@Override
	public String[] getTask() {
		return new String[] {task, at};
	}
}