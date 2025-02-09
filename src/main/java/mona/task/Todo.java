package mona.task;

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
        String status = isDone ? "1" : "0";
        return "T | %s | %s".formatted(status, description);
    }
}