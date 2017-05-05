package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class SearchDAO {

    private LinkedList<Residential> result;

    private int found_rows;

    private Integer p_id;
    private Integer a_id;
    private Integer d_id;
    private String ann;
    private String type;
    private Integer min;
    private Integer max;

    private int limit;
    private int page;

    public SearchDAO() {
        result = new LinkedList<>();
    }

    public LinkedList<Residential> search(Connection conn) throws SQLException {

        String count = "SELECT COUNT(Res_id) AS count FROM residential NATURAL JOIN details NATURAL JOIN location ";

        String select = "SELECT Res_id, Res_name, price, remark FROM residential NATURAL JOIN details NATURAL JOIN location ";

        String cond = "WHERE province_id IN (" + (p_id != null && p_id != 0 ? p_id : "SELECT province_id FROM province") + ") "
                + "AND amphur_id IN (" + (a_id != null && a_id != 0 ? a_id : "SELECT amphur_id FROM amphur") + ") "
                + "AND district_id IN (" + (d_id != null && d_id != 0 ? d_id : "SELECT district_id FROM district") + ") "
                + "AND announce_for IN (" + (ann != null && !ann.equals("") && !ann.equals("ทั้งหมด") ? "'" + ann + "'" : "'ขาย', 'เช่า'") + ") "
                + "AND types IN (" + (type != null && !type.equals("") && !type.equals("ทั้งหมด") ? "'" + type + "'" : "'บ้านเดี่ยว', 'คอนโด', 'ทาวน์เฮ้าส์', 'ที่ดิน', 'อพาร์ทเม้น'") + ") "
                + "AND price BETWEEN " + min + " AND " + max + " "
                + "ORDER BY dt_modified DESC ";
        
        String lim = "LIMIT " + (limit * (page - 1)) + ", " + limit;
        
        Statement stmCount = conn.createStatement();
        ResultSet rsCount = stmCount.executeQuery(count + cond);
        if (rsCount.next()) {
            found_rows = rsCount.getInt("count");
        }

        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(select + cond + lim);

        Residential res = null;

        while (rs.next()) {
            res = new Residential();
            res.setId(rs.getInt("Res_id"));
            res.setName(rs.getString("Res_name"));
            res.setPrice(rs.getInt("price"));
            res.setDetail(rs.getString("remark"));

            result.add(res);
        }

        return result;
    }

    /**
     * @return the result
     */
    public LinkedList<Residential> getResult() {
        return result;
    }

    /**
     * @return the p_id
     */
    public Integer getP_id() {
        return p_id;
    }

    /**
     * @param p_id the p_id to set
     */
    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    /**
     * @return the a_id
     */
    public Integer getA_id() {
        return a_id;
    }

    /**
     * @param a_id the a_id to set
     */
    public void setA_id(Integer a_id) {
        this.a_id = a_id;
    }

    /**
     * @return the d_id
     */
    public Integer getD_id() {
        return d_id;
    }

    /**
     * @param d_id the d_id to set
     */
    public void setD_id(Integer d_id) {
        this.d_id = d_id;
    }

    /**
     * @return the ann
     */
    public String getAnn() {
        return ann;
    }

    /**
     * @param ann the ann to set
     */
    public void setAnn(String ann) {
        this.ann = ann;
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
     * @return the min
     */
    public Integer getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(Integer min) {
        this.min = min != null ? min : -2147483647;
    }

    /**
     * @return the max
     */
    public Integer getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(Integer max) {
        this.max = max != null ? max : 2147483647;
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(Integer page) {
        this.page = page != null ? page : 1;
    }

    /**
     * @return the found_rows
     */
    public int getFound_rows() {
        return found_rows;
    }

}
