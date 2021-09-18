package stepdefs;

import pages.FilterPage;
import pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.PageObjectManager;

public class CoinMarketPageStepDef {

    Long minMarketRange;
    Long maxMarketRange;
    Long minPriceRange;
    Long maxPriceRange;
    HomePage homePage = PageObjectManager.getHomePage();
    FilterPage filterPage = PageObjectManager.getFilterPage();

    @Given("user is on CoinMarket homepage")
    public void user_is_on_coin_market_homepage() {
        Assert.assertTrue("Header not Displayed", homePage.isDisplayedHeader());
    }

    @When("user selects show rows dropdown number as {string}")
    public void user_selects_show_rows_dropdown_number_as(String numberToSelect) {
        homePage.selectShowRowsDropDown(numberToSelect);
    }

    @Then("user should see only {string} number of results got displayed")
    public void user_should_see_only_number_of_results_got_displayed(String listNumberToDisplay) {
        int size = homePage.verifyResultsNoDisplayed();
        if (size != Integer.parseInt(listNumberToDisplay)) {
            Assert.fail("'" + listNumberToDisplay + '"' + "Results are not displyed");
        }
        homePage.isDisplayedEachRowFromTheList(size);
    }

    @When("user selects Filters option and click on {string} Add Filter")
    public void user_selects_filters_option_and_click_on_Add_Filter(String subFilterToBeSelected) {
        Assert.assertTrue("Filters Option is Not Displayed", homePage.isDisplayedFiltersOption());
        homePage.clickFiltersOptn();
        Assert.assertTrue("Sub Filter Menu Is Not Displayed", homePage.isDisplayedSubFiltersMenu(subFilterToBeSelected));
        homePage.selectSpecificFilterFromList(subFilterToBeSelected);

    }

    @Then("user should see an option to filter based on {string} with market cap range {string}")
    public void user_should_see_an_option_to_filter_based_on_with_market_cap_range(String filterName, String range) {
        Assert.assertTrue("Filter Page Is Not Displayed", filterPage.isDisplayedMoreFiltersHeader());
        filterPage.selectSpecificFilterAs(filterName);
        filterPage.selectRangeAs(range);
        minMarketRange = filterPage.getMinMarketCapRange();
        maxMarketRange = filterPage.getMaxMarketCapRange();
        filterPage.clickApplyFilterBtn();
    }

    @Then("user selects {string} filter with price range of {string}")
    public void user_selects_filter_with_price_range_of(String filterName, String range) {
        filterPage.selectSpecificFilterAs(filterName);
        filterPage.selectRangeAs(range);
        minPriceRange = filterPage.getMinMarketCapRange();
        maxPriceRange = filterPage.getMinMarketCapRange();
    }

    @When("user click on apply filter and show results buttons")
    public void user_click_on_apply_filter_and_show_results_buttons() {
        filterPage.clickApplyFilterBtn();
        filterPage.clickShowResultsBtn();
    }

    @Then("user should be navigated to home page where the results displayed should be in between applied market cap range")
    public void user_should_be_navigated_to_home_page_where_the_results_displayed_should_be_in_between_applied_market_cap_range() {
        int noOfRowsData = homePage.verifyResultsNoDisplayed();
        homePage.verifyResultsAfterFilter(minMarketRange, maxMarketRange);
    }
}
