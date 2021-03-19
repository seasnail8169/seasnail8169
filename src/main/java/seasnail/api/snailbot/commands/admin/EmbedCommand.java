package seasnail.api.snailbot.commands.admin;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import seasnail.api.snailbot.commands.Category;
import seasnail.api.snailbot.commands.Command;
import seasnail.api.snailbot.utils.JDAUtils;

public class EmbedCommand extends Command {

    public EmbedCommand() {
        super(Category.Admin, "embed", "Sends an embed.");
    }

    @Override
    public void run(MessageReceivedEvent event) {
        String[] split = event.getMessage().getContentRaw().split(" ");
        MessageEmbed message;

        if (split.length == 1) {
            message = JDAUtils.embed("I can't send an empty embed!");
        } else {
            split = event.getMessage().getContentRaw().substring(7).split(" \\| ");

            if (split.length == 2) message = JDAUtils.embed(split[0], split[1], false);
            else message = JDAUtils.embed(split[0]);
        }

        event.getMessage().delete().queue();
        event.getMessage().getChannel().sendMessage(message).queue();
    }

}