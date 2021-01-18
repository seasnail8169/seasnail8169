package seasnail.api.snailbot.utils;

import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class JDAUtils {
    private static final Color EMBED_COLOR = new Color(255, 247, 145);

    public static EmbedBuilder embedTitle(String title, String format, Object... args) {
        return new EmbedBuilder()
                .setColor(EMBED_COLOR)
                .setTitle(title)
                .setThumbnail("https://cdn.discordapp.com/avatars/736954747122352208/0495e5383ac6b9bffad46a30900dc009.png?size=4096")
                .setDescription(String.format(format, args))
                .setFooter("Created by seasnail8169", "https://cdn.discordapp.com/avatars/736954747122352208/0495e5383ac6b9bffad46a30900dc009.png?size=4096");
    }

    public static EmbedBuilder embed(String format, Object... args) {
        return embedTitle(null, format, args);
    }
}
