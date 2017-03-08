enum wordType { ALLCAPS, FIRSTCAP, NOCAPS }; //enum for CAPS info for the words

public class DictNode{
	public DictNode pointers[];
	public boolean isTerminal;
	public wordType capsType=wordType.NOCAPS;

	public DictNode(boolean isEND, wordType caps){ //constructor for object for DictNode class
		DictNode [] pointers = new DictNode[26];//creates an array of DictNodes to be the first field of "root"oject (first 26 pointers)
		isTerminal=isEND;
		capsType=caps;
	}


	public static void main (String [] args) { 
		DictNode root = new DictNode(false,wordType.NOCAPS); // creates root of the tree
		DictNode temp = new DictNode(false,wordType.NOCAPS);
		
		try{
			
			
		
			if(root.pointers == null){
				System.out.println("jfk");
			}
			if(root == null){
				System.out.println("jfk22");
			}
		
		}
		catch(Exception ex){ //error log
			ex.printStackTrace();
		}
	}
}