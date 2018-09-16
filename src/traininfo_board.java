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
 * /**
 * The frame displaying the information of trains.
 * @author Administrator
 *
 */
public class traininfo_board extends JFrame implements information_display{
	status data;//The system's status
	public traininfo_board(status status){
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
		JButton addTrain = new JButton("Train regist");
		JButton deletTrain = new JButton("Train delet");
		addTrain.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addTrain();
			}
		});
		
		deletTrain.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				deletTrain();
			}
			
		});
		
		JPanel panel = new JPanel();
		panel.add(addTrain);
		panel.add(deletTrain);
		this.getContentPane().add(BorderLayout.SOUTH,panel);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	/**
	 * To get a table according to current data so that the information can be displayed.
	 */
	public JTable setTable(){
		Object [][] tableData = new Object[data.train.size()][5];
		Object [] columnTitle = {"No.","Name","Duty Route","Duty Journey","Duty Driver"};
		int i = 0;
		for(status._ntrain a:data.train){
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
						if(b.train.get(j).equals(a.name)){
							tableData[i][2] = k;
							tableData[i][3] = j+1;
							tableData[i][4] = b.driver.get(j);
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
	 * To allow administrator to add a train with a driver's name.
	 */
	void addTrain(){
		final JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("Train_Register");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(3,1));
		frame.add(new JLabel("Please enter a train's name"));
		final JTextField trainname = new JTextField();
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(file._ntrain a:data.train){
					if(trainname.getText().equals(a.name)){
						JOptionPane.showMessageDialog(null, "There is a train with the samename, try again!");
						return;
					}
				}
				data.train.add(data.new _ntrain(trainname.getText(),0));
				data.saveTrain(data.train);
				//data.driver = data.getDriver();
				frame.dispose();
				clear();
				init(data);
				
			}
			
		});
		frame.add(trainname);
		frame.add(confirm);
		frame.setSize(300,200);;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	/**
	 * To allow administrator to delete a driver with a driver's name.
	 */
	void deletTrain(){
		final JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("Train_Delet");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(3,1));
		frame.add(new JLabel("Please enter a train's name"));
		final JTextField trainname = new JTextField();
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean flage = true;
				for(int i = 0; i < data.train.size(); i++){
					file._ntrain a = data.train.get(i);
					if(trainname.getText().equals(a.name)){
						if(a.vis == 1){
							JOptionPane.showMessageDialog(null, "This train is on duty now!");
							return;
						}else{
							data.train.remove(i);
							flage = false;
							break;
						}
						
					}
				}
				if(flage){
					JOptionPane.showMessageDialog(null, "Not such a train!");
					return;
				}
				data.saveTrain(data.train);
				//data.driver = data.getDriver();
				frame.dispose();
				clear();
				init(data);
				
			}
			
		});
		frame.add(trainname);
		frame.add(confirm);
		frame.setSize(300,200);;
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	boolean isExit(String name){
		for(file._ntrain a:data.train){
			if(a.name.equals(name)) return true;
		}
		return false;
	}
}
