package problem1.Controller;

import java.util.List;
import java.util.Objects;
import problem1.Model.TodoList;

/**
 * This class represents a Display todos Instruction
 */
public class DisplayInstruction implements IInstruction {

  private List<String> commands;

  /**
   * Constructs a DisplayInstruction with given list of commands
   *
   * @param commands - a given list of commands
   */
  public DisplayInstruction(List<String> commands) {
    this.commands = commands;
  }

  /**
   * returns the commands
   *
   * @return the commands
   */
  public List<String> getCommands() {
    return commands;
  }

  @Override
  public void takeAction(TodoList list) {
    list.displayTodo(this.commands);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DisplayInstruction that = (DisplayInstruction) o;
    return Objects.equals(commands, that.commands);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commands);
  }

  @Override
  public String toString() {
    return "DisplayInstruction{" +
        "commands=" + commands +
        '}';
  }
}
