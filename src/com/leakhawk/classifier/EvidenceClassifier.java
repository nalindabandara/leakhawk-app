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
	
		
		//#E1-USER: Does the user, seems suspicious?
				
		if( getDbManager().isUserExists( getEntry().getUser() ) ){
			
			System.out.println("The poster has been involved in earlier hacking incidents");
			getEntry().getClassifierResult().getEvidenceClassifierResult().setUserExists(true);
//			getEntry().getClassifierResult().setEvidencePassed(true);
		}
		
		//#E2 	SUBJECT:Is there any evidence of a hacking attack on the subject?
		
		if( applyClassify1().equals("found")){
			System.out.println("Evidence are found related to a hacking attack or data leakage");
			getEntry().getClassifierResult().getEvidenceClassifierResult().setClassfier1Passed(true);
		}
		
		//#E3 	SUBJECT:Are there any signs of usage of a security tool?
		
		if( applyClassify2().equals("found")){
			System.out.println("Evidence of a mentioning of hacking tool");
			getEntry().getClassifierResult().getEvidenceClassifierResult().setClassfier2Passed(true);
		}
		
		
	}

	
	public String applyClassify1(){
		
		StringBuilder sb = new StringBuilder();
		try {
            
//			System.out.println(FileManager.contentFilePath + getEntry());
//            ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/Evidence/evidence1.sh", FileManager.contentFilePath + getEntry().getEntryFileName());
            ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/Evidence/evidence2.sh", "/home/nalinda/oct/IN/sensitive/GptSy28u");

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
	
	
	public String applyClassify2(){
		
		StringBuilder sb = new StringBuilder();
		try {
            
            ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/Evidence/evidence2.sh", "/home/nalinda/oct/IN/sensitive/GptSy28u");

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
