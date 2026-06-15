package com.porto.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    // ✅ Locators محددة بالـ hamburger-header-container
    private By hotelsResortsMenu = By.cssSelector(
            "#hamburger-header-container span.nav-item-text[data-link-text='HOTELS & RESORTS']");
    private By ainSokhnaRegion = By.cssSelector(
            "#hamburger-header-container li[data-depth='1'] span.nav-item-text[data-link-text='AIN SOKHNA']");
    private By portoSokhnaHotel = By.cssSelector(
            "li[data-depth='2'] a[href='/sokhna-hotel'][data-target-page-alias='sokhna---home']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.actions = new Actions(driver);
    }

    public void navigateToUrl(String url) {
        driver.get(url);
    }

    public void clickHotelsAndResortsMenu() {
        try {
            System.out.println("LOG: Hovering on HOTELS & RESORTS menu...");
            Thread.sleep(3000);
            WebElement element = wait.until(
                    ExpectedConditions.presenceOfElementLocated(hotelsResortsMenu));
            // ✅ Hover لأن القايمة بتظهر بالـ hover مش click
            actions.moveToElement(element).perform();
            System.out.println("LOG: Hovered on HOTELS & RESORTS successfully!");
            Thread.sleep(1500);
        } catch (Exception e) {
            System.out.println("ERROR: Failed to hover HOTELS & RESORTS: " + e.getMessage());
        }
    }

    public void selectAinSokhnaRegion() {
        try {
            System.out.println("LOG: Hovering on AIN SOKHNA...");
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(ainSokhnaRegion));
            // ✅ Hover لأن الـ submenu بيظهر بالـ hover
            actions.moveToElement(element).perform();
            System.out.println("LOG: Hovered on AIN SOKHNA!");
            Thread.sleep(1500);
        } catch (Exception e) {
            System.out.println("ERROR: Failed to hover AIN SOKHNA: " + e.getMessage());
        }
    }

    public void selectPortoSokhnaHotel() {
        try {
            System.out.println("LOG: Clicking PORTO SOKHNA hotel...");
            WebElement element = wait.until(
                    ExpectedConditions.elementToBeClickable(portoSokhnaHotel));
            // ✅ Click صح هنا لأنه Link بيودي لصفحة
            actions.moveToElement(element).click().perform();
            System.out.println("LOG: PORTO SOKHNA hotel clicked!");
        } catch (Exception e) {
            System.out.println("ERROR: Failed to click PORTO SOKHNA: " + e.getMessage());
        }
    }
}