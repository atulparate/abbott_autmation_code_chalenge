package pages;

import static utilities.ElementFactory.createElement;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import actions.UIActions;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ProductsPage extends UIActions {

	public ProductsPage() {
		// TODO Auto-generated constructor stub
	}

	private AndroidElement addToCart_ProductSpecific(String productName) {
		return createElement("xpath", "//android.widget.TextView[@text='"+ productName +"']/parent::android.widget.LinearLayout//android.widget.TextView[@text ='ADD TO CART']");
	}
	
	private AndroidElement addToCartGeneralCounter() {
		return createElement("id", "com.androidsample.generalstore:id/counterText");
	}
	
	private AndroidElement addToCartButton() {
		return createElement("id", "productsPage");
	}
	
	public ProductsPage scrollTillProduct(String productName) {
		boolean productExistFlag = false;
		System.out.println("Inside scrollTillProduct");
		TouchAction action = new TouchAction(mobdriver);
		while (!productExistFlag) {
			try {
				productExistFlag = addToCart_ProductSpecific(productName).isDisplayed();
			} catch (Exception e) {
				productExistFlag = false;
			}
			
			System.out.println("Inside scrollTillProduct - while");
			List<AndroidElement> list_element = mobdriver.findElements(MobileBy.xpath("//android.widget.TextView[@text = 'ADD TO CART']"));
			int elements_count = list_element.size();
			//int elements_count = list_element.size();
			Point source_location = list_element.get(elements_count-1).getLocation();
			int x_source = source_location.x;
			int y_source = source_location.y/2;
			
			Point target_location = list_element.get(0).getLocation();
			int x_target = target_location.x;
			int y_target = target_location.y/2;
			
			action.press(PointOption.point(x_source, y_source)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(x_target, y_target)).release().perform();
			//System.out.println(elements_count);	
		}
		return new ProductsPage();
	}
	
	public ProductsPage addToCart_Product(String productName) throws IOException {
		Click(addToCart_ProductSpecific(productName));
		return new ProductsPage();
	}
	
	public int checkAddToCartCounterNumber() {
		return Integer.parseInt(addToCartGeneralCounter().getText());
		
	}
	
	public ProductsPage clickOnAddToCart() throws IOException {
		Click(addToCartButton());
		return new ProductsPage();
	}
}
