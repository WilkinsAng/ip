public class MonaException extends Exception{
    public MonaException(String message) {
        super(message);
    }

    public static class UnknownCommandException extends MonaException {
        public UnknownCommandException(String command) {
            super(("MErroW? What kind of command is /%s/? That ain't one of my Phantom Thief tricks, Joker!\n" +
                    "Check that your input is one of these and try again:" +
                    "%s").formatted(command, Command.allCommands()));
        }
    }

    public static class EmptyMarkException extends MonaException {
        public EmptyMarkException() {
            super("Eh?! You didn't give me a task number! Try again and give me a valid number!\n" +
                    "Write in this format: mark <number>");
        }
    }
    public static class EmptyDescriptionException extends MonaException {
        public EmptyDescriptionException(String taskType) {
            super(("C'mon, Joker! You forgot to tell me what the %s is! " +
                    "I may be amazing, but I can't read minds!\n" +
                    "Write in this format: %s <description>")
                    .formatted(taskType, taskType));
        }
    }

    //Only for deadlines
    public static class EmptyDeadlineException extends MonaException {
        public EmptyDeadlineException() {
            super("Wait a sec, Joker! You can't set a deadline without a date! " +
                    "What, you expecting me to guess? Give me the details!\n" +
                    "Write in this format: deadline <description> /by <date>");
        }
    }

    // Only used for events
    public static class IncompleteDateException extends MonaException {
        public IncompleteDateException() {
            super("Mrrrow?! An event without a full time range?! " +
                    "Even *Mona the Magnificent* can’t work with that! Give me a proper time, Joker!\n" +
                    "Write in this format: event <description> /from <time> /to <time>");
        }

    }

    public static class TaskNotFoundException extends MonaException {
        public TaskNotFoundException(int index) {
            super("Eh?! There is no task #%d! Are you seeing things, Joker?\n".formatted(index) +
                    "Use /list/ to check your tasks again and give me a valid number!");
        }
    }

    public static class InvalidTaskNumberException extends MonaException {
        public InvalidTaskNumberException(String number) {
            super("Whoa Joker! /%s/ is not a valid task number! Try again and give me a valid number!"
                    .formatted(number));
        }
    }

    public static class EmptyTaskNumberException extends MonaException {
        public EmptyTaskNumberException() {
        super("Huh?! Delete what, Joker? You gotta give me a task number, not just 'delete'!");
        }
    }

    public static class CorruptedFileException extends MonaException {
        public CorruptedFileException() {
            super("Mrow?! This save file is a total mess! Looks like it's corrupted, Joker!" +
                    " I'll have to start fresh.");
        }
    }
}
