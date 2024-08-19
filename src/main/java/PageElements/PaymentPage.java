package PageElements;

import Actions.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PaymentPage extends BasePage {

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    private final By YOUR_NAME = By.xpath("//input[@placeholder='Your Name']");
    private final By YOUR_EMAIL = By.xpath("//input[@placeholder='Your Email']");
    private final By PHONE_NUMBER = By.xpath("//input[@placeholder='Phone Number']");
    private final By IFRAME_CARD_NUMBER = By.xpath("//iframe[@title='Secure card number input frame']");
    private final By CARD_NUMBER = By.xpath("//input[@name='cardnumber']");
    private final By IFRAME_CARD = By.xpath("//iframe[@title='Secure expiration date input frame']");
    private final By MM_YY = By.xpath("//input[@name='exp-date']");
    private final By IFRAME_CVC = By.xpath("//iframe[@title='Secure CVC input frame']");
    private final By CVC = By.xpath("//input[@name='cvc']");
    private final By BILLING_ADDRESS = By.xpath("//input[@name='address']");
    private final By OPTIONAL = By.xpath("//input[@name='apartment']");
    private final By CITY = By.xpath("//input[@name='city']");
    private final By ZIP = By.xpath("//input[@name='zip']");
    private final By COUNTRY = By.xpath("//div[text()='Country']//parent::div//following::div");
    private final By LIST_COUNTRY = By.xpath("//div[contains(@id, 'listbox')] //child::div[2]");
    private final By PURCHASE = By.xpath("//button[text()='Purchase']");
    private final By TYPE_FULL_NAME = By.xpath("//input[@placeholder='Type your full name']");
    private final By CONFIRM = By.xpath("//button[text()='Authorize & Confirm Payment']");

    private final By SIGN_UP_LINK = By.xpath("//a[text()='Sign Up']");
    private final By FIRST_NAME = By.xpath("//input[@name='first_name']");
    private final By LAST_NAME = By.xpath("//input[@name='last_name']");
    private final By EMAIL = By.xpath("//input[@name='email']");
    private final By PASSWORD = By.xpath("//input[@name='password']");
    private final By SIGN_UP_BUTTON = By.xpath("//button[text()='Sign up']");
    private final By SUCCESS = By.xpath("//h4[text()='Success!']");
    private final By ERROR_DUPLICATE_EMAIL = By.xpath("//div[text()='Your account is already registered. Please log in or sign up with another email.']");
    public void enterPersonalDetails(String email) {
        sendKeys(YOUR_NAME, "Name");
        sendKeys(YOUR_EMAIL, email);
        sendKeys(PHONE_NUMBER, "18237521651");
    }

    public void enterCardDetails() {
        scrollIntoView(IFRAME_CARD_NUMBER);
        switchToFrame(IFRAME_CARD_NUMBER);
        sendKeys(CARD_NUMBER, "4242 4242 4242 4242");
        driver.switchTo().defaultContent();
        switchToFrame(IFRAME_CARD);
        sendKeys(MM_YY, "444");
        driver.switchTo().defaultContent();
        switchToFrame(IFRAME_CVC);
        sendKeys(CVC, "444");
        driver.switchTo().defaultContent();
    }

    public void enterBillingDetails() {
        sendKeys(BILLING_ADDRESS, "Address");
        sendKeys(OPTIONAL, "Address");
        sendKeys(CITY, "City");
        sendKeys(ZIP, "50000");
    }

    public void completePurchase() {
        click(COUNTRY);
        click(LIST_COUNTRY);
        click(PURCHASE);
        waitForVisibleOf(TYPE_FULL_NAME);
        sendKeys(TYPE_FULL_NAME, "Name");
        click(CONFIRM);
        click(SIGN_UP_LINK);
    }
    public String randomNumber = generateRandomNumber();
    public void signUpNewAccount()
    {
        sendKeys(FIRST_NAME, "84atd");
        sendKeys(LAST_NAME, randomNumber);
        sendKeys(PASSWORD, "Pass1234!");
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(SIGN_UP_BUTTON, "disabled", "true")));
        wait.until(ExpectedConditions.elementToBeClickable(SIGN_UP_BUTTON));
        click(SIGN_UP_BUTTON);
            if (isErrorMessageVisible()) {
                String email;
                do {
                    email = "lamnguyenbao+85atd" + randomNumber + "@everfit.io";
                    clearAndEnterText(EMAIL, email);
                    clearAndEnterText(LAST_NAME, randomNumber);
                    click(SIGN_UP_BUTTON);
                } while (isErrorMessageVisible());
            }
//            else {
//                System.out.println("No error message is visible. No need to loop.");
//            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS));
    }
}
