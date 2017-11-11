package Game;

import java.awt.*;
import javax.swing.*;

public class Cloud {
	
	//Set variables
	int x;
	Image cloud;
	
	//Non-args constructor
	public Cloud(int startX){
		x = startX;
		//Get the image resource
		ImageIcon img = new ImageIcon(getClass().getResource("/images/Cloud2.png"));
		cloud = img.getImage();
	}
	
	//Cloud movement speed
	public void move(){
		x = x - 2;
	}
	
	//Return x
	public int getX(){
		return x;
	}
	
	//Set x
	public void setX(int x){
		this.x=x;
	}
	
	//Return image
	public Image getImage(){
		return cloud;
	}
}
