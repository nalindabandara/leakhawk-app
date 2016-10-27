package com.leakhawk.classifier;

public class ClassifierResult {
	
	private boolean isDBPassed;
			
	private boolean isCCPassed;
	
	private boolean isPKPassed;

	private EvidenceClassifierResult evidenceClassifierResult;
	
	
	public boolean isDBPassed() {
		return isDBPassed;
	}

	public void setDBPassed(boolean isDBPassed) {
		this.isDBPassed = isDBPassed;
	}


	public boolean isCCPassed() {
		return isCCPassed;
	}

	public void setCCPassed(boolean isCCPassed) {
		this.isCCPassed = isCCPassed;
	}

	public boolean isPKPassed() {
		return isPKPassed;
	}

	public void setPKPassed(boolean isPKPassed) {
		this.isPKPassed = isPKPassed;
	}

	public EvidenceClassifierResult getEvidenceClassifierResult() {
		return evidenceClassifierResult;
	}

	public void setEvidenceClassifierResult(EvidenceClassifierResult evidenceClassifierResult) {
		this.evidenceClassifierResult = evidenceClassifierResult;
	}
			
	
	public boolean isContentClassifierPassed(){
		
		if( isCCPassed || isPKPassed || isDBPassed) {
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
