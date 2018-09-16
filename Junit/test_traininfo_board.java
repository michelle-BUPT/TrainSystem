import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class test_traininfo_board {

	@Test
	public void testIsExit() {
		status data = new status();
		traininfo_board a =new traininfo_board(data);
		assertEquals("isExit failed",true, a.isExit("aaa"));
	}

}
