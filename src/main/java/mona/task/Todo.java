package mona.task;

/**
 * Represents a Todo task with a description and completion status.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a new Todo task with the given description and priority.
     *
     * @param description The description of the task.
     * @param priority The priority of the task.
     */
    public Todo(String description, TaskPriority priority) {
        super(description, priority);
    }

    /**
     * Creates a new Todo task with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is marked as done.
     */
    public Todo(String description, boolean isDone, TaskPriority priority) {
        super(description, isDone, priority);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A formatted string including task type, status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() + " " + priority;
    }

    /**
     * Returns a formatted string for saving the Todo task to storage.
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toSaveFormat() {
        String status = isDone ? "1" : "0";
        return "%d | T | %s | %s".formatted(priority.getPriorityLevel(), status, description);
    }
}
