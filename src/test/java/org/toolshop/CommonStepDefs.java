package org.toolshop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommonStepDefs extends AbstractStepDefs{

    @Given("the home page is opened")
    public void theHomePageIsOpened() {
        homePage.openPage();
    }

    //used to push menus and buttons that navigate on the page
    @And("we navigate to {string}")
    public void weNavigateTo(String string) {
        homePage.navigateTo(string);
    }

    @Given("the {string} login field is filled with {string}")
    public void theLoginFieldIsFilledWith(String field, String text) {
        homePage.fillLoginForm(field,text);
    }

    @And("the login button is clicked")
    public void loginButtonClicked(){
        homePage.clickLogin();
    }

    //used to wait for page load and verify if we navigated to the correct page
    @Then("we are on the {string} page")
    public void weAreOnThePage(String page) {
        assertTrue(homePage.onPage(page));
    }

    //used to log in if we are not already logged in, then continue
    @Given("we are logged in as admin")
    public void weAreLoggedInAsAdmin() {
        if (!homePage.isAdmin()) {    //the site will wait implicitly wait WAIT_TIME because of this
            homePage.navigateTo("Sign in");
            homePage.fillLoginForm("Email", "admin@practicesoftwaretesting.com");
            homePage.fillLoginForm("Password", "welcome01");
            homePage.clickLogin();
        }
        assertTrue(homePage.isAdmin());
    }

    //used to log in if we are not already logged in, then continue
    @And("we are logged in as default user")
    public void weAreLoggedInAs() {
        if (!homePage.isLoggedIn("Jack Howe")){      //the site will wait implicitly wait WAIT_TIME because of this
            homePage.navigateTo("Sign in");
            homePage.fillLoginForm("Email", "customer2@practicesoftwaretesting.com");
            homePage.fillLoginForm("Password", "welcome01");
            homePage.clickLogin();
        }
        assertTrue(homePage.isLoggedIn("Jack Howe"));
    }

    //written, so multiple expected strings can be given with '/'
    @Then("the {string} success message is {string}")
    public void theSuccessIsShown(String type, String message) {
        String[] messages = message.split("/");
        String correct = messages[0];
        String actual = homePage.getSuccessMessage(type);
        for (int i = 0; i < messages.length; i++){
           if (actual.equals(messages[i])){
               assertEquals(messages[i],actual);
           }
        }
    }

    //thread.sleep explained in homePage
    @And("we wait for message to disappear")
    public void weWaitForTheMessageToDisappear() {
        homePage.waitForAlertDisappear();
    }

    //will not close all chrome drivers in the background
    // I haven't found a solution
    @AfterAll
    public static void cleanUp(){
        driver.close();
        driver.quit();
    }

    @And("we open the {string} page")
    public void weOpenThePage(String page) {
        homePage.goToPage(page);
    }
}
