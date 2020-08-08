import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotFoundProduct {

	public static void main(String[] args) throws IOException {
		//WebDriver driver;
		System.setProperty("webdriver.gecko.driver", "C:\\Udaya\\Softwares\\Selenium_Software\\gecko\\geckodriver.exe");
		File f= new File("C:\\Udaya\\Selenium\\Builder\\Excel\\keywords.xls");
		String TestFile = "C:\\Udaya\\Selenium\\Builder\\Excel\\KeyAndValue.txt";
		File FC = new File(TestFile);
		FC.createNewFile();
		FileWriter FW = new FileWriter(TestFile);
		BufferedWriter BW = new BufferedWriter(FW);
		FileInputStream fis=new FileInputStream(f);
		HSSFWorkbook wb=new HSSFWorkbook(fis);
		HSSFSheet s =wb.getSheetAt(0);
		Iterator<Row> iterator=s.iterator();
		String product = null;
		while(iterator.hasNext()){
			Row row=iterator.next();
			Iterator<Cell> cellIterator = row.cellIterator(); 
			while (cellIterator.hasNext()) {
				WebDriver driver=new FirefoxDriver();
				driver.get("https://www.builders.co.za");
				Cell cell = cellIterator.next(); 
				String cols=cell.getStringCellValue().trim();
				/*
				 * List<String> keywordslist= new ArrayList<String>();
				 * keywordslist.add("Hours");
		keywordslist.add("9kg gas refills");
				 */
				//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.id("searchText-overlay")).click();
				driver.findElement(By.id("new-search")).sendKeys(cols);
				Actions a =new Actions(driver);
				a.sendKeys(Keys.ENTER).build().perform();
				WebDriverWait wait=new WebDriverWait(driver, 05);
				wait.until(ExpectedConditions.titleContains("Not Found | Builders South Africa"));
				//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				String expectedTitle = driver.getTitle();
				//System.out.println(expectedTitle);
				String actualTitle = "Not Found | Builders South Africa";
				if(expectedTitle.contentEquals(actualTitle)) {
					System.out.println(expectedTitle);
				}
				BW.append(cols + " : "+ expectedTitle ); 
				BW.newLine();
				product = null;
				driver.close();
	}

}
		BW.close();
	}
}
