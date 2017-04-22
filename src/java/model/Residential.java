package model;

import java.text.NumberFormat;
import java.util.LinkedList;

public class Residential {

    // ref id
    private int id;
    private int emp_num;
    private int owner_id;

    // owner info
    private String fname;
    private String lname;
    private String phone;
    private String email;

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
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the emp_num
     */
    public int getEmp_num() {
        return emp_num;
    }

    /**
     * @param emp_num the emp_num to set
     */
    public void setEmp_num(int emp_num) {
        this.emp_num = emp_num;
    }

    /**
     * @return the owner_id
     */
    public int getOwner_id() {
        return owner_id;
    }

    /**
     * @param owner_id the owner_id to set
     */
    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
