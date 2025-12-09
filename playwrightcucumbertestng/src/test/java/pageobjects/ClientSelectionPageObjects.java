package pageobjects;


import org.sabre.Browserfactory.BrowserManager;
import org.sabre.basefactory.BasePage;

public class ClientSelectionPageObjects extends BasePage {

    private final String SELECT_CLIENT_DROPDOWN = "#ctl00_ClientDropdownlist";
    public ClientSelectionPageObjects(BrowserManager browserManager) {
        super(browserManager);
    }

    public void selectClient(String clientName) {
       selectDropdownByValue(SELECT_CLIENT_DROPDOWN,clientName);
    }
}
