package mona.task;

/**
 * Represents a generic task with a description and a completion status.
 * This is an abstract class that serves as a base for different types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description.strip();
        this.isDone = false;
    }

    /**
     * Creates a new task with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is marked as done.
     */
    public Task(String description, boolean isDone) {
        this.description = description.strip();
        this.isDone = isDone;
    }

    /**
     * Checks whether the task is completed.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a status icon representing the task's completion state.
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
     * Returns the string representation of the task, including its status icon and description..
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[%s] %s".formatted(getStatusIcon(), description);
    }

    /**
     * Returns a formatted string for saving to a file.
     *
     * @return the save format string for file storage.
     */
    public abstract String toSaveFormat();
}