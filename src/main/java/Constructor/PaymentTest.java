package Constructor;


import PageElements.PaymentPage;
import PageElements.PaymentPageMP;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.stream.Stream;

public class PaymentTest {
    private WebDriver driver;
    private PaymentPage paymentPage;
    private PaymentPageMP paymentPageMP;
    private final List<String> websiteURLs001 = Stream.of(
                    "ON264420", // (One time 1$)
                    "AB873993", // (One time 2$)

                    "ON264420", // (One time 1$)
                    "AB873993", // (One time 2$)
//
//                    "CL821359", // (Recurring 1m 2times)
//                    "II372879", // (Recurring 1 month)
//
//                    "CL821359", // (Recurring 1m 2times)
//                    "II372879", // (Recurring 1 month)
//
                    "CL821359", // (Recurring 1m 2times)
                    "RI558348", // (Recurring 2 months)

                    "CL821359", // (Recurring 1m 2times)
                    "RI558348", // (Recurring 2 months)

                    "CL821359", // (Recurring 1m 2times)
                    "RI558348", // (Recurring 2 months)

                    "CL821359", // (Recurring 1m 2times)
                    "RI558348", // (Recurring 2 months)

                    "CL821359", // (Recurring 1m 2times)
                    "RI558348", // (Recurring 2 months)
//
//                    "LH954869", // (Recurring 1m 3 times)
//                    "RI558348", // (Recurring 2 months)
//
//                    "LH954869", // (Recurring 1m 3 times)
//                    "II372879", // (Recurring 1 month)
//
//                    "PB357647", // (Recurring 2w 2times)
//                    "EV834036", // (Recurring 3w)

//                    "CL821359", // (Recurring 1m 2 times)
//                    "YJ068088", // (Recurring 2m 2 times)
//
//                    "II372879", // (Recurring 1 month)
//                    "RI558348", // (Recurring 2 months)
//
//                    "II372879", // (Recurring 1 month)
//                    "RI558348", // (Recurring 2 months)
//
//                    "II372879", // (Recurring 1 month)
//                    "RI558348", // (Recurring 2 months)
//
//                    "CL821359", // (Recurring 1m 2times)
//                    "II372879", // (Recurring 1 month)
//
                    "ON264420", // (One time)
                    "CL821359", // (Recurring 1m 2times)

                    "ON264420", // (One time)
                    "CL821359", // (Recurring 1m 2times)

                    "ON264420", // (One time)
                    "CL821359" // (Recurring 1m 2times)

//                    "ON264420", // (One time)
//                    "CL821359" // (Recurring 1m 2times)

            )
            .map(id -> "https://staging-coach.everfit.io/package/" + id)
            .toList();

    //    private final String websiteURLPackage = "https://landing-dev.everfit.io/product/RH234445";
    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        paymentPage = new PaymentPage(driver);
        paymentPageMP = new PaymentPageMP(driver);
    }

    @Test
    public void TC_001() { //Purchase 2 package with 1 client
        for (int i = 0; i < websiteURLs001.size(); i += 2) {
            // Fetch the first URL in the pair
            String URL = websiteURLs001.get(i);
            driver.get(URL);
            driver.manage().window().maximize();
            String email1 = "lamnguyenbao+8stg" + paymentPage.randomNumber + "@everfit.io";
            paymentPage.enterPersonalDetails(email1);
            paymentPage.enterCardDetails();
            paymentPage.enterBillingDetails();
            paymentPage.completePurchase();
            paymentPage.clickSignUp();
            paymentPage.switchToNewestWindow();
            paymentPage.signUpNewAccount();
            // Fetch the second URL in the pair (only if it exists)
            if (i + 1 < websiteURLs001.size()) {
                String secondURL = websiteURLs001.get(i + 1);
                driver.get(secondURL);
                String email2 = "lamnguyenbao+8stg" + paymentPage.randomNumber + "@everfit.io";
                paymentPage.enterPersonalDetails(email2);
                paymentPage.enterCardDetails();
                paymentPage.enterBillingDetails();
                paymentPage.completePurchase();
                paymentPage.clickLogin();
                paymentPage.switchToNewestWindow();
                paymentPage.loginCurrentAccount();
                System.out.println("Email above is for both packages: " +URL);
                System.out.println("Email above is for both packages: " +secondURL);
            }
        }
    }

    private final List<String> websiteURLs002 = Stream.of(
            "ON264420", // One time 1$
            "CL821359", // Recurring 1m 2 times
            "II372879"  // Recurring 1 month
    ).map(id -> "https://staging-coach.everfit.io/package/" + id).toList();

    @Test
    public void TC_002() { //Purchase 1 package with 1 client
        for (String URL : websiteURLs002) {
            // Fetch the first URL in the pair
            driver.get(URL);
            driver.manage().window().maximize();
            String email1 = "lamnguyenbao+8stg" + paymentPage.randomNumber + "@everfit.io";
            System.out.println("EmailPurchase= " + email1);
            paymentPage.enterPersonalDetails(email1);
            paymentPage.enterCardDetails();
            paymentPage.enterBillingDetails();
            paymentPage.completePurchase();
            paymentPage.clickSignUp();
            paymentPage.switchToNewestWindow();
            paymentPage.onlySignUpNewAccount();
            System.out.println("Email above is for package: " +URL);
        }
    }

    private final List<String> websiteURLs003 = Stream.of(
//            "XZ816313", // (one time 1$)
//            "YF627848", // (one time 2$)
//
//            "XZ816313", // (one time 1$)
//            "YF627848", // (one time 2$)
//
//            "BB790675", // (every 1 month, 2 times)
//            "IW656901", // (every 1 month)
//
//            "BB790675", // (every 1 month 2 times)
//            "JO691876", // (every 2 months)

            "BB790675", // (every 1 month, 2 times)
            "IW656901", // (every 1 month)
//
//            "BB790675", // (every 1 month 2 times)
//            "JO691876", // (every 2 months)

            "QF132219", // (every 1 month 3 times)
            "JO691876", // (every 2 months)

            "QF132219", // (every 1 month 3 times)
            "JO691876", // (every 2 months)

            "GT462348", // (every 2 weeks 2 times)
            "ZZ175928", // (every 2 months)

            "QF132219", // (every 1 month 3 times)
            "IW432910" // (every 2 months 2 times)

    ).map(id -> "https://package-stg.everfit.io/" + id).toList();
    private final String websiteURLEmail = "https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F%3Ftab%3Drm%26ogbl&emr=1&followup=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F%3Ftab%3Drm%26ogbl&ifkv=Ab5oB3ovj5k34RnzmCGQhjyEyRlo0pcjC_F5pjZQvjGvIW90EN_KmKv2f0W0Phk1RqNCElokaPs_OQ&osid=1&passive=1209600&service=mail&flowName=GlifWebSignIn&flowEntry=ServiceLogin&dsh=S-1893503904%3A1724083717557877&ddm=0#inbox";

    @Test
    public void TC_003() { //Purchase 2 package MP with 1 client
        driver.get(websiteURLEmail);
        driver.manage().window().maximize();
        paymentPageMP.loginEmail();
        for (int i = 0; i < websiteURLs003.size(); i += 2) {
            // Fetch the first URL in the pair
            String URL = websiteURLs003.get(i);
            ((JavascriptExecutor) driver).executeScript("window.open()");
            paymentPage.switchToNewestWindow();
            driver.get(URL);
            driver.manage().window().maximize();
            paymentPageMP.signUp();
            paymentPageMP.verifyEmail();
            paymentPageMP.logout();
            paymentPageMP.loginCurrentAccount();
            paymentPageMP.enterCardDetails();
            paymentPageMP.enterBillingDetails();
            // Fetch the second URL in the pair (only if it exists)
            if (i + 1 < websiteURLs003.size()) {
                String secondURL = websiteURLs003.get(i + 1);
                ((JavascriptExecutor) driver).executeScript("window.open()");
                paymentPage.switchToNewestWindow();
                driver.get(secondURL);
                driver.manage().window().maximize();
                paymentPageMP.enterCardDetails();
                paymentPageMP.enterBillingDetails();
                System.out.println("Email above is for both packages: " +URL);
                System.out.println("Email above is for both packages: " +secondURL);
            }
        }
    }

    private final List<String> websiteURLs004 = Stream.of(
            "MO822344",  // Admin
            "BR363278", //Owner
            "TD987720" //Trainer
    ).map(id -> "https://package-stg.everfit.io/" + id).toList();

    @Test
    public void TC_004() { //Purchase 1 package with 1 client
        driver.get(websiteURLEmail);
        driver.manage().window().maximize();
        paymentPageMP.loginEmail();
        for (String URL : websiteURLs004) {
            // Fetch the first URL in the pair
            ((JavascriptExecutor) driver).executeScript("window.open()");
            paymentPage.switchToNewestWindow();
            driver.get(URL);
            driver.manage().window().maximize();
            paymentPageMP.signUp();
            paymentPageMP.verifyEmail();
            paymentPageMP.logout();
            paymentPageMP.loginCurrentAccount();
            paymentPageMP.enterCardDetails();
            paymentPageMP.enterBillingDetails();
            System.out.println("Email above is for package: " +URL);
        }
    }

    //    @Test
//    public void test() {
//        driver.get(websiteURLEmail);
//        driver.manage().window().maximize();
//        paymentPageMP.loginEmail();
//        ((JavascriptExecutor) driver).executeScript("window.open()");
//        paymentPage.switchToNewestWindow();
//        driver.get(websiteURLPackage);
//        paymentPageMP.signUp();
//        paymentPageMP.verifyEmail();
//        paymentPageMP.enterCardDetails();
//        paymentPageMP.enterBillingDetails();
//    }
    @Test
    public void TC_test004() {

    }

    @After
    public void tearDown() {
//        if (driver != null) {
//          driver.quit();
//        }
    }
}
