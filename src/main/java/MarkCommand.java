public class MarkCommand extends Command{
    private int index;

    public MarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new MonaException.TaskNotFoundException(index + 1);
        }

        Task task = tasks.getTask(index);

        if (!task.getIsDone()) {
            task.markAsDone();
        } else {
            throw new MonaException.TaskAlreadyDoneException(task);
        }
        storage.saveData(tasks);
        ui.showMarkMessage(task);
    }
}
