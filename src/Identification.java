
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 * The frame of identification process
 * @author Administrator
 *
 */
public class Identification extends JFrame{
	private static final long serialVersionUID = -4734699270020175476L;
	/**
	 * A constructor for this class. 
	 * The operations in the method are aimed to build up a relative frame 
	 * and to describe the functions of components 
	 * (including two text fields and two buttons) in that frame.
	 */
	public Identification(){
		this.setLayout(new GridLayout(5,1));
		this.add(new JLabel("Please login in to the system."));
		final JTextField username = new JTextField("Username");
		final JTextField password = new JTextField("Password");
		JButton login = new JButton("Login in");
		login.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(identify(username.getText(),password.getText())){
					Welcome_Board frame = new Welcome_Board(true);
					frame.setVisible(true);
					frame.setTitle("Control_Center");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLocationRelativeTo(null);
				}
				else{
					JOptionPane.showMessageDialog(null, "Wrong username or password,try again!");
				}
			}
			
		});
		JButton travel = new JButton("Travel Entry");
		travel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Welcome_Board frame = new Welcome_Board(false);
				frame.setVisible(true);
				frame.setTitle("Control_Center");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
			}
			
		});
		this.add(username);
		this.add(password);
		this.add(login);
		this.add(travel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * To identify the correctness of the input username and password.
	 * @param username
	 * 		The user name to be identified
	 * @param password
	 * 		The password to be identified
	 * @return
	 * 		The correctness of identification
	 */
	public boolean identify(String username,String password){
		try{
			Scanner fin=new Scanner (new FileReader (new File("./database/user")));
			do{
				if(fin.next().equals(username) && fin.next().equals(password)){
					this.setVisible(false);
					fin.close();
					return true;
				}
			}while(fin.hasNextLine());
			fin.close();
		}catch(Exception e) {}
		return false;
	}
	public static void main(String args[]){
		Identification frame = new Identification();
		frame.setSize(300, 200);
		frame.setVisible(true);
		frame.setTitle("Identification");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	
	
}
