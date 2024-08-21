package PageElements;

import Actions.BasePage;
import PageElements.PaymentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPageMP extends BasePage {
    private PaymentPage paymentPage;

    public PaymentPageMP(WebDriver driver) {
        super(driver);
    }
    private final By HEADER_AUTH = By.xpath("//button[contains(@class,'gap-5') and contains(@class,'rounded-lg ')]");
    private final By SIGN_UP = By.xpath("//button[text()='Sign Up']");
    private final By LOGIN = By.xpath("//button[text()='Login']");
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
    public String randomNumber2;
    public void signUp(String email, String lastName, String getEmail)
    {
        click(HEADER_AUTH);
        click(SIGN_UP);
        sendKeys(FIRST_NAME,"84atd");
        sendKeys(LAST_NAME,lastName);
        sendKeys(EMAIL,email);
        sendKeys(PASSWORD,"Pass1234!");
        click(REGISTER);
        if (isErrorMessageMPVisible()) {
            String anotherEmail;
            do {
                randomNumber2 = generateRandomNumber();
                anotherEmail = "lamnguyenbao+85atd" + randomNumber2 + "@everfit.io";
                System.out.println("anotherEmail="+anotherEmail);
                clearAndEnterText(EMAIL, anotherEmail);
                clearAndEnterText(LAST_NAME, randomNumber2);
                click(REGISTER);
            } while (isErrorMessageMPVisible());
        }
        waitForVisibleOf(CHECK_EMAIL_SCREEN);
        switchToWindow(driver,0);
        int maxAttempts = 10;
        int attempts = 0;
        while (attempts < maxAttempts) {
            try {
                waitForVisibleOf(EMAIL_NEW_INBOX);
                break;
            } catch (Exception e) {
                driver.navigate().refresh();
                waitForVisibleOf(EMAIL_NEW_INBOX);
                attempts++;
            }
        }
        attempts = 0;
        click(EMAIL_NEW_INBOX);
        By EMAIL_GET_INFO = By.xpath("//span[@email='" + getEmail +"']");
        System.out.println("EMAIL_GET_INFO=" + EMAIL_GET_INFO);


        boolean emailGetInfoFound = false;
        while (attempts < maxAttempts) {
            try {
                clickEditTime(EMAIL_GET_INFO,1);
                System.out.println("Element found and clicked.");
                break;
            } catch (Exception e) {
                System.out.println("Element not found, reloading page...");
                driver.navigate().refresh();
                attempts++;
            }
        }
        click(EMAIL_EXPAND);
        click(COMPLETE_REGISTRATION);
        switchToWindow(driver,2);
    }
    public void loginCurrentAccount() {
        click(HEADER_AUTH);
        click(LOGIN);
        sendKeys(EMAIL,"lamnguyenbao+dev49@everfit.io");
        sendKeys(PASSWORD,"Pass1234!");
        click(LOGIN_CONFIRM);
        sleep(1000);
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
        waitForVisibleOf(TYPE_FULL_NAME);
        sendKeys(TYPE_FULL_NAME, "Name");
        click(CONFIRM);
        waitForVisibleOf(PAYMENT_SUCCESS);
    }

    }
