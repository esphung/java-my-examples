// main file
// demo for exception handling with try/catch in Java
//import java.util.*;

public class Error {
	public static void main(String[] args) {
		new Error();
	} // end main

	public Error(){
		int x = 0;
		int y = 1;
		int quotient;

		// set up exception handling
		try {
			quotient = (y)/(x); // divide by zero! for fail
			System.out.println("Dividing " + y + " by " + x);
			System.out.println("Answer >> " + quotient); // if win, print
		} // end try
		// if fail, catch
		// catch for int format
		catch(NumberFormatException e){
			System.out.println(e);
		} // end catch arithmetic
		catch(ArithmeticException e){
			System.out.println(e);
		} // end catch for arith
		// default catch for 'all'
		catch (Exception e){
			System.out.println("Hello Catch! (Error caught)"); // if fail, print
			System.out.println("THis is built in reply for 'e' >> " + e);
		}

	} // end constructor


} // end class