package Day3;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class LoggingDemo {

	@Test
	void testLogs() {
		given()
		
		.when()
			.get("https://google.com")
		
		.then().log().status();
		//.headers();
		//.cookies();
		//.body();
		//.all();
	}
}
