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


	//***************************** CC Classifier ************************/


	public boolean isCCPassed() {
		return isCCClassifierPassed;
	}

	public void setCCPassed(boolean isCCPassed) {
		this.isCCClassifierPassed = isCCPassed;
	}

	//***************************** PK Classifier ************************/

	public boolean isPKPassed() {
		return isPKClassifierPassed;
	}

	public void setPKPassed(boolean isPKPassed) {
		this.isPKClassifierPassed = isPKPassed;
	}

	//***************************** WD Classifier ************************/


	public boolean isWDPassed() {
		return isWDClassifierPassed;
	}

	public void setWDPassed(boolean isWDPassed) {
		this.isWDClassifierPassed = isWDPassed;
	}

	//***************************** CF Classifier ************************/


	public boolean isCFPassed() {
		return isCFClassifierPassed;
	}

	public void setCFPassed(boolean isCFPassed) {
		this.isCFClassifierPassed = isCFPassed;
	}

	//***************************** DB Classifier ************************/

	public boolean isDBPassed() {
		return isDBClassifierPassed;
	}

	public void setDBPassed(boolean isDBPassed) {
		this.isDBClassifierPassed = isDBPassed;
	}


	//***************************** UC Classifier ************************/

	public boolean isUCPassed() {
		return isUCClassifierPassed;
	}

	public void setUCPassed(boolean isUCPassed) {
		this.isUCClassifierPassed = isUCPassed;
	}

	//***************************** DA Classifier ************************/


	public boolean isDAPassed() {
		return isDAClassifierPassed;
	}

	public void setDAPassed(boolean isDAPassed) {
		this.isDAClassifierPassed = isDAPassed;
	}

	//***************************** EO Classifier ************************/


	public boolean isEOPassed() {
		return isEOClassifierPassed;
	}

	public void setEOPassed(boolean isEOPassed) {
		this.isEOClassifierPassed = isEOPassed;
	}

	//***************************** EA Classifier ************************/


	public boolean isEAPassed() {
		return isEAClassifierPassed;
	}

	public void setEAPassed(boolean isEAPassed) {
		this.isEAClassifierPassed = isEAPassed;
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
