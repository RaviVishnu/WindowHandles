package com.win;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WindowHandles {

	
	public static void main(String[] args) throws InterruptedException {

		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("remote-allow-origins=*");

		WebDriver driver = new ChromeDriver(opt);
		driver.get("https://www.google.com/");
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		driver.switchTo().frame("callout");
		WebElement nothanks = driver.findElement(By.xpath("//button[@class='M6CB1c rr4y5c']"));
		nothanks.click();
		driver.switchTo().parentFrame();

		String parent = driver.getWindowHandle();

		WebElement w = driver.findElement(By.xpath("//a[@aria-label='Google apps']"));
		w.click();
		driver.switchTo().frame("app");
//		driver.switchTo().frame(1);
//		driver.switchTo().alert().accept();

		List<WebElement> li = driver.findElements(By.xpath("//ul[@class='ngVsM u4RcUd']"));
		for (int i = 0; i < li.size(); i++) {
			WebElement list = li.get(i);
			String text = list.getText();
			System.out.println(text);
		}

		List<WebElement> li2 = driver.findElements(By.xpath("//ul[@class='ngVsM L2gNYe']"));
		for (WebElement b : li2) {

			System.out.println("===============================");
			String text = b.getText();
			System.out.println(text);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		WebElement more = driver.findElement(By.className("NQV3m"));
		more.click();

		System.out.println("+++++++++++++++++++++++++++++++++++");

		Set<String> childWin = driver.getWindowHandles();

		for (String x : childWin) {

			if (!parent.equals(x))
				driver.switchTo().window(x);

		}

		WebElement ok = driver.findElement(By.xpath("(//a[@role='button'])[1]"));
		ok.click();

//		driver.switchTo().parentFrame();
		driver.switchTo().defaultContent();

		WebElement allproduct = driver.findElement(By.xpath("(//a[@data-g-cta_url='#all-products'])[2]"));
		allproduct.click();

		//
		driver.switchTo().window(parent);

		// driver.quit();
	}
	
	
}
