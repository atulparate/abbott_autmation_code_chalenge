package utilities;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ElementFactory extends AndroidDriverUtil {
//	private AppiumDriver driver;

	public ElementFactory() {
//		this.driver = ;
	}

	public static AndroidElement createElement(String locatorType, String locatorValue) {
		AndroidElement element = null;

		WebDriverWait wait = new WebDriverWait(AndroidDriverUtil.mobdriver, 30);

		switch (locatorType.toUpperCase()) {
		case "ID":
			element = (AndroidElement) wait
					.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id(locatorValue)));
			break;
		case "XPATH":
			// element = DriverUtil.driver.findElement(By.xpath(locatorValue));
			element = (AndroidElement) wait
					.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath(locatorValue)));
			break;
		case "LINKTEXT":
			// element = DriverUtil.driver.findElement(By.linkText(locatorValue));
			element = (AndroidElement) wait
					.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.linkText(locatorValue)));
			break;
		case "CSSSELECTOR":
			// element = DriverUtil.driver.findElement(By.cssSelector(locatorValue));
			element = (AndroidElement) wait
					.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.cssSelector(locatorValue)));
			break;
		case "NAME":
			// element = DriverUtil.driver.findElement(By.name(locatorValue));
			element = (AndroidElement) wait
					.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.name(locatorValue)));
			break;
		case "ACCESSIBILITYID":
			// element = DriverUtil.driver.findElement(By.name(locatorValue));
			element = (AndroidElement) wait
					.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId(locatorValue)));
			break;
		case "CLASSNAME":
			element = (AndroidElement) wait
					.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.className(locatorValue)));
		default:
			break;
		}
		return element;
	}
}
