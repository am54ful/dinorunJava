package Game;
import java.awt.*;
import javax.swing.*;
public class Obstacle {
	
	//Initialize variables
	int x,y;
	Image stone;
	
	//Non-constructor
	public Obstacle(int startX){
		x = startX;
		y = 370;
		
		//Get the image resource
		ImageIcon img = new ImageIcon(getClass().getResource("/images/Stone.png"));
		stone = img.getImage();
	}
	
	//Stone movement speed
	public void move(){
		x = x - 10;
	}
	
	//Return x position
	public int getX(){
		return x;
	}
	
	// Return y position
	public int getY(){
		return y;
	}
	
	//Set x position
	public void setX(int x){
		this.x = x;
	}
	
	//Return stone image
	public Image getImage(){
		return stone;
	}
	
	//Return rectangle object
	/*
	 * This method will create an rectangle object from stone image
	 */
	public Rectangle getDimension(){
		
		//The x value is the stone location
		return new Rectangle(x+10,390,80,90);
	}
	
	
}
