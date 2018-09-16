import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class test_Identification {

	@Test
	public void testIdentify() {
		Identification a = new Identification();
		boolean b = a.identify("Username", "Password");
		assertEquals("Identify fail.",true, b);
	}

}
