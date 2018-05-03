package com.demo.springpoc.systest.cucumber;

import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginFeatureTest {
	WebDriver driver = null;

	@Given("^I open the browser$")
	public void openBrowser() {
		ClassLoader classLoader = LoginFeatureTest.class.getClassLoader();
		System.setProperty("webdriver.chrome.driver", classLoader.getResource("chromedriver.exe").getPath());
		driver = new ChromeDriver();
	}

	@When("^I access the website$")
	public void goToFacebook() {
		driver.navigate().to("http://localhost:8081/springpoc/");
	}

	@Then("^I log into the application$")
	public void loginButton() {
		if (driver.findElement(By.id("loginBtn")).isEnabled()) {
			driver.findElement(By.id("username")).sendKeys("a");
			driver.findElement(By.id("password")).sendKeys("a");

			driver.findElement(By.id("loginBtn")).click();

			assertEquals("Successful Login Failed!", StringUtils.equals(driver.getTitle(), "Success!"), true);
		} else {
			fail("Login screen failed to load.");
		}

		driver.close();
	}
}
