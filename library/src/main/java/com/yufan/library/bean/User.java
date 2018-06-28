package com.yufan.library.bean;

/**
 * Created by wangyu on 2018/4/3.
 */

public class User {
    private String id;
    private String nickName;
    private String headImage;
    private String age;
    private String city;
    private String gender;
    private String dateRange;
    private int imageCount;
    private String privacyState;
    private String mobile;
    private String applyState;
    private String job;
    private String loginLabel;
    private boolean isCertification;
    private boolean isVip;
    private boolean collected;
    private String latitude;
    private String longitude;
    private String dressStyle;
    private int clickNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImage() {
        if (headImage.contains("http")) {
            return headImage;
        }
        return "http://p744j002d.bkt.clouddn.com/" + headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public String getPrivacyState() {
        return privacyState;
    }

    public void setPrivacyState(String privacyState) {
        this.privacyState = privacyState;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getApplyState() {
        return applyState;
    }

    public void setApplyState(String applyState) {
        this.applyState = applyState;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLoginLabel() {
        return loginLabel;
    }

    public void setLoginLabel(String loginLabel) {
        this.loginLabel = loginLabel;
    }

    public boolean isCertification() {
        return isCertification;
    }

    public void setCertification(boolean certification) {
        isCertification = certification;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public int getClickNum() {
        return clickNum;
    }

    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDressStyle() {
        return dressStyle;
    }

    public void setDressStyle(String dressStyle) {
        this.dressStyle = dressStyle;
    }
}
