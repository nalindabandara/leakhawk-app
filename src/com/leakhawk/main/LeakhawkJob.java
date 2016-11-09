package com.leakhawk.main;

import java.util.List;

import com.leakhawk.classifier.ClassifierResult;
import com.leakhawk.classifier.ContentClassifier;
import com.leakhawk.classifier.EvidenceClassifier;
import com.leakhawk.filter.ContextFilterComponent;
import com.leakhawk.filter.PreFilterComponent;
import com.leakhawk.io.DBManager;
import com.leakhawk.io.FileManager;
import com.leakhawk.model.FeedEntry;



public class LeakhawkJob extends Thread {

	public static String PRE_FILTER = "PRE_FILTER";
	public static String CONTEXT_FILTER = "CONTEXT_FILTER";

	private List<FeedEntry> feedEntryList = null;

	public void run(){

		if( feedEntryList != null && feedEntryList.size() > 0 ) {

			FileManager fileManager = new FileManager();
			DBManager dbManager = new DBManager();

			//Applying Pre Filter
			PreFilterComponent preFilterComponent = new PreFilterComponent( feedEntryList );			
			preFilterComponent.applyPreFilter();			
			List<FeedEntry> preFilteredList = preFilterComponent.getFilteredEntryList();

			fileManager.saveEntryList(preFilteredList, PRE_FILTER);
			dbManager.saveFeedEntryBatch(preFilteredList);


			//Applying Context Filter
			ContextFilterComponent contextFilterComponent = new ContextFilterComponent(preFilteredList);
			contextFilterComponent.applyContextFilter();

			List<FeedEntry> contextFilteredList = contextFilterComponent.getFilteredEntryList();
			fileManager.saveEntryList(contextFilteredList, CONTEXT_FILTER);
			dbManager.saveContextFeedEntryBatch( contextFilteredList );


			for( FeedEntry entry : contextFilteredList ){

				entry.setClassifierResult( new ClassifierResult());	

//******************************************* CC Classifier	*****************************************************//
				ContentClassifier ccClassifier = new ContentClassifier();				
				ccClassifier.setARFFScriptFilePath( "/home/nalinda/oct/leakhawk-app/Content/CC/CC_classifier.sh" );
				ccClassifier.setPredictorScriptFilePath("/home/nalinda/oct/leakhawk-app/Content/CC/CC_validator.sh");
				ccClassifier.setInputFilePath( entry.getFullFilePath());	
				ccClassifier.setInputFileName(entry.getEntryFileName() + ".CC.arff");
				System.out.println("Executing CC Classifier");
				if( ccClassifier.classify().contains("CC")){
					fileManager.saveEntryAsFile(entry, FileManager.sensitiveFilePath);
					entry.getClassifierResult().setCCPassed(true);
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

//******************************************* PK Classifier	*****************************************************//
				ContentClassifier pkClassifier = new ContentClassifier();				
				pkClassifier.setARFFScriptFilePath( "/home/nalinda/oct/leakhawk-app/Content/PK/PK_classifier.sh" );
				pkClassifier.setPredictorScriptFilePath("/home/nalinda/oct/leakhawk-app/Content/PK/PK_validator.sh");
				pkClassifier.setInputFilePath( entry.getFullFilePath());	
				pkClassifier.setInputFileName(entry.getEntryFileName() + ".PK.arff");
				System.out.println("Executing PK Classifier");
				if( pkClassifier.classify().contains("PK")){
					fileManager.saveEntryAsFile(entry, FileManager.sensitiveFilePath);
					entry.getClassifierResult().setPKPassed(true);
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

//******************************************* WD Classifier	*****************************************************//
	
				ContentClassifier wdClassifier = new ContentClassifier();				
				wdClassifier.setARFFScriptFilePath( "/home/nalinda/oct/leakhawk-app/Content/WD/WD_classifier.sh" );
				wdClassifier.setPredictorScriptFilePath("/home/nalinda/oct/leakhawk-app/Content/WD/WD_validator.sh");
				wdClassifier.setInputFilePath( entry.getFullFilePath());	
				wdClassifier.setInputFileName(entry.getEntryFileName() + ".WD.arff");
				System.out.println("Executing WD Classifier");
				if( wdClassifier.classify().contains("WD")){
					fileManager.saveEntryAsFile(entry, FileManager.sensitiveFilePath);
					entry.getClassifierResult().setWDPassed(true);
				}					
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

//******************************************* CF Classifier	*****************************************************//
	
				ContentClassifier cfClassifier = new ContentClassifier();				
				cfClassifier.setARFFScriptFilePath( "/home/nalinda/oct/leakhawk-app/Content/CF/CF_classifier.sh" );
				cfClassifier.setPredictorScriptFilePath("/home/nalinda/oct/leakhawk-app/Content/CF/CF_validator.sh");
				cfClassifier.setInputFilePath( entry.getFullFilePath());	
				cfClassifier.setInputFileName(entry.getEntryFileName() + ".CF.arff");
				System.out.println("Executing CF Classifier");
				if( cfClassifier.classify().contains("CF")){
					fileManager.saveEntryAsFile(entry, FileManager.sensitiveFilePath);
					entry.getClassifierResult().setCFPassed(true);
				}	

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
//******************************************* DB Classifier	*****************************************************//
				
				ContentClassifier dbClassifier = new ContentClassifier();				
				dbClassifier.setARFFScriptFilePath( "/home/nalinda/oct/leakhawk-app/Content/DB/DB_classifier.sh" );
				dbClassifier.setPredictorScriptFilePath("/home/nalinda/oct/leakhawk-app/Content/DB/DB_validator.sh");
				dbClassifier.setInputFilePath( entry.getFullFilePath());	
				dbClassifier.setInputFileName(entry.getEntryFileName() + ".DB.arff");
				System.out.println("Executing DB Classifier");
				if( dbClassifier.classify().contains("DB")){
					fileManager.saveEntryAsFile(entry, FileManager.sensitiveFilePath);
					entry.getClassifierResult().setDBPassed(true);
				}	

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
//******************************************* UC Classifier	*****************************************************//
				
				ContentClassifier ucClassifier = new ContentClassifier();				
				ucClassifier.setARFFScriptFilePath( "/home/nalinda/oct/leakhawk-app/Content/UC/UC_classifier.sh" );
				ucClassifier.setPredictorScriptFilePath("/home/nalinda/oct/leakhawk-app/Content/UC/UC_validator.sh");
				ucClassifier.setInputFilePath( entry.getFullFilePath());	
				ucClassifier.setInputFileName(entry.getEntryFileName() + ".UC.arff");
				System.out.println("Executing UC Classifier");
				if( ucClassifier.classify().contains("UC")){
					fileManager.saveEntryAsFile(entry, FileManager.sensitiveFilePath);
					entry.getClassifierResult().setUCPassed(true);
				}	

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
					
				
				
				
				
				
				
				
				
				
				
				// Evidence Classifier
				EvidenceClassifier evidenceClassifier = new EvidenceClassifier( dbManager );
				evidenceClassifier.setEntry(entry);
				evidenceClassifier.classify();



				//Sensitivity Prediction
				SensitivityPredictor sensitivityPredictor = new SensitivityPredictor();
				sensitivityPredictor.setEntry(entry);
				sensitivityPredictor.predictSensitivity();
			}

		}
	}

	public List<FeedEntry> getFeedEntryList() {
		return feedEntryList;
	}

	public void setFeedEntryList(List<FeedEntry> feedEntryList) {
		this.feedEntryList = feedEntryList;
	}

}
