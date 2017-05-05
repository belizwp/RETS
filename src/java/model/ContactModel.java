package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ContactModel {

    private int id;
    private String fname;
    private String lname;
    private String phone;
    private String email;
    private String desc;
    private String date;

    public ContactModel() {

    }

    public ContactModel(String fname, String lname, String phone, String email, String desc) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.email = email;
        this.desc = desc;
    }

    public int sendContact(Connection conn, int emp_num) throws SQLException {

        String sql1 = "INSERT INTO customer (Fname, Lname, phone, email) VALUES (?, ?, ?, ?)";
        String sql2 = "SET @cus_id := LAST_INSERT_ID();";
        String sql3 = "INSERT INTO contact (Cus_id, Emp_num, cont_desc) VALUES (@cus_id, ?, ?)";

        PreparedStatement stm1 = conn.prepareStatement(sql1);
        stm1.setString(1, getFname());
        stm1.setString(2, getLname());
        stm1.setString(3, getPhone());
        stm1.setString(4, getEmail());
        stm1.executeUpdate();

        PreparedStatement stm2 = conn.prepareStatement(sql2);
        stm2.executeQuery();

        PreparedStatement stm3 = conn.prepareStatement(sql3);
        stm3.setInt(1, emp_num);
        stm3.setString(2, getDesc());
        int row = stm3.executeUpdate();

        return row;
    }

    public static int delete(Connection conn, int id) throws SQLException {

        String sql1 = "DELETE FROM contact WHERE Cus_id = ?";
        String sql2 = "DELETE FROM customer WHERE Cus_id = ?";

        PreparedStatement stm1 = conn.prepareStatement(sql1);
        stm1.setInt(1, id);
        stm1.executeUpdate();

        PreparedStatement stm2 = conn.prepareStatement(sql2);
        stm2.setInt(1, id);
        int row = stm2.executeUpdate();

        return row;

    }

    public static LinkedList<ContactModel> getContactModels(Connection conn, int emp_num) throws SQLException {

        LinkedList<ContactModel> contacts = new LinkedList<>();

        String sql = "SELECT Cus_id, cont_desc, cont_date, Fname, Lname, phone, email "
                + "FROM contact JOIN customer USING (Cus_id)  "
                + "WHERE Emp_num = ?;";

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, emp_num);
        ResultSet rs = stm.executeQuery();

        ContactModel con = null;

        while (rs.next()) {
            con = new ContactModel();
            con.setId(rs.getInt("Cus_id"));
            con.setDesc(rs.getString("cont_desc"));
            con.setDate(rs.getString("cont_date"));
            con.setFname(rs.getString("Fname"));
            con.setLname(rs.getString("Lname"));
            con.setPhone(rs.getString("phone"));
            con.setEmail(rs.getString("email"));

            contacts.add(con);
        }

        return contacts;
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
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
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
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

}
