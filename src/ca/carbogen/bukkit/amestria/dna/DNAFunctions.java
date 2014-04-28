package ca.carbogen.bukkit.amestria.dna;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.bukkit.entity.Player;

public class DNAFunctions 
{
	private DNA plugin;
	
	public DNAFunctions(DNA instance)
	{
		this.plugin = instance;
	}
	
	public ArrayList<String> dnaToArray(String dna)
	{
		ArrayList<String> list = new ArrayList<String>();
		
		for(int i = 0; i < dna.length(); i+=4)
		{
			String codeStrand = "";
			codeStrand += dna.charAt(i);
			codeStrand += dna.charAt(i+1);
			codeStrand += dna.charAt(i+2);
			list.add(codeStrand);
		}
		
		return list;
	}
	
	public boolean checkNewPlayer(Player player)
	{
		if(!plugin.getConfig().contains("dna.players."+player.getName()))
			return true;
		return false;
	}
	
	public String getPlayerDNA(Player player)
	{
		return plugin.getConfig().getString("dna.players."+player.getName());
	}
	
	@Deprecated
	public String cmpDNA(String dna)
	{
		String race = "human";
		int index = 0;
		int humanPts = 0;
		int vampirePts = 0;
		
		dna = dna.replace(" ", "");
		String human = DNARecords.HUMAN_DNA.replace(" ", "");
		//String vampire = DNARecords.vampireDNA.replace(" ", "");
		
		for(char c : dna.toCharArray())
		{
			if(c == human.charAt(index))
				humanPts++;
		//	if(c == vampire.charAt(index))
				vampirePts++;
			index++;
		}
		
		plugin.getLogger().info("Human scored "+humanPts+" points for DNA String "+dna+".");
		plugin.getLogger().info("Vampire scored "+vampirePts+" points for DNA String "+dna+".");
		
		plugin.getLogger().info("Ratio is "+getRatio(humanPts, vampirePts));
		
		if(humanPts > vampirePts)
			return "human";
		
		else if(vampirePts > humanPts)
			return "vampire";
		
		else if(vampirePts == humanPts)
			return "human";
		
		return race;
	}
	
	public String compareDNA(String dna)
	{
		ArrayList<String> aldna = dnaToArray(dna);
		ArrayList<String> human = dnaToArray(DNARecords.HUMAN_DNA);
		ArrayList<String> pureblood = dnaToArray(DNARecords.PUREBLOOD_DNA);
		ArrayList<String> noble = dnaToArray(DNARecords.NOBLE_DNA);
		ArrayList<String> common = dnaToArray(DNARecords.COMMON_DNA);
		ArrayList<String> exhuman = dnaToArray(DNARecords.EXHUMAN_DNA);
		ArrayList<String> level_e = dnaToArray(DNARecords.LEVELE_DNA);
		ArrayList<String> elf = dnaToArray(DNARecords.ELF_DNA);
		ArrayList<String> wraith = dnaToArray(DNARecords.WRAITH_DNA);
		ArrayList<Integer> scores = new ArrayList<Integer>();
		
		int humanPts = 0;
		int purebloodPts = 0;
		int noblePts = 0;
		int commonPts = 0;
		int exhumanPts = 0;
		int levelEPts = 0;
		int elfPts = 0;
		int wraithPts = 0;
		
		int index = 0;
		
		for(String s : aldna)
		{
			
			if(s.equalsIgnoreCase(human.get(index)))
				humanPts++;
			else if(s.equalsIgnoreCase(pureblood.get(index)))
				purebloodPts++;
			else if(s.equalsIgnoreCase(noble.get(index)))
				noblePts++;
			else if(s.equalsIgnoreCase(common.get(index)))
				commonPts++;
			else if(s.equalsIgnoreCase(exhuman.get(index)))
				exhumanPts++;
			else if(s.equalsIgnoreCase(level_e.get(index)))
				levelEPts++;
			else if(s.equalsIgnoreCase(elf.get(index)))
				elfPts++;
			else if(s.equalsIgnoreCase(wraith.get(index)))
				wraithPts++;
			
			index++;
		}
		
		scores.add(humanPts);
		scores.add(purebloodPts);
		scores.add(noblePts);
		scores.add(commonPts);
		scores.add(exhumanPts);
		scores.add(levelEPts);
		scores.add(elfPts);
		scores.add(wraithPts);
		
		if(Collections.max(scores) == humanPts)
			return "human";
		else if(Collections.max(scores) == purebloodPts)
			return "pureblood vampire";
		else if(Collections.max(scores) == noblePts)
			return "noble vampire";
		else if(Collections.max(scores) == commonPts)
			return "common vampire";
		else if(Collections.max(scores) == exhumanPts)
			return "ex-human vampire";
		else if(Collections.max(scores) == levelEPts)
			return "level e";
		else if(Collections.max(scores) == elfPts)
			return "elf";
		else if(Collections.max(scores) == wraithPts)
			return "wraith";
		
		return "";
	}
	
	public int getCommonD(int a, int b)
	{
		if(b == 0)
			return a;
		
		else
			return 	getCommonD(b, a % b);
	}
	
	public String getRatio(int a, int b)
	{
		final int gcd = getCommonD(a, b);
		return (a > b)? (a/gcd + " : "+ b/gcd) : (b/gcd + " : "+ a/gcd);
	}
	
	public String generateDNA()
	{
		StringBuilder finalDNA = new StringBuilder();
		Random randy = new Random();
				
		for(int i = 0; i < 12; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				int x = randy.nextInt(4);
				char letter = 'X';
				
				if(x == 0)
					letter = 'T';
				
				else if(x == 1)
					letter = 'C';
				
				else if(x == 2)
					letter = 'A';
				
				else if(x == 3)
					letter = 'G';
				
				finalDNA.append(letter);
			}
			
			finalDNA.append(" ");
		}
		
		return finalDNA.toString();
	}
}
