package problem1.Model;

public interface IErrorLogger {

  /**
   * returns the error
   *
   * @return the error
   */
  String getError();

  /**
   * Adds an error
   *
   * @param error - a given String of error
   */
  void addError(String error);

  /**
   * Checks whether there is at least an error, true if there is, false otherwise
   *
   * @return whether there is at least an error, true if there is, false otherwise
   */
  boolean hasError();

}
