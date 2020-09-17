package problem1.View;

import problem1.Model.Option;
import problem1.Model.Options;

/**
 * This class represents a UsageViewHelper
 */
public class UsageViewHelper {

  /**
   * returns the options usage
   *
   * @param options - a given options
   * @return the options usage
   */
  public static String getOptionsUsage(Options options) {
    StringBuilder usage = new StringBuilder();
    usage.append("Usage:");
    usage.append(System.lineSeparator());
    for (Option option : options.getOptions()) {
      usage.append(option.getUsage());
      usage.append(System.lineSeparator());
    }
    return usage.toString();
  }

}
