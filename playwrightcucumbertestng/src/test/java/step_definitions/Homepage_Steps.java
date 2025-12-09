package step_definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.sabre.Browserfactory.BrowserManager;
import org.sabre.basefactory.BasePage;
import pageobjects.HomePageObjects;
import pageobjects.LoginPageObjects;

public class Homepage_Steps {

    public LoginPageObjects loginPageObjects;
    public HomePageObjects homePageObjects;

    public Homepage_Steps(LoginPageObjects loginPageObjects,HomePageObjects homePageObjects) {
        this.loginPageObjects = loginPageObjects;
        this.homePageObjects = homePageObjects;
    }

    @Given("navigate to hotelbooker application")
    public void navigate_to_hotelbooker_application() {
        loginPageObjects.navigateToLoginPage();

    }
    @When("enter username and password")
    public void enter_username_and_password() {
        loginPageObjects.enterUserName("QA_Sabre");
        loginPageObjects.enterPassword("Te5t@1234");
    }
    @And("click login button")
    public void click_login_button() {
       loginPageObjects.clickLoginButton();

    }
    @Then("validate successful login")
    public void validate_successful_login() {
    System.out.println("validate successful login");
    }

    @When("Click on Tools Link")
    public void click_on_tools_link() {
        homePageObjects.clickOnToolsLink();
    }
    @When("Select Agency Admin Link")
    public void select_agency_admin_link() {
        homePageObjects.clickOnAgencyAdminLink();
        homePageObjects.loginAgencyAdmin("QA_Sabre","Te5t@1234");
    }

    @Then("Click on Search Button")
    public void click_on_search_button() {
        homePageObjects.clickOnSearchButton();
    }

}
