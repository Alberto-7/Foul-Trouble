import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
* <p>Title: Foul Trouble</p>
*
* <p>Description: This application will read from a file that contains
* player's names, it will also take input from the console to be able to penalize
* the specified hockey player.</p>
*
* @author Alberto Fernandez (N00858494@students.ncc.edu)
*/
public class Project3 {

	public static void main(String[] args) throws FileNotFoundException {

		HashTable<String,HockeyPlayer> table = new HashTable<String,HockeyPlayer>();	//hashtable
		File file = new File("players.txt");	//player file
		Scanner scnr = new Scanner(file);	
		String name = "";
		HockeyPlayer player;
		int maxPen = 3;	//default penalties

		if(args.length > 0) //if using command line args for max penaltys
		{
			
			maxPen = Integer.parseInt(args[0]);
			if(maxPen < 0) {
				maxPen = 3;
			}
		}

		while(scnr.hasNextLine() && scnr.hasNext()) {	//read from file and add to table
			name = scnr.nextLine();
			player = new HockeyPlayer(name);
			table.add(name, player);
		}

		boolean end = false;	//for termination

		while(!end)
		{
			Scanner scn = new Scanner(System.in);	//read from console
			String console = scn.nextLine();

			if(console.equals("q") || console.equals("quit"))	//end program
			{
				end = true;
			}
			else if(console.equals("l") || console.equals("list"))	//display table
			{
				System.out.println(table.toString());
			}

			else if(table.get(console) == null) 	//not a player name
			{
				System.out.println("Error: Please try again.");
			}
			else									//player exists, penalize
			{
				table.get(console).penalize(maxPen);
				System.out.println(table.get(console).toString());
			}
		}

		System.out.println(table.toString());
	}

}
