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
/**
 * The frame of the display screen in trains
 * @author Administrator
 *
 */
public class train_board extends JFrame{
	status status;//The system's status
	public train_board(status status){
		this.status = status;
		selectRoute();		
	}
	public void init(){
		this.getContentPane().removeAll();
		this.repaint();
	}
	/**
	 * Allow users to choose the route wanted.
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

				//init();
				select_train(jcb.getSelectedIndex());
			}
			
		});
		this.add(jcb);
		this.add(next);
		this.pack();
		this.setVisible(true);
	}
	/**
	 * Allow users to choose the trian wanted.
	 * @param index
	 * 		The route No.
	 */
	public void select_train(final int index){
		int currentTime=(int)(System.currentTimeMillis()/1000%(24*3600/status.timespeed)*status.timespeed),
				h=currentTime/3600,
				m=currentTime%3600/60;
		final ArrayList <status._train>train=status.getTrain(index,h,m);
		if(train.size() == 0){
			JOptionPane.showMessageDialog(null, "There are not trains running in the route.");
			return;
		}
		else{
			init();
			this.setLayout(new GridLayout(3,1));
			this.add(new JLabel("Select Train"));
			ArrayList <String> trainname = new ArrayList<String>();
			for(status._train tra:train){
				trainname.add(tra.name);
			}
			final JComboBox jcb = new JComboBox(trainname.toArray());
			this.add(jcb);
			JButton next = new JButton("Next");
			next.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					init();
					train_info(train.get(jcb.getSelectedIndex()),index);
				}
				
			});
			this.add(next);
			this.pack();
			this.setVisible(true);
		}
		
	}
	/**
	 * Display the information of selected train in the train board.
	 * @param train
	 * 		Selected train
	 * @param rindex
	 * 		Selected route
	 */
	public void train_info(status._train train, int rindex){
		this.setLayout(new GridLayout(3,1));
		this.add(new JLabel("Train name: "+train.name));
		this.add(new JLabel("Next stop£» "+train.stop));
		file._ntrain tmp = null;
		for(int i=0;i<status.train.size();i++){
			if(train.name.equals(status.train.get(i).name)){
				tmp = status.train.get(i);
				break;
			}
		}
		
		if( tmp.stopflag== 1){
			this.add(new JLabel("Train Stopped"));
		}else{
			this.add(new JLabel("Remain time: "+train.m+" min"));
		}
		
		this.setTitle(train.name+"'s driver:"+status.route.get(rindex).driver.get(status.route.get(rindex).train.indexOf(train.name)));
		this.pack();
		this.setVisible(true);
	}
}
