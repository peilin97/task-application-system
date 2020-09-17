package problem1.Controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import problem1.Model.Todo;

/**
 * This class represents a Category Iterator
 */
public class CategoryIterator implements Iterator<Todo> {

  private Map<Integer, Todo> todoList;
  private String category;
  private int ind;

  /**
   * Constructs a CategoryIterator with a given todoList and category
   *
   * @param todoList - a given todoList
   * @param category - a given category
   */
  public CategoryIterator(Map<Integer, Todo> todoList, String category) {
    this.todoList = todoList;
    this.category = category;
    this.ind = 1;
  }

  /**
   * Returns {@code true} if the iteration has more elements. (In other words, returns {@code true}
   * if {@link #next} would return an element rather than throwing an exception.)
   *
   * @return {@code true} if the iteration has more elements
   */
  @Override
  public boolean hasNext() {
    return this.ind <= Collections.max(this.todoList.keySet());
  }

  public int getInd() {
    return ind;
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
    while (this.hasNext() && !this.todoList.containsKey(ind)) {
      ind++;
    }
    Todo curr = this.todoList.get(this.ind++);
    while (!this.todoList.containsKey(ind - 1) || curr.getCategory() == null || !curr.getCategory()
        .equals(this.category)) {
      if (!this.hasNext()) {
        return null;
      }
      curr = this.todoList.get(this.ind++);
    }
    return curr;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CategoryIterator that = (CategoryIterator) o;
    return getInd() == that.getInd() &&
        Objects.equals(todoList, that.todoList) &&
        Objects.equals(category, that.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(todoList, category, getInd());
  }

  @Override
  public String toString() {
    return "CategoryIterator{" +
        "todoList=" + todoList +
        ", category='" + category + '\'' +
        ", ind=" + ind +
        '}';
  }
}
