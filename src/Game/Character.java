package Game;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Character {
	int x, dx, y, dy;
	Image dino;
	
	//Non-args constructor
	public Character(){
		ImageIcon img =  new ImageIcon(getClass().getResource("/images/Dino.gif")); // Import image file
		dino = img.getImage(); // set Image
		
		//Set dino location
		x = 80;
		y = 315;
	}
	
	//Dino jump movement
	public void jump(){
		y = y + dy;
	}
	
	//Set y coordinate
	public void setY(int y){
		this.y = y;
	}
	
	//Return x coordinate
	public int getX(){
		return x;
	}
	
	//Return y coordinate
	public int getY(){
		return y;
	}
	
	//Return dino image
	public Image getImage(){
		return dino;
	}
	
	//ActionListener	
	//If spacekey is pressed, dino is jump
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE)
			dy = 1;
	}
	
	//If space key is released, set dy=0
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE)
			dy = 0;
	}
	
	//Return the Rectangle object
	/*
	 * This method will create an rectangle object from dino image
	 */
	public Rectangle getDimension(){
		
		//The x value determine where dino stand and y value for coordinate of y when dino is jump
		return new Rectangle(x+80,y+100,35,40);
	} 
}
