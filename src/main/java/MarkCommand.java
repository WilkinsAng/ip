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
            System.out.println("All right, Joker! Very smooth!");
        } else {
            System.out.println("Hey! It's already done!!");
        }
        storage.saveData(tasks);
        System.out.println(task);
    }
}
