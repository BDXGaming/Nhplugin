package me.bdx.nhplugin.files;

import me.bdx.nhplugin.Nhplugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

public class NhpluginConfig {
    private static FileConfiguration customfile;

    //Finds or generates config file thing
    public static void setup(){

        Nhplugin.getInstance().saveDefaultConfig();

        customfile = Nhplugin.getInstance().getConfig();
    }

    public static FileConfiguration get(){
        return customfile;
    }

    public static void reload(){

        File folder = new File(String.valueOf(Bukkit.getServer().getPluginManager().
                getPlugin("Managerapi").getDataFolder()));
        File file = new File(folder, "config.yml");
        customfile = YamlConfiguration.loadConfiguration(file);
    }
}
