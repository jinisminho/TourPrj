/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import database.MyConnection;
import dtos.BillDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoang Pham
 */
public class BillDAO {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public BillDAO() {

    }

    public void closeConnection() throws Exception {

        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public boolean insertBill(BillDTO dto) throws Exception {
        boolean inserted = false;
        try {
            String sql = "Insert into tbl_Bill(username, TourID, TourName, BuyQtt, billStt, price, BuyDate) values(?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUsername());
            preStm.setString(2, dto.getTourId());
            preStm.setString(3, dto.getTourName());
            preStm.setString(4, String.valueOf(dto.getBuyQtt()));
            preStm.setString(5, dto.getBillStt());
            preStm.setString(6, String.valueOf(dto.getPrice()));
            preStm.setString(7, LocalDate.now().toString());
            inserted = preStm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return inserted;
    }

    public List<BillDTO> viewBill(String username) throws Exception {
        List<BillDTO> list = null;
        String tourId, date, name, billId;
        int qtt;
        float price;
        try {
            String sql = "Select BillId, TourID, BuyQtt, BuyDate, TourName, price from tbl_Bill where username = ? and billStt != 'cancelled'";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                tourId = rs.getString("TourID");
                date = rs.getString("BuyDate");
                name = rs.getString("TourName");
                qtt = Integer.parseInt(rs.getString("BuyQtt"));
                price = Float.parseFloat(rs.getString("price"));
                billId = rs.getString("BillId");
//                BillDTO dto = new BillDTO(billId, username, id, name, date, qtt, price);
                    BillDTO dto = new BillDTO(billId, tourId, name, date, qtt, price);
//                BillDTO dto = new BillDTO(username, id, username, name, date, qtt, price);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean cancelBill(String id) throws Exception {
        boolean cancelled = false;
        try {
            String sql = "Update tbl_Bill set BillStt = ? where BillId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "cancelled");
            preStm.setString(2, id);
            cancelled = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return cancelled;
    }
}
