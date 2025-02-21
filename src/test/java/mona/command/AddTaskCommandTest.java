package mona.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import mona.exception.MonaException;
import mona.storage.Storage;
import mona.task.TaskList;
import mona.task.Todo;
import mona.ui.Ui;


public class AddTaskCommandTest {

    @Test
    public void testExecute() throws Exception {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        AddTaskCommand addTaskCommand = new AddTaskCommand(new Todo("Test"));

        addTaskCommand.execute(tasks, ui, storage);

        assertEquals(1, tasks.getSize());
        assertEquals("[T][ ] Test [LOW]", tasks.getTask(0).toString());
    }

    @Test
    public void testExecute_multipleTasks() throws Exception {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        AddTaskCommand addTask1 = new AddTaskCommand(new Todo("Task 1"));
        AddTaskCommand addTask2 = new AddTaskCommand(new Todo("Task 2"));

        addTask1.execute(tasks, ui, storage);
        addTask2.execute(tasks, ui, storage);

        assertEquals(2, tasks.getSize());
        assertEquals("[T][ ] Task 1 [LOW]", tasks.getTask(0).toString());
        assertEquals("[T][ ] Task 2 [LOW]", tasks.getTask(1).toString());
    }
}
