package seasnail.api;

public class Config {
    public static String DISCORD_TOKEN;
    public static int PORT;

    public static void init() {
        DISCORD_TOKEN = System.getenv("TOKEN");
        PORT = Integer.parseInt(System.getenv("PORT"));

        System.out.println("TOKEN: " + DISCORD_TOKEN);
        System.out.println("PORT: " + PORT);
    }
}
