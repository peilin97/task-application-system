package problem1.Model;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import problem1.Controller.DisplayProcessor;
import problem1.View.TodoViewHelper;

/**
 * Represents a list of todos.
 */
public class TodoList {

  private Map<Integer, Todo> todoList;
  private ErrorLoggerDecorator errorLogger;

  /**
   * Constructs a todoList object with a collection of todos and the error logger
   * @param todoList a collection of todos; a map, the key is the id of the todo, the value is the todo.
   * @param logger an error logger.
   */
  public TodoList(Map<Integer, Todo> todoList, ErrorLogger logger) {
    this.todoList = todoList;
    this.errorLogger = new ErrorLoggerDecorator(logger);
  }

  /**
   * Gets the todo list.
   * @return the todo list.
   */
  public Map<Integer, Todo> getTodoList() {
    return todoList;
  }

  /**
   * Gets this todoList's error logger decorator.
   * @return this todoList's error logger decorator.
   */
  public ErrorLoggerDecorator getErrorLogger() {
    return errorLogger;
  }

  /**
   * Add a new todo to this list.
   * @param todo a Todo object.
   */
  public void addTodo(Todo todo) {
    Integer newID = this.generateID();
    this.todoList.put(newID, todo);
  }

  /**
   * Helper method which generates the id of the new todo.
   * @return the id of the new todo.
   */
  private Integer generateID() {
    return this.todoList.size() + 1;
  }

  /**
   * Complete the todo by giving ID.
   * @param id the ID of the todo to be completed.
   */
  public void completeTodo(Integer id) {
    if(this.todoList.containsKey(id)) {
      Todo todo = this.todoList.get(id);
      if (!todo.getCompleted()) {
        todo.setCompleted(true);
      } else {
        // Is it better to move this part to the todo.setCompleted() method?
        this.errorLogger.doubleCompleted(id);
      }
    } else {
      this.errorLogger.invalidID(id);
    }
  }

  /**
   * Display the todoList to the screen
   * @param commands commands given by the user
   */
  public void displayTodo(List<String> commands) {
    DisplayProcessor processor = new DisplayProcessor(commands, errorLogger);
    List<Todo> todoList = processor.finalProcess(this);
    if (errorLogger.hasError()) {
      return;
    }
    TodoViewHelper.printTodoList(todoList);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TodoList todoList1 = (TodoList) o;
    return Objects.equals(getTodoList(), todoList1.getTodoList()) &&
        Objects.equals(getErrorLogger(), todoList1.getErrorLogger());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTodoList(), getErrorLogger());
  }

  @Override
  public String toString() {
    return "TodoList{" +
        "todoList=" + todoList +
        ", errorLogger=" + errorLogger +
        '}';
  }
}
