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
        String messageIgnoreCaps = message.toLowerCase();

        while (!messageIgnoreCaps.equals("bye")) {
            if (messageIgnoreCaps.equals("list")) {
                printList();
            } else if (messageIgnoreCaps.contains("mark") || messageIgnoreCaps.contains("unmark")) {
                String[] intr = message.split(" ");
                int index = Integer.parseInt(intr[1]) - 1;
                if (intr[0].equalsIgnoreCase("mark")){
                    markTaskDone(tasks.get(index));
                } else {
                    markTaskUndone(tasks.get(index));
                }
            } else if (messageIgnoreCaps.contains("todo")) {
                handleTodo(message);
            } else if (messageIgnoreCaps.contains("deadline")) {
                handleDeadline(message);
            } else if (messageIgnoreCaps.contains("event")) {
                handleEvent(message);
            } else {
                System.out.println("What? What's that supposed to mean??");
            }
            System.out.println(NEXT_LINE);
            message = input.nextLine();
            messageIgnoreCaps = message.toLowerCase();
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

    public static void handleTodo(String message) {
        String taskName = message.substring(5);
        Task task = new Todo(taskName);
        addList(task);
    }

    public static void handleDeadline(String message) {
        String instr = message.substring(9);
        String[] taskName = instr.split("/by ");
        Task task = new Deadline(taskName[0], taskName[1]);
        addList(task);
    }

    public static void handleEvent(String message) {
        String instr = message.substring(6);
        String[] taskName = instr.split("/from ");
        String[] dates = taskName[1].split("/to ");
        Task task = new Event(taskName[0], dates[0], dates[1]);
        addList(task);
    }
}