
/*
* @Author: Eric Phung
* @Date:   2015-03-20 13:26:46
* @Last Modified by:   Eric Phung
* @Last Modified time: 2015-03-20 18:43:14
* @Purpose:							Screen class for BURN game
* @NOTES:								Youtube video #003
*/

public class Screen {
	public static final int MAP_WIDTH = 64;
	public static final int MAP_WIDTH_MASK = (MAP_WIDTH - 1);

	//public int[] tiles = new int[MAP_WIDTH*MAP_WIDTH]; // brackets or parentheses??
	// ============  uncommented
	//public int[] colours = new int[MAP_WIDTH*MAP_WIDTH*4];

	public int[] pixels;

	public int xOffset = 0;
	public int yOffset = 0;

	public int width;
	public int height;

	public SpriteSheet sheetObject;

	//=============================  CONSTRUCTOR

	public Screen(int width, int height, SpriteSheet sheetObject){
		this.width = width;
		this.height = height;
		this.sheetObject = sheetObject;

		// int[] or nah?? || pixelS
		pixels = new int[width*height];
/*
		for (int i = 0; i < MAP_WIDTH*MAP_WIDTH; i++) {
			colours[i*4+0] = 0xff00ff;
			colours[i*4+1] = 0x00ffff;
			colours[i*4+2] = 0xffff00;
			colours[i*4+3] = 0xffffff;
		} // end for set colour vars
*/

	} // end constructor


	public void render(int xPos,int yPos,int tile,int colour,boolean mirrorX,boolean mirrorY) {
		xPos -= xOffset;
		yPos -= yOffset;

		int xTile = tile % 32;
		int yTile = tile / 32;
		int tileOffset = (xTile << 3) + (yTile << 3) * sheetObject.width;

		for (int y = 0; y < 8; y++) {
			if (y + yPos < 0 || y + yPos >= height) {continue;} // end if

			int ySheet = y;
			if(mirrorY)ySheet = 7 - y;

			for (int x = 0; x < 8; x++) {
				if (x + xPos < 0 || x + xPos >= width) {continue;} // end if
				int xSheet = x;
				if (mirrorX)xSheet = 7 - x;
				int col = (colour >> ( sheetObject.pixels[xSheet + ySheet* sheetObject.width + tileOffset] * 8)) & 255;
				if (col < 255) {
					pixels[(x + xPos) + (y + yPos) * width] = col;
				} // end if col
			} // end for
		} // end for parent
	} // end render





/*
	public static void main(String[] args) {
	} // end main
*/
}