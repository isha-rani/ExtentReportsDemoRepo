package testCases;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class testCase2 {
	@Test
	public void doLogin() {
		System.out.println("Executing Login Test");
	}
	
	@Test
	public void doUSerReg() {
		Assert.fail("User Reg failed");
	}
	
	@Test
	public void isSkip() {
		throw new SkipException("skipping the testcase");
	}
}
