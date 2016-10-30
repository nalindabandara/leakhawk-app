package com.leakhawk.classifier;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import com.leakhawk.io.FileManager;

public class ContentClassifier {

	private String inputFileName;
	
	private String inputFilePath;
	
	private String scriptFilePath;
	
	private String predictorScriptFilePath;
	

	public static void main( String[] args ){
		
		
		System.out.println("echo");
		try {
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "Content/CC/CC_classifier.sh", "/home/nalinda/oct/leakhawk-app/Content/CC/temp/CC_001_CC_BY_HEXX7");
            final Process process = pb.start();

            Thread.sleep(1000);
    		System.out.println("echo2");            
            ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", "Content/CC/CC_validator.sh", "/home/nalinda/oct/leakhawk-app/Content/CC/temp/CC_001_CC_BY_HEXX7.arff");
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


	public String getARFFScriptFilePath() {
		return scriptFilePath;
	}


	public void setARFFScriptFilePath(String scriptFilePath) {
		this.scriptFilePath = scriptFilePath;
	}
	
	public String getPredictorScriptFilePath() {
		return predictorScriptFilePath;
	}


	public void setPredictorScriptFilePath(String predictorScriptFilePath) {
		this.predictorScriptFilePath = predictorScriptFilePath;
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
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", getARFFScriptFilePath(), getInputFilePath());
            final Process process = pb.start();

            Thread.sleep(1000);
//         System.out.println("rt"+FileManager.contentFilePath);
//         System.out.println(getInputFileName());
            ProcessBuilder pbVal = new ProcessBuilder("/bin/bash", getPredictorScriptFilePath(), FileManager.contentFilePath + getInputFileName());
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
	
}
