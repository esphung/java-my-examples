/*
* @Author: 							Eric Phung
* @Date:   							2015-03-20 00:01:18
* @Last Modified by:   Eric Phung
* @Last Modified time: 2015-03-20 18:42:39
* @Purpose:							MAIN CLASS FOR BURN IT GAME
*/

import java.util.*;

import java.awt.Canvas;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.BorderLayout;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.image.DataBufferInt;
import java.awt.Color;
//import java.awt.Image;

public class Game extends Canvas implements Runnable{

//====================================== DEC VARS!!!
	protected String title;

	// VARIABLE DECS
	public static final int 		WIDTH = 180;
	public static final int 		HEIGHT = (WIDTH/12)*9;
	public static final int 		SCALE = 3;
	public static final String 	NAME = "Game";
	private JFrame 							myFrame;
	public boolean 							keepGoing = false;
	public int 									tickCount = 0;

	private BufferedImage imageObject = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)imageObject.getRaster().getDataBuffer()).getData(); // allows you to use pixels anywhere you want to update the image

	// NEW SPRITE SHEET STUFF!!!
	//private SpriteSheet spriteSheetObject = new SpriteSheet("/sprite_sheet.png");

	private int[] colours = new int[6*6*6];



	private Screen screenObject; // instantiate from external
	public InputHandler inputObject;

//====================================== MAIN!!!

	public static void main(String[] args) {

		Game gameObject = new Game();
		gameObject.start();

	} // end main


//========================================= CONSTRUCTORS

	public Game(){
		this.title = "HELLO GAME NAME!";
		this.myFrame = myFrame;

		setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));

		this.myFrame = new JFrame(this.title);
		this.myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.myFrame.setLayout(new BorderLayout());
		this.myFrame.add(this, BorderLayout.CENTER);
		this.myFrame.pack();

		this.myFrame.setResizable(false);
		this.myFrame.setLocationRelativeTo(null);
		this.myFrame.setVisible(true);

	} // end null constructor


/*
	public Game(String title){
		this.title = title;
	} // end single constructor
*/

//============================================= METHODS
	// screen object functions add new screen
	public void init() {
		int index = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r*255/5);
					int gg = (g*255/5);
					int bb = (b*255/5);

					// populate the colours array
					colours[index++] = rr << 16 | gg << 8 | bb;
				} // end 3rd for b
			} // end 2nd for g
		} // end 1st for r


		screenObject = new Screen(WIDTH, HEIGHT, new SpriteSheet("/sprite_sheet.png"));
		inputObject = new InputHandler(this);


	} // end init screen func


	public synchronized void start(){
		keepGoing = true;
		// CREATE NEW THREAD
		//new Thread(this).start();
		Thread threadObject = new Thread();
		threadObject.start();



		run();

	} // function to applet

	public synchronized void stop(){
		keepGoing = false;
	} // function to applet



// ========================================= MAIN GAME LOOP
	public void run(){
		// dec mail loop vars
		long lastTime = System.nanoTime(); // get current time
		double nsPerTick = 1000000000.0/60.0; // one billion muahahaha

		int ticks = 0; // UPS updates per second
		int frames = 0; // FPS frames per second

		long timer = System.currentTimeMillis(); // timer for lastTime var
		double delta = 0;


		init();


		// main game loop
		while (keepGoing) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;

			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			} // end while delta overlimit

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} // end try catch sleep
			if (shouldRender) {
				frames++;
				render();
			} // end if should render

			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				System.out.println(ticks + " ticks , " + frames + " frames per second");
				frames = 0;
				ticks = 0;
			} // end if system timer
		} // end while keepGoing

	} // end run


	// tick will update the game
	public void tick(){
		tickCount++;

		if (inputObject.up.isPressed()){screenObject.yOffset--;}
		if (inputObject.down.isPressed()){screenObject.yOffset++;}
		if (inputObject.left.isPressed()){screenObject.xOffset--;}
		if (inputObject.right.isPressed()){screenObject.xOffset++;}

		//screenObject.xOffset++; // move left
		//screenObject.yOffset++; // move up

		// put images on the screen
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = i + tickCount;
		} // end for pixels image

	} // end tick


	// render will print out updates
	public void render(){
		BufferStrategy bsObject = getBufferStrategy();

		if (bsObject == null) {
			createBufferStrategy(3); // 3 for triple buffering - reduces tearing, but higher value args = more processing power
			return;
		} // end if bsObject null


		// =================================  HERE WE APPEND TO SCREEN!!!
		/* SPRITE SHEET NOTES */
		// Width 256pixels x Height 256pixels

		//screenObject.render(pixels,0,WIDTH);


		for (int y = 0; y < 32; y++) {
			for (int x = 0; x < 32; x++) {
				boolean flipX = x % 2 == 1;
				boolean flipY = y % 2 == 1;
				screenObject.render(x << 3,y << 3, 0, Colours.get(555,505,055,550), flipX, flipY);
			} // end for x
		} // end for y


		for (int y = 0; y < screenObject.height; y++) {
			for (int x = 0; x < screenObject.width; x++) {
				int colourCode = screenObject.pixels[x+y*screenObject.width];
				if (colourCode < 255) {
					pixels[x+y*WIDTH] = colours[colourCode];
				}

			} // end for x
		} // end for y


		Graphics graphicsObject = bsObject.getDrawGraphics();
		//graphicsObject.setColor(Color.BLACK);
		//graphicsObject.fillRect(0,0, getWidth(),getHeight());
		graphicsObject.drawImage(imageObject,0,0,getWidth(),getHeight(),null);

		graphicsObject.dispose();
		bsObject.show();

	} // end render





	public void sayHi(){
		System.out.println("Hi, My name is " + this.title);
	} // end sayHi DEBUG !!!!!!!!!!!!!!!!


/*
	public String getTitle() {
	    return this.title;
	} // end getter title
	public void setTitle(String title) {
	    this.title = title;
	} // end setter title
*/






} // end class defintion
