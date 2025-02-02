public class Event extends Task {
    protected String startFrom;
    protected String endBy;

    public Event(String description, String startFrom, String endBy) {
        super(description);
        this.startFrom = startFrom.strip();
        this.endBy = endBy.strip();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: %s, to: %s)".formatted(startFrom, endBy);
    }
}
