package com.leakhawk.filter.regex;

import java.util.ArrayList;
import java.util.List;

public class RegExpResult {

	private String regExpKey;
	
	private int matchingCount;
	
	private List<String> matchingWordList = new ArrayList<String>();

	public RegExpResult(  String key, String word ){
		
		this.regExpKey = key;
		incrementCount();
		addMatchingWord( word );
	}
	
	public void incrementCount() {
		
		matchingCount++;
	}
	
	public void addMatchingWord( String word ){
		getMatchingWordList().add( word );
	}
		
	public String getRegExpKey() {
		return regExpKey;
	}

	public void setRegExpKey(String regExpKey) {
		this.regExpKey = regExpKey;
	}

	public int getMatchingCount() {
		return matchingCount;
	}

	public void setMatchingCount(int matchingCount) {
		this.matchingCount = matchingCount;
	}

	public List<String> getMatchingWordList() {
		return matchingWordList;
	}

	public void setMatchingWordList(List<String> matchingWordList) {
		this.matchingWordList = matchingWordList;
	}

	@Override
	public String toString() {
		return "RegExpResult [ Matching Count="
				+ matchingCount + ", Matching Word List=" + matchingWordList
				+ "]";
	}	
	
	
}
