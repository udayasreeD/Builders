
package firstProject;
import java.io.BufferedWriter;
import java.io.File; 
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
public class ChromeProductTest {
	public static void main(String[] args) throws Exception {
		//WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "C:\\Udaya\\Softwares\\Selenium_Software\\chrome\\chromedriver.exe");
		/*
		 * File file = new
		 * File("C:\\Udaya\\Softwares\\Selenium_Software\\phontom\\phantomjs.exe");
		 * System.setProperty("phantomjs.binary.path", file.getAbsolutePath()); driver=
		 * new PhantomJSDriver();
		 */
		File f= new File("C:\\Udaya\\Selenium\\Builder\\Excel\\KeywordsInChrome.xls");
		String TestFile = "C:\\Udaya\\Selenium\\Builder\\Excel\\KeyAndValueInChrome.txt";
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
				if(cols instanceof String) {
				driver.findElement(By.id("searchText-overlay")).click();
				driver.findElement(By.id("new-search")).sendKeys(cols);
				Actions a =new Actions(driver);
				a.sendKeys(Keys.ENTER).build().perform();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				try {
				product = driver.findElement(By.xpath("//*[@id=\"search-control-left\"]/span[3]")).getAttribute("innerHTML");
				System.out.println(cols + " : " + product);
				String newValue = product.replace(" &nbsp;","");

				Integer val = Integer.valueOf(newValue);

				if(val == 0) {
					System.out.println(cols + " : " + newValue);

				}
				BW.append(cols + " : "+ val ); 
				BW.newLine();
				BW.flush();
				}
				catch (Exception e) {
					System.out.println("element not found " + cols);
				}
				product = null;
				driver.close();
				}
				else 
				{
					driver.close();
				}
			}
		}
		BW.close();
	}

}
