package com.leakhawk.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.leakhawk.classifier.ClassifierResult;
import com.leakhawk.filter.regex.RegExpResult;

public class FeedEntry {

	private final static Logger logger = Logger.getLogger(FeedEntry.class);
	
	private String scrapperUrl;
	
	private String date;
	
	private String key;		
		
	private String title;		
	
	private String user;
	
	private String syntax;
	
	private InputStream entryStream;
	
	private String entryFileName;
	
	private String fullFilePath;

	private HashMap<String, RegExpResult> contextFilterResultMap = new HashMap<String, RegExpResult>();
	
	private ClassifierResult classifierResult;
	
	private List<String> classifierResultMsgList = new ArrayList<String>();
	
	private List<String> sensitivityResultMsgList = new ArrayList<String>();
	
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
	
	public String getFullFilePath() {
		return fullFilePath;
	}


	public void setFullFilePath(String fullFilePath) {
		this.fullFilePath = fullFilePath;
	}	

	public ClassifierResult getClassifierResult() {
		return classifierResult;
	}


	public void setClassifierResult(ClassifierResult classifierResult) {
		this.classifierResult = classifierResult;
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


	public List<String> getClassifierResultMsgList() {
		return classifierResultMsgList;
	}


	public void setClassifierResultMsgList(List<String> classifierResultMsgList) {
		this.classifierResultMsgList = classifierResultMsgList;
	}


	public List<String> getSensitivityResultMsgList() {
		return sensitivityResultMsgList;
	}


	public void setSensitivityResultMsgList(List<String> sensitivityResultMsgList) {
		this.sensitivityResultMsgList = sensitivityResultMsgList;
	}			
	
	
	
	public void collectClassifireResultMessageList (){
		
		if( this.classifierResult.isCCPassed() ){
			getClassifierResultMsgList().add( "CONTENT: Possible Credit Card Breach" );
		}
		
		if( this.classifierResult.isPKPassed() ){
			getClassifierResultMsgList().add( "CONTENT: Possible Private Key Compromise!" );
		}
		
		if( this.classifierResult.isWDPassed() ){
			getClassifierResultMsgList().add( "CONTENT: Possible Website defacement incident!");
		}
		
		if( this.classifierResult.isCFPassed() ){
			getClassifierResultMsgList().add( "CONTENT: Possible Configuration file exposure!");
		}
		
		if( this.classifierResult.isDBPassed() ){
			getClassifierResultMsgList().add( "CONTENT: Possible Database Dump!");
		}
		
		if( this.classifierResult.isDBPassed() ){
			getClassifierResultMsgList().add( "CONTENT: Possible Database Dump!");
		}
		
		if( this.classifierResult.isUCPassed() ){
			getClassifierResultMsgList().add( "CONTENT: Possible Credentials Dump!");
		}
		
		if( this.classifierResult.isDAPassed() ){
			getClassifierResultMsgList().add( "CONTENT: Possible DNS attack!");
		}
		
		if( this.classifierResult.isEOPassed() ){
			getClassifierResultMsgList().add( "CONTENT: Possible Email Dump!");
		}
		
		if( this.classifierResult.isECPassed() ){
			getClassifierResultMsgList().add( "CONTENT: Possible Email conversation!");
		}
		
		
		
		
		if(this.classifierResult.getEvidenceClassifierResult().isUserExists()){
			getClassifierResultMsgList().add("EVIDENCE: The poster has been involved in earlier hacking incidents");
		}

		if(this.classifierResult.getEvidenceClassifierResult().isClassifier1Passed()){
			getClassifierResultMsgList().add("EVIDENCE: SUBJECT: Evidence are found related to a hacking attack or data leakage");
			this.classifierResult.getEvidenceClassifierResult().setEvidencePassed(true);
		}

		if(this.classifierResult.getEvidenceClassifierResult().isClassifier2Passed()){
			getClassifierResultMsgList().add("EVIDENCE: SUBJECT: Evidence of a mentioning of hacking tool");
		}

		if(this.classifierResult.getEvidenceClassifierResult().isClassifier3Passed()){
			getClassifierResultMsgList().add("EVIDENCE: SUBJECT: Evidence of a mentioning of security vulnerability");
		}		

		if(this.classifierResult.getEvidenceClassifierResult().isClassifier4Passed()){
			getClassifierResultMsgList().add("EVIDENCE: SUBJECT: Evidence of a Hacker involvement/Hacktivist movement");
		}	

		if(this.classifierResult.getEvidenceClassifierResult().isClassifier5Passed()){
			getClassifierResultMsgList().add("EVIDENCE: BODY: Evidence are found related to a hacking attack or data leakage");
		}			

		if(this.classifierResult.getEvidenceClassifierResult().isClassifier6Passed()){
			getClassifierResultMsgList().add("EVIDENCE: BODY: Evidence of a mentioning of hacking tool");
		}	

		if(this.classifierResult.getEvidenceClassifierResult().isClassifier7Passed()){
			getClassifierResultMsgList().add("EVIDENCE: BODY: Evidence of a mentioning of security vulnerability");
		}	

		if(this.classifierResult.getEvidenceClassifierResult().isClassifier8Passed()){
			getClassifierResultMsgList().add("EVIDENCE: BODY: Evidence of a Hacker involvement/Hacktivist movement");
		}	

		if(! this.classifierResult.isContentClassifierPassed()){
			getClassifierResultMsgList().add("NO sensitive data detected!\n");
		}

		if(!this.classifierResult.getEvidenceClassifierResult().isEvidencePassed()){
			getClassifierResultMsgList().add("NO evidence of a data leakage or hacking incident found!\n");
		}			
	}
	
	public synchronized void printMessageList() {
		
		for(String message : getClassifierResultMsgList() ){
			//System.out.println( message + "\n");
			if( getKey() != null ){
				logger.info( getKey() + " - " + message + "\n" );
			} else {
				logger.info( message + "\n" );
			}			
		}
		
		for(String message : getSensitivityResultMsgList() ){
			//System.out.println( message + "\n");	
			if( getKey() != null ){
				logger.info( getKey() + " - " + message + "\n" );
			} else {
				logger.info( message + "\n" );
			}
		}
	}
}
