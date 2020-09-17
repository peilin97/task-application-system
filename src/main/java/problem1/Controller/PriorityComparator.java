package problem1.Controller;

import java.util.Comparator;
import problem1.Model.Todo;

/**
 * This class represents a PriorityComparator
 */
public class PriorityComparator implements Comparator<Todo> {
  private static final int THREE = 4;

  @Override
  public int compare(Todo o1, Todo o2) {
    Integer p1 = (o1.getPriority() == null)? THREE: o1.getPriority();
    Integer p2 = (o2.getPriority() == null)? THREE: o2.getPriority();
    return p1.compareTo(p2);
  }
}
