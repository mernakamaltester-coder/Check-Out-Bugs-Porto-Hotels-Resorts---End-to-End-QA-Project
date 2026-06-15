package com.porto.automation.stepdefinitions;

import com.porto.automation.pages.HomePage;
import com.porto.automation.pages.HotelDetailsPage;
import com.porto.automation.utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PortoSokhnaRestaurantsSteps {

    private WebDriver driver = DriverFactory.getDriver();
    private HomePage homePage = new HomePage(driver);
    private HotelDetailsPage hotelDetailsPage = new HotelDetailsPage(driver);

    @Given("the user is on the Porto Hotels home page")
    public void theUserIsOnThePortoHotelsHomePage() {
        System.out.println("LOG: Navigating to the Porto Hotels live portal...");
        homePage.navigateToUrl("https://www.portohotelseg.com/");
    }

    @When("the user navigates to {string} from the Hotels & Resorts menu")
    public void theUserNavigatesToFromTheHotelsResortsMenu(String hotelName) {
        System.out.println("LOG: Initiating cascaded navigation sequence for: " + hotelName);
        homePage.clickHotelsAndResortsMenu();
        homePage.selectAinSokhnaRegion();
        homePage.selectPortoSokhnaHotel();
    }

    @When("the user clicks on the {string} tab in the sub-navigation menu")
    public void theUserClicksOnTheTabInTheSubNavigationMenu(String tabName) {
        System.out.println("LOG: Clicking the sub-navigation tab: " + tabName);
        hotelDetailsPage.clickRestaurantsTab();
    }

    @Then("the restaurants page should load successfully")
    public void theRestaurantsPageShouldLoadSuccessfully() {
        System.out.println("LOG: Verifying restaurants page load signature...");
        boolean isLoaded = hotelDetailsPage.isRestaurantsPageLoaded();
        Assert.assertTrue(isLoaded, "ERROR: The Restaurants/Dining page layout failed to load.");
    }

    @Then("at least one restaurant profile should be listed with a valid name and description")
    public void atLeastOneRestaurantProfileShouldBeListedWithAValidNameAndDescription() {
        System.out.println("LOG: Validating structural presence of dining restaurant cards...");
        boolean hasProfiles = hotelDetailsPage.areRestaurantProfilesDisplayed();
        Assert.assertTrue(hasProfiles, "ERROR: Structural dining elements or profile cards were missing.");
        System.out.println("LOG: Scenario verification completed successfully.");
    }
}