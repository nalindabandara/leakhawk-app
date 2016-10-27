package com.leakhawk.classifier;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.leakhawk.io.DBManager;
import com.leakhawk.io.FileManager;
import com.leakhawk.model.FeedEntry;

public class EvidenceClassifier {

	private DBManager dbManager;
	
	private FeedEntry entry;
	
	
	
	public EvidenceClassifier( DBManager dbManager){
		
		this.dbManager = dbManager;
	}
	
	public void classify(){
		
		if( getDbManager().isUserExists( getEntry().getUser() ) ){
			
			System.out.println("User exists");
			getEntry().getClassifierResult().getEvidenceClassifierResult().setUserExists(true);
			//getEntry().getClassifierResult().setEvidencePassed(true);
		}
		
		if( applyClassify1().equals("BOB")){
			getEntry().getClassifierResult().getEvidenceClassifierResult().setClassfier1Passed(true);
		}
	}

	
	public String applyClassify1(){
		
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
	
	public DBManager getDbManager() {
		return dbManager;
	}

	public void setDbManager(DBManager dbManager) {
		this.dbManager = dbManager;
	}

	public FeedEntry getEntry() {
		return entry;
	}

	public void setEntry(FeedEntry entry) {
		this.entry = entry;
	}
	
	
	
}
