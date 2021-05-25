package base;

import Utilities.Helper;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;


import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {
    private WebDriver driver;

    String url = "https://m2.leanscale.com";

    public static ChromeOptions chromeOption() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default.content_settings.popups", 0);
        options.setExperimentalOption("prefs", chromePrefs);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return options;
    }

    @BeforeClass
    @Parameters({"browser"})
    public void startDriver(@Optional() String browserName) {
        if (connectors.DriverFactory.getDriver() == null) {
            OpenBrowserName(browserName);
            connectors.DriverFactory.addDriver(driver);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            driver.navigate().to(url);
            PageBase page = new PageBase(driver);
        }

    }

    public void OpenBrowserName(String browser) {
        switch (browser) {
            case "safari":
                System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");
                driver = new SafariDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver");
                driver = new ChromeDriver(chromeOption());
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Drivers/geckodriver");
                driver = new FirefoxDriver();
                break;
            case "IE":
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/Drivers/IEDriverServer");
                driver = new InternetExplorerDriver();
                break;
        }
    }

    @AfterSuite
    public void tearsDown() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                connectors.DriverFactory.removeDriver();
                connectors.DriverFactory.storedDrivers.forEach(WebDriver::quit);
            }
        });
    }

    /*method to take ScreenShot incase the test fail*/
    @AfterMethod
    public void screenShotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed");
            System.out.println("Taking Screenshot....");
            Helper.captureScreenShot(driver, result.getName());
        }
    }

    @Attachment
    public static String logOutput(List<String> outputList) {
        String output = "";
        for (String o : outputList)
            output += o + "<br/>";
        return output;
    }
}
