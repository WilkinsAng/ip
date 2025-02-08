package command;

import exception.MonaException;

public enum Commands {
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    MARK,
    UNMARK,
    LIST,
    BYE;

    public static Commands fromString(String command) throws MonaException {
        try {
            return Commands.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MonaException.UnknownCommandException(command);
        }
    }

    public static String allCommands() {
        StringBuilder commands = new StringBuilder();
        for (Commands command : Commands.values()) {
            commands.append("\n").append(command.name());
        }
        return commands.toString();
    }
}
