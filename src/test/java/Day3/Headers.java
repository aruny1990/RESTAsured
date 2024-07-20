package Day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.response.Response;

public class Headers {

	//@Test
	void testHeaders() {
		given()
		
		.when()
			.get("https://google.com")
		
		.then()
		.header("Content-Type", "text/html; charset=ISO-8859-1")
		.and()
		.header("Content-Encoding", "gzip")
		.and()
		.header("server", "gws")
		;
	}
	
	@Test(priority=2)
	void getHeaders() {
		Response res = given()
		
		.when()
			.get("https://google.com")
		
//		.then()
//		.header("Content-Type", "text/html; charset=ISO-8859-1")
//		.and()
//		.header("Content-Encoding", "gzip")
//		.and()
//		.header("server", "gws")
		;
		
		//get single header info
		String headervalue = res.getHeader("Content-Type");
		System.out.println("The value of Content-Type header is "+headervalue);
		
		//get all headers info
		io.restassured.http.Headers myheaders = res.getHeaders();
		
		for(Header hd:myheaders) {
			System.out.println(hd.getName()+"   "+hd.getValue());
		}
	}
}


