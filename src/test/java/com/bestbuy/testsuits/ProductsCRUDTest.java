package com.bestbuy.testsuits;

import com.bestbuy.bestbuyinfo.BestBuyProductsSteps;
import com.bestbuy.model.BestBuyProductsPojo;
import com.bestbuy.testbase.TerstBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductsCRUDTest extends TerstBase {
    static int id;
    static String name = "Gucci";
    static String type = "femaleProduct";
    static int price = 120;
    static int shipping = 20;
    static String upc = "dft34";
    static String description = "Perfume";
    static String manufacturer = "GucciP";
    static String model = "0258";
    static String url = "Gucci@co.uk";
    static String image = "Gucci3456";
    @Steps
    BestBuyProductsSteps productsSteps;
    @Title("Creating new Product data")

    @Test
    public void test001(){
        ValidatableResponse response = productsSteps.createProductLists(name,type,price,shipping,upc,
                description,manufacturer,model,url,image);
        response.log().all().statusCode(201);
        id = response.extract().path("id");
    }

    @Title("Creating new Product data")

    @Test
    public void test002(){
        ValidatableResponse response= productsSteps.getProductById(id);
        response.log().all().statusCode(200);


    }
    @Title("Update product by Id ")
            @Test
            public void test003() {
        type = "maleProduct";
        ValidatableResponse response = productsSteps.updateProductById(id, name, type, price, shipping, upc,
                description, manufacturer, model, url, image);
        response.log().all().statusCode(200);
      HashMap<String,Object> productUpdate = response.extract().path("");
     Assert.assertThat(productUpdate,hasValue(name));

    }
    @Title("Delete Product by id")
    @Test
    public void test004() {
        productsSteps.deleteProductById(id).statusCode(200);
    productsSteps.getProductById(id).statusCode(404);
    }



}
