/* Hello User - Input
* @Author: Eric Phung
* @Date:   2015-02-26 15:48:18
* @Last Modified by:   Eric Phung
* @Last Modified time: 2015-02-26 15:52:10
*/
import java.util.*;

public class HelloUserInput {
    public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
    	System.out.print("What is your name?");
    	String userName = input.nextLine();
    	System.out.println("Hi there, " + userName + "!");
    }// end mmain
}// end class def