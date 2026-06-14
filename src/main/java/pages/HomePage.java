package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class HomePage {

    private WebDriver driver;

    private By checkAvailabilityButton = By.cssSelector("#bf_submit_a");
    public By BookButton = By.xpath("//*[contains(text(),'Book Now')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPortoHotelWebsite() {
        driver.get("https://www.portohotelseg.com");
    }

    public void clickCheckAvailability() {
        driver.findElement(checkAvailabilityButton).click();
    }

    public void clickBookNow() throws InterruptedException {

        while (true) {

            JavascriptExecutor Js = (JavascriptExecutor) driver;

            try {

                List<String> All_Windows = new ArrayList<>(driver.getWindowHandles());
                driver.switchTo().window(All_Windows.get(1));

                Js.executeScript("arguments[0].scrollIntoView({block:'center'});", driver.findElement(BookButton));
                WebElement Elements = driver.findElements(BookButton).get(2);

                Elements.click();

                break;
            } catch (Exception e) {}
        }

        Thread.sleep(2000);

        ArrayList<String> allTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(allTabs.get(allTabs.size() - 1));

        while (true) {
            JavascriptExecutor Js = (JavascriptExecutor) driver;
            try {
                WebElement bookNewBtn = driver.findElement(By.xpath("//button[contains(@onclick,'request')]"));
                Js.executeScript("arguments[0].scrollIntoView({block:'center'});", bookNewBtn);

                bookNewBtn.click();

                break;

            } catch (Exception e) {}
        }


        Thread.sleep(2000);


        driver.findElement(By.id("ctl14_firstName")).sendKeys("Merna");


        driver.findElement(By.name("ctl14$lastName")).sendKeys("Abdul_Razzaq");


        driver.findElement(By.id("ctl14_email")).sendKeys("merna.abdulrazzaq.tester@gmail.com");


        driver.findElement(By.cssSelector("#ctl14_mobile")).sendKeys("01018708764");

        Thread.sleep(1000);

        driver.findElement(By.id("ctl14_creditCard")).sendKeys("4863930018602819");


        driver.findElement(By.id("ctl14_creditcardName")).sendKeys("Merna Mohamed Kamal Mohamed");

        Thread.sleep(500);

        driver.findElement(By.cssSelector("#card-type div.dropdown-toggle")).click();

        Thread.sleep(500);

        driver.findElement(By.xpath("//a[text()='VISA']")).click();

        Thread.sleep(500);

        driver.findElement(By.cssSelector("#expiry-date-month div.dropdown-toggle")).click();

        driver.findElement(By.xpath("//li[@id='04']")).click();

        driver.findElement(By.cssSelector("#expiry-date-year div.dropdown-toggle")).click();

        driver.findElement(By.xpath("//li[@id='2032']")).click();

        driver.findElement(By.id("ctl14_cvv")).sendKeys("123");

        Thread.sleep(500);

        driver.findElement(By.cssSelector("div.checkbox-button span.glyphicon-ok")).click();

        Thread.sleep(500);

        driver.findElement(By.id("ctl14_btnConfirm")).click();

        Thread.sleep(3000);

        try {

            driver.findElement(By.xpath("//button[text()='OK']")).click();

        } catch (Exception e) {

        }
    }
}