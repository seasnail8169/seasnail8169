package dev.seasnail.api;

public class Config {

    public static String DISCORD_TOKEN;
    public static int PORT;

    public static boolean isLocal;

    public static void initLocal(String token) {
        DISCORD_TOKEN = token;
        PORT = 8082;
        isLocal = true;
    }

    public static void init() {
        DISCORD_TOKEN = System.getenv("TOKEN");
        PORT = Integer.parseInt(System.getenv("PORT"));
        isLocal = PORT == 8082;
    }

}
