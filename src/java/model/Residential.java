package model;

import java.text.NumberFormat;
import java.util.LinkedList;

public class Residential {

    // ref id
    private int id;
    private int emp_num;
    private int owner_id;
    private int loc_id;
    private int details_id;

    // status
    private boolean edit;

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
    private int province;
    private int amphur;
    private int district;

    private String provinceName;
    private String amphurName;
    private String districtName;

    private String name;
    private String address;

    // price
    private int price;
    private String priceFormat;

    // ====== Detail Value =======
    // more detail
    private String floor;
    private String electricity;
    private String water;
    private String facilities;

    // ====== Media Value ======
    private LinkedList<ImageMeta> files;

    private String dt_time;

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
    public int getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(int province) {
        this.province = province;
    }

    /**
     * @return the amphur
     */
    public int getAmphur() {
        return amphur;
    }

    /**
     * @param amphur the amphur to set
     */
    public void setAmphur(int amphur) {
        this.amphur = amphur;
    }

    /**
     * @return the district
     */
    public int getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(Short district) {
        this.setDistrict((int) district);
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
    public String getFloor() {
        return floor;
    }

    /**
     * @param floor the floor to set
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * @return the electricity
     */
    public String getElectricity() {
        return electricity;
    }

    /**
     * @param electricity the electricity to set
     */
    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    /**
     * @return the water
     */
    public String getWater() {
        return water;
    }

    /**
     * @param water the water to set
     */
    public void setWater(String water) {
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

    /**
     * @param district the district to set
     */
    public void setDistrict(int district) {
        this.district = district;
    }

    /**
     * @return the dt_time
     */
    public String getDt_time() {
        return dt_time;
    }

    /**
     * @param dt_time the dt_time to set
     */
    public void setDt_time(String dt_time) {
        this.dt_time = dt_time;
    }

    /**
     * @return the provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * @param provinceName the provinceName to set
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * @return the amphurName
     */
    public String getAmphurName() {
        return amphurName;
    }

    /**
     * @param amphurName the amphurName to set
     */
    public void setAmphurName(String amphurName) {
        this.amphurName = amphurName;
    }

    /**
     * @return the districtName
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * @param districtName the districtName to set
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * @return the edit
     */
    public boolean isEdit() {
        return edit;
    }

    /**
     * @param edit the edit to set
     */
    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    /**
     * @return the loc_id
     */
    public int getLoc_id() {
        return loc_id;
    }

    /**
     * @param loc_id the loc_id to set
     */
    public void setLoc_id(int loc_id) {
        this.loc_id = loc_id;
    }

    /**
     * @return the details_id
     */
    public int getDetails_id() {
        return details_id;
    }

    /**
     * @param details_id the details_id to set
     */
    public void setDetails_id(int details_id) {
        this.details_id = details_id;
    }

}
