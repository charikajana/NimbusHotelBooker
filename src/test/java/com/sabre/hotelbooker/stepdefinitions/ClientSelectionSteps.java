package com.sabre.hotelbooker.stepdefinitions;

import com.sabre.hotelbooker.hotelbookerutility.HotelBookerUtility;
import com.sabre.hotelbooker.playwrightbase.PlayWrightBaseTest;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class ClientSelectionSteps {
    private HotelBookerUtility hotelBookerUtility;

    @Before
    public void assignUtility() {
        hotelBookerUtility = new HotelBookerUtility(PlayWrightBaseTest.page);
    }

    @When("selects client {string}")
    public void selects_client(String clientName) {
        PlayWrightBaseTest.captureScreenshotWithInfo(PlayWrightBaseTest.page, "Selecting client: ",Hooks.test.get());
        hotelBookerUtility.clientSelectionPageObjects.selectClient(clientName);
    }
    @Then("Validate selected client should display on header")
    public void selected_client_should_display_on_header() {
        assertTrue(hotelBookerUtility.clientSelectionPageObjects.isClientDisplayedOnHeader());
    }

}
