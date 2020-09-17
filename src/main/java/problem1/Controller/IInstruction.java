package problem1.Controller;

import problem1.Model.TodoList;

/**
 * This class represents instructions to the todoList
 */
public interface IInstruction {

  /**
   * Change the given list according to the Instruction
   *
   * @param list - a given todoList
   */
  void takeAction(TodoList list);

}
