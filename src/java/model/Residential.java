package model;

import java.text.NumberFormat;
import java.util.LinkedList;

public class Residential {

    // ref id
    private String id;
    private String owner_id;

    // ====== Basic Value =======
    // general
    private String type;
    private String propType;
    private String title;
    private String detail;

    // place
    private Short province;
    private Short amphur;
    private Short district;
    private String name;
    private String number;
    private String road;
    private String postcode;

    // price
    private Long price;
    private String priceFormat;

    // ====== Detail Value =======
    // more detail
    private Short floor;
    private Integer electricity;
    private Integer water;
    private String facilities;

    // ====== Media Value ======
    private LinkedList<ImageMeta> files;

    public Residential() {
        files = new LinkedList<>();
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the propType
     */
    public String getPropType() {
        return propType;
    }

    /**
     * @param propType the propType to set
     */
    public void setPropType(String propType) {
        this.propType = propType;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return the province
     */
    public Short getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(Short province) {
        this.province = province;
    }

    /**
     * @return the amphur
     */
    public Short getAmphur() {
        return amphur;
    }

    /**
     * @param amphur the amphur to set
     */
    public void setAmphur(Short amphur) {
        this.amphur = amphur;
    }

    /**
     * @return the district
     */
    public Short getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(Short district) {
        this.district = district;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the road
     */
    public String getRoad() {
        return road;
    }

    /**
     * @param road the road to set
     */
    public void setRoad(String road) {
        this.road = road;
    }

    /**
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode the postcode to set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * @return the price
     */
    public Long getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * @return the floor
     */
    public Short getFloor() {
        return floor;
    }

    /**
     * @param floor the floor to set
     */
    public void setFloor(Short floor) {
        this.floor = floor;
    }

    /**
     * @return the electricity
     */
    public Integer getElectricity() {
        return electricity;
    }

    /**
     * @param electricity the electricity to set
     */
    public void setElectricity(Integer electricity) {
        this.electricity = electricity;
    }

    /**
     * @return the water
     */
    public Integer getWater() {
        return water;
    }

    /**
     * @param water the water to set
     */
    public void setWater(Integer water) {
        this.water = water;
    }

    /**
     * @return the images
     */
    public LinkedList<ImageMeta> getFiles() {
        return files;
    }

    /**
     * @param files the images to set
     */
    public void setFiles(LinkedList<ImageMeta> files) {
        this.files = files;
    }

    /**
     * @return the priceFormat
     */
    public String getPriceFormat() {
        try {
            return NumberFormat.getIntegerInstance().format(this.getPrice());
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the owner_id
     */
    public String getOwner_id() {
        return owner_id;
    }

    /**
     * @param owner_id the owner_id to set
     */
    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    /**
     * @param priceFormat the priceFormat to set
     */
    public void setPriceFormat(String priceFormat) {
        this.priceFormat = priceFormat;
    }

    /**
     * @return the facilities
     */
    public String getFacilities() {
        return facilities;
    }

    /**
     * @param facilities the facilities to set
     */
    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

}
