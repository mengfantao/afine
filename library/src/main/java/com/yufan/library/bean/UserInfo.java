package com.yufan.library.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyu on 2018/4/3.
 */

public class UserInfo {

    private boolean certification;
    private String height;
    private String weight;
    private String bustInfo;
    private String satisfiedBodyPart;
    private String languages;
    private String emotionState;
    private String qq;
    private String weixin;
    private int noVipfreeviewFemaleTimes;
    private String programs;
    private int neimStatus;
    private String dateConditions;
    private int reportedCount;
    private int feeViewed;
    private String information;
    private String introduction;

    private User user=new User();

    private List<Pics> pics = new ArrayList<>();

    public boolean isCertification() {
        return certification;
    }

    public void setCertification(boolean certification) {
        this.certification = certification;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBustInfo() {
        return bustInfo;
    }

    public void setBustInfo(String bustInfo) {
        this.bustInfo = bustInfo;
    }

    public String getSatisfiedBodyPart() {
        return satisfiedBodyPart;
    }

    public void setSatisfiedBodyPart(String satisfiedBodyPart) {
        this.satisfiedBodyPart = satisfiedBodyPart;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getEmotionState() {
        return emotionState;
    }

    public void setEmotionState(String emotionState) {
        this.emotionState = emotionState;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public int getNoVipfreeviewFemaleTimes() {
        return noVipfreeviewFemaleTimes;
    }

    public void setNoVipfreeviewFemaleTimes(int noVipfreeviewFemaleTimes) {
        this.noVipfreeviewFemaleTimes = noVipfreeviewFemaleTimes;
    }

    public String getPrograms() {
        return programs;
    }

    public void setPrograms(String programs) {
        this.programs = programs;
    }

    public int getNeimStatus() {
        return neimStatus;
    }

    public void setNeimStatus(int neimStatus) {
        this.neimStatus = neimStatus;
    }

    public String getDateConditions() {
        return dateConditions;
    }

    public void setDateConditions(String dateConditions) {
        this.dateConditions = dateConditions;
    }

    public int getReportedCount() {
        return reportedCount;
    }

    public void setReportedCount(int reportedCount) {
        this.reportedCount = reportedCount;
    }

    public int getFeeViewed() {
        return feeViewed;
    }

    public List<Pics> getPics() {
        return pics;
    }

    public void setPics(List<Pics> pics) {
        this.pics = pics;
    }

    public void setFeeViewed(int feeViewed) {
        this.feeViewed = feeViewed;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
