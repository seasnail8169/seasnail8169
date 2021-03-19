package seasnail.api.snailbot.commands.info;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import seasnail.api.snailbot.commands.Category;
import seasnail.api.snailbot.commands.Command;
import seasnail.api.snailbot.commands.Commands;
import seasnail.api.snailbot.utils.JDAUtils;

public class HelpCommand extends Command {

    public HelpCommand() {
        super(Category.Info, "help", "Sends you this message.");
    }

    @Override
    public void run(MessageReceivedEvent event) {
        event.getMessage().delete().queue();
        event.getAuthor().openPrivateChannel().complete().sendMessage(JDAUtils.embed("**Commands**", Commands.HELP, true)).queue();
    }

}
