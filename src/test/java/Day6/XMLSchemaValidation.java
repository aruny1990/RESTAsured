package Day6;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
public class XMLSchemaValidation {

	@Test
	void xmlSchemaValidation() {
		given()
		
		.when()
			.get("https://www.w3schools.com/xml/simple.xml")
		.then().assertThat()
			.body(RestAssuredMatchers.matchesXsdInClasspath("foods.xsd"));
		
			
			;
	}
	
}
