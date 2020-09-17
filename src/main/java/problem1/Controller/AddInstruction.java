package problem1.Controller;

import java.util.Objects;
import problem1.Model.Todo;
import problem1.Model.TodoList;

/**
 * This class represents add "to do" instruction
 */
public class AddInstruction implements IInstruction {

  private Todo todo;

  /**
   * Constructs an AddInstruction with given "to do"
   *
   * @param todo - a given "to do"
   */
  public AddInstruction(Todo todo) {
    this.todo = todo;
  }

  /**
   * returns the todo
   *
   * @return the todo
   */
  public Todo getTodo() {
    return todo;
  }

  @Override
  public void takeAction(TodoList list) {
    list.addTodo(todo);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddInstruction that = (AddInstruction) o;
    return Objects.equals(todo, that.todo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(todo);
  }

  @Override
  public String toString() {
    return "AddInstruction{" +
        "todo=" + todo +
        '}';
  }
}
