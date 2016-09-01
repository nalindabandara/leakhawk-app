package com.leakhawk.model;

import java.io.InputStream;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.leakhawk.filter.regex.RegExpResult;

public class FeedEntry {

	private String scrapperUrl;
	
	private String date;
	
	private String key;		
		
	private String title;		
	
	private String user;
	
	private String syntax;
	
	private InputStream entryStream;
	
	private String entryFileName;

	private HashMap<String, RegExpResult> contextFilterResultMap = new HashMap<String, RegExpResult>();
	
	public FeedEntry( JSONObject jsonObj ){
		
		this.key = (String) jsonObj.get("key");
		this.user = (String) jsonObj.get("user");
		this.scrapperUrl = (String) jsonObj.get("scrape_url");		
		this.title = (String) jsonObj.get("title");
		this.syntax = (String) jsonObj.get("syntax");		
	}
	

	public String getScrapperUrl() {
		return scrapperUrl;
	}

	public void setScrapperUrl(String scrapperUrl) {
		this.scrapperUrl = scrapperUrl;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSyntax() {
		return syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	public InputStream getEntryStream() {
		return entryStream;
	}

	public void setEntryStream(InputStream entryStream) {
		this.entryStream = entryStream;
	}

	public String getEntryFileName() {
		return entryFileName;
	}

	public void setEntryFileName(String entryFileName) {
		this.entryFileName = entryFileName;
	}

	public HashMap<String, RegExpResult> getContextFilterResultMap() {
		return contextFilterResultMap;
	}


	public void setContextFilterResultMap(
			HashMap<String, RegExpResult> contextFilterResultMap) {
		this.contextFilterResultMap = contextFilterResultMap;
	}

	
	public boolean isPassedByContextFilter(){
		
		int totalCount = 0;
		for( RegExpResult result : this.contextFilterResultMap.values() ){			
			totalCount = totalCount + result.getMatchingCount();
		}
		
		if( totalCount > 0 ){
			return true;
		}
		return false;
	}
	


	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("FeedEntry :\n  ScrapperUrl = ").append( scrapperUrl ).append("\n");
		sb.append("  Date = ").append(date).append("\n");
		sb.append("  Key = ").append(key).append("\n");
		sb.append("  Title = " ).append(title).append("\n");
		sb.append("  User = ").append(user).append("\n");
		sb.append("  Syntax = ").append( syntax ).append("\n");
		sb.append("  Entry filename = ").append(entryFileName).append("\n");
		sb.append("  ContextFilter Result :").append("\n  ").append(contextFilterResultMap).append("\n");
		
		return sb.toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeedEntry other = (FeedEntry) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
}
