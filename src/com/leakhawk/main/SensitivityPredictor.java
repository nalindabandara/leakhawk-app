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
	
	
	public void predictSensitivity(){
		
		ClassifierResult classifierResult = getEntry().getClassifierResult();
		EvidenceClassifierResult evidenceClassifierResult = getEntry().getClassifierResult().getEvidenceClassifierResult();


// Parameters from the Content Classifiers 

		if( classifierResult.isCCPassed() ){
			
			setCreditCardNumberCount(Integer.parseInt( extractCCNumberCount() ) );
			
			if( creditCardNumberCount > 30 ){
				sensitivityLabel = "CRITICAL";
			}
		}
		


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
			System.out.println("no evidence of a data leakage or hacking incident found!");
		}			
		
		
		System.out.println("Sensitivity : " + sensitivityLabel);
	}
	

	public String extractCCNumberCount(){
		
		StringBuilder sb = new StringBuilder();
		try {
            
            ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/Content/PK/PK_validator.sh", FileManager.contentFilePath + getEntry().getEntryFileName());
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
		return sb.toString();
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
