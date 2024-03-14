package lab4_3;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.Dimension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SeleniumJupiter.class)
public class PageObjTest {
    private WebDriver driver;

    @FindBy(name = "fromPort")
    private WebElement fromPortButton;

    @FindBy(xpath = "//option[. = 'San Diego']")
    private WebElement sanDiegoOption;

    @FindBy(name = "toPort")
    private WebElement toPortButton;

    @FindBy(xpath = "//option[. = 'Dublin']")
    private WebElement dublinOption;

    @FindBy(css = ".btn-primary")
    private WebElement buttonFind;

    @FindBy(css="tr:nth-child(4) .btn")
    private WebElement buttonChoose;

    @FindBy(id = "inputName")
    private WebElement nameInput;

    @FindBy(id = "address")
    private WebElement addressInput;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "state")
    private WebElement stateInput;

    @FindBy(id = "zipCode")
    private WebElement zipCodeInput;

    @FindBy(name = "cardType")
    private WebElement cardType;

    @FindBy(xpath = "//option[. = 'American Express']")
    private WebElement cardTypeOption;

    @FindBy(id = "creditCardNumber")
    private WebElement cardNumber;

    @FindBy(id = "creditCardMonth")
    private WebElement cardMonth;

    @FindBy(id = "creditCardYear")
    private WebElement cardYear;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCard;

    @FindBy(css = ".btn-primary")
    private WebElement buttonFinish;

    @FindBy(css=".checkbox")
    private WebElement buttonRemember;

    @Test
    public void translatedTest() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();  
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        driver.get("https://blazedemo.com/");

        PageFactory.initElements(driver, this);

        fromPortButton.click();
        sanDiegoOption.click();

        toPortButton.click();
        dublinOption.click();

        buttonFind.click();

        buttonChoose.click();

        nameInput.sendKeys("Rodrigo Graça");
        addressInput.sendKeys("Venda Nova");
        cityInput.sendKeys("Tomar");
        stateInput.sendKeys("Santarém");
        zipCodeInput.sendKeys("1780836");

        cardType.click();
        cardTypeOption.click();

        cardNumber.sendKeys("8612538962");
        cardMonth.sendKeys("10");
        cardYear.sendKeys("2026");

        nameOnCard.sendKeys("Rodrigo Graça");

        buttonRemember.click();
        
        buttonFinish.click();
    }
}