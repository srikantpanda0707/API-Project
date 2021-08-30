package com.w24.API_Proj.setUp;

import static io.restassured.RestAssured.given;

import java.lang.reflect.Method;

import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.w24.API_Proj.TestUtils.ConfigProperty;
import com.w24.API_Proj.TestUtils.ExcelReader;
import com.w24.API_Proj.TestUtils.ExtentManager;
import com.w24.API_Proj.TestUtils.TestUtil;

import  io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;


public class APISetUp {
	
	
	public static ConfigProperty configProperty;
	
	public static ExcelReader excel=new ExcelReader(System.getProperty("user.dir")+"/src/test/resources/testData/API_TestData.xlsx");

	public static ExtentReports extentReport;
	public static ThreadLocal<ExtentTest>classLevelLog=new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest>testLevelLog=new ThreadLocal<ExtentTest>();
	
	@BeforeSuite
	public void beforeSuite() {
		
		ConfigFactory.setProperty("environment",System.getProperty("Environment"));
		configProperty=ConfigFactory.create(ConfigProperty.class);
		RestAssured.baseURI=configProperty.getBaseURI();
		RestAssured.basePath=configProperty.getBasePath();
		TestUtil.archiveTestReport();
		extentReport=ExtentManager.GetExtent(configProperty.getTestFilepath()+configProperty.getTestReportName());
	}
	@BeforeClass
	public void beforeClass() {
		//ExtentTest test=new ExtentTest(getClass().getSimpleName());
		ExtentTest classLevelTest=extentReport.createTest(getClass().getSimpleName());
		classLevelLog.set(classLevelTest);
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		
		/*ExtentTest test=classLevelLog.get().createNode(method.getName());
		testLevelLog.set(test);
		testLevelLog.get().info("Test Case:- "+method.getName()+" execution started");
		*/
		System.out.println("Test Case:- "+method.getName()+" execution started");
	}
	@AfterMethod
	public void afterMethod(ITestResult result) {
		/*if(result.getStatus()==ITestResult.SUCCESS){
			testLevelLog.get().pass("Test case passed");
			System.out.println("This testcase passed");
		}
		else if(result.getStatus()==ITestResult.FAILURE) {
			testLevelLog.get().fail("this test is failed");
			System.out.println("test case failed");
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			testLevelLog.get().skip("test case skipped");
			System.out.println("test skipped");
		}
		extentReport.flush();*/
	}
	@AfterSuite
	public void afterSuite() {
		
	}
	
	public static RequestSpecification setRequestSpecification() {
		RequestSpecification spec=given().auth().basic(configProperty.getSecretKey(),"");
		
		
		testLevelLog.get().info("Request specification set");
		return spec;
	}
}
