enum wordType { ALLCAPS, FIRSTCAP, NOCAPS }; //enum for CAPS info for the words

public class DictNode{
	DictNode newNode[];
	boolean isTerminal;
	wordType capsType=wordType.NOCAPS;

	//possible different constructor
	
	/*public DictNode(int pos, boolean isEND, wordType caps){
		newNode[pos]=new DictNode[];
		isTerminal=isEND;
		capsType=caps;
	}*/

		public DictNode(DictNode [] node, boolean isEND, wordType caps){
		newNode=node;
		isTerminal=isEND;
		capsType=caps;
	}

}