import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.DateTimeException;

public abstract class TimedTask extends Task {
    protected static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a");
    protected static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy MMM dd HH:mm a");

    public TimedTask(String description) {
        super(description);
    }

    public TimedTask(String description, boolean isDone) {
        super(description, isDone);
    }

    // For saving
    public static LocalDateTime parseDateTime(String date) throws MonaException {
        try {
            return LocalDateTime.parse(date, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new MonaException.InvalidDateFormatException();
        }
    }
    // For printing
    public static String formatDateTime(LocalDateTime date) {
        return date.format(OUTPUT_FORMATTER);
    }
}
