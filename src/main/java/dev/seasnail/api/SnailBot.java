package dev.seasnail.api;

import dev.seasnail.api.managers.CommandManager;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.awt.*;

public class SnailBot extends ListenerAdapter {

  public static final Logger LOG = LogManager.getLogger();

  public static final Color COLOR = new Color(255, 247, 145);
  public static String IMAGE_URL;

  public static net.dv8tion.jda.api.JDA JDA;
  public static User MINEGAME, SEASNAIL;
  public static SelfUser BOT;

  public static boolean PROCESS_DISCORD_EVENTS = true;

  public static void main(String[] args) {
    try {

      //Config
      if (System.getenv("PORT") == null) Config.initLocal(args[0]);
      else Config.init();

      //API
      WebServer.init();

      //JDA
      JDABuilder.createDefault(Config.DISCORD_TOKEN)
              .enableIntents(GatewayIntent.GUILD_MEMBERS)
              .enableCache(CacheFlag.EMOTE)
              .addEventListeners(new SnailBot())
              .build();

      //Commands
      CommandManager.init();

    } catch (LoginException ignored) {}
  }

  @Override
  public void onReady(@Nonnull ReadyEvent event) {
    JDA = event.getJDA();

    JDA.getPresence().setActivity(Activity.playing("mmm"));
    JDA.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);

    MINEGAME = JDA.retrieveUserById(205708530408357898L).complete();
    SEASNAIL = JDA.retrieveUserById(736954747122352208L).complete();
    BOT = JDA.getSelfUser();

    IMAGE_URL = BOT.getAvatarUrl();

    LOG.info("Snail Bot has started");
  }

  @Override
  public void onShutdown(@Nonnull ShutdownEvent event) {
    WebServer.close();
  }

  @Override
  public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
    if (!PROCESS_DISCORD_EVENTS || event.getAuthor().isBot() || !event.isFromType(ChannelType.TEXT)) return;
    CommandManager.onMessage(event);
  }

  public static boolean isUserAdmin(User user) {
    return user.getIdLong() == MINEGAME.getIdLong() || user.getIdLong() == SEASNAIL.getIdLong();
  }

}
