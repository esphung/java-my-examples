// WriteFile.java
// demonstrate how to write to file with java
import java.io.*; // lib for read/write
public class WriteFile{
	public static void main(String[] args){
		new WriteFile();
	} // end main

// class definition
	public WriteFile(){
		try {
			// 'false' argument means append
			FileWriter outFile = new FileWriter("poem.txt", false);
			// prints to the file (acts like System.out)
			PrintWriter output = new PrintWriter(outFile); // 'output' object

			// output to file
			output.println("Twas brillig and the slithy toves"); // object method
			output.println("did gyre and gimbal in the wabe");
			output.println("all mimsy were the borogrove");
			output.println("and the mome wrath outgrabe");

			 output.close(); // flush buffer
		} catch (IOException e) {
			System.out.println(e.getMessage());
			} // end try

	} // end constructor
} // end class def