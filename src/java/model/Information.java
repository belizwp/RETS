/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;

/**
 *
 * @author dell
 */
public class Information {

    // ====== Basic Value =======
    // general
    private String topic;
    private String present_date;
    
    //resident
    private String name;
    private String announce_for;
    private String building_name;
    private String type;

    // place
    private Short province;
    private Short amphur;
    private Short district;
    private String address;

    // price
    private int price;
    private String priceFormat;

    // ====== Detail Value =======
    // more detail
    private Short floor;
    private Integer electricity;
    private Integer water;
    private String facilities;
    private String remark;

    // ====== Media Value ======
    private LinkedList<ImageMeta> files;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPresent_date() {
        return present_date;
    }

    public void setPresent_date(String present_date) {
        this.present_date = present_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnounce_for() {
        return announce_for;
    }

    public void setAnnounce_for(String announce_for) {
        this.announce_for = announce_for;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Short getProvince() {
        return province;
    }

    public void setProvince(Short province) {
        this.province = province;
    }

    public Short getAmphur() {
        return amphur;
    }

    public void setAmphur(Short amphur) {
        this.amphur = amphur;
    }

    public Short getDistrict() {
        return district;
    }

    public void setDistrict(Short district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPriceFormat() {
        return priceFormat;
    }

    public void setPriceFormat(String priceFormat) {
        this.priceFormat = priceFormat;
    }

    public Short getFloor() {
        return floor;
    }

    public void setFloor(Short floor) {
        this.floor = floor;
    }

    public Integer getElectricity() {
        return electricity;
    }

    public void setElectricity(Integer electricity) {
        this.electricity = electricity;
    }

    public Integer getWater() {
        return water;
    }

    public void setWater(Integer water) {
        this.water = water;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LinkedList<ImageMeta> getFiles() {
        return files;
    }

    public void setFiles(LinkedList<ImageMeta> files) {
        this.files = files;
    }
    
    
}
