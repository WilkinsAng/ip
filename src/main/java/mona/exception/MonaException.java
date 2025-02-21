package mona.exception;

import mona.command.Commands;
import mona.task.Task;

/**
 * Exception class for handling various types of exceptions in the application.
 */
public class MonaException extends Exception {

    public MonaException(String message) {
        super(message);
    }

    /**
     * Exception thrown when an unknown command is encountered.
     */
    public static class UnknownCommandException extends MonaException {

        /**
         * Constructs a new UnknownCommandException with the specified command.
         *
         * @param command the unknown command.
         */
        public UnknownCommandException(String command) {
            super(("MErroW? What kind of command is /%s/? That ain't one of my Phantom Thief tricks, Joker!\n"
                    + "Check that your input is one of these and try again:"
                    + "%s").formatted(command, Commands.allCommands()));
        }
    }

    /**
     * Exception thrown when a task number is expected but not provided.
     */
    public static class EmptyTaskNumberException extends MonaException {

        /**
         * Constructs a new EmptyTaskNumberException with the specified command.
         *
         * @param command The command that requires a task number.
         */
        public EmptyTaskNumberException(String command) {
            super("Eh?! You didn't give me a task number! Try again and give me a valid number!\n"
                    + "Write in this format: %s <number>".formatted(command));
        }
    }

    /**
     * This exception is thrown when a description is required for a command but
     * is not provided.
     */
    public static class EmptyDescriptionException extends MonaException {
        /**
         * Constructs a new {@code EmptyDescriptionException} with the specified
         * command.
         *
         * @param command The command that requires a description.
         */
        public EmptyDescriptionException(String command) {
            super(((command.equals("find")
                    ? "C'mon, Joker! You forgot to tell me what to find! "
                    : "C'mon, Joker! You forgot to tell me what the %s is! ")
                    + "I may be amazing, but I can't read minds!\n"
                    + "Write in this format: %s <description>")
                    .formatted(command, command));
        }
    }

    /**
     * Exception thrown when a deadline task lacks a date.
     */
    public static class EmptyDeadlineException extends MonaException {

        /**
         * Constructs a new EmptyDeadlineException.
         */
        public EmptyDeadlineException() {
            super("Wait a sec, Joker! You can't set a deadline without a date! "
                    + "What, you expecting me to guess? Give me the details!\n"
                    + "Write in this format: deadline <description> /by <date>");
        }
    }

    /**
     * Exception thrown when an event lacks a complete time range.
     */
    public static class IncompleteDateException extends MonaException {

        /**
         * Constructs a new IncompleteDateException.
         */
        public IncompleteDateException() {
            super("Mrrrow?! An event without a full time range?! "
                    + "Even *Mona the Magnificent* can’t work with that! Give me a proper time, Joker!\n"
                    + "Write in this format: event <description> /from <time> /to <time>");
        }
    }

    /**
     * Exception thrown when an invalid task number is provided.
     */
    public static class TaskNotFoundException extends MonaException {

        /**
         * Constructs a new TaskNotFoundException with the specified index.
         *
         * @param index the index of the task.
         */
        public TaskNotFoundException(int index) {
            super("Eh?! There is no task #%d! Are you seeing things, Joker?\n".formatted(index)
                    + "Use /list/ to check your tasks again and give me a valid number!");
        }
    }

    /**
     * Exception thrown when an invalid task number is provided.
     */
    public static class InvalidTaskNumberException extends MonaException {

        /**
         * Constructs a new InvalidTaskNumberException with the specified number.
         *
         * @param number the invalid task number.
         */
        public InvalidTaskNumberException(String number) {
            super("Whoa Joker! /%s/ is not a valid task number! Try again and give me a valid number!"
                    .formatted(number));
        }
    }

    /**
     * Exception thrown when the save file is corrupted.
     */
    public static class CorruptedFileException extends MonaException {

        /**
         * Constructs a new CorruptedFileException.
         */
        public CorruptedFileException() {
            super("Mrow?! This save file is a total mess! Looks like it's corrupted, Joker!\n"
                    + " I'll have to start fresh.");
        }
    }

    /**
     * Exception thrown when an invalid date format is provided.
     */
    public static class InvalidDateFormatException extends MonaException {

        /**
         * Constructs a new InvalidDateFormatException.
         */
        public InvalidDateFormatException() {
            super("Mrow?! That doesn't look right, Joker! Try this format instead: '15 Jul 2024 11:30pm'.");
        }
    }

    /**
     * Exception thrown when trying to mark a task that is already done.
     */
    public static class TaskAlreadyDoneException extends MonaException {

        /**
         * Constructs a new TaskAlreadyDoneException with the specified task.
         *
         * @param task the task that is already done.
         */
        public TaskAlreadyDoneException(Task task) {
            super("Hey! It's already done!! No need to mark it again!\n%s".formatted(task));
        }
    }

    /**
     * Exception thrown when trying to mark a task that is already undone.
     */
    public static class TaskAlreadyUndoneException extends MonaException {

        /**
         * Constructs a new TaskAlreadyUndoneException with the specified task.
         *
         * @param task the task that is already undone.
         */
        public TaskAlreadyUndoneException(Task task) {
            super("Hey! Did you forget or something?! This task is already incomplete!\n%s".formatted(task));
        }
    }

    /**
     * Exception thrown when the priority is invalid.
     */
    public static class InvalidPrioritizeException extends MonaException {

        /**
         * Constructs a new InvalidPrioritizeException.
         */
        public InvalidPrioritizeException() {
            super("Whoa there, Joker! This isn’t a valid priority!\n"
                + "Write in this format: prioritize <task number> <priority: 1(HIGH), 2(MEDIUM), 3(LOW)>");
        }
    }
}
