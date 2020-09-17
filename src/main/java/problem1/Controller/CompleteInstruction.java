package problem1.Controller;

import java.util.Objects;
import problem1.Model.TodoList;

/**
 * This class represents a Complete "to do" instruction
 */
public class CompleteInstruction implements IInstruction {

  private Integer id;

  /**
   * Constructs a CompleteInstruction with a given id
   *
   * @param id - a given id
   */
  public CompleteInstruction(Integer id) {
    this.id = id;
  }

  /**
   * returns the id
   *
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  @Override
  public void takeAction(TodoList list) {
    list.completeTodo(this.id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompleteInstruction that = (CompleteInstruction) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "CompleteInstruction{" +
        "id=" + id +
        '}';
  }
}
