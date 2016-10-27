package com.leakhawk.classifier;

public class EvidenceClassifierResult {

	//General one
	private boolean isEvidencePassed;
	
	private boolean isUserExists;
	
	private boolean isClassfier1Passed;
	
	private boolean isClassfier2Passed;
	
	private boolean isClassfier3Passed;

	public boolean isEvidencePassed() {
		return isEvidencePassed;
	}

	public void setEvidencePassed(boolean isEvidencePassed) {
		this.isEvidencePassed = isEvidencePassed;
	}

	public boolean isUserExists() {
		return isUserExists;
	}

	public void setUserExists(boolean isUserExists) {
		this.isUserExists = isUserExists;
	}

	public boolean isClassfier1Passed() {
		return isClassfier1Passed;
	}

	public void setClassfier1Passed(boolean isClassfier1Passed) {
		this.isClassfier1Passed = isClassfier1Passed;
	}

	public boolean isClassfier2Passed() {
		return isClassfier2Passed;
	}

	public void setClassfier2Passed(boolean isClassfier2Passed) {
		this.isClassfier2Passed = isClassfier2Passed;
	}

	public boolean isClassfier3Passed() {
		return isClassfier3Passed;
	}

	public void setClassfier3Passed(boolean isClassfier3Passed) {
		this.isClassfier3Passed = isClassfier3Passed;
	}
	
	
}
