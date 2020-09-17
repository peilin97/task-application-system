package problem1.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TodoSystemTest {

  TodoSystem system;

  @Before
  public void setUp() throws Exception {
    system = TodoSystem.startTodoSystem();
  }

  @Test
  public void testEquals() {
    assertTrue(system.equals(system));
    assertFalse(system.equals("aa"));
    TodoSystem copy = TodoSystem.startTodoSystem();
    assertTrue(system.equals(copy));
  }

  @Test
  public void testHashCode() {
    TodoSystem copy = TodoSystem.startTodoSystem();
    assertTrue(system.equals(copy));
  }

  @Test
  public void testToString() {
    String expected = "TodoSystem{options=Options{options=[Option{name='--csv-file', depends=[]," +
        " forbids=[], required=true, requiresValue=true, allowsMultiple=false, usage='--csv-file" +
        " <path/to/file> The CSV file containing the todos. This option is required.'}," +
        " Option{name='--add-todo', depends=[--todo-text], forbids=[], required=false, " +
        "requiresValue=false, allowsMultiple=false, usage='--add-todo Add a new todo. " +
        "If this option is provided, then --todo-text must also be provided.'}, " +
        "Option{name='--todo-text', depends=[--add-todo], forbids=[], required=false, " +
        "requiresValue=true, allowsMultiple=false, usage='--todo-text <description of todo> " +
        "A description of the todo.'}, Option{name='--completed', depends=[--add-todo], " +
        "forbids=[], required=false, requiresValue=false, allowsMultiple=false, " +
        "usage='--completed (Optional) Sets the completed status of a new todo to true.'}, " +
        "Option{name='--due', depends=[--add-todo], forbids=[], required=false, "+
        "requiresValue=true, allowsMultiple=false, usage='--due <due date> (Optional) Sets the "+
        "due date of a new todo.'}, Option{name='--priority', depends=[--add-todo], forbids=[], "+
        "required=false, requiresValue=true, allowsMultiple=false, usage='--priority <1, 2, or 3> "+
        "(Optional) Sets the priority of a new todo. The value can be 1, 2, or 3.'}, "+
        "Option{name='--category', depends=[--add-todo], forbids=[], required=false, requiresValue=true, allowsMultiple=false, usage='--category <a category name> (Optional) Sets the category of a new todo. The value can be any String. Categories do not need to be pre-defined.'}, Option{name='--complete-todo', depends=[], forbids=[], required=false, requiresValue=true, allowsMultiple=true, usage='--complete-todo <id> Mark the Todo with the provided ID as complete.'}, Option{name='--display', depends=[], forbids=[], required=false, requiresValue=false, allowsMultiple=false, usage='--display Display todos. If none of the following optional arguments are provided, displays all todos.'}, Option{name='--show-category', depends=[--display], forbids=[], required=false, requiresValue=true, allowsMultiple=false, usage='--show-category <category> (Optional) If --display is provided, only todos with the given category should be displayed.'}, Option{name='--show-incomplete', depends=[--display], forbids=[], required=false, requiresValue=false, allowsMultiple=false, usage='--show-incomplete (Optional) If --display is provided, only incomplete todos should be displayed.'}, Option{name='--sort-by-date', depends=[--display], forbids=[--sort-by-priority], required=false, requiresValue=false, allowsMultiple=false, usage='--sort-by-date (Optional) If --display is provided, sort the list of todos by date order (ascending). Cannot be combined with --sort-by-priority.'}, Option{name='--sort-by-priority', depends=[--display], forbids=[--sort-by-date], required=false, requiresValue=false, allowsMultiple=false, usage='--sort-by-priority (Optional) If --display is provided, sort the list of todos by priority (ascending). Cannot be combined with --sort-by-date.'}]}}";
    assertEquals(expected, system.toString());
  }
}