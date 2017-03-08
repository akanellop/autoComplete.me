enum wordType { ALLCAPS, FIRSTCAP, NOCAPS }; //enum for CAPS info for the words

public class DictNode{
	DictNode newNode[];
	boolean isTerminal;
	wordType capsType=wordType.NOCAPS;

	/*public DictNode(int pos, boolean isEND, wordType caps){
		newNode[pos]=new DictNode[];
		isTerminal=isEND;
		capsType=caps;
	}*/
	public DictNode(){
	}

	public void NodeSetUp(DictNode [] node, boolean isEND, wordType caps){
		newNode=node;
		isTerminal=isEND;
		capsType=caps;
	}
	

	public static void main (String [] args) { 
		DictNode [] temp = new DictNode[26];
		DictNode [] root = new DictNode[26];
		//temp= new DictNode(NULL,false,NOCAPS);
		
		try{
		
		//for(DictNode i : temp){
			temp[].newNode= new DictNode[26];
			temp[].NodeSetUp(root,false,wordType.NOCAPS);
			System.out.println("jfk");
		//}
		
		}
		catch(Exception ex){ //error log
			ex.printStackTrace();
		}
	}
}