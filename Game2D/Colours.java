/*
* @Author: Eric Phung
* @Date:   2015-03-20 15:15:15
* @Last Modified by:   home
* @Last Modified time: 2015-03-20 17:11:27
* @PURPOSE:							Color class for BURN game
* @NOTES:								Youtube video #005 ???
*/

public class Colours {
	public static int get(int colour1,int colour2,int colour3, int colour4){
		return ((getInt(colour4)<<24)+(getInt(colour3)<<16)+(getInt(colour2)<<8)+(getInt(colour1)));
	} // end get multi-parameter

	private static int getInt(int colour){
		if (colour < 0) {return 255;} // return transparent colour

		int r = (colour/100)%10;
		int g = (colour/10)%10;
		int b = (colour/1)%10;
		return (r*36+g*6+b);
	} // end get colour



} // end class definition