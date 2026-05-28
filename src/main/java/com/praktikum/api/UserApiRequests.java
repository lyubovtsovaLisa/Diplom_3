package com.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiRequests {
    @Step("Создание пользователя")
    public Response createUser(UserInfo userInfo){
        return given()
                .header("Content-type", "application/json")
                .body(userInfo)
                .post(CREATE_USER_ENDPOINT);
    }
    @Step("Логин и получение accessToken пользователя через API")
    public String getAccessToken(UserLoginData loginUserData) {
        Response response = given()
                .header("Content-type", "application/json")
                .body(loginUserData)
                .post(LOGIN_USER_ENDPOINT);
        if (response.getStatusCode() != 200) {
            return null;
        }
        return response.path("accessToken");
    }

    @Step("Удалить пользователя")
    public void deleteUser(String accessToken){
            given().header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .delete(DELETE_USER_ENDPOINT);
    }
}
