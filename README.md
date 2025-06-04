Automated tests on site https://practicesoftwaretesting.com/
Some explaining comments are included in the code.

The 'favorites' and 'password' feature files fail headless,
because of the chrome password leak detection popup.
Manually cancelling it lets the test continue and pass.

Not tested on firefox, but if there are no popups, I expect it to pass.
