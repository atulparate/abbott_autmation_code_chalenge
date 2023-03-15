package pages;

import static utilities.ElementFactory.createElement;

import java.io.IOException;

import actions.UIActions;
import io.appium.java_client.android.AndroidElement;

public class CartsPage extends UIActions{

	public CartsPage() {
		// TODO Auto-generated constructor stub
	}
	
	private AndroidElement productPrice(String productName) {
		return createElement("xpath", "//android.widget.TextView[@text = '"+productName+"']/parent::android.widget.LinearLayout//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice']");
	}
	
	private AndroidElement totalCost() {
		return createElement("id", "com.androidsample.generalstore:id/totalAmountLbl");
	}
	
	private AndroidElement discountsCheckBox() {
		return createElement("classname","android.widget.CheckBox");
	}
	
	private AndroidElement completePurchaseButton() {
		return createElement("id", "com.androidsample.generalstore:id/btnProceed");
	}
	
	public double getProductsCost(String productName) {
		double productCost = Double.parseDouble(productPrice(productName).getText().replace("$", ""));
		return productCost;
	}
	
	public double getTotalCost() {
		double totalCost = Double.parseDouble(totalCost().getText().replace("$", ""));
		return totalCost;
	}
	
	public CartsPage selectDiscountsCheckBox() throws IOException {
		Click(discountsCheckBox());
		return new CartsPage();
	}
	
	public CartsPage clickOnCompletePurchase() throws IOException {
		Click(completePurchaseButton());
		return new CartsPage();
	}
}
