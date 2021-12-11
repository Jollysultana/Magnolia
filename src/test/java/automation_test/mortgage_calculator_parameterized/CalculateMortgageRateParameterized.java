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
import utilities.DateUtils;
import utilities.ReadConfigFiles;
import utilities.SqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CalculateMortgageRateParameterized {
    private static final Logger LOGGER = LogManager.getLogger(CalculateMortgageRateParameterized.class);
    WebDriver driver;

    @BeforeMethod
    public void browserInitialization() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LOGGER.info("--------------------Test Name: Calculate Monthly Payment----------------");


        String browserUrl = ReadConfigFiles.gePropertyValues("Url");
        ActOn.browser(driver).openBrowser(browserUrl);

    }

    @Test
    public void calculateMonthlyPayment() throws SQLException {
        String date = DateUtils.returnNextMonth();
        String[] dates = date.split("-");
        String month = dates[0];
        String year = dates[1];

        ResultSet rs = SqlConnector.readData("select * from monthly_mortgage1");

        try {
            while (rs.next()) {
                new NavigationBar(driver)
                        .navigateToHome()
                        .typeHomePrice(rs.getString("homevalue"))
                        .typeDownPayment(rs.getString("downpayment"))
                        .clickOnDownPaymentInDollar()
                        .typeLoanAmount(rs.getString("loanamount"))
                        .typeInterestRate(rs.getString("interestrate"))
                        .typeLoanTermYears(rs.getString("loanterm"))
                        .selectMonth(month)
                        .typeYear(year)
                        .typePropertyTax(rs.getString("propertytax"))
                        .typePMI(rs.getString("pmi"))
                        .typeHoi(rs.getString("homeownerinsurance"))
                        .typeHoa(rs.getString("monthlyhoa "))
                        .selectLoanType(rs.getString("loantype"))
                        .selectRefiOrBuyOption(rs.getString("buyorrefi"))
                        .clickCalculateButton()
                        .validateTotalMonthlyPayment(rs.getString("totalmonthlypayment"));



            }

        }catch (SQLException e){
            LOGGER.error(e.getMessage());
        }


    }
    @AfterMethod
    public void quitBrowser(){
        LOGGER.info("----End Test Case:Calculate Monthly Payment");

        ActOn.browser(driver).close();}
}
