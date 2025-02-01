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
        String message = input.nextLine().toLowerCase();

        while (!message.equals("bye")) {
            if (message.equals("list")) {
                printList();
            } else if (message.contains("mark") || message.contains("unmark")) {
                String[] intr = message.split(" ");
                int index = Integer.parseInt(intr[1]) - 1;
                if (intr[0].equals("mark")){
                    markTaskDone(tasks.get(index));
                } else {
                    markTaskUndone(tasks.get(index));
                }
            } else {
                addList(message);
            }
            System.out.println(NEXT_LINE);
            message = input.nextLine().toLowerCase();
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
        System.out.println(NEXT_LINE);
    }

    public static void addList(String input) {
        Task task = new Task(input);
        tasks.add(task);
        System.out.printf("Okie Joker, I'll help you remember to %s.\n", input);
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
}