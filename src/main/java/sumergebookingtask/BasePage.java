package sumergebookingtask;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    static int rowCount, colCount;
    static String checkInDate = "",checkOutDate= "";

    @FindBy(xpath = "//button[@aria-label=\"Dismiss sign-in info.\"]")
    private WebElement geniusOfferWindowClosebutton;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
    }

    public void closeGeniusOfferWindow() {
        wait.until(ExpectedConditions.elementToBeClickable(geniusOfferWindowClosebutton));
        geniusOfferWindowClosebutton.click();
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void switchToNewTab(int tabIndex) {
        Set<String> windowHandles = driver.getWindowHandles();
        ArrayList<String> tabs = new ArrayList<>(windowHandles);
        driver.switchTo().window(tabs.get(tabIndex));
    }

    public static Object[][] getTestData(String filePath, String sheetName) throws IOException, InvalidFormatException {
        FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet =  workbook.getSheet(sheetName);
        rowCount = sheet.getPhysicalNumberOfRows();
        colCount = sheet.getRow(0).getPhysicalNumberOfCells();
        
        Object[][] data = new Object[rowCount - 1][colCount];

                for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                checkInDate = row.getCell(1).toString();
                checkOutDate = row.getCell(2).toString();
                Cell cell = row.getCell(j);
                if (cell != null) {
                    data[i - 1][j] = cell.toString(); // Retrieve the cell data as string
                }
            }
        }
        
        workbook.close();

        return data;

    }
}
