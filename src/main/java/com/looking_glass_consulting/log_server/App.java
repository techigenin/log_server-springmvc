package com.looking_glass_consulting.log_server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.looking_glass_consulting.log_server.entity.Call;

public class App 
{
    public static void main( String[] args )
    {
    	Properties props = new Properties();
    	String url, user, pass;
    	
    	
    	try (FileInputStream fis = new FileInputStream("../persistence-mysql.properties")) {
    		props.load(fis);
    		
    		url = props.getProperty("jdbc.url");
    		user = props.getProperty("jdbc.user");
    		pass = props.getProperty("jdbc.password");
    		
    		try  {
    			Connection myConn =
						DriverManager.getConnection(url, user, pass);
    			
				System.out.println("success! " + myConn.toString());
    		} catch (Exception exc) {
    			exc.printStackTrace();
    		}
    	}
    	catch (FileNotFoundException exc) {
    		System.out.println("could not find props file");
    	}
    	catch (IOException exc) {
    		exc.printStackTrace();
    	}    	
    }
}
