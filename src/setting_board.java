import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * A frame of the setting operations
 * @author Administrator
 *
 */
public class setting_board extends JFrame {
	status data;//The system's status
	status._route route ;//A variable to store the route which is waited to edit
	
	public setting_board(status status){
		
		this.data = status;
		
		
		choose();
	}
	
	/**
	 * To initialize the frame to a original frame
	 */
	public void init(){
		this.getContentPane().removeAll();
		this.repaint();
	}
	
	/**
	 * To construct a frame for users to choose operations
	 */
	public void choose(){
		this.setLayout(new GridLayout(5,1));
		this.add(new JLabel("Select Setting Function"));
		JButton addRoute = new JButton("Add Route");
		addRoute.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				init();
				stopNum();
			}
			
		});
		//JButton editRoute = new JButton("Edit Route");
		JButton deleteRoute = new JButton("Delete Route");
		deleteRoute.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				init();
				deleteRoute();
			}
			
		});
		JButton addJourney = new JButton("Add Journey");
		addJourney.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				init();
				chooseRoute_journey();
			}
			
		});
		JButton editJourney = new JButton("Edit Journey");
		editJourney.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				init();
				selectRoute_edit();
			}
			
		});
		//JButton deleteJourney = new JButton("Delete Journey");
		this.add(addRoute);
		this.add(addJourney);
		//this.add(editRoute);
		this.add(editJourney);
		this.add(deleteRoute);
		//this.add(deleteJourney);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * To allow users to enter the number of stops wanted to be set.
	 */
	public void stopNum(){
		route = data.newroute();
		
		this.setLayout(new GridLayout(3,1));
		this.add(new JLabel("Please input the number of stops"));
		final JTextField stopnum = new JTextField();
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if((Integer.parseInt(stopnum.getText()))==1)
				{
					JOptionPane.showMessageDialog(null, "Only a number larger than two is available.");
					return;
				}
				route.stopnum = Integer.parseInt(stopnum.getText());
			
				init();
				setStop();
			}
			
		});
		this.add(stopnum);
		this.add(next);
		this.pack();
		this.setVisible(true);
	}
	/**
	 * To allow users to set stops.
	 */
	public void setStop(){
		int num = route.stopnum;
		final ArrayList<JTextField> stoparray = new ArrayList<JTextField>();
		this.setLayout(new GridLayout(3,1));
		this.add(new JLabel("Please input stop name"));
		JPanel namepane = new JPanel();
		namepane.setLayout(new GridLayout(1,num));
		for(int i=0;i<num;i++){
			JTextField tmp = new JTextField();
			namepane.add(tmp);
			stoparray.add(tmp);
		}
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Integer.parseInt(stoparray.get(0).getText()) != 0){
					JOptionPane.showMessageDialog(null, "The beginning stop must be the center station (number 0)");
					return;
				}
				for(JTextField jtf:stoparray){
					route.stop.add(Integer.parseInt(jtf.getText()));
				}
				for(status._route a:data.route){
					boolean flage = true;
					if(a.stopnum == route.stopnum){
						for(int i = 0; i < a.stopnum; i++){
							if(a.stop.get(i) != route.stop.get(i)){
								flage = false;
								break;
							}
						}
					}
					if(flage && (a.stopnum == route.stopnum)){
						JOptionPane.showMessageDialog(null, "Reduplicated Route, try again!");
						return;
					}
				}
				data.route.add(route);
				init();
				choose();
			}
			
		});
		this.add(namepane);
		this.add(next);
		this.pack();
		this.setVisible(true);
	}
	/**
	 * Choose the journey to be set.
	 */
	public void chooseRoute_journey(){
		this.setLayout(new GridLayout(3,1));
		this.add(new JLabel("choose route"));
		ArrayList<String> namelist = new ArrayList<String>();
		for(int i=1;i<=data.route.size();i++){
			namelist.add("Route "+i);
		}
		final JComboBox jcb = new JComboBox(namelist.toArray());
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				init();
				System.out.println(jcb.getSelectedIndex());
				setJourney(jcb.getSelectedIndex());
			}
			
		});
		this.add(jcb);
		this.add(next);
		this.pack();
		this.setVisible(true);
	}
	/**
	 * To set the specific journey.
	 * @param index
	 * 		The journey No.
	 */
	public void setJourney(final int index){
		final status._route route = data.route.get(index);
		route.junynum++;
		
		this.setTitle("Add Journey");
	
		this.setLayout(new GridLayout(7,1));
		this.add(new JLabel("Set Journey Info"));
		JPanel stoppane = new JPanel();
		stoppane.setLayout(new GridLayout(1,route.stopnum+1));
		for(int stop: route.stop){
			stoppane.add(new JLabel((stop==0)?"Central Station":("Stop "+stop)));
		}
		JPanel outpane = new JPanel();
		outpane.setLayout(new GridLayout(1,route.stopnum+1));
		final ArrayList<JTextField> outlist = new ArrayList<JTextField>();
		outpane.add(new JLabel("outtime"));
		for(int i=0;i<route.stopnum;i++){
			JTextField outtime = new JTextField();
			outlist.add(outtime);
			outpane.add(outtime);
		}
		JPanel repane = new JPanel();
		repane.setLayout(new GridLayout(1,route.stopnum+1));
		repane.add(new JLabel("returntime"));
		final ArrayList<JTextField> relist = new ArrayList<JTextField>();
		for(int i=0;i<route.stopnum;i++){
			JTextField retime = new JTextField();
			relist.add(retime);
			repane.add(retime);
		}
		JPanel assignTrain = new JPanel();
		assignTrain.setLayout(new GridLayout(1,2));
		assignTrain.add(new JLabel("Assign Train"));
		ArrayList<String> trainlist = new ArrayList<String>();
		for(status._ntrain train:data.train){
			if(train.vis==0){
				trainlist.add(train.name);
			}
		}
		final JComboBox trainbox = new JComboBox(trainlist.toArray());
		assignTrain.add(trainbox);
		JPanel assignDriver = new JPanel();
		ArrayList<String> driverlist = new ArrayList<String>();
		for(status._driver driver:data.driver){
			if(driver.vis==0){
				driverlist.add(driver.name);
			}
		}
		assignDriver.setLayout(new GridLayout(1,2));
		assignDriver.add(new JLabel("Assign Driver"));
		final JComboBox driverbox = new JComboBox(driverlist.toArray());
		assignDriver.add(driverbox);
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ArrayList<status.time> tmplist = new ArrayList<status.time>();
				ArrayList<status.time> seclist = new ArrayList<status.time>();
				for(JTextField outtime:outlist){
					String[] s = outtime.getText().split(":");
					tmplist.add(data.new time(Integer.parseInt(s[0]),Integer.parseInt(s[1])));
				}
				for(int i = route.stopnum-1;i>=0;i--){
					String[] s = relist.get(i).getText().split(":");
					seclist.add(data.new time(Integer.parseInt(s[0]),Integer.parseInt(s[1])));
				}
				boolean flag = false;
				for(ArrayList<status.time> timelist:route.timetableout){
					if(timelist.get(0).time()==tmplist.get(0).time()){
						JOptionPane.showMessageDialog(null, "Duplicate journey");
						flag = true;
						init();
						setJourney(index);
						break;
					}
				}
				
				if(!flag){
					route.timetableout.add(tmplist);
					route.timetableret.add(seclist);
					String trainname = (String)trainbox.getSelectedItem();
					route.train.add(trainname);
					for(status._ntrain train:data.train){
						if(train.name.equals(trainname)){
							train.vis = 1;
							break;
						}
					}
					String drivername = (String)driverbox.getSelectedItem();
					route.driver.add(drivername);
					for(status._driver driver:data.driver){
						if(driver.name.equals(drivername)){
							driver.vis = 1;
							break;
						}
					}
					
					route.train.add(trainname);
					route.driver.add(drivername);
					data.saveRoute(data.route);
					data.saveTrain(data.train);
					data.saveDriver(data.driver);
					init();
					chooseRoute_journey();
				}
				
			}
			
		});
		
		this.add(stoppane);
		this.add(outpane);
		this.add(repane);
		this.add(assignTrain);
		this.add(assignDriver);
		this.add(next);
		this.pack();
		this.setVisible(true);
	}
	/**
	 * To delete a specific route.
	 */
	public void deleteRoute(){
		this.setLayout(new GridLayout(3,1));
		this.add(new JLabel("Delete Route"));
		ArrayList<String> routename = new ArrayList<String>();
		for(int i=0;i<data.route.size();i++){
			routename.add("route "+(i+1));
		}
		final JComboBox jcb = new JComboBox(routename.toArray());
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				data.removeRoute(jcb.getSelectedIndex());
				init();
				choose();
			}
		});
		this.add(jcb);
		this.add(next);
		this.pack();
		this.setVisible(true);
	}
	/**
	 * To select a specific route.
	 */
	public void selectRoute_edit(){
		this.setLayout(new GridLayout(3,1));
		this.add(new JLabel("Select Route"));
		//int i[] = new int[100];
		ArrayList<String> routename = new ArrayList<String>();
		for(status._route route:data.route){
			if(route.junynum!=0){
				//int index = map.size();
			//	i[index] = data.route.indexOf(route);
				routename.add("route "+(data.route.indexOf(route)+1));
			}
		}
		final JComboBox jcb = new JComboBox(routename.toArray());
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] s = new String[2];
				s = ((String)jcb.getSelectedItem()).split(" ");
				init();
				selectJourney(Integer.parseInt(s[1])-1);
			}
			
		});
		this.add(jcb);
		this.add(next);
		this.pack();
		this.setVisible(true);
	}
	/**
	 * To select a specific journey.
	 * @param index
	 * 		The journey No.
	 */
	public void selectJourney(final int index){
		this.setLayout(new GridLayout(3,1));
		this.add(new JLabel("Select Journey"));
		//int i[] = new int[100];
		ArrayList<String> journeyname = new ArrayList<String>();
		for(int i=0;i<data.route.get(index).junynum;i++){
				journeyname.add("Journey "+(i+1));
			
		}
		final JComboBox jcb = new JComboBox(journeyname.toArray());
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] s = new String[2];
				s = ((String)jcb.getSelectedItem()).split(" ");
				init();
				//selectJourney();
				editJourney(index,Integer.parseInt(s[1])-1);
			}
			
		});
		this.add(jcb);
		this.add(next);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * To edit a specific journey.
	 * @param rindex
	 * 		The route No.
	 * @param jindex
	 * 		The journey No.
	 */
	public void editJourney(int rindex,final int jindex){
		final status._route route = data.route.get(rindex);
		//route.junynum++;
		this.setLayout(new GridLayout(7,1));
		this.add(new JLabel("Update Journey Info"));
		JPanel stoppane = new JPanel();
		stoppane.setLayout(new GridLayout(1,route.stopnum+1));
		for(int stop: route.stop){
			stoppane.add(new JLabel((stop==0)?"Central Station":("Stop "+stop)));
		}
		JPanel outpane = new JPanel();
		outpane.setLayout(new GridLayout(1,route.stopnum+1));
		final ArrayList<JTextField> outlist = new ArrayList<JTextField>();
		outpane.add(new JLabel("outtime"));
		for(int i=0;i<route.stopnum;i++){
			JTextField outtime = new JTextField();
			outtime.setText(route.timetableout.get(jindex).get(i).showTime());
			outlist.add(outtime);
			
			outpane.add(outtime);
		}
		JPanel repane = new JPanel();
		repane.setLayout(new GridLayout(1,route.stopnum+1));
		repane.add(new JLabel("returntime"));
		final ArrayList<JTextField> relist = new ArrayList<JTextField>();
		for(int i=0;i<route.stopnum;i++){
			JTextField retime = new JTextField();
			retime.setText(route.timetableret.get(jindex).get(route.stopnum-i-1).showTime());
			relist.add(retime);
			repane.add(retime);
		}
		JPanel assignTrain = new JPanel();
		assignTrain.setLayout(new GridLayout(1,2));
		assignTrain.add(new JLabel("Assign Train"));
		ArrayList<String> trainlist = new ArrayList<String>();
		for(status._ntrain train:data.train){
			if(train.vis==0){
				trainlist.add(train.name);
			}
		}
		final JComboBox trainbox = new JComboBox(trainlist.toArray());
		assignTrain.add(trainbox);
		JPanel assignDriver = new JPanel();
		ArrayList<String> driverlist = new ArrayList<String>();
		for(status._driver driver:data.driver){
			if(driver.vis==0){
				driverlist.add(driver.name);
			}
		}
		assignDriver.setLayout(new GridLayout(1,2));
		assignDriver.add(new JLabel("Assign Driver"));
		final JComboBox driverbox = new JComboBox(driverlist.toArray());
		assignDriver.add(driverbox);
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArrayList<status.time> tmplist = new ArrayList<status.time>();
				ArrayList<status.time> seclist = new ArrayList<status.time>();
				for(JTextField outtime:outlist){
					String[] s = outtime.getText().split(":");
					tmplist.add(data.new time(Integer.parseInt(s[0]),Integer.parseInt(s[1])));
				}
				for(int i = route.stopnum-1;i>=0;i--){
					String[] s = relist.get(i).getText().split(":");
					seclist.add(data.new time(Integer.parseInt(s[0]),Integer.parseInt(s[1])));
				}
				route.timetableout.set(jindex, tmplist);
				//route.timetableout.add(tmplist);
				route.timetableret.set(jindex,seclist);
				String s1=route.train.get(jindex);
				for(status._ntrain train:data.train){
					if(train.name.equals(s1)){
						train.vis = 0;
						break;
					}
				}
				
				
				String s2=route.driver.get(jindex);
				for(status._driver train:data.driver){
					if(train.name.equals(s2)){
						train.vis = 0;
						break;
					}
				}

				
				String trainname = (String)trainbox.getSelectedItem();
				
				route.train.add(trainname);
				for(status._ntrain train:data.train){
					if(train.name.equals(trainname)){
						train.vis = 1;
						break;
					}
				}
				String drivername = (String)driverbox.getSelectedItem();
				route.driver.add(drivername);
				for(status._driver driver:data.driver){
					if(driver.name.equals(drivername)){
						driver.vis = 1;
						break;
					}
				}
				
				route.train.set(jindex,trainname);
				route.driver.set(jindex,drivername);
				data.saveRoute(data.route);
				data.saveTrain(data.train);
				data.saveDriver(data.driver);
				init();
				choose();
			}
			
		});
		
		this.add(stoppane);
		this.add(outpane);
		this.add(repane);
		this.add(assignTrain);
		this.add(assignDriver);
		this.add(next);
		this.pack();
		this.setVisible(true);
	}
	
	boolean DriverisOccupied(String name){
		for(file._driver a:data.driver){
			if(a.name.equals(name) && a.vis == 1) return true;
		}
		return false;
	}
	
	boolean TrainisOccupied(String name){
		for(file._ntrain a:data.train){
			if(a.name.equals(name) && a.vis == 1) return true;
		}
		return false;
	}
}
