package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseTest;

public class ExtentReportManager implements ITestListener{

	ExtentSparkReporter ui;
	ExtentReports info;
	ExtentTest test;
	String reportName;

	@Override
	public void onStart(ITestContext context) {
		
		/*
		 * SimpleDateFormat formatOfTheDate = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss"); 
		 * Date date = new Date(); 
		 * String currentDateStamp = formatOfTheDate.format(date);
		 */
		
		String currentDateStamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());
		
		reportName = "TestReport-"+currentDateStamp+".html";
		
		ui = new ExtentSparkReporter(".\\reports\\"+ reportName);

		ui.config().setDocumentTitle("Automation Report");
		ui.config().setReportName("Functional Testing");
		ui.config().setTheme(Theme.STANDARD);

		info = new ExtentReports();
		info.attachReporter(ui);

		info.setSystemInfo("Application", "OpenCart");
		info.setSystemInfo("Computer Name", "Local host");
		info.setSystemInfo("Environment", "QA");
		info.setSystemInfo("UserName", System.getProperty("user.name"));

		String os = context.getCurrentXmlTest().getParameter("os");
		info.setSystemInfo("Selenium Tests running on", os);
		
		String browserName = context.getCurrentXmlTest().getParameter("browser");
		info.setSystemInfo("Selenium Tests running on", browserName);
		
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			info.setSystemInfo("GroupName", includedGroups.toString());
		}
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test = info.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, "TestCase passed "+result.getName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		test = info.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "TestCase Failed "+result.getName());
		test.log(Status.FAIL, "TestCase Failed "+result.getThrowable());
		
		try {
			String imgPath = new BaseTest().Screenshot(result.getTestName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test = info.createTest(result.getName());
		test.log(Status.SKIP, "TestCase Skipped "+result.getName());
		test.log(Status.FAIL, "TestCase Failed "+result.getThrowable());

	}


	@Override
	public void onFinish(ITestContext context) {
		info.flush();
		
		File extentReport = new File(System.getProperty("user.dir")+"\\reports\\"+reportName);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}
