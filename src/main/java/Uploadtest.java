import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.remote.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Uploadtest {

    public String username = System.getenv("LT_USERNAME"); //lambda UserName
    public String accesskey = System.getenv("LT_ACCESS_KEY");
    ; //lambda accessKey
    public RemoteWebDriver driver;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    String status;
    float ClickCommandTime;
    long CommandStart;
    long CommandStop;
    float SendKeyCommand;
    long SendKeysStart;
    long SendKeysStop;


    @BeforeTest
    @org.testng.annotations.Parameters(value = {"browser", "version", "platform", "resolution", "timezone"})
    public void setUp(String browser, String version, String platform, String resolution, String timezone) throws Exception {


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", System.getenv("LT_BROWSER_NAME"));
        capabilities.setCapability("browserVersion", System.getenv("LT_BROWSER_VERSION"));
        capabilities.setCapability("platformName", System.getenv("LT_OPERATING_SYSTEM"));
        capabilities.setCapability("build", System.getenv("LT_BUILD_NAME"));
        capabilities.setCapability("name", "testName");
        capabilities.setCapability("network", true);
        capabilities.setCapability("visual", true);
        capabilities.setCapability("video", true);
        capabilities.setCapability("console", true);


        try {
            String url = "https://" + username + ":" + accesskey + gridURL;
            System.out.println(url);
            driver = new RemoteWebDriver(new URL(url), capabilities);

        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception f) {
            System.out.println(f.getMessage());
        }
    }

    @Test(enabled = true)
    public void DesktopScript() {
        try {
            driver.get("https://lambdatest.github.io/sample-todo-app/");
            WebDriverWait wait = new WebDriverWait(driver, 30);
            WebElement firstItem;
            firstItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div > div > div > ul > li:nth-child(1) > input")));
            WebElement secondItem = driver.findElement(By.cssSelector("body > div > div > div > ul > li:nth-child(2) > input"));
            WebElement thirdItem = driver.findElement(By.cssSelector("body > div > div > div > ul > li:nth-child(4) > input"));
            WebElement fifthElement = driver.findElement(By.cssSelector("body > div > div > div > ul > li:nth-child(5) > input"));

            CommandStart = System.currentTimeMillis();
            firstItem.click();
            secondItem.click();
            thirdItem.click();
            fifthElement.click();
            CommandStop = System.currentTimeMillis();
            ClickCommandTime = CommandStop - CommandStop / 1000f;
            System.out.println("Time taken to perform 4 clicks" + "  " + "=" + ClickCommandTime);
           /* WebElement firstOption = driver.findElement(By.cssSelector("body > div > div > div > ul > li:nth-child(3) > input"));
            action.moveToElement(firstOption).click().perform();*/
            SendKeysStart = System.currentTimeMillis();
            driver.findElement(By.xpath("//*[@id=\"sampletodotext\"]")).sendKeys("new item added");
            SendKeysStop = System.currentTimeMillis();
            SendKeyCommand = SendKeysStop - SendKeysStart / 1000f;
            System.out.println("SendKeys 14 char value Command to execute" + "  " + "=" + SendKeyCommand);

            driver.findElement(By.xpath("//*[@id=\"addbutton\"]")).isDisplayed();
            driver.findElement(By.xpath("//*[@id=\"addbutton\"]")).click();

        } catch (Exception e) {

            status = "failed";
            System.out.println(e.getMessage());
        }
        System.out.println(driver.getCapabilities());

    }

    @AfterSuite
    @org.testng.annotations.Parameters(value = {"browser", "version", "platform"})
    public void tearDown(String version, String platform, String browser) throws Exception {

        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }

    }

}

