package steps;

import base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import pages.BookingPage;
import pages.HotelSelectionPage;

public class BookingSteps extends BaseTest {

    HotelSelectionPage hotelSelectionPage;
    BookingPage bookingPage;

    @Given("User opens the Porto Hotel website")
    public void user_opens_the_porto_hotel_website() {
        hotelSelectionPage = new HotelSelectionPage(driver);
        hotelSelectionPage.openPortoHotelWebsite();
    }

    @When("User clicks on CHECK AVAILABILITY button")
    public void user_clicks_on_check_availability_button() {
        bookingPage = new BookingPage(driver);
        bookingPage.clickCheckAvailability();
    }

    @And("User scrolls down and clicks on Book Now button")
    public void user_scrolls_down_and_clicks_on_book_now_button() throws InterruptedException {
        bookingPage.clickBookNow();
    }
}