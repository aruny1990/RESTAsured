package Day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakeDataGenerator {

	@Test
	void testGenerateDummyData() {
		Faker faker = new Faker();
		String fullName = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		
		String username =faker.name().username();
		String password = faker.internet().password();
		
		String phoneno = faker.phoneNumber().cellPhone();
		String email = faker.internet().safeEmailAddress();
		
		System.out.println("Full name: "+fullName);
		System.out.println("Full name: "+firstName);
		System.out.println("Full name: "+lastName);
		System.out.println("Full name: "+username);
		System.out.println("Full name: "+password);	
		System.out.println("Full name: "+phoneno);
		System.out.println("Full name: "+email);
	}
}
