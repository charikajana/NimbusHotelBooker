package pageobjects;

import io.cucumber.java.en.Then;
import org.sabre.Browserfactory.BrowserManager;
import org.sabre.basefactory.BasePage;

import java.util.logging.Logger;

public class HomePageObjects extends BasePage {
    private static final Logger logger = Logger.getLogger(LoginPageObjects.class.getName());
    private  final String TOOLS_BUTTON = "#ctl00_ucNavigation_liTools";
    private  final String AGENCY_ADMIN_LINK = "//a[text()='Agency Admin']";
    private  final String USERNAME_FIELD = "#txtUserName";
    private  final String PASSWORD_FIELD = "#txtPassword";
    private  final String LOGIN_BUTTON = "#btnLogin";
    private  final String SEARCH_BUTTON = "#ctl00_btnSearch";
    public HomePageObjects(BrowserManager browserManager) {
        super(browserManager);
    }

    public void clickOnToolsLink(){
        waitAndClick(TOOLS_BUTTON);
    }
    public void clickOnAgencyAdminLink(){
        switchToNewWindowAfterClick(AGENCY_ADMIN_LINK);
    }
    public void loginAgencyAdmin(String username, String password){
        waitAndEnterText(USERNAME_FIELD, username);
        waitAndEnterText(PASSWORD_FIELD, password);
        waitAndClick(LOGIN_BUTTON);
    }

    public void clickOnSearchButton(){
        waitAndClick(SEARCH_BUTTON);
    }




}
