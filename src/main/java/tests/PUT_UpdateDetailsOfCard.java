package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import reports.ExtentManager;
import resources.Properties.ReadConfigFile;

public class PUT_UpdateDetailsOfCard {

	private static final Logger logger = LogManager.getLogger(PUT_UpdateDetailsOfCard.class);
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
        baseURI = "https://api.trello.com/1"; 
    }
	
	@Test(priority=4)
	public void UpdateTheCard() {
	test = extent.createTest("Updating details of a card");
    test.log(Status.INFO, "Try Updating card details");
	logger.info("Updating a card details...");
    RestAssured.baseURI = "https://api.trello.com/1";
    Response response = given()
    		.header("Content-Type", "application/json")
    		.contentType(ContentType.JSON)
    		.accept(ContentType.JSON)
    	    .body("{ \"name\": \"The New Test Card\", \"desc\": \"This is my new updated card\" }")
            .queryParam("key", key)
            .queryParam("token", token)
            .when()
            .put("/cards/661e91cb45aca42df900cfbe");
    
    int statusCode = response.getStatusCode();
    test.info("Status Code: " + statusCode);

    Assert.assertEquals(response.getStatusCode(), 200, "Card is updated successfully");
    cardId = response.jsonPath().get("id");
    logger.info("Card ID: " + cardId);
	}
	
	@AfterTest
	public void tearDownTest() {
		ExtentManager.flushReport();
	}
}
