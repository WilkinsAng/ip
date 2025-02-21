package mona.task;

/**
 * Represents a Todo task with a description and completion status.
 *
 * <p>A Todo task is a basic task type that does not have a deadline or any
 * additional information.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a new Todo task with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is marked as done.
     * @param priority The priority level of the task.
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