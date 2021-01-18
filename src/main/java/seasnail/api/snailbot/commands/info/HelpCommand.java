package seasnail.api.snailbot.commands.info;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import seasnail.api.snailbot.commands.Category;
import seasnail.api.snailbot.commands.Command;
import seasnail.api.snailbot.commands.Commands;
import seasnail.api.snailbot.utils.JDAUtils;

public class HelpCommand extends Command {
    public HelpCommand() {
        super(Category.Info, "Sends you this message.", "help");
    }

    @Override
    public void run(MessageReceivedEvent event) {
        event.getMessage().delete().queue();
        event.getAuthor().openPrivateChannel().complete().sendMessage(JDAUtils.embedTitle("**SnaleBOT Commands**", Commands.HELP).build()).queue();
    }
}
