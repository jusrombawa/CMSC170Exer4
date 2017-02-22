//ROMBAWA, JUSTIN AARON S.
//CMSC 170 U-3L

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class BagOfWords
{
	//array for words (with duplicates)
    private String [] spam;
    private String [] ham;
    
    //hash maps for words with their respective counts
    private HashMap<String,Integer> spamMap;
    private HashMap<String,Integer> hamMap;
    
    private int hamTotal;
    private int hamDictSize; //dict size lol
    
    private int spamTotal;
    private int spamDictSize; //ilang taon na ko sa 170 but this never gets old
    
    //file i/o stuff
    private BufferedReader br;
    private FileReader fr;
    
	//filenames
    private String spamInput;
    private String hamInput;
    
	//temp variables
    private String tempInput; //used to compile all messages in spam/ham into one string for splitting
    private String currLine; //temp String for current line in file read
	private Integer tempInt; //holder for hashmap value to be modified
	private Object[] tempArray; //for... stuff. mamaya na to.
    
    public BagOfWords()
    {
    	//some init, nothing special
    	spamMap = new HashMap<String,Integer>();
    	hamMap = new HashMap<String,Integer>();
        spamInput = "spam.txt";
        hamInput = "ham.txt";
        tempInput = new String();
        
        //read spam
		try
		{
			fr = new FileReader(spamInput);
			br = new BufferedReader(fr);
			while((currLine = br.readLine()) != null)
			{
				//if empty, tempInput is currLine
				if(tempInput.isEmpty())
					tempInput = currLine ;
				//else append with space in between (for tokenizing if that's even a word)
				else
					tempInput = tempInput + " " + currLine;
			}
    	}
    	
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}

		finally
		{
			try
			{
				if(br != null)
					br.close();
				if(fr != null)
					fr.close();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}

		//tokenize spam
		spam = tempInput.split(" ");
		//convert to lower case and remove non-alphanumeric characters (if any)
		for(int i=0;i<spam.length;i++)
		{
			spam[i] = spam[i].toLowerCase();
			spam[i] = spam[i].replaceAll("[^a-zA-Z0-9]", "");

		}
		
		//place spam words on hash map, along with their count
		//i mean i could join this with the other loop, but for readability's sake...
		for(int i=0;i<spam.length;i++)
		{
			//if already in map, take value, add 1, replace key-value
			if(spamMap.containsKey(spam[i]))
			{
				tempInt = spamMap.get(spam[i]);
				tempInt++;
				spamMap.put(spam[i],tempInt);
				tempInt = 0; //clear just in case
			}
			//else just add to hash map
			else
			{
				spamMap.put(spam[i],1);
			}
		}
		
		//clear temp variables
		tempInput = new String();
		tempInt = 0;
		
		//same thing but this time for ham
		
		//read ham
		try
		{
			fr = new FileReader(hamInput);
			br = new BufferedReader(fr);
			while((currLine = br.readLine()) != null)
			{
				if(tempInput.isEmpty())
					tempInput = currLine;
				else
					tempInput = tempInput + " " + currLine;
			}
    	}
    	
    	catch(IOException e)
    	{
    		e.printStackTrace();
    	}

		finally
		{
			try
			{
				if(br != null)
					br.close();
				if(fr != null)
					fr.close();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}


		//tokenize ham
		ham = tempInput.split(" ");
		//convert to lower case and remove non-alphanumeric characters (if any)
		for(int i=0;i<ham.length;i++)
		{
			ham[i] = ham[i].toLowerCase();
			ham[i] = ham[i].replaceAll("[^a-zA-Z0-9]", "");

		}
		
		
		//place ham words on hash map, along with their count
		//i mean i could join this with the other loop, but for readability's sake...
		for(int i=0;i<ham.length;i++)
		{	
			//if already in map, take value, add 1, replace key-value
			if(hamMap.containsKey(ham[i]))
			{
				tempInt = hamMap.get(ham[i]);
				tempInt++;
				//System.out.println(tempInt);
				hamMap.put(ham[i],tempInt);
				tempInt = 0; //clear just in case
			}
			//else just add to hash map
			else
			{
				hamMap.put(ham[i],1);
			}
		}
		/*
		//test
		Object[] tempArray = hamMap.keySet().toArray(); //okay... so this is a thing. 
		//object array instead of string array that i expected.
		//it prints fine, but... why?
		//so it kinda works???
		for(int i=0;i<hamMap.size();i++)
		{
			System.out.println(tempArray[i] + " " + hamMap.get(tempArray[i]));
		}*/
		
		//temporarily set to 0
		spamTotal = hamTotal = 0;
		
		//total words and dict sizes
		spamDictSize = spamMap.size();
		hamDictSize = hamMap.size();
		
		//hold keys in... object array? can't typecast for some reason.
		tempArray = spamMap.keySet().toArray(); //okay... so this is a thing. 
		
		for(int i=0;i<spamMap.size();i++)
		{
			spamTotal += spamMap.get(tempArray[i]);
		}
		

		tempArray = hamMap.keySet().toArray();
		for(int i=0;i<hamMap.size();i++)
		{
			hamTotal += hamMap.get((String) tempArray[i]);
		}
    }
    
    //getters
    public HashMap getSpam()
    {
    	return spamMap;
    }
    
    public HashMap getham()
    {
    	return hamMap;
    }
    
    public int getSpamTotal()
    {
		return spamTotal;
    }
    public int getHamTotal()
    {
    	return hamTotal;
    }
    
    public int getSpamDictSize()
    {
    	return spamDictSize;
    }
    
    public int getHamDictSize()
    {
    	return hamDictSize();
    }
}
