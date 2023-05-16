package mask.inc.logintome;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Logintome extends JavaPlugin implements Listener {
    private Map<Player, Long> loginTimes;

    @Override
    public void onEnable() {
        loginTimes = new HashMap<>();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        loginTimes.put(player, System.currentTimeMillis());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (loginTimes.containsKey(player)) {
            long loginTime = loginTimes.get(player);
            long playtime = System.currentTimeMillis() - loginTime;
            // ここでプレイヤーのプレイ時間を保存したり、表示したりする処理を行います
            loginTimes.remove(player);
        }
    }
}
