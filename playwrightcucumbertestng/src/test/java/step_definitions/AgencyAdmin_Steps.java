package step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageobjects.AgencyAdminPageObjects;
import pageobjects.ClientSelectionPageObjects;
import pageobjects.HomePageObjects;
import pageobjects.LoginPageObjects;

public class AgencyAdmin_Steps {

    public AgencyAdminPageObjects agencyAdminPageObjects;

    public AgencyAdmin_Steps(AgencyAdminPageObjects agencyAdminPageObjects) {
        this.agencyAdminPageObjects = agencyAdminPageObjects;
    }

    @When("click on Travel Policy Tab")
    public void click_on_travel_policy_tab() {
        agencyAdminPageObjects.clickOnTravelPolicy();
    }

    @When("Disable Travel Policy")
    public void disable_travel_policy() {
        agencyAdminPageObjects.uncheckPolicyActiveCheckbox();
    }
    @When("Click on update button")
    public void click_on_update_button() {
        agencyAdminPageObjects.clickOnUpdateButton();
        agencyAdminPageObjects.closeAgencyAdminPage();
    }
    @Then("Validate Travel Policy should be disabled successfully")
    public void validate_travel_policy_should_be_disabled_successfully() {

    }



}
