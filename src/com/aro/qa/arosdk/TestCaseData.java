package com.aro.qa.arosdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class TestCaseData {


	private int testCaseNumber;
	private List<String> testValue = new ArrayList<String>();
	private String testCaseDescription;
	private String[] expectedResult;
	
	
	
	public TestCaseData() {
		super();
		
	}
	
/***
 * 	
 * @param testCaseNumber
 * @param testValue
 * @param testCaseDescription
 * @param expectedResult
 */
	
	
	public TestCaseData(int testCaseNumber, List<String> testValue, String testCaseDescription, String[] expectedResult) {
		super();
		this.testCaseNumber = testCaseNumber;
		this.testCaseDescription = testCaseDescription;
		this.testValue = testValue;
		this.expectedResult = expectedResult;
	}	
	
	public int getTestCaseNumber() {
		return testCaseNumber;
	}
	public void setTestCaseNumber(int testCaseNumber) {
		this.testCaseNumber = testCaseNumber;
	}
	public String getTestCaseDescription() {
		return testCaseDescription;
	}
	public void setTestCaseDescription(String testCaseDescription) {
		this.testCaseDescription = testCaseDescription;
	}
	public List<String> getTestValue() {
		return testValue;
	}
	public void setTestValue(List<String> testValue) {
		this.testValue = testValue;
	}
	public String[] getExpectedResult() {
		return expectedResult;
	}
	public void setExpectedResult(String[] expectedResult) {
		this.expectedResult = expectedResult;
	}

	public Set<String> getExpectedResultAsHashSet(){
		Set<String> expectedResultSet = new HashSet<String>(Arrays.asList(this.expectedResult)); 
		return expectedResultSet;
		
	}

	public boolean expectedContainsString(String stringToSearch){
		Set<String> expectedResultsSet = new HashSet<String>(Arrays.asList(this.expectedResult));
		
		return expectedResultsSet.contains(stringToSearch);
		
	}
	
	public boolean expectedContainsStrings(String[] stringArray){
		Set<String> expectedResultsSet = new HashSet<String>(Arrays.asList(this.expectedResult));
		Set<String> actualResults = new HashSet<String>(Arrays.asList(stringArray));
		return expectedResultsSet.containsAll(actualResults);
		
	}
	
	
}
