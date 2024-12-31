
import java.util.ArrayList;
import java.util.Set;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sumergebookingtask.BasePage;
import sumergebookingtask.HomePage;
import sumergebookingtask.HotelPage;
import sumergebookingtask.ReservationPage;

public class BookingReservation extends BaseTest {

    String checkinDate, checkOutDate, hotelName = "Tolip Hotel Alexandria";
    int hotelReservationPage = 1;
    
    
    @Test(priority = 1, dataProvider="ReservationData")
    public void searchForHotels(String location,String checkInDateFile, String checkOutDateFile) throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        BasePage basePage = new BasePage(driver);

        basePage.closeGeniusOfferWindow();
        homePage.clickOnSearchBox();
        homePage.enterLocation(location);
        //homePage.enterLocation("Alexandria");
        homePage.openDatePicker();
        homePage.openDatePicker();

        homePage.selectCheckInDate(driver);
        homePage.selectCheckOutDate(driver);
        checkinDate = homePage.getCheckInDate();
        checkOutDate = homePage.getCheckOutDate();
        homePage.clickSearch();
        homePage.selectHotel();
        System.out.println("Checkin Date:" + checkinDate);
        System.out.println("CheckOut Date:" + checkOutDate);
    }

    @Test(priority = 2)
    public void completeHotelReservation() {
        HotelPage hotelPage = new HotelPage(driver);
        BasePage basePage = new BasePage(driver);
        // basePage.waitForPageToLoad(driver);
        basePage.switchToNewTab(hotelReservationPage);
        hotelPage.selectBedType();
        hotelPage.selectAmount();
        Assert.assertEquals(hotelPage.getSelectedStartDate(), checkinDate);
        Assert.assertEquals(hotelPage.getSelectedEndDate(), checkOutDate);
        hotelPage.clickOnReserveButton();
    }
    
    @Test(priority = 3)
    public void verifyHotelDetailsInReservationPage(){
        ReservationPage reservationPage = new ReservationPage(driver);
        
        reservationPage.checkHotelNameIsDisplaying();
        Assert.assertTrue(reservationPage.getHotelName().contains(hotelName));
    }

}
