package problem1.Controller;

import java.io.File;
import java.util.List;
import problem1.Model.CommandLineParser;
import problem1.Model.ErrorLogger;
import problem1.Model.Options;
import problem1.Model.TodoList;
import problem1.Model.TodoSystem;
import problem1.View.UsageViewHelper;

public class Main {

  public static void main(String[] args) {
    Options options = TodoSystem.startTodoSystem().getOptions();
    ErrorLogger logger = new ErrorLogger();
    CommandLineParser parser = new CommandLineParser(args, options, logger);
    if (logger.hasError()) {
      System.out.println(logger.getError());
      System.out.println(UsageViewHelper.getOptionsUsage(options));
      return;
    }
    String filePath = parser.getOptionValue("--csv-file").get(0);
    TodoList todoList = new TodoList(FileProcessor.getFileToMap(filePath), logger);
    List<IInstruction> instructions = InstructionFactory.makeInstruction(parser);
    for (IInstruction instruction: instructions) {
      instruction.takeAction(todoList);
    }
    if (logger.hasError()) {
      System.out.println(logger.getError());
      System.out.println(UsageViewHelper.getOptionsUsage(options));
      return;
    }
    FileProcessor.writeToFile(filePath, todoList.getTodoList());
  }
}
