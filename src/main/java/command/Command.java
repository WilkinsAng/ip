package command;

import exception.MonaException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an abstract command that can be executed.
 * Each specific command will extend this class and implement the execute method.
 */
public abstract class Command {

    private boolean isExit = false;

    /**
     * Executes the command, performing necessary actions on the task list, user interface, and storage.
     *
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save or load data.
     * @throws MonaException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MonaException;

    /**
     * Returns whether the command is an exit command.
     *
     * @return {@code true} if the command is an exit command, {@code false} otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Sets the command as an exit command.
     */
    public void setExit() {
        this.isExit = true;
    }
}

