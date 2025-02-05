public enum Command {
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    MARK,
    UNMARK,
    LIST,
    BYE;

    public static Command fromString(String command) throws MonaException {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MonaException.UnknownCommandException(command);
        }
    }

    public static String allCommands() {
        StringBuilder commands = new StringBuilder();
        for (Command command : Command.values()) {
            commands.append("\n").append(command.name());
        }
        return commands.toString();
    }
}
