package Game;

import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class Frame{
	//Main method
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		JFrame frame = new JFrame();
		frame.setTitle("DinoRun");
		//add board to frame
		frame.add(new Board());
		frame.setSize(855,520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
	}
}
 