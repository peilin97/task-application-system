package problem1.Model;

import java.util.Objects;

/**
 * This class represents an ErrorLoggerDecorator
 */
public class ErrorLoggerDecorator {

  private ErrorLogger logger;

  /**
   * Constructs an ErrorLoggerDecorator with a given ErrorLogger
   *
   * @param logger - a given ErrorLogger
   */
  public ErrorLoggerDecorator(ErrorLogger logger) {
    this.logger = logger;
  }

  /**
   * returns whether there are errors
   *
   * @return whether there are errors
   */
  public boolean hasError() {
    return logger.hasError();
  }

  /**
   * Add invalid Option Error
   *
   * @param option - a given String of option
   */
  public void invalidOption(String option) {
    this.logger.addError(option + " was invalid.");
  }

  /**
   * Add option missed Error
   *
   * @param option - a given String of option
   */
  public void optionMissed(String option) {
    this.logger.addError(option + " was not provided.");
  }

  /**
   * Add param missed Error
   *
   * @param option - a given String of option
   */
  public void paramMissed(String option) {
    this.logger.addError(option + " was provided, but the parameter was not given.");
  }

  /**
   * Add depended Option missed Error
   *
   * @param providedOption    - a provided option
   * @param notProvidedOption - a not provided option
   */
  public void dependedOptionMissed(String providedOption, String notProvidedOption) {
    this.logger
        .addError(providedOption + " was provided, but " + notProvidedOption + " was not given.");
  }

  /**
   * Add incompatible Options Error
   *
   * @param option1 - a given option
   * @param option2 - a given option
   */
  public void incompatibleOptions(String option1, String option2) {
    this.logger.addError(option1 + " cannot be combined with " + option2 + ".");
  }

  /**
   * Add duplicate Options Error
   *
   * @param option - a given option
   */
  public void duplicateOptions(String option) {
    this.logger.addError(option + " was provided more than one time.");
  }

  /**
   * Add double Completed Error
   *
   * @param id - a given id
   */
  public void doubleCompleted(Integer id) {
    this.logger.addError("The todo with the ID " +
        id + " has already been completed.");
  }

  /**
   * Add invalid id Error
   *
   * @param id - a given id
   */
  public void invalidID(Integer id) {
    this.logger.addError("The todo with the ID " +
        id + " doesn't exist.");
  }

  /**
   * Add category not found Error
   */
  public void categoryNotFound() {
    this.logger.addError("Category is not found!");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorLoggerDecorator that = (ErrorLoggerDecorator) o;
    return Objects.equals(logger, that.logger);
  }

  @Override
  public int hashCode() {
    return Objects.hash(logger);
  }

  @Override
  public String toString() {
    return "ErrorLoggerDecorator{" +
        "logger=" + logger +
        '}';
  }
}
