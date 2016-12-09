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
	private int email_hash_count;
	private int email_count;
	private int URLratio;
	private int domainCount;
	private boolean sensitiveData = false;
	private boolean sensitiveData_CC = false;
	private boolean sensitiveData_CF = false;


	public void predictSensitivity(){

		ClassifierResult classifierResult = getEntry().getClassifierResult();
		EvidenceClassifierResult evidenceClassifierResult = getEntry().getClassifierResult().getEvidenceClassifierResult();


		if( classifierResult.isCCPassed() ){
			//System.out.println("CONTENT: Possible Credit Card Breach");

			setCreditCardNumberCount(Integer.parseInt( extractCCNumberCount() ) );

			if ((creditCardNumberCount < 5) ){
				sensitivityLabel = "MEDIUM-CC";
			}

			if( (creditCardNumberCount < 20) && (creditCardNumberCount > 5) ){
				sensitivityLabel = "HIGH-CC";
			}

			if((creditCardNumberCount > 0) && presenseOfSensitiveData("/home/nalinda/oct/leakhawk-app/predictor/CC_sensitiveData.sh")){
				sensitivityLabel = "CRITICAL-CC";
				entry.getSensitivityResultMsgList().add( "Possible sensitive authentication data found!" );
				//System.out.println("Possible sensitive authentication data found!");

			}

			if( creditCardNumberCount > 20 ){
				sensitivityLabel = "CRITICAL-CC";
			}
		}	

		if( classifierResult.isPKPassed() ){
			//System.out.println("CONTENT: Possible Private Key Compromise!");
			sensitivityLabel = "CRITICAL-PK";
		}			


		if( classifierResult.isWDPassed() ){

			setURLratio(Integer.parseInt( extractURLratio() ));
			//System.out.println("CONTENT: Possible Website defacement incident!");

			if( (URLratio > 0) && (URLratio <70)){
				sensitivityLabel = "HIGH";
			}

			if( URLratio > 70){
				sensitivityLabel = "CRITICAL-WD";
			}
		}	


		if( classifierResult.isCFPassed() ){
			//System.out.println("CONTENT: Possible Configuration file exposure!");

			if(presenseOfSensitiveData("/home/nalinda/oct/leakhawk-app/predictor/CF_sensitiveData.sh")){
				sensitivityLabel = "CRITICAL-CF";
				entry.getSensitivityResultMsgList().add( "Possible Plaintext passwords found!" );
				//System.out.println("Possible Plaintext passwords found!");
			}
		}	


		if( classifierResult.isDBPassed() ){
			//System.out.println("CONTENT: Possible Database Dump!");
			sensitivityLabel = "HIGH-DB";

			// if evidence passed, escalated to CRITICAL

			//weak hashes based on https://en.wikipedia.org/wiki/Hash_function_security_summary
			if(presenseOfSensitiveData("/home/nalinda/oct/leakhawk-app/predictor/DB_sensitiveData.sh")){
				sensitivityLabel = "CRITICAL-DB";
			}
		}	


		if( classifierResult.isUCPassed() ){
			//System.out.println("CONTENT: Possible Credentials Dump!");
			setEmail_hash_count( Integer.parseInt( UCcounter() ));

			if (email_hash_count > 30){
				sensitivityLabel = "CRITICAL-UC";
			}
		}	


		if( classifierResult.isDAPassed() ){
			//System.out.println("CONTENT: Possible DNS attack!");


			setDomainCount( Integer.parseInt( DAcounter() ));
			if (domainCount < 10){
				sensitivityLabel = "CRITICAL-DA";
			}

			if (domainCount >= 10){
				sensitivityLabel = "CRITICAL-DA-l";
			}

		}	


		if( classifierResult.isEOPassed() ){
			//System.out.println("CONTENT: Possible Email Dump!");
			
			setEmail_count( Integer.parseInt( EOcounter() ));
			if (email_count < 50){
				sensitivityLabel = "MEDIUM-EO";
			}

			if (email_count >= 50){
				sensitivityLabel = "HIGH-EO";
			}
		}	


		if( classifierResult.isECPassed() ){
			//System.out.println("CONTENT: Possible Email conversation!");
		
			if(presenseOfSensitiveData("/home/nalinda/oct/leakhawk-app/predictor/EC_sensitiveData.sh")){
				sensitivityLabel = "CRITICAL-EC";
			}						
		}	

		if (classifierResult.isContentClassifierPassed() && evidenceClassifierResult.isEvidencePassed()){
			sensitivityLabel = "CRITICAL-Evid+";
		}

		entry.getSensitivityResultMsgList().add( "Sensitivity : " + sensitivityLabel );
		//System.err.println("Sensitivity : " + sensitivityLabel);

		//		classifierResult.getClassifierResultMsg();
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

		//System.out.println("CC count:"+sb.toString());
		entry.getSensitivityResultMsgList().add( "CC count:" + sb.toString() );
		return sb.toString();
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

		//System.out.println("URL ratio: "+sb.toString()+"\n");
		entry.getSensitivityResultMsgList().add( "URL ratio: " + sb.toString() );
		return sb.toString();
	}


	public int getURLratio() {
		return URLratio;
	}

	public void setURLratio(int uRLratio) {
		this.URLratio = uRLratio;
	}


	//******************************** UC related functions ******************************************** //

	public String UCcounter(){

		StringBuilder sb = new StringBuilder();
		try {

			ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/predictor/UC_counter.sh", FileManager.sensitiveFilePath + getEntry().getEntryFileName());
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

		//System.out.println("Email or Hash count:"+sb.toString());
		entry.getSensitivityResultMsgList().add( "Email or Hash count:" + sb.toString() );
		return sb.toString();
	}


	public int getEmail_hash_count() {
		return email_hash_count;
	}

	public void setEmail_hash_count(int email_hash_count) {
		this.email_hash_count = email_hash_count;
	}





	//******************************** DA related functions ******************************************** //

	public String DAcounter(){

		StringBuilder sb = new StringBuilder();
		try {

			ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/predictor/DA_counter.sh", FileManager.sensitiveFilePath + getEntry().getEntryFileName());
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

		//System.out.println("Related Domain Count: "+sb.toString()+"\n");
		entry.getSensitivityResultMsgList().add( "Related Domain Count: " + sb.toString() );
		return sb.toString();
	}



	public int getDomainCount() {
		return domainCount;
	}

	public void setDomainCount(int domainCount) {
		this.domainCount = domainCount;
	}




	//******************************** EO related functions ******************************************** //

	public String EOcounter(){

		StringBuilder sb = new StringBuilder();
		try {

			ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/predictor/EO_counter.sh", FileManager.sensitiveFilePath + getEntry().getEntryFileName());
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

		//System.out.println("No of emails: "+sb.toString()+"\n");
		entry.getSensitivityResultMsgList().add( "No of emails: "+sb.toString() );
		return sb.toString();
	}


	public int getEmail_count() {
		return email_count;
	}

	public void setEmail_count(int email_count) {
		this.email_count = email_count;
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


	public boolean presenseOfSensitiveData(String path){

		StringBuilder sb = new StringBuilder();
		try {

			ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", path, FileManager.sensitiveFilePath + getEntry().getEntryFileName());
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

		if( ! sb.toString().matches("0")){
			this.sensitiveData = true;
		}
		return sensitiveData;
	}
	
	
	
	
}
