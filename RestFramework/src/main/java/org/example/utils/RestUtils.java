package org.example.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.example.reporting.ExtentReportManager;

import java.util.HashMap;
import java.util.Map;

public class RestUtils {

    private static RequestSpecification requestSpecification(String baseURI, String reqBody, Map<String, String> headers) {
        String methodName = Config.getInstance().method;
        if (methodName.equals("GET")) {
            return RestAssured.given()
                    .baseUri(baseURI)
                    .contentType(ContentType.JSON)
                    .headers(headers);
        }
        return RestAssured.given()
                .baseUri(baseURI)
                .contentType(ContentType.JSON)
                .headers(headers)
                .body(reqBody);
    }

    public static Response callMethod() {
        RequestSpecification requestSpecification = requestSpecification(Config.getInstance().getBaseURI(),
                Config.getInstance().getReqBody(),
                Config.getInstance().headers);
        Response response = null;
        switch (Config.getInstance().method) {
            case "GET":
                response = requestSpecification
                        .get(Config.getInstance().getRelURL());
                break;
            case "POST":
                response = requestSpecification
                        .post(Config.getInstance().getRelURL());
                break;
            case "PUT":
                response = requestSpecification
                        .put(Config.getInstance().getRelURL());
                break;
            case "PATCH":
                response = requestSpecification
                        .patch(Config.getInstance().getRelURL());
                break;
            case "DELETE":
                response = requestSpecification
                        .delete(Config.getInstance().getRelURL());
                break;
            default:
                System.out.println("Method is not Correct");
        }
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", "application/json");
        return headers;
    }

    private static void printRequestLogInReport(RequestSpecification requestSpecification) {
        QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);
        ExtentReportManager.logInfoDetails("Base URI: " + query.getBaseUri());
        ExtentReportManager.logInfoDetails("Request Method: " + query.getMethod());
        ExtentReportManager.logInfoDetails("Request Body is: ");
        ExtentReportManager.logJson(query.getBody());
    }

    private static void printResponseLogInReport(Response response) {
        ExtentReportManager.logInfoDetails("Response Status Code: " + response.getStatusCode());
        ExtentReportManager.logInfoDetails("Response Content Type: " + response.getContentType());
        ExtentReportManager.logInfoDetails("Response Headers: " + response.getHeaders().asList().toString());
        ExtentReportManager.logInfoDetails("Response Body is: ");
        ExtentReportManager.logJson(response.getBody().prettyPrint());
    }
}
