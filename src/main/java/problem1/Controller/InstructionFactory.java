package problem1.Controller;

import java.util.ArrayList;
import java.util.List;
import problem1.Model.CommandLineParser;
import problem1.Model.Todo;
import problem1.Model.Todo.Builder;

/**
 * This class represents an InstructionFactory
 */
public class InstructionFactory {

  private static final String ADD_TODO = "--add-todo";
  private static final String COMPLETE_TODO = "--complete-todo";
  private static final String DISPLAY_TODO = "--display";
  private static final String TODO_TEXT = "--todo-text";
  private static final String COMPLETED = "private static final String";
  private static final String DUE = "--due";
  private static final String PRIORITY = "--priority";
  private static final String CATEGORY = "--category";
  private static final String SHOW_INCOMPLETE = "--show-incomplete";
  private static final String SHOW_CATEGORY = "--show-category";
  private static final String SORT_BY_DATE = "--sort-by-date";
  private static final String SORT_BY_PRIORITY = "--sort-by-priority";

  /**
   * Make the Instructions according to the parser
   *
   * @param parser - a given commandLineParser
   * @return a list of IInstructions
   */
  public static List<IInstruction> makeInstruction(CommandLineParser parser) {
    List<IInstruction> instructions = new ArrayList<>();
    makeAddTodo(instructions, parser);
    makeCompleteTodo(instructions, parser);
    makeDisplayTodo(instructions, parser);
    return instructions;
  }

  /**
   * Make the "Add todo" instructions
   *
   * @param instructions - a given list of instructions
   * @param parser       - a given parser
   */
  private static void makeAddTodo(List<IInstruction> instructions, CommandLineParser parser) {
    if (parser.hasOption(ADD_TODO)) {
      instructions.add(new AddInstruction(generateTodo(parser)));
    }
  }

  /**
   * Make the "Complete todo" instructions
   *
   * @param instructions - a given list of instructions
   * @param parser       - a given parser
   */
  private static void makeCompleteTodo(List<IInstruction> instructions, CommandLineParser parser) {
    if (parser.hasOption(COMPLETE_TODO)) {
      List<String> ids = generateIds(parser);
      for (String id : ids) {
        instructions.add(new CompleteInstruction(Integer.parseInt(id)));
      }
    }
  }

  /**
   * Make the "Display todo" instructions
   *
   * @param instructions - a given list of instructions
   * @param parser       - a given parser
   */
  private static void makeDisplayTodo(List<IInstruction> instructions, CommandLineParser parser) {
    if (parser.hasOption(DISPLAY_TODO)) {
      instructions.add(new DisplayInstruction(generateCommands(parser)));
    }
  }

  /**
   * Generate the "to do" according to the parser
   *
   * @param parser - a given CommandLineParser
   * @return the "to do" according to the parser
   */
  private static Todo generateTodo(CommandLineParser parser) {
    String text;
    String dueDate;
    String priority;
    String category;
    text = parser.getOptionValue(TODO_TEXT).get(0);
    Todo.Builder builder = new Builder(text);
    if (parser.hasOption(COMPLETED)) {
      builder.setCompleted("true");
    }
    if (parser.hasOption(DUE)) {
      dueDate = parser.getOptionValue(DUE).get(0);
      builder.addDueDate(dueDate);
    }
    if (parser.hasOption(PRIORITY)) {
      priority = parser.getOptionValue(PRIORITY).get(0);
      builder.setPriority(priority);
    }
    if (parser.hasOption(CATEGORY)) {
      category = parser.getOptionValue(CATEGORY).get(0);
      builder.setCategory(category);
    }
    return builder.build();
  }

  /**
   * Generates ids according the parser
   *
   * @param parser - a given CommandLineParser
   * @return ids according the parser
   */
  private static List<String> generateIds(CommandLineParser parser) {
    return parser.getOptionValue(COMPLETE_TODO);
  }

  /**
   * Generates list of display commands according the parser
   *
   * @param parser - a given CommandLineParser
   * @return list of display commands according the parser
   */
  private static List<String> generateCommands(CommandLineParser parser) {
    List<String> commands = new ArrayList<>();
    if (parser.hasOption(SHOW_INCOMPLETE)) {
      commands.add(SHOW_INCOMPLETE);
    }
    if (parser.hasOption(SHOW_CATEGORY)) {
      commands.add(SHOW_CATEGORY);
      commands.add(parser.getOptionValue(SHOW_CATEGORY).get(0));
    }
    if (parser.hasOption(SORT_BY_DATE)) {
      commands.add(SORT_BY_DATE);
    }
    if (parser.hasOption(SORT_BY_PRIORITY)) {
      commands.add(SORT_BY_PRIORITY);
    }
    return commands;
  }
}
