package com.sabre.hotelbooker.stepdefinitions;

import com.sabre.hotelbooker.hotelbookerutility.HotelBookerUtility;
import com.sabre.hotelbooker.playwrightbase.PlayWrightBaseTest;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.Scenario;

public class LoginPageSteps {
    private HotelBookerUtility hotelBookerUtility;

    @Before
    public void assignUtility(Scenario scenario) {
        hotelBookerUtility = new HotelBookerUtility(PlayWrightBaseTest.page);
    }

    @Given("Open Browser and Navigate to HotelBooker")
    public void user_is_on_login_page() {
        hotelBookerUtility.loginPageObjects.navigateToLoginPage();
    }

    @When("user enters username and password")
    public void user_enters_username_and_password() {
        hotelBookerUtility.loginPageObjects.enterUserName();
        hotelBookerUtility.loginPageObjects.enterPassword();
    }

    @When("user clicks login button")
    public void user_clicks_login_button() {
        hotelBookerUtility.loginPageObjects.clickLoginButton();
    }
}