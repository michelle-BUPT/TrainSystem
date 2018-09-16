import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Formatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/**
 * The frame displaying the information of trains running in a route
 * @author Administrator
 *
 */
public class train_info {
	status status;//The system's status
	class table
	{
		table(String title,String [][] data,String [] cTitle, ArrayList<status._train> trainlist)
		{
			JFrame jf=new JFrame(title);
			jf.setLayout(new BorderLayout());
			JTable table;
			Object [][] tableData=data;
			Object [] columnTitle=cTitle;
			table = new JTable(tableData,columnTitle);    
        	jf.add(new JScrollPane(table),BorderLayout.CENTER);  
        	
        	JPanel stoppane = new JPanel();
        	stoppane.setLayout(new FlowLayout());
        	
        	ArrayList <String> trainname = new ArrayList<String>();
			for(status._train tra:trainlist){
				trainname.add(tra.name);
			}
			final JComboBox jcb = new JComboBox(trainname.toArray());
			stoppane.add(jcb);
        	JButton start = new JButton("start");
        	start.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String trainname = (String)jcb.getSelectedItem();
					for(int i=0;i<status.train.size();i++){
						if(status.train.get(i).name.equals(trainname)){
							status.train.get(i).stopflag = 0;
							break;
						}
					}
					JOptionPane.showMessageDialog(null, "Train started");
				}
        		
        	});
        	JButton stop = new JButton("stop");
        	stop.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String trainname = (String)jcb.getSelectedItem();
					for(int i=0;i<status.train.size();i++){
						if(status.train.get(i).name.equals(trainname)){
							status.train.get(i).stopflag = 1;
							break;
						}
						
					}
					JOptionPane.showMessageDialog(null, "Train stoped");
				}
        		
        	});
        	stoppane.add(start);
        	stoppane.add(stop);
        	jf.add(stoppane,BorderLayout.SOUTH);
        	jf.pack();  
        	jf.setLocationRelativeTo(null);
        	jf.setVisible(true);  
		}
	}
	/**
	 * A constructor of this class. 
	 * The operation in the constructor is aimed to get the information of trains running in a specific route
	 *  and build up a GUI to display these information.
	 * @param data
	 * 		The system's status
	 * @param index
	 * 		The route No
	 */
	public train_info(status data, int index){
		this.status = data;
		int routenum=data.route.size(),
			    currentTime=(int)(System.currentTimeMillis()/1000%(24*3600/data.timespeed)*data.timespeed),
				h=currentTime/3600,
				m=currentTime%3600/60,
				num=0;
			ArrayList <ArrayList <status._train>> tableData=new ArrayList <ArrayList <status._train>> ();
			ArrayList<status._train> trainlist = null;
			
			trainlist = data.getTrain(index-1, h, m);
			num+=trainlist.size();
			tableData.add(trainlist);
			
			String [][] _tableData=new String[num][3];
			int k=0;
			for (int i=0;i<tableData.size();i++)
			{
				for (int j=0;j<tableData.get(i).size();j++)
				{
					status._train tmp=tableData.get(i).get(j);
					_tableData[k][0]="Train "+tmp.name;
					_tableData[k][1]=(tmp.stop==0)?"Central Station":("Stop "+(char)('A'+tmp.stop-1));
					_tableData[k][2]=tmp.m+((tmp.m==1)?" minute":" minutes");
					k++;
				}
			}
			String [] _columnTitle=new String[]{"Train Name","Next Stop","Arrive Time"};
			String s="Train Status at "+new Formatter().format("%02d:%02d",h,m).toString();
			new table(s,_tableData,_columnTitle, trainlist);
	}
}
