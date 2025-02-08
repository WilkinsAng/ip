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
        ui.showDeleteTask(index, task, tasks.getSize());
    }
}
