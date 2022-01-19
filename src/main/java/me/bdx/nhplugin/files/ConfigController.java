package me.bdx.nhplugin.files;

import me.bdx.nhplugin.Nhplugin;

public class ConfigController {
    public String JS_ENTRY_FILE;

    public ConfigController(){
        JS_ENTRY_FILE = NhpluginConfig.get().getString("fileName");
    }
}
