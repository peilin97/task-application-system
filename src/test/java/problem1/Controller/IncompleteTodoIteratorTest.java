package problem1.Controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import problem1.Model.ErrorLogger;
import problem1.Model.ErrorLoggerDecorator;
import problem1.Model.Todo;
import problem1.Model.Todo.Builder;
import problem1.Model.TodoList;

public class IncompleteTodoIteratorTest {
  IncompleteTodoIterator iIter1, iIter2;
  Map<Integer, Todo> m;
  Todo t1;
  @Before
  public void setUp() throws Exception {
    t1 = new Builder("a").setCompleted("true").setPriority("2").setCategory("work").build();
    m = new HashMap<>();
    m.put(1, t1);
    iIter1 = new IncompleteTodoIterator(m);
  }

  @Test
  public void testEquals() {
    assertEquals(iIter1, iIter1);
    String thing = "";
    assertNotEquals(iIter1, thing);
    iIter2 = new IncompleteTodoIterator(null);
    assertNotEquals(iIter1, iIter2);
    iIter2 = new IncompleteTodoIterator(m);
    assertEquals(iIter1, iIter2);
  }

  @Test
  public void testHashCode() {
    iIter2 = new IncompleteTodoIterator(m);
    assertEquals(iIter1.hashCode(), iIter2.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("IncompleteTodoIterator{todoList={1=text:'a' completed:'true' dueDate:'null' priority:'2' category:'work'}, ind=1}", iIter1.toString());
  }
}