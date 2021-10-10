package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;

public class DriverFactory {

    public static WebDriver getDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(new ChromeOptions().addArguments("headless"));
        } else if (browser.equalsIgnoreCase("opera")) {
            WebDriverManager.operadriver().setup();
            return new OperaDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(new ChromeOptions().addArguments("headless"));
        }
    }
}
