package com.sabre.hotelbooker.hotelbookerutility;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

/**
 * ExplicitWaitUtility - Comprehensive wait strategies for stable automation
 * Handles page loading, JavaScript execution, and backend API calls
 */
public class ExplicitWaitUtility {
    
    private final Page page;
    private static final int DEFAULT_TIMEOUT = 30000; // 30 seconds
    private static final int LONG_TIMEOUT = 60000; // 60 seconds
    private static final int SHORT_TIMEOUT = 10000; // 10 seconds
    
    public ExplicitWaitUtility(Page page) {
        this.page = page;
    }
    
    /**
     * Wait for complete page load including all resources
     */
    public void waitForPageLoad() {
        page.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions().setTimeout(DEFAULT_TIMEOUT));
        waitForJavaScriptToComplete();
    }
    
    /**
     * Wait for JavaScript execution to complete
     */
    public void waitForJavaScriptToComplete() {
        page.waitForFunction("() => document.readyState === 'complete'", null, 
            new Page.WaitForFunctionOptions().setTimeout(DEFAULT_TIMEOUT));
        
        // Wait for jQuery if present
        page.waitForFunction("() => typeof jQuery === 'undefined' || jQuery.active === 0", null,
            new Page.WaitForFunctionOptions().setTimeout(SHORT_TIMEOUT));
    }
    
    /**
     * Wait for all AJAX/API calls to complete
     */
    public void waitForAjaxCallsToComplete() {
        // Wait for jQuery AJAX calls
        page.waitForFunction("() => typeof jQuery === 'undefined' || jQuery.active === 0", null,
            new Page.WaitForFunctionOptions().setTimeout(DEFAULT_TIMEOUT));
        
        // Wait for fetch API calls
        String fetchWaitScript = "() => {" +
            "return new Promise(resolve => {" +
                "if (window.activeFetchCount === undefined) {" +
                    "window.activeFetchCount = 0;" +
                    "const originalFetch = window.fetch;" +
                    "window.fetch = function(...args) {" +
                        "window.activeFetchCount++;" +
                        "return originalFetch.apply(this, args).finally(() => {" +
                            "window.activeFetchCount--;" +
                        "});" +
                    "};" +
                "}" +
                "const checkActive = () => {" +
                    "if (window.activeFetchCount === 0) {" +
                        "resolve(true);" +
                    "} else {" +
                        "setTimeout(checkActive, 100);" +
                    "}" +
                "};" +
                "checkActive();" +
            "});" +
        "}";
        page.waitForFunction(fetchWaitScript, null, new Page.WaitForFunctionOptions().setTimeout(DEFAULT_TIMEOUT));
    }
    
    /**
     * Wait for element to be visible and stable
     */
    public void waitForElementVisible(String selector) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions()
            .setState(WaitForSelectorState.VISIBLE)
            .setTimeout(DEFAULT_TIMEOUT));
    }
    
    /**
     * Wait for element to be clickable (visible and enabled)
     */
    public void waitForElementClickable(String selector) {
        waitForElementVisible(selector);
        page.waitForFunction(
            "selector => document.querySelector(selector) && !document.querySelector(selector).disabled",
            selector,
            new Page.WaitForFunctionOptions().setTimeout(DEFAULT_TIMEOUT)
        );
    }
    
    /**
     * Wait for text to appear in element
     */
    public void waitForTextInElement(String selector, String expectedText) {
        String script = "(args) => { const el = document.querySelector(args.selector); return el && el.textContent.includes(args.text); }";
        java.util.Map<String, Object> args = new java.util.HashMap<>();
        args.put("selector", selector);
        args.put("text", expectedText);
        
        page.waitForFunction(script, args, new Page.WaitForFunctionOptions().setTimeout(DEFAULT_TIMEOUT));
    }
    
    /**
     * Wait for element to disappear
     */
    public void waitForElementToDisappear(String selector) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions()
            .setState(WaitForSelectorState.HIDDEN)
            .setTimeout(DEFAULT_TIMEOUT));
    }
    
    /**
     * Wait for dropdown options to load
     */
    public void waitForDropdownOptionsToLoad(String dropdownSelector) {
        waitForElementVisible(dropdownSelector);
        page.waitForFunction(
            "selector => { const dropdown = document.querySelector(selector); return dropdown && dropdown.options && dropdown.options.length > 1; }",
            dropdownSelector,
            new Page.WaitForFunctionOptions().setTimeout(DEFAULT_TIMEOUT)
        );
    }
    
    /**
     * Wait for modal to be fully loaded
     */
    public void waitForModalToLoad(String modalSelector) {
        waitForElementVisible(modalSelector);
        // Wait for modal animation to complete
        page.waitForTimeout(500);
        // Ensure modal content is loaded
        page.waitForFunction(
            "selector => { const modal = document.querySelector(selector); return modal && modal.style.display !== 'none'; }",
            modalSelector,
            new Page.WaitForFunctionOptions().setTimeout(DEFAULT_TIMEOUT)
        );
    }
    
    /**
     * Wait for search results to load
     */
    public void waitForSearchResultsToLoad(String resultsContainerSelector) {
        waitForElementVisible(resultsContainerSelector);
        waitForAjaxCallsToComplete();
        // Additional wait for content to populate
        page.waitForFunction(
            "selector => { const container = document.querySelector(selector); return container && container.children.length > 0; }",
            resultsContainerSelector,
            new Page.WaitForFunctionOptions().setTimeout(LONG_TIMEOUT)
        );
    }
    
    /**
     * Wait for form submission to complete
     */
    public void waitForFormSubmission() {
        waitForAjaxCallsToComplete();
        waitForJavaScriptToComplete();
        // Additional wait for any redirect or page change
        page.waitForTimeout(1000);
    }
    
    /**
     * Wait for datepicker to load
     */
    public void waitForDatepickerToLoad() {
        waitForElementVisible(".datepicker");
        page.waitForFunction(
            "() => { const datepicker = document.querySelector('.datepicker'); return datepicker && datepicker.style.display !== 'none'; }",
            null,
            new Page.WaitForFunctionOptions().setTimeout(SHORT_TIMEOUT)
        );
    }
    
    /**
     * Wait for network activity to settle
     */
    public void waitForNetworkIdle() {
        page.waitForLoadState(LoadState.NETWORKIDLE, new Page.WaitForLoadStateOptions().setTimeout(LONG_TIMEOUT));
    }
    
    /**
     * Custom wait with condition check
     */
    public void waitForCondition(String jsCondition, int timeoutMs) {
        page.waitForFunction(jsCondition, null, 
            new Page.WaitForFunctionOptions().setTimeout(timeoutMs));
    }
    
    /**
     * Wait for element count to be specific number
     */
    public void waitForElementCount(String selector, int expectedCount) {
        String script = "(args) => document.querySelectorAll(args.selector).length === args.count";
        java.util.Map<String, Object> args = new java.util.HashMap<>();
        args.put("selector", selector);
        args.put("count", expectedCount);
        
        page.waitForFunction(script, args, new Page.WaitForFunctionOptions().setTimeout(DEFAULT_TIMEOUT));
    }
    
    /**
     * Wait for spinner/loader to disappear
     */
    public void waitForSpinnerToDisappear() {
        String[] spinnerSelectors = {
            ".spinner", ".loading", ".loader", 
            "[class*='spin']", "[class*='load']",
            ".fa-spinner", ".fa-circle-o-notch"
        };
        
        for (String selector : spinnerSelectors) {
            try {
                waitForElementToDisappear(selector);
            } catch (Exception e) {
                // Continue if spinner not found
            }
        }
    }
    
    /**
     * Comprehensive wait for page stability
     */
    public void waitForPageStability() {
        waitForPageLoad();
        waitForAjaxCallsToComplete();
        waitForSpinnerToDisappear();
        waitForNetworkIdle();
        // Final stability check
        page.waitForTimeout(500);
    }
}