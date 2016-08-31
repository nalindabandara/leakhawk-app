package com.leakhawk.filter.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpressionHandler {

	private String regExpression;
	
	private  Pattern pattern;
	
	private List<String> matchingWords = new ArrayList<String>();
	
	private int numberOfMatches;

	
	public RegExpressionHandler( String regEx ){
		
		this.regExpression = regEx;
		this.pattern = Pattern.compile( this.regExpression );		
	}
	
	
	public String getRegExpression() {
		return regExpression;
	}

	public void setRegExpression(String regExpression) {
		this.regExpression = regExpression;
	}

	public List<String> getMatchingWords() {
		return matchingWords;
	}

	public void setMatchingWords(List<String> matchingWords) {
		this.matchingWords = matchingWords;
	}

	public int getNumberOfMatches() {
		return numberOfMatches;
	}

	public void setNumberOfMatches(int numberOfMatches) {
		this.numberOfMatches = numberOfMatches;
	}
			
	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}


	public void applyRegEx( String line ){
		
		Matcher matcher = getPattern().matcher(line);
		if( matcher.find()){							
			if( matcher.group() != null ){
				getMatchingWords().add( matcher.group() );				
				numberOfMatches = numberOfMatches + 1;		
			}			
		}
	} 
	
	
	
	
}
