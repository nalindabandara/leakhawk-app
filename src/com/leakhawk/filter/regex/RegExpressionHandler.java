package com.leakhawk.filter.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.leakhawk.model.FeedEntry;

public class RegExpressionHandler {

	private String regExpKey;
	
	private String regExpression;
	
	private  Pattern pattern;
	
	public RegExpressionHandler( String regExpKey, String regExp ){
		
		this.regExpKey = regExpKey;
		this.regExpression = regExp;
		this.pattern = Pattern.compile( this.regExpression );		
	}
	
	
	public String getRegExpression() {
		return regExpression;
	}

	public void setRegExpression(String regExpression) {
		this.regExpression = regExpression;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public String getRegExpKey() {
		return regExpKey;
	}

	public void setRegExpKey(String regExpKey) {
		this.regExpKey = regExpKey;
	}


	public void applyRegEx( String line, FeedEntry entry ){
		
		RegExpResult result = entry.getContextFilterResultMap().get( this.regExpKey);
		
		Matcher matcher = getPattern().matcher(line);
		
		while ( matcher.find() ){
						
			if( result == null ){				
				entry.getContextFilterResultMap().put(this.regExpKey, new RegExpResult( this.regExpKey, matcher.group() ));				
			} else {
				result.incrementCount();
				result.addMatchingWord( matcher.group() );
			}				
		}
	} 
	
	
	
	
}
