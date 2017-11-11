package Game;

import java.awt.*;  
import java.awt.event.*;
import java.awt.geom.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

//This is the board of the game which contain dino,stone and cloud object
//Inherit ActionListener and Runnable class
//Runnable is used for animate the dino jump
public class Board extends JPanel implements ActionListener, Runnable{
	
	private static final long serialVersionUID = -3128331949717611490L;
	//Initialize variable
	Character dino;
	Image background;
	Timer time; // Control all movement in the game
	Thread animator; //Animate dino character when jump
	Frame frame = new Frame();
	int v=315;
	Obstacle stone1;
	Obstacle stone2;
	Cloud cloud1;
	Cloud cloud2;
	int randomNum, randomNum2;
	int score = 0;
	Font myFont = new Font("ArialBlack", Font.BOLD, 32);
	boolean isLost = false;
	JFrame secondFrame = new JFrame();
	JFrame thirdFrame = new JFrame();
	
	AudioInputStream backgroundMusic = AudioSystem.getAudioInputStream(getClass().getResource("/music/DinoSound.wav"));
	Clip audioBG = AudioSystem.getClip();
	AudioInputStream gameOverMusic = AudioSystem.getAudioInputStream(getClass().getResource("/music/GameOver.WAV"));
	Clip overMusic = AudioSystem.getClip();	
	
	//Non-constructor
	public Board() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		audioBG.open(backgroundMusic);
		overMusic.open(gameOverMusic);
		dino = new Character();
		addKeyListener(new ActionListen());
		setFocusable(true);
		ImageIcon img =  new ImageIcon(getClass().getResource("/images/Background.jpg")); //Import image
		background = img.getImage();
		checkRandomNum();
	
		stone1 = new Obstacle(randomNum);
		stone2 = new Obstacle(randomNum2);
		cloud1 = new Cloud(900);
		cloud2 = new Cloud(1400);
		 time = new Timer(15, this);
		JLabel howToPlay = new JLabel("To jump, press [spacebar]");
		howToPlay.setHorizontalAlignment(SwingConstants.CENTER);
		JButton start = new JButton("Start");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		panel.add(howToPlay);
		panel.add(start);
		add(panel);
		secondFrame.setTitle("How to play");
		secondFrame.add(panel);
		secondFrame.setSize(250,110);
		secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		secondFrame.setLocationRelativeTo(null);
		secondFrame.setVisible(true);
		secondFrame.setResizable(false);
		start.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	audioBG.start(); //play audio
		  		time.start(); // Star timer after user click start button
		  		secondFrame.dispose(); // Remove button frame
		      }
		    });
		
	}
	
	//Action performed
	public void actionPerformed(ActionEvent e){
		checkHit();
		dino.jump();
		cloud1.move();
		cloud2.move();
		stone1.move();
		stone2.move();
		repaint();
		
	}
	
	// Check intersection between dino and stones 
	// and set the game lost if any intersection occur
	private void checkHit() {
		
		Rectangle dinoD = dino.getDimension();
		Rectangle stone1D = stone1.getDimension();
		Rectangle stone2D = stone2.getDimension();
		if(testIntersection(dinoD, stone1D) ||testIntersection(dinoD, stone2D))
		isLost = true;
		
	}
	//Fi
	public static boolean testIntersection(Shape shapeA, Shape shapeB) {
		   Area areaA = new Area(shapeA);
		   areaA.intersect(new Area(shapeB));
		   return !areaA.isEmpty();
		}


	boolean a = false;	
	public void paint(Graphics graphic){
	
		//if the dino hit the stone
		if (isLost){
			overMusic.start();
			audioBG.stop();
			audioBG.setFramePosition(0);
			time.stop(); //Stop the timer
			
			//The third frame 
			JLabel label = new JLabel("Dino hit a rock! Game Over ");
			JPanel panel = new JPanel();
			JButton start = new JButton("Play Again");
			start.setMnemonic('g');
			JButton end = new JButton("End");
			end.setMnemonic('E');
			panel.add(label, BorderLayout.CENTER);
			panel.add(start, BorderLayout.NORTH);
			panel.add(end, BorderLayout.NORTH);
			thirdFrame.setResizable(false);
			thirdFrame.setTitle("End game");
			thirdFrame.add(panel);
			thirdFrame.setSize(250,110);
			thirdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			thirdFrame.setLocationRelativeTo(null);
			thirdFrame.setVisible(true);
			
			// Reset the timer and dino location
			dino.setY(315);
			time = new Timer(15, this);
			
			
			start.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			    	  overMusic.setFramePosition(0);
			    	  overMusic.stop();
			    	audioBG.start();
			  		checkRandomNum();
			  		stone1.setX(randomNum);
			  		stone2.setX(randomNum2);
			  		isLost=false;
			  		score=0;
			  		time.start(); //Start the game
			  		thirdFrame.dispose();
			      }
			    });
			end.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent e) {
			    	overMusic.setFramePosition(0);
			    	audioBG.setFramePosition(0);
			    	new AuthorCredit();
			      }
			    });
		}
		
		//Animate dino jump
		if (dino.dy==1 && a==false){
			a = true;
			animator = new Thread(this);
			animator.start();
		}
		super.paint(graphic);
			Graphics2D g = (Graphics2D) graphic;
			
			//Draw all images
			g.drawImage(background, 0, 0, null);
			g.setFont(myFont);
			g.setColor(Color.WHITE);
			g.drawImage(dino.getImage(), dino.getX(), v , null);
			g.drawImage(stone1.getImage(), stone1.getX(),370,null);
			g.drawImage(stone2.getImage(), stone2.getX(),370,null); 
			g.drawImage(cloud1.getImage(), cloud1.getX(),20,null);
			g.drawImage(cloud2.getImage(), cloud2.getX(),20,null);
			
			//Display score
			g.drawString("Score: "+score(),641,71);
			
			//If the stones pass after the frame, reset the x coordinate
			if (stone2.getX()<0){
					checkRandomNum();
					stone1.setX(randomNum);
					stone2.setX(randomNum2);
			}

			if (cloud2.getX()<0){
					cloud1.setX(900);
					cloud2.setX(1400);
			}
	}
	 
	public class ActionListen extends KeyAdapter{
		public void keyReleased(KeyEvent e){
			dino.keyReleased(e);
		}
		
		public void keyPressed(KeyEvent e){
			dino.keyPressed(e);
		}
	}
	
	//Control jump speed
	@Override
	public void run() {
		long beforeTime, timeDiff, sleep;
		
		beforeTime = System.currentTimeMillis();
		while(done==false){
			cycle();
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = 1-timeDiff;
			if(sleep<0)
				sleep = 1;
			try{
				Thread.sleep(sleep);
			} catch(Exception e)
			{}
			beforeTime = System.currentTimeMillis();
			
		}
		done = false;
		b = false;
		a= false;
		
	}
	//Set jump max high
	boolean b = false;
	boolean done = false;
	public void cycle(){
		if (b==false)
			v--;
		if(v==130)
			b=true;
		if(b==true && v<=315)
			v++;
		if (v==315)
			done=true;
		//Set y coordinate
		dino.setY(v);
	}
	
	// Create a random number
	public int generateRandomNum(){
		return (int) (Math.random()*(1200-850)+850);
	}
	
	
	// Check the two random number and make random location of stones
	public void checkRandomNum(){
		boolean isSameNumber=true;
		while(isSameNumber){
			randomNum = generateRandomNum();
			randomNum2 = generateRandomNum();
				if(randomNum>=randomNum2)
					isSameNumber = true;
				else if(randomNum2-randomNum<300){
					randomNum2 = randomNum2 +300;
					isSameNumber = false;
				}
					
			    else isSameNumber = false;
	}
		
		
}
	/*
	 * This method will set the score when the dino jump over the stones
	 */
	public int score(){
		
		//if the dino pass over stone1, increment the score by 1
		if (stone1.getX()<dino.getX() && stone1.getX()>69 &&dino.getY()<315  )
			score += 1;
		
		//if the dino pass over stone1, increment the score by 1
		if (stone2.getX()<dino.getX() && stone2.getX()>69 &&dino.getY()<315  )
			score += 1;
		
		//if the dino hit stone1 or stone2, decrement the score by 1
		/*
		 * Take note that if the dino pass over the stone, the score will increment by 1
		 * So, we want substract the score by 1 after the dino hit the stone
		 */
		if (isLost && dino.getY()<315 && stone2.getX()<dino.getX() && stone1.getX()<dino.getX())
			score=score-1;
		if(score<0)
			score=0;
		return score;
	}
}
