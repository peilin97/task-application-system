package problem1.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ErrorLoggerDecoratorTest {

  ErrorLogger logger;
  ErrorLoggerDecorator loggerDecorator;

  @Before
  public void setUp() throws Exception {
    logger = new ErrorLogger();
    loggerDecorator = new ErrorLoggerDecorator(logger);
  }

  @Test
  public void hasError() {
    assertFalse(loggerDecorator.hasError());
    loggerDecorator.invalidID(2);
    assertTrue(loggerDecorator.hasError());
  }

  @Test
  public void invalidOption() {
    loggerDecorator.invalidOption("-add");
    String expected = "Errors: -add was invalid."+System.lineSeparator();
    assertEquals(expected, logger.getError());
  }

  @Test
  public void optionMissed() {
    loggerDecorator.optionMissed("-add");
    String expected = "Errors: -add was not provided."+System.lineSeparator();
    assertEquals(expected, logger.getError());
  }

  @Test
  public void paramMissed() {
    loggerDecorator.paramMissed("-add");
    String expected = "Errors: -add was provided, but the parameter was not given."+System.lineSeparator();
    assertEquals(expected, logger.getError());
  }

  @Test
  public void dependedOptionMissed() {
    loggerDecorator.dependedOptionMissed("--add-todo", "--todo-text");
    String expected = "Errors: --add-todo was provided, but --todo-text was not given."+System.lineSeparator();
    assertEquals(expected, logger.getError());
  }

  @Test
  public void incompatibleOptions() {
    loggerDecorator.incompatibleOptions("--sort-by-date", "--sort-by-priority");
    String expected = "Errors: --sort-by-date cannot be combined with --sort-by-priority."+System.lineSeparator();
    assertEquals(expected, logger.getError());
  }

  @Test
  public void duplicateOptions() {
    loggerDecorator.duplicateOptions("-add");
    String expected = "Errors: -add was provided more than one time."+System.lineSeparator();
    assertEquals(expected, logger.getError());
  }

  @Test
  public void doubleCompleted() {
    loggerDecorator.doubleCompleted(12);
    String expected = "Errors: The todo with the ID 12 has already been completed."+System.lineSeparator();
    assertEquals(expected, logger.getError());
  }

  @Test
  public void invalidID() {
    loggerDecorator.invalidID(12);
    String expected = "Errors: The todo with the ID 12 doesn't exist."+System.lineSeparator();
    assertEquals(expected, logger.getError());
  }

  @Test
  public void categoryNotFound() {
    loggerDecorator.categoryNotFound();
    String expected = "Errors: Category is not found!"+System.lineSeparator();
    assertEquals(expected, logger.getError());
  }

  @Test
  public void testEquals() {
    assertTrue(loggerDecorator.equals(loggerDecorator));
    ErrorLoggerDecorator decoratorNull = null;
    assertFalse(loggerDecorator.equals(decoratorNull));
    assertFalse(loggerDecorator.equals("a"));
    ErrorLoggerDecorator copy = new ErrorLoggerDecorator(logger);
    assertTrue(loggerDecorator.equals(copy));
    ErrorLogger logger1 = new ErrorLogger();
    ErrorLoggerDecorator decorator1 = new ErrorLoggerDecorator(logger1);
    decorator1.categoryNotFound();
    assertFalse(loggerDecorator.equals(decorator1));
  }

  @Test
  public void testHashCode() {
    ErrorLoggerDecorator copy = new ErrorLoggerDecorator(logger);
    assertTrue(loggerDecorator.hashCode()==copy.hashCode());
  }

  @Test
  public void testToString() {
    String expected = "ErrorLoggerDecorator{logger=ErrorLogger{errors=[]}}";
    assertEquals(expected, loggerDecorator.toString());
  }
}