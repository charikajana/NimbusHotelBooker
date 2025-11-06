
package com.sabre.hotelbooker.hotelbookerutility;

import com.microsoft.playwright.Page;
import com.sabre.hotelbooker.pageobjects.ClientSelectionPageObjects;
import com.sabre.hotelbooker.pageobjects.HotelAvailabilityPageObjects;
import com.sabre.hotelbooker.pageobjects.HotelSearchPageObjects;
import com.sabre.hotelbooker.pageobjects.LoginPageObjects;

public class HotelBookerUtility {

    public LoginPageObjects loginPageObjects;
    public ClientSelectionPageObjects clientSelectionPageObjects;
    public HotelSearchPageObjects hotelSearchPage;
    public HotelAvailabilityPageObjects hotelAvailabilityPage;

    

    public HotelBookerUtility(Page page) {
        this.loginPageObjects = new LoginPageObjects(page);
        this.clientSelectionPageObjects = new ClientSelectionPageObjects(page);
        this.hotelSearchPage = new HotelSearchPageObjects(page);
        this.hotelAvailabilityPage = new HotelAvailabilityPageObjects(page);
    }

}
