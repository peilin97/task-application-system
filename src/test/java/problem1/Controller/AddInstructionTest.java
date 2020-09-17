package problem1.Controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import problem1.Model.ErrorLogger;
import problem1.Model.Todo;
import problem1.Model.Todo.Builder;
import problem1.Model.TodoList;

public class AddInstructionTest {
  Todo todo;
  TodoList list;
  AddInstruction instruction;

  @Before
  public void setUp() throws Exception {
    todo = new Builder("test").build();
    list = new TodoList(FileProcessor.getFileToMap("todos.csv"), new ErrorLogger());
    instruction = new AddInstruction(todo);
  }

  @Test
  public void getTodo() {
    assertEquals(todo, instruction.getTodo());
  }

  @Test
  public void takeAction() {
    instruction.takeAction(list);
    assertEquals(list.getTodoList().get(6), todo);
  }

  @Test
  public void testEquals() {
    AddInstruction instruction1 = null;
    int instruction2 = 2;
    assertFalse(instruction.equals(instruction1));
    assertFalse(instruction.equals(instruction2));
    AddInstruction instruction3 = new AddInstruction(new Builder("TEST").build());
    assertFalse(instruction.equals(instruction3));
    AddInstruction instruction4 = new AddInstruction(new Builder("test").build());
    assertTrue(instruction.equals(instruction4));
    assertTrue(instruction.equals(instruction));
  }

  @Test
  public void testHashCode() {
    assertEquals(instruction.hashCode(), instruction.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("AddInstruction{todo=text:'test' completed:'false' dueDate:'null' priority:'null' category:'null'}", instruction.toString());
  }
}