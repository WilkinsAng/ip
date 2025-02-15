package mona.ui;

import java.io.IOException;
import java.util.ArrayList;

import mona.exception.MonaException;
import mona.task.Task;


/**
 * Handles user interactions, including displaying messages and reading user input.
 */
public class Ui {

    /**
     * Displays the greeting message.
     */
    public static String greet() {
        return "What's up, Joker? What are we going to do today?";
    }

    /**
     * Displays the farewell message and closes the input scanner.
     */
    public String bye() {
        return "We should get ready for tomorrow. Goodnight, Joker. Meowww.\n"
                + "You can close me now!";
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task    The task that has been added.
     * @param tasksSize The size of the task list.
     */
    public String showAddTask(Task task, int tasksSize) {
        return String.format("Okie Joker, I'll help you remember to:\n %s.\n", task.toString())
            + String.format("Don't forget, you have %d tasks now.\n", tasksSize);
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param index   The index of the task in the task list.
     * @param task    The task that has been deleted.
     * @param taskSize The size of the task list.
     */
    public String showDeleteTask(int index, Task task, int taskSize) {
        return String.format("Task #%d has been erased from existence, Joker!\n", index + 1)
                + String.format(" %s\nwon't be bothering us anymore!\n", task)
                + String.format("Don't forget, you have %d tasks now.\n", taskSize);
    }

    /**
     * Displays all tasks currently in the list.
     *
     * @param tasks The task list to be displayed.
     */
    public String showAllTasks(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder("Alright Joker, here is what you need to do:\n");
        if (tasks.isEmpty()) {
            result.append("Waittt, you didn't tell me anything!!");
        } else {
            for (int i = 1; i <= tasks.size(); i++) {
                result.append(i).append(": ").append(tasks.get(i - 1)).append("\n");
            }
        }
        return result.toString();
    }

    /**
     * Displays the results of a search query on the task list.
     *
     * @param tasks The list of tasks that match the search query.
     * @param queries The search query entered by the user.
     * @return A message to display to the user summarizing the search results.
     */
    public String showFindResults(ArrayList<Task> tasks, String... queries) {
        StringBuilder query = new StringBuilder();
        for (String q : queries) {
            query.append('\'').append(q).append('\'').append(", ");
        }
        query.setLength(query.length() - 2);

        if (tasks.isEmpty()) {
            return String.format("Mrrrow?! %s? I don’t see anything like that in your list, Joker! \n"
                    + "Maybe you should actually write it down first, huh?\n", query);
        } else {
            StringBuilder result = new StringBuilder(String.format("HaHA! A flawless search, executed purrfectly!\n"
                    + "Here are the results for %s, Joker!:\n", query));
            for (int i = 1; i <= tasks.size(); i++) {
                result.append(i).append(": ").append(tasks.get(i - 1)).append("\n");
            }
            return result.toString();
        }
    }

    /**
     * Displays a message when a task is marked as completed.
     *
     * @param task The task that has been marked as done.
     */
    public String showMarkMessage(Task task) {
        return "All right, Joker! Very smooth!\n"
            + task.toString();
    }

    /**
     * Displays a message when a task is marked as incomplete.
     *
     * @param task The task that has been marked as undone.
     */
    public String showUnmarkMessage(Task task) {
        return "What?! You changed your mind, Joker...?!\n"
            + task.toString();
    }

    /**
     * Displays an error message.
     *
     * @param e The MonaException thrown.
     * @return The error message.
     */
    public String showErrorMessage(MonaException e) {
        return e.getMessage();
    }

    /**
     * Returns a message to be displayed when an IO error occurs during loading from file.
     *
     * @param e The IOException thrown.
     * @return A message describing the error to the user.
     */
    public String showLoadingError(IOException e) {
        return "Whoa! Looks like a glitch in the system! I got this message: *"
                + e.getMessage() + "*. Better check the files, Joker!";
    }


    /**
     * Returns a message to be displayed when an IO error occurs during saving to file.
     *
     * @param e The IOException thrown.
     * @return A message describing the error to the user.
     */
    public String showSavingError(IOException e) {
        return "Whoa! Looks like a something went wrong while saving, Joker! I got this message: *"
                + e.getMessage();
    }
}
