/*
* @Author: Eric Phung
* @Date:   2015-03-20 00:25:29
* @Last Modified by:   Eric Phung
* @Last Modified time: 2015-03-20 13:10:45
* @Purpose:							Sprite sheet class for BURN game
*/

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {
	public String 				path;
	public int 						width;
	public int 						height;

	public int[] 					pixels;
	BufferedImage					imageObject;


	// find the images by path arg
	public SpriteSheet(String path){

		try{
			imageObject = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
		}
		catch (Exception e) {
			e.printStackTrace();
		} // end try/catch

		if (imageObject == null) {
			return;
		} // end if null
		this.path 					= path;
		this.width 					= imageObject.getWidth();
		this.height 				= imageObject.getHeight();
		pixels 							= imageObject.getRGB(0,0,width,height,null,0,width);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = (pixels[i] & 0xff)/(64); // puts 0,1,2,3, or 4 into spritesheet
		} // end for alpha colors

		for (int i = 0; i < 8; i++) {
			System.out.println(pixels[i]);
		} // end for to print ount first 8 pixels of spritesheet


	} // end single constructor


	/*
	public static void main(String[] args) {
	} // end main
	*/
} // end class def