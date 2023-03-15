package pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import actions.UIActions;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utilities.ElementFactory;
import static utilities.ElementFactory.createElement;

public class MainPage extends UIActions {
	// private ElementFactory elementFactory;

	public MainPage() {
		// this.elementFactory = elementFactory;
	}

	// Objects of the Main Page.
	private AndroidElement countryDropDown() {
		return createElement("id", "com.androidsample.generalstore:id/spinnerCountry");
	}

	private AndroidElement userName() {
		return createElement("id","com.androidsample.generalstore:id/nameField");
	}
	private WebElement countryName() {
		return createElement("xpath", "//android.widget.TextView[@text='Andorra']");
	}

	private WebElement maleRadioButton() {
		return createElement("id", "com.androidsample.generalstore:id/radioMale");
	}

	private WebElement femaleRadioButton() {
		return createElement("id", "com.androidsample.generalstore:id/radioFemale");
	}

	private AndroidElement letsShopButon() {
		return createElement("id", "com.androidsample.generalstore:id/btnLetsShop");
	}

	// Method of the Main Page.
	public void selectCountry(String countryName) throws IOException {
		boolean objectExistFlag = false;
		Click(countryDropDown());
		System.out.println("Inside select country");
		TouchAction action = new TouchAction(mobdriver);
		while(!objectExistFlag){

			try {
				objectExistFlag = mobdriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='"+ countryName +"']")).isDisplayed();
			} catch (Exception e) {
				objectExistFlag = false;
			}
			System.out.println("Inside while");
			AndroidElement list_element = (AndroidElement) mobdriver.findElement(MobileBy.className("android.widget.ListView"));
			int elements_count = list_element.findElements(MobileBy.className("android.widget.TextView")).size();
			Point source_location = list_element.findElements(MobileBy.className("android.widget.TextView")).get(elements_count-1).getLocation();
			int x_source = source_location.x;
			int y_source = source_location.y/2;
			
			Point target_location = list_element.findElements(MobileBy.className("android.widget.TextView")).get(0).getLocation();
			int x_target = target_location.x;
			int y_target = target_location.y/2;
			
			action.press(PointOption.point(x_source, y_source)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(x_target, y_target)).release().perform();
			System.out.println(elements_count);	
		}
		mobdriver.findElement(MobileBy.xpath("//android.widget.TextView[@text='"+ countryName +"']")).click();

	}

	public void enterName(String name) throws IOException {
    	enterText(userName(), name);
    }

	public ProductsPage clickOnLetsShopt() throws IOException {
		Click(letsShopButon());
		return new ProductsPage();
	}

}
