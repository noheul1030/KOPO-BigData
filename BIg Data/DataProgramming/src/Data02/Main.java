package Data02;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

//public class Main {
//	public static WebDriver driver;
//	public static String base_url = "http://www.naver.com";
//	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
//	public static final String WEB_DRIVER_PATH = "C:\\Users\\노을\\Downloads\\chromedriver_win32\\chromedriver.exe";
//
//	public static void main(String[] args) {
//		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
//		// driver = new ChromeDriver();
//
//		// crawl();
//		ChromeOptions chromeOptions = new ChromeOptions();
//
//		chromeOptions.addArguments("--remote-allow-origins=*"); // 해당 부분 추가
//
//		ChromeDriver driver = new ChromeDriver(chromeOptions);
////		driver.get(base_url);
//		driver.get("http://192.168.23.51:8083/Board/boardList.jsp");
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String newButton = "/html/body/div[1]/button";
//		driver.findElement(By.xpath(newButton)).click();
//		System.out.println(driver.getPageSource());
//	}
//
//	public static void crawl() {
//		try {
////			driver.get(base_url);
////			System.out.println(driver.getPageSource());
//
//			driver.get("http://192.168.23.51:8083/Board/boardList.jsp");
//			Thread.sleep(1000);
//			String newButton = "/html/body/div[1]/button";
//			driver.findElement(By.xpath(newButton)).click();
//			System.out.println(driver.getPageSource());
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			driver.close();
//		}
//	}
//
//}

//public class Main extends Thread {
//
//	   public static WebDriver driver;
//	   public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
//	   public static final String WEB_DRIVER_PATH = "C:\\Users\\\\노을\\Downloads\\chromedriver_win32\\chromedriver.exe";
//
//	   public static void main(String[] args) throws InterruptedException {
//
//	      System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
//	      ChromeOptions chromeOptions = new ChromeOptions();
//	      chromeOptions.addArguments("--remote-allow-origins=*"); // 해당 부분 추가
//	      ChromeDriver driver = new ChromeDriver(chromeOptions);
//
//	      String url = "https://www.weather.go.kr/w/index.do#dong/4113552000";
//	      driver.get(url);
//
//	      Thread.sleep(100);
//
//	      String buttonWrite = "/html/body/div[1]/button";
//	      driver.findElement(By.xpath(buttonWrite)).click();
//
//	      String Title = "/html/body/form/table[1]/tbody/tr[2]/td[2]/input";
//	      driver.findElement(By.xpath(Title)).click();
//
//	      driver.findElement(By.xpath(Title)).sendKeys("집에가고싶다2"); // 제목글쓰기
//
//	      String Content = "/html/body/form/table[1]/tbody/tr[4]/td[2]/textarea";
//	      driver.findElement(By.xpath(Content)).click();
//
//	      driver.findElement(By.xpath(Content)).sendKeys("후하후하"); // 내용글쓰기
//
//	      String buttonWriteFinish = "/html/body/form/table[2]/tbody/tr/td/button[2]";
//	      driver.findElement(By.xpath(buttonWriteFinish)).click();
//
//	      String buttonWriteFinishgoMocloc = "/html/body/table[2]/tbody/tr/td/button[1]";
//	      driver.findElement(By.xpath(buttonWriteFinishgoMocloc)).click();
//	   }
//	}

public class Main extends Thread {

	public static WebDriver driver;
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\Users\\노을\\Downloads\\chromedriver_win32\\chromedriver.exe";

	public static void main(String[] args) throws InterruptedException {

		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		ChromeDriver driver = new ChromeDriver(chromeOptions);

		driver.get("https://www.weather.go.kr/w/index.do#dong/2817769000");

		List<Weather> weathers = new ArrayList<>();

		int ulcount = 1;
		int divcount = 1;
		int i = 1;
		int limit = 25;

		while (i < limit) {
			String xpath = "/html/body/div[3]/section/div/div[2]/div[4]/div[2]/div/div[3]/div[1]/div[1]/div[3]/div[2]/div[1]/div["+divcount+"]/div/div[2]/ul["+ ulcount +"]/li[4]/span[2]";

			Thread.sleep(500);

			List<WebElement> weatherRows = driver.findElements(By.xpath(xpath));
			
			if (weatherRows.isEmpty()) {
				ulcount = 1;
				divcount++;
			} else {
				i++;
				ulcount++;
				System.out.println(weatherRows);

				for (WebElement weatherRow : weatherRows) {
					String temperatures = weatherRow.getText();
					String numericValue = temperatures.replaceAll("\\D+", "");
					System.out.println(numericValue);
					if (!numericValue.isEmpty()) {
						String temperatureValue = numericValue;
						Weather weather = new Weather(temperatureValue);
						weathers.add(weather);
					}
				}
			}
		}

		int MIN = Integer.MAX_VALUE;
		int MAX = Integer.MIN_VALUE;
		int SUM = 0;

		// weathers List에 저장된 값을 확인 또는 사용할 수 있습니다.
		for (Weather weather : weathers) {
			if (Integer.parseInt(weather.getTemperatures()) < MIN) {
				MIN = Integer.parseInt(weather.getTemperatures());
			}
			if (Integer.parseInt(weather.getTemperatures()) > MAX) {
				MAX = Integer.parseInt(weather.getTemperatures());
			}
			SUM += Integer.parseInt(weather.getTemperatures());
		}
		System.out.println(" - 24 시간 기준 -");
		System.out.printf(" 최저 기온 : %d\t 최대 기온 : %d \n", MIN, MAX);
		System.out.printf(" 평균 기온 : %.2f", (double) SUM / (limit-1));
		
		driver.close();
		driver.quit();

		try {
			File file = new File("lib/text.csv");
			file.createNewFile();
			
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getPath()),"UTF-8"));

			for (Weather weather : weathers) {
				output.write(weather.getTemperatures()+",");				
			}
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
