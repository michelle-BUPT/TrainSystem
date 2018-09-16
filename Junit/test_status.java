import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class test_status {
	
	@Test
	public void testMin() {
		status a = new status();
		int b = a.min(12, 13);
		assertEquals("Method min() failed.",12, b);
	}

}
