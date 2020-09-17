package problem1.Model;

import java.util.Objects;

/**
 * This class represents a to-do task, including a text, a completed flag, a due date. a priority
 * and a category
 */

public class Todo {

  private String text;  // A required field
  private Boolean completed;  // If not specified, this field should be false by default;
  private String dueDate;
  private Integer priority;
  private String category;

  /**
   * Constructs a to-do instance with a given builder
   *
   * @param builder - a given builder
   */
  public Todo(Builder builder) {
    this.text = builder.text;
    this.completed = builder.completed;
    this.dueDate = builder.dueDate;
    this.priority = builder.priority;
    this.category = builder.category;
  }

  /**
   * returns the text
   *
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * return the completed flag
   *
   * @return the completed flag
   */
  public Boolean getCompleted() {
    return completed;
  }

  /**
   * return the due
   *
   * @return the due
   */
  public String getDueDate() {
    return dueDate;
  }

  /**
   * returns the priority
   *
   * @return the priority
   */
  public Integer getPriority() {
    return priority;
  }

  /**
   * returns the category
   *
   * @return the category
   */
  public String getCategory() {
    return category;
  }

  /**
   * Sets the completed
   *
   * @param completed - a given completed
   */
  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Todo toDo = (Todo) o;
    return Objects.equals(getText(), toDo.getText()) &&
        Objects.equals(getCompleted(), toDo.getCompleted()) &&
        Objects.equals(getDueDate(), toDo.getDueDate()) &&
        Objects.equals(getPriority(), toDo.getPriority()) &&
        Objects.equals(getCategory(), toDo.getCategory());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getText(), getCompleted(), getDueDate(), getPriority(), getCategory());
  }

  @Override
  public String toString() {
    return "text:'" + text + '\'' +
        " completed:'" + completed + '\'' +
        " dueDate:'" + dueDate + '\'' +
        " priority:'" + priority + '\'' +
        " category:'" + category + '\'';
  }

  /**
   * This class represents a to-do builder
   */
  public static class Builder {

    private String text;  // A required field
    private Boolean completed;  // If not specified, this field should be false by default;
    private String dueDate;
    private Integer priority;
    private String category;

    /**
     * Constructs a Builder with a given text
     *
     * @param text - a given text
     */
    public Builder(String text) {
      this.text = text;
      this.completed = false;
    }

    /**
     * returns whether the instruction is provided
     *
     * @param instruction - a given instruction
     * @return true if given, false otherwise
     */
    private boolean isProvided(String instruction) {
      return !instruction.equals("?");
    }

    /**
     * Set the completed flag in the builder
     *
     * @param completed - a completed flag
     * @return this builder
     */
    public Builder setCompleted(String completed) {
      if (isProvided(completed)) {
        this.completed = completed.equals("true");
      }
      return this;
    }

    /**
     * Add the due date in the builder
     *
     * @param dueDate - a given due date
     * @return this builder
     */
    public Builder addDueDate(String dueDate) {
      if (isProvided(dueDate)) {
        this.dueDate = dueDate;
      }
      return this;
    }

    /**
     * Sets the priority in the builder
     *
     * @param priority - a given priority
     * @return this builder
     */
    public Builder setPriority(String priority) {
      if (isProvided(priority)) {
        this.priority = Integer.parseInt(priority);
      }
      return this;
    }

    /**
     * Sets the category in the builder
     *
     * @param category - a given category
     * @return this builder
     */
    public Builder setCategory(String category) {
      if (isProvided(category)) {
        this.category = category;
      }
      return this;
    }

    /**
     * Build the to-do
     *
     * @return the to-do
     */
    public Todo build() {
      return new Todo(this);
    }
  }
}
