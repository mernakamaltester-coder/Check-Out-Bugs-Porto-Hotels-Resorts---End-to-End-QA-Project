package com.porto.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HotelDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ✅ Locators محددة
    private By restaurantsTab = By.cssSelector(
            "a[href='/sokhna-hotel/restaurants'][data-target-page-alias='sokhna-resort/restaurants']");
    // ✅ محدد بالـ id مش بس class عشان ميمسكش كل العناصر
    private By restaurantCards = By.cssSelector("div#group_ab2.flex-element.group");

    public HotelDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void clickRestaurantsTab() {
        try {
            System.out.println("LOG: Waiting for page to load before clicking Restaurants tab...");
            // ✅ بيستنى الصفحة تتحمل الأول
            wait.until(ExpectedConditions.urlContains("sokhna-hotel"));
            WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(restaurantsTab));
            tab.click();
            System.out.println("LOG: Restaurants tab clicked successfully!");
        } catch (Exception e) {
            System.out.println("LOG: Normal click failed, trying JS click...");
            try {
                WebElement tab = wait.until(
                        ExpectedConditions.presenceOfElementLocated(restaurantsTab));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
                System.out.println("LOG: JS click on Restaurants tab done!");
            } catch (Exception ex) {
                System.out.println("ERROR: Failed to click Restaurants tab: " + ex.getMessage());
            }
        }
    }

    public boolean isRestaurantsPageLoaded() {
        try {
            // ✅ بيستنى الـ URL يتغير للـ restaurants
            wait.until(ExpectedConditions.urlContains("/restaurants"));
            System.out.println("LOG: Restaurants page URL confirmed: " + driver.getCurrentUrl());
            return driver.getCurrentUrl().contains("restaurants");
        } catch (Exception e) {
            System.out.println("ERROR: Restaurants page did not load: " + e.getMessage());
            return false;
        }
    }

    public boolean areRestaurantProfilesDisplayed() {
        try {
            // ✅ بيدور على الكارت المحدد بالـ id
            List<WebElement> profiles = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(restaurantCards));
            System.out.println("LOG: Found " + profiles.size() + " restaurant card(s).");
            return !profiles.isEmpty();
        } catch (Exception e) {
            System.out.println("ERROR: No restaurant cards found: " + e.getMessage());
            return false;
        }
    }
}