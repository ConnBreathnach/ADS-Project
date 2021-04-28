public class Trip {

	int tripId;
	String arrivalTime;
	String departureTime ;
	int stopId;
	int stopSequence;
	int pickupType;
	int dropoffType;
	double distTravelled;
	String headsign;
	
	public Trip()
	{
	}

	public void  id(int tripId)
	{
		this.tripId=tripId;
	}
	
	public void arrival(String arrival)
	{
		arrivalTime=arrival;
	}
	
	public void departure(String departure)
	{
		departureTime=departure;
	}
	
	public void stop(int stop)
	{
		stopId=stop;
	}
	
	public void sequence(int sequence)
	{
		stopSequence = sequence;
	}
	
	public void headsign(String sign)
	{
		headsign=sign;
	}
	
	public void pickup(int pickup)
	{
		pickupType=pickup;
	}
	
	
	public void dropoff(int dropoff)
	{
		dropoffType=dropoff;
	}
	
	public void distance(double distance)
	{
		distTravelled=distance;
	}
	
	 public void printTrip() 
	 {
	      System.out.println("Trip ID: "+ tripId );
	      System.out.println("Arrival Time:" + arrivalTime );
	      System.out.println("Departure Time:" + departureTime );
	      System.out.println("Stop ID: " + stopId);
	      System.out.println("Stop Sequence: " + stopSequence);
	      System.out.println("Head-sign: "+ headsign);
	      System.out.println("Pick-up Type: " + pickupType);
	      System.out.println("Drop-off Type: " + dropoffType);
	      System.out.println("Distance Travelled: " + distTravelled);
	   }
}
