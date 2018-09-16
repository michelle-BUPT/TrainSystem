import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * The frame of the display screen in stations
 * @author Administrator
 *
 */
public class station_board extends JFrame{
	private status status;//A variable used to store the system's status
	public station_board(status status){
		this.status = status;
		selectRoute();
	}
	/**
	 * Initialize the GUI to the original state.
	 */
	public void init(){
		this.getContentPane().removeAll();
		this.repaint();
	}
	/**
	 * Select the route which is queried by users.
	 */
	public void selectRoute(){
		this.setLayout(new GridLayout(3,1));
		this.add(new JLabel("choose route"));
		ArrayList<String> namelist = new ArrayList<String>();
		for(int i=1;i<=status.route.size();i++){
			namelist.add("Route "+i);
		}
		final JComboBox jcb = new JComboBox(namelist.toArray());
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				init();
				selectStop(jcb.getSelectedIndex());
			}
			
		});
		this.add(jcb);
		this.add(next);
		this.pack();
		this.setVisible(true);
	}
	/**
	 * Select the stop which is queried by users.
	 * @param index
	 * 		The No. of the station required
	 */
	public void selectStop(final int index){
		status._route route = status.route.get(index);
		ArrayList<String> namelist = new ArrayList<String>();
		for(int stop: route.stop){
			if(stop==0){
				namelist.add("Central Station");
			}else{
				namelist.add("Stop "+ stop);
			}
		}
		this.setLayout(new GridLayout(3,1));
		this.add(new JLabel("Select Stop"));
		final JComboBox jcb = new JComboBox(namelist.toArray());
		this.add(jcb);
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				init();
				stationInfo(index,jcb.getSelectedIndex());
			}
			
		});
		this.add(next);
		this.pack();
		this.setVisible(true);
	}
/**
 * Generate the information of a stop in a specific route which is required.
 * @param rindex
 * 		The route No.
 * @param sindex
 */
	public void stationInfo(int rindex, int sindex){
		int currentTime=(int)(System.currentTimeMillis()/1000%(24*3600/status.timespeed)*status.timespeed),
				h=currentTime/3600,
				m=currentTime%3600/60;
		status.pair tmp=status.getTime(rindex,sindex,h,m);
		this.setLayout(new GridLayout(3,1));
		this.add(new JLabel("Station Info"));
		this.add(new JLabel("Next outward train will come in: "+tmp.first+" mins"));
		this.add(new JLabel("Next return train will come in: "+tmp.last+" mins"));
		this.pack();
		this.setVisible(true);
	}
}
