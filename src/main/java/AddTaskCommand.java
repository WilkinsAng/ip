public class AddTaskCommand extends Command {
    private Task task;

    public AddTaskCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addList(task);
        storage.saveData(tasks);
        System.out.printf("Okie Joker, I'll help you remember to:\n %s.\n", task.toString());
        System.out.printf("Don't forget, you have %d tasks now.\n", tasks.getSize());
    }
}
