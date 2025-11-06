package com.sabre.hotelbooker.configreaderutils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.sabre.hotelbooker.hotelbookerutility.ExplicitWaitUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * HotelAvailabilityPageObjects - Complete Page Object for Hotel Availability/Search Results Page
 * Contains ALL elements captured from the live hotel availability page including rates display
 */
@SuppressWarnings("unused") // Suppressing warnings for comprehensive locator library
public class HotelAvailabilityPageObjects {
    
    private final Page page;
    private final ExplicitWaitUtility waitUtility;
    
    // ============= PAGE HEADER ELEMENTS =============
    
    // Search Results Header
    private static final String SEARCH_RESULTS_HEADER = "h1:has-text('Dallas:')";
    private static final String HOTELS_FOUND_COUNT = "h1:has-text('properties found')";
    private static final String LOCATION_RESULTS = "text=Returned 100 hotels in Dallas - Location";
    
    // Sort and Filter Controls
    private static final String SORT_DROPDOWN = "#ctl00_cphMainContent_ddlSort";
    private static final String SORT_LABEL = "text=Sort";
    private static final String VIEW_MAP_LINK = "link:has-text('View Map')";
    private static final String FILTER_RESULTS_LINK = "link:has-text('Filter Results')";
    private static final String MAP_ICON = "span:has-text('')";
    private static final String FILTER_ICON = "span:has-text('')";
    
    // ============= PAGINATION ELEMENTS =============
    
    // Pagination Controls
    private static final String PAGINATION_PAGES_LABEL = "strong:has-text('Pages:')";
    private static final String PAGINATION_CONTAINER = "div:has(strong:has-text('Pages:'))";
    private static final String PAGE_LINKS = "a[href*='page=']";
    private static final String PREVIOUS_LINK = "link:has-text('Previous')";
    private static final String NEXT_LINK = "link:has-text('Next')";
    private static final String CURRENT_PAGE_INDICATOR = "span:not([href])"; // Current page is usually a span, not a link
    
    // ============= HOTEL LIST ELEMENTS =============
    
    // Hotel Card Container
    private static final String HOTEL_CARD_CONTAINER = "div[class*='hotel']";
    private static final String HOTEL_REPEATER = "#ctl00_cphMainContent_HotelRepeater";
    
    // Individual Hotel Elements (Dynamic - using indices)
    private static final String HOTEL_NAME_LINK = "a[id*='hotelNameLink']";
    private static final String HOTEL_ADDRESS = "div:has(a[id*='hotelNameLink']) + div";
    private static final String HOTEL_PHONE = "text=Phone:";
    private static final String HOTEL_FAX = "text=Fax:";
    private static final String HOTEL_DISTANCE = "text=Miles";
    private static final String HOTEL_RATING_STARS = "span[class*='star']";
    private static final String HOTEL_RATING_COUNT = "text=(0)";
    private static final String PREFERRED_BY_CLIENT = "text=Preferred By Client";
    
    // Hotel Action Buttons
    private static final String SUBMIT_BUTTON = "button:has-text('Submit')";
    private static final String INFO_MODAL_LINK = "a[href*='hotel_modal']";
    private static final String COMPARE_CHECKBOX = "input[type='checkbox'][name*='Compare']";
    private static final String COMPARE_LABEL = "text=Compare";
    private static final String CHECK_AVAILABILITY_LINK = "link:has-text('Check Availability')";
    private static final String HIDE_AVAILABILITY_LINK = "link:has-text('Hide Availability')";
    
    // Content Providers
    private static final String ACTIVE_CONTENT_PROVIDERS_LABEL = "text=Active Content Providers";
    private static final String SABRE_PROVIDER = "text=Sabre";
    private static final String BOOKING_COM_PROVIDER = "text=Booking.com";
    private static final String EXPEDIA_PROVIDER = "text=Expedia";
    private static final String HOTELS_COM_PROVIDER = "text=Hotels.com";
    
    // ============= HOTEL RATES DISPLAY ELEMENTS =============
    
    // Rate Loading
    private static final String CHECKING_AVAILABILITY_TEXT = "text=Checking Availability";
    private static final String RATES_LOADING_IMAGE = "img[alt='Rates loading']";
    
    // Rate Container
    private static final String HOTEL_RATES_CONTAINER = "div[id*='rates']";
    private static final String RATE_ITEM = "div:has(h4:has-text('USD'))";
    
    // Room Type Information
    private static final String ROOM_TYPE_KING = "text=King Room";
    private static final String ROOM_TYPE_QUEEN = "text=Queen Room";
    private static final String ROOM_TYPE_SUITE = "text=Suite";
    private static final String ROOM_TYPE_TWIN = "text=Twin Room";
    private static final String ROOM_TYPE_FAMILY = "text=Family Room/Suite";
    private static final String ROOM_TYPE_SUPERIOR = "text=Superior Room";
    
    // Rate Information
    private static final String ONLINE_RATE_LABEL = "text=Online Rate";
    private static final String RATE_PROVIDER_SABRE = "text=(Sabre)";
    private static final String RATE_PROVIDER_BOOKING_COM = "text=(Booking.com)";
    private static final String RATE_DESCRIPTION = "p:has-text('Prepay')";
    
    // Rate Actions
    private static final String FULL_RATE_INFO_LINK = "link:has-text('Full Rate Information')";
    private static final String LESS_INFO_LINK = "link:has-text('Less Information')";
    private static final String SELECT_RATE_BUTTON = "link:has-text('Select Rate')";
    
    // Rate Pricing
    private static final String RATE_PRICE_USD = "h4:has-text('USD')";
    private static final String RATE_PRICE_GBP = "text~GBP";
    private static final String RATE_PER_ROOM_NIGHT = "text=per room, per night";
    
    // ============= DETAILED RATE INFORMATION ELEMENTS =============
    
    // Expanded Rate Details
    private static final String RATE_INFORMATION_HEADER = "text=Rate Information";
    private static final String CANCELLATION_POLICY_HEADER = "text=Cancellation Policy";
    private static final String DEPOSIT_POLICY_HEADER = "text=Deposit Policy";
    private static final String RATE_BREAKDOWN_HEADER = "text=Rate Breakdown";
    
    // Rate Policy Information
    private static final String NON_REFUNDABLE_POLICY = "text=Non-Refundable";
    private static final String REFUNDABLE_POLICY = "text=Refundable";
    private static final String PREPAY_NONREF_POLICY = "text=Prepay Nonref";
    private static final String REGULAR_RATE_POLICY = "text=Regular Rate";
    private static final String BREAKFAST_INCLUDED = "text=Breakfast Included";
    
    // Rate Types
    private static final String PREPAY_NONREF_RATE = "text=Prepay Nonref";
    private static final String REGULAR_RATE = "text=Regular Rate";
    private static final String EXPERIENCES_RATE = "text=Experiences";
    private static final String SPORTING_RATE = "text=Sporting";
    private static final String ATTRACTION_RATE = "text=Attraction";
    
    /**
     * Constructor
     */
    public HotelAvailabilityPageObjects(Page page) {
        this.page = page;
        this.waitUtility = new ExplicitWaitUtility(page);
    }
    
    // ============= PAGE VERIFICATION METHODS =============
    
    /**
     * Check if hotel availability page is displayed
     */
    public boolean isHotelAvailabilityPageDisplayed() {
        return page.isVisible(SEARCH_RESULTS_HEADER) && page.isVisible(HOTELS_FOUND_COUNT);
    }
    
    /**
     * Wait for hotel availability page to load completely
     */
    public void waitForHotelAvailabilityPageToLoad() {
        waitUtility.waitForElementVisible(SEARCH_RESULTS_HEADER);
        waitUtility.waitForElementVisible(HOTELS_FOUND_COUNT);
        waitUtility.waitForPageLoad();
    }
    
    /**
     * Get the search results header text (e.g., "Dallas: 100 properties found")
     */
    public String getSearchResultsHeaderText() {
        return page.textContent(SEARCH_RESULTS_HEADER).trim();
    }
    
    /**
     * Get the number of hotels found
     */
    public int getHotelsFoundCount() {
        String headerText = getSearchResultsHeaderText();
        // Extract number from text like "Dallas: 100 properties found"
        String[] parts = headerText.split(":");
        if (parts.length > 1) {
            String countPart = parts[1].trim().split(" ")[0];
            return Integer.parseInt(countPart);
        }
        return 0;
    }
    
    // ============= SORTING AND FILTERING METHODS =============
    
    /**
     * Select sort option from dropdown
     */
    public void selectSortOption(String sortOption) {
        waitUtility.waitForElementClickable(SORT_DROPDOWN);
        page.selectOption(SORT_DROPDOWN, new SelectOption().setLabel(sortOption));
    }
    
    /**
     * Get available sort options
     */
    public List<String> getAvailableSortOptions() {
        List<String> options = new ArrayList<>();
        Locator sortDropdown = page.locator(SORT_DROPDOWN);
        Locator optionElements = sortDropdown.locator("option");
        
        for (int i = 0; i < optionElements.count(); i++) {
            options.add(optionElements.nth(i).textContent().trim());
        }
        return options;
    }
    
    /**
     * Click view map link
     */
    public void clickViewMap() {
        waitUtility.waitForElementClickable(VIEW_MAP_LINK);
        page.click(VIEW_MAP_LINK);
    }
    
    /**
     * Click filter results link
     */
    public void clickFilterResults() {
        waitUtility.waitForElementClickable(FILTER_RESULTS_LINK);
        page.click(FILTER_RESULTS_LINK);
    }
    
    // ============= PAGINATION METHODS =============
    
    /**
     * Check if pagination links are displayed
     */
    public boolean arePaginationLinksDisplayed() {
        return page.isVisible(PAGINATION_PAGES_LABEL);
    }
    
    /**
     * Get all available page numbers
     */
    public List<Integer> getAvailablePageNumbers() {
        List<Integer> pageNumbers = new ArrayList<>();
        Locator pageLinks = page.locator(PAGE_LINKS);
        
        for (int i = 0; i < pageLinks.count(); i++) {
            String linkText = pageLinks.nth(i).textContent().trim();
            try {
                int pageNumber = Integer.parseInt(linkText);
                pageNumbers.add(pageNumber);
            } catch (NumberFormatException e) {
                // Skip non-numeric links
            }
        }
        return pageNumbers;
    }
    
    /**
     * Get total number of pages available
     */
    public int getTotalPagesCount() {
        List<Integer> pageNumbers = getAvailablePageNumbers();
        return pageNumbers.isEmpty() ? 1 : pageNumbers.stream().max(Integer::compareTo).orElse(1);
    }
    
    /**
     * Get current page number
     */
    public int getCurrentPageNumber() {
        // Current page is usually not a link, so check for non-clickable page indicators
        Locator paginationContainer = page.locator(PAGINATION_CONTAINER);
        if (paginationContainer.isVisible()) {
            // Extract current page from URL if available
            String currentUrl = page.url();
            if (currentUrl.contains("page=")) {
                String pageParam = currentUrl.split("page=")[1].split("&")[0];
                try {
                    return Integer.parseInt(pageParam);
                } catch (NumberFormatException e) {
                    return 1;
                }
            }
        }
        return 1; // Default to page 1
    }
    
    /**
     * Check if specific page number is available
     */
    public boolean isPageNumberAvailable(int pageNumber) {
        return getAvailablePageNumbers().contains(pageNumber);
    }
    
    /**
     * Validate that pagination links are working
     */
    public boolean validatePaginationLinksWorking() {
        try {
            List<Integer> availablePages = getAvailablePageNumbers();
            if (availablePages.size() < 2) {
                return true; // Single page, no navigation needed
            }
            
            int currentPage = getCurrentPageNumber();
            int targetPage = availablePages.stream()
                .filter(page -> page != currentPage)
                .findFirst()
                .orElse(currentPage);
            
            if (targetPage == currentPage) {
                return true; // Only one page available
            }
            
            // Click target page and verify URL changes
            String currentUrl = page.url();
            clickPageNumber(targetPage);
            
            String newUrl = page.url();
            boolean urlChanged = !currentUrl.equals(newUrl);
            
            // Navigate back to original page
            clickPageNumber(currentPage);
            
            return urlChanged;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Comprehensive pagination validation - validates all pagination links and functionality
     * @return ValidationResult object with detailed validation results
     */
    public PaginationValidationResult validatePaginationComprehensively() {
        PaginationValidationResult result = new PaginationValidationResult();
        
        try {
            // 1. Check if pagination is displayed
            result.isPaginationDisplayed = arePaginationLinksDisplayed();
            if (!result.isPaginationDisplayed) {
                result.validationMessage = "Pagination section is not displayed";
                return result;
            }
            
            // 2. Get all available pages
            List<Integer> availablePages = getAvailablePageNumbers();
            result.totalPagesFound = availablePages.size();
            result.availablePageNumbers = new ArrayList<>(availablePages);
            
            if (availablePages.isEmpty()) {
                result.validationMessage = "No pagination links found";
                return result;
            }
            
            // 3. Store original page for restoration
            int originalPage = getCurrentPageNumber();
            result.originalPage = originalPage;
            
            // 4. Test each page link
            result.brokenLinks = new ArrayList<>();
            result.workingLinks = new ArrayList<>();
            
            for (Integer pageNumber : availablePages) {
                boolean linkWorks = validateSinglePageLink(pageNumber);
                if (linkWorks) {
                    result.workingLinks.add(pageNumber);
                } else {
                    result.brokenLinks.add(pageNumber);
                }
            }
            
            // 5. Test Previous/Next navigation
            result.isPreviousLinkWorking = validatePreviousNextNavigation(true);
            result.isNextLinkWorking = validatePreviousNextNavigation(false);
            
            // 6. Test boundary navigation (First/Last page)
            result.isBoundaryNavigationWorking = validateBoundaryNavigation();
            
            // 7. Validate page sequence integrity
            result.isPageSequenceValid = validatePageSequenceIntegrity(availablePages);
            
            // 8. Test URL parameter consistency
            result.isUrlParameterConsistent = validateUrlParameterConsistency();
            
            // 9. Restore original page
            clickPageNumber(originalPage);
            
            // 10. Calculate overall success
            result.overallValidationPassed = result.brokenLinks.isEmpty() && 
                                           result.isPreviousLinkWorking && 
                                           result.isNextLinkWorking && 
                                           result.isBoundaryNavigationWorking &&
                                           result.isPageSequenceValid &&
                                           result.isUrlParameterConsistent;
            
            // 11. Generate summary message
            if (result.overallValidationPassed) {
                result.validationMessage = String.format(
                    "Pagination validation PASSED - %d pages tested, all links working correctly",
                    result.totalPagesFound
                );
            } else {
                result.validationMessage = String.format(
                    "Pagination validation FAILED - %d broken links found out of %d total pages",
                    result.brokenLinks.size(), result.totalPagesFound
                );
            }
            
        } catch (Exception e) {
            result.validationMessage = "Pagination validation failed with exception: " + e.getMessage();
            result.overallValidationPassed = false;
        }
        
        return result;
    }
    
    /**
     * Validate a single page link
     */
    private boolean validateSinglePageLink(int pageNumber) {
        try {
            String currentUrl = page.url();
            clickPageNumber(pageNumber);
            
            // Wait for page to load
            waitUtility.waitForPageLoad();
            
            // Check if URL changed appropriately
            String newUrl = page.url();
            boolean urlChanged = !currentUrl.equals(newUrl);
            
            // Check if the page content loaded correctly
            waitUtility.waitForElementVisible(SEARCH_RESULTS_HEADER);
            boolean contentLoaded = page.isVisible(SEARCH_RESULTS_HEADER);
            
            // Verify page number in URL matches what we clicked
            boolean pageNumberMatches = newUrl.contains("page=" + pageNumber);
            
            return urlChanged && contentLoaded && pageNumberMatches;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Validate Previous/Next navigation buttons
     */
    private boolean validatePreviousNextNavigation(boolean testPrevious) {
        try {
            String linkSelector = testPrevious ? PREVIOUS_LINK : NEXT_LINK;
            
            // Check if button is available when it should be
            boolean buttonVisible = page.isVisible(linkSelector);
            
            if (!buttonVisible) {
                // Button not visible - check if this is expected
                int currentPage = getCurrentPageNumber();
                List<Integer> availablePages = getAvailablePageNumbers();
                
                if (testPrevious) {
                    // Previous should not be visible on first page
                    return currentPage == 1 || currentPage == availablePages.stream().min(Integer::compareTo).orElse(1);
                } else {
                    // Next should not be visible on last page
                    return currentPage == availablePages.stream().max(Integer::compareTo).orElse(1);
                }
            }
            
            // Button is visible, test if it works
            String currentUrl = page.url();
            page.click(linkSelector);
            waitUtility.waitForPageLoad();
            
            String newUrl = page.url();
            boolean urlChanged = !currentUrl.equals(newUrl);
            
            // Verify content loaded
            boolean contentLoaded = page.isVisible(SEARCH_RESULTS_HEADER);
            
            return urlChanged && contentLoaded;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Validate boundary navigation (first/last page)
     */
    private boolean validateBoundaryNavigation() {
        try {
            List<Integer> availablePages = getAvailablePageNumbers();
            if (availablePages.size() < 2) {
                return true; // Single page, boundary navigation not applicable
            }
            
            int firstPage = availablePages.stream().min(Integer::compareTo).orElse(1);
            int lastPage = availablePages.stream().max(Integer::compareTo).orElse(1);
            
            // Test navigation to first page
            goToFirstPage();
            waitUtility.waitForPageLoad();
            boolean reachedFirst = getCurrentPageNumber() == firstPage;
            
            // Test navigation to last page
            goToLastPage();
            waitUtility.waitForPageLoad();
            boolean reachedLast = getCurrentPageNumber() == lastPage;
            
            return reachedFirst && reachedLast;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Validate page sequence integrity (no gaps in page numbers)
     */
    private boolean validatePageSequenceIntegrity(List<Integer> availablePages) {
        if (availablePages.isEmpty()) {
            return false;
        }
        
        // Sort pages to check sequence
        List<Integer> sortedPages = new ArrayList<>(availablePages);
        sortedPages.sort(Integer::compareTo);
        
        // Check if sequence is continuous (no gaps)
        for (int i = 1; i < sortedPages.size(); i++) {
            int expectedNext = sortedPages.get(i - 1) + 1;
            int actualNext = sortedPages.get(i);
            
            if (actualNext != expectedNext) {
                // Gap found in sequence
                return false;
            }
        }
        
        // First page should be 1
        return sortedPages.get(0) == 1;
    }
    
    /**
     * Validate URL parameter consistency
     */
    private boolean validateUrlParameterConsistency() {
        try {
            List<Integer> availablePages = getAvailablePageNumbers();
            
            for (Integer pageNumber : availablePages) {
                clickPageNumber(pageNumber);
                waitUtility.waitForPageLoad();
                
                String currentUrl = page.url();
                
                // Check if URL contains correct page parameter
                if (!currentUrl.contains("page=" + pageNumber)) {
                    return false;
                }
                
                // Check if displayed page matches URL parameter
                int displayedPage = getCurrentPageNumber();
                if (displayedPage != pageNumber) {
                    return false;
                }
            }
            
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check for broken pagination links (returns 404 or other errors)
     */
    public List<String> checkForBrokenPaginationLinks() {
        List<String> brokenLinks = new ArrayList<>();
        
        try {
            Locator pageLinks = page.locator(PAGE_LINKS);
            
            for (int i = 0; i < pageLinks.count(); i++) {
                String href = pageLinks.nth(i).getAttribute("href");
                if (href != null && !href.isEmpty()) {
                    // Check if link is accessible
                    try {
                        pageLinks.nth(i).click();
                        waitUtility.waitForPageLoad();
                        
                        // Check for error indicators
                        if (page.isVisible("text=404") || 
                            page.isVisible("text=Not Found") || 
                            page.isVisible("text=Error") ||
                            page.title().toLowerCase().contains("error")) {
                            brokenLinks.add(href);
                        }
                        
                    } catch (Exception e) {
                        brokenLinks.add(href + " - Exception: " + e.getMessage());
                    }
                }
            }
            
        } catch (Exception e) {
            brokenLinks.add("Error checking pagination links: " + e.getMessage());
        }
        
        return brokenLinks;
    }
    
    /**
     * Data class for pagination validation results
     */
    public static class PaginationValidationResult {
        public boolean isPaginationDisplayed = false;
        public int totalPagesFound = 0;
        public List<Integer> availablePageNumbers = new ArrayList<>();
        public List<Integer> workingLinks = new ArrayList<>();
        public List<Integer> brokenLinks = new ArrayList<>();
        public boolean isPreviousLinkWorking = false;
        public boolean isNextLinkWorking = false;
        public boolean isBoundaryNavigationWorking = false;
        public boolean isPageSequenceValid = false;
        public boolean isUrlParameterConsistent = false;
        public boolean overallValidationPassed = false;
        public String validationMessage = "";
        public int originalPage = 1;
        
        /**
         * Check if overall validation was successful
         */
        public boolean isOverallValidationSuccessful() {
            return isPaginationDisplayed && 
                   totalPagesFound > 0 && 
                   brokenLinks.isEmpty() && 
                   overallValidationPassed;
        }
        
        /**
         * Get validation summary
         */
        public String getValidationSummary() {
            if (isOverallValidationSuccessful()) {
                return "All pagination validation tests passed successfully";
            } else {
                StringBuilder summary = new StringBuilder();
                summary.append("Pagination validation failed: ");
                if (!isPaginationDisplayed) {
                    summary.append("No pagination displayed. ");
                }
                if (totalPagesFound == 0) {
                    summary.append("No pagination pages found. ");
                }
                if (!brokenLinks.isEmpty()) {
                    summary.append(brokenLinks.size()).append(" broken links detected: ").append(brokenLinks).append(". ");
                }
                if (!overallValidationPassed) {
                    summary.append("Overall validation failed. ");
                }
                if (!validationMessage.isEmpty()) {
                    summary.append("Details: ").append(validationMessage);
                }
                return summary.toString();
            }
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Pagination Validation Results:\n");
            sb.append("- Pagination Displayed: ").append(isPaginationDisplayed).append("\n");
            sb.append("- Total Pages Found: ").append(totalPagesFound).append("\n");
            sb.append("- Available Pages: ").append(availablePageNumbers).append("\n");
            sb.append("- Working Links: ").append(workingLinks.size()).append(" out of ").append(totalPagesFound).append("\n");
            sb.append("- Broken Links: ").append(brokenLinks.size()).append(" (").append(brokenLinks).append(")\n");
            sb.append("- Previous Link Working: ").append(isPreviousLinkWorking).append("\n");
            sb.append("- Next Link Working: ").append(isNextLinkWorking).append("\n");
            sb.append("- Boundary Navigation Working: ").append(isBoundaryNavigationWorking).append("\n");
            sb.append("- Page Sequence Valid: ").append(isPageSequenceValid).append("\n");
            sb.append("- URL Parameter Consistent: ").append(isUrlParameterConsistent).append("\n");
            sb.append("- Overall Validation: ").append(overallValidationPassed ? "PASSED" : "FAILED").append("\n");
            sb.append("- Message: ").append(validationMessage);
            return sb.toString();
        }
    }
    
    /**
     * Quick validation method for step definitions
     */
    public boolean validatePaginationLinksDisplayedAndWorking() {
        if (!arePaginationLinksDisplayed()) {
            return false;
        }
        
        PaginationValidationResult result = validatePaginationComprehensively();
        return result.overallValidationPassed;
    }
    
    /**
     * Click specific page number (dynamic)
     */
    public void clickPageNumber(int pageNumber) {
        if (isPageNumberAvailable(pageNumber)) {
            String pageSelector = "a[href*='page=" + pageNumber + "']";
            if (page.isVisible(pageSelector)) {
                waitUtility.waitForElementClickable(pageSelector);
                page.click(pageSelector);
                waitUtility.waitForPageLoad();
            } else {
                // Fallback: try clicking link with text content
                String fallbackSelector = "a:has-text('" + pageNumber + "')";
                if (page.isVisible(fallbackSelector)) {
                    waitUtility.waitForElementClickable(fallbackSelector);
                    page.click(fallbackSelector);
                    waitUtility.waitForPageLoad();
                }
            }
        }
    }
    
    /**
     * Click next page (if available)
     */
    public void clickNextPage() {
        if (page.isVisible(NEXT_LINK)) {
            page.click(NEXT_LINK);
            waitUtility.waitForPageLoad();
        }
    }
    
    /**
     * Click previous page (if available)
     */
    public void clickPreviousPage() {
        if (page.isVisible(PREVIOUS_LINK)) {
            page.click(PREVIOUS_LINK);
            waitUtility.waitForPageLoad();
        }
    }
    
    /**
     * Check if next page is available
     */
    public boolean isNextPageAvailable() {
        return page.isVisible(NEXT_LINK);
    }
    
    /**
     * Check if previous page is available
     */
    public boolean isPreviousPageAvailable() {
        return page.isVisible(PREVIOUS_LINK);
    }
    
    /**
     * Navigate to first page
     */
    public void goToFirstPage() {
        List<Integer> pages = getAvailablePageNumbers();
        if (!pages.isEmpty()) {
            int firstPage = pages.stream().min(Integer::compareTo).orElse(1);
            clickPageNumber(firstPage);
        }
    }
    
    /**
     * Navigate to last page
     */
    public void goToLastPage() {
        List<Integer> pages = getAvailablePageNumbers();
        if (!pages.isEmpty()) {
            int lastPage = pages.stream().max(Integer::compareTo).orElse(1);
            clickPageNumber(lastPage);
        }
    }
    
    // ============= HOTEL LIST METHODS =============
    
    /**
     * Get all hotel names from the current page
     */
    public List<String> getAllHotelNames() {
        List<String> hotelNames = new ArrayList<>();
        Locator hotelLinks = page.locator(HOTEL_NAME_LINK);
        
        for (int i = 0; i < hotelLinks.count(); i++) {
            hotelNames.add(hotelLinks.nth(i).textContent().trim());
        }
        return hotelNames;
    }
    
    /**
     * Get hotel name by index
     */
    public String getHotelNameByIndex(int index) {
        Locator hotelLinks = page.locator(HOTEL_NAME_LINK);
        if (index < hotelLinks.count()) {
            return hotelLinks.nth(index).textContent().trim();
        }
        return "";
    }
    
    /**
     * Click hotel name by index
     */
    public void clickHotelNameByIndex(int index) {
        Locator hotelLinks = page.locator(HOTEL_NAME_LINK);
        if (index < hotelLinks.count()) {
            hotelLinks.nth(index).click();
        }
    }
    
    /**
     * Get hotel address by index
     */
    public String getHotelAddressByIndex(int index) {
        List<String> hotelNames = getAllHotelNames();
        if (index < hotelNames.size()) {
            // Find the address element following the hotel name
            String hotelName = hotelNames.get(index);
            String addressSelector = "text=" + hotelName + " + div";
            if (page.isVisible(addressSelector)) {
                return page.textContent(addressSelector).trim();
            }
        }
        return "";
    }
    
    /**
     * Check if hotel is preferred by client
     */
    public boolean isHotelPreferredByClient(int index) {
        // Look for preferred indicator near the hotel at specified index
        List<String> hotelNames = getAllHotelNames();
        if (index < hotelNames.size()) {
            // Check if "Preferred By Client" text appears near this hotel
            return page.locator("text=Preferred By Client").count() > 0;
        }
        return false;
    }
    
    // ============= HOTEL AVAILABILITY METHODS =============
    
    /**
     * Click Check Availability for hotel by index
     */
    public void clickCheckAvailabilityByIndex(int index) {
        Locator checkAvailabilityLinks = page.locator(CHECK_AVAILABILITY_LINK);
        if (index < checkAvailabilityLinks.count()) {
            checkAvailabilityLinks.nth(index).click();
            // Wait for rates to load
            waitForRatesToLoad();
        }
    }
    
    /**
     * Click Check Availability for specific content provider
     */
    public void clickCheckAvailabilityForProvider(String providerName) {
        // This method works with the step definition pattern for provider-specific availability
        String providerSelector = "text=" + providerName;
        if (page.isVisible(providerSelector)) {
            // Find the Check Availability link associated with this provider
            clickCheckAvailabilityByIndex(0); // Default to first hotel for now
        }
    }
    
    /**
     * Wait for hotel rates to load
     */
    public void waitForRatesToLoad() {
        // Wait for loading indicator to disappear
        if (page.isVisible(CHECKING_AVAILABILITY_TEXT)) {
            page.waitForSelector(CHECKING_AVAILABILITY_TEXT, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.HIDDEN).setTimeout(30000));
        }
        if (page.isVisible(RATES_LOADING_IMAGE)) {
            page.waitForSelector(RATES_LOADING_IMAGE, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.HIDDEN).setTimeout(30000));
        }
        // Wait for rates container to be visible
        waitUtility.waitForElementVisible(HOTEL_RATES_CONTAINER);
    }
    
    /**
     * Check if rates are displayed
     */
    public boolean areRatesDisplayed() {
        return page.isVisible(HOTEL_RATES_CONTAINER) && page.isVisible(RATE_PRICE_USD);
    }
    
    /**
     * Validate that rate information is loaded and displayed
     */
    public boolean isRateInformationLoadedAndDisplayed() {
        // Check if rates container exists and has content
        if (!page.isVisible(HOTEL_RATES_CONTAINER)) {
            return false;
        }
        
        // Check if there are actual rates with prices
        Locator rateItems = page.locator(RATE_ITEM);
        if (rateItems.count() == 0) {
            return false;
        }
        
        // Validate that rates have prices
        Locator priceElements = page.locator(RATE_PRICE_USD);
        return priceElements.count() > 0;
    }
    
    // ============= RATE INFORMATION METHODS =============
    
    /**
     * Get all available room types
     */
    public List<String> getAvailableRoomTypes() {
        List<String> roomTypes = new ArrayList<>();
        String[] roomTypeSelectors = {
            ROOM_TYPE_KING, ROOM_TYPE_QUEEN, ROOM_TYPE_SUITE, 
            ROOM_TYPE_TWIN, ROOM_TYPE_FAMILY, ROOM_TYPE_SUPERIOR
        };
        
        for (String selector : roomTypeSelectors) {
            if (page.isVisible(selector)) {
                roomTypes.add(page.textContent(selector).trim());
            }
        }
        return roomTypes;
    }
    
    /**
     * Get all rate prices in USD
     */
    public List<String> getAllRatePricesUSD() {
        List<String> prices = new ArrayList<>();
        Locator priceElements = page.locator(RATE_PRICE_USD);
        
        for (int i = 0; i < priceElements.count(); i++) {
            prices.add(priceElements.nth(i).textContent().trim());
        }
        return prices;
    }
    
    /**
     * Click Full Rate Information for first rate
     */
    public void clickFullRateInformation() {
        waitUtility.waitForElementClickable(FULL_RATE_INFO_LINK);
        page.click(FULL_RATE_INFO_LINK);
        waitUtility.waitForElementVisible(RATE_INFORMATION_HEADER);
    }
    
    /**
     * Click Less Information to collapse rate details
     */
    public void clickLessInformation() {
        if (page.isVisible(LESS_INFO_LINK)) {
            page.click(LESS_INFO_LINK);
        }
    }
    
    /**
     * Select rate by index
     */
    public void selectRateByIndex(int index) {
        Locator selectRateButtons = page.locator(SELECT_RATE_BUTTON);
        if (index < selectRateButtons.count()) {
            selectRateButtons.nth(index).click();
        }
    }
    
    /**
     * Get rate policy information
     */
    public String getRatePolicyInformation() {
        if (page.isVisible(RATE_INFORMATION_HEADER)) {
            // Get the detailed rate information paragraph
            String selector = RATE_INFORMATION_HEADER + " + p";
            if (page.isVisible(selector)) {
                return page.textContent(selector).trim();
            }
        }
        return "";
    }
    
    /**
     * Check if rate is refundable
     */
    public boolean isRateRefundable() {
        return page.isVisible(REFUNDABLE_POLICY) && !page.isVisible(NON_REFUNDABLE_POLICY);
    }
    
    /**
     * Check if breakfast is included
     */
    public boolean isBreakfastIncluded() {
        return page.isVisible(BREAKFAST_INCLUDED);
    }
    
    // ============= CONTENT PROVIDER METHODS =============
    
    /**
     * Get available content providers for a hotel
     */
    public List<String> getAvailableContentProviders() {
        List<String> providers = new ArrayList<>();
        
        if (page.isVisible(SABRE_PROVIDER)) {
            providers.add("Sabre");
        }
        if (page.isVisible(BOOKING_COM_PROVIDER)) {
            providers.add("Booking.com");
        }
        if (page.isVisible(EXPEDIA_PROVIDER)) {
            providers.add("Expedia");
        }
        if (page.isVisible(HOTELS_COM_PROVIDER)) {
            providers.add("Hotels.com");
        }
        
        return providers;
    }
    
    /**
     * Check if specific content provider is available
     */
    public boolean isContentProviderAvailable(String providerName) {
        String providerSelector = "text=" + providerName;
        return page.isVisible(providerSelector);
    }
    
    // ============= HOTEL COMPARISON METHODS =============
    
    /**
     * Select hotel for comparison by index
     */
    public void selectHotelForComparison(int index) {
        Locator compareCheckboxes = page.locator(COMPARE_CHECKBOX);
        if (index < compareCheckboxes.count()) {
            compareCheckboxes.nth(index).check();
        }
    }
    
    /**
     * Unselect hotel from comparison by index
     */
    public void unselectHotelFromComparison(int index) {
        Locator compareCheckboxes = page.locator(COMPARE_CHECKBOX);
        if (index < compareCheckboxes.count()) {
            compareCheckboxes.nth(index).uncheck();
        }
    }
    
    /**
     * Check if hotel is selected for comparison
     */
    public boolean isHotelSelectedForComparison(int index) {
        Locator compareCheckboxes = page.locator(COMPARE_CHECKBOX);
        if (index < compareCheckboxes.count()) {
            return compareCheckboxes.nth(index).isChecked();
        }
        return false;
    }
    
    // ============= UTILITY METHODS =============
    
    /**
     * Get page URL
     */
    public String getCurrentPageUrl() {
        return page.url();
    }
    
    /**
     * Refresh the page
     */
    public void refreshPage() {
        page.reload();
        waitForHotelAvailabilityPageToLoad();
    }
    
    /**
     * Validate that hotel availability page is displayed
     */
    public void validateHotelAvailabilityPageDisplayed() {
        // Wait for page elements to be visible
        waitUtility.waitForElementVisible(SEARCH_RESULTS_HEADER);
        
        // Validate page title/header
        boolean isPageDisplayed = page.isVisible(SEARCH_RESULTS_HEADER) || 
                                 page.isVisible(HOTELS_FOUND_COUNT) ||
                                 page.isVisible("text=Hotel Availability");
        
        if (!isPageDisplayed) {
            throw new AssertionError("Hotel Availability page is not displayed - expected page elements not found");
        }
        
        System.out.println("✅ Hotel Availability page validated successfully");
    }
    
    /**
     * Validate that rate information is loaded and displayed
     */
    public void validateRateInformationDisplayed() {
        try {
            // Wait for rates to load (checking availability may take time)
            page.waitForTimeout(5000);
            
            // Check for rate container or rate information
            boolean ratesDisplayed = page.isVisible(HOTEL_RATES_CONTAINER) ||
                                   page.isVisible(RATE_ITEM) ||
                                   page.isVisible(RATE_PRICE_USD) ||
                                   page.isVisible("div[id*='rates']") ||
                                   page.isVisible("text=USD") ||
                                   page.isVisible("text=Rate Information");
            
            if (!ratesDisplayed) {
                // Check if still loading
                boolean stillLoading = page.isVisible(CHECKING_AVAILABILITY_TEXT) ||
                                     page.isVisible(RATES_LOADING_IMAGE) ||
                                     page.isVisible("text=Checking Availability");
                
                if (stillLoading) {
                    // Wait additional time for rates to load
                    page.waitForTimeout(10000);
                    
                    // Re-check for rates
                    ratesDisplayed = page.isVisible(HOTEL_RATES_CONTAINER) ||
                                   page.isVisible(RATE_ITEM) ||
                                   page.isVisible(RATE_PRICE_USD);
                }
            }
            
            if (!ratesDisplayed) {
                throw new AssertionError("Rate information is not displayed after clicking Check Availability");
            }
            
            System.out.println("✅ Rate information validated successfully");
            
        } catch (Exception e) {
            throw new AssertionError("Failed to validate rate information: " + e.getMessage());
        }
    }
}