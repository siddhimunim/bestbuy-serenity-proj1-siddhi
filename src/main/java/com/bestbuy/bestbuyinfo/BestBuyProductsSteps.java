package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.BestBuyProductsPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class BestBuyProductsSteps {
    @Step("Create product with name {0},  type {1}, price {2}, upc {3}, shipping {4}, description {5},manufacturer {6}, " +
            "model {7}, url {8}, image {9}, ")
    public ValidatableResponse createProductLists(String name,String type,int price,int shipping,String upc,
                                                  String description,String manufacturer,String model,String url,
                                                  String image){

        BestBuyProductsPojo bestBuyProductsPojo = new BestBuyProductsPojo();
        bestBuyProductsPojo.setName(name);
        bestBuyProductsPojo.setType(type);
        bestBuyProductsPojo.setPrice(price);
        bestBuyProductsPojo.setShipping(shipping);
        bestBuyProductsPojo.setUpc(upc);
        bestBuyProductsPojo.setDescription(description);
        bestBuyProductsPojo.setManufacturer(manufacturer);
        bestBuyProductsPojo.setModel(model);
        bestBuyProductsPojo.setUrl(url);
        bestBuyProductsPojo.setImage(image);
        return SerenityRest. given().log().all()
                .header("Connection","keep-alive")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(bestBuyProductsPojo)
                .when()
                .post(EndPoints.CREATE_PRODUCTS)
                .then().log().all();

    }
    @Step("Get product data with id")
    public ValidatableResponse getProductById(int id){
        return SerenityRest.given()
                .pathParam("id",id)
                .when()
                .header("Connection","keep-alive")
                .header("Accept", "application/json")
                .get(EndPoints.GET_PRODUCTS_BY_ID)
                .then();

    }
    @Step("Update product data  with name {0},  type {1}, price {2}, upc {3}, shipping {4}, description {5},manufacturer {6},model {7}, " +
            "url {8}, image {9}, ")
    public ValidatableResponse updateProductById (int id,String name,String type,int price,int shipping,String upc,
                                                  String description,String manufacturer,String model,String url,
                                                  String image){
        BestBuyProductsPojo bestBuyProductsPojo = new BestBuyProductsPojo();
        bestBuyProductsPojo.setName(name);
        bestBuyProductsPojo.setType(type);
        bestBuyProductsPojo.setPrice(price);
        bestBuyProductsPojo.setShipping(shipping);
        bestBuyProductsPojo.setUpc(upc);
        bestBuyProductsPojo.setDescription(description);
        bestBuyProductsPojo.setManufacturer(manufacturer);
        bestBuyProductsPojo.setModel(model);
        bestBuyProductsPojo.setUrl(url);
        bestBuyProductsPojo.setImage(image);
        return SerenityRest. given().log().all()
                .pathParam("id",id)
                .header("Connection","keep-alive")
                .header("Accept", "application/json")
                .body(bestBuyProductsPojo)

                .when()
                .patch(EndPoints.UPDATE_PRODUCTS_BY_ID)
                .then().log().all();



    }
    @Step("Delete product with id :{0}")
    public ValidatableResponse deleteProductById(int id) {
        return SerenityRest.given().log().all()
                .pathParam("id", id)
                .header("Connection", "keep-alive")
                .header("Accept", "application/json")
                .when()
                .delete(EndPoints.DELETE_PRODUCTS_BY_ID)
                .then().log().all();


    }
}
