package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewTest {
    ChromeDriver driver;
    WebDriverWait wait;
    String package7 = "PF511538";
    String websiteURL = "https://dev-coach.everfit.io/package/"+ package7;

    // This method runs before each test method
    @Before
    public void setUp() {
        // Initialize ChromeDriver with options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(websiteURL);
        driver.manage().window().maximize();
    }


    @Test
    public void TC_008() {
        sendKeys(YOUR_NAME, "Name");
        sendKeys(YOUR_EMAIL, "lamnguyenbao+dev49@everfit.io");
        sendKeys(PHONE_NUMBER, "18237521651");

        WebElement iframeElement = driver.findElement(IFRAME_CARD_NUMBER);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", iframeElement);

        switchToFrame(IFRAME_CARD_NUMBER);
        sendKeys(CARD_NUMBER, "4242 4242 4242 4242");
        getDriver().switchTo().defaultContent();
        switchToFrame(IFRAME_CARD);
        sendKeys(MM_YY, "444");
        getDriver().switchTo().defaultContent();
        switchToFrame(IFRAME_CVC);
        sendKeys(CVC, "444");
        getDriver().switchTo().defaultContent();

        sendKeys(BILLING_ADDRESS, "Address");
        sendKeys(OPTIONAL, "Address");
        sendKeys(CITY, "City");
        sendKeys(ZIP, "50000");
        click(COUNTRY);
        click(LIST_COUNTRY);
        click(PURCHASE);
        waitForVisibleOf(TYPE_FULL_NAME);
        sendKeys(TYPE_FULL_NAME, "Name");
        click(CONFIRM);

    }

    // This method runs after each test method
    @After
    public void tearDown() {
//        if (driver != null) {
//            driver.quit(); // Clean up and close the browser
//        }
    }
    public ChromeDriver getDriver() {
        return driver;
    }
    public void click(By elementLocator) {
        Actions action = new Actions(driver);
        WebElement we = wait.until(ExpectedConditions.visibilityOf(driver.findElement(elementLocator)));
        action.moveToElement(we).click().build().perform();
        sleep(500);
    }
    public void sendKeys(By elementLocator, String value) {
        driver.findElement(elementLocator).sendKeys(value);
    }
    public void switchToFrame(By elementLocator) {
        WebElement frameElement = driver.findElement(elementLocator);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }
    public void waitForVisibleOf(By elementLocator) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(elementLocator)));
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static final By YOUR_NAME = By.xpath("//input[@placeholder='Your Name']");
    public static final By YOUR_EMAIL = By.xpath("//input[@placeholder='Your Email']");
    public static final  By PHONE_NUMBER = By.xpath("//input[@placeholder='Phone Number']");
    public static final  By IFRAME_CARD_NUMBER = By.xpath("//iframe[@title='Secure card number input frame']");
    public static final  By CARD_NUMBER = By.xpath("//input[@name= 'cardnumber']");
    public static final  By IFRAME_CARD = By.xpath("//iframe[@title='Secure expiration date input frame']");
    public static final By MM_YY = By.xpath("//input[@name= 'exp-date']");
    public static final  By IFRAME_CVC = By.xpath("//iframe[@title='Secure CVC input frame']");
    public static final  By CVC = By.xpath("//input[@name= 'cvc']");
    public static final By BILLING_ADDRESS = By.xpath("//input[@name= 'address']");
    public static final By OPTIONAL = By.xpath("//input[@name= 'apartment']");
    public static final By CITY = By.xpath("//input[@name= 'city']");
    public static final By ZIP = By.xpath("//input[@name= 'zip']");
    public static final By COUNTRY = By.xpath("//div[text()='Country']");
    public static final  By LIST_COUNTRY = By.xpath("//div[contains(@id, 'listbox')] //child::div[2]");
    public static final By PURCHASE = By.xpath("//button[text()='Purchase']");
    public static final By TYPE_FULL_NAME = By.xpath("//input[@placeholder='Type your full name']");
    public static final By CONFIRM = By.xpath("//button[text()='Authorize & Confirm Payment']");
}
