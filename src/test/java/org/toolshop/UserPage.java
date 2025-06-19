package org.toolshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;

import java.util.Map;

public class UserPage {
    private final WebDriver driver;

    private static final Map<String, By> toolFilters = Map.of(
            "Chisels", By.xpath("//*[@id=\"filters\"]/div[1]/ul/div[6]/label/input"),
            "Wrench", By.xpath("//*[@id=\"filters\"]/div[1]/ul/div[3]/label/input"),
            "Hand Saw", By.xpath("//*[@id=\"filters\"]/div[1]/ul/div[2]/label/input"),
            "Screwdriver", By.xpath("//*[@id=\"filters\"]/div[1]/ul/div[4]/label/input")
    );

    private static final Map<String, By> toolCards = Map.of(
        "Wood Carving Chisels", By.xpath("/html/body/app-root/div/app-category/div[2]/div[2]/div[1]/a[2]"),
        "Swiss Woodcarving Chisels", By.xpath("/html/body/app-root/div/app-category/div[2]/div[2]/div[1]/a[3]"),
            "Adjustable Wrench", By.xpath("/html/body/app-root/div/app-category/div[2]/div[2]/div[1]/a[1]"),
            "Chisels Set", By.xpath("/html/body/app-root/div/app-category/div[2]/div[2]/div[1]/a[1]")
    );

    private static final Map<String, By> typeFields = Map.of(
        "New Password", By.xpath("/html/body/app-root/div/app-profile/div[1]/form[2]/div[2]/div/div[2]/div/app-password-input/div/input"),
            "Confirm New Password", By.xpath("/html/body/app-root/div/app-profile/div[1]/form[2]/div[2]/div/div[3]/div/app-password-input/div/input"),
            "Quantity", By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[1]/app-cart/div/table/tbody/tr/td[2]/input")
    );

    private static final Map<String, By> buttons = Map.of(
        "Add to Cart", By.xpath("/html/body/app-root/div/app-detail/div[1]/div[2]/div[2]/button[1]"),
            "Delete Product", By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[1]/app-cart/div/table/tbody/tr/td[5]/a"),
            "Add to Favorites", By.xpath("/html/body/app-root/div/app-detail/div[1]/div[2]/div[2]/button[2]"),
            "Delete Favorite", By.xpath("/html/body/app-root/div/app-favorites/div/div[1]/div/div[3]/button/fa-icon"),
            "Remove item", By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[1]/app-cart/div/table/tbody/tr[1]/td[5]/a")
    );

    public UserPage(WebDriver driver) {
        this.driver = driver;
    }

    public void filterTool(String tool) {
        driver.findElement(toolFilters.get(tool)).click();
    }

    // thread.sleep is used because this element is always there,
    // but we have to wait until the filter is applied so the correct,
    // new number of elements are counted
    public int shownToolNumber() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return driver.findElements(By.xpath("/html/body/app-root/div/app-category/div[2]/div[2]/div[1]/a")).size();
    }

    public void clickToolPage(String tool) {
        driver.findElement(toolCards.get(tool)).click();
    }

    //If I don't wait this way, the site navigates so fast that
    //the button does not take action on the site
    public void pressButton(String button) {
        driver.findElement(buttons.get(button)).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCheckoutPrice() {
        return driver.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[1]/app-cart/div/table/tfoot/tr/td[4]")).getText();
    }

    public void typeInField(String field, String value) {
        WebElement element = driver.findElement(typeFields.get(field));
        element.sendKeys(Keys.BACK_SPACE,value,Keys.ENTER);
    }

    // the items are always there, so implicitly waiting for them
    // does not help, thread.sleep used to wait until the site reacts
    public String getPasswordStrength() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement element = driver.findElement(By.xpath("/html/body/app-root/div/app-profile/div[1]/form[2]/div[2]/div/div[2]/div/div[2]/div[1]"));
        return element.findElement(By.className("active")).getText();
    }

    public String getFavoriteItem(){
        WebElement element = driver.findElement(By.xpath("/html/body/app-root/div/app-favorites/div/div/div/div[2]/div/h5"));
        return element.getText();
    }
}
