package UploadDownloadFile;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;

import org.testng.annotations.Test;
public class UploadDownloadFile {

	@Test
	void downloadFile() throws IOException {
		Response response = given().when().get("http://localhost:3000/store");
		byte[] bytes = response.getBody().asByteArray();
		File file = new File(".\\storeFile.json");
		Files.write(file.toPath(), bytes);
		
	}
	
	@Test
	void uploadFile() {
		File f = new File(".\\test.PNG");
		
		Response response = given().multiPart("file",f,"multipart/form-data")
				.post("https://the-internet.herokuapp.com/upload");
		
		System.out.println(response.prettyPrint());
	}
}
