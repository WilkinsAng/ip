package mona.command;

import mona.exception.MonaException;

/**
 * Represents the different commands that can be executed by the application.
 */
public enum Commands {
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    MARK,
    UNMARK,
    LIST,
    FIND,
    BYE;
    
    /**
     * Converts a string representation of a command into a corresponding {@code Commands} enum.
     *
     * @param command The string representation of the command.
     * @return The corresponding {@code Commands} enum.
     * @throws MonaException.UnknownCommandException If the string does not
     * correspond to a valid command.
     */
    public static Commands fromString(String command) throws MonaException {
        try {
            return Commands.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MonaException.UnknownCommandException(command);
        }
    }

    /**
     * Returns a string containing all available commands.
     *
     * @return A formatted string containing all supported commands.
     */
    public static String allCommands() {
        StringBuilder commands = new StringBuilder();
        for (Commands command : Commands.values()) {
            commands.append("\n").append(command.name());
        }
        return commands.toString();
    }
}