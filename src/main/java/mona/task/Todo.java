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
     * Creates a new Todo task with the given description and completion status.
     * Used for loading a saved task.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is marked as done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A formatted string including task type, status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string for saving the Todo task to storage.
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toSaveFormat() {
        String status = isDone ? "1" : "0";
        return "T | %s | %s".formatted(status, description);
    }
}
