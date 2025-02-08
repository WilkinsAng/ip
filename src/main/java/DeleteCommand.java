public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new MonaException.TaskNotFoundException(index + 1);
        }
        Task task = tasks.deleteTask(index);
        storage.saveData(tasks);
        System.out.printf("Task #%d has been erased from existence, Joker!\n", index + 1);
        System.out.printf(" %s\nwon't be bothering us anymore!\n", task);
        System.out.printf("Don't forget, you have %d tasks now.\n", tasks.size());
    }
}
