package seasnail.api;

import spark.Route;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

import static spark.Spark.*;

public class Main {
  private static File snales;

  public static void main(String[] args) {
    port(getHerokuAssignedPort());

    staticFiles.externalLocation(new File(System.getProperty("user.dir"), "public").getAbsolutePath());
    staticFiles.registerMimeType("png", "image/png");
    staticFiles.registerMimeType("jpg", "image/jpeg");
    staticFiles.registerMimeType("jpeg", "image/jpeg");
    staticFiles.registerMimeType("webp", "image/webp");

    snales = new File(System.getProperty("user.dir"), "public/snales");

    get("/api/snale", HANDLE_SNALE_API);
    get("/discord", HANDLE_DISCORD);
    get("/github", HANDLE_GITHUB);
//    get("/snale", HANDLE_SNALE);
  }

  public static Route HANDLE_SNALE_API = (request, response) -> getUrl() + "/snales/" + getRandomSnale();

  public static Route HANDLE_SNALE = (request, response) -> {
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    BufferedImage bufferedImage = ImageIO.read(new File(System.getProperty("user.dir") + "public/snales/" + getRandomSnale()));

    ImageIO.write(bufferedImage, "jpg", stream);
    stream.flush();

    return stream.toByteArray();
  };

  public static Route HANDLE_DISCORD = (request, response) -> {
    response.redirect("https://discord.com/invite/Pta3APY");
    return null;
  };

  public static Route HANDLE_GITHUB = (request, response) -> {
    response.redirect("https://www.github.com/seasnail8169");
    return null;
  };

  private static String getRandomSnale() {
    String[] snaleList = snales.list();
    return snaleList == null ? "somethingwentwronglol" : snaleList[ThreadLocalRandom.current().nextInt(0, snaleList.length)];
  }

  private static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) return Integer.parseInt(processBuilder.environment().get("PORT"));
    return 8082;
  }

  private static boolean isLocahost() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    return processBuilder.environment().get("PORT") == null;
  }

  private static String getUrl() {
    if (isLocahost()) return "https://localhost:8082";
    else return "https://www.seasnail.xyz";
  }
}
