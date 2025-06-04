package org.toolshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;
import java.util.Objects;

public class HomePage {
    private static final String PAGE_URL = "https://practicesoftwaretesting.com/";

    private final WebDriver driver;

    @FindBy(xpath = "/html/body/app-root/div/app-login/div/div/div/form/div[3]/input")
    private WebElement loginButton;

    private static final Map<String, By> navigationLinks = Map.ofEntries(
            Map.entry("Main Menu", By.id("menu")),
            Map.entry("Sign in", By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[4]/a")),
            Map.entry("Users", By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[4]/ul/li[6]/a")),
            Map.entry("Add User", By.xpath("/html/body/app-root/div/app-users-list/form/div/a")),
            Map.entry("Brands", By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[4]/ul/li[2]/a")),
            Map.entry("Add Brand", By.xpath("/html/body/app-root/div/app-list/form/div/a")),
            Map.entry("Categories", By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/a")),
            Map.entry("Hand Tools", By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/ul/li[1]/a")),
            Map.entry("Cart", By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[5]/a")),
            Map.entry("My Profile", By.xpath("/html/body/app-root/app-header/nav/div/div/ul/li[4]/ul/li[3]/a")),
            Map.entry("My Favorites", By.xpath("/html/body/app-root/app-header/nav/div/div/ul/li[4]/ul/li[2]/a"))
    );

    private static final Map<String, By> loginFields = Map.of(
            "Email", By.id("email"),
            "Password", By.id("password")
    );

    private static final Map<String, By> dropdowns = Map.of(
        "Main", By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[4]/ul"),
            "Categories", By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]/ul")
    );

    private static final Map<String,String> pages = Map.of(
            "Admin Dashboard", "https://practicesoftwaretesting.com/admin/dashboard",
            "Add User", "https://practicesoftwaretesting.com/admin/users/add",
            "Add Brand", "https://practicesoftwaretesting.com/admin/brands/add",
            "Sign in", "https://practicesoftwaretesting.com/auth/login",
            "Admin", "https://practicesoftwaretesting.com/admin/",
            "Homepage", "https://practicesoftwaretesting.com/",
            "My Profile", "https://practicesoftwaretesting.com/account/profile"
    );

    private static final Map<String, By> succesMessages = Map.of(
            "User", By.cssSelector("body > app-root > div > app-users-add-edit > div > form > div:nth-child(3) > div > div"),
            "Brand", By.cssSelector("body > app-root > div > app-brands-add-edit > div > form > div:nth-child(3) > div > div"),
            "Cart", By.cssSelector("#toast-container > div > div")
    );

    private static final Map<String, By> profileName = Map.of(
        "Profile Name", By.xpath("//*[@id=\"menu\"]")
    );

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void closePage() {
        driver.quit();
    }

    public void fillLoginForm(String field, String text){
        driver.findElement(loginFields.get(field)).sendKeys(text);
    }

    public void goToPage(String page){
        driver.navigate().to(pages.get(page));
    }

    public void navigateTo(String string) {
        driver.findElement(navigationLinks.get(string)).click();
    }

    public boolean onPage(String page) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Objects.requireNonNull(driver.getCurrentUrl()).startsWith(pages.get(page));
    }

    public void clickLogin() {
        loginButton.click();
    }

    public boolean isLoggedIn(String name){
        boolean loggedIn = !driver.findElements(profileName.get("Profile Name")).isEmpty();
        return loggedIn && driver.findElement(profileName.get("Profile Name")).getText().equals(name);
    }

    public boolean isAdmin(){
        boolean loggedIn = !driver.findElements(profileName.get("Profile Name")).isEmpty();
        return loggedIn && driver.findElement(profileName.get("Profile Name")).getText().equals("John Doe");
    }

    public String getSuccessMessage(String type) {
        return driver.findElement(succesMessages.get(type)).getText();
    }

    //I couldn't figure out how to wait for an element to 'Can not be found'
    //visibility check doesn't work
    public void waitForAlertDisappear() {
        try {
            Thread.sleep(6500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
