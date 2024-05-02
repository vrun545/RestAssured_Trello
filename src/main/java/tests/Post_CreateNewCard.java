package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import reports.ExtentManager;

import static io.restassured.RestAssured.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Properties.ReadConfigFile;

public class Post_CreateNewCard {

	 	private static final Logger logger = LogManager.getLogger(Post_CreateNewCard.class);
		private ReadConfigFile readConfigFile;
	    private String key;
	    private String token;
	    private String cardId;
	    private ExtentReports extent;
		private ExtentTest test;
	        
		@BeforeTest
	    public void setup() {
	        readConfigFile = new ReadConfigFile();
	        key = readConfigFile.getAPI_KEY();
	        token = readConfigFile.getToken();        
	        extent = ExtentManager.getInstance();
		    extent.setSystemInfo("OS", "Windows 11");
	        baseURI = "https://api.trello.com/1"; 
	    }
		
		@Test(priority=1)
		public void CreateNewCard() {
		test = extent.createTest("Creating a new card");
	    test.log(Status.INFO, "Try Creating a new card");
		logger.info("Creating a new card...");
        RestAssured.baseURI = "https://api.trello.com/1";
        Response response = given()
        		.header("Content-Type", "application/json")
        		.contentType(ContentType.JSON)
        		.accept(ContentType.JSON)
                .queryParam("name", "Test Card - 8")
                .queryParam("desc", "This is a test card number - 8")
                .queryParam("idList", "661e6c43f14584a6dbf1348b")
                .queryParam("key", key)
                .queryParam("token", token)
                .when()
                .post("/cards")
                .then().log().all().extract().response();
        
        int statusCode = response.getStatusCode();
        test.info("Status Code: " + statusCode);

        Assert.assertEquals(response.getStatusCode(), 200, "Card is created successfully");
        cardId = response.jsonPath().get("id");
        logger.info("New card created with ID: " + cardId);
		}
		
		@AfterTest
		public void tearDownTest() {
			ExtentManager.flushReport();
		}
}
