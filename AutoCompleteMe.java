import java.util.Scanner;
import java.io.File;
import java.util.regex.*;

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
			String user_option = input.next(); // wait for user input, select your option
			
			if (user_option.equals("print"))		{// "print"		
				print_dictionary();
			}
			else if (user_option.equals("quit"))	{ // "quit"
				System.out.println("Ok, Bye!\n");
				System.exit(0);
			}
		}
	}
	
	public static void print_dictionary(){
		
	}
	
	/*public static void ReadFromFile(){ //Thanos Scanner 
		try{
			
			Scanner scanned_file = new Scanner(new File("01.txt"));
			
			while ( scanned_file.hasNext() ) { //scanner has tokens, so continue scanning
				String word = scanned_file.next();
				
				Pattern p = Pattern.compile("\\p{Punct}"); //compile this pattern
				Matcher m = p.matcher(word);
				
				if (m.find()){//if you find the pattern in the word
					word=word.substring(0,m.start());	//
				}
				System.out.println(word);
			}	
		}
		catch(Exception ex){ //error log
			ex.printStackTrace();
		}
	}*/
	
	public static void main (String [] args) {
		
		
		//spelling check
		String word = "NO !!!"; // blank spaces and punctations are used as wild cards
		if (word.toUpperCase().equals(word)) {
			System.out.println("this is all uppercase, yes!\n");
		}
		if (word.toLowerCase().equals(word)) {
			System.out.println("this is all lowercase, yes!\n");
		}
		//menu();
	}


}