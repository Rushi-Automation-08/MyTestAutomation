package base;

import common.CommonUtils;
import common.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.IClassListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import web.pageobjects.BasePage;
import web.pageobjects.Page;

@Listeners(base.BaseTest.class)
public class BaseTest implements ITestListener, IClassListener {

    public static WebDriver driver;
    public static Page page;
    private static String suiteName;
    private static String environment;
    private static String browser;
    private static String testName;

    @Override
    public void onStart(ITestContext context) {
        CommonUtils.readEnvironmentData(context);
        init(context);
    }

    @Override
    public void onTestStart(ITestResult result) {
        if(!testName.contains("API")) {
            if(browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            } else {
                System.out.println("Invalid or Unimplemented Browser: " + browser);
            }
            page = new BasePage(driver);
            driver.get("https://www.ebay.com/");
            driver.manage().window().maximize();
            page.waitForPageLoadTimeout();
        }
    }

    private void init(ITestContext context) {
        suiteName = context.getCurrentXmlTest().getSuite().getName();
        testName = context.getCurrentXmlTest().getName();
        browser = CommonUtils.environmentAttributes.get("browser");
        browser = browser== null ? Constants.DEFAULT_BROWSER : browser;
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        closeDriver();
    }


/*
    @BeforeMethod
    @Parameters(value = {"browser"})
    public void setUpTest(String browser) {
        browser = browser == null ? CommonUtils.environmentAttributes.get("Browser") : browser;
        if(browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            System.out.println("Invalid or Unimplemented Browser: " + browser);
        }
        page = new BasePage(driver);
        driver.get("https://www.ebay.com/");
        driver.manage().window().maximize();
        page.waitForPageLoadTimeout();
    }*/

    public void closeDriver() {
        if(!testName.contains("API") && driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
