package Day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;
import java.util.Set;

public class CookieDemo {

	//@Test
	void testCookies() {
		given()
		
		.when()
			.get("https://google.com")
		.then()
		.cookie("AEC","AVYB7crBsVkmoALitXESHgBsXr6A4Z-NjqAZsCYFIGyDXs-jau1XLTRIwHY")
		.log().all();
	}
	
	@Test
	void getCookiesInfo() {
		Response res = given()
		
		.when()
				.get("https://google.com/");
		
		//get single cookies information
		String ccokie_value = res.getCookie("AEC");
		System.out.println("value of cookies is "+ccokie_value);
		//= res.getCookie("NID");
		
		//get all cookies info
		Map<String, String> cookies_value = res.getCookies();
		//.then();
		//Set<String> keys = cookies_value.keySet();
		System.out.println(cookies_value.keySet());
		
		for(String k:cookies_value.keySet()) {
			String cookie_value = res.getCookie(k);
			System.out.println(k+"   "+cookies_value);
		}
	}
}
