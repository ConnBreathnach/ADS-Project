import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
class TSTNode {
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
		this.middle = null;
	}
	public TSTNode () {
	}
}
class TST{
	private TSTNode root;
	private ArrayList<String> al;
	public TST () {
		root = new TSTNode();
	}
	public void insert () throws FileNotFoundException {
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
        if (stop[position] < r.data) {
            r.left = insert(r.left, stop, position);
        }
        else if (stop[position] > r.data) {
            r.right = insert(r.right, stop, position);
        }
        else
        {
            if (position + 1 < stop.length) {
            	r.middle = insert(r.middle, stop, position + 1);
            }
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
		if (stop == "") {
			return "emtpy string";
		}
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
		if (position < stop.length) {
			return r.data + search(r.middle, stop, position +1);
		}
		return "lol";
		
	}
	public String toString()
    {
        al = new ArrayList<String>();
        traverse(root, "");
        return "\nTernary Search Tree : "+ al;
    }
    /** function to traverse tree **/
    private void traverse(TSTNode r, String str)
    {
        if (r != null)
        {
            traverse(r.left, str);
 
            str = str + r.data;
            if (r.isWord)
                al.add(str);
 
            traverse(r.middle, str);
            str = str.substring(0, str.length() - 1);
 
            traverse(r.right, str);
        }
    }
	
	public static void main(String[] args) throws FileNotFoundException {
		TST lol = new TST();
		lol.insert();
		//System.out.print(lol.toString());
		System.out.print(lol.search("a"));
	}
}

