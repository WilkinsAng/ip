public class Deadline extends Task{
    protected String doneBy;

    public Deadline(String description, String doneBy) {
        super(description);
        this.doneBy = doneBy.strip();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: %s)".formatted( doneBy);
    }
}
