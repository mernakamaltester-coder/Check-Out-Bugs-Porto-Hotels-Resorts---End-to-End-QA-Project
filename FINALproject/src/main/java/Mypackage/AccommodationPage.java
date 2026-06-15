package Mypackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AccommodationPage {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public AccommodationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
    }

    // LOCATORS
    By hotelsAndResortsMenu =
            By.xpath("(//span[@data-link-text='HOTELS & RESORTS'])[2]");

    By northCoast =
            By.xpath("(//span[@data-link-text=\"NORTH COAST\"])[2]");

    By portoMarina =
            By.xpath("(//a[@href=\"/marina-Hotel-home\"])[3]");

    By accommodationTab =
            By.xpath("(//a[.//span[normalize-space(@data-link-text)=\"ACCOMMODATION\"]])[7]");

    By roomCards =
            By.xpath("//strong[contains(@style,'color: rgb(43, 155, 180)')]");

    By bookNowButton =
            By.xpath("(//a[@href='https://portomarina.book-onlinenow.net/'])[1]");

    // ACTIONS

    public void NavigateToPortoMarina() {
        WebElement hotels = wait.until(
                ExpectedConditions.visibilityOfElementLocated(hotelsAndResortsMenu)
        );
        actions.moveToElement(hotels).perform();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        WebElement north = wait.until(
                ExpectedConditions.visibilityOfElementLocated(northCoast)
        );
        actions.moveToElement(north).perform();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        WebElement marina = wait.until(
                ExpectedConditions.elementToBeClickable(portoMarina)
        );
        actions.moveToElement(marina).perform();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
        actions.click().perform();
    }

    public void ClickOnAccommodationTab() {
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(accommodationTab)
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }

    public boolean isRoomInfoDisplayed() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            for (int i = 0; i < 4; i++) {
                js.executeScript("window.scrollBy(0, 500)");
                try { Thread.sleep(800); } catch (InterruptedException ignored) {}
            }

            List<WebElement> rooms = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(roomCards)
            );

            System.out.println(" Rooms found: " + rooms.size());
            return rooms.size() >= 1;

        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
            return false;
        }
    }

    public void SelectRoom() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 600)");
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            js.executeScript("window.scrollBy(0, 600)");
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}

            List<WebElement> rooms = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(roomCards)
            );

            if (!rooms.isEmpty()) {
                js.executeScript(
                        "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                        rooms.get(0)
                );
                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
                rooms.get(0).click();
            } else {
                throw new RuntimeException("No rooms available to select!");
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error selecting room: " + e.getMessage());
        }
    }

    public void ClickBookNow() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement button = wait.until(
                    ExpectedConditions.presenceOfElementLocated(bookNowButton)
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                    button
            );
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            js.executeScript("arguments[0].click();", button);

        } catch (Exception e) {
            throw new RuntimeException("Book Now button not found: " + e.getMessage());
        }
    }

    public boolean isBookingConfirmed() {
        try {
            //  انتقل للـ tab الجديد
            String originalTab = driver.getWindowHandle();

            for (String tab : driver.getWindowHandles()) {
                if (!tab.equals(originalTab)) {
                    driver.switchTo().window(tab);
                    break;
                }
            }

            // استنى الـ URL يتحمل
            wait.until(ExpectedConditions.urlContains("portomarina"));

            String currentUrl = driver.getCurrentUrl();
            System.out.println(" Current URL: " + currentUrl);

            return currentUrl.contains("portomarina")
                    || currentUrl.contains("book-onlinenow")
                    || currentUrl.contains("booking")
                    || currentUrl.contains("reservation");

        } catch (Exception e) {
            System.out.println(" Booking URL Error: " + e.getMessage());
            return false;
        }
    }
}