package automation_test.mortgage_calculator_parameterized;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.NavigationBar;
import parameters.DataProviderClass;
import utilities.ReadConfigFiles;


public class CalculateRealAprRateParameterized {
    private static final Logger LOGGER = LogManager.getLogger(CalculateRealAprRateParameterized.class);
    WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        LOGGER.info("--------------------Test Name: Calculate Real APR----------------");

        String browserUrl = ReadConfigFiles.gePropertyValues("Url");
        ActOn.browser(driver).openBrowser(browserUrl);


    }

    @Test(dataProvider = "RealAprRates",dataProviderClass = DataProviderClass.class)
    public void calculateRealApr(String homePrice,String downPayment,String interestRate, String actualApr) {
        new NavigationBar(driver)
                .mouseHoverToRates()
                .navigateToRealApr()
                .waitForPageToLoad()
                .typeHomePrice(homePrice)
                .clickOnDownPaymentInDollar()
                .typeDownPayment(downPayment)
                .typeInterestRate(interestRate)
                .clickOnCalculateButton()
                .validateRealAprRate(actualApr);
    }
    @AfterMethod
    public void closeBrowser() {
        LOGGER.info("----End Test Case:Calculate Real APR ");
        ActOn.browser(driver).close();
    }

}
