package com.sabre.hotelbooker.configreaderutils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.sabre.hotelbooker.hotelbookerutility.ExplicitWaitUtility;

import java.time.LocalDate;

/**
 * DashboardPageObjects - Complete Page Object for HotelBooker Main Dashboard
 * Contains ALL elements captured from the live dashboard page
 */
@SuppressWarnings("unused") // Suppressing warnings for comprehensive locator library
public class DashboardPageObjects {
    
    private final Page page;
    private final ExplicitWaitUtility waitUtility;
    
    // ============= HEADER NAVIGATION ELEMENTS =============
    
    // Brand and Logo
    private static final String BRAND_LINK = "link:has-text('Brand')";
    private static final String BRAND_IMAGE = "img[alt='Brand']";
    
    // Navigation Menu
    private static final String NAV_NUMBER_5 = "link:has-text('5')";
    private static final String NAV_CLIENT_LINK = "link:has-text('Test QA Client(Sabre)')";
    private static final String TOOLS_BUTTON = "button:has-text('Tools')";
    private static final String WELCOME_USER_BUTTON = "button:has-text('Welcome , Sabre AU')";
    
    // ============= MAIN CONTENT TABS =============
    
    // Booking Tabs
    private static final String MAKE_BOOKING_TAB = "link:has-text('Make a booking')";
    private static final String VIEW_BOOKINGS_TAB = "link:has-text('View bookings')";
    
    // ============= HOTEL SEARCH FORM ELEMENTS =============
    
    // Date and Stay Details
    private static final String ARRIVAL_DATE_LABEL = "text=Arrival Date";
    private static final String ARRIVAL_DATE_INPUT = "textbox[aria-label='Arrival Date']";
    private static final String NIGHTS_LABEL = "text=Nights";
    private static final String NIGHTS_INPUT = "textbox[aria-label='Nights']";
    private static final String ROOMS_LABEL = "text=Rooms";
    private static final String ROOMS_DROPDOWN = "combobox[aria-label='Rooms']";
    private static final String GUESTS_LABEL = "text=Guests";
    private static final String GUESTS_DROPDOWN = "combobox[aria-label='Guests']";
    
    // Location Details
    private static final String COUNTRY_LABEL = "text=Country";
    private static final String COUNTRY_DROPDOWN = "combobox[aria-label='Country']";
    private static final String LOCATION_LABEL = "text=Location";
    private static final String LOCATION_INPUT = "textbox[aria-label='Location']";
    private static final String HOTEL_NAME_LABEL = "text=Hotel Name";
    private static final String HOTEL_NAME_INPUT = "textbox[aria-label='Hotel Name']";
    private static final String DISTANCE_LABEL = "text=Distance";
    private static final String DISTANCE_DROPDOWN = "combobox[aria-label='Distance']";
    
    // Search Controls
    private static final String ADVANCED_OPTIONS_LINK = "link:has-text('Advanced Options')";
    private static final String SEARCH_BUTTON = "button:has-text('Search')";
    
    // ============= BOOKING DETAILS SECTION =============
    
    // Booking Details Panel
    private static final String BOOKING_DETAILS_HEADER = "text=Booking Details";
    private static final String BOOKING_DETAILS_EXPAND_BUTTON = "link[href*='expandBookingDetails']";
    
    // Booking Management Links
    private static final String ADD_CONTACT_LINK = "link:has-text('Add Contact')";
    private static final String ADD_TRAVELLERS_LINK = "link:has-text('Add Travellers')";
    private static final String RETRIEVE_PNR_LINK = "link:has-text('Retrieve PNR')";
    
    // ============= OPEN BOOKINGS SECTION =============
    
    // Open Bookings Header
    private static final String OPEN_BOOKINGS_HEADER = "heading:has-text('Open Bookings')";
    private static final String MY_RECENT_BOOKINGS = "#ctl00_cphMainContent_lnkMyRecentBookings";
    private static final String TODAYS_BOOKINGS = "#ctl00_cphMainContent_lnkTodaysBookings";
    private static final String OPEN_BOOKINGS_BUTTON = "button:has-text('Open Bookings')";
    
    // Booking Links - Dynamic booking references
    private static final String BOOKING_283628_LINK = "link[href*='283628']";
    private static final String BOOKING_283625_LINK = "link[href*='283625']";
    private static final String BOOKING_283628_HOTEL = "text=BOOKING 283628";
    private static final String BOOKING_283625_HOTEL = "text=BOOKING 283625";
    
    // Sorting Options
    private static final String SORT_BY_ARRIVAL_DATE = "button:has-text('By Arrival Date')";
    private static final String SORT_BY_OLDEST = "button:has-text('Oldest')";

    // ============= FOOTER ELEMENTS =============
    
    // Footer Links and Text
    private static final String FOOTER_COPYRIGHT = "text=Sabre 2025 © Copyright. All rights reserved | 5.15 |";
    private static final String TERMS_CONDITIONS_LINK = "link:has-text('Terms & Conditions')";
    private static final String PRIVACY_LINK = "link:has-text('Privacy')";
    private static final String POWERED_BY_SABRE_IMAGE = "img[alt='Powered By Sabre']";
    
    public DashboardPageObjects(Page page) {
        this.page = page;
        this.waitUtility = new ExplicitWaitUtility(page);
    }
    
    // ============= DASHBOARD INITIALIZATION =============
    
    /**
     * Wait for dashboard to load completely
     */
    public void waitForDashboardToLoad() {
        waitUtility.waitForPageStability();
        waitUtility.waitForElementVisible(SEARCH_BUTTON);
        waitUtility.waitForElementVisible(COUNTRY_DROPDOWN);
        waitUtility.waitForElementVisible(MAKE_BOOKING_TAB);
    }
    
    // ============= HEADER NAVIGATION METHODS =============
    
    /**
     * Click brand/logo to go to home
     */
    public void clickBrandLogo() {
        waitUtility.waitForElementClickable(BRAND_LINK);
        page.click(BRAND_LINK);
    }
    
    /**
     * Click navigation number 5
     */
    public void clickNavNumber5() {
        waitUtility.waitForElementClickable(NAV_NUMBER_5);
        page.click(NAV_NUMBER_5);
    }
    
    /**
     * Click client name in navigation
     */
    public void clickClientNavigation() {
        waitUtility.waitForElementClickable(NAV_CLIENT_LINK);
        page.click(NAV_CLIENT_LINK);
    }
    
    /**
     * Click Tools button
     */
    public void clickToolsButton() {
        waitUtility.waitForElementClickable(TOOLS_BUTTON);
        page.click(TOOLS_BUTTON);
    }
    
    /**
     * Click Welcome user button
     */
    public void clickWelcomeUserButton() {
        waitUtility.waitForElementClickable(WELCOME_USER_BUTTON);
        page.click(WELCOME_USER_BUTTON);
    }
    
    // ============= MAIN TABS METHODS =============
    
    /**
     * Switch to Make a booking tab
     */
    public void clickMakeBookingTab() {
        waitUtility.waitForElementClickable(MAKE_BOOKING_TAB);
        page.click(MAKE_BOOKING_TAB);
    }
    
    /**
     * Switch to View bookings tab
     */
    public void clickViewBookingsTab() {
        waitUtility.waitForElementClickable(VIEW_BOOKINGS_TAB);
        page.click(VIEW_BOOKINGS_TAB);
    }
    
    // ============= HOTEL SEARCH FORM METHODS =============
    
    /**
     * Set arrival date with string input (for backward compatibility)
     */
    public void setArrivalDate(String date) {
        waitUtility.waitForElementClickable(ARRIVAL_DATE_INPUT);
        page.fill(ARRIVAL_DATE_INPUT, date);
    }
    
    /**
     * Select arrival date using calendar picker with days from today
     * @param daysFromToday number of days from today to select
     */
    public void selectArrivalDateInCalendar(int daysFromToday) {
        LocalDate targetDate = LocalDate.now().plusDays(daysFromToday);
        int targetDay = targetDate.getDayOfMonth();
        int targetMonth = targetDate.getMonthValue();
        int targetYear = targetDate.getYear();
        
        // Click arrival date field to open calendar
        page.click(ARRIVAL_DATE_INPUT);
        
        String monthLabelSelector = ".datepicker-days .datepicker-switch";
        String nextMonthButtonSelector = ".datepicker-days th.next";
        
        page.waitForSelector(monthLabelSelector);
        
        // Navigate to target month/year
        while (true) {
            String monthYearText = page.textContent(monthLabelSelector).trim();
            java.time.format.DateTimeFormatter calFmt = java.time.format.DateTimeFormatter.ofPattern("MMMM yyyy");
            java.time.YearMonth displayedMonthYear = java.time.YearMonth.parse(monthYearText, calFmt);
            
            if (displayedMonthYear.getMonthValue() == targetMonth && displayedMonthYear.getYear() == targetYear) {
                break;
            }
            page.click(nextMonthButtonSelector);
            page.waitForTimeout(200);
        }
        
        // Click the target day
        String dayCellSelector = String.format(".datepicker-days td.day:not(.old):not(.new):text-is('%d')", targetDay);
        page.click(dayCellSelector);
    }
    
    /**
     * Set number of nights
     */
    public void setNights(String nights) {
        waitUtility.waitForElementClickable(NIGHTS_INPUT);
        page.fill(NIGHTS_INPUT, nights);
    }
    
    /**
     * Select number of rooms - Generic method for any room count
     */
    public void selectRooms(String rooms) {
        waitUtility.waitForElementClickable(ROOMS_DROPDOWN);
        page.selectOption(ROOMS_DROPDOWN, new SelectOption().setLabel(rooms));
    }
    
    /**
     * Select number of guests - Generic method for any guest count
     */
    public void selectGuests(String guests) {
        waitUtility.waitForElementClickable(GUESTS_DROPDOWN);
        page.selectOption(GUESTS_DROPDOWN, new SelectOption().setLabel(guests));
    }
    
    /**
     * Select country - Generic method for any country
     */
    public void selectCountry(String country) {
        waitUtility.waitForElementClickable(COUNTRY_DROPDOWN);
        page.selectOption(COUNTRY_DROPDOWN, new SelectOption().setLabel(country));
        waitUtility.waitForAjaxCallsToComplete();
    }
    
    /**
     * Select country by value (alternative method)
     */
    public void selectCountryByValue(String countryValue) {
        waitUtility.waitForElementClickable(COUNTRY_DROPDOWN);
        page.selectOption(COUNTRY_DROPDOWN, new SelectOption().setValue(countryValue));
        waitUtility.waitForAjaxCallsToComplete();
    }
    
    /**
     * Enter location
     */
    public void enterLocation(String location) {
        waitUtility.waitForElementClickable(LOCATION_INPUT);
        page.fill(LOCATION_INPUT, location);
    }
    
    /**
     * Enter hotel name
     */
    public void enterHotelName(String hotelName) {
        waitUtility.waitForElementClickable(HOTEL_NAME_INPUT);
        page.fill(HOTEL_NAME_INPUT, hotelName);
    }
    
    /**
     * Select distance - Generic method for any distance
     */
    public void selectDistance(String distance) {
        waitUtility.waitForElementClickable(DISTANCE_DROPDOWN);
        page.selectOption(DISTANCE_DROPDOWN, new SelectOption().setLabel(distance));
    }
    
    // ============= GENERIC DROPDOWN SELECTION METHODS =============
    
    /**
     * Generic method to select any option from country dropdown
     */
    public void selectCountryOption(String countryName) {
        waitUtility.waitForElementClickable(COUNTRY_DROPDOWN);
        waitUtility.waitForDropdownOptionsToLoad(COUNTRY_DROPDOWN);
        
        // Try exact match first
        try {
            page.selectOption(COUNTRY_DROPDOWN, new SelectOption().setLabel(countryName));
        } catch (Exception e) {
            // Try partial match if exact match fails
            String optionSelector = COUNTRY_DROPDOWN + " option:has-text('" + countryName + "')";
            if (page.isVisible(optionSelector)) {
                page.selectOption(COUNTRY_DROPDOWN, new SelectOption().setLabel(page.textContent(optionSelector)));
            } else {
                throw new RuntimeException("Country '" + countryName + "' not found in dropdown");
            }
        }
        waitUtility.waitForAjaxCallsToComplete();
    }
    
    /**
     * Generic method to select any distance option
     */
    public void selectDistanceOption(String distanceValue) {
        waitUtility.waitForElementClickable(DISTANCE_DROPDOWN);
        
        // Handle different distance formats: "20 Miles", "20", "20Miles"
        String normalizedDistance = distanceValue;
        if (!distanceValue.toLowerCase().contains("mile")) {
            normalizedDistance = distanceValue + " Miles";
        }
        
        try {
            page.selectOption(DISTANCE_DROPDOWN, new SelectOption().setLabel(normalizedDistance));
        } catch (Exception e) {
            // Try without normalization
            page.selectOption(DISTANCE_DROPDOWN, new SelectOption().setLabel(distanceValue));
        }
    }
    
    /**
     * Generic method to select any number of rooms
     */
    public void selectRoomsOption(String roomCount) {
        waitUtility.waitForElementClickable(ROOMS_DROPDOWN);
        page.selectOption(ROOMS_DROPDOWN, new SelectOption().setLabel(roomCount));
    }
    
    /**
     * Generic method to select any number of guests
     */
    public void selectGuestsOption(String guestCount) {
        waitUtility.waitForElementClickable(GUESTS_DROPDOWN);
        page.selectOption(GUESTS_DROPDOWN, new SelectOption().setLabel(guestCount));
    }
    
    // ============= DROPDOWN VALIDATION METHODS =============
    
    /**
     * Get all available countries from dropdown
     */
    public String[] getAvailableCountries() {
        waitUtility.waitForElementVisible(COUNTRY_DROPDOWN);
        waitUtility.waitForDropdownOptionsToLoad(COUNTRY_DROPDOWN);
        
        return page.locator(COUNTRY_DROPDOWN + " option").allTextContents().toArray(new String[0]);
    }
    
    /**
     * Get all available distance options
     */
    public String[] getAvailableDistances() {
        waitUtility.waitForElementVisible(DISTANCE_DROPDOWN);
        return page.locator(DISTANCE_DROPDOWN + " option").allTextContents().toArray(new String[0]);
    }
    
    /**
     * Get all available room options
     */
    public String[] getAvailableRooms() {
        waitUtility.waitForElementVisible(ROOMS_DROPDOWN);
        return page.locator(ROOMS_DROPDOWN + " option").allTextContents().toArray(new String[0]);
    }
    
    /**
     * Get all available guest options
     */
    public String[] getAvailableGuests() {
        waitUtility.waitForElementVisible(GUESTS_DROPDOWN);
        return page.locator(GUESTS_DROPDOWN + " option").allTextContents().toArray(new String[0]);
    }
    
    /**
     * Check if specific country is available
     */
    public boolean isCountryAvailable(String countryName) {
        String[] countries = getAvailableCountries();
        for (String country : countries) {
            if (country.equalsIgnoreCase(countryName) || country.contains(countryName)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Check if specific distance is available
     */
    public boolean isDistanceAvailable(String distance) {
        String[] distances = getAvailableDistances();
        for (String dist : distances) {
            if (dist.equalsIgnoreCase(distance) || dist.contains(distance)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Click search button
     */
    public void clickSearch() {
        waitUtility.waitForElementClickable(SEARCH_BUTTON);
        page.click(SEARCH_BUTTON);
        waitUtility.waitForFormSubmission();
    }
    
    /**
     * Click advanced options
     */
    public void clickAdvancedOptions() {
        waitUtility.waitForElementClickable(ADVANCED_OPTIONS_LINK);
        page.click(ADVANCED_OPTIONS_LINK);
        waitUtility.waitForAjaxCallsToComplete();
    }
    
    // ============= BOOKING DETAILS SECTION METHODS =============
    
    /**
     * Click to expand booking details
     */
    public void expandBookingDetails() {
        waitUtility.waitForElementClickable(BOOKING_DETAILS_EXPAND_BUTTON);
        page.click(BOOKING_DETAILS_EXPAND_BUTTON);
        waitUtility.waitForAjaxCallsToComplete();
    }
    
    /**
     * Click Add Contact link
     */
    public void clickAddContact() {
        waitUtility.waitForElementClickable(ADD_CONTACT_LINK);
        page.click(ADD_CONTACT_LINK);
        waitUtility.waitForFormSubmission();
    }
    
    /**
     * Click Add Travellers link
     */
    public void clickAddTravellers() {
        waitUtility.waitForElementClickable(ADD_TRAVELLERS_LINK);
        page.click(ADD_TRAVELLERS_LINK);
        waitUtility.waitForFormSubmission();
    }
    
    /**
     * Click Retrieve PNR link
     */
    public void clickRetrievePNR() {
        waitUtility.waitForElementClickable(RETRIEVE_PNR_LINK);
        page.click(RETRIEVE_PNR_LINK);
    }
    
    // ============= OPEN BOOKINGS SECTION METHODS =============
    
    /**
     * Click Open Bookings button
     */
    public void clickOpenBookingsButton() {
        waitUtility.waitForElementClickable(OPEN_BOOKINGS_BUTTON);
        page.click(OPEN_BOOKINGS_BUTTON);
    }
    
    /**
     * Sort bookings by arrival date
     */
    public void sortByArrivalDate() {
        waitUtility.waitForElementClickable(SORT_BY_ARRIVAL_DATE);
        page.click(SORT_BY_ARRIVAL_DATE);
        waitUtility.waitForAjaxCallsToComplete();
    }
    
    /**
     * Sort bookings by oldest
     */
    public void sortByOldest() {
        waitUtility.waitForElementClickable(SORT_BY_OLDEST);
        page.click(SORT_BY_OLDEST);
        waitUtility.waitForAjaxCallsToComplete();
    }
    
    // ============= FOOTER METHODS =============
    
    /**
     * Click Terms & Conditions
     */
    public void clickTermsAndConditions() {
        waitUtility.waitForElementClickable(TERMS_CONDITIONS_LINK);
        page.click(TERMS_CONDITIONS_LINK);
    }
    
    /**
     * Click Privacy link
     */
    public void clickPrivacyLink() {
        waitUtility.waitForElementClickable(PRIVACY_LINK);
        page.click(PRIVACY_LINK);
    }
    
    // ============= COMPREHENSIVE SEARCH METHODS =============
    
    /**
     * Perform complete hotel search with generic dropdown selections
     */
    public void performHotelSearch(String country, String location, String hotelName, 
                                  String distance, String nights, String rooms, String guests) {
        waitForDashboardToLoad();
        selectCountryOption(country);
        enterLocation(location);
        enterHotelName(hotelName);
        selectDistanceOption(distance);
        setNights(nights);
        selectRoomsOption(rooms);
        selectGuestsOption(guests);
        clickSearch();
    }
    /**
     * Perform complete hotel search with generic dropdown selections
     */
    public void performHotelSearch(String country, String location, 
                                  String distance, String nights, String rooms, String guests) {
        waitForDashboardToLoad();
        selectCountryOption(country);
        enterLocation(location);
        selectDistanceOption(distance);
        setNights(nights);
        selectRoomsOption(rooms);
        selectGuestsOption(guests);
        clickSearch();
    }
    
    /**
     * Perform hotel search with arrival date and generic selections
     */
    public void performHotelSearchWithDate(String arrivalDate, String country, String location, 
                                          String hotelName, String distance, String nights, 
                                          String rooms, String guests) {
        waitForDashboardToLoad();
        setArrivalDate(arrivalDate);
        selectCountryOption(country);
        enterLocation(location);
        enterHotelName(hotelName);
        selectDistanceOption(distance);
        setNights(nights);
        selectRoomsOption(rooms);
        selectGuestsOption(guests);
        clickSearch();
    }
    
    /**
     * Quick hotel search with minimal parameters
     */
    public void performQuickHotelSearch(String country, String location) {
        waitForDashboardToLoad();
        selectCountryOption(country);
        enterLocation(location);
        clickSearch();
    }
    
    /**
     * Advanced hotel search with all parameters and validation
     */
    public void getHotelAvailabilityWithOutHotelName(int arrivalDate, String country, String location, 
                                          String distance, String nights, 
                                         String rooms, String guests) {
        waitForDashboardToLoad();
        selectArrivalDateInCalendar(arrivalDate);
        selectCountryOption(country);
        enterLocation(location);
        selectDistanceOption(distance);
        setNights(nights);
        selectRoomsOption(rooms);
        selectGuestsOption(guests);
       
        clickSearch();
    }
    
    /**
     * Hotel search using calendar date picker with days from today
     * @param daysFromToday number of days from today for arrival date
     */
    public void getHotelAvailabilityWithOutHotelName(int daysFromToday, String country, String location, 
                                              String hotelName, String distance, String nights, 
                                              String rooms, String guests) {
        waitForDashboardToLoad();
        selectArrivalDateInCalendar(daysFromToday);
        selectCountryOption(country);
        enterLocation(location);
        enterHotelName(hotelName);
        selectDistanceOption(distance);
        setNights(nights);
        selectRoomsOption(rooms);
        selectGuestsOption(guests);
        clickSearch();
    }
    
    // ============= VALIDATION METHODS =============
    
    /**
     * Check if search button is enabled
     */
    public boolean isSearchButtonEnabled() {
        return page.isEnabled(SEARCH_BUTTON);
    }
    
    /**
     * Check if dashboard is loaded
     */
    public boolean isDashboardLoaded() {
        return page.isVisible(SEARCH_BUTTON) && page.isVisible(COUNTRY_DROPDOWN);
    }
    
    /**
     * Get current arrival date value
     */
    public String getArrivalDate() {
        return page.inputValue(ARRIVAL_DATE_INPUT);
    }
    
    /**
     * Get current nights value
     */
    public String getNights() {
        return page.inputValue(NIGHTS_INPUT);
    }
    
    /**
     * Get selected country
     */
    public String getSelectedCountry() {
        return page.locator(COUNTRY_DROPDOWN + " option[selected]").textContent();
    }
  
    /**
     * Check if Make Booking tab is active
     */
    public boolean isMakeBookingTabActive() {
        return page.isVisible(MAKE_BOOKING_TAB);
    }
    
    /**
     * Check if View Bookings tab is active
     */
    public boolean isViewBookingsTabActive() {
        return page.isVisible(VIEW_BOOKINGS_TAB);
    }
}