package problem1.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IErrorLoggerTest {

  IErrorLogger logger;

  @Before
  public void setUp() throws Exception {
    logger = new ErrorLogger();
  }

  @Test
  public void addAndGetError() {
    assertEquals("Errors: ", logger.getError());
    logger.addError("abc");
    assertEquals("Errors: abc"+System.lineSeparator(), logger.getError());
    logger.addError("xxx");
    assertEquals("Errors: abc"+System.lineSeparator()+"xxx"+System.lineSeparator(), logger.getError());

  }

  @Test
  public void hasError() {
    assertFalse(logger.hasError());
    logger.addError("abc");
    assertTrue(logger.hasError());
  }

  @Test
  public void testEquals() {
    ErrorLogger copy = new ErrorLogger();
    assertTrue(logger.equals(logger));
    assertTrue(logger.equals(copy));
    ErrorLogger errorNull = null;
    assertFalse(logger.equals(errorNull));
    assertFalse(logger.equals("a"));
    ErrorLogger diff = new ErrorLogger();
    diff.addError("aaa");
    assertFalse(logger.equals(diff));
  }

  @Test
  public void testHashCode() {
    ErrorLogger copy = new ErrorLogger();
    assertTrue(logger.hashCode()==copy.hashCode());
  }

  @Test
  public void testToString() {
    String expected = "ErrorLogger{errors=[]}";
    assertEquals(expected, logger.toString());
  }

}