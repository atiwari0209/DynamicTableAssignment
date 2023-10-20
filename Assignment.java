package SeleniumProject.Webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;
import org.json.JSONObject;
import org.json.JSONArray;  

import java.util.List;

public class Assignment {
	public static void main(String[] args) throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
		driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div/div[3]/details/summary")).click();
        
        String jsonData = "[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, {\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, {\"name\":\r\n"
        		+ "\"Sara\", \"age\" : 42, \"gender\": \"female\"}, {\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, {\"name\":\r\n"
        		+ "\"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]";
        JSONArray jsonArray = new JSONArray(jsonData);
        
        
        driver.findElement(By.xpath("//*[@id=\"jsondata\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"jsondata\"]")).sendKeys(jsonData);
        driver.findElement(By.id("refreshtable")).click();
        
        WebElement table = driver.findElement(By.id("dynamictable"));

        List<WebElement> allRows = table.findElements(By.tagName("tr"));
	
        for(int i=1;i<allRows.size();i++) {
        	JSONObject rowJson = jsonArray.getJSONObject(i-1);
        	String[] rowDataValue = {rowJson.getString("name"), String.valueOf(rowJson.getInt("age")),rowJson.getString("gender")};
        	WebElement currentRow = allRows.get(i);
        	List<WebElement> cells = currentRow.findElements(By.tagName("td"));
        	for(int j=0;j<cells.size();j++) {
        		WebElement cell = cells.get(j);
        		String cellText = cell.getText();
        		System.out.print(cellText + " ");
        		assertEquals(rowDataValue[j], cellText);
        	}
        	System.out.println();
        }
        
}
}
