package com.leakhawk.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.leakhawk.classifier.ClassifierResult;
import com.leakhawk.io.FileManager;
import com.leakhawk.model.FeedEntry;

public class SensitivityPredictor {

	private FeedEntry entry;
	
	private String sensitivityLabel;
	
	private int creditCardNumberCount;
	
	
	public void predictSensitivity(){
		
		ClassifierResult classifierResult = getEntry().getClassifierResult();
		
		if( classifierResult.isCCPassed() ){
			
			setCreditCardNumberCount(Integer.parseInt( extractCCNumberCount() ) );
			
			if( creditCardNumberCount > 30 ){
				sensitivityLabel = "CRITICAL";
			}
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
