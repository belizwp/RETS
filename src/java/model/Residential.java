package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.LinkedList;
import javax.imageio.ImageIO;

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
    
    public Residential(Connection conn, int id) throws SQLException, IOException {
        // residetial table
        String sql = "SELECT *\n"
                + "FROM residential\n"
                + "JOIN owner ON (residential.Owner_Own_id = owner.Own_id)\n"
                + "NATURAL JOIN details\n"
                + "NATURAL JOIN location\n"
                + "NATURAL JOIN province\n"
                + "NATURAL JOIN amphur\n"
                + "NATURAL JOIN district\n"
                + "WHERE Res_id = ?;";
        
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        
        if (rs.next()) {
            
            this.setEdit(true);
            
            this.setId(id);
            this.setEmp_num(rs.getInt("Emp_num"));
            this.setOwner_id(rs.getInt("Owner_Own_id"));
            this.setLoc_id(rs.getInt("Loc_id"));
            this.setDetails_id(rs.getInt("details_id"));
            
            this.setType(rs.getString("announce_for"));
            this.setPropType(rs.getString("types"));
            this.setProvince(rs.getShort("province_id"));
            this.setAmphur(rs.getShort("amphur_id"));
            this.setDistrict(rs.getShort("district_id"));
            
            this.setProvinceName(rs.getString("province_name"));
            this.setAmphurName(rs.getString("amphur_name"));
            this.setDistrictName(rs.getString("district_name"));
            
            this.setTitle(rs.getString("Res_name"));
            this.setDetail(rs.getString("remark"));
            this.setName(rs.getString("buliding_name"));
            this.setAddress(rs.getString("address"));
            
            this.setPrice(rs.getInt("price"));
            
            this.setFname(rs.getString("Fname"));
            this.setLname(rs.getString("Lname"));
            this.setPhone(rs.getString("phone"));
            this.setEmail(rs.getString("email"));
            
            this.setFloor(rs.getString("floor"));
            this.setElectricity(rs.getString("electric_bill"));
            this.setWater(rs.getString("water_bill"));
            this.setFacilities(rs.getString("facilities"));
            
            this.setDt_time(rs.getString("dt_modified"));
            
            String imageSQL = "SELECT image_id, image from `image of detail` WHERE Res_id = ?;";
            PreparedStatement imgSTM = conn.prepareStatement(imageSQL);
            imgSTM.setInt(1, this.getId());
            ResultSet imgRS = imgSTM.executeQuery();
            
            files = new LinkedList<>();
            ImageMeta temp = null;
            while (imgRS.next()) {
                temp = new ImageMeta();
                temp.setId(imgRS.getInt("image_id"));
                temp.setImg(ImageIO.read(imgRS.getBinaryStream("image")));
                files.add(temp);
            }
        }
    }
    
    public int create(Connection connection, Employee emp) throws SQLException, IOException {
        
        String sql1 = "INSERT INTO owner(Fname, Lname, email, phone) VALUES(?, ?, ?, ?);";
        String sql2 = "SET @owner_id := LAST_INSERT_ID();";
        String sql3 = "INSERT INTO location(address, province_id, amphur_id, district_id) VALUES(?, ?, ?, ?);";
        String sql4 = "SET @loc_id := LAST_INSERT_ID();";
        String sql5 = "INSERT INTO residential(Res_name, announce_for, Emp_num, Owner_Own_id, Loc_id) VALUES(?, ?, ?, @owner_id, @loc_id);";
        String sql6 = "SET @res_id := LAST_INSERT_ID();";
        String sql7 = "INSERT INTO details(buliding_name, types, floor, price, water_bill, electric_bill, facilities, remark, Res_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?, @res_id);";
        String sql8 = "SET @detail_id := LAST_INSERT_ID();";
        String sql9 = "INSERT INTO `image of detail`(image, details_id, Res_id) VALUES (?, @detail_id, @res_id);";
        
        PreparedStatement stm1 = connection.prepareStatement(sql1);
        // owner info
        stm1.setString(1, this.getFname());
        stm1.setString(2, this.getLname());
        stm1.setString(3, this.getEmail());
        stm1.setString(4, this.getPhone());
        stm1.executeUpdate();

        // owner id
        PreparedStatement stm2 = connection.prepareStatement(sql2);
        stm2.executeQuery();
        
        PreparedStatement stm3 = connection.prepareStatement(sql3);
        // location info
        stm3.setString(1, this.getAddress());
        stm3.setInt(2, this.getProvince());
        stm3.setInt(3, this.getAmphur());
        stm3.setInt(4, this.getDistrict());
        stm3.executeUpdate();

        // loc id
        PreparedStatement stm4 = connection.prepareStatement(sql4);
        stm4.executeQuery();
        
        PreparedStatement stm5 = connection.prepareStatement(sql5);
        // resident info
        stm5.setString(1, this.getTitle());
        stm5.setString(2, this.getType());
        stm5.setInt(3, emp.getNumber());
        stm5.executeUpdate();

        // res id
        PreparedStatement stm6 = connection.prepareStatement(sql6);
        stm6.executeQuery();
        
        PreparedStatement stm7 = connection.prepareStatement(sql7);
        // detail info
        stm7.setString(1, this.getName());
        stm7.setString(2, this.getPropType());
        stm7.setString(3, this.getFloor() + "");
        stm7.setInt(4, this.getPrice());
        stm7.setString(5, this.getWater() + "");
        stm7.setString(6, this.getElectricity() + "");
        stm7.setString(7, this.getFacilities());
        stm7.setString(8, this.getDetail());
        int row = stm7.executeUpdate();

        // detail id
        PreparedStatement stm8 = connection.prepareStatement(sql8);
        stm8.executeQuery();

        // image file
        PreparedStatement stm9 = connection.prepareStatement(sql9);
        
        LinkedList<ImageMeta> files = this.getFiles();
        
        int i = 0;
        
        for (ImageMeta file : files) {
            stm9.setBlob(1, file.getInputStream());
            
            stm9.addBatch();
            i++;
            
            if (i % 100 == 0 || i == files.size()) {
                stm9.executeBatch(); // Execute every 100 items.
            }
        }
        
        return row;
    }

    // update record
    public void update(Connection connection) throws SQLException, IOException {
        
        String sql1 = "UPDATE residential SET Res_name = ?, announce_for = ? WHERE Res_id = ?";
        String sql2 = "UPDATE owner SET Fname = ?, Lname = ?, phone = ?, email = ? WHERE Own_id = ?";
        String sql3 = "UPDATE location SET address = ?, province_id = ?, amphur_id = ?, district_id = ? WHERE Loc_id = ?";
        String sql4 = "UPDATE details SET "
                + "buliding_name = ?, "
                + "types = ?, "
                + "floor = ?, "
                + "price = ?, "
                + "water_bill = ?, "
                + "electric_bill = ?, "
                + "facilities = ?, "
                + "remark = ? "
                + "WHERE details_id = ?";
        String sql5 = "DELETE FROM `image of detail` WHERE Res_id = ?";
        String sql6 = "INSERT INTO `image of detail`(image, details_id, Res_id) VALUES (?, ?, ?);";
        
        PreparedStatement stm1 = connection.prepareStatement(sql1);
        PreparedStatement stm2 = connection.prepareStatement(sql2);
        PreparedStatement stm3 = connection.prepareStatement(sql3);
        PreparedStatement stm4 = connection.prepareStatement(sql4);
        PreparedStatement stm5 = connection.prepareStatement(sql5);
        
        stm1.setString(1, this.getTitle());
        stm1.setString(2, this.getType());
        stm1.setInt(3, this.getId());
        
        stm2.setString(1, this.getFname());
        stm2.setString(2, this.getLname());
        stm2.setString(3, this.getPhone());
        stm2.setString(4, this.getEmail());
        stm2.setInt(5, this.getOwner_id());
        
        stm3.setString(1, this.getAddress());
        stm3.setInt(2, this.getProvince());
        stm3.setInt(3, this.getAmphur());
        stm3.setInt(4, this.getDistrict());
        stm3.setInt(5, this.getOwner_id());
        
        stm4.setString(1, this.getName());
        stm4.setString(2, this.getPropType());
        stm4.setString(3, this.getFloor());
        stm4.setInt(4, this.getPrice());
        stm4.setString(5, this.getWater());
        stm4.setString(6, this.getElectricity());
        stm4.setString(7, this.getFacilities());
        stm4.setString(8, this.getDetail());
        stm4.setInt(9, this.getDetails_id());
        
        stm5.setInt(1, this.getId());
        
        stm1.executeUpdate();
        stm2.executeUpdate();
        stm3.executeUpdate();
        stm4.executeUpdate();
        stm5.executeUpdate();

        // image file
        PreparedStatement stm6 = connection.prepareStatement(sql6);
        
        int i = 0;
        
        for (ImageMeta file : this.files) {
            stm6.setBlob(1, file.getInputStream());
            stm6.setInt(2, this.getDetails_id());
            stm6.setInt(3, this.getId());
            
            stm6.addBatch();
            i++;
            
            if (i % 100 == 0 || i == this.files.size()) {
                stm6.executeBatch(); // Execute every 100 items.
            }
        }
    }
    
    public static int delete(Connection conn, int id) throws SQLException {
        
        String sql1 = "DELETE FROM `image of detail` WHERE Res_id = ?";
        PreparedStatement stm1 = conn.prepareCall(sql1);
        stm1.setInt(1, id);
        stm1.executeUpdate();
        
        String sql2 = "DELETE FROM `details` WHERE Res_id = ?";
        PreparedStatement stm2 = conn.prepareCall(sql2);
        stm2.setInt(1, id);
        stm2.executeUpdate();
        
        String sql3 = "DELETE FROM `residential` WHERE Res_id = ?";
        PreparedStatement stm3 = conn.prepareCall(sql3);
        stm3.setInt(1, id);
        int row = stm3.executeUpdate();
        
        return row;
        
    }
    
    public static LinkedList<Residential> getResidentials(Connection conn, int emp_num) throws SQLException {
        
        LinkedList<Residential> ress = new LinkedList<>();
        
        String sql = "SELECT Res_id, Res_name, dt_modified FROM residential WHERE Emp_num = ?;";
        
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, emp_num);
        ResultSet rs = stm.executeQuery();
        
        Residential res = null;
        
        while (rs.next()) {
            res = new Residential();
            res.setId(rs.getInt("Res_id"));
            res.setName(rs.getString("Res_name"));
            res.setDt_time(rs.getString("dt_modified"));
            
            ress.add(res);
        }
        
        return ress;
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
