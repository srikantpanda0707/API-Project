package com.w24.API_Proj.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListeners implements IRetryAnalyzer{

	int count=0;
	int maxRetry=3;
	
	public boolean retry(ITestResult result) {
		if(count<maxRetry)
		{
			count++;
			return true;
		}
		else
		return false;
	}
	
	

}
