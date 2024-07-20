package Day1;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
/*given() - pre-requisiite or precondition - Before sending any API request what type of content

* Authentication, content-Type, path param, query param, cookies,headers passed along with the requests

 

when() - request type - get,post,put,delete

 

then() - All validations we have to do under the then condition

*/

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.response.ResponseBody;
/*given() - pre-requisiite or precondition - Before sending any API request what type of content

* Authentication, content-Type, path param, query param, cookies,headers passed along with the requests

 

when() - request type - get,post,put,delete

 

then() - All validations we have to do under the then condition

*/
 public class HttpRequests {

	 public int id =0;
	 @Test(priority=1)
	 void getUsers() {
		 given().
		 when().get("https://reqres.in/api/users?page=2").
		 then().statusCode(200).body("page",equalTo(2)).log().all();
		 
		 
	 }
	 
	 @Test(priority = 2)
	 void createUser() {
		 HashMap hm = new HashMap();
		 hm.put("name", "pavan");
		 hm.put("job", "trainer");		
		 id = given().contentType("application/json")
		 .body(hm)		 .
		 when().	 
		 post("https://reqres.in/api/users")
		 .jsonPath().getInt("id");
		//.then().statusCode(201)
		
		System.out.println("====id is ======"+id);
	 }
	 
	 @Test(priority=3,dependsOnMethods = "createUser" )
	 void updateUser() {
		 HashMap hm = new HashMap();
		 hm.put("name", "pavan");
		 hm.put("job", "Engineer");		
		 given().contentType("application/json")
		 .body(hm)		 .
		 when().	 
		 put("https://reqres.in/api/users/"+id)
		 .then().statusCode(200).log().all();
		 
	 }
	 
	 @Test(priority=4)
	 void DeleteUser() {
		 given()
		 .when().delete("https://reqres.in/api/users/"+id).then().statusCode(204).log().all();
	 }
	
}
