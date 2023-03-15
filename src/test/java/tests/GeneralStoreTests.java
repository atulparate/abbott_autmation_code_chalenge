package tests;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.Constants;
import hooks.TestListener;
import pages.CartsPage;
import pages.Common;
import pages.MainPage;
import pages.ProductsPage;
import utilities.AndroidDriverUtil;
import utilities.MobDriverUtil;


public class GeneralStoreTests extends TestListener{
	
	MainPage mainPage;
	ProductsPage productsPage;
	CartsPage cartsPage;
	Common common;
	
	@BeforeMethod
	public void setupMob(ITestResult test) throws MalformedURLException {
		//ReportUtil.test = ReportUtil.extent.createTest(test.getTestName());
		AndroidDriverUtil.Initialize();
		mainPage = new MainPage();
		productsPage = new ProductsPage();
		cartsPage = new CartsPage();
		common = new Common();
	}
	
	@Test
	public void T1() throws IOException, InterruptedException {
		mainPage.selectCountry(Constants.countryName);
		mainPage.enterName(Constants.userName);
		mainPage.clickOnLetsShopt()
			.scrollTillProduct(Constants.firstProductToPurchase)
			.addToCart_Product(Constants.firstProductToPurchase)
			.scrollTillProduct(Constants.secondProductToPurchase)
			.addToCart_Product(Constants.secondProductToPurchase);
			
		Assert.assertEquals(2, productsPage.checkAddToCartCounterNumber());
		productsPage.clickOnAddToCart();
		Assert.assertEquals(cartsPage.getTotalCost(), cartsPage.getProductsCost(Constants.firstProductToPurchase) + cartsPage.getProductsCost(Constants.secondProductToPurchase));
		
		cartsPage.selectDiscountsCheckBox()
			.clickOnCompletePurchase();
		
		common.changeContextToWebView();
		common.enterTextInSearchField(Constants.textToEnterInSearchField);
		
		common.goToBackToApp();
	}
	
	@AfterMethod
	public void stop() {
		AndroidDriverUtil.QuitDriver();
	}
}
