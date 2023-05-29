package me.bdx.nhplugin.files;
import me.bdx.nhplugin.Nhplugin;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;

import java.io.*;

public class JavaScriptFileManager {

    public JavaScriptFileManager(){
        String[] subfolders = {"events", "commands"};

        String folderPath = Nhplugin.configcontroller.JS_ENTRY_FILE.substring(
                0,Nhplugin.configcontroller.JS_ENTRY_FILE.lastIndexOf('\\'));
        String scriptFolderPath = Nhplugin.configcontroller.JS_ENTRY_FILE.substring(
                0,Nhplugin.configcontroller.JS_ENTRY_FILE.lastIndexOf('\\')) +"\\scripts";
        String fileName = Nhplugin.configcontroller.JS_ENTRY_FILE.substring(
                Nhplugin.configcontroller.JS_ENTRY_FILE.lastIndexOf('\\')+1);
        String commandHandlerFileName = "command.js";
        String eventHandlerFileName = "eventHandler.js";

        try {
            File folder = new File(folderPath);
            File scriptFolder = new File(scriptFolderPath);
            InputStream fileContent = Nhplugin.getInstance().getResource("main.js");

            // Create the folder if it doesn't exist
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Create the folder if it doesn't exist
            if (!scriptFolder.exists()) {
                scriptFolder.mkdirs();
            }

            File file = new File(folder, fileName);

            // Creates all of the subdirectories
            for(String fold: subfolders){
                folder = new File(scriptFolderPath + "\\"+fold);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
            }

            // Create file objects for the script files
            File commandFile = new File(scriptFolder, commandHandlerFileName);
            File eventFile = new File(scriptFolder, eventHandlerFileName);
            File scriptLoadFile = new File(new File(scriptFolderPath+"\\events"), "scriptLoadEvent.js");

            // Create the file
            if (!file.exists()) {
                file.createNewFile();
            }

            if (!commandFile.exists()) {
                commandFile.createNewFile();
            }

            if (!eventFile.exists()) {
                eventFile.createNewFile();
            }

            if(!scriptLoadFile.exists()){
                scriptLoadFile.createNewFile();
            }

            // Write the content into the file using FileOutputStream
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            fileContent.close();

            //Creates the command handler file
            fileContent = Nhplugin.getInstance().getResource("scripts/command.js");
            outputStream = new FileOutputStream(commandFile);
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            fileContent.close();

            // Creates the Event handler file
            fileContent = Nhplugin.getInstance().getResource("scripts/eventHandler.js");
            outputStream = new FileOutputStream(eventFile);
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            fileContent.close();

            // Creates the script reload event file that dynamically registers events to listen for
            fileContent = Nhplugin.getInstance().getResource("scripts/scriptLoadEvent.js");
            outputStream = new FileOutputStream(scriptLoadFile);
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            fileContent.close();

        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }
}
