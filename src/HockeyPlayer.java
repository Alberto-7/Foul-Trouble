/**
 * <p>Title: Hockey Player</p>
 *
 * <p>Description: Represents Hockey Players in a league, contains their names 
 * and how many penalties they've accumulated.</p>
 *
 * @author Alberto Fernandez (N00858494@students.ncc.edu)
 */
public class HockeyPlayer {
	private String name;
	private int penalty;
	/**
	 * parameterized constructor - initializes instance variables
	 * @param nme - a String representing the hockey player's name
	 */
	public HockeyPlayer(String nme) 
	{
		name = nme;
		penalty = 0;
	}
	/**
	 * getName - retrieves hockey player's name
	 * @return - a string representing a hockey player's name
	 */
	public String getName() 
	{
		return this.name;
	}
	/**
	 * getPenalty - retrieves how many penalties a certain player has
	 * @return - an integer representing the # of penalties
	 */
	public int getPenalty() 
	{
		return penalty;
	}
	/**
	 * penalize - increments player's penalty count if it does not exceed the penaly limit 
	 * specified by the parameter.
	 * @param - an integer that sets a limit on the amount of penalties a player can accumulate
	 */
	public void penalize(int maxPen) 
	{
		if(penalty >= maxPen)
		{
			System.out.println(this.name + " has fouled out. \n");
			return;
		}
		this.penalty++;
		System.out.println(this.name + " has been penalized. " + (maxPen - penalty) + " more allowed until fouled out");
		if(penalty >= maxPen)
		{
			System.out.println(this.name + " has fouled out. \n");
			return;
		}
	}
	/**
	 * hashCode2 - this is the polynomial hash code from 10.2 in the textbook
	 * @return an integer referring to the hash code
	 */
	public int hashCode2() 
	{	
		int hashSum = 0;
		int ascii;
		
		for(int i = 0; i < name.length(); i++) {
			ascii = name.charAt(i);
			hashSum += ascii * (Math.pow(2, (name.length() - i - 1)));
		}
		return hashSum;
	}
	/**
	 * hashCode - multiplies the ascii value of a char in a string then multiply by a prime
	 * @return
	 */
	public int hashCode() 
	{
		int hashSum = 7;
		for (int i = 0; i < name.length(); i++) 
		{
		    hashSum = hashSum * 17 + name.charAt(i);
		}
		//Double hash
		//hashSum *= this.hashCode();
		return hashSum;
	}
	/**
	 * toString - returns a string representing the state of a Hockey Player object.
	 */
	public String toString() 
	{
		return name + " - Penalty(ies): " + penalty;
	}
}
