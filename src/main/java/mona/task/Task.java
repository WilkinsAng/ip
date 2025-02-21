package mona.task;

/**
 * Represents a generic task with a description and a completion status.
 * This is an abstract class that serves as a base for different types of tasks.
 */
public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;
    protected TaskPriority priority;

    /**
     * Creates a new task with the given description.
     * The task is initially marked as not done with a low priority.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description.strip();
        this.isDone = false;
        this.priority = TaskPriority.LOW;
    }

    /**
     * Creates a new task with the given description and priority.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     * @param priority The priority level of the task.
     */
    public Task(String description, TaskPriority priority) {
        this.description = description.strip();
        this.isDone = false;
        this.priority = priority;
    }

    /**
     * Creates a new task with the given description, completion status, and priority.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is marked as done.
     * @param priority The priority level of the task.
     */
    public Task(String description, boolean isDone, TaskPriority priority) {
        this.description = description.strip();
        this.isDone = isDone;
        this.priority = priority;
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
     * Sets the priority of the task.
     * @param priority The new priority.
     */
    public void setPriority(TaskPriority priority) {
        this.priority = priority;
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
     * Compares this task with the specified task for order based on priority.
     *
     * @param o the task to be compared.
     * @return a negative integer, zero, or a positive integer as this task
     *         has a lower, equal, or higher priority than the specified task.
     */
    @Override
    public int compareTo(Task o) {
        return this.priority.compareTo(o.priority);
    }

    /**
     * Returns the string representation of the task, including its status icon and description..
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "%s: [%s] %s".formatted(priority, getStatusIcon(), description);
    }

    /**
     * Returns a formatted string for saving to a file.
     *
     * @return the save format string for file storage.
     */
    public abstract String toSaveFormat();
}
