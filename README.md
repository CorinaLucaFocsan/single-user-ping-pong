# Single User Ping Pong

## Summary
Used Object Oriented Principles to create a Ping Pong game for a single user with 2 balls. One ball will randomly change colors and speed every time the game is played.

## Game.java

Uses inheritance to extend from the PApplet class. The game is set up by creating a window where the game will be displayed as well as importing a background photo and sound effects. Two star objects are that will be bouncing around the screen are initialized. The game keeps track of the X and Y coordinates of the user's mouse and will respond to user movement. If the moving star objects fall within the range of the user's paddle, the star object will keep moving and invert the direction, else it will be counted as a miss. The game will also initialize a "score" variable and a "lives" variable to track how many times the user has missed hitting the ball with the paddle. After 3 misses the game terminates.

## Star.java
For this game I created a moving ball-like object, which is encapsulated in the star class. The star object is drawn and speedily deleted to give the impression of movement and trajectory. It has added features such as a color which can be set or randomly selected. In addition, there is an "overlaps" function that will detect when the star object collides with the borders of the display window or the user-controlled paddle.

