package stepDefeinition;
import Hooks.hooks;
import Mypackage.AccommodationPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.testng.Assert;

public class AccommodationSteps {

    AccommodationPage accommodationPage;

    @Given("User navigated to Porto Hotels website for Accommodation")
    public void user_navigated_to_porto_hotels_website()
            throws InterruptedException {

        hooks.driver.get("https://www.portohotelseg.com/");
        Thread.sleep(3000);
        accommodationPage = new AccommodationPage(hooks.driver);
    }

    @When("User navigates to Accommodation page of Porto Marina Resort")
    public void user_opens_porto_marina_resort_accommodation_page()
            throws InterruptedException {

        accommodationPage.NavigateToPortoMarina();
        Thread.sleep(3000);
        accommodationPage.ClickOnAccommodationTab();
        Thread.sleep(2000);
    }

    @Then("Room information should be displayed correctly")
    public void room_information_should_be_displayed_correctly() {

        Assert.assertTrue(
                accommodationPage.isRoomInfoDisplayed(),
                "Room information is not displayed correctly"
        );
    }

    @When("User selects a room and completes the booking")
    public void user_selects_a_room_and_completes_the_booking()
            throws InterruptedException {

        accommodationPage.SelectRoom();
        Thread.sleep(2000);
        accommodationPage.ClickBookNow();
        Thread.sleep(2000);
    }

    @Then("Booking should be confirmed successfully")
    public void booking_should_be_confirmed_successfully() {

        Assert.assertTrue(
                accommodationPage.isBookingConfirmed(),
                "Booking was not confirmed!"
        );
    }
}