package mona.command;

import mona.exception.MonaException;
import mona.storage.Storage;
import mona.task.TaskList;
import mona.ui.Ui;

/**
 * Represents an abstract command that can be executed.
 * Each specific command will extend this class and implement the execute method.
 */
public abstract class Command {

    private String reply;

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
     * Retrieves the reply message associated with the command.
     *
     * @return The reply message as a String.
     */
    public String getReply() {
        assert reply != null : "Reply should not be null";
        return reply;
    };

    /**
     * Sets the reply message associated with the command.
     *
     * @param reply The reply message as a String.
     */
    public void setReply(String reply) {
        this.reply = reply;
    }
}
