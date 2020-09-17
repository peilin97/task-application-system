package problem1.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents an ErrorLogger
 */
public class ErrorLogger implements IErrorLogger {

  private List<String> errors;

  /**
   * Constructs an ErrorLogger
   */
  public ErrorLogger() {
    this.errors = new ArrayList<>();
  }

  @Override
  public void addError(String error) {
    this.errors.add(error);
  }

  @Override
  public String getError() {
    StringBuilder result = new StringBuilder("Errors: ");
    for (String error : this.errors) {
      result.append(error);
      result.append(System.lineSeparator());
    }
    return result.toString();
  }

  @Override
  public boolean hasError() {
    return this.errors.size() != 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorLogger that = (ErrorLogger) o;
    return Objects.equals(errors, that.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errors);
  }

  @Override
  public String toString() {
    return "ErrorLogger{" +
        "errors=" + errors +
        '}';
  }
}
