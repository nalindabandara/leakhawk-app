package com.leakhawk.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class FeedEntry {

	private String scrapperUrl;
	
	private String date;
	
	private String key;		
		
	private String title;		
	
	private String user;
	
	private String syntax;
	
	private InputStream entryStream;
	
	private String entryFileName;

	private List<String> matchingKeyWordList;
	
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


	
	public List<String> getMatchingKeyWordList() {
		if( matchingKeyWordList == null){			 
			matchingKeyWordList = new ArrayList<String>();
			return matchingKeyWordList;
		}
		return matchingKeyWordList;
	}


	public void setMatchingKeyWordList(List<String> matchingKeyWordList) {
		this.matchingKeyWordList = matchingKeyWordList;
	}


	@Override
	public String toString() {
		return "FeedEntry [scrapperUrl=" + scrapperUrl + ", key=" + key
				+ ", title=" + title + ", user=" + user + ", syntax=" + syntax
				+ "]";
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
