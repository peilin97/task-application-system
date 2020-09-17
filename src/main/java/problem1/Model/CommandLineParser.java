package problem1.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This class represents a CommandLineParser
 */
public class CommandLineParser {

  private String[] args;
  private Options options;
  private Map<String, List<String>> result;
  private ErrorLoggerDecorator errorLogger;

  /**
   * Constructs a CommandLineParser with given args, options and logger
   *
   * @param args    - a given args
   * @param options - given options
   * @param logger  - a given logger
   */
  public CommandLineParser(String[] args, Options options, ErrorLogger logger) {
    this.args = args;
    this.options = options;
    this.result = new HashMap<>();
    this.errorLogger = new ErrorLoggerDecorator(logger);
    this.parse();
  }

  /**
   * parse the arguments
   */
  private void parse() {
    if (this.processArgs()) {
      this.checkRequiredOptions();
    }
  }

  /**
   * process args, returns whether there's an error
   *
   * @return whether there's an error
   */
  private boolean processArgs() {
    for (int i = 0; i < this.args.length; i++) {
      if (this.options.contains(this.args[i])) {
        Option option = this.options.getOption(this.args[i]);
        if (!this.checkCombinedOptions(option)) {
          return false;
        }
        if (!this.checkIncompatibleOptions(option)) {
          return false;
        }
        if (!this.checkAllowsMultiple(option)) {
          return false;
        }
        if (!option.isRequiresValue()) {
          this.addOptionWithoutValue(this.args[i]);
        } else {
          if (!this.addOptionWithValue(option, i)) {
            return false;
          } else {
            i++;
          }
        }
      } else {
        this.errorLogger.invalidOption(this.args[i]);
        return false;
      }
    }
    return true;
  }

  /**
   * Adds option without value
   *
   * @param optionName -  a given option name
   */
  private void addOptionWithoutValue(String optionName) {
    if (this.result.containsKey(optionName)) {
      this.result.get(optionName).add(optionName);
    } else {
      List<String> params = new ArrayList<>();
      params.add(optionName);
      this.result.put(optionName, params);
    }
  }

  /**
   * Checks whether there are Required Options
   *
   * @return whether there are Required Options
   */
  private boolean checkRequiredOptions() {
    for (Option option : this.options.getOptions()) {
      if (option.isRequired()) {
        if (!this.argsContains(option.getName())) {
          this.errorLogger.optionMissed(option.getName());
          return false;
        }
      }
    }
    return true;
  }

  /**
   * check whether there's Incompatible Option
   *
   * @param option - a given option
   * @return whether there's Incompatible Option
   */
  private boolean checkIncompatibleOptions(Option option) {
    for (String str : option.getForbids()) {
      if (this.argsContains(str)) {
        this.errorLogger.incompatibleOptions(option.getName(), str);
        return false;
      }
    }
    return true;
  }

  /**
   * check whether there's Combined Option
   *
   * @param option - a given option
   * @return whether there's Combined Option
   */
  private boolean checkCombinedOptions(Option option) {
    for (String str : option.getDepends()) {
      if (!this.argsContains(str)) {
        this.errorLogger.dependedOptionMissed(option.getName(), str);
        return false;
      }
    }
    return true;
  }

  /**
   * checks whether allows Multiple option
   *
   * @param option - a given option
   * @return whether allows Multiple option
   */
  private boolean checkAllowsMultiple(Option option) {
    if (!option.isAllowsMultiple()) {
      if (this.result.containsKey(option.getName())) {
        this.errorLogger.duplicateOptions(option.getName());
        return false;
      }
    }
    return true;
  }

  /**
   * returns whether contains an argument
   *
   * @param argument - a given argument
   * @return whether contains an argument
   */
  private boolean argsContains(String argument) {
    for (String arg : this.args) {
      if (arg.equals(argument)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Add Option with value
   *
   * @param option - a given option
   * @param pos    - a given position
   * @return whether there's an error
   */
  private boolean addOptionWithValue(Option option, int pos) {
    if (!this.result.containsKey(option.getName())) {
      this.result.put(option.getName(), new ArrayList<>());
    }
    return this.addValue(option, pos);
  }

  /**
   * Adds the options, returns true if there's an error, false otherwise
   *
   * @param option - given option
   * @param pos    - given position
   * @return true if there's an error, false otherwise
   */
  private boolean addValue(Option option, int pos) {
    if (pos + 1 < this.args.length) {
      if (!this.options.contains(this.args[pos + 1])) {
        this.result.get(option.getName()).add(this.args[pos + 1]);
      } else {
        this.errorLogger.paramMissed(option.getName());
        return false;
      }
    } else {
      this.errorLogger.paramMissed(option.getName());
      return false;
    }
    return true;
  }

  /**
   * returns whether there's a specific option
   *
   * @param option - a given option
   * @return whether there's a specific option
   */
  public boolean hasOption(String option) {
    return this.result.containsKey(option);
  }

  /**
   * returns the option value with a given option
   *
   * @param option - a given option
   * @return the option value with a given option
   */
  public List<String> getOptionValue(String option) {
    return this.result.get(option);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommandLineParser parser = (CommandLineParser) o;
    return Arrays.equals(args, parser.args) &&
        Objects.equals(options, parser.options) &&
        Objects.equals(result, parser.result) &&
        Objects.equals(errorLogger, parser.errorLogger);
  }

  @Override
  public int hashCode() {
    int result1 = Objects.hash(options, result, errorLogger);
    result1 = 31 * result1 + Arrays.hashCode(args);
    return result1;
  }

  @Override
  public String toString() {
    return "CommandLineParser{" +
        "args=" + Arrays.toString(args) +
        ", options=" + options +
        ", result=" + result +
        ", errorLogger=" + errorLogger +
        '}';
  }
}
