
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import sumergebookingtask.BasePage;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {

        try {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://booking.com");            

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage());
        }

    }
    
    @DataProvider(name = "ReservationData")
    public Object[][] getData() throws Exception {
        return BasePage.getTestData("E:\\Nesma/Booking Reservation Data.xlsx", "Sheet1");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
        
 
}