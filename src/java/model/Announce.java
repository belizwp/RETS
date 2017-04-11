package model;

import javax.servlet.http.HttpServletRequest;

public class Announce {

    // ====== Basic Value =======
    // general
    private Short type;
    private Short propType;
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
    // private Image map;

    // price
    private Long price;

    // area
    private Integer area;
    private Integer width;
    private Integer height;

    // ====== Detail Value =======
    // more detail
    private Short floor;
    private Integer electricity;
    private Integer water;

    // facilities
    private String internet;
    private String security;
    private String swimPool;
    private String laundry;
    private String cam;
    private String parklot;

    // ====== Media Value ======
    // private Image[] image; 
    public void saveBasicVal(HttpServletRequest request) {
        type = request.getParameter("type") != null ? Short.parseShort(request.getParameter("type")) : null;
        propType = request.getParameter("propType") != null ? Short.parseShort(request.getParameter("propType")) : null;
        province = request.getParameter("province") != null ? Short.parseShort(request.getParameter("province")) : null;
        amphur = request.getParameter("amphur") != null ? Short.parseShort(request.getParameter("amphur")) : null;
        district = request.getParameter("district") != null ? Short.parseShort(request.getParameter("district")) : null;

        title = request.getParameter("title") != null ? request.getParameter("title") : null;
        detail = request.getParameter("detail") != null ? request.getParameter("detail") : null;
        name = request.getParameter("name") != null ? request.getParameter("name") : null;
        number = request.getParameter("number") != null ? request.getParameter("number") : null;
        road = request.getParameter("road") != null ? request.getParameter("road") : null;
        postcode = request.getParameter("postcode") != null ? request.getParameter("postcode") : null;

        price = !request.getParameter("price").equals("") ? Long.parseLong(request.getParameter("price")) : null;

        area = !request.getParameter("area").equals("") ? Integer.parseInt(request.getParameter("area")) : null;
        width = !request.getParameter("width").equals("") ? Integer.parseInt(request.getParameter("width")) : null;
        height = !request.getParameter("height").equals("") ? Integer.parseInt(request.getParameter("height")) : null;
    }

    public void saveDetailVal(HttpServletRequest request) {
        floor = !request.getParameter("floor").equals("") ? Short.parseShort(request.getParameter("floor")) : null;
        electricity = !request.getParameter("electricity").equals("") ? Integer.parseInt(request.getParameter("electricity")) : null;
        water = !request.getParameter("water").equals("") ? Integer.parseInt(request.getParameter("water")) : null;

        internet = request.getParameter("internet") != null ? request.getParameter("internet") : null;
        security = request.getParameter("security") != null ? request.getParameter("security") : null;
        swimPool = request.getParameter("swimPool") != null ? request.getParameter("swimPool") : null;
        laundry = request.getParameter("laundry") != null ? request.getParameter("laundry") : null;
        cam = request.getParameter("cam") != null ? request.getParameter("cam") : null;
        parklot = request.getParameter("parklot") != null ? request.getParameter("parklot") : null;

    }

    public void saveMediaVal(HttpServletRequest request) {

    }

    /**
     * @return the type
     */
    public Short getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * @return the propType
     */
    public Short getPropType() {
        return propType;
    }

    /**
     * @param propType the propType to set
     */
    public void setPropType(Short propType) {
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
     * @return the area
     */
    public Integer getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(Integer area) {
        this.area = area;
    }

    /**
     * @return the width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Integer height) {
        this.height = height;
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
     * @return the internet
     */
    public String getInternet() {
        return internet;
    }

    /**
     * @param internet the internet to set
     */
    public void setInternet(String internet) {
        this.internet = internet;
    }

    /**
     * @return the security
     */
    public String getSecurity() {
        return security;
    }

    /**
     * @param security the security to set
     */
    public void setSecurity(String security) {
        this.security = security;
    }

    /**
     * @return the swimPool
     */
    public String getSwimPool() {
        return swimPool;
    }

    /**
     * @param swimPool the swimPool to set
     */
    public void setSwimPool(String swimPool) {
        this.swimPool = swimPool;
    }

    /**
     * @return the laundry
     */
    public String getLaundry() {
        return laundry;
    }

    /**
     * @param laundry the laundry to set
     */
    public void setLaundry(String laundry) {
        this.laundry = laundry;
    }

    /**
     * @return the cam
     */
    public String getCam() {
        return cam;
    }

    /**
     * @param cam the cam to set
     */
    public void setCam(String cam) {
        this.cam = cam;
    }

    /**
     * @return the parklot
     */
    public String getParklot() {
        return parklot;
    }

    /**
     * @param parklot the parklot to set
     */
    public void setParklot(String parklot) {
        this.parklot = parklot;
    }

}
