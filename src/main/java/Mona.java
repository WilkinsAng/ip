import java.util.ArrayList;
import java.util.Scanner;

public class Mona {
    private static final String INIT = "What's up, Joker? What are we going to do today?";
    private static final String NEXT_LINE = "---------------------------------------------------------------------";
    private static final String GOODBYE = "We should get ready for tomorrow. Goodnight, Joker.";

    private static ArrayList<String> list = new ArrayList<String>(100);

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        greet();
        while (true) {
            String message = input.nextLine();
            if (message.equalsIgnoreCase("bye")) {
                break;
            }
            if (message.equalsIgnoreCase("list")) {
                printList();
            } else {
                addList(message);
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
        System.out.println(NEXT_LINE);
    }

    public static void addList(String input) {
        list.add(input);
        System.out.printf("Okie Joker, I'll help you remember to %s.", input);
        System.out.println(NEXT_LINE);
    }

    public static void printList() {
        System.out.println("Alright Joker, here is what you need to do:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + ": " + list.get(i - 1));
        }
        System.out.println(NEXT_LINE);
    }
}