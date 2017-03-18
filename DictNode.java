package ce325.hw1;

import java.io.*;

enum wordType { ALLCAPS, FIRSTCAP, NOCAPS }; //enumeration for each word's lowercase/uppercase structure

/*the DictNode class has 3 fields
	1. 26 DictNode kids
	2. a boolean 'isTerminal' field which indicates if we should print the word or not
	3. an enum wordType field which indicates on *how* should we print the word
*/
public class DictNode implements java.io.Serializable {
	
	//Class fields
	public DictNode pointers[]; 
	public boolean isTerminal;
	public wordType capsType = wordType.NOCAPS;
	
	//Class Constructor
	public DictNode(boolean isEND, wordType caps){ 
		pointers = new DictNode[26]; // each time we construct a node, we must create its kids also
		isTerminal = isEND;
		capsType = caps;
	}
	
	public static void main (String [] args) { 

	}
}
