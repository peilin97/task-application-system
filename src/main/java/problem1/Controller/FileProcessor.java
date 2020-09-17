package problem1.Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import problem1.Model.Todo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * This class reads the file and generates a map of TodoList
 */
public class FileProcessor {

  private static final String REGEX = "\",\"|\"";
  private static final String HEAD_LINE = "\"id\",\"text\",\"completed\",\"due\",\"priority\",\"category\"";
  private static final Integer INDEX1 = 1;
  private static final Integer INDEX2 = 2;
  private static final Integer INDEX3 = 3;
  private static final Integer INDEX4 = 4;
  private static final Integer INDEX5 = 5;
  private static final Integer INDEX6 = 6;

  /**
   * returns a map from the file
   *
   * @param fileName - a file name
   * @return a map from the file
   */
  public static Map<Integer, Todo> getFileToMap(String fileName) {
    return readFile(fileName);
  }

  /**
   * write the given map of "to do"s to file
   *
   * @param fileName - a given file
   * @param map      - a given map of "to do"s
   */
  public static void writeToFile(String fileName, Map<Integer, Todo> map) {
    String path = generatePath(fileName);
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(path));
      writer.write(HEAD_LINE + System.lineSeparator());
      for (Entry<Integer, Todo> entry : map.entrySet()) {
        String id = "\"" + entry.getKey().toString() + "\"" + ",";
        writer.write(id + todoToString(entry.getValue()) + System.lineSeparator());
      }
      writer.close();
    } catch (IOException ioe) {
      System.out.println("Something went wrong when writing file to dir" + ioe.getMessage());
      ioe.printStackTrace();
    }
  }

  /**
   * Convert a line of string to a "to do" and add it to a map
   *
   * @param line - a given string of lines
   * @param map  -  a given map
   */
  private static void addToMap(String line, Map<Integer, Todo> map) {
    Pattern split = Pattern.compile(REGEX);
    String[] header = split.split(line);
    Todo todo = new Todo.Builder(header[INDEX2]).setCompleted(header[INDEX3])
        .addDueDate(header[INDEX4])
        .setPriority(header[INDEX5]).setCategory(header[INDEX6]).build();
    map.put(Integer.parseInt(header[INDEX1]), todo);
  }

  /**
   * Generates the path from the given file name
   *
   * @param fileName the given file name
   * @return the path from the given file name
   */
  private static String generatePath(String fileName) {
    String[] directory = fileName.split("([/\\\\])");
    StringBuilder path = new StringBuilder();
    for (int i = 0; i < directory.length; i++) {
      if (i == directory.length - 1) {
        path.append(directory[i]);
      } else {
        path.append(directory[i]).append(File.separator);
      }
    }
    return path.toString();
  }

  /**
   * returns a map of "to do"s
   *
   * @param fileName - a given filename
   * @return a map of "to do"s
   */
  private static Map<Integer, Todo> readFile(String fileName) {
    String path = generatePath(fileName);

    Map<Integer, Todo> map = new HashMap<>();
    BufferedReader inputFile = null;
    try {
      inputFile = new BufferedReader(new FileReader(path));

      String line;
      inputFile.readLine(); // Pass the header
      while ((line = inputFile.readLine()) != null) {
        addToMap(line, map);
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("A file was not found : " + fnfe.getMessage());
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
    } finally {
      if (inputFile != null) {
        try {
          inputFile.close();
        } catch (IOException e) {
          System.out.println("Failed to close input stream");
        }
      }
    }
    return map;
  }

  /**
   * Convert a "to do" to a String
   *
   * @param todo - a given todo
   * @return a "to do" to a String
   */
  private static String todoToString(Todo todo) {
    String task = "";
    task += "\"" + todo.getText() + "\"" + ",";
    task += "\"" + todo.getCompleted() + "\"" + ",";
    task += "\"" + ((todo.getDueDate() == null) ? "?" : todo.getDueDate()) + "\"" + ",";
    task += "\"" + ((todo.getPriority() == null) ? "?" : todo.getPriority()) + "\"" + ",";
    task += "\"" + ((todo.getCategory() == null) ? "?" : todo.getCategory()) + "\"";
    return task;
  }
}
