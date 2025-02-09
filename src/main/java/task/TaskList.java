package task;

import java.util.ArrayList;

/**
 * Manages a list of tasks, providing methods to add, remove, and retrieve tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list with an initial capacity of 100 tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Creates a task list with a pre-existing list of tasks.
     *
     * @param tasks The tasks to add to the list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds the given task to the list.
     *
     * @param task The task to add.
     */
    public void addList(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns a task from the list at the specified index.
     *
     * @param index The index of the task to remove.
     * @return The task that was removed.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the list of all tasks.
     *
     * @return The list containing all tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a specific task at the given index.
     *
     * @param index The index of the task to return (0-based).
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }
}