package com.leakhawk.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.leakhawk.classifier.ClassifierResult;
import com.leakhawk.classifier.EvidenceClassifierResult;
import com.leakhawk.io.FileManager;
import com.leakhawk.model.FeedEntry;

public class SensitivityPredictor {

	private FeedEntry entry;

	private String sensitivityLabel;

	private int creditCardNumberCount;
	private boolean sensitiveData = false;


	public void predictSensitivity(){

		ClassifierResult classifierResult = getEntry().getClassifierResult();

		//		System.out.println("CCPASSED"+classifierResult.isCCPassed());
		// Parameters from the Content Classifiers 


		// Parameters from the Content Classifiers 

		if( classifierResult.isCCPassed() ){
			System.out.println("CONTENT: Possible Credit Card Breach");
			
			setCreditCardNumberCount(Integer.parseInt( extractCCNumberCount() ) );
			
			if ((creditCardNumberCount < 1) && ){
				
			}
			if( creditCardNumberCount > 30 ){
				sensitivityLabel = "CRITICAL";
			}
			
		}	

		if( classifierResult.isPKPassed() ){
			System.err.println("CONTENT: Possible Private Key Compromise");
		}			


		if( classifierResult.isWDPassed() ){
			System.err.println("CONTENT: Possible Website defacement incident");
		}	


		if( classifierResult.isCFPassed() ){
			System.err.println("CONTENT: Possible Configuration file exposure");
		}	


		if( classifierResult.isDBPassed() ){
			System.err.println("CONTENT: Possible Database Dump!");
		}	


		if( classifierResult.isUCPassed() ){
			System.err.println("UC passed at sense pred");
		}	


		if( classifierResult.isDAPassed() ){
			System.err.println("DA passed at sense pred");
		}	


		if( classifierResult.isEOPassed() ){
			System.err.println("EO passed at sense pred");
		}	


		if( classifierResult.isEAPassed() ){
			System.err.println("EA passed at sense pred");
		}	

		if( !classifierResult.isContentClassifierPassed() ){
			System.out.println("NO sensitive data detected!");
		}


		EvidenceClassifierResult evidenceClassifierResult = getEntry().getClassifierResult().getEvidenceClassifierResult();

		// Parameters from the Evidence Classifiers 

		if(evidenceClassifierResult.isUserExists()){
			System.out.println("EVIDENCE: The poster has been involved in earlier hacking incidents");
		}

		if(evidenceClassifierResult.isClassifier1Passed()){
			System.out.println("EVIDENCE: SUBJECT: Evidence are found related to a hacking attack or data leakage");
		}

		if(evidenceClassifierResult.isClassifier2Passed()){
			System.out.println("EVIDENCE: SUBJECT: Evidence of a mentioning of hacking tool");
		}

		if(evidenceClassifierResult.isClassifier3Passed()){
			System.out.println("EVIDENCE: SUBJECT: Evidence of a mentioning of security vulnerability");
		}		

		if(evidenceClassifierResult.isClassifier4Passed()){
			System.out.println("EVIDENCE: SUBJECT: Evidence of a Hacker involvement/Hacktivist movement");
		}	

		if(evidenceClassifierResult.isClassifier5Passed()){
			System.out.println("EVIDENCE: BODY: Evidence are found related to a hacking attack or data leakage");
		}			

		if(evidenceClassifierResult.isClassifier6Passed()){
			System.out.println("EVIDENCE: BODY: Evidence of a mentioning of hacking tool");
		}	

		if(evidenceClassifierResult.isClassifier7Passed()){
			System.out.println("EVIDENCE: BODY: Evidence of a mentioning of security vulnerability");
		}	

		if(evidenceClassifierResult.isClassifier8Passed()){
			System.out.println("EVIDENCE: BODY: Evidence of a Hacker involvement/Hacktivist movement");
		}	

		if(!evidenceClassifierResult.isUserExists() && !evidenceClassifierResult.isClassifier1Passed() && !evidenceClassifierResult.isClassifier2Passed() && !evidenceClassifierResult.isClassifier3Passed() && !evidenceClassifierResult.isClassifier4Passed() && !evidenceClassifierResult.isClassifier5Passed() && !evidenceClassifierResult.isClassifier6Passed() && !evidenceClassifierResult.isClassifier7Passed() && !evidenceClassifierResult.isClassifier8Passed()){
			System.out.println("NO evidence of a data leakage or hacking incident found!");
		}			

		System.out.println("Sensitivity : " + sensitivityLabel);
	}


	public String extractCCNumberCount(){

		StringBuilder sb = new StringBuilder();
		try {

			ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/predictor/CC_counter.sh", FileManager.sensitiveFilePath + getEntry().getEntryFileName());
			final Process processVal = pbVal.start();            

			BufferedReader br = new BufferedReader(new InputStreamReader(processVal.getInputStream()));
			PrintWriter pw = new PrintWriter(processVal.getOutputStream());
			String line;

			while ((line = br.readLine()) != null) {
				System.out.println(line+"\n");
				sb.append( line );
				pw.flush();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
		
		System.out.println("CC count:"+sb.toString());
		return sb.toString();
	}


	public boolean presenseOfSensitiveData(){

		StringBuilder sb = new StringBuilder();
		try {

			ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/predictor/CC_counter.sh", FileManager.sensitiveFilePath + getEntry().getEntryFileName());
			final Process processVal = pbVal.start();            

			BufferedReader br = new BufferedReader(new InputStreamReader(processVal.getInputStream()));
			PrintWriter pw = new PrintWriter(processVal.getOutputStream());
			String line;

			while ((line = br.readLine()) != null) {
				System.out.println(line+"\n");
				sb.append( line );
				pw.flush();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		
		
		System.out.println("CC count:"+sb.toString());
		if( sb.toString() != null){
			this.sensitiveData = true;
		}
			return sensitiveData;
	}
	
	
	public String getSensitivityLabel() {
		return sensitivityLabel;
	}

	public void setSensitivityLabel(String sensitivityLabel) {
		this.sensitivityLabel = sensitivityLabel;
	}

	public int getCreditCardNumberCount() {
		return creditCardNumberCount;
	}

	public void setCreditCardNumberCount(int creditCardNumberCount) {
		this.creditCardNumberCount = creditCardNumberCount;
	}


	public FeedEntry getEntry() {
		return entry;
	}


	public void setEntry(FeedEntry entry) {
		this.entry = entry;
	}

}
