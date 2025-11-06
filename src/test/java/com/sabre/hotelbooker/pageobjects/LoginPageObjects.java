package com.sabre.hotelbooker.pageobjects;

import com.microsoft.playwright.Page;
import com.sabre.hotelbooker.configreaderutils.ConfigReader;
import com.sabre.hotelbooker.hotelbookerutility.ApplicationConstants;
import com.sabre.hotelbooker.hotelbookerutility.ExplicitWaitUtility;

/**
 * LoginPageObjects - Simple Page Object for HotelBooker Login Page
 */
public class LoginPageObjects {
    
    private final Page page;
    private final ExplicitWaitUtility waitUtility;
    
    // Basic Login Elements (updated to match live page)
    private static final String USERNAME_FIELD = "#ctl00_cphMainContent_txtUserName";
    private static final String PASSWORD_FIELD = "#ctl00_cphMainContent_txtPassword";
    private static final String LOGIN_BUTTON = "#ctl00_cphMainContent_btnLogin";
    
    public LoginPageObjects(Page page) {
        this.page = page;
        this.waitUtility = new ExplicitWaitUtility(page);
    }

    public void navigateToLoginPage() {
        page.navigate(ConfigReader.getProperty(ApplicationConstants.HOTEL_BOOKER_URL));
    }

    public void enterUserName() {
        waitUtility.waitForElementClickable(USERNAME_FIELD);
        String UserName = ConfigReader.getProperty(ApplicationConstants.USER_NAME);
        page.fill(USERNAME_FIELD, UserName);
    }

    public void enterPassword() {
        waitUtility.waitForElementClickable(PASSWORD_FIELD);
        String Password = ConfigReader.getProperty(ApplicationConstants.PASSWORD);
        page.fill(PASSWORD_FIELD, Password);
    }
    public void clickLoginButton() {
        page.click(LOGIN_BUTTON);
        waitUtility.waitForPageStability();
    }

}