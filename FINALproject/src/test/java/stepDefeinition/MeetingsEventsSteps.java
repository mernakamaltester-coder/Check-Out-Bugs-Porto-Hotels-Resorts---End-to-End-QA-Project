package stepDefeinition;

import Hooks.hooks;
import Mypackage.MeetingsEventsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class MeetingsEventsSteps {

    MeetingsEventsPage meetingsEventsPage;

    @Given("User navigated to Porto Hotels website for Meetings and Events")
    public void user_navigated_to_porto_hotels_website_for_meetings_and_events()
            throws InterruptedException {
        hooks.driver.get("https://www.portohotelseg.com/");
        Thread.sleep(3000);
        meetingsEventsPage = new MeetingsEventsPage(hooks.driver);
    }

    @When("User navigates to Meetings and Events page of Porto Sokhna Hotel")
    public void user_navigates_to_meetings_and_events_page_of_porto_sokhna_hotel()
            throws InterruptedException {
        meetingsEventsPage.navigateToPortoSokhna();
        Thread.sleep(2000);

        // السكرول البطيء لحد Learn More والكليك عليها كله جوا الـ Page class
        meetingsEventsPage.clickMeetingsAndEvents();
        Thread.sleep(2000);
    }

    @Then("Venue details or capacity information should be displayed")
    public void venue_details_or_capacity_information_should_be_displayed() {
        Assert.assertTrue(
                meetingsEventsPage.isVenueInfoDisplayed(),
                "Venue details or capacity information is not displayed!"
        );
    }
}