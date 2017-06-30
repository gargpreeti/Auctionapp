package com.zoptal.eauction.model;

import java.io.Serializable;

/**
 * Created by preeti on 6/14/17.
 */
public class Model_MyAuctions implements Serializable {

    private String id;
    private String userid;
    private String catid;
    private String levelid;
    private String leveltype;
    private String productname;
    private String productcondition;
    private String openprice;
    private  String createdtime;
    private String expiredtime;
    private String catname;
    private  String remainingtime;
    private String images;
    private String plusimg;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getLevelid() {
        return levelid;
    }

    public void setLevelid(String levelid) {
        this.levelid = levelid;
    }

    public String getProductcondition() {
        return productcondition;
    }

    public void setProductcondition(String productcondition) {
        this.productcondition = productcondition;
    }

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getRemainingtime() {
        return remainingtime;
    }

    public void setRemainingtime(String remainingtime) {
        this.remainingtime = remainingtime;
    }

    public String getExpiredtime() {
        return expiredtime;
    }

    public void setExpiredtime(String expiredtime) {
        this.expiredtime = expiredtime;
    }

    public String getOpenprice() {
        return openprice;
    }

    public void setOpenprice(String openprice) {
        this.openprice = openprice;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getLeveltype() {
        return leveltype;
    }

    public void setLeveltype(String leveltype) {
        this.leveltype = leveltype;
    }

    public String getPlusimg() {
        return plusimg;
    }

    public void setPlusimg(String plusimg) {
        this.plusimg = plusimg;
    }
}

