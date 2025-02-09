package ui;

import java.util.ArrayList;
import java.util.Scanner;

import task.Task;

public class Ui {
    private static final String NEXT_LINE = "---------------------------------------------------------------------";

    private Scanner input;

    public Ui() {
        input = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("What's up, Joker? What are we going to do today?");
        System.out.println(NEXT_LINE);
    }

    public void bye() {
        input.close();
        System.out.println("We should get ready for tomorrow. Goodnight, Joker. Meowww.");
        System.out.print(NEXT_LINE);
    }

    public void nextLine() {
        System.out.println(NEXT_LINE);
    }

    public String readCommand() {
        return input.nextLine();
    }

    public void showAddTask(Task task, int tasksSize) {
        System.out.printf("Okie Joker, I'll help you remember to:\n %s.\n", task.toString());
        System.out.printf("Don't forget, you have %d tasks now.\n", tasksSize);
    }

    public void showDeleteTask(int index, Task task, int taskSize) {
        System.out.printf("task.Task #%d has been erased from existence, Joker!\n", index + 1);
        System.out.printf(" %s\nwon't be bothering us anymore!\n", task);
        System.out.printf("Don't forget, you have %d tasks now.\n", taskSize);
    }

    public void showAllTasks(ArrayList<Task> tasks) {
        System.out.println("Alright Joker, here is what you need to do:");
        if (tasks.isEmpty()) {
            System.out.println("Waittt, you didn't tell me anything!!");
        } else {
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + ": " + tasks.get(i - 1));
            }
        }
    }

    public void showFindResults(ArrayList<Task> tasks, String query) {
        if (tasks.isEmpty()) {
            System.out.printf("Mrrrow?! '%s'? I don’t see anything like that in your list, Joker! \n" +
                    "Maybe you should actually write it down first, huh?\n", query);
        } else {
            System.out.printf("HaHA! A flawless search, executed purrfectly!\n" +
                    "Here are the results for '%s', Joker!:\n", query);
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + ": " + tasks.get(i - 1));
            }
        }
    }

    public void showMarkMessage(Task task) {
        System.out.println("All right, Joker! Very smooth!");
        System.out.println(task);
    }

    public void showUnmarkMessage(Task task) {
        System.out.println("What?! Come on, Joker...!");
        System.out.println(task);
    }
}
