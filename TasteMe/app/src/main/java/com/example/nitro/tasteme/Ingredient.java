package com.example.nitro.tasteme;

/**
 * Created by Nitro on 2016-01-17.
 */
public class Ingredient {

    private String product;
    public Integer quantity;
    public String measurement;

    public Ingredient(){

    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String pr) {
        product = pr;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer q) {
        quantity = q;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String m) {
        measurement = m;
    }
}