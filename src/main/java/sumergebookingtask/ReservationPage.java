
package sumergebookingtask;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ReservationPage extends BasePage{
    
    public ReservationPage(WebDriver driver){
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
    }
    
    @FindBy(xpath="//div//h1")
    private WebElement hotelName;
    
    public void checkHotelNameIsDisplaying(){
        Assert.assertTrue(hotelName.isDisplayed());
    }   
    
    public String getHotelName(){
        return hotelName.getText();
    }
    
}
