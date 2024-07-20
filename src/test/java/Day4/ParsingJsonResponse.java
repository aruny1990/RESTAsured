package Day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;

import org.testng.annotations.Test;
public class ParsingJsonResponse {
	//Approach 1 - add vlaidations in then section
	//@Test(priority=1)
	void testJsonResponseAppproach1() {
		//Approach
		given()
		.contentType("ContentType.JSON")
		.when()
			.get("http://localhost:3000/store")
			
		.then()
		.statusCode(200)
		.header("Content-Type", "application/json")
		.body("book[3].title", equalTo("The Lord of the Rings"));
		;
		
			}
	
	//@Test(priority=2)
	void testJsonResponseApproach2() {	
		//Approach 2 - getting the values from response body it is having more advantage because we can traverse through entire body
		Response res = given()
			.contentType(ContentType.JSON)
		
			.when()
				.get("http://localhost:3000/store");
		
		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		String bookName = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		Assert.assertEquals(bookName, "The Lord of the Rings");
	}
	

	@Test(priority=3)
	void fetchallElementsFromJson() {
		Response res = given()
				.contentType(ContentType.JSON)
			
				.when()
					.get("http://localhost:3000/store");
		JSONObject jo = new JSONObject(res.asString()); //Converting to JSON object type
	//	System.out.println("Title are == \n");
		boolean status = false;
		for(int i=0;i<jo.getJSONArray("book").length();i++) {
			String title = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
//			System.out.println(title);
//			System.out.println();
			if(title.equals("The Lord of the Rings")) {
				status = true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
		double totalPrice=0;
		//Validation 2 - validate total price of books
		for(int i=0;i<jo.getJSONArray("book").length();i++) {
			String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
//			System.out.println(title);
//			System.out.println();
			double currentPrice = Double.parseDouble(price);
			totalPrice = totalPrice+currentPrice;
					}
		System.out.println("Total Price of the books is = "+totalPrice);
	
	}
	
}
