package com.example.nitro.tasteme.data.parse.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Recipe")
public class Recipe extends ParseObject{
    public void Recipe() {
    }

    public String getImage() {
        return getString("image");
    }

    public void setImage(String value) {
        put("image", value);
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String value) {
        put("name", value);
    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String value) {
        put("description", value);
    }

    public Boolean getIsVegetarian() {
        return getBoolean("isVegetarian");
    }

    public void setIsVegetarian(Boolean value) {
        put("isVegetarian", value);
    }

    public Boolean getIsCold() {
        return getBoolean("isCold");
    }

    public void setIsCold(Boolean value) {
        put("isCold", value);
    }

    public Boolean getIsHot() {
        return getBoolean("isHot");
    }

    public void setIsHot(Boolean value) {
        put("isHot", value);
    }

    public Boolean getIsSweet() {
        return getBoolean("isSweet");
    }

    public void setIsSweet(Boolean value) {
        put("isSweet", value);
    }

    public Double getLongitude() {
        return getDouble("longitude");
    }

    public void setLongitude(Double value) {
        put("longitude", value);
    }

    public Double getLatitude() {
        return getDouble("latitude");
    }

    public void setLatitude(Double value) {
        put("latitude", value);
    }

    public Integer getLikes() {
        return getInt("likes");
    }

    public void setLikes(Integer value) {
        put("likes", value);
    }
}
