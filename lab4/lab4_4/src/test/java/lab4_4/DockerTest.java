package lab4_4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.seljup.BrowserType;
import io.github.bonigarcia.seljup.DockerBrowser;

@ExtendWith(SeleniumJupiter.class)
public class DockerTest {

    WebDriver driver;

    @BeforeEach
    public void setUp(@DockerBrowser(type = BrowserType.CHROME) ChromeDriver dockerDriver) {
        this.driver = dockerDriver;
    }

    @Test
    public void test() {
        driver.get("https://blazedemo.com/");

        WebElement dropdown = driver.findElement(By.name("fromPort"));
        dropdown.findElement(By.xpath("//option[. = 'San Diego']")).click();

        WebElement element = driver.findElement(By.name("fromPort"));
        Actions builder = new Actions(driver);
        builder.moveToElement(element).clickAndHold().perform();
        builder.moveToElement(element).perform();
        builder.moveToElement(element).release().perform();

        dropdown = driver.findElement(By.name("toPort"));
        dropdown.findElement(By.xpath("//option[. = 'Dublin']")).click();

        element = driver.findElement(By.name("toPort"));
        builder = new Actions(driver);
        builder.moveToElement(element).clickAndHold().perform();
        builder.moveToElement(element).perform();
        builder.moveToElement(element).release().perform();

        driver.findElement(By.cssSelector(".btn-primary")).click();
        driver.findElement(By.cssSelector("tr:nth-child(4) .btn")).click();
        driver.findElement(By.id("inputName")).click();
        driver.findElement(By.id("inputName")).sendKeys("Rodrigo Graça");
        driver.findElement(By.id("address")).click();
        driver.findElement(By.id("address")).sendKeys("Venda Nova");
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("Tomar");
        driver.findElement(By.id("state")).click();
        driver.findElement(By.id("state")).sendKeys("Santarém");
        driver.findElement(By.id("zipCode")).click();
        driver.findElement(By.id("zipCode")).sendKeys("4282818");

        dropdown = driver.findElement(By.id("cardType"));
        dropdown.findElement(By.xpath("//option[. = 'American Express']")).click();

        element = driver.findElement(By.id("cardType"));
        builder = new Actions(driver);
        builder.moveToElement(element).clickAndHold().perform();
        builder.moveToElement(element).perform();
        builder.moveToElement(element).release().perform();

        driver.findElement(By.id("creditCardNumber")).click();
        driver.findElement(By.id("creditCardNumber")).sendKeys("371892604607");
        driver.findElement(By.id("creditCardMonth")).click();
        driver.findElement(By.id("creditCardMonth")).sendKeys("10");
        driver.findElement(By.id("creditCardYear")).click();
        driver.findElement(By.id("creditCardYear")).sendKeys("2026");
        driver.findElement(By.id("nameOnCard")).click();
        driver.findElement(By.id("nameOnCard")).sendKeys("Rodrigo Graça");
        driver.findElement(By.id("rememberMe")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }
}
