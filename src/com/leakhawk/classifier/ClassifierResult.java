package com.leakhawk.classifier;

import com.leakhawk.model.FeedEntry;

public class ClassifierResult {

	private boolean isCCClassifierPassed;
	private boolean isPKClassifierPassed;
	private boolean isWDClassifierPassed;
	private boolean isCFClassifierPassed;
	private boolean isDBClassifierPassed;
	private boolean isUCClassifierPassed;
	private boolean isDAClassifierPassed;	
	private boolean isEOClassifierPassed;
	private boolean isECClassifierPassed;
	
	private FeedEntry entry;

	private EvidenceClassifierResult evidenceClassifierResult;

	public ClassifierResult(){

		this.evidenceClassifierResult =  new EvidenceClassifierResult();
	}


	//***************************** CC Classifier ************************/


	public boolean isCCPassed() {
		return isCCClassifierPassed;
	}

	public void setCCPassed(boolean isCCPassed) {
		this.isCCClassifierPassed = isCCPassed;
	}

	//***************************** PK Classifier ************************/

	public boolean isPKPassed() {
		return isPKClassifierPassed;
	}

	public void setPKPassed(boolean isPKPassed) {
		this.isPKClassifierPassed = isPKPassed;
	}

	//***************************** WD Classifier ************************/


	public boolean isWDPassed() {
		return isWDClassifierPassed;
	}

	public void setWDPassed(boolean isWDPassed) {
		this.isWDClassifierPassed = isWDPassed;
	}

	//***************************** CF Classifier ************************/


	public boolean isCFPassed() {
		return isCFClassifierPassed;
	}

	public void setCFPassed(boolean isCFPassed) {
		this.isCFClassifierPassed = isCFPassed;
	}

	//***************************** DB Classifier ************************/

	public boolean isDBPassed() {
		return isDBClassifierPassed;
	}

	public void setDBPassed(boolean isDBPassed) {
		this.isDBClassifierPassed = isDBPassed;
	}


	//***************************** UC Classifier ************************/

	public boolean isUCPassed() {
		return isUCClassifierPassed;
	}

	public void setUCPassed(boolean isUCPassed) {
		this.isUCClassifierPassed = isUCPassed;
	}

	//***************************** DA Classifier ************************/


	public boolean isDAPassed() {
		return isDAClassifierPassed;
	}

	public void setDAPassed(boolean isDAPassed) {
		this.isDAClassifierPassed = isDAPassed;
	}

	//***************************** EO Classifier ************************/


	public boolean isEOPassed() {
		return isEOClassifierPassed;
	}

	public void setEOPassed(boolean isEOPassed) {
		this.isEOClassifierPassed = isEOPassed;
	}

	//***************************** EC Classifier ************************/


	public boolean isECPassed() {
		return isECClassifierPassed;
	}

	public void setECPassed(boolean isECPassed) {
		this.isECClassifierPassed = isECPassed;
	}






	public EvidenceClassifierResult getEvidenceClassifierResult() {
		return evidenceClassifierResult;
	}

	public void setEvidenceClassifierResult(EvidenceClassifierResult evidenceClassifierResult) {
		this.evidenceClassifierResult = evidenceClassifierResult;
	}


	public boolean isContentClassifierPassed(){

		if( isCCClassifierPassed || isPKClassifierPassed || isDBClassifierPassed || isCFClassifierPassed || isDAClassifierPassed || isECClassifierPassed || isEOClassifierPassed || isUCClassifierPassed || isWDClassifierPassed) {
			return true;
		}

		return false;		
	}

	public String getClassifierResultMsg(){
		
		System.out.println("Classifier Results:");
		if( !isContentClassifierPassed() && getEvidenceClassifierResult().isEvidencePassed() ){
			return "Evidance Passed, Content Failed";
		}
		
		if(evidenceClassifierResult.isUserExists()){
			System.out.println("EVIDENCE: The poster has been involved in earlier hacking incidents");
		}
		
		return "";
	}
	
	public void collectMessageList (){
				
		if( this.isCCPassed() ){
			entry.getClassifierResultMsgList().add( "CONTENT: Possible Credit Card Breach" );
		}
		
		if( this.isPKPassed() ){
			entry.getClassifierResultMsgList().add( "CONTENT: Possible Private Key Compromise!" );
		}
		
		if( this.isWDPassed() ){
			entry.getClassifierResultMsgList().add( "CONTENT: Possible Website defacement incident!");
		}
		
		if( this.isCFPassed() ){
			entry.getClassifierResultMsgList().add( "CONTENT: Possible Configuration file exposure!");
		}
		
		if( this.isDBPassed() ){
			entry.getClassifierResultMsgList().add( "CONTENT: Possible Database Dump!");
		}
		
		if( this.isDBPassed() ){
			entry.getClassifierResultMsgList().add( "CONTENT: Possible Database Dump!");
		}
		
		if( this.isUCPassed() ){
			entry.getClassifierResultMsgList().add( "CONTENT: Possible Credentials Dump!");
		}
		
		if( this.isDAPassed() ){
			entry.getClassifierResultMsgList().add( "CONTENT: Possible DNS attack!");
		}
		
		if( this.isEOPassed() ){
			entry.getClassifierResultMsgList().add( "CONTENT: Possible Email Dump!");
		}
		
		if( this.isECPassed() ){
			entry.getClassifierResultMsgList().add( "CONTENT: Possible Email conversation!");
		}
		
		
		
		
		if(evidenceClassifierResult.isUserExists()){
			entry.getClassifierResultMsgList().add("EVIDENCE: The poster has been involved in earlier hacking incidents");
		}

		if(evidenceClassifierResult.isClassifier1Passed()){
			entry.getClassifierResultMsgList().add("EVIDENCE: SUBJECT: Evidence are found related to a hacking attack or data leakage");
			evidenceClassifierResult.setEvidencePassed(true);
		}

		if(evidenceClassifierResult.isClassifier2Passed()){
			entry.getClassifierResultMsgList().add("EVIDENCE: SUBJECT: Evidence of a mentioning of hacking tool");
		}

		if(evidenceClassifierResult.isClassifier3Passed()){
			entry.getClassifierResultMsgList().add("EVIDENCE: SUBJECT: Evidence of a mentioning of security vulnerability");
		}		

		if(evidenceClassifierResult.isClassifier4Passed()){
			entry.getClassifierResultMsgList().add("EVIDENCE: SUBJECT: Evidence of a Hacker involvement/Hacktivist movement");
		}	

		if(evidenceClassifierResult.isClassifier5Passed()){
			entry.getClassifierResultMsgList().add("EVIDENCE: BODY: Evidence are found related to a hacking attack or data leakage");
		}			

		if(evidenceClassifierResult.isClassifier6Passed()){
			entry.getClassifierResultMsgList().add("EVIDENCE: BODY: Evidence of a mentioning of hacking tool");
		}	

		if(evidenceClassifierResult.isClassifier7Passed()){
			entry.getClassifierResultMsgList().add("EVIDENCE: BODY: Evidence of a mentioning of security vulnerability");
		}	

		if(evidenceClassifierResult.isClassifier8Passed()){
			entry.getClassifierResultMsgList().add("EVIDENCE: BODY: Evidence of a Hacker involvement/Hacktivist movement");
		}	

		if(! this.isContentClassifierPassed()){
			entry.getClassifierResultMsgList().add("NO sensitive data detected!\n");
		}

		if(!evidenceClassifierResult.isEvidencePassed()){
			entry.getClassifierResultMsgList().add("NO evidence of a data leakage or hacking incident found!\n");
		}			
	}
	
	public synchronized void printMessageList() {
		
		for(String message : entry.getClassifierResultMsgList() ){
			System.out.println( message + "\n");			
		}
		
		for(String message : entry.getSensitivityResultMsgList() ){
			System.out.println( message + "\n");			
		}
	} 
}
