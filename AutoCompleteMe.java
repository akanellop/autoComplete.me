import java.util.Scanner;
import java.io.File;
import java.util.regex.*;
import java.lang.*;

//enum wordType {ALLCAPS, FIRSTCAP, NOCAPS}; //enum for CAPS info for the words



public class AutoCompleteMe {
	
	static DictNode root = new DictNode(false,wordType.NOCAPS); // creates root of the tree
	
	//MENU method prints out the menu in terminal and waits user's input
	public static void menu() {
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
	
	public static void loadFromFile(String file_argument){ // Serialisation to be done later
		
	}
	public static void saveToFile(String file_argument){ // Serialisation to be done later
		
	}
	
	public static void printDictionary(){
		
		/* tha ksekinaei apo to root kai tha psaxnei ola tou ta paidia
		epeita tha psaxnei ta paidia twn paidiwn k.o.k.
		Ola auta se ena for loop diladi.
		Tha xrisimopoihsoume ena String to opoio tha to xtizoume siga siga xarakthra xarakthra
		se kathe komvo pou vriskomaste, tha exoume enan char o 
		 
		ch = pos + 'a';*/
		
		DictNode temp = new DictNode(false,wordType.NOCAPS);
		//DictNode temp2 = new DictNode(false,wordType.NOCAPS);
		
		temp = root;
		//String str = "";
		
		char ch;
		
		
			for (int i = 0 ; i<25 ; i++) { // gia kathe deikth pou exei o root
				while (1){
					if ( temp.pointers[i] != null ) { // an ontws uparxei paidi
						//  //tote auto exei na mas dwsei, opote as to psaxoume perissotero
								//vevaia mporei na theloume na psaxoume perissotero KAI auto to paidi
								//mipws xreiazetai for loop?
							
							String str = ""; // arxikopoihsh string
							
							ch = i + 'a'; // not sure
							str = str + ch;
							
							if ( temp.isTerminal == true ) { // check temp.capsType on how we want to print this shit
								System.out.println("Word in Dictionary: " + str);
							}
							
							temp=temp.pointers[i]; //next
							//i=0;
						}
						
					}			
				}
			}
		}
	}
	
	//takes file as input and save all the words in a dictionary-like structure
	public static void readFromFile(String file_argument){  
		wordType checkCaps = wordType.NOCAPS; //variable so we can check CAPS status for each word
		boolean checkPunct=false;
		int pos;
		DictNode temp = new DictNode(false,wordType.NOCAPS);
		DictNode temp2 = new DictNode(false,wordType.NOCAPS);
		try{
			
			String file_name = file_argument;
			Scanner scanned_file = new Scanner(new File(file_name));
			
			while ( scanned_file.hasNext() ) { //scanner has tokens, so continue scanning
				String word = scanned_file.next(); //reads word
					
				
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
				
				while(word.endsWith( ".")|| word.endsWith( "?")|| word.endsWith( "!") // erase punct symblos in the end of the word
					|| word.endsWith( "\'") || word.endsWith( ",")|| word.endsWith( ":")){// for example, we get "absolute." replaced with "absolute"
					word=word.substring(0, word.length()-1);
					checkPunct= true;
				} 
				Matcher m = p.matcher(word);//checks if there are other punc symbols in the word
				
				
				//if you don't find the pattern in the word seperate the letters to put them "in" the nodes
				// reminder :we do this for each word, we are inside a while loop
				if (!(m.find()) ){
					
					word=word.toLowerCase();//word needs conversion into ALL lowercase to find the correct position for each letter
					System.out.println(word);
					
					temp=root;// for each word, we start from the root 
					
					for ( char ch : word.toCharArray()) { //enhanced loop, iterates through each character
						System.out.println(ch);
						pos = ch -'a'; // index 
						
						//if ( pos == 0 ) { // in case there is not a node for 'a'
						if (temp.pointers[pos] == null ) { //we "build" a new object in the correct position of the array of the current node
								
							temp.pointers[pos] = new DictNode(false,wordType.NOCAPS);//create node test
						}
								
								
						//if the character is the last one define the rest fields of the node
						if ( pos == word.length - 1){
							temp.isTerminal = true;
							temp.capsType = checkCaps;
						}
						
						temp=temp.pointers[pos]; // iteration for next node
					}
					
				}
				
				//the following code is for testing if we actually "put" the letters in the nodes !!WORKS 
	
				temp2=root;
				temp2=temp2.pointers[0];
				temp2=temp2.pointers[0];
				if (temp2.pointers[0]==null){
					System.out.println("didnt work");
				}
				else{
					System.out.println("third a in correct pos");
				}
				temp2=temp2.pointers[0];
				if (temp.pointers[0]==null){
					System.out.println("work");
				}
				temp2=temp2.pointers[0];
		
			}		
		}
		catch(Exception ex){ //error log
			ex.printStackTrace();
		}
	}
	public static void suggestWordPhrase(String word_phrase){//after finishing the structure
		
	}
	
	 
	public static void main (String [] args) {
		menu();
	}


}

	
					/* for (int i = 0 ; i< word.length(); i++){
						char ch = word.charAt(i);
						System.out.println(ch);
						
					} */
					
					
					
				//char firstChar = word.charAt(0);//find first char of the word
				/*if ( (checkPunct) && (Character.isUpperCase(firstChar) )  ){
					
					char firstCharLowerCase = Character.toLowerCase(firstChar);
					word = firstCharLowerCase + word.substring(1,word.length());
					//word.setCharAt(0, firstCharLowerCase); 
					//input: "go away. Mary went away." 
					//dict should be:"go away mary went away "
					//capital 'M' gets replaced with lowercase 'm'
				}*/