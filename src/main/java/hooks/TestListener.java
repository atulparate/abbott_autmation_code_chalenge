package hooks;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.ReportUtil;

public class TestListener implements ITestListener {

	public TestListener() {
		// TODO Auto-generated constructor stub
	}
	
	@BeforeSuite
	public void beforeExecution() throws IOException {
		FileUtils.deleteDirectory(new File("testreports/screenshots"));
		FileUtils.deleteQuietly(new File("testreports/report.html"));
		ReportUtil.startReport();
	}
	
	@AfterSuite
	public void afterExecution() {
		ReportUtil.stopReport();
	}
	
	public void onTestStart(ITestResult result) {
		System.out.println("New Test Started" +result.getName());
		ReportUtil.test = ReportUtil.extent.createTest(result.getName());
	}
}
