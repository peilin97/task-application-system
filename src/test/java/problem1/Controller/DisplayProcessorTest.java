package problem1.Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import problem1.Model.ErrorLogger;
import problem1.Model.ErrorLoggerDecorator;
import problem1.Model.Todo;
import problem1.Model.Todo.Builder;
import problem1.Model.TodoList;

public class DisplayProcessorTest {
  DisplayProcessor ip1, ip2, ip3, ip4, ip5, ip6, ip7, ip8;
  List<String> cmd1, cmd2, cmd3, cmd4, cmd5, cmd6, cmd7, cmd8;
  TodoList todoList;
  Todo t1, t2, t3, t4;
  ErrorLoggerDecorator logger;
  ErrorLogger l;
  @Before
  public void setUp() throws Exception {
    cmd1 = new ArrayList<>(Arrays.asList("--sort-by-priority"));
    cmd2 = new ArrayList<>(Arrays.asList("--sort-by-priority", "--sort-by-date"));
    cmd3 = new ArrayList<>(Arrays.asList("--sort-by-date"));
    cmd4 = new ArrayList<>(Arrays.asList("--show-incomplete"));
    cmd5 = new ArrayList<>(Arrays.asList("--show-incomplete", "--show-category", "work"));
    cmd6 = new ArrayList<>(Arrays.asList("--show-category", "c1", "--show-category", "c2"));
    cmd7 = new ArrayList<>(Arrays.asList("--show-incomplete", "--show-category"));
    cmd8 = new ArrayList<>(Arrays.asList("--sort-by-date", "--show-category", "work"));
    Map<Integer, Todo> hm = new HashMap<>();
    t1 = new Builder("a").setCompleted("true").setPriority("2").setCategory("work").build();
    t2 = new Builder("b").addDueDate("2020-04-01").setCategory("work").build();
    t3 = new Builder("c").setCompleted("true").setPriority("1").build();
    t4 = new Builder("d").addDueDate("2019-09-05").setCategory("home").build();
    hm.put(1, t1);
    hm.put(2, t2);
    hm.put(3, t3);
    hm.put(4, t4);
    l = new ErrorLogger();
    logger = new ErrorLoggerDecorator(l);
    todoList = new TodoList(hm, l);
    ip1 = new DisplayProcessor(cmd1, logger);
    ip3 = new DisplayProcessor(cmd3, logger);
    ip4 = new DisplayProcessor(cmd4, logger);
    ip5 = new DisplayProcessor(cmd5, logger);
    ip8 = new DisplayProcessor(cmd8, logger);
  }

  @Test
  public void sortPriority() {
    assertEquals(t3, ip1.finalProcess(todoList).get(0));
    assertEquals(t1, ip1.finalProcess(todoList).get(1));
    assertEquals(t2, ip1.finalProcess(todoList).get(2));
    assertEquals(t4, ip1.finalProcess(todoList).get(3));
    ip2 = new DisplayProcessor(cmd2, logger);
  }

  @Test
  public void sortDate(){
    assertEquals(t4, ip3.finalProcess(todoList).get(0));
    assertEquals(t2, ip3.finalProcess(todoList).get(1));
    assertEquals(t1, ip3.finalProcess(todoList).get(2));
    assertEquals(t3, ip3.finalProcess(todoList).get(3));
  }

  @Test
  public void filterIncomplete() {
    assertEquals(t2, ip4.finalProcess(todoList).get(0));
    assertEquals(t4, ip4.finalProcess(todoList).get(1));
    assertEquals(2, ip4.finalProcess(todoList).size());
  }

  @Test
  public void filterCategoryAndIncomplete() {
    assertEquals(t2, ip5.finalProcess(todoList).get(0));
    assertEquals(1, ip5.finalProcess(todoList).size());
    ip6 = new DisplayProcessor(cmd6, logger);
  }

  @Test
  public void filterAndSort() {
    assertEquals(t2, ip8.finalProcess(todoList).get(0));
    assertEquals(t1, ip8.finalProcess(todoList).get(1));
    assertEquals(2, ip8.finalProcess(todoList).size());
  }

  @Test
  public void testEquals() {
    assertEquals(ip1, ip1);
    DisplayProcessor ip9 = null;
    assertNotEquals(ip1, ip9);
    assertNotEquals(ip1, ip3);
    String ip10 = "";
    assertNotEquals(ip1, ip10);
    ip9 = new DisplayProcessor(cmd1, null);
    assertNotEquals(ip1, ip9);
  }

  @Test
  public void testHashCode() {
    ip2 = new DisplayProcessor(cmd1, logger);
    assertEquals(ip1.hashCode(), ip2.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("DisplayProcessor{command=[--sort-by-priority], logger=ErrorLoggerDecorator{logger=ErrorLogger{errors=[]}}}", ip1.toString());
  }
}