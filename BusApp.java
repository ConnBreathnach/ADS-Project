import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BusApp {
	static EdgeWeightedDigraph graph;
	static Dijkstra dijkstra;
	static TST tree;


	public static void main(String[] args) throws FileNotFoundException
	{
		graph = new EdgeWeightedDigraph();
		tree = new TST();
		tree.insert();
		MenuScreen();
	}
	
	private static void MenuScreen()
	{
		
		System.out.println("Welcome to the Vancouver bus app! ");
		boolean quitEverything = false;
		while(!quitEverything)
		{
		System.out.println("Please type: \n 1 - To find the shortest path between two bus stops \n 2 - To search for a bus stop by name \n 3 - To search for all trips with a given arrival time \n 'quit' - To quit the application");
		String input = "";
		boolean chosen;
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("\\n|\\r|,");
	
		
		do 
		{	
			
			if((sc.hasNext("1"))|| (sc.hasNext("2"))||(sc.hasNext("3"))|| (sc.hasNext("quit")))
					{
						chosen =false;
						input=sc.next();
	
					}
							
			else {
						System.out.println("Please only enter an integer value, 1, 2, 3 or 'quit'");
						chosen = true;
			
				 }
			
			
		}while(chosen);
		
		switch(input) 
		{
			case "1":
			boolean quit = false;
			while(!quit) 
			{
		
				System.out.println("You selected the shortest path finder" );
				System.out.println("Please enter your starting and ending bus stop ids, separated by a comma (,). ");
				System.out.println("Otherwise type 'quit' to quit or type 'back' to return to the menu. ");
			
				try {
					if(sc.hasNext("back"))
					{
						quit = true;
						break;
					}
					
					else if(sc.hasNext("quit"))
					{
						quitEverything=true;
						break;
						
					}
					
					
					else
					{
						int firstId = sc.nextInt();
						int secondId = sc.nextInt();
						int firstStopId = graph.findVertexById(firstId);
						int secondStopId = graph.findVertexById(secondId);
						dijkstra = new Dijkstra(graph, firstStopId);
						double distance = dijkstra.getDistTo(secondStopId);
						System.out.println(String.format("Shortest path between stop id %d and stop id %d is %f", firstId, secondId, distance));
					}
				}
				catch (InputMismatchException e)
				{
					System.out.println("The input you gave is not valid.");
				}
			
				catch(NullPointerException e)
				{
					System.out.println("One of the Ids you entered does not exist as a valid bus stop. Please try again");
				}
				
		   }
			break;

		
			case "2":
				boolean quit2 = false;
				while(!quit2) 
				{
			System.out.println("You selected the bus stop finder" );
			System.out.println("Please enter a bus stop by name" );
			System.out.println("Otherwise type 'quit' to quit or type 'back' to return to the menu. ");
			
			
			try{
				if(sc.hasNext("back"))
				{
					quit = true;
					break;
				}
				
				else if(sc.hasNext("quit"))
				{
					quitEverything=true;
					break;
					
				}
				
				String stopName = sc.next().toUpperCase();
				
				System.out.println(tree.search(stopName));
			}
			catch(InputMismatchException e){
				System.out.println("Input is invalid. Please try again.");
			}
				}
				break;
		case "3":
			boolean quit3 = false;
			while(!quit3) 
			{
			System.out.println("You selected the trip finder" );
			System.out.println("Please enter your desired arrival in hh:mm:ss format." );
			System.out.println("Otherwise type 'quit' to quit or type 'back' to return to the menu. ");
			try {
				
				if(sc.hasNext("back"))
				{
					quit = true;
					break;
				}
				
				else if(sc.hasNext("quit"))
				{
					quitEverything=true;
					break;
					
				}
				
				
				String stopTime = sc.next();
				SearchTrips.printTrips(stopTime);
			} catch (InputMismatchException e){
				System.out.println("Input is invalid.");
			}
			}
			break;
		case "quit":
			System.out.println("Goodbye..." );
			quitEverything=true;
			break;
		default:
			System.out.println("Error");
			break;
		}
		}
	}
	
}
