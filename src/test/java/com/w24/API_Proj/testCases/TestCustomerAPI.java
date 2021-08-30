package com.w24.API_Proj.testCases;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import com.aventstack.extentreports.gherkin.model.Given;
import com.w24.API_Proj.API.CustomerAPI;
import com.w24.API_Proj.TestUtils.DataProviderClass;
import com.w24.API_Proj.setUp.APISetUp;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestCustomerAPI extends APISetUp{
	
	@Test(dataProviderClass=DataProviderClass.class,dataProvider="dp",priority=1,enabled=true)
	public void validateCreateCustomerAPIWithValidData(Hashtable<String,String> data)
	{
		testLevelLog.get().assignAuthor("Srikant");
		testLevelLog.get().assignCategory("Sanity");
		
		Response response=CustomerAPI.sendPostRequestWithValidDataWithArguments(data);
		response.prettyPrint();
		//Integer.parseInt(data.get("expectedStatusCode"))
		Assert.assertEquals(response.statusCode(),200);
		Assert.assertEquals(response.jsonPath().get("email"), data.get("expectedEmail"));
		//Assert.assertEquals(response.statusLine(), "created");
		
		/*Response response=setRequestSpecification().formParam("email", data.get("email"))
		.formParam("description", data.get("description")).post(data.get("endpoint"));*/
	}
	/*
	@Test(dataProviderClass=DataProvider.class,dataProvider="dp",priority=0,enabled=true)
	public void validateCreateCustomerAPIWithInValidAuthKey(Hashtable<String,String> data)
	{
		
	}
	
	@Test(dataProviderClass=DataProvider.class,dataProvider="dp",priority=4,enabled=true)
	public void validateCreateCustomerAPIWithInValidEmail(Hashtable<String,String> data)
	{
		
	}
	
	@Test(dataProviderClass=DataProvider.class,dataProvider="dp",priority=2,enabled=true)
	public void validateCreateCustomerAPIWithoutPassingAuthKey(Hashtable<String,String> data)
	{
		
	}
	
	@Test(dataProviderClass=DataProvider.class,dataProvider="dp",priority=3,enabled=true)
	public void validateCreateCustomerAPIWithInvalidField(Hashtable<String,String> data)
	{
		
	}*/
}
