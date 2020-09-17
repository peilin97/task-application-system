package problem1.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import problem1.Model.Todo.Builder;

public class TodoTest {
  private Todo todo;

  @Before
  public void setUp() throws Exception {
    todo = new Builder("text").build();
  }

  @Test
  public void getText() {
    assertEquals("text", todo.getText());
  }

  @Test
  public void getDueDate() {
    todo = new Builder("text").addDueDate("?").build();
    assertNull(todo.getDueDate());
  }

  @Test
  public void getPriority() {
    todo = new Builder("text").setPriority("?").build();
    assertNull(todo.getPriority());
  }

  @Test
  public void getCategory() {
    todo = new Builder("text").setCategory("?").build();
    assertNull(todo.getCategory());
  }

  @Test
  public void setCompleted() {
    todo.setCompleted(true);
    assertTrue(todo.getCompleted());
    Todo todo1 = new Builder("text").setCompleted("?").build();
    assertFalse(todo1.getCompleted());
  }

  @Test
  public void testEquals() {
    Todo nothing = null;
    int two = 2;
    assertFalse(todo.equals(nothing));
    assertFalse(todo.equals(two));
    Todo todo1 = new Builder("text").addDueDate("1/3/2020").build();
    assertFalse(todo.equals(todo1));
    Todo todo2 = new Builder("text2").build();
    assertFalse(todo.equals(todo2));
    Todo todo3 = new Builder("text").setCompleted("true").build();
    assertFalse(todo.equals(todo3));
    Todo todo4 = new Builder("text").setCategory("food").build();
    assertFalse(todo.equals(todo4));
    Todo todo5 = new Builder("text").setPriority("2").build();
    assertFalse(todo.equals(todo5));
    assertTrue(todo.equals(todo));
    Todo todo6 = new Builder("text").build();
    assertTrue(todo.equals(todo6));
  }

  @Test
  public void testHashCode() {
     assertEquals(todo.hashCode(), todo.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("text:'text' completed:'false' dueDate:'null' priority:'null' category:'null'", todo.toString());
  }
}