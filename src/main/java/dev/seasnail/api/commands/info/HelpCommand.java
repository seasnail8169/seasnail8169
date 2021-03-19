package dev.seasnail.api.commands.info;

import dev.seasnail.api.managers.CommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import dev.seasnail.api.commands.Category;
import dev.seasnail.api.commands.Command;
import dev.seasnail.api.utils.JDAUtils;

public class HelpCommand extends Command {

    public HelpCommand() {
        super(Category.Info, "help", "Sends you this message.");
    }

    @Override
    public void run(MessageReceivedEvent event) {
        event.getMessage().delete().queue();
        event.getAuthor().openPrivateChannel().complete().sendMessage(JDAUtils.embed("**Commands**", CommandManager.HELP, true)).queue();
    }

}
