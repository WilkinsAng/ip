import java.util.ArrayList;
import java.util.Scanner;


public class Mona {
    private static final String INIT = "What's up, Joker? What are we going to do today?";
    private static final String NEXT_LINE = "---------------------------------------------------------------------";
    private static final String GOODBYE = "We should get ready for tomorrow. Goodnight, Joker. Meowww.";

    private static ArrayList<Task> tasks = new ArrayList<>(100);

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        greet();
        String message = input.nextLine();

        while (!message.toUpperCase().equals(Command.BYE.name())) {
            try {
                String[] splitMsg = message.split(" ");
                Command command = Command.fromString(splitMsg[0]);
                switch (command) {
                case LIST:
                    printList();
                    break;
                case MARK:
                case UNMARK:
                    handleMark(splitMsg);
                    break;
                case TODO:
                    handleTodo(message);
                    break;
                case DEADLINE:
                    handleDeadline(message);
                    break;
                case EVENT:
                    handleEvent(message);
                    break;
                case DELETE:
                    handleDelete(message);
                    break;
                default:
                    throw new MonaException.UnknownCommandException(message);
                }
            } catch (MonaException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(NEXT_LINE);
                message = input.nextLine();
            }
        }

        input.close();
        bye();
    }

    public static void greet() {
        System.out.println(INIT);
        System.out.println(NEXT_LINE);
    }

    public static void bye() {
        System.out.println(GOODBYE);
        System.out.print(NEXT_LINE);
    }

    public static void addList(Task input) {
        tasks.add(input);
        System.out.printf("Okie Joker, I'll help you remember to:\n %s.\n", input);
        System.out.printf("Don't forget, you have %d tasks now.\n", tasks.size());
    }

    public static void printList() {
        System.out.println("Alright Joker, here is what you need to do:");
        if (tasks.isEmpty()) {
            System.out.println("Waittt, you didn't tell me anything!!");
        } else {
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + ": " + tasks.get(i - 1));
            }
        }
    }

    public static void handleMark(String[] instr) throws MonaException {
        if (instr.length != 2) {
            throw new MonaException.EmptyMarkException();
        }
        try {
            int index = Integer.parseInt(instr[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new MonaException.TaskNotFoundException(index + 1);
            }
            if (instr[0].equalsIgnoreCase("mark")) {
                markTaskDone(tasks.get(index));
            } else {
                markTaskUndone(tasks.get(index));
            }
        } catch (NumberFormatException e) {
            throw new MonaException.InvalidTaskNumberException(instr[1]);
        }
    }

    public static void markTaskDone(Task task) {
        if (!task.isDone) {
            task.markAsDone();
            System.out.println("All right, Joker! Very smooth!");
        } else {
            System.out.println("Hey! It's already done!!");
        }
        System.out.println(task);
    }

    public static void markTaskUndone(Task task) {
        if (task.isDone){
            task.markAsUndone();
            System.out.println("What?! Come on, Joker...!");
        } else {
            System.out.println("Hey! Did you forget or something?!");
        }
        System.out.println(task);
    }

    public static void handleTodo(String message) throws MonaException {
        if (message.length() < 6) {
            throw new MonaException.EmptyDescriptionException("todo");
        }
        String taskName = message.substring(5);

        if (taskName.isBlank()) {
            throw new MonaException.EmptyDescriptionException("todo");
        }
        Task task = new Todo(taskName);
        addList(task);
    }

    public static void handleDeadline(String message) throws MonaException {
        String[] instr = message.split(" /by");

        //If the first part is just the word "deadline", then there is no description.
        if (instr[0].equalsIgnoreCase("deadline") ||
                instr[0].equalsIgnoreCase("deadline ")) {
            throw new MonaException.EmptyDescriptionException("deadline task");
        }

        //Ie. 2nd half doesn't exist.
        if (instr.length < 2) {
            throw new MonaException.EmptyDeadlineException();
        }
        String taskName = instr[0].substring(9);
        Task task = new Deadline(taskName, instr[1]);
        addList(task);
    }

    public static void handleEvent(String message) throws MonaException {
        String[] instr = message.split(" /from");
        if (instr[0].equalsIgnoreCase("event") ||
                instr[0].equalsIgnoreCase("event ")) {
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
        Task task = new Event(taskName, dates[0], dates[1]);
        addList(task);
    }

    public static void handleDelete(String message) throws MonaException {
        String[] instr = message.split(" ");
        if (instr.length != 2) {
            throw new MonaException.EmptyTaskNumberException();
        }
        try {
            int index = Integer.parseInt(instr[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new MonaException.TaskNotFoundException(index + 1);
            }
            Task task = tasks.remove(index);
            System.out.printf("Task #%d has been erased from existence, Joker!\n", index + 1);
            System.out.printf(" %s\nwon't be bothering us anymore!\n", task);
            System.out.printf("Don't forget, you have %d tasks now.\n", tasks.size());
        } catch (NumberFormatException e) {
            throw new MonaException.InvalidTaskNumberException(instr[1]);
        }
    }
}