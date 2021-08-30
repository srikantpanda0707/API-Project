package roughExamples;

import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.w24.API_Proj.TestUtils.DataProviderClass;
import com.w24.API_Proj.setUp.APISetUp;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestCreateCustomerAPI2 extends APISetUp{
	
	@Test(dataProviderClass=DataProviderClass.class,dataProvider="dp",enabled=false)
	public void validateCreateCustomerAPI(Hashtable<String,String> data) {
		
		/*
		 * URI->https://api.stripe.com/v1/customers
		 * MethodType-POST
		 * argument-email,description,balance
		 * Authorize- HTTP Basic Auth. 
		 * key->sk_test_51JSKhsSIEtekbW52Hw6reFIeTdsDmB4R7qQroq
		 * F6PYud6hqifekc98blZxRQ6QanZPOKmRfATLIfsXBM3j5Ft8Hh00NTVbBkzK
		 * 
		 */
		testLevelLog.get().assignAuthor("Rahul");
		testLevelLog.get().assignCategory("Regression");
		RequestSpecification spec=setRequestSpecification().formParam("email",data.get("email"))
		.formParam("description", data.get("description"))
		.formParam("balance",100000000).log().all();
		System.out.println("--------------------------------------------------");
		
		Response response=spec.post("customers");
		testLevelLog.get().info(response.asString());
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(),200);
		
		//fetch the email from response 
		String emailInTheResponse = response.path("email");
		System.out.println("Email in response:- "+emailInTheResponse);
		String descriptionInResponse=response.path("description");
		System.out.println("description in response:- "+descriptionInResponse);
		
		System.out.println("Footer-->"+response.path("invoice_settings.footer"));
		
		//or use JsonPath
		JsonPath responseJson=new JsonPath(response.asString());
		System.out.println("Email using Json path-> "+responseJson.get("email "));
		
		//System.out.println("URL under Subscription->"+response.path("subscription.url"));
		//fetch the description from response
	}
	
	
	@Test(dataProviderClass=DataProviderClass.class,dataProvider="dp",enabled=false)
	public void fetchCustomers(Hashtable<String,String> data) {
		
		testLevelLog.get().assignAuthor("Rahul");
		testLevelLog.get().assignCategory("Regression");
		//to limit add .formParam("limit",data.get("limit"))
		RequestSpecification spec=setRequestSpecification().log().all();
		System.out.println("--------------------------------------------------");
		
		
		
		Response response=spec.request(data.get("methodType"),data.get("endPoint"));
		/*response.prettyPrint();
		
		System.out.println("Size of Data "+response.path("data.size()"));
		System.out.println("Size of Data inside data "+response.path("data[0].size()"));
		
		ArrayList<String> listOfIds=response.path("data.id");
		System.out.println(listOfIds);*/
		
		//fetch the customerId of the customer whose (default_source:card_1JTj6QSIEtekbW52VP4c5uz1)
				int lengthOfData=response.path("data.size()");
				String expectedDefaultSource="card_1JTj6QSIEtekbW52VP4c5uz1";
				
				for(int i=0;i<lengthOfData;i++) {
					String actualDefaultSource=response.path("data["+i+"].default_source");
					if(expectedDefaultSource.equals(actualDefaultSource))
					{
						System.out.println(response.path("data["+i+"].id"));
					
						break;
					}
				}
	}
	@Test
	public void parseJsonUsingPath() {
		String json="\"{\"destination_addresses\":[\"Philadelphia, PA, USA\"],\"origin_addresses\":[\"New York, NY, USA\"],\"rows\":[{\"elements\":[{\"distance\":{\"text\":\"94.6 mi\",\"value\":152193},\"duration\":{\"text\":\"1 hour 44 mins\",\"value\":6227},\"status\":\"OK\"}]}],\"status\":\"OK\"}";
		JsonPath path=new JsonPath(json);
		path.get("rows.elements.duration.value");
	}
	@Test(dataProviderClass=DataProviderClass.class,dataProvider="dp")
	public void validateDeleteCustomerAPI(Hashtable<String,String> data) {
		
	}

}
