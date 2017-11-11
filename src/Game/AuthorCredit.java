package Game;
import java.awt.*;
import javax.swing.*;

public class AuthorCredit extends JFrame
{
	private static final long serialVersionUID = 5471277427641457313L;
	JLabel tittle =new JLabel("DinoRun v1.0");
	public AuthorCredit(){
	Font myFont = new Font("Terminator Two", Font.BOLD, 20);
	JFrame credit = new JFrame("About us");
	credit.setVisible(true);
	credit.setSize(250,200);
	credit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	credit.setLocationRelativeTo(null);
	credit.setLayout(new GridLayout(6,1));
	credit.add(tittle);
	tittle.setFont(myFont);
	tittle.setHorizontalAlignment(SwingConstants.CENTER);
   
	credit.add(new JLabel("Project Leader by Khairulamirin"){
		private static final long serialVersionUID = 177331;
	{	
		setHorizontalAlignment(SwingConstants.CENTER);
    }});
	
	credit.add(new JLabel("Graphics Designer by Amirul"){	
		private static final long serialVersionUID = 180495;
	{	
		setHorizontalAlignment(SwingConstants.CENTER);
    }});
	
	credit.add(new JLabel("Graphics Visual by Hariz"){
		private static final long serialVersionUID = 178546;

	{	
		setHorizontalAlignment(SwingConstants.CENTER);
    }});	
	credit.add(new JLabel("Sound FX by Fakrullah"){
		private static final long serialVersionUID = 180670;
	{	
		setHorizontalAlignment(SwingConstants.CENTER);
    }});
	
	credit.add(new JLabel("Documentation by Alif"){
		private static final long serialVersionUID = 180159;

	{	
		setHorizontalAlignment(SwingConstants.CENTER);
    }});
	}
}
