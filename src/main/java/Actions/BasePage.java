package Actions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;


public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait shortWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    protected void sendKeys(By elementLocator, String value) {
        WebElement element = driver.findElement(elementLocator);
        element.sendKeys(value);
    }

    protected void click(By elementLocator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
        element.click();
    }
//    protected void clickEditTime(By locator, int timeoutInSeconds) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
//        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
//        element.click();
//    }

    protected void switchToFrame(By frameLocator) {
        WebElement frame = driver.findElement(frameLocator);
        driver.switchTo().frame(frame);
    }

    protected void scrollIntoView(By elementLocator) {
        WebElement element = driver.findElement(elementLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void waitForVisibleOf(By elementLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }
    protected void waitForInvisibleOf(By elementLocator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
    }
    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public String generateRandomNumber() {
        Random random = new Random();
        return String.valueOf(random.nextInt(500) + 1); // 1 to 500
    }

    public String generateRandomNumber2() {
        Random random = new Random();
        return String.valueOf(random.nextInt(499) + 501); // 501 to 999
    }

    public boolean isErrorMessageVisible() {
        By errorMessageLocator = By.xpath("//div[text()='Your account is already registered. Please log in or sign up with another email.']");
        try {
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
            return true; // Error message is visible
        } catch (Exception e) {
            return false; // Error message is not visible
        }
    }
    public boolean isPaymentSuccessVisible() {
        By errorMessageLocator = By.xpath("//h4[text()='Success!']");
        try {
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
            return true; // Error message is visible
        } catch (Exception e) {
            return false; // Error message is not visible
        }
    }

    public boolean isErrorMessagePurchaseActived() {
        By errorMessageLocator = By.xpath("//div[text()='Purchase has been activated already.']");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
            return true; // Error message is visible
        } catch (Exception e) {
            return false; // Error message is not visible
        }
    }
    public boolean isCheckYourEmailVisible() {
        By errorMessageLocator = By.xpath("//h3[text()='Check your email!']");
        try {
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
            return true; // Error message is visible
        } catch (Exception e) {
            return false; // Error message is not visible
        }
    }
    public boolean isErrorMessageMPVisible() {
        By errorMessageLocator = By.xpath("//p[text()='This email has already been used.']");
        try {
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
            return true; // Error message is visible
        } catch (Exception e) {
            return false; // Error message is not visible
        }
    }

    protected void clearData(By elementLocator) {
        WebElement element = driver.findElement(elementLocator);
        element.clear();
    }

    protected void clearAndEnterText(By elementLocator, String text) {
        WebElement element = driver.findElement(elementLocator);
        element.click(); // Focus on the element
        element.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END)); // Select all text
        element.sendKeys(Keys.BACK_SPACE); // Delete all selected text
        element.sendKeys(text); // Enter the new text
    }

    public void switchToWindow(WebDriver driver, int windowIndex) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> d.getWindowHandles().size() > 1);
        // Get all window handles
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());

        // Ensure the index is within bounds
        for (int i = 0; i < windowHandles.size(); i++) {
            driver.switchTo().window(windowHandles.get(i));
//            System.out.println("Window " + i + " Title: " + driver.getTitle());
        }

        // Ensure the index is within bounds
        if (windowIndex >= 0 && windowIndex < windowHandles.size()) {
            // Switch to the specified window
            driver.switchTo().window(windowHandles.get(windowIndex));
//            System.out.println("Switched to window index: " + windowIndex);
        } else {
//            System.out.println("Invalid window index: " + windowIndex);
        }
    }
    public void switchToNewestWindow() {
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> newWindows = new ArrayList<>(windowHandles);
        driver.switchTo().window(newWindows.get(newWindows.size() - 1));
    }
    public boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean waitToElementVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}