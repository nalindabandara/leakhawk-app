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
	
		//#U1-USER: Does the user, seems suspicious?
				
		if( getDbManager().isUserExists( getEntry().getUser() ) ){
			getEntry().getClassifierResult().getEvidenceClassifierResult().setUserExists(true);
		}
		
		//#E1 	SUBJECT:Is there any evidence of a hacking attack on the subject?
		
		if( applyClassify1().equals("found")){
			getEntry().getClassifierResult().getEvidenceClassifierResult().setClassifier1Passed(true);
		}
		
		//#E2 	SUBJECT:Are there any signs of usage of a security tool?
		
		if( applyClassify2().equals("found")){
			getEntry().getClassifierResult().getEvidenceClassifierResult().setClassifier2Passed(true);
		}

		//#E3 	SUBJECT:Are there any signs of security vulnerability?
		
		if( applyClassify3().equals("found")){
			getEntry().getClassifierResult().getEvidenceClassifierResult().setClassifier3Passed(true);
		}
		
		//#E4 	SUBJECT:Evidence of a Hacker involvement/Hacktivist movement?
		
		if( applyClassify4().equals("found")){
			getEntry().getClassifierResult().getEvidenceClassifierResult().setClassifier4Passed(true);
		}
		
		//#E5 	BODY:	Is there any evidence of a hacking attack in the body text?
		
		if( applyClassify5().equals("found")){
			getEntry().getClassifierResult().getEvidenceClassifierResult().setClassifier5Passed(true);
		}
		
		//#E6 	BODY:	Are there any signs of usage of a security tool in the body text?
		
		if( applyClassify6().equals("found")){
			getEntry().getClassifierResult().getEvidenceClassifierResult().setClassifier6Passed(true);
		}
		
		//#E7	BODY:	Are there any signs of security vulnerability in the body text?
		
		if( applyClassify7().equals("found")){
			getEntry().getClassifierResult().getEvidenceClassifierResult().setClassifier7Passed(true);
		}
		
		//#E8	BODY:	Are there any signs of security vulnerability in the body text?
		
		if( applyClassify8().equals("found")){
			getEntry().getClassifierResult().getEvidenceClassifierResult().setClassifier8Passed(true);
		}
	}

	
	public String applyClassify1(){
		
		StringBuilder sb = new StringBuilder();
		try {
//            System.out.println("applyclassify1 is running");
//			System.out.println(FileManager.contextFilePath + getEntry().getEntryFileName());
            ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/Evidence/evidence1.sh", FileManager.contextFilePath + getEntry().getEntryFileName());

            final Process processVal = pbVal.start();            
            BufferedReader br = new BufferedReader(new InputStreamReader(processVal.getInputStream()));
            PrintWriter pw = new PrintWriter(processVal.getOutputStream());
            String line;
            
            while ((line = br.readLine()) != null) {
//                System.out.println(line+"\n");
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
            ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/Evidence/evidence2.sh", FileManager.contextFilePath + getEntry().getEntryFileName());
            final Process processVal = pbVal.start();            
            BufferedReader br = new BufferedReader(new InputStreamReader(processVal.getInputStream()));
            PrintWriter pw = new PrintWriter(processVal.getOutputStream());
            String line;
            
            while ((line = br.readLine()) != null) {
//                System.out.println(line+"\n");
                sb.append( line );
                pw.flush();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }		
		return sb.toString();
	}
	
	public String applyClassify3(){
		
		StringBuilder sb = new StringBuilder();
		try {
            ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/Evidence/evidence3.sh", FileManager.contextFilePath + getEntry().getEntryFileName());
            final Process processVal = pbVal.start();            
            BufferedReader br = new BufferedReader(new InputStreamReader(processVal.getInputStream()));
            PrintWriter pw = new PrintWriter(processVal.getOutputStream());
            String line;
            
            while ((line = br.readLine()) != null) {
//                System.out.println(line+"\n");
                sb.append( line );
                pw.flush();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }		
		return sb.toString();
	}	
	
	
	public String applyClassify4(){
		
		StringBuilder sb = new StringBuilder();
		try {
            ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/Evidence/evidence4.sh", FileManager.contextFilePath + getEntry().getEntryFileName());
            final Process processVal = pbVal.start();            
            BufferedReader br = new BufferedReader(new InputStreamReader(processVal.getInputStream()));
            PrintWriter pw = new PrintWriter(processVal.getOutputStream());
            String line;
            
            while ((line = br.readLine()) != null) {
//                System.out.println(line+"\n");
                sb.append( line );
                pw.flush();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }		
		return sb.toString();
	}	
	
	
	public String applyClassify5(){
		
		StringBuilder sb = new StringBuilder();
		try {
            ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/Evidence/evidence5.sh", FileManager.contextFilePath + getEntry().getEntryFileName());
            final Process processVal = pbVal.start();            
            BufferedReader br = new BufferedReader(new InputStreamReader(processVal.getInputStream()));
            PrintWriter pw = new PrintWriter(processVal.getOutputStream());
            String line;
            
            while ((line = br.readLine()) != null) {
//                System.out.println(line+"\n");
                sb.append( line );
                pw.flush();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }		
		return sb.toString();
	}	
	
	
	public String applyClassify6(){
		
		StringBuilder sb = new StringBuilder();
		try {
            ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/Evidence/evidence6.sh", FileManager.contextFilePath + getEntry().getEntryFileName());
            final Process processVal = pbVal.start();            
            BufferedReader br = new BufferedReader(new InputStreamReader(processVal.getInputStream()));
            PrintWriter pw = new PrintWriter(processVal.getOutputStream());
            String line;
            
            while ((line = br.readLine()) != null) {
//                System.out.println(line+"\n");
                sb.append( line );
                pw.flush();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }		
		return sb.toString();
	}
	
	
	public String applyClassify7(){
		
		StringBuilder sb = new StringBuilder();
		try {
            ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/Evidence/evidence7.sh", FileManager.contextFilePath + getEntry().getEntryFileName());
            final Process processVal = pbVal.start();            
            BufferedReader br = new BufferedReader(new InputStreamReader(processVal.getInputStream()));
            PrintWriter pw = new PrintWriter(processVal.getOutputStream());
            String line;
            
            while ((line = br.readLine()) != null) {
//                System.out.println(line+"\n");
                sb.append( line );
                pw.flush();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }		
		return sb.toString();
	}	
	
	
	
	public String applyClassify8(){
		
		StringBuilder sb = new StringBuilder();
		try {
            ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "/home/nalinda/oct/leakhawk-app/Evidence/evidence8.sh", FileManager.contextFilePath + getEntry().getEntryFileName());
            final Process processVal = pbVal.start();            
            BufferedReader br = new BufferedReader(new InputStreamReader(processVal.getInputStream()));
            PrintWriter pw = new PrintWriter(processVal.getOutputStream());
            String line;
            
            while ((line = br.readLine()) != null) {
//                System.out.println(line+"\n");
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
