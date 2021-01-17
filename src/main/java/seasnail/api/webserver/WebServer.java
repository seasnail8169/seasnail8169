package seasnail.api.webserver;

import java.io.File;

import static spark.Spark.*;
import static spark.Spark.get;

public class WebServer {
    public static File snales;

    public static void init() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.environment().putIfAbsent("PORT", "8082");

        port(Integer.parseInt(processBuilder.environment().get("PORT")));

        staticFiles.externalLocation(new File(System.getProperty("user.dir"), "src/main/resources/public").getAbsolutePath());
        staticFiles.registerMimeType("jpg", "image/jpeg");

        snales = new File(System.getProperty("user.dir"), "src/main/resources/public/snales");

        get("/api/snale", RouteController.HANDLE_SNALE_API);
        get("/discord", RouteController.HANDLE_DISCORD);
        get("/github", RouteController.HANDLE_GITHUB);
    }
}
