package com.leakhawk.classifier;

public class EvidenceClassifierResult {

	//General one
	private boolean isEvidencePassed;

	private boolean isUserExists;

	private boolean isClassifier1Passed;
	private boolean isClassifier2Passed;
	private boolean isClassifier3Passed;
	private boolean isClassifier4Passed;
	private boolean isClassifier5Passed;
	private boolean isClassifier6Passed;
	private boolean isClassifier7Passed;
	private boolean isClassifier8Passed;

	public boolean isEvidencePassed() {

		if( isUserExists || isClassifier1Passed || isClassifier2Passed || isClassifier3Passed || isClassifier4Passed || isClassifier5Passed || isClassifier6Passed || isClassifier7Passed || isClassifier8Passed) {
			setEvidencePassed(true);
		} else{
			setEvidencePassed(false);
		}

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

	public boolean isClassifier1Passed() {
		return isClassifier1Passed;
	}

	public void setClassifier1Passed(boolean isClassifier1Passed) {
		this.isClassifier1Passed = isClassifier1Passed;
	}

	public boolean isClassifier2Passed() {
		return isClassifier2Passed;
	}

	public void setClassifier2Passed(boolean isClassifier2Passed) {
		this.isClassifier2Passed = isClassifier2Passed;
	}

	public boolean isClassifier3Passed() {
		return isClassifier3Passed;
	}

	public void setClassifier3Passed(boolean isClassfier3Passed) {
		this.isClassifier3Passed = isClassfier3Passed;
	}

	public boolean isClassifier4Passed() {
		return isClassifier4Passed;
	}

	public void setClassifier4Passed(boolean isClassfier4Passed) {
		this.isClassifier4Passed = isClassfier4Passed;
	}

	public boolean isClassifier5Passed() {
		return isClassifier5Passed;
	}

	public void setClassifier5Passed(boolean isClassfier5Passed) {
		this.isClassifier5Passed = isClassfier5Passed;
	}

	public boolean isClassifier6Passed() {
		return isClassifier6Passed;
	}

	public void setClassifier6Passed(boolean isClassfier6Passed) {
		this.isClassifier6Passed = isClassfier6Passed;
	}

	public boolean isClassifier7Passed() {
		return isClassifier7Passed;
	}

	public void setClassifier7Passed(boolean isClassfier7Passed) {
		this.isClassifier7Passed = isClassfier7Passed;
	}

	public boolean isClassifier8Passed() {
		return isClassifier8Passed;
	}

	public void setClassifier8Passed(boolean isClassfier8Passed) {
		this.isClassifier8Passed = isClassfier8Passed;
	}
}
