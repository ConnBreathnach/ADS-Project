import java.util.Scanner;

public class BusApp {

	public static void main(String[] args) 
	{
		MenuScreen();
		
		
		
	}
	
	private static void MenuScreen() 
	{
		System.out.println("Welcome to the Vancouver bus app! Please type: \n 1 - To find the shortest path between two bus stops \n 2 - To search for a bus stop by name \n 3 - To search for all trips with a given arrival time \n 4 - To quit the application");
		String input;
		boolean chosen;
		do 
		{
			Scanner sc = new Scanner(System.in);
			input = sc.next();
			try 
			{
				Integer.parseInt(input);
				if(Integer.parseInt(input)>4)
				{
					System.out.println("Please input a smaller number!"); 
					chosen = true;
				}
				else				
				{
					chosen = false;
				}
				
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please only enter an integer value, 1, 2, 3 or 4");
				chosen = true;
			}
		}while(chosen);
		
		switch(input) 
		{
		case "1":
			System.out.println("You selected the shortest path finder" );
			break;
		case "2":
			System.out.println("You selected the bus stop finder" );
			break;
		case "3":
			System.out.println("You selected the trip finder" );
			break;
		case "4":
			System.out.println("Goodbye..." );
			break;
		default:
			System.out.println("Error");
			break;
		}
	}
	
	
	
	
}
