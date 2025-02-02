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
            super(("C'mon, Joker! You forgot to tell me what the " + taskType + " is! " +
                    "I may be amazing, but I can't read minds!")
                    .formatted(taskType));
        }
    }
}
