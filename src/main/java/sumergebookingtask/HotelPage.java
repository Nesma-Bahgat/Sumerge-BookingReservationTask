package sumergebookingtask;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelPage extends BasePage {

    public HotelPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
    }

    @FindBy(id = "hprt-table")
    private WebElement availabiltyTable;

    @FindBy(className = "hotelchars")
    private WebElement hotelDetailscontainer;

    @FindBy(xpath = "//div[@class='hotelchars']//button[@data-testid='date-display-field-start']//span")
    private WebElement startDate;

    @FindBy(xpath = "//div[@class='hotelchars']//button[@data-testid='date-display-field-end']//span")
    private WebElement endDate;

    @FindBy(xpath = "//div[@class='rt-bed-type-select']//input[@value='1'][1]")
    private WebElement bedType;

    @FindBy(id = "hprt_nos_select_78883120_373531459_2_34_0_131741")
    private WebElement amount;

    @FindBy(xpath = "//div[@class='hprt-reservation-cta']//button")
    private WebElement reserveButton;

    public void scrollToAvailabilitySection() {
        try {
            while (!availabiltyTable.isDisplayed()) {
                scrollToElement(hotelDetailscontainer);
            }
        } catch (Exception e) {
            scrollToElement(hotelDetailscontainer);
        }
    }

    public String getSelectedStartDate() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
         By.xpath("//div[@class='hotelchars']//button[@data-testid='date-display-field-start']//span")));
        return startDate.getText();
    }

    public String getSelectedEndDate() {
                wait.until(ExpectedConditions.presenceOfElementLocated(
         By.xpath("//div[@class='hotelchars']//button[@data-testid='date-display-field-end']//span")));
        return endDate.getText();
    }

    public void selectBedType() {
        bedType.click();
    }

    public void selectAmount() {
        Select amountDropDown = new Select(amount);
        amountDropDown.selectByValue("1");
    }

    public void clickOnReserveButton() {
        reserveButton.click();
    }

}
