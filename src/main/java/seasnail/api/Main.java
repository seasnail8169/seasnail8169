package seasnail.api;

import seasnail.api.webserver.WebServer;

public class Main {
  public static void main(String[] args) {
    Config.init();
    WebServer.init();
  }
}
