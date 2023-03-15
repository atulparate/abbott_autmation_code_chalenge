package utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidDriverUtil {

	public AndroidDriverUtil() {
		// TODO Auto-generated constructor stub
	}

	public static AndroidDriver mobdriver = null;

	public static void Initialize() throws MalformedURLException {
		if (mobdriver == null) {
			// WebDriverManager.chromedriver().setup();
			// WebDriverManager.chromedriver().browserVersion("90.0.4430.93").setup();
			DesiredCapabilities options = new DesiredCapabilities();

			options.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			options.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
			options.setCapability(MobileCapabilityType.UDID, "emulator-5554");

//			options.setCapability(MobileCapabilityType.APP, "D:\\Softwares\\General-Store.apk");
			options.setCapability("appPackage", "com.androidsample.generalstore");
			options.setCapability("appActivity", "com.androidsample.generalstore.SplashActivity");
			mobdriver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), options);
		}

		mobdriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}
	
	

	public static void CloseDriver() {
		mobdriver.close();
		mobdriver = null;
	}

	public static void QuitDriver() {
		mobdriver.quit();
		mobdriver = null;
	}
}
