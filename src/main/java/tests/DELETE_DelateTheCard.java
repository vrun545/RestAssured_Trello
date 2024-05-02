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

public class DELETE_DelateTheCard {

	private static final Logger logger = LogManager.getLogger(DELETE_DelateTheCard.class);
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
	
	@Test(priority=5)
	public void DeleteTheCard() {
	test = extent.createTest("Deleting a card");
    test.log(Status.INFO, "Try Deleting a card");
	logger.info("Deleting a card...");
    RestAssured.baseURI = "https://api.trello.com/1";
    Response response = given()
    		.queryParam("key", key)
            .queryParam("token", token)
            .when()
            .delete("/cards/661ff06cdb5b84cf7d25a35a");
    
    int statusCode = response.getStatusCode();
    test.info("Status Code: " + statusCode);

    Assert.assertEquals(response.getStatusCode(), 200, "Card is deleted successfully");
    cardId = response.jsonPath().get("id");
    logger.info("Card ID: " + cardId);
	}
	
	@AfterTest
	public void tearDownTest() {
		ExtentManager.flushReport();
	}
}
