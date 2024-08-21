package PageElements;

import Actions.BasePage;
import PageElements.PaymentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class PaymentPageMP extends BasePage {
    private PaymentPage paymentPage;
    public PaymentPageMP(WebDriver driver) {
        super(driver);
    }
    private final By HEADER_AUTH = By.xpath("//button[contains(@class,'gap-5') and contains(@class,'rounded-lg ')]");
    private final By SIGN_UP = By.xpath("//button[text()='Sign Up']");
    private final By LOGIN = By.xpath("//button[text()='Login']");
    private final By LOGOUT = By.xpath("//button[text()='Log Out']");
    private final By LOGIN_CONFIRM = By.xpath("(//button[text()='Login'])[2]");
    private final By FIRST_NAME = By.xpath("//input[@name='first_name']");
    private final By LAST_NAME = By.xpath("//input[@name='last_name']");
    private final By EMAIL  = By.xpath("//input[@name='email']");
    private final By PASSWORD = By.xpath("//input[@name='password']");
    private final By REGISTER = By.xpath("//button[text()='Register']");
    private final By CHECK_EMAIL_SCREEN = By.xpath("//h3[text()='Check your email!']");

    private final By EMAIL_EMAIL = By.xpath("//input[@type='email']");
    private final By EMAIL_CONTINUE = By.xpath("//span[text()='Next']");
    private final By EMAIL_PASSWORD = By.xpath("//input[@type='password']");
    private final By EMAIL_INBOX = By.xpath("//a[text()='Inbox']");
    private final By EMAIL_TOTEST = By.xpath("//a[text()='To Test']");
    private final By EMAIL_OPTION = By.xpath("//div[@aria-label ='More email options']");
    private final  By MARK_ALLREAD = By.xpath("//div[text() ='Mark all as read']");
    private final By CLICK_NEW_EMAIL = By.xpath("//tr[@class='zA zE']");

    private final By EMAIL_NEW_INBOX = By.xpath("(//span[text()='Welcome to Everfit Marketplace'])[2]");
    private final By EMAIL_EXPAND = By.xpath("//div[@data-tooltip='Show trimmed content']");
    private final By COMPLETE_REGISTRATION = By.xpath("//span[text()='Complete my Registration']");
    private final By EMAIL_NEW_MESSSAGE = By.xpath("//span[text()=' New Message from marketplace-noreply@everfit-mail.com']");
    private final By SHOW_BUTTON = By.xpath("//span[text()='Show']");

    private final By YOUR_NAME = By.xpath("//input[@id='card_name']");
    private final By IFRAME_CARD_NUMBER = By.xpath("//iframe[@title='Secure card number input frame']");
    private final By CARD_NUMBER = By.xpath("//input[@type='text' and @name='cardnumber']");
    private final By IFRAME_MM_YY = By.xpath("//iframe[@title='Secure expiration date input frame']");
    private final By MM_YY = By.xpath("//input[@type='text' and @name='exp-date']");
    private final By IFRAME_CVC = By.xpath("//iframe[@title='Secure CVC input frame']");
    private final By CVC = By.xpath("//input[@type='text' and @name='cvc']");
    private final By COUNTRY = By.xpath("//label[text()='Country']");
    private final By LIST_COUNTRY = By.xpath("//div[text()='Algeria']");
    private final By BILLING_ADDRESS = By.xpath("//input[@id='billing_address']");
    private final By CITY = By.xpath("//input[@id='city']");
    private final By ZIP = By.xpath("//input[@id='postal_code']");
    private final By PHONE_NUMBER = By.xpath("//input[@id='phone_number']");
    private final By PURCHASE = By.xpath("//button[text()='Purchase']");
    private final By TYPE_FULL_NAME = By.xpath("//input[@placeholder='Type your full name']");
    private final By CONFIRM = By.xpath("//button[text()='Authorize & Confirm Payment']");
    private final By PAYMENT_SUCCESS = By.xpath("//div[text()='Payment Success!']");


    public void loginEmail() {
        sendKeys(EMAIL_EMAIL, "lamnguyenbao@everfit.io");
        click(EMAIL_CONTINUE);
        waitForVisibleOf(EMAIL_PASSWORD);
        sendKeys(EMAIL_PASSWORD, "lamnguyenbao@everfit.io");
        click(EMAIL_CONTINUE);
        waitForVisibleOf(EMAIL_INBOX);
    }
    String randomNumberMP = generateRandomNumber();
    public void signUp() {
        String email = "lamnguyenbao+8stg" + randomNumberMP + "@everfit.io";

        click(HEADER_AUTH);
        try {
            // Wait until SIGN_UP is visible
            WebElement signUpElement = new WebDriverWait(driver, Duration.ofSeconds(1))
                    .until(ExpectedConditions.visibilityOfElementLocated(SIGN_UP));
            signUpElement.click(); // Click SIGN_UP if visible
        } catch (TimeoutException e) {
            // If SIGN_UP is not visible within the timeout, click LOGOUT instead
            WebElement logoutElement = new WebDriverWait(driver, Duration.ofSeconds(1))
                    .until(ExpectedConditions.visibilityOfElementLocated(LOGOUT));
            logoutElement.click();
            click(HEADER_AUTH);
            click(SIGN_UP);
        }
        sendKeys(FIRST_NAME, "8stg");
        sendKeys(LAST_NAME, randomNumberMP);
        sendKeys(EMAIL, email);
        sendKeys(PASSWORD, "Pass1234!");
        click(REGISTER);
        if (isErrorMessageMPVisible()) {
            String anotherEmail;
            do {
                String randomNumberMP2 = generateRandomNumber2();
                anotherEmail = "lamnguyenbao+8stg" + randomNumberMP2 + "@everfit.io";
                clearAndEnterText(EMAIL, anotherEmail);
                clearAndEnterText(LAST_NAME, randomNumberMP2);
                click(REGISTER);
                randomNumberMP = randomNumberMP2;
            } while (isErrorMessageMPVisible());
        }
    }

    public void verifyEmail()
        {
            switchToWindow(driver, 0);
            int maxAttempts = 10;
            int attempts = 0;
            while (attempts < maxAttempts) {
                try {
                    waitForVisibleOf(EMAIL_NEW_INBOX);
                    break;
                } catch (Exception e) {
                    click(EMAIL_INBOX);
                    waitForVisibleOf(EMAIL_NEW_INBOX);
                    attempts++;
                }
            }
            attempts = 0;
            click(EMAIL_NEW_INBOX);
            String email2 = "lamnguyenbao+8stg" + randomNumberMP + "@everfit.io";
            By EMAIL_GET_INFO = By.xpath("//span[@email='" + email2 + "']");
            System.out.println("EMAIL_GET_INFO=" + EMAIL_GET_INFO);
            boolean emailGetInfoFound = false;
            while (attempts < maxAttempts) {
                try {
                    WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
                    shortWait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_GET_INFO));
                    System.out.println("Element found.");
                    break;
                } catch (Exception e) {
                    System.out.println("Element not found, try again...");
                    click(EMAIL_INBOX);
                    click(EMAIL_NEW_INBOX);
                    attempts++;
                }
            }
            try {
                // Try to click COMPLETE_REGISTRATION
                click(COMPLETE_REGISTRATION);
            } catch (NoSuchElementException | TimeoutException e) {
                // If COMPLETE_REGISTRATION is not found, click EMAIL_EXPAND again
                click(EMAIL_EXPAND);
                click(COMPLETE_REGISTRATION);
            }
            switchToNewestWindow();
            waitForVisibleOf(HEADER_AUTH);
        }
    public void loginCurrentAccount() {
        String email2 = "lamnguyenbao+8stg" + randomNumberMP + "@everfit.io";
        click(HEADER_AUTH);
        click(LOGIN);
        sendKeys(EMAIL,email2);
        sendKeys(PASSWORD,"Pass1234!");
        click(LOGIN_CONFIRM);
        System.out.println("finalEmail= "+email2);
    }
    public void enterCardDetails() {
        waitForVisibleOf(YOUR_NAME);
        sendKeys(YOUR_NAME, "Name");
        waitForVisibleOf(IFRAME_CARD_NUMBER);
        switchToFrame(IFRAME_CARD_NUMBER);
        sendKeys(CARD_NUMBER, "4242 4242 4242 4242");
        driver.switchTo().defaultContent();
        switchToFrame(IFRAME_MM_YY);
        sendKeys(MM_YY, "444");
        driver.switchTo().defaultContent();
        switchToFrame(IFRAME_CVC);
        sendKeys(CVC, "444");
        driver.switchTo().defaultContent();
    }
    public void enterBillingDetails() {
        scrollIntoView(PURCHASE);
        sleep(500);
        click(COUNTRY);
        click(LIST_COUNTRY);
        sendKeys(BILLING_ADDRESS, "Address");
        sendKeys(CITY, "City");
        sendKeys(ZIP, "50000");
        sendKeys(PHONE_NUMBER,"18237521651");
        click(PURCHASE);
        try {
            waitForVisibleOf(TYPE_FULL_NAME);
            sendKeys(TYPE_FULL_NAME, "Name");
            click(CONFIRM);
            waitForVisibleOf(PAYMENT_SUCCESS);
        } catch (Exception ignored) {
        }
    }
    public void logout() {
        click(HEADER_AUTH);
        click(LOGOUT);
    }
    }
