/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import database.MyConnection;
import dtos.UserDTO;
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
public class UserDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public UserDAO() {

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

    public String checkLogin(String username, String password) throws Exception {
        String role = "failed";
        try {
            String sql = "select role from tbl_User where Username = ? and Password = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                role = rs.getString("Role");
            }
        } finally {
            closeConnection();
        }
        return role;
    }

    public List<UserDTO> printList() throws Exception {
        List<UserDTO> list = null;
        String username = null;
        String fullname = null;
        String dob = null;
        String stt = null;
        UserDTO dto = null;

        try {
            String sql = "Select username, fullname, dob, userstt from tbl_User";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                username = rs.getString("Username");
                fullname = rs.getString("Fullname");
                dob = rs.getString("dob");
                stt = rs.getString("Userstt");
                dto = new UserDTO(username, fullname, dob, stt);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean deleteUser(String username) throws Exception {
        boolean delete = false;

        try {
            String sql = "update tbl_User set UserStt = ? where username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "inactive");
            preStm.setString(2, username);
            delete = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return delete;
    }

    public boolean updateUser(UserDTO dto) throws Exception {
        boolean updated = false;
        try {
            String sql = "update tbl_User set fullname = ?, dob = ?, Userstt = ? where username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getFullname());
            preStm.setString(2, dto.getDob());
            preStm.setString(3, dto.getStt());
            preStm.setString(4, dto.getUsername());
            updated = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return updated;
    }

    public boolean insertUser(UserDTO dto) throws Exception {
        boolean inserted = false;
        try {
            String sql = "Insert into tbl_User(Username, Password, Fullname, Role, DoB, UserStt) "
                    + "values(?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUsername());
            preStm.setString(2, dto.getPassword());
            preStm.setString(3, dto.getFullname());
            preStm.setString(4, "User");
            preStm.setString(5, dto.getDob());
            preStm.setString(6, "active");
            inserted = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return inserted;
    }

    public UserDTO searchUsername(String searchUsername) throws Exception {
        UserDTO dto = null;
        String username = null;
        String fullname = null;
        String dob = null;
        String stt = null;

        try {
            String sql = "Select username, fullname, dob, userstt from tbl_User where username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, searchUsername);
            rs = preStm.executeQuery();
            if (rs.next()) {
                username = rs.getString("Username");
                fullname = rs.getString("Fullname");
                dob = rs.getString("dob");
                stt = rs.getString("UserStt");
                dto = new UserDTO(username, fullname, dob, stt);
            }

        } finally {
            closeConnection();
        }
        return dto;
    }

    public UserDTO viewProfile(String searchUsername) throws Exception {
        UserDTO dto = null;
        String fullname = null;
        String dob = null;
        String stt = null;
        String password = null;

        try {
            String sql = "Select password, fullname, dob, userstt from tbl_User where username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, searchUsername);
            rs = preStm.executeQuery();
            if (rs.next()) {
                fullname = rs.getString("Fullname");
                dob = rs.getString("dob");
                stt = rs.getString("UserStt");
                password = rs.getString("Password");
                dto = new UserDTO(searchUsername, password, fullname, dob, stt);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean updateProfile(UserDTO dto) throws Exception {
        boolean updated = false;
        try {
            String sql = "update tbl_User set fullname = ?, dob = ? where username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getFullname());
            preStm.setString(2, dto.getDob());
            preStm.setString(3, dto.getUsername());
            updated = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return updated;
    }

    public boolean updatePassword(UserDTO dto) throws Exception {
        boolean updated = false;
        try {
            String sql = "update tbl_User set password = ? where username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getPassword());
            preStm.setString(2, dto.getUsername());
            updated = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return updated;
    }

    public String getPassword(String username) throws Exception {
        String pass = null;
        try {
            String sql = "select password from tbl_User where username = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, username);
            rs = preStm.executeQuery();
            while (rs.next()) {
                pass = rs.getString("Password");
            }
        } finally {
            closeConnection();
        }
        return pass;
    }
}
