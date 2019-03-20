package Server;

import java.net.DatagramPacket;

public class StatisticData {
	
	private int minLength;
	private int maxLength;
	
	private int minWords;
	private int maxWords;
	
	private int lastWordCount;
	
	public StatisticData()
	{
		this.maxLength = 0;
		this.minLength = 2147483647;
		this.maxWords = 0;
		this.minWords = 2147483647;
		this.lastWordCount = 0;
	}
	
	
	/**
	 * Updates min and max values of received packets
	 * @param dp
	 */
	public void update(DatagramPacket dp)
	{
		//extract String from Datapacket
		String str = new String(dp.getData(), 0, dp.getLength());
		
		//Set min/max length values
		this.minLength = (str.length() < this.minLength)? str.length() : this.minLength ;
		this.maxLength = (str.length() > this.maxLength)? str.length() : this.maxLength ;
		
		String[] words = str.split("\\s+");
		
		//Set min/max word count values
		this.minWords = (words.length < this.minWords)? words.length : this.minWords ;
		this.maxWords = (words.length > this.maxWords)? words.length : this.maxWords ;
		
		this.lastWordCount = words.length;
	}


	public int getMinLength() {
		return minLength;
	}


	public int getMaxLength() {
		return maxLength;
	}


	public int getMinWords() {
		return minWords;
	}


	public int getMaxWords() {
		return maxWords;
	}


	public int getLastWordCount() {
		return lastWordCount;
	}

}
