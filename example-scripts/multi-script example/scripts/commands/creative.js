function gamemodeCreative(sender, commandName, args, managerapi, event){
    if(args.length >1){
        return
    }

    event.setCancelled(true)
    if(sender.hasPermission("nh.gamemode.creative") || sender.hasPermission("nh.gamemode.*") || sender.hasPermission("nh.gamemode.all")){
        sender.setGameMode(GameMode.CREATIVE);
        sender.sendMessage(ChatColor.RED + "You are in creative")
    }else{
        sender.sendMessage(ChatColor.RED + permissionErrorMessage)
    }
}