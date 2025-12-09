package org.sabre.basefactory;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.sabre.Browserfactory.BrowserManager;

public class BasePage {
    private final BrowserManager browserManager;

    public BasePage(BrowserManager browserManager) {
        this.browserManager = browserManager;
    }

    //In Java, "protected" means that the member can be accessed by classes in the same package or subclasses.
    protected BrowserManager getBrowserManager() {
        return browserManager;
    }

    public void navigate(String url) {
        browserManager.getPage().navigate(url);
    }

    public Locator Locator(String locator) {
        return browserManager.getPage().locator(locator);
    }

    public void waitAndClickByRole(String role, String name) {
        Locator element = browserManager.getPage().getByRole(AriaRole.valueOf(role.toUpperCase()), new Page.GetByRoleOptions().setName(name));
        element.click();
    }

    public void waitAndClickBySelector(String selector) {
        browserManager.getPage().waitForSelector(selector, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        browserManager.getPage().click(selector);
    }

    public void waitAndClick(String locator)
    {
        Locator(locator).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Locator(locator).click();
    }
    public void waitAndEnterText(String locator, String text) {
        Locator(locator).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Locator(locator).fill(text);
    }

    public void switchToNewWindowAfterClick(String Locator) {
        Page newPage = browserManager.getContext().waitForPage(() -> {
            browserManager.getPage().click(Locator);
        });
        browserManager.setPage(newPage);
        browserManager.getPage().bringToFront();
    }

    public void selectDropdownByValue(String locator, String value) {
        browserManager.getPage().selectOption(locator, new SelectOption().setLabel(value));
    }

    public void waitAndUncheckCheckbox(String locator) {
        Locator(locator).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Locator(locator).setChecked(false);
    }

    public void waitAndCheckCheckbox(String locator) {
        Locator(locator).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Locator(locator).setChecked(true);
    }

    public void closeCurrentPageAndSwitch(){
        Page currentPage = browserManager.getPage();
        currentPage.close();
        Page firstPage = browserManager.getContext().pages().get(0);
        browserManager.setPage(firstPage);
        firstPage.bringToFront();
    }

}
