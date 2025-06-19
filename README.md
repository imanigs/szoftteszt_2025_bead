Automated tests on site https://practicesoftwaretesting.com/
Some explaining comments are included in the code.

The 'favorites' and 'password' feature files fail headless,
because of the chrome password leak detection popup.
Manually cancelling it lets the test continue and pass.
Explaining comments in the feature files.

Not tested on firefox, but if there are no popups, I expect it to pass.

Thread.sleep is used in the project, because I haven't found better waiting strategy in the selenium documentation that works for these cases.
The 'Page' and 'StepDef' files are separated based on work logic, but the 'Page' files are not used for site loading, only the HomePage. I don't know what is the best practice.
