package seasnail.api;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.Logger;
import seasnail.api.webserver.WebServer;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {

  public static final Logger LOG = JDALogger.getLog("MeteorBot");

  public static JDA JDA;

  public static User SEASNAIL;

  public static Guild SNALELAND;
  public static Role SNALE_MOD;

  public static Guild METEOR;
  public static Role METEOR_DEV;
  public static Role METEOR_MOD;



  public static boolean PROCESS_DISCORD_EVENTS = true;

  public static void main(String[] args) {
    try {
      Config.init();
      WebServer.init();

      JDABuilder.createDefault(Config.DISCORD_TOKEN)
              .enableIntents(GatewayIntent.GUILD_MEMBERS)
              .enableCache(CacheFlag.EMOTE)
              .addEventListeners(new Main())
              .build();

    } catch (LoginException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onReady(@Nonnull ReadyEvent event) {
    JDA = event.getJDA();
    event.getJDA().getPresence().setActivity(Activity.playing("Serving the Snale Kingdom!"));

    SEASNAIL = JDA.getUserById("736954747122352208");

    SNALELAND = JDA.getGuildById("750784696283299911");

    if (SNALELAND != null) {
      SNALE_MOD = SNALELAND.getRoleById("764168582241189909");
    }

//    METEOR = JDA.getGuildById("689197705683140636");
//
//    if (METEOR != null) {
//      METEOR_DEV = METEOR.getRoleById("689198253753106480");
//      METEOR_MOD = METEOR.getRoleById("689197893340758022");
//    }

    LOG.info("SnaleBot has started");
  }

  @Override
  public void onShutdown(@Nonnull ShutdownEvent event) {
    WebServer.close();
  }

  @Override
  public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
    if (!PROCESS_DISCORD_EVENTS || event.getAuthor().isBot() || !event.isFromType(ChannelType.TEXT)) return;

    System.out.println(event.getAuthor() + ": " + event.getMessage());
    if (event.getAuthor().equals(SEASNAIL)) event.getChannel().sendMessage("sex");
  }
}
