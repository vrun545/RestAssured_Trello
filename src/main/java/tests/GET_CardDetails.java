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
import io.restassured.response.Response;
import reports.ExtentManager;
import resources.Properties.ReadConfigFile;

public class GET_CardDetails {

    private static final Logger logger = LogManager.getLogger(GET_CardDetails.class);
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
    
    @Test(priority=2)
    public void GetCardDetails() {
        test = extent.createTest("Fetching details of requested card");
        test.log(Status.INFO, "Try fetching details of a card");
        logger.info("Fetching Details of Requested Card...");
        RestAssured.baseURI = "https://api.trello.com/1";
        Response response = given()
                .queryParam("key", key)
                .queryParam("token", token)
                .when()
                .get("/cards/661e91cb45aca42df900cfbe")
                .then().log().all().extract().response();

        int statusCode = response.getStatusCode();
        test.info("Status Code: " + statusCode);
        
        Assert.assertEquals(response.getStatusCode(), 200, "Card details are found");
        cardId = response.jsonPath().get("id");
        logger.info("Card Details are found: " + cardId);
    }
    
    @AfterTest
    public void tearDownTest() {
        ExtentManager.flushReport();
    }
}
