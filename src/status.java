import java.util.*;
/**
 * Store the current status of the system
 * @author Administrator
 *
 */
public class status extends file
{
	/**
	 * To return a new route object in convenience for adding a new route.
	 * @return
	 * 		A new route
	 */
	_route newroute()
	{
		return new _route();
	}
	int timespeed=1;//The multiple of time in the system to real time in order to stimulate.
	ArrayList <_route> route=getRoute();//A list used to store the information of current routes of the system.
	ArrayList <_ntrain> train=getTrain();//A list used to store the information of trains.
	ArrayList <_driver> driver=getDriver();//A list used to store the information of drivers.
	
	
	class pair
	{
		int first,last;
		pair(int a,int b)
		{
			first=a;
			last=b;
		}
	}
	class _train
	{
		String name;
		int stop,m;
		_train(String a,int b,int c)
		{
			name=a;
			stop=b;
			m=c;
		}
	}
	
	/**
	 * Return the smaller integer.
	 * @param a
	 * @param b
	 * @return
	 * 		The smaller integer
	 */
	int min(int a,int b)
	{
		return a<b?a:b;
	}
	/**
	 * To get the information of trains running along a specific route in a specific time.
	 * @param route
	 * 		The route queried
	 * @param h
	 * 		Time of hours
	 * @param m
	 * 		Time of minutes
	 * @return
	 * 		The running trains
	 */
	ArrayList <_train> getTrain(int route,int h,int m)
	{
		ArrayList <_train> tmp=new ArrayList <_train>();
		_route t=this.route.get(route);
		int time=h*60+m,last;
		for (int i=0;i<t.timetableout.size();i++)
		{
			int tt=0;
			last=t.timetableout.get(i).get(0).time();
			for (int j=1;j<t.timetableout.get(i).size();j++)
			{
				if (last<=time&&t.timetableout.get(i).get(j).time()>time)
				{
					tmp.add(new _train(t.train.get(i),t.stop.get(j),t.timetableout.get(i).get(j).time()-time));
					tt=1;
					break;
				}
				last=t.timetableout.get(i).get(j).time();
			}
			if (tt==1)
				break;
		}
		for (int i=0;i<t.timetableret.size();i++)
		{
			int tt=0;
			last=t.timetableret.get(i).get(0).time();
			for (int j=1;j<t.timetableret.get(i).size();j++)
			{
				if (last<=time&&t.timetableret.get(i).get(j).time()>time)
				{
					tmp.add(new _train(t.train.get(i),t.stop.get(t.timetableret.get(i).size()-j-1),t.timetableret.get(i).get(j).time()-time));
					tt=1;
					break;
				}
				last=t.timetableret.get(i).get(j).time();
			}
			if (tt==1)
				break;
		}
		return tmp;
	}
	/**
	 * To get the time interval the given time in.
	 * @param route
	 * 		The route queried
	 * @param num
	 * @param h
	 * @param m
	 * @return
	 * 		Time interval
	 */
	pair getTime(int route,int num,int h,int m)
	{
		_route t=this.route.get(route);
		int min1=0xfffff,min2=0xfffff,now=h*60+m;
		for (int i=0;i<t.timetableout.size();i++)
		{
			int time=t.timetableout.get(i).get(num).time()-now;
			if (time<=0)
				time+=24*60;
			min1=min(time,min1);
		}
		for (int i=0;i<t.timetableret.size();i++)
		{
			int time=t.timetableret.get(i).get(t.timetableret.get(i).size()-num-1).time()-now;
			if (time<=0)
				time+=24*60;
			min2=min(time,min2);
		}
		return new pair(min1,min2);
	}
	/**
	 * Remove a specific journey.
	 * @param rout
	 * 		The specific route
	 * @param index
	 * 		The journey No.
	 */
	void removeJourney(int rout,int index)
	{
		_route tmp=route.get(rout);
		tmp.junynum--;
		tmp.timetableout.remove(index);
		tmp.timetableret.remove(index);
		String __train=tmp.train.get(index),__driver=tmp.driver.get(index);
		tmp.train.remove(index);
		tmp.driver.remove(index);
		for (int i=0;i<train.size();i++)
		{
			if (train.get(i).name.equals(__train))
			{
				train.get(i).vis=0;
				break;
			}
		}
		for (int i=0;i<driver.size();i++)
		{
			if (driver.get(i).name.equals(__driver))
			{
				driver.get(i).vis=0;
				break;
			}
		}
		saveTrain(train);
		saveDriver(driver);
		saveRoute(route);
	}

	/**
	 * Remove a specific route.
	 * @param rout
	 * 		The route No.
	 */
	void removeRoute(int rout)
	{
		_route tmp=route.get(rout);
		for (int i=0;i<tmp.train.size();i++)
		{
			String s=tmp.train.get(i);
			for (int j=0;j<train.size();j++)
				if (s.equals(train.get(j).name))
				{
					train.set(j,new _ntrain(s,0));
					break;
				}
		}
		for (int i=0;i<tmp.driver.size();i++)
		{
			String s=tmp.driver.get(i);
			for (int j=0;j<driver.size();j++)
				if (s.equals(driver.get(j).name))
				{
					driver.set(j,new _driver(s,0));
					break;
				}
		}
		route.remove(tmp);
		saveTrain(train);
		saveDriver(driver);
		saveRoute(route);
	}

	
	status()
	{}
}