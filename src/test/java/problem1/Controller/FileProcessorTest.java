package problem1.Controller;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import problem1.Model.Todo;
import problem1.Model.Todo.Builder;

public class FileProcessorTest {

  @Test
  public void getFileToMap() {
    Map<Integer, Todo> map = FileProcessor.getFileToMap("todos.csv");
    Todo todo1 = new Builder("Finish HW9").setCompleted("false").addDueDate("2020-03-22").setPriority("1").setCategory("school").build();
    assertEquals(map.get(1), todo1);
  }

  @Test
  public void getFileToMap2() {
    FileProcessor.getFileToMap("todos2.csv");
  }
}