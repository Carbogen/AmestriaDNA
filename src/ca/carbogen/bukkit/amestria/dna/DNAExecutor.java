package ca.carbogen.bukkit.amestria.dna;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DNAExecutor implements CommandExecutor
{

	@SuppressWarnings("unused")
	private DNA plugin;
	
	public DNAExecutor(DNA instance)
	{
		this.plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(label.equalsIgnoreCase("dna"))
		{
			if(sender.hasPermission("amestria.dna.use"))
			{
				if(args.length == 1)
				{
					if(args[0].equalsIgnoreCase("get"))
					{
						sender.sendMessage(DNA.dnaf.getPlayerDNA((Player) sender));
					}
					
					else if(args[0].equalsIgnoreCase("race"))
					{
						sender.sendMessage(DNA.dnaf.compareDNA(DNA.dnaf.getPlayerDNA((Player) sender)));
					}
				}
			}
		}
		return false;
	}

}
