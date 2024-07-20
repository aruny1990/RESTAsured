package Day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class DifferentWaysToCratePostrequestBody {

	//@Test
	void postRequestBodyUsingHashMap() {
		HashMap data = new HashMap();
		data.put("name","scott");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String courseArr[] = {"C","C++"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name", equalTo("scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]",equalTo("C"))
		.body("courses[1]",equalTo("C++"))
		.header("Content-Type", equalTo("application/json"))
		.log().all()
		;
	}
	
	//@Test(priority=2)
	void deleteRequest() {
		given()
		
		.when()
		.delete("http://localhost:3000/students/f4e9")
		.then().statusCode(200);
		
	}
	
	//@Test
	void postRequestUsingOrgJsonLibrary() {
		JSONObject data = new JSONObject();
		data.put("name","Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String courseArr[] = {"C","C++"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]",equalTo("C"))
		.body("courses[1]",equalTo("C++"))
		.header("Content-Type", equalTo("application/json"))
		.log().all()
		;
	}
	
	//3 Create POJO Class
	//@Test
	void postRequestBodyUsingPOJO() {
		POJO_PostRequest data = new POJO_PostRequest();
		data.setName("Scott");
		data.setLocation("France");
		data.setPhone("123456");
		
		String[] courses = {"C","C++"};
		data.setCourses(courses);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]",equalTo("C"))
		.body("courses[1]",equalTo("C++"))
		.header("Content-Type", equalTo("application/json"))
		.log().all()
		;
	}
	
	//Post request body using external json file
	@Test
	void postRequestBodyUsingExternalFile() throws FileNotFoundException {
		File f = new File(".\\body.json");
		FileReader fr = new FileReader(f);
		JSONTokener jd = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jd);
		data.put("name","Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String courseArr[] = {"C","C++"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("123456"))
		.body("courses[0]",equalTo("C"))
		.body("courses[1]",equalTo("C++"))
		.header("Content-Type", equalTo("application/json"))
		.log().all()
		;
	}
}
