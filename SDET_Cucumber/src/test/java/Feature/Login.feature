Feature: Facebook Login page
  Scenario Outline: Login Page Test Facebook
    Given Opening the browser <Browser>
    And enter the <url> in chrome browser
    When Login page open
    And enter <username> and <password> in textbox
    And click on submit button
    Then welcome home page open
    
    Examples:
     |Browser|username|password|url|
     |chrome|manojk2829@gmail.com|maurya@282920|http://facebook.com/|
     |chrome|skushwaha2829@gmail.com|applesu|http://rediffmail.com/|