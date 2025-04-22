package org.cca.stepDefinitions;

import com.sun.deploy.cache.CacheEntry;
import com.sun.deploy.config.Config;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import java.net.URLEncoder;

public class AddressManagementSteps {
    private Response response;
    private RequestSpecification request;

    @Given("I have access to the Address Management API")
    public void setupApiAccess() {
        request = RestAssured.given()
                    .baseUri(GlobalConfig.get("base.url"))
                    .header("Authorization", GlobalConfig.get("auth.token"))
                    .timeout(Integer.parseInt(GlobalConfig.get("api.timeout")));
        }
    @When("I create a {string} request with uri params {string} query params {string} headers {string} with payload {string}")
    public void createRequest(String method, String uriParams, String queryParams, String headers, String payload)
            throws Exception {
        String[] params = queryParams.split(",");
        String jsonParam = params[0].trim();
        String intParam = params[1].trim();
        request.header("Authorization", headers)
                .queryParam("data", URLEncoder.encode(jsonParam, "UTF-8"))
                .queryParam("startpoint", intParam);

        response = request.get();
    }

    @Then("I receive a response with HTTP status code {string} with headers {string} with payload {string}")
    public void verifyResponse(String statusCode, String headers, String expectedPayload) {
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
        if (!"none".equals(expectedPayload)) {
            Assert.assertTrue(response.getBody().asString().contains(expectedPayload));
        }
    }

    @Then("the response should match the schema {string}")
    public void validateSchema(String schemaFile) {
        if (!schemaFile.isEmpty()) {
            response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/" + schemaFile));
        }
    }


    @When("I create a {string} request with uri params {string} query params {string} headers {string} with payload {string}")
    public void iCreateARequestWithUriParamsQueryParamsHeadersWithPayload(String arg0, String arg1, String arg2, String arg3, String arg4) {
    }
}
