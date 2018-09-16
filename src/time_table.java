import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Formatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/**
 * The frame of displaying timetable
 * @author Administrator
 *
 */
public class time_table {
	status._route route;//A variable used to store the information of route whose timetable is asked to query.
	status status;//The system's status
	class table
	{
		table(String title,String [][] data,String [] cTitle)
		{
			JFrame jf=new JFrame(title);
			JTable table;
			Object [][] tableData=data;
			Object [] columnTitle=cTitle;
			table = new JTable(tableData,columnTitle);  
			if((title.indexOf(" Outward"))>0)
			{
				jf.setLocation(400,300);
			}
			else 
			{
				jf.setLocation(900,300);
			}
			
			
			
        	jf.add(new JScrollPane(table));  
        	jf.pack();  
        	//jf.setLocationRelativeTo(null);
        	jf.setVisible(true);  
        	
		}
	}
	/**
	 * Set a table to display the timetable scheduled to the route.
	 * @param type
	 * 		Distinguish the outward and return timetable
	 * @param route
	 * 		The route to be queried
	 * @param stop
	 * 		The stops' No. in the route
	 * @param timeTable
	 * 		The timetable of the route
	 */
	void setTable(int type,int route,ArrayList<Integer> stop,ArrayList <ArrayList <status.time>> timeTable)
	{
		int stopnum=stop.size(),junynum=timeTable.size();
		String[][]_tableData=new String[stopnum][junynum+1];
		for (int i=0;i<stopnum;i++)
		{
			int k;
			if (type==1)
				k=i;
			else
				k=stopnum-i-1;
			if (stop.get(k)>0)
				_tableData[i][0]=new String("Stop "+(char)('A'+stop.get(k)-1));
			else
				_tableData[i][0]=new String("Central Station");
			for (int j=1;j<=junynum;j++)
				_tableData[i][j]=new Formatter().format("%02d:%02d",timeTable.get(j-1).get(i).h,timeTable.get(j-1).get(i).m).toString();
		}
		String[] _columnTitle=new String[junynum+1];
		_columnTitle[0]="Stop Name";
		for (int i=1;i<=junynum;i++)
			_columnTitle[i]="Journey "+String.valueOf(i);
		String s;
		if (type==1)
			s="Route "+String.valueOf(route+1)+" Outward";
		else
			s="Route "+String.valueOf(route+1)+" Return";
		new table(s,_tableData,_columnTitle);
	}
	public time_table(status status, status._route route){
		this.status = status;
		this.route = route;
		setTable(1,status.route.indexOf(route),route.stop,route.timetableout);
		setTable(2,status.route.indexOf(route),route.stop,route.timetableret);
	}
}
