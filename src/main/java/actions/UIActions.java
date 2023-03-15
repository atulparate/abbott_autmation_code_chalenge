package actions;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.Status;

import io.appium.java_client.android.AndroidElement;
import utilities.AndroidDriverUtil;
//import utilities.MobDriverUtil;
import utilities.ReportUtil;

public class UIActions extends AndroidDriverUtil {

	public UIActions() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void Click(AndroidElement element) throws IOException {
		try {
			element.click();;
//			String path = takeScrenshot();
//			System.out.println("path = "+ path);
//			ReportUtil.log(Status.PASS, "Clicked on Element => " + element.toString(), path);
		} catch (Exception e) {
			ReportUtil.log(Status.FAIL, "Unable to click on Element =>" + e.getMessage(), takeScrenshot());
		}
	}
	
	public void enterText(AndroidElement element, String textToEnter) throws IOException{
		try {
			element.sendKeys(textToEnter);
//			String path = takeScrenshot();
//			System.out.println("path = "+ path);
//			ReportUtil.log(Status.PASS, "Text Entered in Element => " + element.toString(), path);
		}catch (Exception e) {
			ReportUtil.log(Status.FAIL, "Unable to click on Element =>" + e.getMessage(), takeScrenshot());
		}
	}

	public String takeScrenshot() throws IOException {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		String screnshotFileName = timestamp.getTime()+".png";
		String screenshotPath = "testReports/screenshots/"+screnshotFileName;
		System.out.println("Screenshot name = " + screnshotFileName);
		System.out.println("Screenshot path = " + screenshotPath);
		
		 System.out.println("Cw = "+System.getProperty("user.dir"));
		
		File screenshotFile = ((TakesScreenshot) AndroidDriverUtil.mobdriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile , new File(screenshotPath));
		
		return System.getProperty("user.dir")+"\\"+screenshotPath;
	}
}
