package com.leakhawk.classifier;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.leakhawk.io.FileManager;

public class ContentClassifier {

	private String inputFileName;
	
	private String inputFilePath;
	
	private String scriptFilePath;
	

	public static void main( String[] args ){
		
		try {
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "CC/CC_classifier.sh", "CC/CC_001_CC_BY_HEXX7.txt");
            final Process process = pb.start();

            Thread.sleep(1000);
            
            ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "CC/CC_validator.sh", "CC/CC_001_CC_BY_HEXX7.arff");
            final Process processVal = pbVal.start();

            
            BufferedReader br = new BufferedReader(new InputStreamReader(processVal.getInputStream()));
            PrintWriter pw = new PrintWriter(processVal.getOutputStream());
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                pw.flush();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	
	public String getInputFilePath() {
		return inputFilePath;
	}


	public void setInputFilePath(String inputFilePath) {
		this.inputFilePath = inputFilePath;
	}


	public String getScriptFilePath() {
		return scriptFilePath;
	}


	public void setScriptFilePath(String scriptFilePath) {
		this.scriptFilePath = scriptFilePath;
	}
	
	
	
	public String getInputFileName() {
		return inputFileName;
	}


	public void setInputFileName(String inputFileName) {
		this.inputFileName = inputFileName;
	}


	public String classify(){
		
		StringBuilder sb = new StringBuilder();
		try {
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "CC/CC_classifier.sh", getInputFilePath());
            final Process process = pb.start();

            Thread.sleep(1000);
            
            ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "CC/CC_validator.sh", FileManager.contentFilePath + getInputFileName() + ".arff");
            final Process processVal = pbVal.start();            
            
            BufferedReader br = new BufferedReader(new InputStreamReader(processVal.getInputStream()));
            PrintWriter pw = new PrintWriter(processVal.getOutputStream());
            String line;
            
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                sb.append( line );
                pw.flush();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }		
		return sb.toString();
	}
	
}
