import java.util.Scanner;
import java.io.File;
import java.util.regex.*;

enum wordType {ALLCAPS, FIRSTCAP, NOCAPS}; //enum for CAPS info for the words

public class AutoCompleteMe {
	
	
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
		
	}
	public static void readFromFile(String file_argument){  
		wordType checkCaps = wordType.NOCAPS; //variable so we can check CAPS status for each word
		boolean checkPunct=false;
		
		try{
			
			//String word1="01.txt";
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
				checkPunct = true;
				Pattern p = Pattern.compile("\\p{Punct}"); //compile this pattern
				
				//if words ends with punc , keeps only the characters before that
				while(word.endsWith( ".")|| word.endsWith( "?")|| word.endsWith( "!")
					|| word.endsWith( "\'") || word.endsWith( ",")|| word.endsWith( ":")){
					word=word.substring(0, word.length()-1);
					checkPunct= true;
				}
				Matcher m = p.matcher(word);
				
				
				if (!(m.find()) ){//if you don't find the pattern in the word
					
					System.out.println(word); // we get the word WITH NO changes
					/*BUILD THE STRUCTURE
					YOU FILTHY
					ANIMAL
					HEY
					HEY
					*/
				}
			}	
		}
		catch(Exception ex){ //error log
			ex.printStackTrace();
		}
	}
	public static void suggestWordPhrase(String word_phrase){//after finishing the structure
		
	}
	

	
	public static void main (String [] args) {
		boolean checkCAPS=false;  //check variable for keeping info for Caps Status
		
		menu();
	}


}