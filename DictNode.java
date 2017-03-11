enum wordType { ALLCAPS, FIRSTCAP, NOCAPS }; //enum for CAPS info for the words

public class DictNode{
	public DictNode pointers[];// = new DictNode[26];
	public boolean isTerminal;
	public wordType capsType=wordType.NOCAPS;

	public DictNode(boolean isEND, wordType caps){ //constructor for object for DictNode class
		pointers = new DictNode[26];//creates an array of DictNodes to be the first field of "root"oject (first 26 pointers)
		isTerminal=isEND;
		capsType=caps;
	}
	

	public static void main (String [] args) { 
		String temp = "pww";
	
		//for (int i = 0 ; i<4 ; i++) {
		System.out.println(temp.length());
	//	}
			
	}
}

//not necessary code, use for checking !!WORKS
	
		/*DictNode root = new DictNode(false,wordType.NOCAPS); // creates root of the tree
		DictNode next = new DictNode(false,wordType.NOCAPS);
		DictNode next2 = new DictNode(false,wordType.NOCAPS);
		
		try{
				
			root.pointers[0] = new DictNode(false,wordType.NOCAPS);
			//each DictNode should be called with its instructor
			
			next=root.pointers[0];
			next2= next.pointers[25];
			
			
			if ( next == null) {
				System.out.println("\n\nnext = null\n\n");
			}
			
			if ( next2 == null) {
				System.out.println("\n\nnext2 = null");
			}
			

		}
		catch(Exception ex){ //error log
			ex.printStackTrace();
		}*/