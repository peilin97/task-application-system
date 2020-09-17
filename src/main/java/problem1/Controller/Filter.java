package problem1.Controller;

import java.util.Objects;

/**
 * This class represents a filter
 */
public class Filter {

  private boolean filterByIncomplete = false;
  private String filterByCategory;

  /**
   * Constructs the Filter with given Builder
   *
   * @param builder - a given Builder
   */
  private Filter(Builder builder) {
    this.filterByIncomplete = builder.filterByIncomplete;
    this.filterByCategory = builder.filterByCategory;
  }

  /**
   * returns whether isFilterByIncomplete
   *
   * @return whether isFilterByIncomplete
   */
  public boolean isFilterByIncomplete() {
    return filterByIncomplete;
  }

  /**
   * returns filterByCategory
   *
   * @return filterByCategory
   */
  public String getFilterByCategory() {
    return filterByCategory;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Filter filter = (Filter) o;
    return isFilterByIncomplete() == filter.isFilterByIncomplete() &&
        Objects.equals(getFilterByCategory(), filter.getFilterByCategory());
  }

  @Override
  public int hashCode() {
    return Objects.hash(isFilterByIncomplete(), getFilterByCategory());
  }

  @Override
  public String toString() {
    return "Filter{" +
        "filterByIncomplete=" + filterByIncomplete +
        ", filterByCategory='" + filterByCategory + '\'' +
        '}';
  }

  /**
   * This class represents a filter Builder
   */
  public static class Builder {

    private boolean filterByIncomplete;
    private String filterByCategory;

    /**
     * Constructs a Builder
     */
    public Builder() {
      this.filterByIncomplete = false;
      this.filterByCategory = null;
    }

    /**
     * set FilterByIncomplete, return the Builder
     *
     * @return the Builder
     */
    public Builder setFilterByIncomplete() {
      this.filterByIncomplete = true;
      return this;
    }

    /**
     * set FilterByCategory
     *
     * @param category - a given category
     * @return the builder
     */
    public Builder setFilterByCategory(String category) {
      this.filterByCategory = category;
      return this;
    }

    /**
     * Build the filter
     *
     * @return the filter
     */
    public Filter build() {
      return new Filter(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Builder builder = (Builder) o;
      return filterByIncomplete == builder.filterByIncomplete &&
          Objects.equals(filterByCategory, builder.filterByCategory);
    }

    @Override
    public int hashCode() {
      return Objects.hash(filterByIncomplete, filterByCategory);
    }

    @Override
    public String toString() {
      return "Builder{" +
          "filterByIncomplete=" + filterByIncomplete +
          ", filterByCategory='" + filterByCategory + '\'' +
          '}';
    }
  }
}
