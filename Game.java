package edu.nyu.cs;

import java.util.ArrayList;

import org.apache.commons.lang3.SystemUtils;

import processing.core.*; // import the base Processing library
import processing.sound.*; // import the processing sound library

/**
 * 
 * @author Corina Luca
 * @version 0.3
 */
public class Game extends PApplet {

  private SoundFile soundStartup; // will refer to a sound file to play when the program first starts
  private SoundFile soundClick; // will refer to a sound file to play when the user clicks the mouse
  private PImage imgMe; // will hold a photo of me
  private final int POINTS_PER_STAR = 1; // the number of points to award the user for each star they destroy
  
  private int score = 0; // the user's score
  private int lives = 3; // lives

  private int rectWidth = 50;
  private int rectHeight = 200;

  private Star star1 = new Star(this, 500, 300, 255,255,255);
  private Star star2 = new Star(this, 700, 400);


	/**
	 * This method will be automatically called by Processing when the program runs.
   * - Use it to set up the initial state of any instance properties you may use in the draw method.
	 */
	public void setup() {
    // set the cursor to crosshairs
    this.cursor(PApplet.CROSS);

    // load up a sound file and play it once when program starts up
    //this.soundStartup = new SoundFile(this, "sounds/vibraphon.mp3"); // if you're on Windows, you may have to change this to "sounds\\vibraphon.mp3"
    //this.soundStartup.play();

    // load up a sound file and play it once when the user clicks
    this.soundClick = new SoundFile(this, "sounds/thump.aiff"); // if you're on Windows, you may have to change this to "sounds\\thump.aiff"
 
    // load up an image of me
    this.imgMe = loadImage("images/back.jpg"); // if you're on Windows, you may have to change this to "images\\me.png"

    // some basic settings for when we draw shapes
    this.rectMode(PApplet.CENTER); // setting so ellipses radiate away from the x and y coordinates we specify.
    this.imageMode(PApplet.CENTER); // setting so the ellipse radiates away from the x and y coordinates we specify.

  
	}

	/**
	 * This method is called automatically by Processing every 1/60th of a second by default.
   * - Use it to modify what is drawn to the screen.
   * - There are methods for drawing various shapes, including `ellipse()`, `circle()`, `rect()`, `square()`, `triangle()`, `line()`, `point()`, etc.
	 */
	public void draw() {
    // fill the window with solid color
    this.background(0, 0, 0); // fill the background with the specified r, g, b color.

    // show an image of me that wanders around the window
    image(this.imgMe, this.width / 2, this.height/2); // draw image to center of window

    // draw an ellipse at the current position of the mouse
    this.fill(255, 255, 255); // set the r, g, b color to use for filling in any shapes we draw later.
    this.rect(0, this.mouseY, this.rectWidth, this.rectHeight); // draw an ellipse wherever the mouse is

    star1.draw(); // draw the star to the screen
    star2.draw();

    this.wins();
    this.misses();

    String scoreString = String.format("SCORE: %d   LIVES: %d", this.score, this.lives);
    text(scoreString, this.width/2, this.height-50);

    if(this.lives == 0){
      System.out.println("Game over! Your score is:" + this.score);
      System.exit(0);
    }
  }

	public void wins(){

    if (star1.overlaps(this.rectWidth, this.mouseY, this.rectHeight)) {
      // if so, award the user some points
      score += POINTS_PER_STAR;        
    }
    if (star2.overlaps(this.rectWidth, this.mouseY, this.rectHeight)) {
      // if so, award the user some points
      score += POINTS_PER_STAR;        
    }

  }


	public void misses(){
    if(star1.getLoss()){
      this.lives--;
      this.soundClick.play();
    }
    if(star2.getLoss()){
      this.lives--;
      this.soundClick.play();
    }

  }

	/**
	 * This method is automatically called by Processing every time the user presses a key while viewing the map.
	 * - The `key` variable (type char) is automatically is assigned the value of the key that was pressed.
	 * - The `keyCode` variable (type int) is automatically is assigned the numeric ASCII/Unicode code of the key that was pressed.
	 */
	public void keyPressed() {
    // the `key` variable holds the char of the key that was pressed, the `keyCode` variable holds the ASCII/Unicode numeric code for that key.
		System.out.println(String.format("Key pressed: %s, key code: %d.", this.key, this.keyCode));
	}  

	/**
	 * This method is automatically called by Processing every time the user clicks a mouse button.
	 * - The `mouseX` and `mouseY` variables are automatically is assigned the coordinates on the screen when the mouse was clicked.
   * - The `mouseButton` variable is automatically assigned the value of either the PApplet.LEFT or PApplet.RIGHT constants, depending upon which button was pressed.
   */
	public void mouseMoved() {
		System.out.println(String.format("Mouse moved at: %d:%d.", this.mouseX, this.mouseY));
	}

	/**
	 * This method is automatically called by Processing every time the user presses down and drags the mouse.
	 * The `mouseX` and `mouseY` variables are automatically is assigned the coordinates on the screen when the mouse was clicked.
   */
	public void mouseDragged() {
		System.out.println(String.format("Mouse dragging at: %d:%d.", mouseX, mouseY));
	}

  /**
   * A method that can be used to modify settings of the window, such as set its size.
   * This method shouldn't really be used for anything else.  
   * Use the setup() method for most other tasks to perform when the program first runs.
   */
  public void settings() {
		size(1000, 700); // set the map window size, using the OpenGL 2D rendering engine
		System.out.println(String.format("Set up the window size: %d, %d.", width, height));    
  }

  /**
   * The main function is automatically called first in a Java program.
   * When using the Processing library, this method must call PApplet's main method and pass it the full class name, including package.
   * You shouldn't need to modify this method.
   * 
   * @param args An array of any command-line arguments.
   */
  public static void main(String[] args) {
    // make sure we're using Java 1.8
		System.out.printf("\n###  JDK IN USE ###\n- Version: %s\n- Location: %s\n### ^JDK IN USE ###\n\n", SystemUtils.JAVA_VERSION, SystemUtils.getJavaHome());
		boolean isGoodJDK = SystemUtils.IS_JAVA_1_8;
		if (!isGoodJDK) {
			System.out.printf("Fatal Error: YOU MUST USE JAVA 1.8, not %s!!!\n", SystemUtils.JAVA_VERSION);
		}
		else {
			PApplet.main("edu.nyu.cs.Game"); // do not modify this!
		}
  }

}
