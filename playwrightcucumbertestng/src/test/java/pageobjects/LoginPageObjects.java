package pageobjects;


import org.sabre.Browserfactory.BrowserManager;
import org.sabre.basefactory.BasePage;

import java.util.logging.Logger;

/**
 * LoginPageObjects - Simple Page Object for HotelBooker Login Page
 */
public class LoginPageObjects extends BasePage {
    private static final Logger logger = Logger.getLogger(LoginPageObjects.class.getName());

    private  final String USERNAME_FIELD = "#ctl00_cphMainContent_txtUserName";
    private  final String PASSWORD_FIELD = "#ctl00_cphMainContent_txtPassword";
    private  final String LOGIN_BUTTON = "#ctl00_cphMainContent_btnLogin";
    private  final String USER_MENU_BUTTON_SELECTOR = "#lstUserMenu";
    private  final String LOGOUT_LINK_SELECTOR = "a:has-text('Log Out')";

    public LoginPageObjects(BrowserManager browserManager) {
        super(browserManager);
    }

    public void navigateToLoginPage() {
        navigate("https://hotelbooker.cert.sabre.com/");
    }

    public void enterUserName(String username) {
        waitAndEnterText(USERNAME_FIELD, username);
    }

    public void enterPassword(String password) {
        waitAndEnterText(PASSWORD_FIELD, password);

    }

    public void clickLoginButton() {
        waitAndClick(LOGIN_BUTTON);
    }


}