package dev.seasnail.api.commands.admin;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import dev.seasnail.api.commands.Category;
import dev.seasnail.api.commands.Command;
import dev.seasnail.api.utils.EmbedUtils;

public class EmbedCommand extends Command {

    public EmbedCommand() {
        super(Category.Admin, "embed", "Sends an embed.");
    }

    @Override
    public void run(MessageReceivedEvent event) {
        String[] split = event.getMessage().getContentRaw().split(" ");
        MessageEmbed message;

        if (split.length == 1) {
            message = EmbedUtils.embed("I can't send an empty embed!");
        } else {
            split = event.getMessage().getContentRaw().substring(7).split(" \\| ");

            if (split.length == 2) message = EmbedUtils.embed(split[0], split[1], false);
            else message = EmbedUtils.embed(split[0]);
        }

        event.getMessage().delete().queue();
        event.getMessage().getChannel().sendMessage(message).queue();
    }

}