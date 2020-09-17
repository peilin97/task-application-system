package problem1.Model;

import java.util.Arrays;

/**
 * This class represents Options
 */
public class Options {

  private Option[] options;

  /**
   * Constructs Options with given array of options
   *
   * @param options - a given array of options
   */
  public Options(Option[] options) {
    this.options = options;
  }

  /**
   * returns whether contains a given option
   *
   * @param optionName - a given option name
   * @return whether contains a given option
   */
  public boolean contains(String optionName) {
    for (Option o : options) {
      if (o.getName().equals(optionName)) {
        return true;
      }
    }
    return false;
  }

  /**
   * returns the option according to the option name
   *
   * @param optionName - the option name
   * @return the option according to the option name
   */
  public Option getOption(String optionName) {
    for (Option o : options) {
      if (o.getName().equals(optionName)) {
        return o;
      }
    }
    return null;
  }

  /**
   * returns the array of options
   *
   * @return the array of options
   */
  public Option[] getOptions() {
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
    Options options1 = (Options) o;
    return Arrays.equals(getOptions(), options1.getOptions());
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(getOptions());
  }

  @Override
  public String toString() {
    return "Options{" +
        "options=" + Arrays.toString(options) +
        '}';
  }
}
