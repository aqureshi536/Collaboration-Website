package com.ahmad.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Test {
	@Id
	private String testId;
	private String testName;
	private Timestamp testDate;

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public Timestamp getTestDate() {
		return testDate;
	}

	public void setTestDate(Timestamp testDate) {
		this.testDate = testDate;
	}

}
