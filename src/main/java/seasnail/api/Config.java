package seasnail.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static String DISCORD_TOKEN;
    public static int PORT;

    public static void init() {
        try {
            Properties properties = new Properties();

            InputStream in = new FileInputStream("private-config.properties");
            properties.load(in);
            in.close();

            System.getenv().putIfAbsent("TOKEN", properties.getProperty("TOKEN"));
            System.getenv().putIfAbsent("PORT", "8082");

            DISCORD_TOKEN = System.getenv("TOKEN");
            PORT = Integer.parseInt(System.getenv("PORT"));

            System.out.println("TOKEN: " + DISCORD_TOKEN);
            System.out.println("PORT: " + PORT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
