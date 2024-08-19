package PageElements;

import Actions.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPageMP extends BasePage {

    public PaymentPageMP(WebDriver driver) {
        super(driver);
    }
    private final By HEADER_AUTH = By.xpath("//button[contains(@class,'gap-5') and contains(@class,'rounded-lg ')]");
    private final By SIGN_UP = By.xpath("//button[text()='Sign Up']");
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
    public void loginEmail() {
        sendKeys(EMAIL_EMAIL, "lamnguyenbao@everfit.io");
        click(EMAIL_CONTINUE);
        waitForVisibleOf(EMAIL_PASSWORD);
        sendKeys(EMAIL_PASSWORD, "lamnguyenbao@everfit.io");
        click(EMAIL_CONTINUE);
        waitForVisibleOf(EMAIL_INBOX);
    }
}
