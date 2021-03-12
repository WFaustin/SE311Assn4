import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFileWriter {
	
	public OutputFileWriter() {

	}
	
	public void printOutput(String s) {
		//Modified from the example given in W3 Schools. Link is: https://www.w3schools.com/java/java_files_create.asp
		try {
			
			//Create new file 
			File file = new File(System.getProperty("user.dir") + File.separator + "OutputFile.txt");
			file.createNewFile(); 
			System.out.println("File created in OutputFiles Directory: " + file.getName());
			
			//Write to said file
			FileWriter fileWriter = new FileWriter(file); 
			fileWriter.write(s + "\n");
			
			//Close File Writer and Tell User Action has been done
			fileWriter.close(); 
			System.out.println("Your output has been completed.");
		}catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}


}
