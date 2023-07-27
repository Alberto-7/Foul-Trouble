import java.util.ArrayList;
/**
 * <p>Title: HashTable</p>
 *
 * <p>Description: This class is an implementation of a hash table using seperate chaining.</p>
 *
 * @author Alberto Fernandez (N00858494@students.ncc.edu)
 */
public class HashTable<K,V>{
	private int numBuckets;		//
	private int size;			//# of players
	private ArrayList<Node<K,V>> bucket;	//linked list for collisions
	/**
	 * default constructor - initializes all instance variables
	 */
	public HashTable() 
	{
		numBuckets = 11;
		bucket = new ArrayList<>(numBuckets); 
		size = 0;

		for(int i = 0; i < numBuckets; i++) //creates a bucket array
		{
			bucket.add(null);
		}	
	}
	/**
	 * size - Determines how many hockey players are in the table
	 * @return - an integer representing total players
	 */
	public int size()
	{
		return size;
	}
	/**
	 * isEmpty - determines if table is empty
	 * @return - true if size == 0, false otherwise
	 */
	public boolean isEmpty()
	{
		return size == 0;
	}
	/**
	 * get - Takes a hockey player's name as the key, hashes it to find it's index and return that name's value
	 * @param key - K, in this project it represents a string
	 * @return - A HockeyPlayer object
	 */
	public V get(K key)
	{
		int location = hashValue(key);
		Node<K, V> head = bucket.get(location);
		while(head != null)
		{
			if(head.key.equals(key))
			{
				return head.value;
			}
			head = head.next;
		}
		return null;	
	}
	/**
	 * hashValue - takes a string of a hockey player object, hashes then compresses that string
	 * @param name - a string representing the hockey player's name
	 * @return - an integer representing the bucket that hockey player is stored at.
	 */
	public int hashValue(K name) 
	{
		HockeyPlayer value = new HockeyPlayer((String)name);
		int hashValue = value.hashCode2();
		if(hashValue < 0) {
			hashValue *= -1;
		}
		int index = hashValue % numBuckets;

		return index;

	}
	/**
	 * add - Places a hockey player object into the hash table, expands when load factor of .7 is exceeded.
	 * @param key - K representing a player's name
	 * @param value - V representing a hockey player object
	 */
	public void add(K key, V value) 
	{

		int location = this.hashValue(key);
		Node<K,V> head = bucket.get(location);
		Node<K,V> adding = new Node<>(key,value);

		if(head == null) {	//bucket is empty
			bucket.set(location, adding);
			size++;
		}
		else {
			while(head != null) 
			{
				if(head.key.equals(key))	//ovewriting if adding same person
				{
					head.value = value;
					break;
				}
				head = head.next;
			}
			if(head == null)
			{
				head = bucket.get(location);
				adding.next = head;
				bucket.set(location, adding);
				size++;
			}
		}

		if((1.0 * size) / numBuckets > 0.9) 
		{
			this.expandCapacity();			
		}
	}
	/**
	 * expandCapacity - expands hash table when threshold of .7 is exceeded.
	 */
	public void expandCapacity() 
	{
		ArrayList<Node<K, V>> temp = bucket;
		bucket = new ArrayList<>();
		numBuckets = 2 * numBuckets;
		if(!isPrime(numBuckets)) 
		{
			numBuckets = nextPrime(numBuckets);
		}
		for(int i = 0; i < numBuckets; i++)
		{
			bucket.add(null);
		}
		size = 0;
		for(Node<K, V> headNode:temp)
		{
			while(headNode != null)
			{
				add(headNode.key, headNode.value);
				headNode = headNode.next;
			}
		}
	}
	/**
	 * toString - returns a string representing the state of the Hash table
	 */
	public String toString() {
		String str = "";

		for(int i = 0; i < numBuckets; i++) {
			if(bucket.get(i) == null) 
			{
				str += "Bucket: " + i + "\n";
			}
			else 
			{
				String players = "";
				Node<K,V> temp = bucket.get(i);
				while(temp != null) {
					players += temp.value.toString() + "; ";
					temp = temp.next;
				}
				str += "Bucket: " + i + " Players: "+ players + "\n";
			}
		}
		return str;
	}
	//BELOW CODE FOR PRIME NUMBER TAKEN FROM https://www.geeksforgeeks.org/program-to-find-the-next-prime-number/
	
//Java implementation of the approach
	// Function that returns true if n
	// is prime else returns false
	static boolean isPrime(int n)
	{
		// Corner cases
		if (n <= 1) return false;
		if (n <= 3) return true;
		
		// This is checked so that we can skip
		// middle five numbers in below loop
		if (n % 2 == 0 || n % 3 == 0) return false;
		
		for (int i = 5; i * i <= n; i = i + 6)
			if (n % i == 0 || n % (i + 2) == 0)
			return false;
		
		return true;
	}
	
	// Function to return the smallest
	// prime number greater than N
	static int nextPrime(int N)
	{
	
		// Base case
		if (N <= 1)
			return 2;
	
		int prime = N;
		boolean found = false;
	
		// Loop continuously until isPrime returns
		// true for a number greater than n
		while (!found)
		{
			prime++;
	
			if (isPrime(prime))
				found = true;
		}
	
		return prime;
	}
	}
//This code is contributed by AnkitRai01


