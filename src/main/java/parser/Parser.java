/**
 * Parses user input into commands.
 */
package parser;

import command.AddTaskCommand;
import command.ByeCommand;
import command.Command;
import command.Commands;
import command.DeleteCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import exception.MonaException;
import task.Deadline;
import task.Event;
import task.Todo;

/**
 * Parses user input messages and converts them into executable commands.
 */
public class Parser {

    /**
     * Parses a user input message and returns the corresponding command.
     *
     * @param message The user input message.
     * @return The corresponding {@code Command} object.
     * @throws MonaException If the input message is invalid.
     */
    public static Command parse(String message) throws MonaException {
        String[] splitMsg = message.split(" ");
        Commands command = Commands.fromString(splitMsg[0]);
        switch (command) {
        case LIST:
            return printList();
        case MARK:
        case UNMARK:
            return handleMark(splitMsg);
        case TODO:
            return handleTodo(message);
        case DEADLINE:
            return handleDeadline(message);
        case EVENT:
            return handleEvent(message);
        case DELETE:
            return handleDelete(message);
        case BYE:
            return handleBye();
        default:
            throw new MonaException.UnknownCommandException(message);
        }
    }

    /**
     * Creates a command to list all tasks.
     * 
     * @return a new {@link ListCommand}
     */
    public static ListCommand printList() {
        return new ListCommand();
    }

    /**
     * Creates a command to exit the program.
     * 
     * @return a new {@link ByeCommand}
     */
    public static ByeCommand handleBye() {
        return new ByeCommand();
    }

    /**
     * Handles marking or unmarking a task.
     * 
     * @param parts The user input split into words.
     * @return a new {@link MarkCommand} or an {@link UnmarkCommand}
     * @throws MonaException if the input is invalid
     */
    public static Command handleMark(String[] parts) throws MonaException {
        if (parts.length != 2) {
            throw new MonaException.EmptyMarkException();
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (parts[0].equalsIgnoreCase("mark")) {
                return new MarkCommand(index);
            } else {
                return new UnmarkCommand(index);
            }
        } catch (NumberFormatException e) {
            throw new MonaException.InvalidTaskNumberException(parts[1]);
        }
    }

    /**
     * Handles adding a new Todo task.
     * 
     * @param message The user input message.
     * @return a new {@link AddTaskCommand} for a {@link Todo}
     * @throws MonaException if the input is invalid
     */
    public static AddTaskCommand handleTodo(String message) throws MonaException {
        if (message.length() < 6) {
            throw new MonaException.EmptyDescriptionException("todo");
        }
        String taskName = message.substring(5);

        if (taskName.isBlank()) {
            throw new MonaException.EmptyDescriptionException("todo");
        }
        return new AddTaskCommand(new Todo(taskName));
    }

    /**
     * Handles adding a new Deadline task.
     *
     * @param message The user input message.
     * @return a new {@link AddTaskCommand} for a {@link Deadline}
     * @throws MonaException if the input is invalid
     */
    public static AddTaskCommand handleDeadline(String message) throws MonaException {
        String[] instr = message.split(" /by");

        // If the first part is just the word "deadline", then there is no description.
        if (instr[0].strip().equalsIgnoreCase("deadline")) {
            throw new MonaException.EmptyDescriptionException("deadline task");
        }

        // Ie. 2nd half doesn't exist.
        if (instr.length < 2) {
            throw new MonaException.EmptyDeadlineException();
        }
        String taskName = instr[0].substring(9);
        String date = instr[1].strip();
        return new AddTaskCommand(new Deadline(taskName, date));
    }

    /**
     * Handles adding a new Event task.
     *
     * @param message The user input message.
     * @return a new {@link AddTaskCommand} for an {@link Event}
     * @throws MonaException if the input is invalid
     */
    public static AddTaskCommand handleEvent(String message) throws MonaException {
        String[] instr = message.split(" /from");
        if (instr[0].strip().equalsIgnoreCase("event")) {
            throw new MonaException.EmptyDescriptionException("event");
        }
        if (instr.length < 2) {
            throw new MonaException.IncompleteDateException();
        }
        String[] dates = instr[1].split("/to ");
        if (dates.length < 2 || dates[0].isBlank() || dates[1].isBlank()) {
            throw new MonaException.IncompleteDateException();
        }
        String taskName = instr[0].substring(6);
        String from = dates[0].strip();
        String to = dates[1].strip();
        return new AddTaskCommand(new Event(taskName, from, to));
    }

    /**
     * Handles deleting a task from the list.
     *
     * @param message The user input message.
     * @return a new {@link DeleteCommand}
     * @throws MonaException if the input is invalid
     */
    public static DeleteCommand handleDelete(String message) throws MonaException {
        String[] instr = message.split(" ");
        if (instr.length != 2) {
            throw new MonaException.EmptyTaskNumberException();
        }
        try {
            int index = Integer.parseInt(instr[1]) - 1;
            return new DeleteCommand(index);

        } catch (NumberFormatException e) {
            throw new MonaException.InvalidTaskNumberException(instr[1]);
        }
    }
}