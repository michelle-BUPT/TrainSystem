import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
/**
 * The frame displaying the information of drivers.
 * @author Administrator
 *
 */
public class driverinfo_board extends JFrame implements information_display{
	status data;//The system's status
	
	public driverinfo_board(status status){
		this.init(status);
	}
	/**
	 * To clear all components in the frame and repaint it.
	 */
	public void clear(){
		this.getContentPane().removeAll();
		this.repaint();
	}
	/**
	 * Initialize the frame and describe the components
	 * @param status
	 * 		The system's status
	 */
	public void init(status status){
		this.data = status;
		this.getContentPane().add(BorderLayout.NORTH,new JScrollPane(setTable()));
		//this.setSize(900,600);
		JButton addDriver = new JButton("Driver regist");
		JButton deletDriver = new JButton("Driver delet");
		addDriver.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addDriver();
			}
		});
		
		deletDriver.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				deletDriver();
			}
			
		});
		
		JPanel panel = new JPanel();
		panel.add(addDriver);
		panel.add(deletDriver);
		this.getContentPane().add(BorderLayout.SOUTH,panel);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	/**
	 * To get a table according to current data so that the information can be displayed.
	 */
	public JTable setTable(){
		Object [][] tableData = new Object[data.driver.size()][5];
		Object [] columnTitle = {"No.","Name","Duty Route","Duty Journey","Duty Train"};
		int i = 0;
		for(status._driver a:data.driver){
			tableData[i][0] = i+1;
			tableData[i][1] = a.name;
			if(a.vis == 0){
				tableData[i][2] = "NULL";
				tableData[i][3] = "NULL";
				tableData[i][4] = "NULL";
			}else{
				int k = 1;
				for(status._route b:data.route){
					for(int j = 0;j < b.junynum; j++){
						if(b.driver.get(j).equals(a.name)){
							tableData[i][2] = k;
							tableData[i][3] = j+1;
							tableData[i][4] = b.train.get(j);
						}
					}
					k++;
				}
			}
			i++;
		}
		JTable table = new JTable(tableData,columnTitle);
		return table;
	}
	/**
	 * To allow administrator to add a driver with a driver's name.
	 */
	void addDriver(){
		final JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("Driver_Register");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(3,1));
		frame.add(new JLabel("Please enter a driver's name"));
		final JTextField drivername = new JTextField();
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(file._driver a:data.driver){
					if(drivername.getText().equals(a.name)){
						JOptionPane.showMessageDialog(null, "There is a driver with the samename, try again!");
						return;
					}
				}
				data.driver.add(data.new _driver(drivername.getText(),0));
				data.saveDriver(data.driver);
				//data.driver = data.getDriver();
				frame.dispose();
				clear();
				init(data);
				
			}
			
		});
		frame.add(drivername);
		frame.add(confirm);
		frame.setSize(300,200);;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	/**
	 * To allow administrator to delete a driver with a driver's name.
	 */
	void deletDriver(){
		final JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("Driver_Delet");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(3,1));
		frame.add(new JLabel("Please enter a driver's name"));
		final JTextField drivername = new JTextField();
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean flage = true;
				for(int i = 0; i < data.driver.size(); i++){
					file._driver a = data.driver.get(i);
					if(drivername.getText().equals(a.name)){
						if(a.vis == 1){
							JOptionPane.showMessageDialog(null, "This driver is on duty now!");
							return;
						}else{
							data.driver.remove(i);
							flage = false;
							break;
						}
						
					}
				}
				if(flage){
					JOptionPane.showMessageDialog(null, "Not such a driver!");
					return;
				}
				data.saveDriver(data.driver);
				//data.driver = data.getDriver();
				frame.dispose();
				clear();
				init(data);
				
			}
			
		});
		frame.add(drivername);
		frame.add(confirm);
		frame.setSize(300,200);;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	boolean isExit(String name){
		for(file._driver a:data.driver){
			if(a.name.equals(name)) return true;
		}
		return false;
	}
}
