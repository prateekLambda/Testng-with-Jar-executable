import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class LocalDriverTest {
    float ClickCommandTime;
    long CommandStart;
    long CommandStop;
    float SendKeyCommand;
    long SendKeysStart;
    long SendKeysStop;

    @Test
    public void IntializeDriver() {
        System.setProperty("webdriver.chrome.driver", "E:\\localDrivers\\chromedriver.exe"); // your system driver path
        WebDriver driver = new ChromeDriver();
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
            driver.quit();
        } catch (Exception e) {
            System.out.println(e);

        }


    }
}
