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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
        js.executeScript("document.querySelector(\"body > div.root.responsivegrid > div > div > div > owcc-car-configurator\").shadowRoot.querySelector(\"div > cc-app-container > div > div.cc-app-container__main > div > div > div > div.cc-app-container__content-selectables-container > cc-motorization > cc-motorization-filters-section > cc-motorization-filters > form > fieldset.cc-motorization-filter.cc-col-mq1-12.cc-col-mq4-8 > div.cc-motorization-filter__options > div:nth-child(2) > wb-checkbox-control > label > input\").click();");

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



        List<Long> prices = new ArrayList<>();


        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
        String valor1 = (String) js.executeScript( "document.querySelector(\"body > div.root.responsivegrid > div > div > div > owcc-car-configurator\").shadowRoot.querySelector(\"div > cc-app-container > div > div.cc-app-container_main > div > div.cc-grid-container.ng-star-inserted > div > div.cc-app-container_content-selectables-container > cc-motorization > cc-motorization-comparison > div > div > div:nth-child(1) > div > cc-motorization-header > div > div > div.wb-type-copy.ng-star-inserted\").innerText" );
        valor1 = valor1.replace("£","");
        Long price1 = Long.valueOf( valor1 );
        prices.add( price1 );


        String valor2 = (String) js.executeScript( "document.querySelector(\"body > div.root.responsivegrid > div > div > div > owcc-car-configurator\").shadowRoot.querySelector(\"div > cc-app-container > div > div.cc-app-container_main > div > div.cc-grid-container.ng-star-inserted > div > div.cc-app-container_content-selectables-container > cc-motorization > cc-motorization-comparison > div > div > div:nth-child(2) > div > cc-motorization-header > div > div > div.wb-type-copy.ng-star-inserted\").innerText" );
        valor2 = valor2.replace("£","");
        Long price2 = Long.valueOf( valor2 );
        prices.add( price2 );


        String valor3 = (String) js.executeScript( "document.querySelector(\"body > div.root.responsivegrid > div > div > div > owcc-car-configurator\").shadowRoot.querySelector(\"div > cc-app-container > div > div.cc-app-container_main > div > div.cc-grid-container.ng-star-inserted > div > div.cc-app-container_content-selectables-container > cc-motorization > cc-motorization-comparison > div > div > div:nth-child(3) > div > cc-motorization-header > div > div > div.wb-type-copy.ng-star-inserted\").innerText" );
        valor3 = valor3.replace("£","");
        Long price3 = Long.valueOf( valor3 );
        prices.add( price3 );

        String valor4 = (String) js.executeScript( "document.querySelector(\"body > div.root.responsivegrid > div > div > div > owcc-car-configurator\").shadowRoot.querySelector(\"div > cc-app-container > div > div.cc-app-container_main > div > div.cc-grid-container.ng-star-inserted > div > div.cc-app-container_content-selectables-container > cc-motorization > cc-motorization-comparison > div > div > div:nth-child(4) > div > cc-motorization-header > div > div > div.wb-type-copy.ng-star-inserted\").innerText" );
        valor4 = valor4.replace("£","");
        Long price4 = Long.valueOf( valor4 );
        prices.add( price4 );

        String valor5 = (String) js.executeScript( "document.querySelector(\"body > div.root.responsivegrid > div > div > div > owcc-car-configurator\").shadowRoot.querySelector(\"div > cc-app-container > div > div.cc-app-container_main > div > div.cc-grid-container.ng-star-inserted > div > div.cc-app-container_content-selectables-container > cc-motorization > cc-motorization-comparison > div > div > div:nth-child(5) > div > cc-motorization-header > div > div > div.wb-type-copy.ng-star-inserted\").innerText" );
        valor5 = valor5.replace("£","");
        Long price5 = Long.valueOf( valor5 );
        prices.add( price5 );

        String valor6 = (String) js.executeScript( "document.querySelector(\"body > div.root.responsivegrid > div > div > div > owcc-car-configurator\").shadowRoot.querySelector(\"div > cc-app-container > div > div.cc-app-container_main > div > div.cc-grid-container.ng-star-inserted > div > div.cc-app-container_content-selectables-container > cc-motorization > cc-motorization-comparison > div > div > div:nth-child(6) > div > cc-motorization-header > div > div > div.wb-type-copy.ng-star-inserted\").innerText" );
        valor6 = valor6.replace("£","");
        Long price6 = Long.valueOf( valor6 );
        prices.add( price6 );

        prices.stream().sorted().collect( Collectors.toList() );

        prices.get(0);
        prices.get(prices.size()-1); 

        System.out.println("The lowest price: "+prices.get(0));
        System.out.println("The highest price: "+prices.get(prices.size()-1));


    }



}
