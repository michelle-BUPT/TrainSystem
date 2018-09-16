import static org.junit.Assert.*;

import org.junit.Test;

public class test_driverinfo_board {

	@Test
	public void testIsExit() {
		status data = new status();
		driverinfo_board a =new driverinfo_board(data);
		assertEquals("isExit failed",true, a.isExit("AAA"));
	}

}
