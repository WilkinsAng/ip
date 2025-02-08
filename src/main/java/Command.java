public abstract class Command {

    private boolean isExit = false;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException;

    public boolean isExit() {
        return this.isExit;
    }

    public void setExit() {
        isExit = true;
    }
}
