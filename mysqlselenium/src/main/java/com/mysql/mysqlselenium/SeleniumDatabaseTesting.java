package com.mysql.mysqlselenium;

import java.sql.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumDatabaseTesting 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println( "Hello World!2" );
        
        //Testdata
        String email = "abc@gmail.com";
        String zipcode = "222222";
        String firstname = "Lucky";
        String lastname = "Singh";
        
        //Input testdata using Selenium
        WebDriver driver = new FirefoxDriver();
        driver.get("https://mailing.dollartree.com/user/signup.jsp");
        driver.findElement(By.xpath(".//*[@id='emailAddress']")).sendKeys(email);
        driver.findElement(By.xpath(".//*[@id='zipCode']")).sendKeys(zipcode);
        driver.findElement(By.xpath(".//*[@id='firstName']")).sendKeys(firstname);
        driver.findElement(By.xpath(".//*[@id='lastName']")).sendKeys(lastname);        
        driver.findElement(By.xpath(".//*[@id='subscribeForm']/div[2]/input[4]")).click();
        driver.findElement(By.xpath(".//*[@id='subscribeForm']/div[3]/input[2]")).click();
        
        //Connecting to database
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String databaseName = "bantidatabase";
        String username = "root";
        String password = "password1";
        
        try{
        	Class.forName("com.mysql.jdbc.Driver");
        	conn = DriverManager.getConnection(url+databaseName, username, password);
        	//String sqlQuery = "Select * from bantidatabase.employeeinfo";
        	String sqlQuery = "Select * from bantidatabase.employee_details order by signupid desc";
        	Statement statement = conn.createStatement();
        	ResultSet result = statement.executeQuery(sqlQuery);
        	result.next();
        	
        	System.out.println(result.getString("email"));
        	System.out.println(result.getString("zipcode"));
        	System.out.println(result.getString("firstname"));
        	System.out.println(result.getString("lastname"));
        	
        	if(!result.getString("email").equals(email)){
        		System.out.println("email id stored wrong");
        	}
        	if(!result.getString("zipcode").equals(zipcode)){
        		System.out.println("zipcode id stored wrong");
        	}
        	if(!result.getString("firstname").equals(firstname)){
        		System.out.println("firstname id stored wrong");
        	}
        	if(!result.getString("lastname").equals(lastname)){
        		System.out.println("lastname id stored wrong");
        	}
        	
        }catch(Exception e){
        	System.out.println(e);
        }finally{
        	if(conn != null){
        		conn = null;
        	}
        }
        driver.quit();
        
    }
}
