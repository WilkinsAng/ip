public class UnmarkCommand extends Command{
    private int index;

    public UnmarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new MonaException.TaskNotFoundException(index + 1);
        }

        Task task = tasks.getTask(index);

        if (task.getIsDone()){
            task.markAsUndone();
            System.out.println("What?! Come on, Joker...!");
        } else {
            System.out.println("Hey! Did you forget or something?!");
        }
        System.out.println(task);
    }
}
