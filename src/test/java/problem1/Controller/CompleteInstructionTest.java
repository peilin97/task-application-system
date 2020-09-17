package problem1.Controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import problem1.Model.ErrorLogger;
import problem1.Model.Todo.Builder;
import problem1.Model.TodoList;

public class CompleteInstructionTest {
  CompleteInstruction instruction;
  TodoList list;

  @Before
  public void setUp() throws Exception {
    instruction = new CompleteInstruction(1);
    list = new TodoList(FileProcessor.getFileToMap("todos.csv"), new ErrorLogger());
  }

  @Test
  public void getId() {
    assertEquals(1, (Object)instruction.getId());
  }

  @Test
  public void takeAction() {
    instruction.takeAction(list);
    assertTrue(list.getTodoList().get(1).getCompleted());
  }

  @Test
  public void testEquals() {
    CompleteInstruction instruction1 = null;
    int instruction2 = 2;
    assertFalse(instruction.equals(instruction1));
    assertFalse(instruction.equals(instruction2));
    CompleteInstruction instruction3 = new CompleteInstruction(2);
    assertFalse(instruction.equals(instruction3));
    CompleteInstruction instruction4 = new CompleteInstruction(1);
    assertTrue(instruction.equals(instruction4));
    assertTrue(instruction.equals(instruction));
  }

  @Test
  public void testHashCode() {
    assertEquals(instruction.hashCode(), instruction.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("CompleteInstruction{id=1}", instruction.toString());
  }
}