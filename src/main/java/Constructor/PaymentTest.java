package Constructor;


import Actions.BasePage;
import PageElements.PaymentPage;
import PageElements.PaymentPageMP;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PaymentTest {
    private WebDriver driver;
    private PaymentPage paymentPage;
    private PaymentPageMP paymentPageMP;
    private final List<String> websiteURLs = Stream.of(
                    "SB553384",
                    "YA328378",
                    "FB304592",
                    "IO675356",
                    "FL052337",
                    "AI547677",
                    "LY543460",
                    "OL663313",
                    "NP946061",
                    "DR610112",
                    "VN422858"
            )
            .map(id -> "https://dev-coach.everfit.io/package/" + id)
            .toList();
    private final List<String> websiteURLsMarketplace = Stream.of(
            "123"
    ).map(id -> "" + id).toList();
    private final String websiteURLEmail = "https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F%3Ftab%3Drm%26ogbl&emr=1&followup=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F%3Ftab%3Drm%26ogbl&ifkv=Ab5oB3ovj5k34RnzmCGQhjyEyRlo0pcjC_F5pjZQvjGvIW90EN_KmKv2f0W0Phk1RqNCElokaPs_OQ&osid=1&passive=1209600&service=mail&flowName=GlifWebSignIn&flowEntry=ServiceLogin&dsh=S-1893503904%3A1724083717557877&ddm=0#inbox";
    private final String websiteURLPackage = "https://package-dev.everfit.io/DC821698";
    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        paymentPage = new PaymentPage(driver);
        paymentPageMP = new PaymentPageMP(driver);

    }
    @Test
    public void TC_001() {
        for (int i = 0; i < websiteURLs.size(); i += 2) {
            // Fetch the first URL in the pair
            String firstURL = websiteURLs.get(i);
            driver.get(firstURL);
            driver.manage().window().maximize();
            paymentPage.randomNumber = paymentPage.generateRandomNumber();
            String email1 = "lamnguyenbao+85atd" + paymentPage.randomNumber + "@everfit.io";
            paymentPage.enterPersonalDetails(email1);
            paymentPage.enterCardDetails();
            paymentPage.enterBillingDetails();
            paymentPage.completePurchase();
            paymentPage.switchToNewestWindow();
            paymentPage.signUpNewAccount();
            System.out.println(email1);

            // Fetch the second URL in the pair (only if it exists)
            if (i + 1 < websiteURLs.size()) {
                String secondURL = websiteURLs.get(i + 1);
                driver.get(secondURL);
                driver.manage().window().maximize();
                String email2 = "lamnguyenbao+85atd" + paymentPage.randomNumber + "@everfit.io";
                paymentPage.enterPersonalDetails(email2);
                paymentPage.enterCardDetails();
                paymentPage.enterBillingDetails();
                paymentPage.completePurchase();
                paymentPage.switchToNewestWindow();
                paymentPage.signUpNewAccount();
                System.out.println(email2);
            }
        }
    }
    @Test
    public void TC_002() {
    driver.get(websiteURLEmail);
        driver.manage().window().maximize();
        paymentPageMP.loginEmail();
        ((JavascriptExecutor) driver).executeScript("window.open()");
        paymentPage.switchToNewestWindow();
        driver.get(websiteURLPackage);
        paymentPage.randomNumber = paymentPage.generateRandomNumber();
        String email = "lamnguyenbao+85atd" + paymentPage.randomNumber + "@everfit.io";
        paymentPageMP.signUp(email, paymentPage.randomNumber, email);
        paymentPage.switchToNewestWindow();
        driver.get(websiteURLPackage);
//        driver.manage().window().maximize();
//        paymentPageMP.loginCurrentAccount();
        paymentPageMP.enterCardDetails();
        paymentPageMP.enterBillingDetails();

    }
    @Test
    public void TC_003() {
        driver.get(websiteURLPackage);
        driver.manage().window().maximize();
        paymentPageMP.loginCurrentAccount();
        paymentPageMP.enterCardDetails();
        paymentPageMP.enterBillingDetails();
    }
    @After
    public void tearDown() {
//        if (driver != null) {
//          driver.quit();
//        }
    }
}
