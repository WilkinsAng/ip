public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.strip();
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[%s] %s".formatted(getStatusIcon(), description);
    }
}