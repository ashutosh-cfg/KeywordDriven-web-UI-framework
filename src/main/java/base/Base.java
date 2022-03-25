package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	public WebDriver driver;
	public Properties prop;
	String projectpath = System.getProperty("user.dir");

	public WebDriver init_driver(String browserName) {

		System.out.println(projectpath);
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectpath +"/src/main/resources/Driver/chromedriver.exe");
			if(prop.getProperty("headless").equals("yes")) {
				// headless mode
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
			}
			else {
				driver= new ChromeDriver();
			}
		}
		return driver;
	}

	public Properties init_properties(){
		prop= new Properties();
		FileInputStream ip = null;
		try {
			ip = new FileInputStream(projectpath+"/src/main/resources/keywordConfig/config.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
}
