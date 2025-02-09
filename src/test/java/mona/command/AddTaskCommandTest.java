package mona.command;

import org.junit.jupiter.api.Test;
import mona.storage.Storage;
import mona.task.TaskList;
import mona.ui.Ui;
import mona.task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTaskCommandTest {

    @Test
    public void testExecute() throws Exception {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        AddTaskCommand addTaskCommand = new AddTaskCommand(new Todo("Test"));

        addTaskCommand.execute(tasks, ui, storage);

        assertEquals(1, tasks.getSize());
        assertEquals("[T][ ] Test", tasks.getTask(0).toString());
    }

}
