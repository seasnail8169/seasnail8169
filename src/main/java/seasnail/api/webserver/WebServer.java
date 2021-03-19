package seasnail.api.webserver;

import seasnail.api.Config;

import java.io.File;

import static spark.Spark.*;
import static spark.Spark.get;

public class WebServer {
    public static File snales;

    public static void init() {
        port(Config.PORT);

        staticFiles.externalLocation(new File(System.getProperty("user.dir"), "src/main/resources/public").getAbsolutePath());
        staticFiles.registerMimeType("jpg", "image/jpeg");

        snales = new File(System.getProperty("user.dir"), "src/main/resources/public/snales");

        get("/api/snale", RouteController.HANDLE_SNALE_API);
        get("/discord", RouteController.HANDLE_DISCORD);
        get("/github", RouteController.HANDLE_GITHUB);
        get("/oops", RouteController.HANDLE_OOPS);
        notFound(RouteController.HANDLE_OOPS);
    }

    public static void close() {
        stop();
        awaitStop();
    }
}
