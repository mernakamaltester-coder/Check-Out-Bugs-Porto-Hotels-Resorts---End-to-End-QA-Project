package pages;

import org.openqa.selenium.WebDriver;

public class HotelSelectionPage {

    private WebDriver driver;

    public HotelSelectionPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPortoHotelWebsite() {
        driver.get("https://www.portohotelseg.com");
    }
}