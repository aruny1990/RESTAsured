package Day5;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import org.testng.annotations.Test;
public class ParsingXMLResponse {

	//@Test
	void testXMLResponse() {
		//Approach 1
		given()
		
		.when()
			.get("https://www.w3schools.com/xml/simple.xml")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "text/xml")
			.body("breakfast_menu.food[0].name", equalTo("Belgian Waffles"))
			.body("breakfast_menu.food[0].price", equalTo("$5.95"))
		;
	}
	
	//@Test
	void testXMLResponseApproach2() {
		//Approach 1
		Response res = given()
		
		.when()
			.get("https://www.w3schools.com/xml/simple.xml");
		
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.header("Content-Type"),"text/xml");
		String name1 = res.xmlPath().get("breakfast_menu.food[0].name").toString();
		Assert.assertEquals(name1, "Belgian Waffles");
		String price1 = res.xmlPath().get("breakfast_menu.food[0].price").toString();
		Assert.assertEquals(price1, "$5.95");
//		.then()
//			.statusCode(200)
//			.header("Content-Type", "text/xml")
//			.body("breakfast_menu.food[0].name", equalTo("Belgian Waffles"))
//			.body("breakfast_menu.food[0].price", equalTo("$5.95"))
//		;
	}
	
	@Test
	void testXMLResponseApproach3() {
		//Approach 1
		Response res = given()
		
		.when()
			.get("https://www.w3schools.com/xml/simple.xml");
		
		XmlPath xmlObj = new XmlPath(res.asString());
		List<String> foods = xmlObj.getList("breakfast_menu.food");
		System.out.println("Total foods are == "+foods.size());
		Assert.assertEquals(foods.size(), 5);
		List<String> names = xmlObj.getList("breakfast_menu.food.name");
		boolean status = false;
		//Verify food name is present in the list
		for(String name:names) {
			//System.out.println("name is "+name);
			if(name.equals("Belgian Waffles")) {
				status = true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
	}
}
