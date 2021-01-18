package seasnail.api;

import java.util.Map;

public class Main {
  public static void main(String[] args) {
//    WebServer.init();
    System.out.println("\nRead All Variables:-\n");
    for (Map.Entry <String, String> entry: System.getenv().entrySet()) System.out.println(entry.getKey() + ": " + entry.getValue());
  }
}
