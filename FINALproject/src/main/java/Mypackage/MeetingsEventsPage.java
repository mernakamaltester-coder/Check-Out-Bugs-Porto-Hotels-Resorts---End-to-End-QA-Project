package Mypackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MeetingsEventsPage {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public MeetingsEventsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
    }

    // LOCATORS
    By hotelsAndResortsMenu =
            By.xpath("(//span[@data-link-text='HOTELS & RESORTS'])[2]");

    By ainSokhna =
            By.xpath("(//span[@data-link-text='AIN SOKHNA'])[2]");


    By portoSokhna =
            By.xpath("//a[@data-target-page-alias='sokhna---home']");

    By meetingsAndEventsTab =
            By.xpath("//a[@href='/sokhna-hotel/meetings-and-events']");

    By venueTable =
            By.xpath("//table[.//td[contains(text(),'Theater') or contains(text(),'Classroom') or contains(text(),'Dinner')]]");

    By meetingsHeader =
            By.xpath("//span[contains(text(),'Corporates') or contains(text(),'Celebrations')]");

    // ACTIONS

    public void navigateToPortoSokhna() {
        WebElement hotels = wait.until(
                ExpectedConditions.visibilityOfElementLocated(hotelsAndResortsMenu)
        );
        actions.moveToElement(hotels).perform();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {} //  استنى عشان تشوف الـ submenu

        WebElement sokhna = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ainSokhna)
        );
        actions.moveToElement(sokhna).perform();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {} //  استنى عشان تشوف الـ submenu

        WebElement portoSokhnaLink = wait.until(
                ExpectedConditions.elementToBeClickable(portoSokhna)
        );
        actions.moveToElement(portoSokhnaLink).perform();
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {} //  استنى عشان تشوف قبل الكليك
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", portoSokhnaLink);
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }

    public void clickMeetingsAndEvents() {
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < 6; i++) {
            js.executeScript("window.scrollBy(0, 500)");
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        }

        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(meetingsAndEventsTab)
        );
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {

        }
        js.executeScript("arguments[0].click();", element);
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {

        }
    }

    public boolean isVenueInfoDisplayed() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            //  استنى الصفحة تتحمل
            try { Thread.sleep(3000); } catch (InterruptedException ignored) {}

            //  تحقق من الـ URL الأول
            System.out.println(" Current URL: " + driver.getCurrentUrl());

            // scroll تدريجي أكتر
            for (int i = 0; i < 8; i++) {
                js.executeScript("window.scrollBy(0, 400)");
                try { Thread.sleep(600); } catch (InterruptedException ignored) {}
            }

            // بيدور على أي نص في الصفحة
            By pageContent = By.xpath(
                    "//*[contains(text(),'Corporates') " +
                            "or contains(text(),'Celebrations') " +
                            "or contains(text(),'Banquet') " +
                            "or contains(text(),'Ballroom') " +
                            "or contains(text(),'Theater') " +
                            "or contains(text(),'Weddings')]"
            );

            WebElement content = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(pageContent)
            );
            System.out.println(" Content found: " + content.getText());

            return content.isDisplayed();

        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
            System.out.println(" Current URL: " + driver.getCurrentUrl());
            return false;
        }
    }
}