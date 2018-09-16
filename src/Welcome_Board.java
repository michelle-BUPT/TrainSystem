import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;
/**
 * A welcome frame shown first
 * @author Administrator
 *
 */
public class Welcome_Board extends JFrame {
	status status = new status();//The system's status
	/**
	 * A constructor of this class. 
	 * The operations in the constructor are aimed to build up a relative frame 
	 * and to describe the functions of components 
	 * (including several panels and buttons) in that frame.
	 * @param admin
	 */
	public Welcome_Board(boolean admin){
		System.out.println(status.driver.get(5).name);
		System.out.println(status.driver.size());
		JButton admin_center = new JButton("Admin_Center");
		admin_center.setBackground(new Color(250,191,137));
		admin_center.setUI(new BasicButtonUI());
		admin_center.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				admin_center frame = new admin_center(status);
				frame.setVisible(true);
				frame.setTitle("Control_Center");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
			}
			
		});
		JButton station = new JButton("Station");
		
		station.setBackground(new Color(250,191,137));
		station.setUI(new BasicButtonUI());
		station.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				station_board frame = new station_board(status);
				frame.setVisible(true);
				frame.setTitle("Control_Center");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
			}
		});
		JButton driver_train = new JButton("TrainBoard");
		driver_train.setBackground(new Color(168,241,238));
		driver_train.setUI(new BasicButtonUI());
		driver_train.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				train_board frame = new train_board(status);
				frame.setVisible(true);
				frame.setTitle("Control_Center");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
			}
			
		});
		JButton setting = new JButton("setting");
		setting.setBackground(new Color(168,241,238));
		setting.setUI(new BasicButtonUI());
		setting.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setting_board frame = new setting_board(status);
				frame.setSize(300,200);
				frame.setVisible(true);
				//frame.setTitle("Control_Center");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
			}
			
		});
		JButton driver_info = new JButton("Driver information");
		driver_info.setBackground(new Color(150,245,174));
		driver_info.setUI(new BasicButtonUI());
		driver_info.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				driverinfo_board frame = new driverinfo_board(status);
				frame.setVisible(true);
				frame.setTitle("Control_Center");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
			}
			
			
		});
		JButton train_info = new JButton("Train information");
		train_info.setBackground(new Color(150,245,174));
		train_info.setUI(new BasicButtonUI());
		train_info.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				traininfo_board frame = new traininfo_board(status);
				frame.setVisible(true);
				frame.setTitle("Control_Center");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
			}
			
		});
		if(admin){
			//this.setLayout(new GridLayout(7,1));
			//this.add(new JLabel("Train Management System"));
			this.getContentPane().add(BorderLayout.NORTH,new JLabel(new ImageIcon("./pic/MainGui.jpg")));
			JPanel welbuttons = new JPanel();
			welbuttons.setLayout(new GridLayout(6,1));
			welbuttons.add(admin_center);
			welbuttons.add(station);
			welbuttons.add(driver_train);
			welbuttons.add(setting);
			welbuttons.add(driver_info);
			welbuttons.add(train_info);
			this.getContentPane().add(BorderLayout.CENTER,welbuttons);
			//this.add(admin_center);
			//this.add(station);
			//this.add(driver_train);
			//this.add(setting);
			//this.add(driver_info);
			//this.add(train_info);
		}else{
			this.setLayout(new GridLayout(3,1));
			this.add(new JLabel("Train Management System"));
			this.add(station);
			this.add(driver_train);
		}
		
		this.setSize(600, 500);
		this.setVisible(true);
	}
}
