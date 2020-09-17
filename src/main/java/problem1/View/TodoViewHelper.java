package problem1.View;

import java.util.List;
import problem1.Model.Todo;

/**
 * This class represents a ToDoView, printing the todos
 */
public class TodoViewHelper {

  /**
   * prints the todos in the given todoList
   *
   * @param todoList - a given list of todo
   */
  public static void printTodoList(List<Todo> todoList) {
    for (Todo t : todoList) {
      System.out.println(t.toString());
    }
  }
}
