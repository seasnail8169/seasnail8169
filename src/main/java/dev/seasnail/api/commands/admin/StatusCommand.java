package dev.seasnail.api.commands.admin;

import dev.seasnail.api.SnailBot;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import dev.seasnail.api.commands.Category;
import dev.seasnail.api.commands.Command;
import dev.seasnail.api.utils.JDAUtils;

public class StatusCommand extends Command {

    public StatusCommand() {
        super(Category.Admin, "status", "Sets the bots status.");
    }

    @Override
    public void run(MessageReceivedEvent event) {
        String[] split = event.getMessage().getContentRaw().split(" ");
        Activity activity = null;

        if (split.length == 1) {
            event.getMessage().getChannel().sendMessage(JDAUtils.embed(String.format("Current status is: `%s`", SnailBot.JDA.getPresence().getActivity().getName()))).queue();
            return;
        }

        String action = event.getMessage().getContentRaw().substring(8);

        if (split.length > 2) {
            if (!split[1].equals("playing") && !split[1].equals("watching") && !split[1].equals("listening")) {
                activity = Activity.playing(action);
            } else {
                switch (split[1]) {
                    case "watching":    activity = Activity.watching(action.substring(9)); break;
                    case "listening":   activity = Activity.listening(action.substring(10)); break;
                    case "playing":     activity = Activity.playing(action.substring(8)); break;
                }
            }
        }
        else if (split.length == 2) activity = Activity.playing(action);

        if (activity == null) activity = Activity.watching("snails");

        SnailBot.JDA.getPresence().setActivity(activity);

        event.getMessage().delete().queue();
        event.getMessage().getChannel().sendMessage(JDAUtils.embed(String.format("Updated status to: `%s`", action))).queue();
    }

}
