package uk.co.mercedesbenz.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;



public class Steps {

    private ChromeDriver chromeDriver;
    private FirefoxDriver firefoxDriver;

    @Before()
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "drivers_resources/chromedriver.exe");
        chromeDriver = new ChromeDriver();
        //System.setProperty("webdriver.gecko.driver", "drivers_resources/geckodriver.exe");
        //firefoxDriver = new FirefoxDriver();


       //firefoxDriver.manage().window().maximize();
        chromeDriver.manage().window().maximize();
    }


    @Given("that I open Mercedes Benz United Kingdom market")
    public void that_I_open_Mercedes_Benz_United_Kingdom_market(){

        chromeDriver.get("https://www.mercedes-benz.co.uk");
        System.out.println(chromeDriver.getTitle());

        chromeDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        List<WebElement> listOfElements = chromeDriver.findElements(By.tagName("cmm-cookie-banner__content"));

        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
        js.executeScript( "document.querySelector(\"body > cmm-cookie-banner\").shadowRoot.querySelector(\"div > div > div.cmm-cookie-banner__content > div.toggle-buttons-wrapper > div > button.wb-button.wb-button--primary.wb-button--small.wb-button--accept-all\").click();" );

    }


    @When("I click in our cars")
    public void i_click_in_our_cars() {

        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
        js.executeScript("document.querySelector(\"body > div.root.responsivegrid > div > owc-header\").shadowRoot.querySelector(\"header > div > nav.owc-header__header-navigation > div > ul > li.owc-header-navigation-topic.owc-header-navigation-topic--desktop-nav.owc-header-navigation-topic__model-flyout > button > p\").click();");

    }


    @When("I select model Hatchbacks")
    public void i_select_model_hatchbacks() {

        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
        js.executeScript("document.querySelector(\"body > div.root.responsivegrid > div > owc-header\").shadowRoot.querySelector(\"header > div > nav.owc-header__header-navigation > div > ul > li.owc-header-navigation-topic.owc-header-navigation-topic--desktop-nav.owc-header-navigation-topic__model-flyout.owc-header-navigation-topic--selected > div > div.owc-header-flyout__innerContent > ul > li:nth-child(4) > a > p\").click();");

    }


    @When("I Mouse over the A Class model available and proceed to Build your car")
    public void i_mouse_over_the_a_class_model_available_and_proceed_to_build_your_car() {

        //TODO This piece of code is not considered best practice, but it is working. For the next versions, I will analyze how I get the mouse over.
        chromeDriver.get("https://www.mercedes-benz.co.uk/passengercars/mercedes-benz-cars/car-configurator.html/motorization/CCci/GB/en/A-KLASSE/KOMPAKT-LIMOUSINE\n");

    }


    @When("I filter by Fuel type Diesel")
    public void i_filter_by_fuel_type_diesel() {

        chromeDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
        js.executeScript("document.querySelector(\"body > div.root.responsivegrid > div > div > div > owcc-car-configurator\").shadowRoot.querySelector(\"div > cc-app-container > div > div.cc-app-container_main > div > div > div > div.cc-app-containercontent-selectables-container > cc-motorization > cc-motorization-filters-section > cc-motorization-filters > form > fieldset.cc-motorization-filter.cc-col-mq1-12.cc-col-mq4-8 > div.cc-motorization-filter_options > div:nth-child(2) > wb-checkbox-control > label > input\").click();");

    }


    @Then("I take and save a screenshot of the results")
    public void i_take_and_save_a_screenshot_of_the_results() {
        File screenshot = ((TakesScreenshot) chromeDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("C:\\projectScreenshots\\results.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Then("I save the value £ of the highest and lowest price results in a text file")
    public void i_save_the_value_£_of_the_highest_and_lowest_price_results_in_a_text_file() {

      //  document.querySelector("body > div.root.responsivegrid > div > div > div > owcc-car-configurator").shadowRoot.querySelector("div > cc-app-container > div > div.cc-app-container__main > div > div.cc-grid-container.ng-star-inserted > div > div.cc-app-container__content-selectables-container > cc-motorization > cc-motorization-comparison > div > div > div:nth-child(2) > div > cc-motorization-header > div > div > div.wb-type-copy.ng-star-inserted")
      //  document.querySelector("body > div.root.responsivegrid > div > div > div > owcc-car-configurator").shadowRoot.querySelector("div > cc-app-container > div > div.cc-app-container__main > div > div.cc-grid-container.ng-star-inserted > div > div.cc-app-container__content-selectables-container > cc-motorization > cc-motorization-comparison > div > div > div:nth-child(1) > div > cc-motorization-header > div > div > div.wb-type-copy.ng-star-inserted")
      //  document.querySelector("body > div.root.responsivegrid > div > div > div > owcc-car-configurator").shadowRoot.querySelector("div > cc-app-container > div > div.cc-app-container__main > div > div.cc-grid-container.ng-star-inserted > div > div.cc-app-container__content-selectables-container > cc-motorization > cc-motorization-comparison > div > div > div:nth-child(3) > div > cc-motorization-header > div > div > div.wb-type-copy.ng-star-inserted")
       // document.querySelector("body > div.root.responsivegrid > div > div > div > owcc-car-configurator").shadowRoot.querySelector("div > cc-app-container > div > div.cc-app-container__main > div > div.cc-grid-container.ng-star-inserted > div > div.cc-app-container__content-selectables-container > cc-motorization > cc-motorization-comparison > div > div > div:nth-child(4) > div > cc-motorization-header > div > div > div.wb-type-copy.ng-star-inserted")
      //  document.querySelector("body > div.root.responsivegrid > div > div > div > owcc-car-configurator").shadowRoot.querySelector("div > cc-app-container > div > div.cc-app-container__main > div > div.cc-grid-container.ng-star-inserted > div > div.cc-app-container__content-selectables-container > cc-motorization > cc-motorization-comparison > div > div > div:nth-child(5) > div > cc-motorization-header > div > div > div.wb-type-copy.ng-star-inserted")
      //  document.querySelector("body > div.root.responsivegrid > div > div > div > owcc-car-configurator").shadowRoot.querySelector("div > cc-app-container > div > div.cc-app-container__main > div > div.cc-grid-container.ng-star-inserted > div > div.cc-app-container__content-selectables-container > cc-motorization > cc-motorization-comparison > div > div > div:nth-child(6) > div > cc-motorization-header > div > div > div.wb-type-copy.ng-star-inserted")

                //6
      //  document.querySelector("body > div.root.responsivegrid > div > div > div > owcc-car-configurator").shadowRoot.querySelector("div > cc-app-container > div > div.cc-app-container__main > div > div.cc-grid-container.ng-star-inserted > div > div.cc-app-container__content-selectables-container > cc-motorization > cc-motorization-comparison > div > cc-motorization-comparison-status > div")

   //     JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
    //    js.executeScript("document.querySelector(\"body > div.root.responsivegrid > div > div > div > owcc-car-configurator\").shadowRoot.querySelector(\"div > cc-app-container > div > div.cc-app-container__main > div > div.cc-grid-container.ng-star-inserted > div > div.cc-app-container__content-selectables-container > cc-motorization > cc-motorization-comparison > div > cc-motorization-comparison-status > div\")\n");
      //    System.out.println("VALOR TOTAL " + js);

        /*

prices.get(0); // valor mais barato
		prices.get(prices.size()-1); // valor mais alto



List<Long> prices = new ArrayList<>();
		// pegar a div pai
		// pra cada div filha pegar o price
		Long price = Long.valueOf("valor do preço no site");
		for () {
			prices.add(price);
		}
		prices.stream().sorted().collect(Collectors.toList());



		import java.util.*;
import java.util.stream.*;
public class SortListExample1
{
public static void main(String[] args)
{
//returns a list view
List<String> slist = Arrays.asList("Tanu", "Kamal", "Suman", "Lucky", "Bunty", "Amit");
List<String> sortedList = slist.stream().sorted().collect(Collectors.toList());
sortedList.forEach(System.out::println);
}
}


         */


    }




}
