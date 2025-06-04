package org.toolshop;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserStepDefs extends AbstractStepDefs{

    @And("we filter {string}")
    public void weFilterTool(String tool) {
        userPage.filterTool(tool);
    }

    //thread.sleep here, explained in userPage
    @Then("{string} tools are shown")
    public void item_numberToolsAreShown(String number) {
        assertEquals(Integer.parseInt(number), userPage.shownToolNumber());
    }

    @And("we view the {string}")
    public void weViewTheTool(String tool) {
        userPage.clickToolPage(tool);
    }

    @And("the {string} button is pressed")
    public void theButtonIsPressed(String button) {
        userPage.pressButton(button);
    }

    @Then("the total price is {string}")
    public void theTotalPriceIs$(String price) {
        assertEquals(price, userPage.getCheckoutPrice());
    }

    @Given("we type {string}: {string}")
    public void weType(String field, String value) {
        userPage.typeInField(field,value);
    }

    @Then("the password strength is shown as {string}")
    public void thePasswordStrengthIsShownAs(String strength) {
        assertEquals(strength, userPage.getPasswordStrength());
    }

    @Then("the {string} is in the favorites")
    public void theItemIsInTheFavorites(String item) {
        assertEquals(item,userPage.getFavoriteItem());
    }

    @And("we delete the favorite item")
    public void weDeleteTheFavoriteItem() {
        userPage.pressButton("Delete Favorite");
    }
}
