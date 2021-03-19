package dev.seasnail.api.utils;

import dev.seasnail.api.SnailBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import org.jetbrains.annotations.Nullable;

public class EmbedUtils {

    public static MessageEmbed embed(@Nullable String title, String description, boolean thumbnail) {
        EmbedBuilder embed = new EmbedBuilder().setColor(SnailBot.COLOR).setDescription(description.replace("(n)", "\n"));

        if (title != null) embed.setTitle(title);
        if (thumbnail) embed.setThumbnail(SnailBot.IMAGE_URL);

        return embed.build();
    }

    public static MessageEmbed embed(String body) {
        return embed(null, body, false);
    }

}
