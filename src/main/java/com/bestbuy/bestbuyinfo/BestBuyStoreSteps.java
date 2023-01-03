package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.BestBuyStorePojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;

public class BestBuyStoreSteps {
    @Step("Creating store data with name :{0},type :{1}address:{2},address2 :{3},city :{4},state :{5},zip :{6},lat :{7},lng :{8},hours :{9}")
    public ValidatableResponse createStoreData(String name , String type , String address , String address2 , String city , String state ,
                                               String zip , int lat ,
                                               int lng , String hours,BestBuyStorePojo.Services services ){
        BestBuyStorePojo bestBuyStorePojo = new BestBuyStorePojo();
        bestBuyStorePojo.setName(name);
        bestBuyStorePojo.setType(type);
        bestBuyStorePojo.setAddress(address);
        bestBuyStorePojo.setAddress2(address2);
        bestBuyStorePojo.setCity(city);
        bestBuyStorePojo.setState(state);
        bestBuyStorePojo.setZip(zip);
        bestBuyStorePojo.setLat(lat);
        bestBuyStorePojo.setLng(lng);
        bestBuyStorePojo.setHours(hours);
        BestBuyStorePojo.Services service = new BestBuyStorePojo.Services();
      service.setServices(services);
        return SerenityRest.given().log().all()
                  .header("Connection","keep-alive")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bestBuyStorePojo)
                .when()
                .post(EndPoints.CREATE_STORES)
                .then().log().all();


    }
    @Step("get storedetail by id :{0}")
    public ValidatableResponse getstoreDetailById(int id) {
        return SerenityRest.given().log().all()
                .pathParam("id", id)
                .when()
                .header("Connection", "keep-alive")
                .get(EndPoints.GET_STORES_BY_ID)
                .then();

    }
    @Step("Creating store data with name :{0},type :{1}address:{2},address2 :{3},city :{4},state :{5},zip :{6},lat :{7},lng :{8},hours :{9}")
    public ValidatableResponse UpdateStoreData(int id,String name , String type , String address , String address2 , String city , String state ,
                                               String zip , int lat ,
                                               int lng , String hours,BestBuyStorePojo.Services services ) {
        BestBuyStorePojo bestBuyStorePojo = new BestBuyStorePojo();
        bestBuyStorePojo.setName(name);
        bestBuyStorePojo.setType(type);
        bestBuyStorePojo.setAddress(address);
        bestBuyStorePojo.setAddress2(address2);
        bestBuyStorePojo.setCity(city);
        bestBuyStorePojo.setState(state);
        bestBuyStorePojo.setZip(zip);
        bestBuyStorePojo.setLat(lat);
        bestBuyStorePojo.setLng(lng);
        bestBuyStorePojo.setHours(hours);
        BestBuyStorePojo.Services service = new BestBuyStorePojo.Services();
        service.setServices(services);
        return SerenityRest.given().log().all()
                .pathParam("id",id)
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bestBuyStorePojo)
                .when()
                .put(EndPoints.UPDATE_STORES_BY_ID)
                .then().log().all();
    }
    @Step("Delete store by id : {0}")
    public ValidatableResponse deleteStoreById(int id) {
        return SerenityRest.given().log().all()
                .header("Connection", "keep-alive")
                .pathParam("id", id)
                .when()

                .delete(EndPoints.DELETE_STORES_BY_ID)
                .then().log().all();

    }



}
