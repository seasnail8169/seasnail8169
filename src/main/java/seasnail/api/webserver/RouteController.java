package seasnail.api.webserver;

import seasnail.api.Config;
import spark.Route;

import java.util.concurrent.ThreadLocalRandom;

public class RouteController {
/*
    public static Route HANDLE_SNALE = (request, response) -> {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        BufferedImage bufferedImage = ImageIO.read(new File(System.getProperty("user.dir") + "public/snales/" + getRandomSnale()));

        ImageIO.write(bufferedImage, "jpg", stream);
        stream.flush();

        return stream.toByteArray();
    };
*/

    public static Route HANDLE_SNALE_API = (request, response) -> getUrl() + "/snales/" + getRandomSnale();

    public static Route HANDLE_DISCORD = (request, response) -> {
        response.redirect("https://discord.com/invite/Pta3APY");
        return null;
    };

    public static Route HANDLE_GITHUB = (request, response) -> {
        response.redirect("https://www.github.com/seasnail8169");
        return null;
    };

    private static String getUrl() {
        if (Config.LOCAL) return "http://localhost:8082";
        else return "https://www.seasnail.xyz";
    }

    private static String getRandomSnale() {
        String[] snaleList = WebServer.snales.list();
        return snaleList == null ? "oops" : snaleList[ThreadLocalRandom.current().nextInt(0, snaleList.length)];
    }
}
