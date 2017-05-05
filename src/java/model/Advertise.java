/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.servlet.http.Part;

/**
 *
 * @author dell
 */
public class Advertise {

    private int ads_id;
    private String topic;
    private String detail;
    private int res_id;
    private Part img;
    private String date;
    private boolean status;

    private boolean edit;

    public Advertise() {

    }

    public Advertise(String topic, String detail, Part img, int res_id) {
        this.topic = topic;
        this.detail = detail;
        this.img = img;
        this.res_id = res_id;

    }

    public Advertise(Connection conn, int id) throws SQLException {

        String sql = "SELECT topic, detail, Res_id FROM advertised WHERE Ads_id = ?;";

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            String topic = rs.getString("topic");
            String detail = rs.getString("detail");
            int res_id = rs.getInt("Res_id");

            this.setAds_id(id);
            this.setTopic(topic);
            this.setDetail(detail);
            this.setRes_id(res_id);
            this.setEdit(true);
        }

    }

    public static LinkedList<Advertise> getAdvertises(Connection conn, int emp_num) throws SQLException {

        LinkedList<Advertise> adss = new LinkedList<>();

        String sql = "SELECT Ads_id, topic, present_date, role_ads, Res_id FROM advertised WHERE Emp_num = ?;";

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, emp_num);
        ResultSet rs = stm.executeQuery();

        Advertise ads = null;

        while (rs.next()) {
            ads = new Advertise();
            ads.setAds_id(rs.getInt("Ads_id"));
            ads.setTopic(rs.getString("topic"));
            ads.setDate(rs.getString("present_date"));
            ads.setRes_id(rs.getInt("Res_id"));
            ads.setStatus(rs.getBoolean("role_ads"));

            adss.add(ads);
        }

        return adss;
    }

    public static int show(Connection conn, int id) throws SQLException {
        String sql1 = "SELECT role_ads FROM advertised WHERE Ads_id = ?;";
        PreparedStatement stm1 = conn.prepareStatement(sql1);
        stm1.setInt(1, id);
        ResultSet rs = stm1.executeQuery();

        boolean show = false;

        if (rs.next()) {
            show = rs.getBoolean("role_ads");
        }

        String sql = "UPDATE advertised SET role_ads = ? WHERE Ads_id = ?;";

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setBoolean(1, show ? false : true);
        stm.setInt(2, id);

        int row = stm.executeUpdate();

        return row;
    }

    public int present(Connection conn, int emp_num) throws SQLException, IOException {

        String sql = "INSERT INTO advertised (image, topic, detail, Res_id, Emp_num, role_ads) VALUES (?, ?, ?, ?, ?, ?);";

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setBlob(1, getImg().getInputStream());
        stm.setString(2, getTopic());
        stm.setString(3, getDetail());
        stm.setInt(4, getRes_id());
        stm.setInt(5, emp_num);
        stm.setBoolean(6, true);

        int row = stm.executeUpdate();

        return row;

    }

    public int update(Connection conn, int ads_id) throws SQLException, IOException {

        String sql = "UPDATE advertised SET image = ?, topic = ?, detail = ?, Res_id = ? WHERE Ads_id = ?;";

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setBlob(1, getImg().getInputStream());
        stm.setString(2, getTopic());
        stm.setString(3, getDetail());
        stm.setInt(4, getRes_id());
        stm.setInt(5, ads_id);

        int row = stm.executeUpdate();

        return row;

    }

    public static int delete(Connection conn, int ads_id) throws SQLException {
        String sql = "DELETE FROM advertised WHERE Ads_id = ?;";

        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setInt(1, ads_id);
        int row = stm.executeUpdate();

        return row;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public int getAds_id() {
        return ads_id;
    }

    public void setAds_id(int Ads_id) {
        this.ads_id = Ads_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getRes_id() {
        return res_id;
    }

    public void setRes_id(int res_id) {
        this.res_id = res_id;
    }

    /**
     * @return the img
     */
    public Part getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(Part img) {
        this.img = img;
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

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

}
