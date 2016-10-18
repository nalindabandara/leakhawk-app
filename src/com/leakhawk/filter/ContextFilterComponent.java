package com.leakhawk.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.leakhawk.filter.regex.RegExpressionHandler;
import com.leakhawk.main.LeakhawkManager;
import com.leakhawk.model.FeedEntry;
import com.leakhawk.util.LeakhawkUtils;



public class ContextFilterComponent {

	public static List<RegExpressionHandler> regExpHandlerList;
	
	private List<FeedEntry> originalEntryList;

	private List<FeedEntry> filteredEntryList = new ArrayList<FeedEntry>();

	public ContextFilterComponent(List<FeedEntry> list) {

		this.originalEntryList = list;
	}
	
	public ContextFilterComponent(){
		
	}
	
	public static void main(String[] args ){
		
		ContextFilterComponent cf = new ContextFilterComponent();
		cf.regExpTestDrive();
	}
	
	public static void loadRegExpList( int rgexpCount ){
		
		regExpHandlerList = new ArrayList<RegExpressionHandler>();		
		for( int i = 1; i <= rgexpCount; i++ ){
			regExpHandlerList.add( new RegExpressionHandler( ("regexp" + i), LeakhawkUtils.properties.getProperty("regexp" + i) ) );
		}					
	}
	
	public void applyContextFilter(){
		
		if( LeakhawkManager.isApplyingContextFilter){
			
			for( FeedEntry entry : originalEntryList ){	
								
				if( regExpressionMatched( entry ) ){
					
					filteredEntryList.add(entry);
					System.out.println("************************************  Context Filter Match Fount ************************************");
					System.out.println("FILE NAME : " + entry.getEntryFileName());
					System.out.print( entry.toString());
					System.out.println("*****************************************************************************************************");
				}
			}

		} else {
			if( originalEntryList != null && originalEntryList.size() > 0 ){
				filteredEntryList.addAll( originalEntryList );
			}	
		}
	}
	
	private static boolean regExpressionMatched( FeedEntry entry ){
				
		BufferedReader reader = null;		
		try {
															
		    URL my_url = new URL(entry.getScrapperUrl());
			reader = new BufferedReader(new InputStreamReader( my_url.openStream() ));			   
			String line;
			
			while ((line = reader.readLine()) != null) {								
				
				for( RegExpressionHandler regExpressionHandler : regExpHandlerList){					
					regExpressionHandler.applyRegEx( line, entry );
				}				
			}	
			
			return entry.isPassedByContextFilter();
			
		} catch (IOException e) {
			e.printStackTrace();			
		} catch (Exception fe) {
			fe.printStackTrace();			
		}		
		finally {			
			try {
				
				if( reader != null ){
					reader.close();
				}

			} catch (IOException e) {				
				e.printStackTrace();
			}			
		}			      
		return false;
	}

	
	

	
	private void regExpTestDrive(){
		
		BufferedReader reader = null;		
		try {
				
			String regEx1 = "^4[0-9]{12}(?:[0-9]{3})?$";
			
			//String regEx1 = "[0-9]*";
			
			String regEx2 = "^4[1-5][0-9]{14}$";

		    // Create a Pattern object
		    Pattern pattern1 = Pattern.compile(regEx1);		    
		    Pattern pattern2 = Pattern.compile(regEx2);
		    
		    // Now create matcher object.
		    		      
			
		    reader = new BufferedReader( new FileReader("E:\\myworksapce\\epsilon-nalinda\\LeakHawk\\src\\com\\epsilon\\LeakHawk\\test\\testing.txt") );			   
			String line;
			
			while ((line = reader.readLine()) != null) {	
				
				Matcher matcher1 = pattern1.matcher(line);
				if ( matcher1.find() ) {
					System.out.println("Found value: " + matcher1.group(0) );
				} else {
					System.out.println("No match found");
				}								
			}				
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (Exception fe) {
			fe.printStackTrace();
			
		}		
		finally {			
			try {
				
				if( reader != null ){
					reader.close();
				}

			} catch (IOException e) {				
				e.printStackTrace();
			}			
		}			      		
	}
	
	public List<FeedEntry> getFilteredEntryList() {
		return filteredEntryList;
	}

	public void setFilteredEntryList(List<FeedEntry> filteredEntryList) {
		this.filteredEntryList = filteredEntryList;
	}
	
	
}
