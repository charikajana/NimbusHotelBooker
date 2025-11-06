package com.sabre.hotelbooker.stepdefinitions;

import com.sabre.hotelbooker.hotelbookerutility.HotelBookerUtility;
import com.sabre.hotelbooker.playwrightbase.PlayWrightBaseTest;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.Scenario;

public class HotelAvailabilitySteps {
    private HotelBookerUtility hotelBookerUtility;
    private Scenario scenario;

    @Before
    public void assignUtility(Scenario scenario) {
        hotelBookerUtility = new HotelBookerUtility(PlayWrightBaseTest.page);
        this.scenario = scenario;
    }

    @And("Select the Rate Plan from {string} with refundable {string}")
    public void selected_client_should_display_on_header(String Provider, String RateType) {
        hotelBookerUtility.hotelAvailabilityPage.SelectHotelRatesForProvider(Provider, RateType);
        scenario.log("HotelName " + hotelBookerUtility.hotelAvailabilityPage.selectedHotelDetails.get("HotelName"));
        scenario.log("CancellationPolicy " + hotelBookerUtility.hotelAvailabilityPage.selectedHotelDetails.get("CancellationPolicy"));
        // Remove screenshot attachment logic
    }
}
