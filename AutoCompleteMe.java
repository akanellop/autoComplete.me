import java.util.Scanner;
import java.io.File;
import java.util.regex.*;
import java.lang.*;
import java.io.*;


public class AutoCompleteMe {//implements java.io.Serializable{//
	
	static DictNode root = new DictNode(false,wordType.NOCAPS); // creates root of the tree
	static File f = new File("dictionary.txt");
	static PrintWriter outputStream ;//= null;// = new PrintWriter(new FileWriter("dictionary.txt"));
	//MENU method prints out the menu in terminal and waits user's input
	
	static int counterForWords = 0 ;
	static int flagForFile ;
	
	public static void menu()  {
		while(true){
			
			System.out.println("-----------MENU-----------\n"  
			+"1. Load dictionary from binary file (type: load fromFilepath)\n"
			+"2. Save dictionary to binary file (type: save toFilepath)\n"
			+"3. Populate dictionary from txt file (type: read fromTxtFilePath)\n"
			+"4. Suggest word (type: suggest wordPhrase)\n"
			+"5. Print dictionary information (type: print)\n"
			+"6. Quit (type: quit)\n");
			
			Scanner input = new Scanner(System.in); //create input variable
			String data = input.nextLine();  //get whole line from terminal
			String[] user_option = data.split("\\s+");// split the line where " " is used
			
			
			if ( user_option[0].equals("load") ){ // load fromFilePath
				loadFromFile(user_option[1]);
			}
			else if ( user_option[0].equals("save") ){ //save toFilePath
				saveToFile(user_option[1]);
			}
			else if ( user_option[0].equals("read") ){ //read fromFilePath
				readFromFile(user_option[1]);
			}
			else if ( user_option[0].equals("suggest") ){ //suggest wordPhrase
				flagForFile = 0;
				suggestWordPhrase(user_option[1]);
			}
			else if (user_option[0].equals("print"))		{// "print"		
				printDictionary();
			}
			else if (user_option[0].equals("quit"))	{ // "quit"
				System.out.println("Ok, Bye!\n");
				System.exit(0);
			}
		}
	}
	
	public static void loadFromFile(String file_argument)  {// Serialisation to be done later
		try{
			FileInputStream fileIn = new FileInputStream(file_argument+".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			root = (DictNode) in.readObject();
			
			in.close();
			fileIn.close();
		}
		catch(IOException i){
			i.printStackTrace();
			return;
		}
		catch(ClassNotFoundException ex) {
			System.out.println("Class Not Found");
			ex.printStackTrace();
			return;
		}
	}
	
	public static void saveToFile(String file_argument) { // Serialisation to be done later
		try{
			FileOutputStream fileOut = new FileOutputStream(file_argument+".ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			out.writeObject(root);
			
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in "+file_argument+".ser");
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	
	public static void traversalPrint(DictNode current, String str){
    	
		int chtemp;
		char ch;
		int counter;
		
		DictNode temp = current ; //new DictNode(false,wordType.NOCAPS);
			
		for (int i = 0 ; i < 26 ; i++ ) {//check all the kids for this node
			if (current.pointers[i] != null ) {//if you find a kid that has a letter, search its path
												//this happens recursively..
												
				chtemp = i + 'a'; 
				ch=(char)chtemp;
				str = str + ch;		// put the letter in the string
				
				temp=current.pointers[i]; // passing onto the child
				
				
				if ( temp.isTerminal == true ) { 
					if (temp.capsType == wordType.ALLCAPS){//make it all caps
						//System.out.println("this word is ALLCAPS");
						str=str.toUpperCase();
					}
					else if ( temp.capsType == wordType.FIRSTCAP) {//make it first capital
						//System.out.println("this word is FIRSTCAP");
						str=str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
					}
					System.out.println(str);//print the word now
					if ( flagForFile == 1) {
						outputStream.println(str);
						counterForWords = counterForWords + 1;
					}
					
				}
				
				traversalPrint(temp,str);//continue searching the tree , call method recursively
             
				str = str.substring(0,str.length()-1); ////every time i return from the traversal method
													//i erase the previous node- letter which was given
			}
		}
	}

	//Prints the Dictionary 
	public static void printDictionary(){
		  
		String str="";
		
		flagForFile = 1;
		if (!f.exists()) {//ean den uparxei 
			try{
				f.createNewFile();//tote dimiourgise to
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		try{
			outputStream = new PrintWriter(new FileWriter("dictionary.txt"));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		//outputStream.println("ADSDSASDASADSADSDAASDASDDSASDADSAASD");
		traversalPrint(root,str);
		System.out.println("File name = dictionary.txt , Words Counter = " +counterForWords);
		counterForWords=0;
		outputStream.close();
	} 
	
	//takes file as input and save all the words in a dictionary-like structure
	public static void readFromFile(String file_argument){  
		wordType checkCaps = wordType.NOCAPS; //variable so we can check CAPS status for each word
		boolean checkPunct=false;
		int pos;
		DictNode temp = new DictNode(false,wordType.NOCAPS);
		DictNode temp2 = new DictNode(false,wordType.NOCAPS);
		
		int counter=0;
		
		try{
			
			String file_name = file_argument;
			Scanner scanned_file = new Scanner(new File(file_name));
			
			while ( scanned_file.hasNext() ) { //scanner has tokens, so continue scanning
				String word = scanned_file.next(); //reads word
				word = word.replaceAll("[^A-Za-z]","?"); //replace all the symbols that are not letters with symbol "?"
				
				//checks Caps Status for the word
				if (word.toUpperCase().equals(word)) { //word is ALL UPPERCASE, ALLCAPS
					checkCaps = wordType.ALLCAPS;
				}
				else if(Character.isUpperCase(word.charAt(0)) && checkPunct==false){ //first char is UPPERCASE, FIRSTCAP
					checkCaps = wordType.FIRSTCAP;
				}
				else {
					checkCaps = wordType.NOCAPS; // word is lowercase, NOCAPS
				}
				
				//checks if there is a punctuation symbol for each situation
				Pattern p = Pattern.compile("\\p{Punct}"); //compile this pattern
				
				//while words ends with punc , keeps only the characters before that
				checkPunct = false;//initialize for this word
				
				while(word.endsWith( "?")){// for example, we get "absolute." replaced with "absolute"
					word=word.substring(0, word.length()-1);
					checkPunct= true;
				} 
				Matcher m = p.matcher(word);//checks if there are other punc symbols in the word
				
				
				//if you don't find the pattern in the word and the word inst blank seperate the letters to put them "in" the nodes
				// reminder :we do this for each word, we are inside a while loop
				if (!(m.find()) && !(word.equals(""))){ 
					
					word=word.toLowerCase();//word needs conversion into ALL lowercase to find the correct position for each letter
					System.out.println(word);
					
					temp=root;// for each word, we start from the root 
					counter=0;
					for ( char ch : word.toCharArray()) { //enhanced loop, iterates through each character
						pos = ch -'a'; // index 
						
						//if ( pos == 0 ) { // in case there is not a node for 'a'
						if (temp.pointers[pos] == null ) { //we "build" a new object in the correct position of the array of the current node
								
							temp.pointers[pos] = new DictNode(false,wordType.NOCAPS);//create node test
						}
								
						temp=temp.pointers[pos];//passing onto the child
						//if the character is the last one define the rest fields of the node
						if ( counter == word.length()-1 ){
							temp.isTerminal = true;
							temp.capsType = checkCaps;
						}
						
						counter=counter+1;
					}
				}
			}	
		}
		catch(Exception ex){ //error log
			ex.printStackTrace();
		}
	}

	public static int suggestWordPhrase(String word_phrase){//after the structure is finished, the program can show suggestions to the user
	
		int pos;//index
		DictNode temp = root;//initiliaze current object as the root of the tree
		
		int counter = 0;
		int chtemp ;
		
		String str = "Sorry, there are no words that start with that wordphrase :(";
		word_phrase=word_phrase.toLowerCase();
		
		for ( char ch : word_phrase.toCharArray()) { //for each letter in the string
		
			counter = counter + 1;
			pos = ch -'a'; // find the correct node in current object
			
			if (temp.pointers[pos] == null ) { //if there isn't a node for the letter we are searching
				System.out.println(str);
				return -1;
			}
			else {//if there is a node 
				temp=temp.pointers[pos]; // pass onto it
			}
		}
		if ( counter==word_phrase.length() ){ //Call traversalPrint to find all suggestions
			traversalPrint(temp,word_phrase);
		}
		return 1;
	}
	
	 
	public static void main (String [] args) {// throws ClassNotFoundException,NullPointerException {
		menu();
	}


}
	
