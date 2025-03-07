package mona.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import mona.exception.MonaException;
import mona.task.Task;
import mona.task.TaskPriority;

/**
 * Handles user interactions, including displaying messages and reading user input.
 */
public class Ui {

    /**
     * Returns the greeting message.
     *
     * @return A String containing the greeting message.
     */
    public static String greet() {
        return "What's up, Joker? What are we going to do today?";
    }

    /**
     * Returns the farewell message.
     *
     * @return A String containing the farewell message.
     */
    public String bye() {
        return "We should get ready for tomorrow. Goodnight, Joker. Meowww.\n"
                + "You can close me now!";
    }

    /**
     * Returns a message confirming the addition of a task.
     *
     * @param task      The task that has been added.
     * @param tasksSize The current size of the task list.
     * @return A String containing the confirmation message.
     */
    public String showAddTask(Task task, int tasksSize) {
        return String.format("Okie Joker, I'll help you remember to:\n %s.\n", task.toString())
                + String.format("Don't forget, you have %d tasks now.\n", tasksSize);
    }

    /**
     * Returns a message confirming the deletion of a task.
     *
     * @param index    The index of the task in the task list.
     * @param task     The task that has been deleted.
     * @param taskSize The current size of the task list.
     * @return A String containing the confirmation message.
     */
    public String showDeleteTask(int index, Task task, int taskSize) {
        return String.format("Task #%d has been erased from existence, Joker!\n", index + 1)
                + String.format(" %s\nwon't be bothering us anymore!\n", task)
                + String.format("Don't forget, you have %d tasks now.\n", taskSize);
    }

    /**
     * Returns a message displaying all tasks in the list.
     *
     * @param tasks The list of tasks to be displayed.
     * @return A String containing the list of all tasks.
     */
    public String showAllTasks(ArrayList<Task> tasks) {
        String result = "Alright Joker, here is what you need to do:\n";

        if (tasks.isEmpty()) {
            return result + "Waittt, you didn't tell me anything!!";
        }

        return result
                + IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ": " + tasks.get(i))
                .collect(Collectors.joining("\n"));
    }

    /**
     * Returns a message displaying the results of a search query on the task list.
     *
     * @param tasks   The list of tasks matching the search query.
     * @param queries The search queries entered by the user.
     * @return A String summarizing the search results.
     */
    public String showFindResults(ArrayList<Task> tasks, String... queries) {
        String formattedQueries = Arrays.stream(queries)
                .map(query -> "'" + query + "'")
                .collect(Collectors.joining(", "));

        if (tasks.isEmpty()) {
            return String.format("Mrrrow?! %s? I don’t see anything like that in your list, Joker! \n"
                    + "Maybe you should actually write it down first, huh?\n", formattedQueries);
        }

        return String.format(String.format("HaHA! A flawless search, executed purrfectly!\n"
                        + "Here are the results for %s, Joker!:\n%s",
                formattedQueries,
                IntStream.range(0, tasks.size())
                        .mapToObj(i -> (i + 1) + ": " + tasks.get(i))
                        .collect(Collectors.joining("\n"))
        ));
    }

    /**
     * Returns a message indicating a task has been marked as completed.
     *
     * @param task The task that has been marked as done.
     * @return A String containing the confirmation message.
     */
    public String showMarkMessage(Task task) {
        return "All right, Joker! Very smooth!\n"
                + task.toString();
    }

    /**
     * Returns a message indicating a task has been marked as incomplete.
     *
     * @param task The task that has been marked as undone.
     * @return A String containing the confirmation message.
     */
    public String showUnmarkMessage(Task task) {
        return "What?! You changed your mind, Joker...?!\n"
                + task.toString();
    }

    /**
     * Returns an error message based on the exception provided.
     *
     * @param e The MonaException thrown.
     * @return A String containing the error message.
     */
    public String showErrorMessage(MonaException e) {
        return e.getMessage();
    }

    /**
     * Returns a message indicating a task's priority has been changed.
     *
     * @param task     The task whose priority has been changed.
     * @param priority The new priority of the task.
     * @return A String describing the priority change.
     */
    public String showPriorityChange(Task task, TaskPriority priority) {
        return String.format("Meow-ha! Task: \n"
                + " %s\n"
                + "is now at %s priority! Better get to it before it becomes a real heist!", task, priority);
    }

    /**
     * Returns a message indicating an IO error occurred during file loading.
     *
     * @param e The IOException thrown.
     * @return A String describing the loading error.
     */
    public String showLoadingError(IOException e) {
        return "Whoa! Looks like a glitch in the system! I got this message: *"
                + e.getMessage() + "*. Better check the files, Joker!";
    }

    /**
     * Returns a message indicating an IO error occurred during file saving.
     *
     * @param e The IOException thrown.
     * @return A String describing the saving error.
     */
    public String showSavingError(IOException e) {
        return "Whoa! Looks like a something went wrong while saving, Joker! I got this message: *"
                + e.getMessage();
    }
}
