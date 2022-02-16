import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Util for running the jasmin files in the CLI.
 */
public class RunProgram {

  public static void run(String command) {
    try {
      Process proc = Runtime.getRuntime().exec(command);
      BufferedReader reader = new BufferedReader(
        new InputStreamReader(proc.getInputStream())
      );

      String line = "";
      while ((line = reader.readLine()) != null) {
        System.out.print(line + "\n");
      }
      proc.waitFor();
    } catch (Exception e) {
      System.out.println("Error running program in CLI");
      e.printStackTrace();
    }
  }
}
