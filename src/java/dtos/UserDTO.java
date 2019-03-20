/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Hoang Pham
 */
public class UserDTO {

    private String username, password, fullname, role, dob, stt;

    public UserDTO() {
    }

    public UserDTO(String username) {
        this.username = username;
    }
    
    

    public UserDTO(String username, String password, String fullname, String dob, String stt) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.dob = dob;
        this.stt = stt;
    }

    public UserDTO(String username, String fullname, String dob, String stt) {
        this.username = username;
        this.fullname = fullname;
        this.dob = dob;
        this.stt = stt;
    }

    public UserDTO(String username, String fullname, String dob) {
        this.username = username;
        this.fullname = fullname;
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "username=" + username + ", password=" + password + ", fullname=" + fullname + ", role=" + role + ", dob=" + dob + ", stt=" + stt + '}';
    }

}
