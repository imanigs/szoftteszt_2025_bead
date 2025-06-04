package org.toolshop;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStepDefs {

    private static final int WAIT_TIME = 10;

    protected static final WebDriver driver;

    protected static HomePage homePage;
    protected static AdminPage adminPage;
    protected static UserPage userPage;

    static {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        options.addArguments("window-size=1500,600");
        //options.addArguments("--headless=new");

        //trying to prevent the password leak detection popup.. (:
        // sometimes works, sometimes doesn't
        options.addArguments("--password-store=basic");
        options.addArguments("--no-sandbox");
        options.addArguments("credentials_enable_service", "false");
        options.addArguments("profile.password_manager_enabled", "false");
        options.addArguments("profile.password_manager_leak_detection", "false");
        options.addArguments("--disable-features=SafeBrowsing");
        options.addArguments("--disable-features=PasswordManagerOnboarding");
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);

        /*
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        */

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_TIME));

        homePage = new HomePage(driver);
        adminPage = new AdminPage(driver);
        userPage = new UserPage(driver);
    }
}

