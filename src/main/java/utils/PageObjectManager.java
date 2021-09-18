package utils;

import pages.FilterPage;
import pages.HomePage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    public static WebDriver driver;

    public static void setDriver(WebDriver driverInstance) {
        driver = driverInstance;
    }

    public static HomePage getHomePage() {
        return new HomePage(driver);
    }

    public static WaitHelper getWaitHelper() {
        return new WaitHelper(driver);
    }

    public static FilterPage getFilterPage() {
        return new FilterPage(driver);
    }
}
