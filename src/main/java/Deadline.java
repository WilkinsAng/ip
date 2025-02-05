public class Deadline extends Task{
    protected String doneBy;

    public Deadline(String description, String doneBy) {
        super(description);
        this.doneBy = doneBy.strip();
    }

    public Deadline(String description, boolean isDone, String doneBy) {
        super(description, isDone);
        this.doneBy = doneBy.strip();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: %s)".formatted( doneBy);
    }
}
