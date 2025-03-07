package mona.parser;

import java.util.ArrayList;

import mona.command.AddTaskCommand;
import mona.command.ByeCommand;
import mona.command.Command;
import mona.command.Commands;
import mona.command.DeleteCommand;
import mona.command.FindCommand;
import mona.command.ListCommand;
import mona.command.MarkCommand;
import mona.command.PrioritizeCommand;
import mona.command.UnmarkCommand;
import mona.exception.MonaException;
import mona.task.Deadline;
import mona.task.Event;
import mona.task.TaskPriority;
import mona.task.Todo;

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
            return createListCommand();
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
        case FIND:
            return handleFind(splitMsg);
        case PRIORITIZE:
            return handlePrioritize(splitMsg);
        case BYE:
            return createByeCommand();
        default:
            throw new MonaException.UnknownCommandException(message);
        }
    }

    /**
     * Creates a command to list all tasks.
     *
     * @return a new {@link ListCommand}
     */
    public static ListCommand createListCommand() {
        return new ListCommand();
    }

    /**
     * Creates a command to exit the program.
     *
     * @return a new {@link ByeCommand}
     */
    public static ByeCommand createByeCommand() {
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
            throw new MonaException.EmptyTaskNumberException("mark");
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
        if (message.length() <= 5) {
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
        String[] parts = message.split(" /by");

        //If the first part is just the word "deadline", then there is no description.
        if (parts[0].strip().equalsIgnoreCase("deadline")) {
            throw new MonaException.EmptyDescriptionException("deadline");
        }

        //i.e. 2nd half doesn't exist.
        if (parts.length < 2) {
            throw new MonaException.EmptyDeadlineException();
        }
        String taskName = parts[0].substring(9);
        String date = parts[1].strip();
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
        String[] parts = message.split(" /from");
        if (parts[0].strip().equalsIgnoreCase("event")) {
            throw new MonaException.EmptyDescriptionException("event");
        }
        if (parts.length < 2) {
            throw new MonaException.IncompleteDateException();
        }
        String[] dates = parts[1].split("/to ");
        if (dates.length < 2 || dates[0].isBlank() || dates[1].isBlank()) {
            throw new MonaException.IncompleteDateException();
        }
        String taskName = parts[0].substring(6);
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
        String[] parts = message.split(" ");
        if (parts.length != 2) {
            throw new MonaException.EmptyTaskNumberException("delete");
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            return new DeleteCommand(index);

        } catch (NumberFormatException e) {
            throw new MonaException.InvalidTaskNumberException(parts[1]);
        }
    }

    /**
     * Handles finding tasks in the task list by keyword.
     *
     * @param parts The user input split into words.
     * @return a new {@link FindCommand}
     * @throws MonaException if the input is invalid
     */
    public static FindCommand handleFind(String[] parts) throws MonaException {
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new MonaException.EmptyDescriptionException("find");
        }
        ArrayList<String> queries = new ArrayList<>();
        for (String part : parts) {
            if (!part.equals(parts[0])) {
                queries.add(part);
            }
        }
        return new FindCommand(queries.toArray(new String[0]));
    }

    /**
     * Handles prioritizing a task in the task list.
     *
     * @param parts The user input split into words.
     * @return a new {@link PrioritizeCommand}
     * @throws MonaException if the input is invalid
     */
    public static PrioritizeCommand handlePrioritize(String[] parts) throws MonaException {
        if (parts.length != 3) {
            throw new MonaException.InvalidPrioritizeException();
        }

        try {
            int index = Integer.parseInt(parts[1]) - 1;
            int priority = Integer.parseInt(parts[2]);
            return new PrioritizeCommand(index, TaskPriority.fromPriorityLevel(priority));
        } catch (IllegalArgumentException e) {
            throw new MonaException.InvalidPrioritizeException();
        }
    }
}
