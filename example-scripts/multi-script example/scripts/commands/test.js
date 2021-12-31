
function testExport(sender, cmd, args, managerapi, event, nhplugin){
    //sender.sendMessage(ChatColor.RED + "Testing this command implementation") ;
    //sender.sendMessage(ChatColor.AQUA + "This can be used for pretty much any bukkit-based commands!")
    //sender.performCommand("g I am a loser")
    managerapi.getActionbarHelper().sendActionBar(ChatColor.RED + ChatColor.BOLD + "Test"+ChatColor.YELLOW+ ChatColor.BOLD+" Message", sender)
}