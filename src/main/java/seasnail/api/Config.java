package seasnail.api;

public class Config {
    public static String DISCORD_TOKEN;
    public static int PORT;
    public static boolean LOCAL;

    public static void init() {
        DISCORD_TOKEN = System.getenv("TOKEN");
        PORT = Integer.parseInt(System.getenv("PORT"));
        LOCAL = PORT == 8082;
    }
}
