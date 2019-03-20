/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Hoang Pham
 */
public class ValidationFailObj {

    String usernameErr, passErr, fullnameErr, dobErr, tourIdErr, tourNameErr,
            priceErr, descErr, dateErr, cancelErr, categoryErr, qttErr, sttErr,
            invalidLogin, passMismatched, oldPassMismatched;

    public String getOldPassMismatched() {
        return oldPassMismatched;
    }

    public void setOldPassMismatched(String oldPassMismatched) {
        this.oldPassMismatched = oldPassMismatched;
    }

    public String getPassMismatched() {
        return passMismatched;
    }

    public void setPassMismatched(String passMismatched) {
        this.passMismatched = passMismatched;
    }

    public String getInvalidLogin() {
        return invalidLogin;
    }

    public void setInvalidLogin(String invalidLogin) {
        this.invalidLogin = invalidLogin;
    }

    public ValidationFailObj() {
    }

    public String getUsernameErr() {
        return usernameErr;
    }

    public void setUsernameErr(String usernameErr) {
        this.usernameErr = usernameErr;
    }

    public String getPassErr() {
        return passErr;
    }

    public void setPassErr(String passErr) {
        this.passErr = passErr;
    }

    public String getFullnameErr() {
        return fullnameErr;
    }

    public void setFullnameErr(String fullnameErr) {
        this.fullnameErr = fullnameErr;
    }

    public String getDobErr() {
        return dobErr;
    }

    public void setDobErr(String dobErr) {
        this.dobErr = dobErr;
    }

    public String getTourIdErr() {
        return tourIdErr;
    }

    public void setTourIdErr(String tourIdErr) {
        this.tourIdErr = tourIdErr;
    }

    public String getTourNameErr() {
        return tourNameErr;
    }

    public void setTourNameErr(String tourNameErr) {
        this.tourNameErr = tourNameErr;
    }

    public String getPriceErr() {
        return priceErr;
    }

    public void setPriceErr(String priceErr) {
        this.priceErr = priceErr;
    }

    public String getDescErr() {
        return descErr;
    }

    public void setDescErr(String descErr) {
        this.descErr = descErr;
    }

    public String getDateErr() {
        return dateErr;
    }

    public void setDateErr(String dateErr) {
        this.dateErr = dateErr;
    }

    public String getCancelErr() {
        return cancelErr;
    }

    public void setCancelErr(String cancelErr) {
        this.cancelErr = cancelErr;
    }

    public String getCategoryErr() {
        return categoryErr;
    }

    public void setCategoryErr(String categoryErr) {
        this.categoryErr = categoryErr;
    }

    public String getQttErr() {
        return qttErr;
    }

    public void setQttErr(String qttErr) {
        this.qttErr = qttErr;
    }

    public String getSttErr() {
        return sttErr;
    }

    public void setSttErr(String sttErr) {
        this.sttErr = sttErr;
    }

    public boolean checkDob(String date) throws Exception {
        boolean valid = false;

//        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
//        format.setLenient(false);
//        LocalDate birthday = LocalDate.parse(date, formatter);
//        format.parse(date);
//        int age = Period.between(birthday, LocalDate.now()).getYears();
//        if (age >= 18) {
//            valid = true;
//        }
        DateFormat format2 = new SimpleDateFormat("yyyy-M-d");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-M-d");
        format2.setLenient(false);
        LocalDate birthday2 = LocalDate.parse(date, formatter2);
        format2.parse(date);
        int age2 = Period.between(birthday2, LocalDate.now()).getYears();
        if (age2 >= 18) {
            valid = true;
        }
        return valid;
    }

    public boolean checkDate(String date) throws Exception {
        boolean valid = false;
        DateFormat format = new SimpleDateFormat("yyyy-M-dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        format.setLenient(false);
        LocalDate goDate = LocalDate.parse(date, formatter);
        format.parse(date);
        int day = Period.between(goDate, LocalDate.now()).getDays();
        int month = Period.between(goDate, LocalDate.now()).getMonths();
        int year = Period.between(goDate, LocalDate.now()).getYears();
        if (year == 0) {
            if (month == 0) {
                if (day < 0) {
                    valid = true;
                }
            }
            if (month < 0) {
                valid = true;
            }
        }
        if (year < 0) {
            valid = true;
        }
        return valid;
    }

    public boolean checkCancel(String date, int cancel) throws Exception {
        boolean valid = false;
        DateFormat format = new SimpleDateFormat("yyyy-M-dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        format.setLenient(false);
        LocalDate goDate = LocalDate.parse(date, formatter);
        format.parse(date);
        int day = Period.between(goDate, LocalDate.now()).getDays();
        int month = Period.between(goDate, LocalDate.now()).getMonths();
        int year = Period.between(goDate, LocalDate.now()).getYears();
        if (year == 0) {
            if (month == 0) {
                if (day < -cancel) {
                    valid = true;
                }
            }
            if (month < 0) {
                valid = true;
            }
        }
        if (year < 0) {
            valid = true;
        }
        return valid;
    }

}
