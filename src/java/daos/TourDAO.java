/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import database.MyConnection;
import dtos.TourDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hoang Pham
 */
public class TourDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public TourDAO() {
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

    public List<TourDTO> printTourList() throws Exception {
        List<TourDTO> list = null;
        String id = null;
        String name = null;
        float price = 0;
        String desc = null;
        String date = null;
        String category = null;
        int cancel = 0;
        int qtt = 0;
        String stt = null;
        TourDTO dto = null;

        try {
            String sql = "Select TourID, TourName, Price, TourDesc, GoDate, CancelDay, "
                    + "Category, TourQuantity, TourStt from tbl_Tour where TourStt = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "active");
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                id = rs.getString("TourID");
                name = rs.getString("TourName");
                price = Float.parseFloat(rs.getString("Price"));
                desc = rs.getString("TourDesc");
                date = rs.getString("GoDate");
                category = rs.getString("Category");
                cancel = Integer.parseInt(rs.getString("CancelDay"));
                qtt = Integer.parseInt(rs.getString("TourQuantity"));
                stt = rs.getString("TourStt");
                dto = new TourDTO(id, name, desc, date, category, stt, qtt, cancel, price);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }

        return list;
    }

    public List<TourDTO> printTourListIndex() throws Exception {
        List<TourDTO> list = null;
        String id = null;
        String name = null;
        float price = 0;
        String desc = null;
        String date = null;
        String category = null;
        int cancel = 0;
        int qtt = 0;
        String stt = null;
        TourDTO dto = null;

        try {
            String sql = "Select TourID, TourName, Price, TourDesc, GoDate, CancelDay, "
                    + "Category, TourQuantity, TourStt from tbl_Tour where TourStt = ? and TourQuantity > 0";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "active");
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                id = rs.getString("TourID");
                name = rs.getString("TourName");
                price = Float.parseFloat(rs.getString("Price"));
                desc = rs.getString("TourDesc");
                date = rs.getString("GoDate");
                category = rs.getString("Category");
                cancel = Integer.parseInt(rs.getString("CancelDay"));
                qtt = Integer.parseInt(rs.getString("TourQuantity"));
                stt = rs.getString("TourStt");
                dto = new TourDTO(id, name, desc, date, category, stt, qtt, cancel, price);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }

        return list;
    }

    public int getTourQtt(String tourId) throws Exception {
        int qtt = 0;
        try {
            String sql = "Select TourQuantity from tbl_Tour where TourId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, tourId);
            rs = preStm.executeQuery();
            if (rs.next()) {
                qtt = Integer.parseInt(rs.getString("TourQuantity"));
            }
        } finally {
            closeConnection();
        }
        return qtt;
    }

    public boolean updateTourQtt(String tourId, int buyQtt) throws Exception {
        boolean updated = false;
        try {
            String sql = "update tbl_Tour set TourQuantity = TourQuantity - ? where TourID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, String.valueOf(buyQtt));
            preStm.setString(2, tourId);
            updated = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return updated;
    }

    public List<TourDTO> printDelTourList() throws Exception {
        List<TourDTO> list = null;
        String id = null;
        String name = null;
        float price = 0;
        String desc = null;
        String date = null;
        String category = null;
        int cancel = 0;
        int qtt = 0;
        String stt = null;
        TourDTO dto = null;

        try {
            String sql = "Select TourID, TourName, Price, TourDesc, GoDate, CancelDay, "
                    + "Category, TourQuantity, TourStt from tbl_Tour where TourStt = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "inactive");
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                id = rs.getString("TourID");
                name = rs.getString("TourName");
                price = Float.parseFloat(rs.getString("Price"));
                desc = rs.getString("TourDesc");
                date = rs.getString("GoDate");
                category = rs.getString("TourDesc");
                cancel = Integer.parseInt(rs.getString("CancelDay"));
                qtt = Integer.parseInt(rs.getString("TourQuantity"));
                stt = rs.getString("TourStt");
                dto = new TourDTO(id, name, desc, date, category, stt, qtt, cancel, price);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }

        return list;
    }

    public boolean deleteTour(String id) throws Exception {
        boolean delete = false;

        try {
            String sql = "update tbl_Tour set TourStt = ? where TourID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "inactive");
            preStm.setString(2, id);
            delete = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return delete;
    }

    public boolean updateTour(TourDTO dto) throws Exception {
        boolean updated = false;
        try {
            String sql = "update tbl_Tour set TourName = ?, Price = ?, TourDesc = ?, "
                    + "GoDate = ?, CancelDay = ?, Category = ?, TourQuantity = ?, "
                    + "TourStt = ? where TourID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getName());
            preStm.setString(2, String.valueOf(dto.getPrice()));
            preStm.setString(3, dto.getDesc());
            preStm.setString(4, dto.getDate());
            preStm.setString(5, String.valueOf(dto.getCancel()));
            preStm.setString(6, dto.getCategory());
            preStm.setString(7, String.valueOf(dto.getQtt()));
            preStm.setString(8, dto.getStt());
            preStm.setString(9, dto.getId());
            updated = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return updated;
    }

    public boolean insertTour(TourDTO dto) throws Exception {
        boolean inserted = false;
        try {
            String sql = "Insert into tbl_Tour(TourID, TourName, Price, TourDesc, GoDate, CancelDay, "
                    + "Category, TourQuantity, TourStt) "
                    + "values(?,?,?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getId());
            preStm.setString(2, dto.getName());
            preStm.setString(3, String.valueOf(dto.getPrice()));
            preStm.setString(4, dto.getDesc());
            preStm.setString(5, dto.getDate());
            preStm.setString(6, String.valueOf(dto.getCancel()));
            preStm.setString(7, dto.getCategory());
            preStm.setString(8, String.valueOf(dto.getQtt()));
            preStm.setString(9, dto.getStt());
            inserted = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return inserted;
    }

    public List<TourDTO> searchTourByLikeName(String searchName) throws Exception {
        List<TourDTO> result = null;
        String id = null;
        String name = null;
        float price = 0;
        String desc = null;
        String date = null;
        String category = null;
        int cancel = 0;
        int qtt = 0;
        String stt = null;
        TourDTO dto = null;

        try {
            String sql = "Select TourID, TourName, Price, TourDesc, GoDate, CancelDay, "
                    + "Category, TourQuantity, TourStt from tbl_Tour where TourName Like ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + searchName + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                id = rs.getString("TourID");
                name = rs.getString("TourName");
                price = Float.parseFloat(rs.getString("Price"));
                desc = rs.getString("TourDesc");
                date = rs.getString("GoDate");
                category = rs.getString("TourDesc");
                cancel = Integer.parseInt(rs.getString("CancelDay"));
                qtt = Integer.parseInt(rs.getString("TourQuantity"));
                stt = rs.getString("TourStt");
                dto = new TourDTO(id, name, desc, date, category, stt, qtt, cancel, price);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }

        return result;
    }

    public int getCancelDays(String tourId) throws Exception {
        int days = 0;
        try {
            String sql = "Select CancelDay from tbl_Tour where tourId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, tourId);
            rs = preStm.executeQuery();
            while (rs.next()) {
                days = Integer.parseInt(rs.getString("CancelDay"));
            }
        } finally {
            closeConnection();
        }
        return days;
    }

    public String getGoDate(String tourId) throws Exception {
        String date = null;
        try {
            String sql = "Select GoDate from tbl_Tour where tourId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, tourId);
            rs = preStm.executeQuery();
            while (rs.next()) {
                date = rs.getString("GoDate");
            }
        } finally {
            closeConnection();
        }
        return date;
    }

    public boolean returnTourQtt(String tourId, int returnQtt) throws Exception {
        boolean updated = false;
        try {
            String sql = "update tbl_Tour set TourQuantity = TourQuantity + ? where TourID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, String.valueOf(returnQtt));
            preStm.setString(2, tourId);
            updated = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return updated;
    }

    public TourDTO viewTourInfo(String tourId) throws Exception {
        TourDTO dto = null;
        String id = tourId;
        String name = null;
        String desc = null;
        String date = null;
        String category = null;
        int qtt = 0;
        int cancel = 0;
        float price = 0;

        try {
            String sql = "Select TourName, Price, TourDesc, GoDate, CancelDay, Category, TourQuantity "
                    + "from tbl_Tour where TourID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            while (rs.next()) {
                name = rs.getString("TourName");
                desc = rs.getString("TourDesc");
                date = rs.getString("GoDate");
                category = rs.getString("Category");
                qtt = Integer.parseInt(rs.getString("TourQuantity"));
                cancel = Integer.parseInt(rs.getString("CancelDay"));
                price = Float.parseFloat(rs.getString("price"));
                dto = new TourDTO(id, name, desc, date, category, "active", qtt, cancel, price);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
