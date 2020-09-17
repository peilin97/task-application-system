package problem1.Controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import problem1.Controller.Filter.Builder;

public class FilterTest {
  Builder b1, b2;
  Filter f1, f2;
  @Before
  public void setUp() throws Exception {
    b1 = new Builder().setFilterByCategory("a");
    f1 = b1.build();
  }

  @Test
  public void testEquals() {
    assertEquals(f1, f1);
    String thing = "";
    assertNotEquals(f1, thing);
    b2 = new Builder().setFilterByCategory("a");
    f2 = b2.build();
    assertEquals(f1, f2);
    b2 = new Builder().setFilterByCategory("b");
    f2 = b2.build();
    assertNotEquals(f1, f2);
    b2 = new Builder().setFilterByIncomplete();
    f2 = b2.build();
    assertNotEquals(f1, f2);
  }

  @Test
  public void testHashCode() {
    b2 = new Builder().setFilterByCategory("a");
    f2 = b2.build();
    assertEquals(f1.hashCode(), f2.hashCode());
  }

  @Test
  public void testToString() {
    assertEquals("Filter{filterByIncomplete=false, filterByCategory='a'}", f1.toString());
  }
}