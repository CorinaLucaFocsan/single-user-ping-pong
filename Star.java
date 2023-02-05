package edu.nyu.cs;

import processing.core.PApplet;


public class Star {
    // instance properties
    private Game app; // will hold a reference to the main Game object
    private int x; // will hold the x coordinate of this object on the screen
    private int y; // will hold the y coordinate of this object on the screen

    private int speedX;
    private int speedY;

    private int r;
    private int g;
    private int b;;

    private int width;
    private int height;

    private static final int MAX_SPEED = 10;
    

    /**
     * Constructor to create a Star object at a specific position on the screen
     * @param app a reference to the Game object that created this object
     * @param x the x coordinate of this object on the screen
     * @param y the y coordinate of this object on the screen
     */
    public Star(Game app, int x, int y, int r, int b, int g) {
        this.app = app; // store a reference to the main game object

        // store the x and y coordinates of this object on the screen
        this.x = x;
        this.y = y;

        this.r = r;
        this.b = b;
        this.g = g;

        this.speedX = 9;
        this.speedY = 9; 

        this.width = 50;
        this.height = 50;

    
    }

    public Star(Game app, int x, int y) {
        this.app = app; // store a reference to the main game object

        // store the x and y coordinates of this object on the screen
        this.x = x;
        this.y = y;

        this.r = (int) (Math.random() * 255);
        this.g = (int) (Math.random() * 255);
        this.b = (int) (Math.random() * 255);

        this.speedX = (int) ((Math.random() * Star.MAX_SPEED) + 1)*2 - Star.MAX_SPEED;
        this.speedY = (int) ((Math.random() * Star.MAX_SPEED) + 1)*2 - Star.MAX_SPEED;

        this.width = 50;
        this.height = 50;

    }

    /**
     * Draw this star's image to the screen at the appropriate coordinates
     */
    public void draw() {
        // draw this object's image at its x and y coordinates
        //this.app.ellipseMode(PApplet.CENTER); // setting so the image is drawn centered on the specified x and y coordinates

        
        //app.ellipseMode(app.CENTER);

        app.fill(this.r, this.g, this.b);
        app.ellipse(this.x, this.y, this.width, this.height); // draw an ellipse at x=250, y=150

        this.setX(this.x+speedX);
        this.setY(this.y+speedY);
    }

    public void setX(int newX) {
        if (newX >= app.height || newX <= 0) {
            this.speedX = -this.speedX;
        }
        else {
            this.x= newX;
        }
    } 

    public void setY(int newY) {
        if (newY >= app.height || newY <= 0) {
            this.speedY = -this.speedY;
        }
        else {
            this.y = newY;
        }
    } 
    
    public boolean getLoss(){
        boolean loss = false;
        int l = this.x - this.width/2; // the left edge's x coord

        if(l <= 0){
            loss = true;
        }

        return loss;
        
    }
    
    /**
     * Randomly move the star.
     */

    /**
     * Determines whehter a given x, y coordinate overlaps with this Star.
     * @param x The x coordinate of interest.
     * @param y The y coordinate of interest.
     * @param fudgeFactor An amount by which to expand the area we consider overlap
     * @return Boolean true if the x,y coordinate overlaps with this star, false otherwise.
     */
    public boolean overlaps(int x, int y, int rHeight) {
        // get the coordinates of all edges of this Star's image
        boolean xCollision = false;
        boolean yCollision = false;

        int l = this.x - this.width/2; // the left edge's x coord

        if(l <= x && !(l==0)){
            xCollision = true;
        }

        if(this.y > y - rHeight/2 && this.y < y + rHeight/2){
            yCollision = true;
        }

        if(xCollision && yCollision){
            
             this.speedX = -this.speedX;
             this.speedY = -this.speedY;
             draw();
    
        }
        return (xCollision && yCollision);
    }

}
