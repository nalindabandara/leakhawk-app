package com.leakhawk.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.leakhawk.aggregator.PastebinAggregator;
import com.leakhawk.model.FeedEntry;
import com.leakhawk.sensor.PastbinSensor;
import com.leakhawk.util.LeakhawkUtils;

public class LeakhawkManager {

	public static boolean isApplyingPreFilter;
	
	public static boolean isApplyingContextFilter;
	
	public static void main( String[] args ){
		
		LeakhawkUtils.readConfigFile();
		
		System.out.print("Do you want to apply the Pre Filter ? [y/n] : ");		
		try{
		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    String s = bufferRead.readLine();		      		    
		    if( s.equalsIgnoreCase("y")){
		    	isApplyingPreFilter = true;		    	
		    } else {
		    	isApplyingPreFilter = false;	    	
		    }		    
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.print("Do you want to apply the Context Filter ? [y/n] : ");		
		try{
		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    String s = bufferRead.readLine();		      		    
		    if( s.equalsIgnoreCase("y")){
		    	isApplyingContextFilter = true;		    	
		    } else {
		    	isApplyingContextFilter = false;	    	
		    }		    
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		PastbinSensor pastebinSensor = new PastbinSensor();	
		
		PastebinAggregator pastebinAggrgator = new PastebinAggregator();
		
		pastebinSensor.setAggregator( pastebinAggrgator );		
		pastebinSensor.start();
		
		while( true ){
			
			try {
				Thread.sleep( 10000 );
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			
			List<FeedEntry> entryList = pastebinAggrgator.getFeedEntryList();
			
			LeakhawkJob leakHawkJob = new LeakhawkJob();
			leakHawkJob.setFeedEntryList(entryList);
			leakHawkJob.start();			
		}
	}
	
}
