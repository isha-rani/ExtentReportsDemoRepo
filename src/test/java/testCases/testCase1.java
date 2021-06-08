package testCases;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class testCase1 {

	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	@BeforeTest
	public void setReport() {
		htmlReporter = new ExtentSparkReporter("./reports/extent.html"); // root mein reports folder bnega fr extent file bnegi
		htmlReporter.config().setEncoding("utf-8"); // required
		htmlReporter.config().setDocumentTitle("W2A Automation Reports");
		htmlReporter.config().setReportName("Automation test results");
		htmlReporter.config().setTheme(Theme.STANDARD); // two themes- std and dark
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("Automation Tester", "Isha Rani");
		extent.setSystemInfo("Organization", "Way2Automation");
		extent.setSystemInfo("Build no", "w2a-1234");
		
	}
	
	@AfterTest
	public void endReport() {
		
		extent.flush();
	}
	
	@Test
	public void doLogin() {
		test=extent.createTest("Login test");
		System.out.println("Executing Login Test");
	}
	
	@Test
	public void doUSerReg() {
		test=extent.createTest("User Reg test");
		Assert.fail("User Reg failed");
	}
	
	@Test
	public void isSkip() {
		test=extent.createTest("skipped test");
		throw new SkipException("skipping the testcase");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			String methodName=result.getMethod().getMethodName(); // this will give the method name in which the test case skipped.
			
			String logText="<b>"+"TESTCASE : -"+methodName.toUpperCase()+"  FAILED"+"</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED); // this is designing part.like for success we give yellow color
			test.fail(m);	
		}else if(result.getStatus()==ITestResult.SKIP) {
			String methodName=result.getMethod().getMethodName(); // this will give the method name in which the test case skipped.
			
			String logText="<b>"+"TESTCASE : -"+methodName.toUpperCase()+"  SKIPPED"+"</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW); // this is designing part.like for success we give yellow color
			test.skip(m);
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			String methodName=result.getMethod().getMethodName(); // this will give the method name in which the test case passed.
			
			String logText="<b>"+"TESTCASE : -"+methodName.toUpperCase()+"  PASSED"+"</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN); // this is designing part.like for success we give green color
			test.pass(m);
			
		}
	}
}
