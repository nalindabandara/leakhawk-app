package com.leakhawk.classifier;

public class ClassifierResult {

	private boolean isCCClassifierPassed;
	private boolean isPKClassifierPassed;
	private boolean isWDClassifierPassed;
	private boolean isCFClassifierPassed;
	private boolean isDBClassifierPassed;
	private boolean isUCClassifierPassed;
	private boolean isDAClassifierPassed;	
	private boolean isEOClassifierPassed;
	private boolean isEAClassifierPassed;
	
	private EvidenceClassifierResult evidenceClassifierResult;
	
	public ClassifierResult(){
		
		this.evidenceClassifierResult =  new EvidenceClassifierResult();
	}
	
	public boolean isDBPassed() {
		return isDBClassifierPassed;
	}

	public void setDBPassed(boolean isDBPassed) {
		this.isDBClassifierPassed = isDBPassed;
	}


	public boolean isCCPassed() {
		return isCCClassifierPassed;
	}

	public void setCCPassed(boolean isCCPassed) {
		this.isCCClassifierPassed = isCCPassed;
	}

	public boolean isPKPassed() {
		return isPKClassifierPassed;
	}

	public void setPKPassed(boolean isPKPassed) {
		this.isPKClassifierPassed = isPKPassed;
	}

	public EvidenceClassifierResult getEvidenceClassifierResult() {
		return evidenceClassifierResult;
	}

	public void setEvidenceClassifierResult(EvidenceClassifierResult evidenceClassifierResult) {
		this.evidenceClassifierResult = evidenceClassifierResult;
	}
			
	
	public boolean isContentClassifierPassed(){
		
		if( isCCClassifierPassed || isPKClassifierPassed || isDBClassifierPassed) {
			return true;
		}
		return false;		
	}
	
	public String getClassifierResultMsg(){
		
		if( !isContentClassifierPassed() && getEvidenceClassifierResult().isEvidencePassed() ){
			return "Evidance Passed, Content Failed";
		}
		return "";
	}
}
