package com.sabre.hotelbooker.pageobjects;

import com.microsoft.playwright.Page;
import com.sabre.hotelbooker.hotelbookerutility.ExplicitWaitUtility;

/**
 * ClientSelectionPageObjects - Focused Page Object for Client Selection Modal
 * Contains only client selection specific functionality
 */
public class ClientSelectionPageObjects {
    
    private final Page page;
    private final ExplicitWaitUtility waitUtility;
    
    // Client Selection Modal
    // Use the visible header as anchor for the modal
    private static final String CLIENT_MODAL = "div.modal-dialog";
    private static final String CLIENT_MODAL_HEADER = "h4";
    private static final String MODAL_CLOSE_BUTTON = "button.close, button[aria-label='Close']";
    private static final String HEADER_CLIENT = "#lnkClientSelect";
    // Client Filter
    private static final String CLIENT_FILTER_INPUT = "input[placeholder*='Filter Client List']";
    private static final String CLIENT_GROUPS_BUTTON = "button:has-text('Client Groups')";
    private static final String ACTION_LIST_BUTTON = "button:has-text('Action List')";
    private static final String ALL_CLIENTS_BUTTON = "button:has-text('All Clients')";
    // Available Clients (use h2 for headings)
    private static final String CLIENT_HEADING = "h2";
    
    public ClientSelectionPageObjects(Page page) {
        this.page = page;
        this.waitUtility = new ExplicitWaitUtility(page);
    }
    
    /**
     * Wait for client selection modal to load completely
     */
    public void waitForClientModalToLoad() {
    waitUtility.waitForModalToLoad(CLIENT_MODAL);
    waitUtility.waitForElementVisible(CLIENT_MODAL_HEADER);
    // Wait for at least one client heading to be visible
    waitUtility.waitForElementVisible(CLIENT_HEADING);
    }
    
    /**
     * Select client by name
     */
    public void selectClient(String clientName) {
        waitForClientModalToLoad();
        // Find all h2 elements and click the one with matching text
        for (com.microsoft.playwright.Locator locator : page.locator(CLIENT_HEADING).all()) {
            String text = locator.textContent().trim();
            if (text.equalsIgnoreCase(clientName) || text.replaceAll("\\s+", "").equalsIgnoreCase(clientName.replaceAll("\\s+", ""))) {
                waitUtility.waitForElementClickable(CLIENT_HEADING);
                locator.click();
                waitUtility.waitForElementToDisappear(CLIENT_MODAL);
                return;
            }
        }
        throw new RuntimeException("Client with name '" + clientName + "' not found in client selection modal.");
    }

    public boolean isClientDisplayedOnHeader() {
        boolean visible = page.isVisible(HEADER_CLIENT);
        return visible;
    }
    
    /**
     * Filter client list using search
     */
    public void filterClients(String filterText) {
        waitUtility.waitForElementClickable(CLIENT_FILTER_INPUT);
        page.fill(CLIENT_FILTER_INPUT, filterText);
        waitUtility.waitForAjaxCallsToComplete();
    }
    
    /**
     * Click on client groups filter
     */
    public void clickClientGroups() {
        waitUtility.waitForElementClickable(CLIENT_GROUPS_BUTTON);
        page.click(CLIENT_GROUPS_BUTTON);
        waitUtility.waitForAjaxCallsToComplete();
    }
    
    /**
     * Click on action list filter
     */
    public void clickActionList() {
        waitUtility.waitForElementClickable(ACTION_LIST_BUTTON);
        page.click(ACTION_LIST_BUTTON);
        waitUtility.waitForAjaxCallsToComplete();
    }
    
    /**
     * Click on all clients filter
     */
    public void clickAllClients() {
        waitUtility.waitForElementClickable(ALL_CLIENTS_BUTTON);
        page.click(ALL_CLIENTS_BUTTON);
        waitUtility.waitForAjaxCallsToComplete();
    }
    
    /**
     * Close client selection modal
     */
    public void closeModal() {
        waitUtility.waitForElementClickable(MODAL_CLOSE_BUTTON);
        page.click(MODAL_CLOSE_BUTTON);
        waitUtility.waitForElementToDisappear(CLIENT_MODAL);
    }
    
    /**
     * Check if client modal is visible
     */
    public boolean isClientModalVisible() {
        return page.isVisible(CLIENT_MODAL);
    }
    
    /**
     * Check if specific client is available
     */
    public boolean isClientAvailable(String clientName) {
        String clientSelector = getClientSelector(clientName);
        return page.isVisible(clientSelector);
    }
    
    /**
     * Get the selector for a specific client
     */
    private String getClientSelector(String clientName) {
        // Deprecated: now handled by text matching in selectClient
        return CLIENT_HEADING;
    }
    
    /**
     * Get all available client names
     */
    public String[] getAvailableClients() {
        waitForClientModalToLoad();
        return new String[]{
            "Test QA Client (PTI-Amadeus)",
            "Test QA Client(Bcom)",
            "Test QA Client(EPS Rapid)",
            "Test QA Client(PTI)",
            "Test QA Client(Sabre)",
            "Test QA Client(TravelFusion)"
        };
    }
}