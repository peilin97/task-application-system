package problem1.Model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class TodoListTest {

  TodoList list;
  Todo todo1;
  ErrorLogger logger;

  @Before
  public void setUp() throws Exception {
    todo1 = new Todo.Builder("finish hw8").build();
    Map<Integer, Todo> map = new HashMap<>();
    map.put(1, todo1);
    logger = new ErrorLogger();
    list = new TodoList(map, logger);
  }

  @Test
  public void getTodoList() {
    Map<Integer, Todo> expected = new HashMap<>();
    expected.put(1, todo1);
    assertEquals(expected, list.getTodoList());
  }

  @Test
  public void getErrorLogger() {
    ErrorLoggerDecorator expected = new ErrorLoggerDecorator(logger);
    assertEquals(expected, list.getErrorLogger());
  }

  @Test
  public void addTodo() {
    Todo todo2 = new Todo.Builder("code walk").build();
    list.addTodo(todo2);
    Map<Integer, Todo> expected = new HashMap<>();
    expected.put(1, todo1);
    expected.put(2, todo2);
    assertEquals(expected, list.getTodoList());
  }

  @Test
  public void completeTodo() {
    list.completeTodo(1);
    assertTrue(todo1.getCompleted());
    list.completeTodo(1);
    String error = "Errors: The todo with the ID 1 has already been completed."+System.lineSeparator();
    assertEquals(error, logger.getError());
  }

  @Test
  public void displayTodo() {
    List<String> commands1 = new ArrayList<>();
    commands1.add("--show-category");
    commands1.add("work");
    list.displayTodo(commands1);
    String error = "Errors: Category is not found!"+System.lineSeparator();
    assertEquals(error, logger.getError());
    List<String> commands2 = new ArrayList<>();
    commands2.add("--show-incomplete");
  }

  @Test
  public void testEquals() {
    assertTrue(list.equals(list));
    TodoList nullList = null;
    assertFalse(list.equals(nullList));
    assertFalse(list.equals("aa"));
    Map<Integer, Todo> map = new HashMap<>();
    map.put(1, todo1);
    TodoList copy = new TodoList(map, logger);
    assertTrue(list.equals(copy));
    Todo todo2 = new Todo.Builder("code walk").build();
    map.put(2, todo2);
    TodoList diffMap = new TodoList(map, logger);
    assertFalse(list.equals(diffMap));
    ErrorLogger logger2 = new ErrorLogger();
    logger2.addError("aaa");
    TodoList diffLogger = new TodoList(map, logger2);
    assertFalse(diffLogger.equals(diffMap));
  }

  @Test
  public void testHashCode() {
    Map<Integer, Todo> map = new HashMap<>();
    map.put(1, todo1);
    TodoList copy = new TodoList(map, logger);
    assertTrue(list.hashCode()==copy.hashCode());
  }

  @Test
  public void testToString() {
    String expected = "TodoList{todoList={1=text:'finish hw8' completed:'false' dueDate:'null' "+
        "priority:'null' category:'null'}, "+
        "errorLogger=ErrorLoggerDecorator{logger=ErrorLogger{errors=[]}}}";
    assertEquals(expected, list.toString());
  }
}