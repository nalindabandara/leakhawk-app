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

	private String sensitivityLabel = "NULL";

	private int creditCardNumberCount;
	private int URLratio;
	private boolean sensitiveData_CC = false;
	private boolean sensitiveData_CF = false;


	public void predictSensitivity(){

		ClassifierResult classifierResult = getEntry().getClassifierResult();
		
//		System.out.println("checkpoint 1: "+ sensitivityLabel);

		if( classifierResult.isCCPassed() ){
			System.out.println("CONTENT: Possible Credit Card Breach");

			setCreditCardNumberCount(Integer.parseInt( extractCCNumberCount() ) );

//			System.out.println("checkpoint 2: "+ sensitivityLabel);

			if ((creditCardNumberCount < 5) ){
				sensitivityLabel = "LOW";
			}

			if( (creditCardNumberCount < 20) && (creditCardNumberCount > 5) ){
				sensitivityLabel = "HIGH";
			}

			if((creditCardNumberCount > 0) && presenseOfSensitiveData_CC()){
				
//				System.out.println("checkpoint 3: "+ sensitivityLabel);
				
				sensitivityLabel = "CRITICAL";
//				System.out.println("checkpoint 4: "+ sensitivityLabel);
			}


			if( creditCardNumberCount > 20 ){
//				System.out.println("checkpoint 5: "+ sensitivityLabel);
				sensitivityLabel = "CRITICAL";
//				System.out.println("checkpoint 6: "+ sensitivityLabel);
			}

		}	

		if( classifierResult.isPKPassed() ){
			System.out.println("checkpoint 7: "+ sensitivityLabel);
			System.out.println("CONTENT: Possible Private Key Compromise!");
			sensitivityLabel = "CRITICAL";
		}			


		if( classifierResult.isWDPassed() ){

			setURLratio(Integer.parseInt( extractURLratio() ));
//			System.out.println("checkpoint 8: "+ sensitivityLabel);
			System.out.println("CONTENT: Possible Website defacement incident!");

			if( (URLratio > 0) && (URLratio <70)){
				sensitivityLabel = "HIGH";
			}

			if( URLratio > 70){
//				System.out.println("checkpoint 9: "+ sensitivityLabel);
				sensitivityLabel = "CRITICAL";
			}
		}	


		if( classifierResult.isCFPassed() ){
			System.out.println("CONTENT: Possible Configuration file exposure!");
//			System.out.println("checkpoint 10: "+ sensitivityLabel);
			if(presenseOfSensitiveData_CF()){
//				System.out.println("checkpoint 11: "+ sensitivityLabel);
				sensitivityLabel = "CRITICAL";
//				System.out.println("checkpoint 12: "+ sensitivityLabel);
			}
//			System.out.println("checkpoint 13: "+ sensitivityLabel);
		}	


		if( classifierResult.isDBPassed() ){
			System.out.println("CONTENT: Possible Database Dump!");
		}	


		if( classifierResult.isUCPassed() ){
			System.out.println("CONTENT: Possible Credentials Dump!");
		}	


		if( classifierResult.isDAPassed() ){
			System.out.println("CONTENT: Possible DNS attack!");
		}	


		if( classifierResult.isEOPassed() ){
			System.out.println("CONTENT: Possible Email Dump!");
		}	


		if( classifierResult.isECPassed() ){
			System.out.println("CONTENT: Possible Email conversation!");
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

		System.err.println("Sensitivity : " + sensitivityLabel);
	}

//******************************** CC related functions ******************************************** //
	public String extractCCNumberCount(){

		StringBuilder sb = new StringBuilder();
		try {

			ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/predictor/CC_counter.sh", FileManager.sensitiveFilePath + getEntry().getEntryFileName());
			final Process processVal = pbVal.start();            

			BufferedReader br = new BufferedReader(new InputStreamReader(processVal.getInputStream()));
			PrintWriter pw = new PrintWriter(processVal.getOutputStream());
			String line;

			while ((line = br.readLine()) != null) {
				//				System.out.println(line+"\n");
				sb.append( line );
				pw.flush();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		

		System.out.println("CC count:"+sb.toString());
		return sb.toString();
	}



	public boolean presenseOfSensitiveData_CC(){

		StringBuilder sb = new StringBuilder();
		try {

			ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/predictor/CC_sensitiveData.sh", FileManager.sensitiveFilePath + getEntry().getEntryFileName());
			final Process processVal = pbVal.start();            

			BufferedReader br = new BufferedReader(new InputStreamReader(processVal.getInputStream()));
			PrintWriter pw = new PrintWriter(processVal.getOutputStream());
			String line;

			while ((line = br.readLine()) != null) {
				//				System.out.println(line+"\n");
				sb.append( line );
				pw.flush();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		

		//		System.out.println("Sensitive Data:"+sb.toString());
		if( sb.toString() != null){
			this.sensitiveData_CC = true;
		}
		return sensitiveData_CC;
	}

	public int getCreditCardNumberCount() {
		return creditCardNumberCount;
	}

	public void setCreditCardNumberCount(int creditCardNumberCount) {
		this.creditCardNumberCount = creditCardNumberCount;
	}


//******************************** WD related functions ******************************************** //
	
	public String extractURLratio(){

		StringBuilder sb = new StringBuilder();
		try {

			ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/predictor/URL_counter.sh", FileManager.sensitiveFilePath + getEntry().getEntryFileName());
			final Process processVal = pbVal.start();            

			BufferedReader br = new BufferedReader(new InputStreamReader(processVal.getInputStream()));
			PrintWriter pw = new PrintWriter(processVal.getOutputStream());
			String line;

			while ((line = br.readLine()) != null) {
				//				System.out.println(line+"\n");
				sb.append( line );
				pw.flush();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		

		System.out.println("URL ratio: "+sb.toString()+"\n");
		return sb.toString();
	}


	public int getURLratio() {
		return URLratio;
	}

	public void setURLratio(int uRLratio) {
		this.URLratio = uRLratio;
	}

	//******************************** CF related functions ******************************************** //
	
	public boolean presenseOfSensitiveData_CF(){

		StringBuilder sb = new StringBuilder();
		try {

			ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/predictor/CF_sensitiveData.sh", FileManager.sensitiveFilePath + getEntry().getEntryFileName());
			final Process processVal = pbVal.start();            

			BufferedReader br = new BufferedReader(new InputStreamReader(processVal.getInputStream()));
			PrintWriter pw = new PrintWriter(processVal.getOutputStream());
			String line;

			while ((line = br.readLine()) != null) {
				//				System.out.println(line+"\n");
				sb.append( line );
				pw.flush();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}		

//				System.out.println("Sensitive Data:"+sb.toString());
//				System.out.println("sensitiveData_CF1: "+sensitiveData_CF);
		if( ! sb.toString().matches("0")){
//			System.out.println("sensitiveData_CF2: "+sensitiveData_CF);
			this.sensitiveData_CF = true;
		}
//		System.out.println("sensitiveData_CF3: "+sensitiveData_CF);
		return sensitiveData_CF;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//*************************************************************************************************** //
	

	public String getSensitivityLabel() {
		return sensitivityLabel;
	}

	public void setSensitivityLabel(String sensitivityLabel) {
		this.sensitivityLabel = sensitivityLabel;
	}


	public FeedEntry getEntry() {
		return entry;
	}


	public void setEntry(FeedEntry entry) {
		this.entry = entry;
	}

}
