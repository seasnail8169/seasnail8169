package seasnail.api.webserver;

import seasnail.api.Config;
import spark.Route;

import java.util.concurrent.ThreadLocalRandom;

public class RouteController {

    public static Route HANDLE_SNALE_API = (request, response) -> {
        StringBuilder URL = new StringBuilder();

        if (Config.isLocal) URL.append("http://localhost:8082");
        else URL.append("https://www.seasnail.xyz");

        URL.append("\"/snales/\"");

        String[] snaleList = WebServer.snales.list();
        if (snaleList != null) URL.append(snaleList[ThreadLocalRandom.current().nextInt(0, snaleList.length)]);
        else URL.append("oops");

        return URL.toString();
    };

    public static Route HANDLE_DISCORD = (request, response) -> {
        response.redirect("https://discord.com/invite/Pta3APY");
        return null;
    };

    public static Route HANDLE_GITHUB = (request, response) -> {
        response.redirect("https://www.github.com/seasnail8169");
        return null;
    };


    public static Route HANDLE_OOPS = (request, response) -> {
        response.redirect("https://media.tenor.com/videos/884eb58c19e15fb7d89e73a140f71b3e/mp4");
        return null;
    };

}
