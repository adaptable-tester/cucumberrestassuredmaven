package libManagementApi;

import com.adaptabletester.app.Utils.PropertyUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class libraryMgmtResponseApiService {

    RequestSpecification httpRequest;

    public libraryMgmtResponseApiService() {
        RestAssured.baseURI = "https://librarymanagementapisystem.onrender.com";
        httpRequest = given();
    }


    public String getAuthToken(String username, String password) {
        PropertyUtils props = new PropertyUtils("auth.properties");
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", username);
        requestParams.put("password", password);
        Response response = given()
                                  .contentType("application/json")
                                  .body(requestParams.toString())
                            .when()
                            .post("/member/login/")
                            .then()
                            .extract().response();

        JsonPath authResponseJsonObj = response.jsonPath();
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
        String dts = dt.format(new Date());
        props.setProperties("tokenCreatedDateTime", dts);
        return authResponseJsonObj.getString("token");
    }

    public Response getAuthResponse(String resource, String username, String password ){
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", username);
        requestParams.put("password", password);
        return given()
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post(resource)
                .then()
                .extract().response();
    }

    public Response getApiResponse(String resource) {
        return given()
                .when()
                .get(resource)
                .then()
                .extract().response();
    }

    public Response getApiResponseWithPathParams(String resource, String pathVariable, String pathParams) {
         return given()
                 .contentType("application/json").pathParam(pathVariable, pathParams)
                 .when()
                .get(resource)
                .then()
                .extract().response();
    }

    public Response getApiResponseWithQueryParams(String resource, String queryVariable, String queryParam) {
        return given().queryParams(queryVariable, queryParam)
                .when()
                .get(resource)
                .then()
                .extract().response();
    }

    public String getValueFromResponse(Response res, String field) {
        JsonPath getFieldValue = res.jsonPath();
        return getFieldValue.getString(field);
    }

    public int getBookAvailableCount(Response res) {
        int availableCount;
        String getLatestAvailCount = getValueFromResponse(res, "availableCopies");

        if (getLatestAvailCount.equals("Not available") || getLatestAvailCount.equals("0")) {
            return availableCount = 0;
        } else {
            availableCount = Integer.parseInt(getValueFromResponse(res, "availableCopies"));
            return availableCount;
        }
    }

    public Response getTransactionResponse(String resource, String token, String username, String title) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", username);
        requestParams.put("title", title);
        return given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post(resource)
                .then()
                .extract().response();
    }
    public Response getTransactionHistoryResponse(String resource, String token, String username) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", username);
        return given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(requestParams.toString())
                .when()
                .post(resource)
                .then()
                .extract().response();
    }


}

