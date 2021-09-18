package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitHelper;

import java.util.List;

public class FilterPage {
    WebDriver driver;
    WaitHelper waitHelper;

    public FilterPage(WebDriver driverInstance) {
        this.driver = driverInstance;
        PageFactory.initElements(driver, this);
        waitHelper = new WaitHelper(driver);
    }

    @FindBy(css = "h4.sc-1q9q90x-0.cnJknd")
    WebElement filterPageHeader;

    @FindBy(css = "div.cmc-filter-presets>button")
    List<WebElement> range;

    @FindBy(xpath = "//input[@data-qa-id='range-filter-input-min']")
    WebElement minRange;

    @FindBy(xpath = "//input[@data-qa-id='range-filter-input-max']")
    WebElement maxRange;

    @FindBy(xpath = "//button[@data-qa-id='filter-dd-button-apply']")
    WebElement applyFilterBtn;

    @FindBy(css = "button.x0o17e-0.lgnbfA.cmc-filter-button")
    WebElement showResultsbtn;

    public Boolean isDisplayedMoreFiltersHeader() {
        waitHelper.waitCondition(filterPageHeader, 10);
        return filterPageHeader.isDisplayed();
    }

    public void selectSpecificFilterAs(String filterName) {
        waitHelper.driverWait(1000);
        driver.findElement(By.xpath("//button[contains(text(),'" + filterName + "')]")).click();
    }

    public void selectRangeAs(String rangeToBeSelected) {
        try {
            waitHelper.driverWait(1000);
            for (WebElement we : range) {
                if (we.getText().equals(rangeToBeSelected)) {
                    we.click();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Return Long value in minimum market cap filed which will come after selecting the range
     * @return Long
     */
    public Long getMinMarketCapRange() {
        Long minRangeAmt;
        waitHelper.waitCondition(minRange, 10);
        minRangeAmt = Long.parseLong(minRange.getAttribute("value").replaceAll("\\D", ""));
        return minRangeAmt;
    }

    /**
     * Return Long value in minimum market cap filed which will come after selecting the range
     * @return Long
     */
    public Long getMaxMarketCapRange() {
        Long maxRangeAmt;
        waitHelper.waitCondition(maxRange, 10);
        maxRangeAmt = Long.parseLong(maxRange.getAttribute("value").replaceAll("\\D", ""));
        return maxRangeAmt;
    }

    public void clickApplyFilterBtn() {
        applyFilterBtn.click();
    }

    public void clickShowResultsBtn() {
        waitHelper.waitCondition(showResultsbtn, 10);
        showResultsbtn.click();
    }
}
