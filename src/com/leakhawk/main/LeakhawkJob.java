package com.leakhawk.main;

import java.util.List;

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
				
				
			// Evidence Classifier
				EvidenceClassifier evidenceClassifier = new EvidenceClassifier( dbManager );
				evidenceClassifier.setEntry(entry);
				evidenceClassifier.classify();
				

			// CC Classifier	
				ContentClassifier ccClassifier = new ContentClassifier();				
				ccClassifier.setARFFScriptFilePath( "/home/nalinda/oct/leakhawk-app/Content/CC/CC_classifier.sh" );
				ccClassifier.setPredictorScriptFilePath("/home/nalinda/oct/leakhawk-app/Content/CC/CC_validator.sh");
				ccClassifier.setInputFilePath( entry.getFullFilePath());	
				ccClassifier.setInputFileName(entry.getEntryFileName() + ".CC.arff");
					if( ccClassifier.classify().contains("CC")){
						System.err.println("Possible Credit Card Breach");
						
						fileManager.saveEntryAsFile(entry, FileManager.contentFilePath);
						
						entry.getClassifierResult().setCCPassed(true);
					}
				
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				// PK Classifier	
				ContentClassifier pkClassifier = new ContentClassifier();				
				pkClassifier.setARFFScriptFilePath( "/home/nalinda/oct/leakhawk-app/Content/PK/PK_classifier.sh" );
				pkClassifier.setPredictorScriptFilePath("/home/nalinda/oct/leakhawk-app/Content/PK/PK_validator.sh");
				pkClassifier.setInputFilePath( entry.getFullFilePath());	
				pkClassifier.setInputFileName(entry.getEntryFileName() + ".PK.arff");
					if( pkClassifier.classify().equals("PK")){
						System.err.println("Possible Private Key Compromise");
					}

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				// WD Classifier	
				ContentClassifier wdClassifier = new ContentClassifier();				
				wdClassifier.setARFFScriptFilePath( "/home/nalinda/oct/leakhawk-app/Content/WD/WD_classifier.sh" );
				wdClassifier.setPredictorScriptFilePath("/home/nalinda/oct/leakhawk-app/Content/WD/WD_validator.sh");
				wdClassifier.setInputFilePath( entry.getFullFilePath());	
				wdClassifier.setInputFileName(entry.getEntryFileName() + ".WD.arff");
						if( wdClassifier.classify().equals("WD")){
							System.err.println("Possible Website defacement incident");
						}					
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
				// WD Classifier	
				ContentClassifier cfClassifier = new ContentClassifier();				
				cfClassifier.setARFFScriptFilePath( "/home/nalinda/oct/leakhawk-app/Content/CF/CF_classifier.sh" );
				cfClassifier.setPredictorScriptFilePath("/home/nalinda/oct/leakhawk-app/Content/CF/CF_validator.sh");
				cfClassifier.setInputFilePath( entry.getFullFilePath());	
				cfClassifier.setInputFileName(entry.getEntryFileName() + ".CF.arff");
						if( cfClassifier.classify().equals("CF")){
							System.err.println("Possible Configuration File exposure");
						}	
						
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
