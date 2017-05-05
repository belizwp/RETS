package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {

    private int flag;
    private int number;
    private String fname;
    private String lname;
    private String phone;
    private String email;
    private String password;
    private String role;

    public Employee() {

    }

    public Employee(Connection conn, int number) throws SQLException {

        String sql = "SELECT * FROM employees WHERE Emp_num = ?;";

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, number);
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {

            this.setNumber(number);
            this.setFname(rs.getString("Fname"));
            this.setLname(rs.getString("Lname"));
            this.setPhone(rs.getString("phone"));
        }

    }

    public boolean validate(Connection connection, String email, String password) throws SQLException {
        String sql = "SELECT Emp_num, Fname, Lname, phone, role FROM employees WHERE email = ? AND password = ?;";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, email);
        stm.setString(2, password);

        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            this.setNumber(rs.getInt("Emp_num"));
            this.setFname(rs.getString("Fname"));
            this.setLname(rs.getString("Lname"));
            this.setPhone(rs.getString("phone"));
            this.setEmail(email);
            this.setPassword(password);
            this.setRole(rs.getString("role"));

            this.setFlag(1); // login flag

            return true;
        }

        return false;
    }

    public boolean register(Connection connection) throws SQLException {

        String sql = "INSERT INTO employees(Fname, Lname, phone, email, password, role) VALUES (?, ?, ?, ?, ?, ?);";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, fname);
        stm.setString(2, lname);
        stm.setString(3, phone);
        stm.setString(4, email);
        stm.setString(5, password);
        stm.setString(6, role);

        int row = stm.executeUpdate();

        if (row > 0) {
            return true;
        }

        return false;
    }

    public int update(Connection connection) throws SQLException {

        String sql = "UPDATE employees SET fname = ?, lname = ?, phone = ?, email = ?, password = ? WHERE Emp_num = ?;";

        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, this.fname);
        stm.setString(2, this.lname);
        stm.setString(3, this.phone);
        stm.setString(4, this.email);
        stm.setString(5, this.password);
        stm.setInt(6, this.number);

        int row = stm.executeUpdate();

        return row;

    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
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
     * @return the flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

}
