package com.leakhawk.main;

import java.util.List;

import com.leakhawk.filter.ContextFilterComponent;
import com.leakhawk.filter.PreFilterComponent;
import com.leakhawk.io.DBManager;
import com.leakhawk.io.FileManager;
import com.leakhawk.model.FeedEntry;



public class LeakhawkJob extends Thread {

	public static String PRE_FILTER = "PRE_FILTER";
	public static String CONTEXT_FILTER = "CONTEXT_FILTER";
	
	private List<FeedEntry> feedEntryList = null;
	
	public void run(){
		
		if( feedEntryList != null && feedEntryList.size() > 0 ) {
					
			FileManager fileManager = new FileManager();
			DBManager dbManager = new DBManager();
			
			//Applying Pre Filter
			PreFilterComponent preFilterComponent = new PreFilterComponent( feedEntryList );			
			preFilterComponent.applyPreFilter();			
			List<FeedEntry> preFilteredList = preFilterComponent.getFilteredEntryList();
						
			fileManager.saveEntryList(preFilteredList, PRE_FILTER);
			dbManager.saveFeedEntryBatch(preFilteredList);
			
			
			//Applying Context Filter
			ContextFilterComponent contextFilterComponent = new ContextFilterComponent(preFilteredList);
			contextFilterComponent.applyContextFilter();
			
			List<FeedEntry> contextFilteredList = contextFilterComponent.getFilteredEntryList();
			fileManager.saveEntryList(contextFilteredList, CONTEXT_FILTER);
			dbManager.saveContextFeedEntryBatch( contextFilteredList );
												
		}
	}

	public List<FeedEntry> getFeedEntryList() {
		return feedEntryList;
	}

	public void setFeedEntryList(List<FeedEntry> feedEntryList) {
		this.feedEntryList = feedEntryList;
	}
	
}
