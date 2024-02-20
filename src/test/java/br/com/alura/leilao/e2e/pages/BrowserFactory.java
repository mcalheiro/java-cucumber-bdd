package br.com.alura.leilao.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class BrowserFactory {

//	http://chromedriver.storage.googleapis.com/index.html
//	https://github.com/mozilla/geckodriver/releases	
//  Download latest versions and replace Paths in init functions below
	
	public WebDriver createWebDriver() {
		String webdriver = System.getProperty("browser", "firefox");
		switch (webdriver) {
			case "firefox":
				return initFirefoxDriver();
			case "chrome":
				return initChromeDriver();
			default:
				return new HtmlUnitDriver();
		}
	}

	private  WebDriver initChromeDriver() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Alura\\bdd-cucumber\\1821-bdd-cucumber-java-aula1\\drivers\\chromedriver.exe");
		return new ChromeDriver();
	}

	private  WebDriver initFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver",
				"D:\\Alura\\bdd-cucumber\\1821-bdd-cucumber-java-aula1\\drivers\\geckodriver.exe");
		return new FirefoxDriver();
	}
}
