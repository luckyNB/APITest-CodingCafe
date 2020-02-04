package com.codingcafe;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CodingCafeAPITestsClass {
    public String token = null;

    @Before
    public void setUp() throws Exception {
        token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUUEiLCJlbWFpbElkIjoidGVzdGluZ3Rlc3RkYXRhYmx6QGdtYWlsLmNvbSIsImlkIjoiNWRjZmRiYmRjNTgzNmIwMDA2OTc4YWFhIiwiZXhwIjoxNTgwOTA0ODc2fQ.eVwun7ECTr1U6KFqqIYEpJWcNKXvA5wNw31pIbdfWCY";
        RestAssured.baseURI = "https://cccampdash.incubation.bridgelabz.com";

    }

    @Test
    public void givenValidToken_WhenCorrect_ThenShould_ReturnAllCounts() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token", token)
                .when()
                .get("/campaign/dashboard");
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("message", Matchers.equalTo("Data Retrieved Successfully."));
    }

    @Test
    public void givenShortName_WhenAvailable_ThenShould_DisplayCompaignDetails() {
        Response response = given()
                .param("campaignShortName", "ABH")
                .when()
                .get("/campaign/dashbaord/campaign");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void givenToken_And_InstitutionDetails_WhenCorrect_ThenShouldAddInstitutionDetailsToDatabase() {
JSONObject institution=new JSONObject();
/*institution.put("")*/
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("token",token)
                .when()
                .get("/campaign/dashboard/institutions");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
    }
}
/*
{
        "address": "RajKot",
        "branchName": "Rampur",
        "city": "Raipur",
        "institutionName": "Rao Institute",
        "institutionType": "Royal",
        "shortName": "RAI",
        "url": "http://www.google.com"
        }*/
