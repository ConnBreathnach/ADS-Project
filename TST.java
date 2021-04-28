import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class TST{
	private TSTNode root;
	private class TSTNode {
		public TSTNode left;
		public TSTNode right;
		public TSTNode middle;
		public char data;
		public boolean isWord;
		public TSTNode (char data) {
			this.data = data;
			this.left = null;
			this.right = null;
			this.isWord = false;
		}
	}
	public TST () throws FileNotFoundException {
		root = null;
		Scanner fileName = new Scanner (new File("C:\\Users\\Melis\\OneDrive\\Dokumente\\programming Year 2\\TST\\src\\stops.txt"));
		while (fileName.hasNextLine()) {
			String currentLine = fileName.nextLine();
			String[] parts = currentLine.split(",");
			String currentStop = parts[2];
			currentStop = formatString(currentStop);
			insert(root, currentStop.toCharArray(), 0);
		}
		fileName.close();
	}
	public TSTNode insert (TSTNode r, char [] stop, int position) {
		if (r == null) {
            r = new TSTNode(stop[position]);
		}
        if (stop[position] < r.data)
            r.left = insert(r.left, stop, position);
        else if (stop[position] > r.data)
            r.right = insert(r.right, stop, position);
        else
        {
            if (position + 1 < stop.length)
                r.middle = insert(r.middle, stop, position + 1);
            else
                r.isWord = true;
        }
        return r;
	}
	
	private	String formatString(String currentStop) {
			if (currentStop.startsWith("WB")) {
				currentStop = currentStop.replaceFirst("WB", "");
				currentStop = currentStop.concat(" WB");
				return currentStop;
			}
			if (currentStop.startsWith("NB")) {
				currentStop = currentStop.replaceFirst("NB", "");
				currentStop = currentStop.concat(" NB");
				return currentStop;
			} 
			if (currentStop.startsWith("SB")) {
				currentStop = currentStop.replaceFirst("SB", "");
				currentStop = currentStop.concat(" SB");
				return currentStop;
			}
			if (currentStop.startsWith("EB")) {
				currentStop = currentStop.replaceFirst("EB", "");
				currentStop = currentStop.concat(" EB");
				return currentStop;
			}
			if (currentStop.startsWith("Flagstop")) {
				currentStop = currentStop.replaceFirst("Flagstop", "");
				currentStop = currentStop.concat(" Flagstop");
			}
			return currentStop;
		}	
		
	public String search (String stop) {
		return search (root, stop.toCharArray(), 0);
	}
	private String search (TSTNode r, char[] stop, int position) {
		if (r == null) {
			return "0";
		}
		if (stop[position] < r.data) {
			return search(r.left, stop, position);
		}
		if (stop[position] > r.data) {
			return search(r.right, stop, position);
		}
		if (position < stop.length - 1) return search(r.middle, stop, position + 1);
        return r.toString();
		
	}
	public static void main (String[] args) throws FileNotFoundException {
			TST lol = new TST();
			System.out.print(lol.search("HASTINGS ST FS HOLDOM AVE- WB"));
			
		}
	
}

