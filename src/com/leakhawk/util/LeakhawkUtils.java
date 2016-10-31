package com.leakhawk.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.leakhawk.filter.ContextFilterComponent;

public class LeakhawkUtils {

	public static Properties properties = new Properties();
	
	private static SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd 'at' hh:mm:ss");
	
	private static SimpleDateFormat sft = new SimpleDateFormat ("yyyy.MM.dd_hh.mm");

	// Reading the configuration file
	public static void readConfigFile() {
		
		InputStream input = null;
		try {
			System.out.println("************* Reading the configuration file ***************");
			
			input = LeakhawkUtils.class.getClassLoader()
                    .getResourceAsStream("config.properties");
			
			//input = new FileInputStream("config.properties");

			// load a properties file
			properties.load(input);
			ContextFilterComponent.loadRegExpList(18);
			
			// get the property value and print it out
			//System.out.println( "Key Word List : " + keyWordList );
			//System.out.println( "Allowed Syntax List : " + allowedSyntaxList );	

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// convert InputStream to String
	public static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	public static void printDateTime(){
		
	     Date dNow = new Date( );	      
	     System.out.println("Current Time: " + ft.format(dNow));
	}
	
	public static String getCurrentDateTime(){
		
		Date dNow = new Date( );
		return sft.format(dNow);
	}
}