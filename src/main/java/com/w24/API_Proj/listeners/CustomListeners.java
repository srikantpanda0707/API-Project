package com.w24.API_Proj.listeners;

import java.util.Arrays;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.w24.API_Proj.setUp.APISetUp;

public class CustomListeners extends APISetUp implements ITestListener{

	public void onTestStart(ITestResult result) {

		ExtentTest test=classLevelLog.get().createNode(result.getName());
		testLevelLog.set(test);
		testLevelLog.get().info("<b>"+"Test Case:- "+result.getName()+" execution started"+"</b>");
		
		//System.out.println("Test Case:- "+result.getName()+" execution started");
		
	}

	public void onTestSuccess(ITestResult result) {
		testLevelLog.get().pass("<b>"+"This test case got passed"+"</b>");
		extentReport.flush();
		
	}

	public void onTestFailure(ITestResult result) {
		String exceptionMessage= Arrays.toString(result.getThrowable().getStackTrace());
		testLevelLog.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>"
				+ " \n");
		
		String failureLogg = "This Test case got Failed";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testLevelLog.get().log(Status.FAIL, m);
		
		extentReport.flush();
	}

	public void onTestSkipped(ITestResult result) {
		testLevelLog.get().skip("this testcase got skipped");
		extentReport.flush();
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
//      
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	
}
