package seasnail.api;

public class Config {

    public static String DISCORD_TOKEN;
    public static int PORT;

    public static boolean isLocal;

    public static void init(String token, int port) {
        DISCORD_TOKEN = token;
        PORT = port;

        isLocal = PORT == 8082;
    }

}
