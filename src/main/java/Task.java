/**
 * Represents a task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description.strip();
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description.strip();
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[%s] %s".formatted(getStatusIcon(), description);
    }

    public abstract String toSaveFormat();
}
