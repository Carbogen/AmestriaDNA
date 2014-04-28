package ca.carbogen.bukkit.amestria.dna;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DNA extends JavaPlugin
{
	public static DNAFunctions dnaf;
	private DNAExecutor cmddna;
	private DNAListener lisdna;
	@Override
	public void onEnable()
	{
		dnaf = new DNAFunctions(this);
		cmddna = new DNAExecutor(this);
		lisdna = new DNAListener(this);
		this.saveDefaultConfig();
		this.getConfig();
		this.saveConfig();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.lisdna, this);
		getCommand("dna").setExecutor(cmddna);
	}
	
	@Override
	public void onDisable()
	{
		
	}
}
