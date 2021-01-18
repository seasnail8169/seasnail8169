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
            ProcessBuilder processBuilder = new ProcessBuilder();
            Properties properties = new Properties();

            InputStream in = new FileInputStream("private-config.properties");
            properties.load(in);
            in.close();

            processBuilder.environment().putIfAbsent("TOKEN", properties.getProperty("TOKEN"));
            processBuilder.environment().putIfAbsent("PORT", "8082");

            DISCORD_TOKEN = processBuilder.environment().get("TOKEN");
            PORT = Integer.parseInt(processBuilder.environment().get("PORT"));

            System.out.println("TOKEN: " + DISCORD_TOKEN);
            System.out.println("PORT: " + PORT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
