package problem1.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import problem1.Model.Todo;

/**
 * This class represents a date comparator
 */
public class DateComparator implements Comparator<Todo> {
  private static final String DEFAULT_DATE = "9999-09-09";

  @Override
  public int compare(Todo o1, Todo o2) {
    LocalDate d1 = null, d2 = null;
    if (o1.getDueDate() == null) {
      d1 = LocalDate.parse(DEFAULT_DATE);
    } else {
      try {
        d1 = LocalDate.parse(o1.getDueDate());
      } catch (DateTimeParseException e) {
        e.printStackTrace();
      }
    }
    if (o2.getDueDate() == null) {
      d2 = LocalDate.parse(DEFAULT_DATE);
    } else {
      try {
        d2 = LocalDate.parse(o2.getDueDate());
      } catch (DateTimeParseException e) {
        e.printStackTrace();
      }
    }

    return d1.compareTo(d2);
  }
}
