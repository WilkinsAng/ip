package task;

import java.util.ArrayList;
import java.util.Locale;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addList(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> findResults(String query) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task: tasks) {
            String des = task.description.toLowerCase();
            query = query.toLowerCase();
            if (des.contains(query)) {
                results.add(task);
            }
        }
        return results;
    }
}
