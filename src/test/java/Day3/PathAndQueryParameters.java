package Day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters {

	@Test
	void testQueryPathParameters() {
		given()
		.pathParam("mypath1","api") //path parametrs are considered as variables
			.pathParam("mypath2","users") 
			.queryParam("page", 2) //query parameter
			.queryParam("id", 5) //query parameter
		.when()
			.get("https://reqres.in/{mypath1}/{mypath2}")
		
		.then()
		.statusCode(200).log().all();
	}
}
