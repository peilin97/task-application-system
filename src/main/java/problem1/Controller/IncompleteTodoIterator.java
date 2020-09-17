package problem1.Controller;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import problem1.Model.Todo;

/**
 * This class represents IncompleteTodoIterator
 */
public class IncompleteTodoIterator implements Iterator<Todo> {

  private Map<Integer, Todo> todoList;
  private int ind = 1;

  /**
   * Constructs an IncompleteTodoIterator with given todoList
   *
   * @param todoList - a given map of todo list
   */
  public IncompleteTodoIterator(Map<Integer, Todo> todoList) {
    this.todoList = todoList;
  }

  /**
   * Returns {@code true} if the iteration has more elements. (In other words, returns {@code true}
   * if {@link #next} would return an element rather than throwing an exception.)
   *
   * @return {@code true} if the iteration has more elements
   */
  @Override
  public boolean hasNext() {
    return this.ind <= todoList.size();
  }

  /**
   * Returns the next element in the iteration.
   *
   * @return the next element in the iteration
   * @throws NoSuchElementException if the iteration has no more elements
   */
  @Override
  public Todo next() {
    if (!this.hasNext()) {
      throw new NoSuchElementException();
    }
    Todo curr = this.todoList.get(this.ind++);
    while (curr != null && curr.getCompleted()) {
      curr = this.todoList.get(this.ind++);
    }
    return curr;
  }

  /**
   * returns the index;
   *
   * @return the index;
   */
  public int getInd() {
    return ind;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IncompleteTodoIterator that = (IncompleteTodoIterator) o;
    return getInd() == that.getInd() &&
        Objects.equals(todoList, that.todoList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(todoList, getInd());
  }

  @Override
  public String toString() {
    return "IncompleteTodoIterator{" +
        "todoList=" + todoList +
        ", ind=" + ind +
        '}';
  }
}
