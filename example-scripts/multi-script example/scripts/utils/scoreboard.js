function makePlayerScoreboard(player, chat, managerapi){
    var scoreboard =Bukkit.getScoreboardManager()
    var board = scoreboard.getNewScoreboard()

    var ob = board.registerNewObjective("Gold", "")
    ob.setDisplaySlot(DisplaySlot.SIDEBAR)
    ob.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + serverName)

    var playerCount = managerapi.getGlobalPlayers().getOnlinePlayers().length
    playerName = chat.getPlayerPrefix(player) + player.getName()
    var currentServerName = managerapi.getStatusController().server

    var score1 = ob.getScore(ChatColor.WHITE + playerCount);
    var score2 = ob.getScore(ChatColor.AQUA + "Online:");
    var score3 = ob.getScore(ChatColor.WHITE + "   ");
    var score4 = ob.getScore(ChatColor.WHITE + currentServerName);
    var score5 = ob.getScore(ChatColor.AQUA + "Server:");
    var score6 = ob.getScore(ChatColor.AQUA + "  ");
    var score7 = ob.getScore(ChatColor.WHITE + "$"+managerapi.getEssentials().getUser(player).getMoney().toString());
    var score8 = ob.getScore(ChatColor.AQUA + "Current Balance:");
    var score9 = ob.getScore(ChatColor.WHITE + " ");
    var score10 = ob.getScore(ChatColor.WHITE + ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&',playerName));
    var score11 = ob.getScore(ChatColor.AQUA + "User:");
    var score12 = ob.getScore(ChatColor.WHITE + "");
    score1.setScore(1);
    score2.setScore(2);
    score3.setScore(3);
    score4.setScore(4);
    score5.setScore(5);
    score6.setScore(6);
    score7.setScore(7);
    score8.setScore(8);
    score9.setScore(9);
    score10.setScore(10);
    score12.setScore(11);
    player.setScoreboard(board)
}