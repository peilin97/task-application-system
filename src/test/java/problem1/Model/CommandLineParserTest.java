package problem1.Model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class CommandLineParserTest {

  Options todoOptions;
  ErrorLogger logger;

  @Before
  public void setUp() throws Exception {
    todoOptions = TodoSystem.startTodoSystem().getOptions();
    logger = new ErrorLogger();
  }

  @Test
  public void parseAddTodo() {
    String[] args = {"--csv-file", "filepath", "--add-todo", "--todo-text", "finish hw8",
        "--completed",
        "--due", "04/01/2020", "--priority", "1", "--category", "cs5004"};
    CommandLineParser parser = new CommandLineParser(args, todoOptions, logger);
    assertTrue(parser.hasOption("--csv-file"));
    assertTrue(parser.hasOption("--add-todo"));
    assertTrue(parser.hasOption("--todo-text"));
    assertTrue(parser.hasOption("--completed"));
    assertTrue(parser.hasOption("--due"));
    assertEquals("filepath", parser.getOptionValue("--csv-file").get(0));
    assertEquals("finish hw8", parser.getOptionValue("--todo-text").get(0));
    assertEquals("04/01/2020", parser.getOptionValue("--due").get(0));
    assertEquals("1", parser.getOptionValue("--priority").get(0));
    assertEquals("cs5004", parser.getOptionValue("--category").get(0));
    assertFalse(logger.hasError());
  }

  @Test
  public void completeTodo() {
    String[] args = {"--complete-todo", "5", "--csv-file", "filepath", "--complete-todo", "20"};
    CommandLineParser parser = new CommandLineParser(args, todoOptions, logger);
    assertFalse(logger.hasError());
    ArrayList<String> ids = new ArrayList<>();
    ids.add("5");
    ids.add("20");
    assertEquals(ids, parser.getOptionValue("--complete-todo"));
  }

  @Test
  public void missRequiredOptions() {
    String[] args = {"--add-todo", "--todo-text", "finish hw8", "--display", "--complete-todo",
        "20"};
    CommandLineParser parser = new CommandLineParser(args, todoOptions, logger);
    assertTrue(logger.hasError());
    String expected = "Errors: --csv-file was not provided."+System.lineSeparator();
    assertEquals(expected, logger.getError());
  }

  @Test
  public void invalidOptions() {
    String[] args = {"--invalid", "--todo-text", "finish hw8", "--display", "--complete-todo",
        "20"};
    CommandLineParser parser = new CommandLineParser(args, todoOptions, logger);
    assertTrue(logger.hasError());
    String expected = "Errors: --invalid was invalid."+System.lineSeparator();
    assertEquals(expected, logger.getError());
  }

  @Test
  public void missValue1() {
    String[] args = {"--csv-file", "filepath", "--add-todo", "--todo-text", "--display",
        "--complete-todo", "20"};
    CommandLineParser parser = new CommandLineParser(args, todoOptions, logger);
    assertTrue(logger.hasError());
    String expected = "Errors: --todo-text was provided, but the parameter was not given."+System.lineSeparator();
    assertEquals(expected, logger.getError());
  }

  @Test
  public void missValue2() {
    String[] args = {"--csv-file", "filepath", "--display", "--complete-todo"};
    CommandLineParser parser = new CommandLineParser(args, todoOptions, logger);
    assertTrue(logger.hasError());
    String expected = "Errors: --complete-todo was provided, but the parameter was not given."+System.lineSeparator();
    assertEquals(expected, logger.getError());
  }

  @Test
  public void duplicateOptions() {
    String[] args = {"--csv-file", "filepath", "--display", "--complete-todo", "20", "--display"};
    CommandLineParser parser = new CommandLineParser(args, todoOptions, logger);
    assertTrue(logger.hasError());
    String expected = "Errors: --display was provided more than one time."+System.lineSeparator();
    assertEquals(expected, logger.getError());
  }

  @Test
  public void validMultipleOptions() {
    Option add = new Option.Builder("--add").
        setAllowsMultiple().
        build();
    String[] args = {"--add", "--add"};
    Options options1 = new Options(new Option[]{add});
    CommandLineParser parser = new CommandLineParser(args, options1, logger);
    assertFalse(logger.hasError());
    ArrayList<String> adds = new ArrayList<>();
    adds.add("--add");
    adds.add("--add");
    assertEquals(adds, parser.getOptionValue("--add"));
  }

  @Test
  public void missDependedOptions() {
    String[] args = {"--csv-file", "filepath", "--add-todo", "--complete-todo", "20", "--display"};
    CommandLineParser parser = new CommandLineParser(args, todoOptions, logger);
    assertTrue(logger.hasError());
    String expected = "Errors: --add-todo was provided, but --todo-text was not given."+System.lineSeparator();
    assertEquals(expected, logger.getError());
  }

  @Test
  public void incompatibleOptions() {
    String[] args = {"--csv-file", "filepath", "--sort-by-date", "--sort-by-priority", "--display"};
    CommandLineParser parser = new CommandLineParser(args, todoOptions, logger);
    assertTrue(logger.hasError());
    String expected = "Errors: --sort-by-date cannot be combined with --sort-by-priority."+System.lineSeparator();
    assertEquals(expected, logger.getError());
  }

  @Test
  public void incompatibleOptions2() {
    String[] args = {"--csv-file", "filepath", "--sort-by-date", "--display"};
    CommandLineParser parser = new CommandLineParser(args, todoOptions, logger);
    assertFalse(logger.hasError());
  }

  @Test
  public void testEquals() {
    Option add = new Option.Builder("--add").
        setRequiresValue().
        setRequired().
        build();
    Option clear = new Option.Builder("--clear").
        build();
    Options options1 = new Options(new Option[]{add});
    Options options2 = new Options(new Option[]{add, clear});
    String[] args = {"--add", "abc"};
    CommandLineParser parser = new CommandLineParser(args, options1, logger);
    CommandLineParser parserCopy = new CommandLineParser(args, options1, logger);
    assertTrue(parser.equals(parser));
    assertTrue(parser.equals(parserCopy));
    CommandLineParser parseNull = null;
    assertFalse(parser.equals(parseNull));
    assertFalse(parser.equals("2"));
    // test parser with different args
    String[] differentArgs = {"--add", "aaa"};
    CommandLineParser parser2 = new CommandLineParser(differentArgs, options1, logger);
    assertFalse(parser.equals(parser2));
    // test parser with different options
    CommandLineParser parser3 = new CommandLineParser(args, options2, logger);
    assertFalse(parser.equals(parser3));
    // test parser with different error logger
    ErrorLogger logger1 = new ErrorLogger();
    logger1.addError("xxx");
    CommandLineParser parser4 = new CommandLineParser(args, options1, logger1);
    assertFalse(parser.equals(parser4));
  }

  @Test
  public void testHashCode() {
    String[] args = {"--csv-file", "filepath", "--display"};
    CommandLineParser parser = new CommandLineParser(args, todoOptions, logger);
    CommandLineParser parserCopy = new CommandLineParser(args, todoOptions, logger);
    assertTrue(parser.hashCode() == parserCopy.hashCode());
  }

  @Test
  public void testToString() {
    Option add = new Option.Builder("--add").
        setRequiresValue().
        setRequired().
        build();
    String[] args = {"--add", "abc"};
    Options options1 = new Options(new Option[]{add});
    CommandLineParser parser = new CommandLineParser(args, options1, logger);
    String expected = "CommandLineParser{args=[--add, abc], options=Options{options=[Option{name='--add', depends=[], forbids=[], required=true, requiresValue=true, allowsMultiple=false, usage='null'}]}, result={--add=[abc]}, errorLogger=ErrorLoggerDecorator{logger=ErrorLogger{errors=[]}}}";
    assertEquals(expected, parser.toString());
  }


}