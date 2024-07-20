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
public class UpdateUser {

	@Test
	void test_updateUser(ITestContext context) {
		Faker faker = new Faker();
		JSONObject data = new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		String bearerToken = "c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";
		
		int id = (Integer) context.getAttribute("user_id");
				given()
				.headers("Authorization","Bearer "+bearerToken)
				.contentType("application/json")
				.pathParam("id", id)
				.body(data.toString())
				
				.when()
				.put("https://gorest.co.in/public/v2/users/{id}")
				.then()
				.statusCode(200).log().all();
		
	}
}
