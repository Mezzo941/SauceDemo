package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.*;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public abstract class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected CatalogPage catalogPage;
    protected CartPage cartPage;
    protected CheckoutStepOnePage checkoutStepOnePage;
    protected CheckoutStepTwoPage checkoutStepTwoPage;
    protected CompletePage completePage;

    @Parameters("browser")
    @BeforeMethod(groups = "smoke")
    public void setup(@Optional("chrome") String browser) {
        String mvnBrowser = System.getProperty("browser");
        driver = DriverPusher.push(mvnBrowser);
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
        if (driver != null)
            driver.quit();
    }

}
