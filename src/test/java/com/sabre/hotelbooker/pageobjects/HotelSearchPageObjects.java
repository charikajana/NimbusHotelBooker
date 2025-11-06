package com.sabre.hotelbooker.pageobjects;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import com.sabre.hotelbooker.hotelbookerutility.ExplicitWaitUtility;
import java.time.LocalDate;

public class HotelSearchPageObjects {
  
    private static final String CLIENT_HEADING = "h2:has-text('Test QA Client(Sabre)')";
    private static final String COUNTRY_DROPDOWN = "#ctl00_lstCountry";
    private static final String LOCATION_FIELD = "input[placeholder*='Place']";
    private static final String HOTEL_NAME_FIELD = "#ctl00_txtHotelName";
    private static final String DISTANCE_DROPDOWN = "#ctl00_lstDistance";
    private static final String ARRIVAL_DATE_FIELD = "#ctl00_txtArrivalDate";
    private static final String NIGHTS_FIELD = "#ctl00_txtNights";
    private static final String ROOMS_DROPDOWN = "#ctl00_lstRooms";
    private static final String GUESTS_DROPDOWN = "#ctl00_lstOccupancy";
    private static final String SEARCH_BUTTON = "#ctl00_btnSearch";
    private static final String HEADER_CLIENT = "#lnkClientSelect";
    private static final String NO_HOTEL_MESSAGE = "text=Sorry, we weren't able to find any hotels for the criteria you specified.";
    private static final String SEARCH_RESULTS = "#ctl00_cphMainContent_pnlHotels";

    private final Page page;
    private final ExplicitWaitUtility waitUtility;

    public HotelSearchPageObjects(Page page) {
        this.page = page;
        this.waitUtility = new ExplicitWaitUtility(page);
    }

    public void selectClient() {
        waitUtility.waitForElementClickable(CLIENT_HEADING);
        page.click(CLIENT_HEADING);
    }

    public boolean isClientDisplayedOnHeader() {
        boolean visible = page.isVisible(HEADER_CLIENT);
        return visible;
    }

    public void selectCountry(String country) {
        waitUtility.waitForDropdownOptionsToLoad(COUNTRY_DROPDOWN);
        page.selectOption(COUNTRY_DROPDOWN, new SelectOption().setLabel(country));
    }

    public void enterLocation(String location) {
        waitUtility.waitForElementVisible(LOCATION_FIELD);
        page.fill(LOCATION_FIELD, location);
    }

    public void enterHotelName(String hotelName) {
        waitUtility.waitForElementVisible(HOTEL_NAME_FIELD);
        page.fill(HOTEL_NAME_FIELD, hotelName);
    }

    public void selectDistance(String distance) {
        waitUtility.waitForDropdownOptionsToLoad(DISTANCE_DROPDOWN);
        page.selectOption(DISTANCE_DROPDOWN, new SelectOption().setLabel(distance));
    }

    public void setArrivalDateDaysFromToday(int days) {
        waitUtility.waitForElementVisible(ARRIVAL_DATE_FIELD);
        page.locator(ARRIVAL_DATE_FIELD).clear();
        selectArrivalDateInCalendar(days);
    }

    public void setNights(String nights) {
        waitUtility.waitForElementVisible(NIGHTS_FIELD);
        page.fill(NIGHTS_FIELD, nights);
    }

    public void selectRooms(String rooms) {
        waitUtility.waitForDropdownOptionsToLoad(ROOMS_DROPDOWN);
        page.selectOption(ROOMS_DROPDOWN, new SelectOption().setLabel(rooms));
    }

    public void selectGuests(String guests) {
        waitUtility.waitForDropdownOptionsToLoad(GUESTS_DROPDOWN);
        page.selectOption(GUESTS_DROPDOWN, new SelectOption().setLabel(guests));
    }

    public void clickSearchButton() {
        waitUtility.waitForElementClickable(SEARCH_BUTTON);
        page.click(SEARCH_BUTTON);
    }

    public boolean isSearchResultDisplayedOrNoHotelMessage() {
       waitUtility.waitForElementVisible(SEARCH_RESULTS);
        boolean result = page.isVisible(SEARCH_RESULTS) || page.isVisible(NO_HOTEL_MESSAGE);
        return result;
    }
    public void selectArrivalDateInCalendar(int daysFromToday) {
        LocalDate targetDate = LocalDate.now().plusDays(daysFromToday);
        int targetDay = targetDate.getDayOfMonth();
        int targetMonth = targetDate.getMonthValue();
        int targetYear = targetDate.getYear();
        page.click(ARRIVAL_DATE_FIELD);
        String monthLabelSelector = ".datepicker-days .datepicker-switch";
        String nextMonthButtonSelector = ".datepicker-days th.next";
        while (true) {
            String monthYearText = page.textContent(monthLabelSelector).trim();
            java.time.format.DateTimeFormatter calFmt = java.time.format.DateTimeFormatter.ofPattern("MMMM yyyy");
            java.time.YearMonth displayedMonthYear = java.time.YearMonth.parse(monthYearText, calFmt);
            if (displayedMonthYear.getMonthValue() == targetMonth && displayedMonthYear.getYear() == targetYear) {
                break;
            }
            page.click(nextMonthButtonSelector);
        }
        String dayCellSelector = String.format(".datepicker-days td.day:not(.old):not(.new):text-is('%d')", targetDay);
        page.click(dayCellSelector);
    }
}
