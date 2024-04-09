package backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;

@ExtendWith(SeleniumJupiter.class)
public class SeleniumTest {
  private ChromeDriver driver;

  @Test
  public void backend() throws InterruptedException {

    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();  

    options.addArguments("--remote-allow-origins=*");

    // 1 | open | http://
    driver = new ChromeDriver(options);
    driver.get("http://localhost:8000/html/index.html");
    //driver.get("http://127.0.0.1:5500/frontend/html/index.html");
    driver.manage().window().setSize(new Dimension(1920, 1048));

    // 2 | wait until page ready
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pickup")));


    // 3 | select | id=pickup | label=Faro
    {
      WebElement dropdown = driver.findElement(By.id("pickup"));
      dropdown.findElement(By.xpath("//option[. = 'Faro']")).click();
    }
    // 4 | mouseDownAt | id=pickup | 0,-0.75
    {
      WebElement element = driver.findElement(By.id("pickup"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    // 5 | mouseMoveAt | id=pickup | 0,-0.75
    {
      WebElement element = driver.findElement(By.id("pickup"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    // 6 | mouseUpAt | id=pickup | 0,-0.75
    {
      WebElement element = driver.findElement(By.id("pickup"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    // 7 | select | id=destination | label=Porto
    {
      WebElement dropdown = driver.findElement(By.id("destination"));
      dropdown.findElement(By.xpath("//option[. = 'Porto']")).click();
    }
    // 8 | mouseDownAt | id=destination | -0.984375,-0.75
    {
      WebElement element = driver.findElement(By.id("destination"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    // 9 | mouseMoveAt | id=destination | -0.984375,-0.75
    {
      WebElement element = driver.findElement(By.id("destination"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    // 10 | mouseUpAt | id=destination | -0.984375,-0.75
    {
      WebElement element = driver.findElement(By.id("destination"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    // 11 | click | css=.w3-button | 
    driver.findElement(By.cssSelector(".w3-button")).click();
    // 12 | select | id=currencyDropdown | label=GBP
    {
      WebElement dropdown = driver.findElement(By.id("currencyDropdown"));
      dropdown.findElement(By.xpath("//option[. = 'GBP']")).click();
    }
    // 13 | mouseDownAt | id=currencyDropdown | 0,-0.5
    {
      WebElement element = driver.findElement(By.id("currencyDropdown"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    // 14 | mouseMoveAt | id=currencyDropdown | 0,-0.5
    {
      WebElement element = driver.findElement(By.id("currencyDropdown"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    // 15 | mouseUpAt | id=currencyDropdown | 0,-0.5
    {
      WebElement element = driver.findElement(By.id("currencyDropdown"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    // 16 | click | css=tr:nth-child(2) .w3-button | 
    driver.findElement(By.cssSelector("tr:nth-child(2) .w3-button")).click();
    // 17 | click | id=name | 
    driver.findElement(By.id("name")).click();
    // 18 | type | id=name | Rodrigo
    driver.findElement(By.id("name")).sendKeys("Rodrigo");
    // 19 | click | id=email | 
    driver.findElement(By.id("email")).click();
    // 20 | type | id=email | rodrigo@gmail.com
    driver.findElement(By.id("email")).sendKeys("rodrigo@gmail.com");
    // 21 | click | id=ccardnumber | 
    driver.findElement(By.id("ccardnumber")).click();
    // 22 | type | id=ccardnumber | 1234 1234 1234 1234
    driver.findElement(By.id("ccardnumber")).sendKeys("1234 1234 1234 1234");
    // 23 | click | id=ccardyear | 
    driver.findElement(By.id("ccardyear")).click();
    // 24 | type | id=ccardyear | 12/26
    driver.findElement(By.id("ccardyear")).sendKeys("12/26");
    // 25 | click | id=ccardcvv | 
    driver.findElement(By.id("ccardcvv")).click();
    // 26 | type | id=ccardcvv | 123
    driver.findElement(By.id("ccardcvv")).sendKeys("123");
    // 27 | click | id=ccardname | 
    driver.findElement(By.id("ccardname")).click();
    // 28 | type | id=ccardname | Rodrigo Graça
    driver.findElement(By.id("ccardname")).sendKeys("Rodrigo Graça");
    // 29 | click | css=.w3-button | 
    driver.findElement(By.cssSelector(".w3-button")).click();
    // 30 | wait
    {
      Thread.sleep(500);
    }
    // 31 | see | h3 | Thank you for your reservation!
    {
      WebElement element = driver.findElement(By.cssSelector("h3"));
      assertNotNull(element, "Thank you message element is not found.");
      assertEquals("Thank you for your purchase today!", element.getText(), "Unexpected message content.");
    }
    // 32 | quit
    driver.quit();
  }
}
