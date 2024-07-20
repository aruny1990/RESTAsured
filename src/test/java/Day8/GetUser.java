package Day8;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import io.restassured.response.Response;
public class GetUser {

	@Test
	void test_getUser(ITestContext context) {
		 int id = (Integer) context.getAttribute("user_id");
		String bearerToken = "c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.pathParam("id", id)
			.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		.then().statusCode(200).log().all();
	}
	
}
