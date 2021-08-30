package roughExamples;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestCreateCustomerAPI1 {
	
	@Test
	public void validateCreateCustomerAPIWithValidData() {
		
		/*
		 * URI->https://api.stripe.com/v1/customers
		 * MethodType-POST
		 * argument-email,description,balance
		 * Authorize- HTTP Basic Auth. 
		 * key->sk_test_51JSKhsSIEtekbW52Hw6reFIeTdsDmB4R7qQroq
		 * F6PYud6hqifekc98blZxRQ6QanZPOKmRfATLIfsXBM3j5Ft8Hh00NTVbBkzK
		 * 
		 */
		RequestSpecification spec=given().auth().basic("sk_test_51JSKhsSIEtekbW52Hw6reFIeTdsDmB4R7qQroqF6PYud6hqifekc98blZxRQ6QanZPOKmRfATLIfsXBM3j5Ft8Hh00NTVbBkzK", "")
		.formParam("email", "sibraj@gmail.com").formParam("description", "superclass contractor")
		.formParam("balance",100000000).log().all();
		System.out.println("--------------------------------------------------");
		
		Response response=spec.post("https://api.stripe.com/v1/customers");
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(),200);
	}

}
