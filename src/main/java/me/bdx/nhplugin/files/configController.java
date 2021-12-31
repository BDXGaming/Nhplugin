package me.bdx.nhplugin.files;

import me.bdx.nhplugin.Nhplugin;

public class configController {
    public String JS_ENTRY_FILE;

    public configController(){
        JS_ENTRY_FILE = NhpluginConfig.get().getString("fileName");
    }
}
