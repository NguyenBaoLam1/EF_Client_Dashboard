package Constructor;


import PageElements.PaymentPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class PaymentTest {
    private WebDriver driver;
    private PaymentPage paymentPage;
    private final List<String> websiteURLs = Arrays.asList(
            "https://dev-coach.everfit.io/package/PF511538",
            "https://dev-coach.everfit.io/package/PF511538",
            "https://dev-coach.everfit.io/package/PF511538",
            "https://dev-coach.everfit.io/package/PF511538",
            "https://dev-coach.everfit.io/package/PF511538",
            "https://dev-coach.everfit.io/package/PF511538"
            );

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        paymentPage = new PaymentPage(driver);
    }
    @Test
    public void TC_001() {
        for (String websiteURL : websiteURLs) {
            driver.get(websiteURL);
        driver.manage().window().maximize();
        String email = "lamnguyenbao+85atd"+paymentPage.generateRandomNumber()+"@everfit.io";
        paymentPage.enterPersonalDetails(email);
        paymentPage.enterCardDetails();
        paymentPage.enterBillingDetails();
        paymentPage.completePurchase();
        paymentPage.switchToNewestWindow();
//        paymentPage.switchToWindow(driver, 1);
        paymentPage.signUpNewAccount();
        System.out.println(email);
    }
    }
    @After
    public void tearDown() {
//        if (driver != null) {
//          driver.quit();
//        }
    }
}
