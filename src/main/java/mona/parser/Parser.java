package mona.parser;

import mona.command.AddTaskCommand;
import mona.command.ByeCommand;
import mona.command.Command;
import mona.command.Commands;
import mona.command.DeleteCommand;
import mona.command.ListCommand;
import mona.command.MarkCommand;
import mona.command.UnmarkCommand;
import mona.exception.MonaException;
import mona.task.Deadline;
import mona.task.Event;
import mona.task.Todo;

public class Parser {

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
        case BYE:
            return createByeCommand();
        default:
            throw new MonaException.UnknownCommandException(message);
        }
    }

    public static ListCommand createListCommand() {
        return new ListCommand();
    }

    public static ByeCommand createByeCommand() {
        return new ByeCommand();
    }

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

    public static AddTaskCommand handleDeadline(String message) throws MonaException {
        String[] parts = message.split(" /by");

        //If the first part is just the word "deadline", then there is no description.
        if (parts[0].strip().equalsIgnoreCase("deadline")) {
            throw new MonaException.EmptyDescriptionException("deadline task");
        }

        //Ie. 2nd half doesn't exist.
        if (parts.length < 2) {
            throw new MonaException.EmptyDeadlineException();
        }
        String taskName = parts[0].substring(9);
        String date = parts[1].strip();
        return new AddTaskCommand(new Deadline(taskName, date));
    }

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

    public static DeleteCommand handleDelete(String message) throws MonaException {
        String[] parts = message.split(" ");
        if (parts.length != 2) {
            throw new MonaException.EmptyTaskNumberException();
        }
        try {
            int index = Integer.parseInt(parts[1]) - 1;
            return new DeleteCommand(index);

        } catch (NumberFormatException e) {
            throw new MonaException.InvalidTaskNumberException(parts[1]);
        }
    }
}
