package problem1.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OptionsTest {

  Option add;
  Options options;

  @Before
  public void setUp() throws Exception {
    add = new Option.Builder("--add").build();
    options = new Options(new Option[]{add});
  }

  @Test
  public void contains() {
    assertTrue(options.contains("--add"));
    assertFalse(options.contains("--clear"));
  }

  @Test
  public void getOption() {
    assertEquals(add, options.getOption("--add"));
    assertEquals(null, options.getOption("--clear"));
  }

  @Test
  public void getOptions() {
    assertArrayEquals(new Option[]{add}, options.getOptions());
  }

  @Test
  public void testEquals() {
    assertTrue(options.equals(options));
    Options optionsNull = null;
    assertFalse(options.equals(optionsNull));
    assertFalse(options.equals("aaa"));
    Options copy = new Options(new Option[]{add});
    assertTrue(options.equals(copy));
    Option clear = new Option.Builder("--clear").build();
    Options diff = new Options(new Option[]{add, clear});
    assertFalse(options.equals(diff));
  }

  @Test
  public void testHashCode() {
    Options copy = new Options(new Option[]{add});
    assertTrue(options.hashCode()==copy.hashCode());
  }

  @Test
  public void testToString() {
    String expected = "Options{options=[Option{name='--add', depends=[], forbids=[],"+
        " required=false, requiresValue=false, allowsMultiple=false, usage='null'}]}";
    assertEquals(expected, options.toString());
  }
}