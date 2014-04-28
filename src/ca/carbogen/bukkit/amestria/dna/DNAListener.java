package ca.carbogen.bukkit.amestria.dna;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DNAListener implements Listener
{
	private DNA plugin;
	
	public DNAListener(DNA instance)
	{
		this.plugin = instance;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		if(DNA.dnaf.checkNewPlayer(e.getPlayer()))
		{
			plugin.getConfig().set("dna.players."+e.getPlayer().getName(), DNA.dnaf.generateDNA());
			plugin.saveConfig();
		}
	}
}
