package com.w24.API_Proj.API;

import java.util.Hashtable;

import com.w24.API_Proj.TestUtils.TestUtil;
import com.w24.API_Proj.setUp.APISetUp;

import io.restassured.response.Response;

public class CustomerAPI extends APISetUp {

	public static Response sendPostRequestWithValidDataWithArguments(Hashtable<String,String>data) {
		
		Response response=TestUtil.setFormParam(data.get("arguments"),setRequestSpecification()).post(data.get("endpoint"));
		return response;
	}
}
