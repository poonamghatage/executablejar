package com.javapackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class definition {

    private WebDriver driver;

     static void openbrowser() throws IOException

    {
        FileReader reader= null;
        try {
            reader = new FileReader("E:\\executablejar\\src\\com\\javapackage\\config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Properties p=new Properties();
        try {
            p.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String driver_value=p.getProperty("driver");
        System.out.println(driver_value);
        definition d1 = new definition();
        if ("firefox".equals(driver_value)) {
            d1.firefox();
        } else if ("chrome".equals(driver_value)) {
            d1.chrome();
        } else {
            System.err.println("Invalid browser input. Please use either chrome or firefox");
        }
    }




    private void chrome() throws IOException {

        String s = System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
        this.driver = new ChromeDriver();
        open_url_in_chrome();

    }

    private void firefox()
    {

        System.setProperty("webdriver.gecko.driver","C:\\Users\\perennial\\IdeaProjects\\Testing\\driver\\geckodriver.exe");
        this.driver = new FirefoxDriver();
        open_url_in_firefox();

    }




    private void open_url_in_chrome() throws IOException {


        FileReader reader= null;
        reader = new FileReader("E:\\executablejar\\src\\com\\javapackage\\config.properties");
        Properties p=new Properties();
        p.load(reader);


        driver.get(p.getProperty("url"));
        System.out.println("Test 2:Open URL");
        driver.findElement(By.id("identifierId")).sendKeys(p.getProperty("uname"));
        driver.findElement(By.cssSelector("#identifierNext > div > button")).click();
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.name("password")).sendKeys(p.getProperty("pass"));
        driver.findElement(By.cssSelector("#passwordNext > div > button > div.VfPpkd-RLmnJb")).click();
        driver.findElement(By.cssSelector("#gb > div.gb_Td.gb_de.gb_3d > div.gb_7d.gb_Ta.gb_Sd.gb_ae > div.gb_3e > div.gb_Oa.gb_dd.gb_Fg.gb_i.gb_vb.gb_Uf > div > a")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        String str = driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[4]/div[2]/div[2]/div[1]")).getText();
        String str1="Poonam Ghatage";
        if(str.equalsIgnoreCase(str1))
        {
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            driver.findElement(By.cssSelector("#gb_71")).click();
        }

    }

    private void open_url_in_firefox(){
        firefox();
        WebDriver driver = (WebDriver) new FirefoxDriver();
        driver.get("https://accounts.google.com/signin");
     }

}
