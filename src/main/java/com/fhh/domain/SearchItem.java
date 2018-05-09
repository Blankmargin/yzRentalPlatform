package com.fhh.domain;

import java.io.Serializable;

//Solr索引库对应的Item结构
public class SearchItem implements Serializable {
    private String id;
    private String title;
    private String sellPoint;
    private float price;
    private String image;
    private String categoryName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String[] getImages(){
        if(image!=null&&"".equals(image)){
            String[] strings=image.split(",");
            return strings;
        }
        return null;
    }
}
