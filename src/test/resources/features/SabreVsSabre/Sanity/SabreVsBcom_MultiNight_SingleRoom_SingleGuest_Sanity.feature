@Sabre_vs_BCOM_Sanity @sanity
Feature: Sabre vs BCOM Multi Night Single Room Single Guest Booking

  Background:
    Given Open Browser and Navigate to HotelBooker
    When user enters username and password
    And user clicks login button
    Given user selects client "Test QA Client(Sabre)"
    Then selected client should display on header
  Scenario Outline: Sabre vs BCOM Multi Night Single Room Single Guest Virtual Card Booking
    # SHOP Phase
    When user selects country "<country>"
    And enters location "<location>" from suggestion
    And enters hotel name "<hotelName>"
    And selects distance "<distance>"
    And enters number of nights as "<nights>"
    And selects number of rooms as "<rooms>"
    And selects number of guests as "<guests>"
    And selects arrival date <days> days from today
    And clicks on search button
    
    # AVAILABILITY VALIDATION - Mandatory Pattern
    Then hotel Availability page should be displayed
    And Validate pagination links are displayed and working
    
    When user clicks on Check Availability for Active Content Provider "<provider>"
    Then rate information should be loaded and displayed
    
    # BOOK Phase  
    When user reviews and selects rate with refundable option "<refundable>" and deposit requirement "<deposit>"
    And user proceeds to booking page with special instructions "<special_instruction>"
    And user fills guest information for single guest
    And user completes payment process using "<payment_method>"
    Then booking confirmation page should be displayed with generated PNR
    And virtual card details should be successfully generated and displayed
    And booking confirmation email should be sent to all recipients "<email_recipients>"
    
    # RETRIEVE Phase
    When user navigates to booking retrieval section
    And user searches for booking using the generated PNR
    Then retrieved booking details should match the original reservation
    And virtual card status should be displayed as "ACTIVE"
    And overall booking status should show as "CONFIRMED"
    
    # CANCEL Phase
    When user initiates booking cancellation using "<cancel_method>" option
    Then cancellation confirmation page should be displayed
    And virtual card should be automatically cancelled and status updated
    And cancellation notification email should be sent to all recipients "<email_recipients>"
    And final booking status should be updated to "CANCELLED"

    Examples:
      | country | location            | hotelName | distance | days | nights | rooms | guests | provider    | refundable | deposit | special_instruction | payment_method | cancel_method    | email_recipients    |
      | USA     | New York - Location | Holiday   | 20 Miles | 30   | 3      | 1     | 1      | Booking.com | No         | false   | Room Only          | Virtual Card   | Cancel by Room   | Booker,Agent,Client |
      | UK      | London - Location   | Holiday   | 10 Miles | 45   | 5      | 1     | 1      | Booking.com | Yes        | true    | Bed & Breakfast    | Virtual Card   | Full Cancellation| Booker,Travellers   |
      | France  | Paris - Location    | Holiday   | 15 Miles | 60   | 7      | 1     | 1      | Booking.com | No         | false   | Room Only          | Virtual Card   | Cancel by Room   | Agent,Client        |