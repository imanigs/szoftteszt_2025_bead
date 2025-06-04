package org.toolshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class AdminPage {
    private final WebDriver driver;

    private static final Map<String, By> errorMessages = Map.of(
            "First Name", By.cssSelector("body > app-root > div > app-users-add-edit > div > form > div:nth-child(2) > div > div:nth-child(1) > div.alert.alert-danger.mt-3.p-2 > div"),
            "Last Name", By.cssSelector("body > app-root > div > app-users-add-edit > div > form > div:nth-child(2) > div > div:nth-child(2) > div.alert.alert-danger.mt-3.p-2 > div"),
            "Date of birth", By.cssSelector("body > app-root > div > app-users-add-edit > div > form > div:nth-child(2) > div > div:nth-child(3) > div.alert.alert-danger.mt-3.p-2 > div"),
            "Street", By.cssSelector("body > app-root > div > app-users-add-edit > div > form > div:nth-child(2) > div > div.ng-invalid.ng-touched.ng-dirty > div:nth-child(1) > div.alert.alert-danger.mt-3.p-2 > div")
    );

    private static final Map<String, By> formFields = Map.ofEntries(
            Map.entry("First Name", By.id("firstname")),
            Map.entry("Last Name", By.id("lastname")),
            Map.entry("Date of birth", By.id("dob")),
            Map.entry("Street", By.id("street")),
            Map.entry("Postal code", By.id("postal_code")),
            Map.entry("City", By.id("city")),
            Map.entry("State", By.id("state")),
            Map.entry("Country", By.id("country")),
            Map.entry("Phone", By.id("phone")),
            Map.entry("Email", By.id("email")),
            Map.entry("Failed login attempts", By.id("failed_login_attempts")),
            Map.entry("Password", By.id("password")),
            Map.entry("Brand Name", By.id("name")),
            Map.entry("Brand Slug", By.id("slug"))
    );

    private static final Map<String, By> checkBoxes = Map.of(
            "Enabled", By.id("enabled")
    );

    private static final Map<String,By> buttons = Map.of(
            "Delete brand", By.xpath("/html/body/app-root/div/app-list/table/tbody/tr[3]/td[4]/button")
    );

    private static final Map<String, By> formSubmitButtons = Map.of(
            "Add User", By.xpath("/html/body/app-root/div/app-users-add-edit/div/form/div[3]/div/button"),
            "Create Brand", By.xpath("/html/body/app-root/div/app-brands-add-edit/div/form/div[3]/div/button")
    );

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    public void sendForm(String form) {
        driver.findElement(formSubmitButtons.get(form)).click();
    }

    public void setFieldValue(String field, String text) {
        driver.findElement(formFields.get(field)).sendKeys(text);
    }

    public void checkBox(String checkbox) {
        driver.findElement(checkBoxes.get(checkbox)).click();
    }

    public String getErrorMessage(String field) {
        return driver.findElement(errorMessages.get(field)).getText();
    }

    public String getAlertMessage() {
        return driver.findElement(By.cssSelector("#toast-container > div")).getText();
    }

    public void clickButton(String button) {
        driver.findElement(buttons.get(button)).click();
    }
}
