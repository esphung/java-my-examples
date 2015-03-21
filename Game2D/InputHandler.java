/*
* @Author: Eric Phung
* @Date:   2015-03-20 14:26:49
* @Last Modified by:   Eric Phung
* @Last Modified time: 2015-03-20 15:05:54
* @PURPOSE:							Handle input Class for BURN game
* @NOTES:								Youtube video #004
*/
//import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class InputHandler implements KeyListener {



	// this block removes requirement for invokation of key method in Game class
	public InputHandler(Game gameObject){
		gameObject.addKeyListener(this);
	} // end constructor



	public class Key{
		private int numTimesPressed = 0;
		private boolean pressed = false;




		public int getNumTimesPressed(){
			return numTimesPressed;
		} // end return number of times pressed




		public boolean isPressed() {
			if (pressed == true) {
				numTimesPressed++;
			} // end if number of times pressed
			return pressed;
		} // end return boolean pressed



		public void toggle(boolean isPressed){
			pressed = isPressed;
		} // end toggle def
	} // end key class def

	//public List<Key> keys = new ArrayList<Key>();



	// dec human keys
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();



	//@Override
	public void keyPressed(KeyEvent e){
		toggleKey(e.getKeyCode(),true);
	} // end key pressed



	//@Override
	public void keyReleased(KeyEvent e){
		toggleKey(e.getKeyCode(),false);
	} // end key released



	//@Override
	public void keyTyped(KeyEvent e){
	} // end key Typed




	// function to toggle keys
	public void toggleKey(int keyCode, boolean isPressed){
		// compare different key codes
		if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {up.toggle(isPressed);}
		if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {down.toggle(isPressed);}
		if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {left.toggle(isPressed);}
		if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {right.toggle(isPressed);}
	} // end toggle keys



	/*
	public static void main(String[] args) {
	} //end main func
	*/
} // end class definition