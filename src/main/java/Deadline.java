public class Deadline extends Task{
	protected String by;

	public Deadline() {
		this(null, null);
	}

	public Deadline(String task, String by) {
		super(task);
		this.by = by;
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + by + ")";
	}
}