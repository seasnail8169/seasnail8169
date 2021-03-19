package seasnail.api.snailbot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {

    public final Category category;
    public final String name;
    public final String description;

    public Command(Category category, String name, String description) {
        this.category = category;
        this.name = name;
        this.description = description;
    }

    public abstract void run(MessageReceivedEvent event);

}
