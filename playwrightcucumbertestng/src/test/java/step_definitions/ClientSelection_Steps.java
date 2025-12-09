package step_definitions;


import io.cucumber.java.en.When;
import org.sabre.Browserfactory.BrowserManager;
import org.sabre.basefactory.BasePage;
import pageobjects.ClientSelectionPageObjects;

public class ClientSelection_Steps {
    private  final String ClientName = "//h2[text()='Test QA Client(Sabre)']";

    public ClientSelectionPageObjects clientSelectionPageObjects;

    public ClientSelection_Steps(ClientSelectionPageObjects clientSelectionPageObjects) {
        this.clientSelectionPageObjects = clientSelectionPageObjects;
    }

    @When("Select the client {string}")
    public void select_the_client(String string) {
        clientSelectionPageObjects.waitAndClick(ClientName);
    }

}
