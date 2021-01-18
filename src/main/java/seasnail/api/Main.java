package seasnail.api;

public class Main {
  public static void main(String[] args) {
//    WebServer.init();
    ProcessBuilder processBuilder = new ProcessBuilder();
    System.out.println("PORT " + processBuilder.environment().get("PORT"));
    System.out.println("TOKEN " + processBuilder.environment().get("TOKEN"));
  }
}
