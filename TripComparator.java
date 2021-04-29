import java.util.Comparator;

public class TripComparator implements Comparator<Trip>{

	@Override
	public int compare(Trip t, Trip t2) {
		return t.tripId - t2.tripId ;
	}

}
