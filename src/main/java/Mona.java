import java.util.Scanner;

public class Mona {
    private static final String INIT = "What's up, Joker? What are we going to do today?";
    private static final String NEXT_LINE = "---------------------------------------------------------------------";
    private static final String GOODBYE = "We should get ready for tomorrow. Goodnight, Joker.";

    public static void echo(String message) {
        System.out.println(message);
        System.out.println(NEXT_LINE);
    }

    public static void main(String[] args) {
        System.out.println(INIT);
        System.out.println(NEXT_LINE);

        Scanner input = new Scanner(System.in);

        while (true) {
            String message = input.nextLine();
            if (message.equalsIgnoreCase("bye")) {
                break;
            }
            echo(message);
        }

        input.close();

        System.out.println(GOODBYE);
        System.out.println(NEXT_LINE);
    }
}