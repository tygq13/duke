public class Task {
	protected String task;
	protected boolean isDone;

	public Task(String task) {
		this.task = task;
		this.isDone = false;
	}

	public String getTask() {
		return task;
	}

	public boolean getStatus() {
		return isDone;
	}

	public String getStatusIcon() {
		return (isDone ? "\u2713" : "\u2715"); //tick or cross
	}

	public void markAsDone() {
		this.isDone = true;
	}
}