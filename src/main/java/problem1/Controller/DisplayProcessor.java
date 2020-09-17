package problem1.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Objects;
import problem1.Controller.Filter.Builder;
import problem1.Model.ErrorLoggerDecorator;
import problem1.Model.Todo;
import problem1.Model.TodoList;

/**
 * This class represents a display processor
 */
public class DisplayProcessor {

  private static final String FILTER_INCOMPLETE = "--show-incomplete";
  private static final String FILTER_CATEGORY = "--show-category";
  private static final String SORT_DATE = "--sort-by-date";
  private static final String SORT_PRIORITY = "--sort-by-priority";

  private List<String> command;
  private Builder builder;
  private ErrorLoggerDecorator logger;

  /**
   * Constructs a DisplayProcessor with given command and ErrorLoggerDecorator
   *
   * @param command - given commands
   * @param logger  - a given ErrorLoggerDecorator
   */
  public DisplayProcessor(List<String> command, ErrorLoggerDecorator logger) {
    this.command = command;
    this.builder = new Builder();
    this.logger = logger;
  }

  /**
   * returns the command
   *
   * @return the command
   */
  public List<String> getCommand() {
    return command;
  }

  /**
   * returns the builder
   *
   * @return the builder
   */
  public Builder getBuilder() {
    return builder;
  }

  /**
   * returns the error logger
   *
   * @return the error logger
   */
  public ErrorLoggerDecorator getLogger() {
    return logger;
  }

  /**
   * checks the filter
   */
  private void checkFilter() {
    if (command.contains(FILTER_INCOMPLETE)) {
      this.builder.setFilterByIncomplete();
    }
    int categoryInd = command.indexOf(FILTER_CATEGORY);
    if (categoryInd >= 0) {
      this.builder.setFilterByCategory(command.get(categoryInd + 1));
    }
  }

  /**
   * Filter the map of todos
   *
   * @param f   - a given filter
   * @param raw - a given map of todos
   * @return the map of todos after filtering
   */
  private HashMap<Integer, Todo> filter(Filter f, Map<Integer, Todo> raw) {
    HashMap<Integer, Todo> filterOutput = new HashMap<Integer, Todo>();
    if (f.isFilterByIncomplete()) {
      IncompleteTodoIterator iIter = new IncompleteTodoIterator(raw);
      while (iIter.hasNext()) {
        Todo curr = iIter.next();
        if (curr != null) {
          filterOutput.put(iIter.getInd() - 1, curr);
        }
      }
    }
    if (f.getFilterByCategory() != null) {
      CategoryIterator cIter = new CategoryIterator(filterOutput.isEmpty() ? raw : filterOutput,
          f.getFilterByCategory());
      HashMap<Integer, Todo> tmp = new HashMap<Integer, Todo>();
      while (cIter.hasNext()) {
        Todo curr = cIter.next();
        if (curr != null) {
          tmp.put(cIter.getInd() - 1, curr);
        }
      }
      filterOutput = tmp;
    }
    return filterOutput;
  }

  /**
   * Sort the given map of todos
   *
   * @param hm - the given map of todos
   * @return the sorted given map of todos
   */
  private List<Todo> sort(Map<Integer, Todo> hm) {
    List<Todo> sortOutput = new ArrayList<>(hm.values());
    if (command.contains(SORT_DATE)) {
      sortOutput.sort(new DateComparator());
    } else if (command.contains(SORT_PRIORITY)) {
      sortOutput.sort(new PriorityComparator());
    }
    return sortOutput;
  }

  /**
   * Check the filter and returns the sorted todoList
   *
   * @param todoList - a given todoList
   * @return the sorted todoList
   */
  public List<Todo> finalProcess(TodoList todoList) {
    this.checkFilter();
    HashMap<Integer, Todo> fr = this.filter(this.builder.build(), todoList.getTodoList());
    if (fr.isEmpty() && command.contains(FILTER_CATEGORY)) {
      logger.categoryNotFound();
    }
    return sort(fr.isEmpty() ? todoList.getTodoList() : fr);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DisplayProcessor processor = (DisplayProcessor) o;
    return Objects.equals(getCommand(), processor.getCommand()) &&
        Objects.equals(getLogger(), processor.getLogger());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCommand(), getLogger());
  }

  @Override
  public String toString() {
    return "DisplayProcessor{" +
        "command=" + command +
        ", logger=" + logger +
        '}';
  }
}
