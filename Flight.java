import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlightMasterPlugin extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        // プラグインの起動ロジック
        getLogger().info("FlightMasterPlugin ver1.0 が有効化されました");
        getCommand("fly").setExecutor(this);  // コマンドをこのクラスで処理するように設定
        getCommand("unfly").setExecutor(this);  // コマンドをこのクラスで処理するように設定
    }

    @Override
    public void onDisable() {
        // プラグインのシャットダウンロジック
        getLogger().info("FlightMasterPlugin ver1.0 が無効化されました");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("fly")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("このコマンドはプレイヤーのみ使用可能です。");
                return true;
            } else if (args.length != 1) {
                sender.sendMessage("プレイヤーを指定してください");
                return false;
            }

            Player targetPlayer = Bukkit.getPlayer(args[0]);

            if (targetPlayer == null) {
                sender.sendMessage("指定されたプレイヤーが見つかりません。");
                return true;
            }

            // 飛行モードを有効にする
            targetPlayer.setAllowFlight(true);
            targetPlayer.sendMessage("飛行モードが有効になりました。");

            return true;
        } else if (command.getName().equalsIgnoreCase("unfly")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("このコマンドはプレイヤーのみ使用可能です。");
                return true;
            } else if (args.length != 1) {
                sender.sendMessage("プレイヤーを指定してください");
                return false;
            }

            Player targetPlayer = Bukkit.getPlayer(args[0]);

            if (targetPlayer == null) {
                sender.sendMessage("指定されたプレイヤーが見つかりません。");
                return true;
            }

            // 飛行モードを無効にする
            targetPlayer.setAllowFlight(false);
            targetPlayer.sendMessage("飛行モードが無効になりました。");

            return true;
        }
        return false;
    }
}
