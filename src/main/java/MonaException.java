public class MonaException extends Exception{
    public MonaException(String message) {
        super(message);
    }

    public class UnknownCommandException extends MonaException {
        public UnknownCommandException(String command) {
            super(("MErroW? What kind of command is %s? That ain't one of my Phantom Thief tricks, Joker! " +
                    "Check your input and try again.")
                    .formatted(command));
        }
    }

    public class EmptyDescriptionException extends MonaException {
        public EmptyDescriptionException(String taskType) {
            super(("C'mon, Joker! You forgot to tell me what the %s is! " +
                    "I may be amazing, but I can't read minds!")
                    .formatted(taskType));
        }
    }

    //Only for deadlines
    public class EmptyDeadlineException extends MonaException {
        public EmptyDeadlineException() {
            super("Wait a sec, Joker! You can't set a deadline without a date! " +
                    "What, you expecting me to guess? Give me the details!");
        }
    }

    // Only used for events
    public class IncompleteDateException extends MonaException {
        public IncompleteDateException() {
            super("Mrrrow?! An event without a full time range?! " +
                    "Even *Mona the Magnificent* can’t work with that! Give me a proper time, Joker!");
        }
    }
}
