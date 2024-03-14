package base;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import utils.CookieManager;
import utils.EventReporter;
import utils.WindowManager;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseTests {

    //Create WebDriver object
    private EventFiringWebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    //Create method
    public void setUp() {
        //Set up the property
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        //Instantiate the WebDriver object
        driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));
        driver.register(new EventReporter());
        //To launch the application in the browser
        goHome();
        setCookie();

        homePage = new HomePage(driver);
    }

    @BeforeMethod
    public void goHome(){
        driver.get("https://the-internet.herokuapp.com/");
    }
        //driver.manage().window().setSize(new Dimension(390,844));

        /*List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println(links.size());

        WebElement inputLinks = driver.findElement(By.linkText("Inputs"));
        inputLinks.click();*/

        /*driver.findElement(By.linkText("Shifting Content")).click();
        driver.findElement(By.linkText("Example 1: Menu Element")).click();

        List<WebElement> menuItems = driver.findElements(By.tagName("li"));
        System.out.println("Number of menu items: " + menuItems.size());*/

    @AfterClass
    public void tearDown(){
            driver.quit();
    }

    //Create the main method to try execute
    //public static void main(String[] args) {
        //Create the instance of the class
    //    BaseTests baseTests = new BaseTests();
        //Call the method
    //    baseTests.setUp();

    //}

    @AfterMethod
    public void recordFailure(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus()){
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            //System.out.println("Screenshot taken: " + screenshot.getAbsolutePath());
            try {
                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }


    public WindowManager getWindowManager(){
        return new WindowManager(driver);
    }

    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-infobars");
        //options.setHeadless(true);
        return options;
    }

    private void setCookie(){
        Cookie cookie = new Cookie.Builder("tau", "123")
                .domain("the-internet.herokuapp.com")
                .build();
        driver.manage().addCookie(cookie);
    }

    public CookieManager getCookieManager(){
        return new CookieManager(driver);
    }
}
