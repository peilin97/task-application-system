package problem1.Controller;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import problem1.Model.CommandLineParser;
import problem1.Model.ErrorLogger;
import problem1.Model.Option;
import problem1.Model.Options;
import problem1.Model.Todo;
import problem1.Model.Todo.Builder;
import problem1.Model.TodoList;
import problem1.Model.TodoSystem;

public class InstructionFactoryTest {

  CommandLineParser parser;
  CommandLineParser parser2;
  Options options;
  ErrorLogger logger;

  @Before
  public void setUp() throws Exception {
    logger = new ErrorLogger();
    options = TodoSystem.startTodoSystem().getOptions();
    String[] args = new String[]{"--csv-file", "todos.csv", "--add-todo", "--todo-text", "testing",
        "--complete-todo", "1", "--display", "--sort-by-date"};
    String[] args2 = new String[]{"--csv-file", "todos.csv", "--add-todo", "--todo-text", "testing",
        "--completed", "--due", "2020-04-07", "--priority", "2", "--category", "music", "--display", "--show-incomplete", "--sort-by-priority", "--show-category", "school"};
    parser = new CommandLineParser(args, options, logger);
    parser2 = new CommandLineParser(args2, options, logger);
  }

  @Test
  public void makeInstruction() {
    TodoList myList = new TodoList(FileProcessor.getFileToMap("todos.csv"), new ErrorLogger());
    List<IInstruction> list = InstructionFactory.makeInstruction(parser);
    assertEquals(3, list.size());

    for (IInstruction instruction: list) {
      instruction.takeAction(myList);
    }

    list = InstructionFactory.makeInstruction(parser2);
    assertEquals(2, list.size());

    for (IInstruction instruction: list) {
      instruction.takeAction(myList);
    }
  }
}