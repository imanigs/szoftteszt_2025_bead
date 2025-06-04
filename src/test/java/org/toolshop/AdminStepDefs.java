package org.toolshop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminStepDefs extends AbstractStepDefs{

    @And("the {string} form is sent")
    public void theFormIsSent(String form) {
        adminPage.sendForm(form);
    }

    @Given("the {string} is set to {string}")
    public void theFieldIsSetToText(String field, String text) {
        adminPage.setFieldValue(field, text);
    }

    @And("the {string} is checked")
    public void theCheckboxIsChecked(String checkbox) {
        adminPage.checkBox(checkbox);
    }

    @Then("the {string} alert is shown")
    public void theAlertIsShown(String message) {
        assertEquals(message,adminPage.getAlertMessage());
    }

    @Then("on the {string} field the {string} error is shown")
    public void onTheFieldTheErrorIsShown(String field, String message) {
        assertEquals(message, adminPage.getErrorMessage(field));
    }

    @Given("the {string} button is clicked")
    public void theButtonIsClicked(String button) {
        adminPage.clickButton(button);
    }
}
