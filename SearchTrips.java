import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

 public class SearchTrips {

 	public static void printTrips(String time){
 		ArrayList<Trip> allTrips = search(time);
 		if(allTrips == null)
		{
			System.out.println("Problem with allTrips");
			return;
		}
 		for(Trip current : allTrips){
 			current.printTrip();
 			System.out.println();
		}
	}

	static ArrayList<Trip> search(String inputArrivalTime)
	{
		//read in stop_times.txt file

		try 
		{
			File stopTimes = new File ("stop_times.txt");
			Scanner scStopTimes = new Scanner (stopTimes);
			ArrayList<Trip> arr = new ArrayList<Trip>();
			DateFormat f = new SimpleDateFormat("HH:mm:ss");
			Date max = f.parse("23:59:59"); //max time allowed
			Date inputTime = f.parse(inputArrivalTime); //input arrival time as Date




			if(inputArrivalTime.charAt(0) == '0')
			{
				System.out.println("Do not start your desired arrival time with '0'.");
			}
			else if((inputTime.compareTo(max)==1)||(inputArrivalTime.charAt(0)=='-')) 
			{
				System.out.println("Invalid arrival time has been entered.'23:59:59' is the maximum time input allowed. Please enter your desired arrival time in hh:mm:ss format");
			}

			String input = " "+inputArrivalTime;//allow for space before times in the txt file

			//skip the first line of text
			scStopTimes.nextLine();

			//search for given arrival time
			while (scStopTimes.hasNextLine())
			{
				Trip t = new Trip();
				String line = scStopTimes.nextLine();
				String[] array = line.split(",");

				 if(array[1].equals(input)==true)
					{
						t.id(Integer.parseInt(array[0]));
						t.arrival(array[1]);
						t.departure(array[2]);
						t.stop(Integer.parseInt(array[3]));
						t.sequence(Integer.parseInt(array[4]));
						t.headsign(array[5]);
						t.pickup(Integer.parseInt(array[6]));
						t.dropoff(Integer.parseInt(array[7]));
						if(array.length==9)		//if distance is not missing
						{
							t.distance(Double.parseDouble(array[8]));
						}
						arr.add(t);
					}
			}

			scStopTimes.close();
			if(arr.size()==0 && (inputArrivalTime.charAt(0) != '0')&&(inputTime.compareTo(max)!=1)&&(inputArrivalTime.charAt(0)!='-'))
			{
				System.out.println("There are no trips with the given arrival time of " + inputArrivalTime + ".");
			}
			Collections.sort(arr, new TripComparator());	//sort the trip IDs
			return arr;
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		catch(ParseException e)
		{
			System.out.println("Invalid arrival time has been entered. Please enter your desired arrival time using numbers in hh:mm:ss format.");
		}
		return null;
	}		
 }
		
