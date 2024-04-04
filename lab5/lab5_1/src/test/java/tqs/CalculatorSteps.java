package tqs;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorSteps {

    static final Logger log = getLogger(lookup().lookupClass());

    private Calculator calc;

    @Given("a calculator I just turned on$")
    public void setup() {
        calc = new Calculator();
    }

    @When("I add {int} and {int}")
    public void add(int arg1, int arg2) {
        log.debug("Adding {} and {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("+");
    }

    @When("I substract {int} to {int}")
    public void substract(int arg1, int arg2) {
        log.debug("Substracting {} and {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("-");
    }

    @When ("I multiply {int} by {int}")
    public void multiply(int arg1, int arg2) {
        log.debug("Multiplying {} and {}", arg1,arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("*");
    }


    @When("I divide {int} and {int}")
    public void divide(int arg1, int arg2) {
        log.debug("Dividing {} and {}", arg1,arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("/");
    }


    @Then("the result is {int}")
    public void the_result_is(double expected) {
        Number value = calc.value();
        log.debug("Result: {} (expected {})", value, expected);
        assertEquals(expected, value);
    }

    @Then("the result is NaN")
    public void verifyNaNResult() {
        Number result = calc.value();
        log.debug("ERROR");
        assertTrue(Double.isNaN(result.doubleValue()));
    }

    

}