package utilities;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ReportUtil {

	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentHtmlReporter html;
	
	public static void startReport() throws UnknownHostException {
		extent = new ExtentReports();
		html = new ExtentHtmlReporter(System.getProperty("user.dir") + "/testreports/report.html");
		extent.attachReporter(html);
		
		extent.setSystemInfo("Test Environment", "QA");
		extent.setSystemInfo("HostName", InetAddress.getLocalHost().getHostName());
		extent.setSystemInfo("UserName", System.getProperty("user.name"));
		
		html.loadConfig("extentConfig.xml");
		
	}
	
	public static void stopReport() {
		extent.flush();
	}
	
	public static void log(Status status,String stepDetails,String screenshot) {
		switch (status) {
		case PASS:	
			test.log(Status.PASS,stepDetails);
			if(screenshot != null) {			
				test.log(Status.INFO, "<a href = '"+screenshot.toString()+"'>Screenshot Taken</a>");
				System.out.println(screenshot);
			}			
			break;
		case FAIL:
			if(screenshot != null) {
				test.log(Status.FAIL,stepDetails);
				test.log(Status.INFO, "<a href = '"+screenshot+"'>Screenshot Taken</a>");
			}				
			break;
		case INFO:
			test.log(Status.INFO,stepDetails);
			break;
		case WARNING:
			test.log(Status.WARNING,stepDetails);
			break;
		default:
			break;
		}
	}		
}
