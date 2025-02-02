public class Event extends Task {
    protected String startFrom;
    protected String endBy;

    public Event(String description, String startFrom, String endBy) {
        super(description);
        this.startFrom = startFrom;
        this.endBy = endBy;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: %s, to: %s)".formatted(startFrom, endBy);
    }
}
