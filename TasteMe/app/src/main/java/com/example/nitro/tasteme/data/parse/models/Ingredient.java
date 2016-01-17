package com.example.nitro.tasteme.data.parse.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Ingredient")
public class Ingredient extends ParseObject{
    public void Ingredient() {

    }

    public void Ingredient(String product, Integer quantity, String measurement) {
        this.setProduct(product);
        this.setQuantity(quantity);
        this.setMeasurement(measurement);
    }

    public String getProduct() {
        return getString("product");
    }

    public void setProduct(String value) {
        put("product", value);
    }

    public Integer getQuantity() {
        return getInt("quantity");
    }

    public void setQuantity(Integer value) {
        put("quantity", value);
    }

    public String getMeasurement() {
        return getString("measurement");
    }

    public void setMeasurement(String value) {
        put("measurement", value);
    }
}
