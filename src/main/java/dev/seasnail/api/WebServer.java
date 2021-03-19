package dev.seasnail.api;

import dev.seasnail.api.managers.RouteManager;

import java.io.File;

import static spark.Spark.*;

public class WebServer {
    public static File snales;

    public static void init() {
        port(Config.PORT);

        staticFiles.externalLocation(new File(System.getProperty("user.dir"), "src/main/resources/public").getAbsolutePath());
        staticFiles.registerMimeType("jpg", "image/jpeg");

        snales = new File(System.getProperty("user.dir"), "src/main/resources/public/snales");

        get("/api/snale", RouteManager.HANDLE_SNALE_API);
        get("/discord", RouteManager.HANDLE_DISCORD);
        get("/github", RouteManager.HANDLE_GITHUB);
        get("/oops", RouteManager.HANDLE_OOPS);
        notFound(RouteManager.HANDLE_OOPS);
    }

    public static void close() {
        stop();
        awaitStop();
    }
}
