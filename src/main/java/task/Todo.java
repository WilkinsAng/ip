package task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }

    @Override
    public String toSaveFormat() {
        return "T | %s | %s".formatted(isDone ? "1" : "0", description);
    }
}
