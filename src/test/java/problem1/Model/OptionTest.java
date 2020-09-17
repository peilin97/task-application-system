package problem1.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OptionTest {

  Option option1;

  @Before
  public void setUp() throws Exception {
    option1 = new Option.Builder("--aaa").
        setRequiresValue().
        setAllowsMultiple().
        addDepends(new String[]{"--bbb"}).
        addForbids(new String[]{"--ccc"}).
        setUsage("aaa").
        setRequired().
        build();
  }

  @Test
  public void getName() {
    assertEquals("--aaa", option1.getName());
  }

  @Test
  public void getDepends() {
    String[] expected = new String[]{"--bbb"};
    assertArrayEquals(expected, option1.getDepends());
  }

  @Test
  public void getForbids() {
    String[] expected = new String[]{"--ccc"};
    assertArrayEquals(expected, option1.getForbids());
  }

  @Test
  public void isRequired() {
    assertTrue(option1.isRequired());
  }

  @Test
  public void isRequiresValue() {
    assertTrue(option1.isRequiresValue());
  }

  @Test
  public void getUsage() {
    assertEquals("aaa", option1.getUsage());
  }

  @Test
  public void isAllowsMultiple() {
    assertTrue(option1.isAllowsMultiple());
  }

  @Test
  public void testEquals() {
    assertTrue(option1.equals(option1));
    Option optionNull = null;
    assertFalse(option1.equals(optionNull));
    assertFalse(option1.equals("a"));
    Option copy = new Option.Builder("--aaa").
        setRequiresValue().
        setAllowsMultiple().
        addDepends(new String[]{"--bbb"}).
        addForbids(new String[]{"--ccc"}).
        setUsage("aaa").
        setRequired().
        build();
    assertTrue(option1.equals(copy));
    Option diffName = new Option.Builder("--a").
        setRequiresValue().
        setAllowsMultiple().
        addDepends(new String[]{"--bbb"}).
        addForbids(new String[]{"--ccc"}).
        setUsage("aaa").
        setRequired().
        build();
    Option notRequireValue = new Option.Builder("--aaa").
        setAllowsMultiple().
        addDepends(new String[]{"--bbb"}).
        addForbids(new String[]{"--ccc"}).
        setUsage("aaa").
        setRequired().
        build();
    Option notAllowMultiple = new Option.Builder("--aaa").
        setRequiresValue().
        addDepends(new String[]{"--bbb"}).
        addForbids(new String[]{"--ccc"}).
        setUsage("aaa").
        setRequired().
        build();
    Option diffDepends = new Option.Builder("--aaa").
        setRequiresValue().
        setAllowsMultiple().
        addForbids(new String[]{"--ccc"}).
        setUsage("aaa").
        setRequired().
        build();
    Option diffForbids = new Option.Builder("--aaa").
        setRequiresValue().
        setAllowsMultiple().
        addDepends(new String[]{"--bbb"}).
        setUsage("aaa").
        setRequired().
        build();
    Option diffUsage = new Option.Builder("--aaa").
        setRequiresValue().
        setAllowsMultiple().
        addDepends(new String[]{"--bbb"}).
        addForbids(new String[]{"--ccc"}).
        setUsage("a").
        setRequired().
        build();
    Option optional = new Option.Builder("--aaa").
        setRequiresValue().
        setAllowsMultiple().
        addDepends(new String[]{"--bbb"}).
        addForbids(new String[]{"--ccc"}).
        setUsage("aaa").
        build();
    assertFalse(option1.equals(diffName));
    assertFalse(option1.equals(notRequireValue));
    assertFalse(option1.equals(notAllowMultiple));
    assertFalse(option1.equals(diffDepends));
    assertFalse(option1.equals(diffForbids));
    assertFalse(option1.equals(diffUsage));
    assertFalse(option1.equals(optional));
  }

  @Test
  public void testHashCode() {
    Option copy = new Option.Builder("--aaa").
        setRequiresValue().
        setAllowsMultiple().
        addDepends(new String[]{"--bbb"}).
        addForbids(new String[]{"--ccc"}).
        setUsage("aaa").
        setRequired().
        build();
    assertTrue(option1.hashCode()==copy.hashCode());
  }

  @Test
  public void testToString() {
    String expected = "Option{name='--aaa', depends=[--bbb], forbids=[--ccc], required=true,"+
        " requiresValue=true, allowsMultiple=true, usage='aaa'}";
    assertEquals(expected, option1.toString());
  }
}