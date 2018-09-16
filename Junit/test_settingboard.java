import static org.junit.Assert.*;

import org.junit.Test;

public class test_settingboard {

	@Test
	public void testDriverisOccupied() {
		status data = new status();
		setting_board a =new setting_board(data);
		assertEquals("testTrainisOccupied failed",true,a.DriverisOccupied("AAA") );
	}

	@Test
	public void testTrainisOccupied() {
		status data = new status();
		setting_board a =new setting_board(data);
		assertEquals("testTrainisOccupied failed",true,a.TrainisOccupied("aaa") );
	}

}
