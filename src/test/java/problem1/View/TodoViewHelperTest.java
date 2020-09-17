package problem1.View;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import problem1.Model.Todo;
import problem1.Model.Todo.Builder;

public class TodoViewHelperTest {
  private List<Todo> todoList;

  @Test
  public void printTodoList() {
    todoList = new ArrayList<>();
    Todo todo1 = new Builder("text1").build();
    Todo todo2 = new Builder("text2").build();
    todoList.add(todo1);
    todoList.add(todo2);
    TodoViewHelper.printTodoList(todoList);
  }
}