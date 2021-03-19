package dev.seasnail.api.managers;

import dev.seasnail.api.commands.Category;
import dev.seasnail.api.commands.Command;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import dev.seasnail.api.SnailBot;
import dev.seasnail.api.commands.admin.EmbedCommand;
import dev.seasnail.api.commands.admin.StatusCommand;
import dev.seasnail.api.commands.info.HelpCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
public class CommandManager {

    private static final Map<Class<? extends Command>, Command> commands = new HashMap<>();
    private static final Map<Category, List<Command>> categories = new HashMap<>();

    public static String HELP;

    public static void init() {
        add(new HelpCommand());
        add(new EmbedCommand());
        add(new StatusCommand());

        SnailBot.LOG.info("Loaded {} commands", commands.size());

        generateHelp();
    }

    public static Command get(String name) {
        for (Command command : commands.values()) {
            if (command.name.equalsIgnoreCase(name)) return command;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Command> T get(Class<T> klass) {
        return (T) commands.get(klass);
    }

    public static void add(Command command) {
        commands.put(command.getClass(), command);
        categories.computeIfAbsent(command.category, category -> new ArrayList<>()).add(command);
    }

    public static void onMessage(MessageReceivedEvent event) {
        String str = event.getMessage().getContentRaw();

        if (str.startsWith("%")) {
            String name = str.substring(1).split(" ")[0];

            Command command = get(name);

            if (command == null) return;

            if (command.category != Category.Admin || SnailBot.isUserAdmin(event.getAuthor())) command.run(event);
        }
    }

    private static void generateHelp() {
        StringBuilder sb = new StringBuilder();

        int i = 0;
        for (Category category : Category.values()) {
            if (i > 0) sb.append("\n");
            sb.append("\n**").append(category).append(":**");

            List<Command> commands = categories.get(category);
            if (commands != null) {
                for (Command command : commands) {
                    sb.append("\n - *").append(command.name).append(":* ").append(command.description);
                }
            }

            i++;
        }

        HELP = sb.toString();
    }

}
