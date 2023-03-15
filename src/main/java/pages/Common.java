package pages;

import org.openqa.selenium.By;

import actions.UIActions;

public class Common extends UIActions{

	public Common() {
		// TODO Auto-generated constructor stub
	}

	
	public void goToBackToApp() {
		mobdriver.context("NATIVE_APP");
	    // Go back to the application
		mobdriver.navigate().back();
	}
	
	public void changeContextToWebView() {
		String contextName = mobdriver.getContextHandles().toArray()[1].toString();
	    mobdriver.context(contextName);
	}
	
	public void enterTextInSearchField(String textToEnter) {
		mobdriver.findElementById("tsf").findElement(By.className("android.widget.EditText")).sendKeys(textToEnter);
	}
}
