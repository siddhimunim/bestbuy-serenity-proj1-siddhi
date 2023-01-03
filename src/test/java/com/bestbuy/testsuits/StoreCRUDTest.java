package com.bestbuy.testsuits;

import com.bestbuy.bestbuyinfo.BestBuyProductsSteps;
import com.bestbuy.bestbuyinfo.BestBuyStoreSteps;
import com.bestbuy.model.BestBuyStorePojo;
import com.bestbuy.testbase.TerstBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoreCRUDTest extends TerstBase {
    static int id;
    static String name = "WestSide";
    static String type = "Fashion";
    static String address = "20 fashion street";
    static String address2 = "west lane";
    static String city = "coventry";
    static String state = "West midland";
    static String zip = "01528/8";
    static int lat = 5;
    static int lng = 8;
    static String hours = "8AM to 8PM";
    BestBuyStorePojo.Services services;

    @Steps
    BestBuyStoreSteps storeSteps;
    @Title("Create new store data")

    @Test
    public void test001(){

        ValidatableResponse response = storeSteps.createStoreData(name,type,address,address2,city,state,zip,lat,lng,hours,services);
        response.log().all().statusCode(201);
        id = response.extract().path("id");

    }
    @Title("Get store Detail")
    @Test
    public  void test002(){
        ValidatableResponse response= storeSteps.getstoreDetailById(id);
        response.log().all().statusCode(200);
    }
    @Title("Update store by Id ")
    @Test
    public void test003() {
        address = "24 Broad street";
        ValidatableResponse response = storeSteps.UpdateStoreData(id, name, type, address, address2,
                city, state, zip, lat, lng, hours, services);
        response.log().all().statusCode(200);
        HashMap<String,Object>store = response.extract().path("");
        Assert.assertThat(store,hasValue(address));

    }
    @Title("Delete store by id")
    @Test
    public void test004() {
       storeSteps.deleteStoreById(id).statusCode(200);
        storeSteps.getstoreDetailById(id).statusCode(404);
    }

}
