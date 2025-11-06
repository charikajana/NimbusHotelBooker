@Sabre_vs_Sabre_Sanity @AI @sanity
Feature: Sabre vs BCOM Single Night Single Room Single Guest Booking

  Background:
    Given Open Browser and Navigate to HotelBooker
    When user enters username and password
    And user clicks login button
    And selects client "Test QA Client(Sabre)"
    Then Validate selected client should display on header

  Scenario Outline: Sabre vs BCOM Single Night Single Room Single Guest Standard Booking
    # SHOP Phase
    When user selects country "<country>"
#    And enters location "<location>" from suggestion
#    And selects distance "<distance>"
#    And enters number of nights as "<nights>"
#    And selects number of rooms as "<rooms>"
#    And selects number of guests as "<guests>"
#    And selects arrival date <days> days from today
#    And clicks on search button
#    Then hotel search results should be displayed or a message if no hotels found
#    And Select the Rate Plan from "<provider>" with refundable "<refundable>"


    Examples:
      | country | location   | hotelName | distance | days | nights | rooms | guests | provider    | refundable | deposit | special_instruction | payment_method | cancel_method    | email_recipients    |
      | USA     | Dallas     | Holiday   | 20 Miles | 30   | 1      | 1     | 1      | Sabre       | No        | false   | Room Only          | Credit Card    | Cancel by Room   | Booker,Agent,Client |
      | India     | New - York | Holiday   | 20 Miles | 30   | 1      | 1     | 1      | Sabre       | No        | false   | Room Only          | Credit Card    | Cancel by Room   | Booker,Agent,Client |

  Scenario Outline: Sabre vs BCOM Single Night Single Room Single Guest Standard Booking
    # SHOP Phase
    When user selects country "<country>"
#    And enters location "<location>" from suggestion
#    And selects distance "<distance>"
#    And enters number of nights as "<nights>"
#    And selects number of rooms as "<rooms>"
#    And selects number of guests as "<guests>"
#    And selects arrival date <days> days from today
#    And clicks on search button
#    Then hotel search results should be displayed or a message if no hotels found
#    And Select the Rate Plan from "<provider>" with refundable "<refundable>"


    Examples:
      | country | location   | hotelName | distance | days | nights | rooms | guests | provider    | refundable | deposit | special_instruction | payment_method | cancel_method    | email_recipients    |
      | USA     | Dallas     | Holiday   | 20 Miles | 30   | 1      | 1     | 1      | Sabre       | No        | false   | Room Only          | Credit Card    | Cancel by Room   | Booker,Agent,Client |
