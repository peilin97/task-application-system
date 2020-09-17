package problem1.Model;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents the option
 */
public class Option {

  private String name;
  private String[] depends;
  private String[] forbids;
  private boolean required;
  private boolean requiresValue;
  private boolean allowsMultiple;
  private String usage;

  /**
   * Constructs the Option with given builder
   *
   * @param builder - a given builder
   */
  private Option(Builder builder) {
    this.name = builder.name;
    this.depends = builder.depends;
    this.forbids = builder.forbids;
    this.required = builder.required;
    this.requiresValue = builder.requiresValue;
    this.allowsMultiple = builder.allowsMultiple;
    this.usage = builder.usage;
  }

  /**
   * returns the name
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * returns the depends
   *
   * @return the depends
   */
  public String[] getDepends() {
    return depends;
  }

  /**
   * returns the forbids
   *
   * @return the forbids
   */
  public String[] getForbids() {
    return forbids;
  }

  /**
   * returns whether is required
   *
   * @return whether is required
   */
  public boolean isRequired() {
    return required;
  }

  /**
   * returns whether is required value
   *
   * @return whether is required value
   */
  public boolean isRequiresValue() {
    return requiresValue;
  }

  /**
   * returns the usage
   *
   * @return the usage
   */
  public String getUsage() {
    return usage;
  }

  /**
   * returns whether allows Multiple
   *
   * @return whether allows Multiple
   */
  public boolean isAllowsMultiple() {
    return allowsMultiple;
  }

  /**
   * This class represents a Builder
   */
  public static class Builder {

    private String name;
    private String[] depends;
    private String[] forbids;
    private boolean required;
    private boolean requiresValue;
    private boolean allowsMultiple;
    private String usage;

    /**
     * Constructs the Builder with given name
     *
     * @param name - a given name
     */
    public Builder(String name) {
      this.name = name;
      this.depends = new String[]{};
      this.forbids = new String[]{};
      this.required = false;
      this.requiresValue = false;
      this.allowsMultiple = false;
      this.usage = null;
    }

    /**
     * returns the Builder with depends added
     *
     * @param depends - a given depends
     * @return the Builder with depends added
     */
    public Builder addDepends(String[] depends) {
      this.depends = depends;
      return this;
    }

    /**
     * returns the Builder with forbids added
     *
     * @param forbids - a given forbids
     * @return the Builder with forbids added
     */
    public Builder addForbids(String[] forbids) {
      this.forbids = forbids;
      return this;
    }

    /**
     * returns the Builder with required thing setted
     *
     * @return the Builder with required thing setted
     */
    public Builder setRequired() {
      this.required = true;
      return this;
    }

    /**
     * returns the Builder with required value setted
     *
     * @return the Builder with required value setted
     */
    public Builder setRequiresValue() {
      this.requiresValue = true;
      return this;
    }

    /**
     * returns the Builder withAllowsMultiple setted
     *
     * @return the Builder withAllowsMultiple setted
     */
    public Builder setAllowsMultiple() {
      this.allowsMultiple = true;
      return this;
    }

    /**
     * Sets the usage, and returns the Builder
     *
     * @param usage -  a given usage
     * @return the Builder
     */
    public Builder setUsage(String usage) {
      this.usage = usage;
      return this;
    }

    /**
     * Build the option
     *
     * @return the option
     */
    public Option build() {
      return new Option(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Option option = (Option) o;
    return isRequired() == option.isRequired() &&
        isRequiresValue() == option.isRequiresValue() &&
        isAllowsMultiple() == option.isAllowsMultiple() &&
        Objects.equals(getName(), option.getName()) &&
        Arrays.equals(getDepends(), option.getDepends()) &&
        Arrays.equals(getForbids(), option.getForbids()) &&
        Objects.equals(getUsage(), option.getUsage());
  }

  @Override
  public int hashCode() {
    int result = Objects
        .hash(getName(), isRequired(), isRequiresValue(), isAllowsMultiple(), getUsage());
    result = 31 * result + Arrays.hashCode(getDepends());
    result = 31 * result + Arrays.hashCode(getForbids());
    return result;
  }

  @Override
  public String toString() {
    return "Option{" +
        "name='" + name + '\'' +
        ", depends=" + Arrays.toString(depends) +
        ", forbids=" + Arrays.toString(forbids) +
        ", required=" + required +
        ", requiresValue=" + requiresValue +
        ", allowsMultiple=" + allowsMultiple +
        ", usage='" + usage + '\'' +
        '}';
  }
}
