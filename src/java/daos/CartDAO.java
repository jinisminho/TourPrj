/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import database.MyConnection;
import dtos.CartDTO;
import dtos.TourDTO;
import dtos.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoang Pham
 */
public class CartDAO {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public CartDAO() {

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

    public boolean addToCart(CartDTO dto) throws Exception {
        boolean added = false;
        try {
            String sql = "Insert into tbl_Cart(Username, TourID, tourName, quantity, price) "
                    + "values(?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUser().getUsername());
            preStm.setString(2, dto.getTour().getId());
            preStm.setString(3, dto.getTour().getName());
//            preStm.setString(4, String.valueOf(dto.getTour().getBuyQtt()));
            preStm.setString(4, "1");
            preStm.setString(5, String.valueOf(dto.getTour().getPrice()));
            added = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return added;
    }

    public int checkDuplicate(String username, String tourId) throws Exception {
        int duplicated = -1;
        try {
            String sql = "Select quantity from tbl_Cart where username = ? and tourId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, tourId);
            rs = preStm.executeQuery();
            if (rs.next()) {
                duplicated = Integer.parseInt(rs.getString("quantity"));
            } else {
                duplicated = -1;
            }
        } finally {
            closeConnection();
        }
        return duplicated;
    }

    public boolean updateCart(CartDTO dto) throws Exception {
        boolean updated = false;
        try {
            String sql = "update tbl_Cart set quantity = quantity+1 where username = ? and tourId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUser().getUsername());
            preStm.setString(2, dto.getTour().getId());
            updated = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return updated;
    }

    public boolean updateCart2(String username, String id, int qtt) throws Exception {
        boolean updated = false;
        try {
            String sql = "update tbl_Cart set quantity = ? where username = ? and tourId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, String.valueOf(qtt));
            preStm.setString(2, username);
            preStm.setString(3, id);
            updated = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return updated;
    }

    public List<CartDTO> printCart(String username) throws Exception {
        List<CartDTO> list = null;
        CartDTO cartDto = null;
        UserDTO userDto = null;
        TourDTO tourDto = null;
        String tourId = null;
        String tourName = null;
        int qtt = 0;
        float price = 0;
        try {
            String sql = "Select TourId, TourName, Quantity, Price from tbl_Cart where username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                tourId = rs.getString("TourId");
                tourName = rs.getString("TourName");
                qtt = Integer.parseInt(rs.getString("Quantity"));
                price = Float.parseFloat(rs.getString("Price"));
                userDto = new UserDTO(username);
                tourDto = new TourDTO(tourId, tourName, qtt, price);
                cartDto = new CartDTO(userDto, tourDto);
                list.add(cartDto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean deleteCart(String username, String tourId) throws Exception {
        boolean deleted = false;
        try {
            String sql = "delete from tbl_Cart where username = ? and tourId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, tourId);
            deleted = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return deleted;
    }
}
