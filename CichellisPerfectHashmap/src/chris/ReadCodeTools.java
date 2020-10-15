package chris;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadCodeTools {
	
	public static void checkKeys(String word, List<Keys> listOfWords) {
		for(Keys i:listOfWords) {
			if(i.getKeyword().equals(word)) {
				int newValue = i.getValue();
				newValue++;
				i.setValue(newValue);
			}
		}
	}
	
	public static int countLines(File f) throws IOException{
		BufferedReader reader;
		int lines = 0;
		reader = new BufferedReader(new FileReader(f));
		String line = reader.readLine();
		while(line != null) {
			System.out.println(line);
			if (line.contains("//")) {
				lines--;
			}
			if(line.startsWith("/*")) {
				do {
					lines--;
				}while(!line.startsWith("*/"));
			}
			if(line.isBlank()) {
				lines--;
			}
			lines++;
			line = reader.readLine();
			System.out.println(lines);
		}
		reader.close();
		return lines;
		
	}
	
	public static String displayKeywords(List<Keys> listOfWords, int lineCount) {
		String output = "";
		for(Keys t: listOfWords) {
			output = output.concat(String.valueOf(t.getValue()));
			output = output.concat(" of key word: ").concat(t.getKeyword()).concat("\n");
		}
		output = output.concat("total time in ms for program to run: ").concat(String.valueOf(Timer.getTime())).concat("\n");
		output = output.concat("Lines of code: ").concat(String.valueOf(lineCount));
		 try {
		      FileWriter myWriter = new FileWriter("newFile.txt");
		      myWriter.write(output);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return output;
	}
	
}
