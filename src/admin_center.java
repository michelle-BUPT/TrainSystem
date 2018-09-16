import java.awt.BorderLayout;
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
/**
 * The frame of the administrator center
 * @author Administrator
 *
 */
public class admin_center extends JFrame {
	status status;//The system's status
	/**
	 * A constructor for this class. 
	 * The operations in the method are aimed to build up a relative frame 
	 * and to describe the functions of components 
	 * (including several panels and a button) in that frame.
	 * @param status
	 * 		The system's status
	 */
	public admin_center(final status status){
		this.setSize(600,400);
		this.setLayout(new BorderLayout());
		this.add(new JLabel(new ImageIcon("./pic/map.jpg")),BorderLayout.NORTH);
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(status.route.size(),1));
		for(int i=1;i<=status.route.size();i++){
			final int j = i;
			JPanel routepane = new JPanel();
			routepane.setLayout(new GridLayout(1,3));
			routepane.add(new JLabel("route " + i));
			JButton timetable = new JButton("Timetable");
			final int index = i;
			timetable.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					time_table tt = new time_table(status,status.route.get(index-1));
				}
				
			});
			JButton traininfo = new JButton("Train status");
			traininfo.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					train_info traininfo = new train_info(status,j);
				}
				
			});
			routepane.add(timetable);
			routepane.add(traininfo);
			center.add(routepane);
		}
		this.add(center, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}
	
}
