package sumergebookingtask;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

public class HomePage extends BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
//String checkInDateFile = checkInDate, checkOutDateFile = checkOutDate,
       //     checkInDateXpath = "//span[@data-date='"+checkInDateFile+"']",
         //   checkOutDateXpath = "//span[@data-date='"+checkOutDateFile+"']";
    
    public HomePage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
    }

    @FindBy(id = ":rh:")
    private WebElement searchBox;

    @FindBy(xpath = "//*[text()='Alexandria Governorate, Egypt']")
    private WebElement locationCity;

    @FindBy(xpath = "//button[@data-testid='date-display-field-start']")
    private WebElement dateInput;

   /* @FindBy(xpath = "//span[@data-date='2025-01-20']")
    private WebElement checkInDate;

    @FindBy(xpath = "//span[@data-date='2025-01-29']")
    private WebElement checkOutDate;*/
    
   // private WebElement checkInDate = driver.findElement(By.xpath(checkInDateFile));
    
    //private WebElement checkOutDate = driver.findElement(By.xpath(checkOutDateFile));
	

    @FindBy(xpath = "//button[@data-testid='date-display-field-start']//span")
    private WebElement selectedStartDate;

    @FindBy(xpath = "//button[@data-testid='date-display-field-end']//span")
    private WebElement selectedEndDate;

    @FindBy(css = "[type='submit']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@role='list']")
    private WebElement hotelsListContainer;

    @FindBy(xpath = "//*[text()='Tolip Hotel Alexandria']")
    private WebElement hotelName;

    @FindBy(xpath = "//*[text()='Tolip Hotel Alexandria']")
    private WebElement seeAvailabiltyButton;

   

    public void clickOnSearchBox() {
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.click();
    }

    public void enterLocation(String location) {
        searchBox.sendKeys(location);
        wait.until(ExpectedConditions.elementToBeClickable(locationCity));
        locationCity.click();
    }

    public void openDatePicker() {
        dateInput.click();
    }

    public void selectCheckInDate(WebDriver driver) {
        WebElement checkInDateElem = driver.findElement(By.xpath("//span[@data-date='"+checkInDate+"']"));
        while (!checkInDateElem.isDisplayed()) {
            scrollToElement(dateInput);
        }
        checkInDateElem.click();
    }

    public void selectCheckOutDate(WebDriver driver) {
        WebElement checkOutDateElem = driver.findElement(By.xpath("//span[@data-date='"+checkOutDate+"']"));
        while (!checkOutDateElem.isDisplayed()) {
            scrollToElement(dateInput);
        }
        checkOutDateElem.click();
    }

    public void clickSearch() {
        searchButton.click();
    }

    public String getCheckInDate() {
        return  selectedStartDate.getText();
    }

    public String getCheckOutDate() {
        return selectedEndDate.getText();
    }

    public void selectHotel() {
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Tolip Hotel Alexandria']")));
            while (!hotelName.isDisplayed()) {
                scrollToElement(hotelsListContainer);
            }
        

        wait.until(ExpectedConditions.elementToBeClickable(hotelName));
        hotelName.click();
    
    }
}
