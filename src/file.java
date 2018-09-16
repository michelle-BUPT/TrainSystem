import java.io.*;
import java.util.*;
/**
 * Define operations to operate the data stored in the local file
 * @author Administrator
 *
 */
public class file
{
	/**
	 * Store time
	 * @author Administrator
	 *
	 */
	public class time
	{
		int h;//The value of hours
		int m;//The value of minutes
		time(Scanner fin)
		{
			h=fin.nextInt();
			m=fin.nextInt();
		}
		int time()
		{
			return h*60+m;
		}
		/**
		 * Print the information of the time object to local file.
		 * @param out
		 * 		The file print writer
		 */
		void print(PrintWriter out)
		{
			out.println(h+" "+m);
		}
		/**
		 * To return the time as a string.
		 * @return
		 */
		String showTime()
		{
			return h+":"+m;
		}
		String _toString()
		{
			return new Formatter().format("%02d:%02d",h,m).toString();
		}
		time()
		{}
		time(int a,int b)
		{
			h=a;
			m=b;
		}
	}
	/**
	 * Store the information of routes
	 * @author Administrator
	 *
	 */
	public class _route
	{
		int stopnum=0;//The amount of stops in the route.
		int junynum=0;//The amount of arranged journeys in the route.
		ArrayList <Integer> stop=new ArrayList <Integer> ();//The No. of stops along the route.
		ArrayList <ArrayList <time>> timetableout=new ArrayList <ArrayList <time> > ();//The name of trains which are assigned to the route.
		ArrayList <ArrayList <time>> timetableret=new ArrayList <ArrayList <time> > ();//The name of drivers which are assigned to the driver.
		ArrayList <String> train=new ArrayList <String> ();//The outward timetable scheduled for the route.
		ArrayList <String> driver=new ArrayList <String> ();//The return timetable scheduled for the route.
		_route()
		{}
	}
	/**
	 * Store the information of trains
	 * @author Administrator
	 *
	 */
	public class _ntrain
	{
		String name;//The train's name
		int vis;//A flag indicate if the train is on the duty.
		int stopflag = 0;//A flag indicate if the train is running.
		/**
		 * A constructor used to import the information from local file.
		 * @param fin
		 * 		The file scanner
		 */
		_ntrain(Scanner fin)
		{
			name=fin.next();
			vis=fin.nextInt();
		}
		/**
		 * A constructor used in other operations.
		 * @param s
		 * 		The train's name
		 * @param d
		 * 		The train's state
		 */
		_ntrain(String s,int d)
		{
			name=s;
			vis=d;
		}
		/**
		 * Print the information of the train to the local file.
		 * @param out
		 * 		The file print writer
		 */
		void print(PrintWriter out)
		{
			out.println(name);
			out.println(vis);
		}
	}
	/**
	 * Store the information of drivers
	 * @author Administrator
	 *
	 */
	public class _driver
	{
		String name;//Driver's name
		int vis;//Driver's state
		/**
		 * To import the information from local file
		 * @param fin
		 * 		File scanner
		 */
		_driver(Scanner fin)
		{
			name=fin.next();
			vis=fin.nextInt();
		}
		/**
		 * To construct a new driver
		 * @param s
		 * 		Driver's name
		 * @param d
		 * 		Driver's state
		 */
		_driver(String s,int d)
		{
			name=s;
			vis=d;
		}
		/**
		 * To print the information into local file
		 * @param out
		 * 		The file print writer
		 */
		void print(PrintWriter out)
		{
			out.println(name);
			out.println(vis);
		}
	}
	/**\
	 * As an assistance in helping operation getRoute() to get the information of timetables from the local file.
	 * @param fin
	 * 		The file scanner
	 * @param juny
	 * 		The journey No.
	 * @param stop
	 * 		The stop No.
	 * @return
	 * 		Relative timetable
	 */
	ArrayList <ArrayList <time> > getTime(Scanner fin,int juny, int stop)
	{
		ArrayList <ArrayList <time>> tmp=new ArrayList <ArrayList <time> > ();
		for (int i=1;i<=juny;i++)
		{
			ArrayList <time> _tmp=new ArrayList <time> ();
			for (int j=1;j<=stop;j++)
				_tmp.add(new time(fin));
			tmp.add(_tmp);
		}
		return tmp;
	}
	/**
	 * As an assistance in helping operation getRoute() to get the names of drivers/trains from the local file.
	 * @param fin
	 * 		The file scanner
	 * @param juny
	 * 		The journey No.
	 * @return
	 * 		The names of drivers/trains
	 */
	ArrayList <String> getName(Scanner fin,int juny)
	{
		ArrayList <String> tmp=new ArrayList <String> ();
		for (int i=1;i<=juny;i++)
			tmp.add(fin.next());
		return tmp;
	}
	/**
	 * Import the information of routes from the local file.
	 * @return
	 * 		The routes' information
	 */
	ArrayList <_route> getRoute()
	{
		ArrayList <_route> route=new ArrayList <_route> ();
		try
		{
			Scanner fin=new Scanner (new FileReader (new File("./database/route")));
			int routenum=fin.nextInt();
			for (int i=1;i<=routenum;i++)
			{
				_route tmp=new _route();
				tmp.stopnum=fin.nextInt();
				for (int j=1;j<=tmp.stopnum;j++)
					tmp.stop.add(fin.nextInt());
				tmp.junynum=fin.nextInt();
				tmp.timetableout=getTime(fin,tmp.junynum,tmp.stopnum);
				tmp.timetableret=getTime(fin,tmp.junynum,tmp.stopnum);
				tmp.train=getName(fin,tmp.junynum);
				tmp.driver=getName(fin,tmp.junynum);
				route.add(tmp);
			}
			fin.close();
		}
		catch(Exception e) {}
		return route;
	}
	/**
	 * Store the route in the local file
	 * @param route
	 * 		The routes to be stored
	 */
	void saveRoute(ArrayList <_route> route)
	{
		try
		{
			PrintWriter out=new PrintWriter(new FileWriter("./database/route"));
			out.println(route.size());
			for (int i=0;i<route.size();i++)
			{
				int stopnum=route.get(i).stopnum,junynum=route.get(i).junynum;
				out.println(stopnum);
				for (int j=0;j<stopnum;j++)
					out.println(route.get(i).stop.get(j));
				out.println(junynum);
				for (int j=0;j<junynum;j++)
					for (int k=0;k<stopnum;k++)
						route.get(i).timetableout.get(j).get(k).print(out);
				for (int j=0;j<junynum;j++)
					for (int k=0;k<stopnum;k++)
						route.get(i).timetableret.get(j).get(k).print(out);
				for (int j=0;j<junynum;j++)
					out.println(route.get(i).train.get(j));
				for (int j=0;j<junynum;j++)
					out.println(route.get(i).driver.get(j));
			}
			out.close();
		}
		catch(Exception e) {}
	}
	/**
	 * Import the information of drivers from the local file.
	 * @return
	 * 		The drivers' information
	 */
	ArrayList <_driver> getDriver()
	{
		ArrayList <_driver> driver=new ArrayList <_driver> ();
		try
		{
			Scanner fin=new Scanner (new FileReader (new File("./database/driver")));
			int num=fin.nextInt();
			for (int i=1;i<=num;i++)
				driver.add(new _driver(fin));
			fin.close();
		}
		catch(Exception e) {}
		return driver;
	}
	/**
	 * Import the information of trains from the local file.
	 * @return
	 * 		The trains' information
	 */
	ArrayList <_ntrain> getTrain()
	{
		ArrayList <_ntrain> train=new ArrayList <_ntrain> ();
		try
		{
			Scanner fin=new Scanner (new FileReader (new File("./database/train")));
			int num=fin.nextInt();
			for (int i=1;i<=num;i++)
				train.add(new _ntrain(fin));
			fin.close();
		}
		catch(Exception e) {}
		return train;
	}
	/**
	 * Store the information of trains in the local file
	 * @param train
	 * 		The information of trains to be stored
	 */
	void saveTrain(ArrayList <_ntrain> train)
	{
		try
		{
			PrintWriter out=new PrintWriter(new FileWriter("./database/train"));
			out.println(train.size());
			for (int i=0;i<train.size();i++)
				train.get(i).print(out);
			out.close();
		}
		catch(Exception e) {}
	}
	/**
	 * Store the information of drivers in the local file
	 * @param driver
	 * 		The drivers' information to be stored
	 */
	void saveDriver(ArrayList <_driver> driver)
	{
		try
		{
			PrintWriter out=new PrintWriter(new FileWriter("./database/driver"));
			out.println(driver.size());
			for (int i=0;i<driver.size();i++)
				driver.get(i).print(out);
			out.close();
		}
		catch(Exception e) {}
	}
	file()
	{}
}