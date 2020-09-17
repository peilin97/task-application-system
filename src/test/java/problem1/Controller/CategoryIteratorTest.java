package problem1.Controller;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import problem1.Model.Todo;
import problem1.Model.Todo.Builder;

public class CategoryIteratorTest {
  CategoryIterator cIter1, cIter2;
  Map<Integer, Todo> m;
  Todo t1;
  @Before
  public void setUp() throws Exception {
    t1 = new Builder("a").setCompleted("true").setPriority("2").setCategory("work").build();
    m = new HashMap<>();
    m.put(1, t1);
    cIter1 = new CategoryIterator(m, "w");
  }

  @Test
  public void testEquals() {
    assertEquals(cIter1, cIter1);
    String thing = "";
    assertNotEquals(cIter1, thing);
    cIter2 = new CategoryIterator(null, "w");
    assertNotEquals(cIter1, cIter2);
    cIter2 = new CategoryIterator(m, "w");
    assertEquals(cIter1, cIter2);
  }

  @Test
  public void testHashCode() {
    cIter2 = new CategoryIterator(m, "w");
    assertEquals(cIter1.hashCode(), cIter2.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("CategoryIterator{todoList={1=text:'a' completed:'true' dueDate:'null' priority:'2' category:'work'}, category='w', ind=1}", cIter1.toString());
  }
}