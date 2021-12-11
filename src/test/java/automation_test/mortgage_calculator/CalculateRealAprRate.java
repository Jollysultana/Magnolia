package automation_test.mortgage_calculator;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.NavigationBar;


public class CalculateRealAprRate {
    WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            ActOn.browser(driver).openBrowser("https://www.mortgagecalculator.org/");

    }

    @Test
    public void calculateRealApr() {
        new NavigationBar(driver)
                .mouseHoverToRates()
                .navigateToRealApr()
                .waitForPageToLoad()
                .typeHomePrice("200000")
                .clickOnDownPaymentInDollar()
                .typeDownPayment("15000")
                .typeInterestRate("3")
                .clickOnCalculateButton()
                .validateRealAprRate("3.130%");
    }
    @AfterMethod
    public void closeBrowser() {
        //ActOn.browser(driver).close();
    }

}
