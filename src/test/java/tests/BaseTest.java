package tests;

import factory.DriverFactory;
import factory.ScreenshotFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import tests.utils.PropertyReader;
import tests.utils.TestListener;

import java.util.concurrent.TimeUnit;

@Listeners({TestListener.class})
public abstract class BaseTest {

    protected final String USER = System.getenv().getOrDefault("SD_USER", PropertyReader.getProperty("sd.user"));
    protected final String LOCKED_USER = System.getenv().getOrDefault("SD_LOCKED_USER", PropertyReader.getProperty("sd.locked.user"));
    protected final String PASS = System.getenv().getOrDefault("SD_PASS", PropertyReader.getProperty("sd.pass"));

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected CatalogPage catalogPage;
    protected CartPage cartPage;
    protected CheckoutStepOnePage checkoutStepOnePage;
    protected CheckoutStepTwoPage checkoutStepTwoPage;
    protected CompletePage completePage;

    @Parameters("browser")
    @BeforeMethod(groups = "smoke")
    public void setup(@Optional("chrome") String browser, ITestContext context) {
        try {
            String mvnBrowser = System.getProperty("browser");
            driver = DriverFactory.getDriver(mvnBrowser);
        } catch (NullPointerException exception) {
            driver = DriverFactory.getDriver(browser);
        }
        context.setAttribute("driver", driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        catalogPage = new CatalogPage(driver);
        cartPage = new CartPage(driver);
        checkoutStepOnePage = new CheckoutStepOnePage(driver);
        checkoutStepTwoPage = new CheckoutStepTwoPage(driver);
        completePage = new CompletePage(driver);
    }

    @AfterMethod(alwaysRun = true, groups = "smoke")
    public void tearDown() {
        ScreenshotFactory.getScreen(driver);
        if (driver != null)
            driver.quit();
    }

}
