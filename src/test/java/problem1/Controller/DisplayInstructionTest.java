package problem1.Controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import problem1.Model.ErrorLogger;
import problem1.Model.TodoList;

public class DisplayInstructionTest {
  DisplayInstruction instruction;
  List<String> list;
  TodoList todoList;

  @Before
  public void setUp() throws Exception {
    list = new ArrayList<>(Arrays.asList("--show-category", "school"));
    instruction = new DisplayInstruction(list);
    todoList = new TodoList(FileProcessor.getFileToMap("todos.csv"), new ErrorLogger());
  }

  @Test
  public void getCommands() {
    assertEquals(list, instruction.getCommands());
  }

  @Test
  public void takeAction() {
    instruction.takeAction(todoList);
  }

  @Test
  public void testEquals() {
    DisplayInstruction instruction1 = null;
    int instruction2 = 2;
    assertFalse(instruction.equals(instruction1));
    assertFalse(instruction.equals(instruction2));
    DisplayInstruction instruction3 = new DisplayInstruction(new ArrayList<>(Arrays.asList("--sort-by-date")));
    assertFalse(instruction.equals(instruction3));
    DisplayInstruction instruction4 = new DisplayInstruction(new ArrayList<>(Arrays.asList("--show-category", "school")));
    assertTrue(instruction.equals(instruction4));
    assertTrue(instruction.equals(instruction));
  }

  @Test
  public void testHashCode() {
    assertEquals(instruction.hashCode(), instruction.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("DisplayInstruction{commands=[--show-category, school]}", instruction.toString());
  }
}