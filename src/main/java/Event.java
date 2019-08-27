public class Event extends Task{
	protected String at;

	public Event() {
		this(null, null);
	}

	public Event(String task, String at) {
		super(task);
		this.at = at;
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at: " + at + ")";
	}
}