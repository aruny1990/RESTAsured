package Day6;
//POJO -JSON - Serialization
//JSON - POJO - Deserialization
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Day2.POJO_PostRequest;
import io.restassured.matcher.RestAssuredMatchers;
public class SerializationDeserialization {

	//@Test
	void convertPojoJson() throws JsonProcessingException {
		POJO_PostRequest stupojo = new POJO_PostRequest();
		stupojo.setName("Scott");
		stupojo.setLocation("France");
		stupojo.setPhone("123456");
		
		String[] courses = {"C","C++"};
		stupojo.setCourses(courses);
		
		//Convert java object --> json object (serialization)
		ObjectMapper objMapper = new ObjectMapper();
		
		String jsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);
		
		System.out.println(jsonData);
	}
	
	@Test
	//Deserialization
	void convertJsonToPojo() throws JsonProcessingException {
		String jsonData = "{\r\n"
				+ "  \"name\" : \"Scott\",\r\n"
				+ "  \"location\" : \"France\",\r\n"
				+ "  \"phone\" : \"123456\",\r\n"
				+ "  \"courses\" : [ \"C\", \"C++\" ]\r\n"
				+ "}";
		
		//convert json Data into POJO class object
		ObjectMapper objMapper = new ObjectMapper();
		POJO_PostRequest stupojo = objMapper.readValue(jsonData, POJO_PostRequest.class);
		String name = stupojo.getName();
		String location = stupojo.getLocation();
		String phone = stupojo.getPhone();
		String[] courses = stupojo.getCourses();
		System.out.println("name is == " +name);
		System.out.println("location is == " +location);
		System.out.println("phone is == " +phone);
		System.out.println("courses 1 is == " +courses[0]);
		System.out.println("courses 2 is == " +courses[1]);
	}
}
