package com.w24.API_Proj.testCases;

import java.util.HashMap;
import java.util.Hashtable;
import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;

import com.w24.API_Proj.Pojo.User;
import com.w24.API_Proj.TestUtils.DataProviderClass;
import com.w24.API_Proj.setUp.APISetUp;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestCreateUserAPI extends APISetUp{
	
	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "dp")
	public void validateCreateUserAPIWithValidData(Hashtable<String, String> data) {
	    //pojo approach
		//User user = new User(data.get("name"), data.get("job"));
		//hashmap approarch
		HashMap<String, String> map= new HashMap<String, String>();
		map.put("name",data.get("name"));
		map.put("job", data.get("job"));
		
		Response response = given().contentType(ContentType.JSON).body(map).post("https://reqres.in/api/users");
		response.prettyPrint();

	}

}
