package mona.ui;

import java.util.ArrayList;
import java.util.Scanner;

import mona.task.Task;


/**
 * Handles user interactions, including displaying messages and reading user input.
 */
public class Ui {
    private static final String NEXT_LINE = "---------------------------------------------------------------------";

    private Scanner input;

    /**
     * Creates a new user interface with input handling.
     */
    public Ui() {
        input = new Scanner(System.in);
    }

    /**
     * Displays the greeting message.
     */
    public void greet() {
        System.out.println("What's up, Joker? What are we going to do today?");
        System.out.println(NEXT_LINE);
    }

    /**
     * Displays the farewell message and closes the input scanner.
     */
    public void bye() {
        input.close();
        System.out.println("We should get ready for tomorrow. Goodnight, Joker. Meowww.");
        System.out.print(NEXT_LINE);
    }

    /**
     * Prints a line separator to the user.
     */
    public void nextLine() {
        System.out.println(NEXT_LINE);
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return input.nextLine();
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task    The task that has been added.
     * @param tasksSize The size of the task list.
     */
    public void showAddTask(Task task, int tasksSize) {
        System.out.printf("Okie Joker, I'll help you remember to:\n %s.\n", task.toString());
        System.out.printf("Don't forget, you have %d tasks now.\n", tasksSize);
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param index   The index of the task in the task list.
     * @param task    The task that has been deleted.
     * @param taskSize The size of the task list.
     */
    public void showDeleteTask(int index, Task task, int taskSize) {
        System.out.printf("Task #%d has been erased from existence, Joker!\n", index + 1);
        System.out.printf(" %s\nwon't be bothering us anymore!\n", task);
        System.out.printf("Don't forget, you have %d tasks now.\n", taskSize);
    }

    /**
     * Displays all tasks currently in the list.
     *
     * @param tasks The task list to be displayed.
     */
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


    /**
     * Displays the results of a search query on the task list.
     *
     * @param tasks The list of tasks that match the search query.
     * @param query The search query entered by the user.
     */
    public void showFindResults(ArrayList<Task> tasks, String query) {
        if (tasks.isEmpty()) {
            System.out.printf("Mrrrow?! '%s'? I don’t see anything like that in your list, Joker! \n"
                    + "Maybe you should actually write it down first, huh?\n", query);
        } else {
            System.out.printf("HaHA! A flawless search, executed purrfectly!\n"
                    + "Here are the results for '%s', Joker!:\n", query);
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + ": " + tasks.get(i - 1));
            }
        }
    }

    /**
     * Displays a message when a task is marked as completed.
     *
     * @param task The task that has been marked as done.
     */
    public void showMarkMessage(Task task) {
        System.out.println("All right, Joker! Very smooth!");
        System.out.println(task);
    }

    /**
     * Displays a message when a task is marked as incomplete.
     *
     * @param task The task that has been marked as undone.
     */
    public void showUnmarkMessage(Task task) {
        System.out.println("What?! You changed your mind, Joker...?!");
        System.out.println(task);
    }
}
