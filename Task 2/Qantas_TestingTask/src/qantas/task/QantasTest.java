package qantas.task;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class QantasTest {

	@Test
	public void validateSite() {
		WebDriver driver = getChromeDriver();
		driver.get("https://www.qantas.com/au/en/travel-info/check-in.html");
		// checking for title
		Assert.assertEquals(driver.getTitle(), "Check-in | Qantas AU");
		driver.findElement(By.xpath("//input[@aria-label='Booking or voucher reference field']")).sendKeys("QWE456");
		driver.findElement(By.xpath("//input[@aria-label='Last name field']")).sendKeys("LastName");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		// explicit wait
		WebDriverWait d = new WebDriverWait(driver, 5);
		d.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form-validation-summary']")));
		// checking for error message
		WebElement msg = driver.findElement(By.xpath("//div[@class='form-validation-summary']//div[2]//strong"));
		String text = msg.getText();
		Assert.assertEquals(text, "It looks like something went wrong there");

	}

	/**
	 * @return chrome web driver.
	 */
	private WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\driver\\chromedriver_ver77.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
}