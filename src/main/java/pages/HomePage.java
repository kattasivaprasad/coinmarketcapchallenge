package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitHelper;
import org.junit.Assert;

import java.util.List;

public class HomePage {
    WebDriver driver;
    WaitHelper waitHelper;

    public HomePage(WebDriver driverInstance) {
        this.driver = driverInstance;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }

    @FindBy(css = "h1.sc-1q9q90x-0.TyVlS")
    WebElement headerLogo;

    @FindBy(css = "div.sc-16r8icm-0.kjciSH.table-control-area div.sc-16r8icm-0.tu1guj-0.cSTqvK")
    WebElement showRowsDropDown;

    @FindBy(css = "button.x0o17e-0.bnhhNH")
    List<WebElement> numbersFromDropDown;

    @FindBy(css = "table.h7vnx2-2.czTsgW.cmc-table tbody tr")
    List<WebElement> resultsList;

    @FindBy(css = "div.sc-1hxnufv-5.fmuRvw span.icon-Slider")
    WebElement filtersOptn;

    @FindBy(css = "div.cmc-cookie-policy-banner__close")
    WebElement closeCookiesPopUp;

    @FindBy(css = "table.h7vnx2-2.czTsgW.cmc-table span.sc-1ow4cwt-1.ieFnWP")
    List<WebElement> marketCapTxt;

    public Boolean isDisplayedHeader() {
        waitHelper.waitCondition(headerLogo, 10);
        closeCookiesPopUp.click();
        return headerLogo.isDisplayed();
    }

    /**
     *The dropdown selection is parameterised. Can select with any number that is available in drop down
     */

    public void selectShowRowsDropDown(String numberToSelect) {
        showRowsDropDown.click();
        waitHelper.driverWait(2000);
        for (WebElement we : numbersFromDropDown) {
            if (we.getText().equalsIgnoreCase(numberToSelect)) {
                we.click();
                waitHelper.driverWait(2000);
                break;
            }
        }
    }

    public int verifyResultsNoDisplayed() {
        return resultsList.size();
    }

    /**
     * This method is to scroll and check if all the 100 elements are displayed on UI
     */

    public void isDisplayedEachRowFromTheList(int size) {
        for(int i=0; i<=size-1;i++){
           ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView();", resultsList.get(i));
            if (!resultsList.get(i).isDisplayed()){
                Assert.fail("Element Not Visible/Displayed");
            }
        }
    }

    public boolean isDisplayedFiltersOption() {
        return filtersOptn.isDisplayed();
    }

    public void clickFiltersOptn() {
        filtersOptn.click();
    }


    public boolean isDisplayedSubFiltersMenu(String subFilterToBeSelected) {
        Boolean flag = false;
        try {
            waitHelper.driverWait(2000);
            WebElement ele = driver.findElement(By.xpath("//button[contains(text(),'" + subFilterToBeSelected + "')]"));
            if (!ele.isDisplayed()) {
                flag = false;
            } else {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     *This method accepts String param which should be same as filter name in the UI and performs clickon filter matches given string
     */

    public void selectSpecificFilterFromList(String subFilterToBeSelected) {
        driver.findElement(By.xpath("//button[contains(text(),'" + subFilterToBeSelected + "')]")).click();
    }

    /** scroll method is because when there are more than
     * 20 results the findElements of marketCapTxt is giving only first 20 results.
     */

    public void verifyResultsAfterFilter(Long minMarketRange, Long maxMarketRange) {
        waitHelper.driverWait(1000);
        int loopCount = resultsList.size();
        for (int i=0;i<=loopCount-1;i++){
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView();", resultsList.get(i));
        }
        for (WebElement we : marketCapTxt) {
            waitHelper.driverWait(1000);
            long cap = Long.parseLong(we.getText().replaceAll("\\D", ""));
            if (!(cap >= minMarketRange || cap <= maxMarketRange)) {
                Assert.fail("Market cap is not in range");
            }
        }
    }
}
