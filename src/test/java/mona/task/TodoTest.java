package mona.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import mona.task.Todo;


public class TodoTest {

    @Test
    void testTodoConstructor() {
        Todo todo = new Todo("Test"); //Create test Task

        //Verify task name is set correctly
        assertEquals("Test", todo.description);
        assertFalse(todo.isDone, "New tasks should be marked as not done by default");
    }

    @Test
    void testMarkAsDone() {
        Todo todo = new Todo("Test"); //Create test Task

        //Verify task is done
        todo.markAsDone();
        assertTrue(todo.isDone, "Task should be marked as done after calling markAsDone()");
    }

    @Test
    void testMarkAsUndone() {
        Todo todo = new Todo("Test"); //Create test Task

        //Verify task can be undone
        todo.markAsDone();
        todo.markAsUndone();
        assertFalse(todo.isDone, "Task should be marked as not done after calling markAsUndone()");
    }


    @Test
    public void testToString() {
        Todo todo = new Todo("Test"); //Create test Task

        //Verify the full output format
        assertEquals("[T][ ] Test", todo.toString());

        //Verify task is not done initially
        assertFalse(todo.isDone, "New tasks should be marked as not done by default");
        todo.markAsDone();
        assertEquals("[T][X] Test", todo.toString(), "Incorrect string representation for a completed task");
    }

    @Test
    void testToSaveFormat() {
        Todo todo = new Todo("Test"); //Create test Task

        //Verify that the save format is correct when the task is incomplete
        assertEquals("T | 0 | Test", todo.toSaveFormat(), "Incorrect save format for an incomplete task");

        //Verify that the save format is correct when the task is complete
        todo.markAsDone();
        assertEquals("T | 1 | Test", todo.toSaveFormat(), "Incorrect save format for a completed task");
    }
}