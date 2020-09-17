package problem1.Model;

import java.util.Objects;

/**
 * This class represents a TodoSystem
 */
public class TodoSystem {

  private static Options options;

  private static TodoSystem todoSystem;
  private static final String CSV_FILE = "--csv-file";
  private static final String ADD_TODO = "--add-todo";
  private static final String TODO_TEXT = "--todo-text";
  private static final String COMPLETED = "--completed";
  private static final String DUE = "--due";
  private static final String PRIORITY = "--priority";
  private static final String CATEGORY = "--category";
  private static final String COMPLETE_TODO = "--complete-todo";
  private static final String DISPLAY = "--display";
  private static final String SHOW_INCOMPLETE = "--show-incomplete";
  private static final String SHOW_CATEGORY = "--show-category";
  private static final String SORT_DATE = "--sort-by-date";
  private static final String SORT_PRIORITY = "--sort-by-priority";

  /**
   * Constructs a TodoSystem
   */
  private TodoSystem() {
    this.options = this.optionsSetUp();
  }

  /**
   * StartTodoSystem
   *
   * @return a TodoSystem instance
   */
  public static TodoSystem startTodoSystem() {
    if (todoSystem == null) {
      todoSystem = new TodoSystem();
    }
    return todoSystem;
  }

  /**
   * Set up the options
   *
   * @return the options
   */
  private Options optionsSetUp() {
    Option csvFile = new Option.Builder(CSV_FILE).
        setRequiresValue().
        setRequired().
        setUsage(
            "--csv-file <path/to/file> The CSV file containing the todos. This option is required.")
        .
            build();
    Option addTodo = new Option.Builder(ADD_TODO).
        addDepends(new String[]{TODO_TEXT}).
        setUsage(
            "--add-todo Add a new todo. If this option is provided, then --todo-text must also be provided.")
        .
            build();
    Option todoText = new Option.Builder(TODO_TEXT).
        addDepends(new String[]{ADD_TODO}).
        setRequiresValue().
        setUsage("--todo-text <description of todo> A description of the todo.").
        build();
    Option completed = new Option.Builder(COMPLETED).
        addDepends(new String[]{ADD_TODO}).
        setUsage("--completed (Optional) Sets the completed status of a new todo to true.").
        build();
    Option due = new Option.Builder(DUE).
        addDepends(new String[]{ADD_TODO}).
        setRequiresValue().
        setUsage("--due <due date> (Optional) Sets the due date of a new todo.").
        build();
    Option priority = new Option.Builder(PRIORITY).
        addDepends(new String[]{ADD_TODO}).
        setRequiresValue().
        setUsage(
            "--priority <1, 2, or 3> (Optional) Sets the priority of a new todo. The value can be 1, 2, or 3.")
        .
            build();
    Option category = new Option.Builder(CATEGORY).
        addDepends(new String[]{ADD_TODO}).
        setRequiresValue().
        setUsage(
            "--category <a category name> (Optional) Sets the category of a new todo. The value can be any String. Categories do not need to be pre-defined.")
        .
            build();
    Option completeTodo = new Option.Builder(COMPLETE_TODO).
        setRequiresValue().
        setAllowsMultiple().
        setUsage("--complete-todo <id> Mark the Todo with the provided ID as complete.").
        build();
    Option display = new Option.Builder(DISPLAY).
        setUsage(
            "--display Display todos. If none of the following optional arguments are provided, displays all todos.")
        .
            build();
    Option showIncomplete = new Option.Builder(SHOW_INCOMPLETE).
        addDepends(new String[]{DISPLAY}).
        setUsage(
            "--show-incomplete (Optional) If --display is provided, only incomplete todos should be displayed.")
        .
            build();
    Option showCategory = new Option.Builder(SHOW_CATEGORY).
        addDepends(new String[]{DISPLAY}).
        setRequiresValue().
        setUsage(
            "--show-category <category> (Optional) If --display is provided, only todos with the given category should be displayed.")
        .
            build();
    Option sortDate = new Option.Builder(SORT_DATE).
        addDepends(new String[]{DISPLAY}).
        addForbids(new String[]{SORT_PRIORITY}).
        setUsage(
            "--sort-by-date (Optional) If --display is provided, sort the list of todos by date order (ascending). Cannot be combined with --sort-by-priority.")
        .
            build();
    Option sortPriority = new Option.Builder(SORT_PRIORITY).
        addDepends(new String[]{DISPLAY}).
        addForbids(new String[]{SORT_DATE}).
        setUsage(
            "--sort-by-priority (Optional) If --display is provided, sort the list of todos by priority (ascending). Cannot be combined with --sort-by-date.")
        .
            build();
    Option[] optionArray = {csvFile, addTodo, todoText, completed, due, priority, category,
        completeTodo, display, showCategory, showIncomplete, sortDate, sortPriority};
    return new Options(optionArray);
  }

  /**
   * returns the options
   *
   * @return the options
   */
  public Options getOptions() {
    return options;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TodoSystem that = (TodoSystem) o;
    return Objects.equals(getOptions(), that.getOptions());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getOptions());
  }

  @Override
  public String toString() {
    return "TodoSystem{" +
        "options=" + options +
        '}';
  }
}
