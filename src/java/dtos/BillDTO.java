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
public class BillDTO {

    String id, username, tourId, tourName, billStt, buyDate;
    int buyQtt;
    float price;

    public BillDTO() {
    }

    public BillDTO(String id, String username, String tourId, String tourName, String billStt, String buyDate, int buyQtt, float price) {
        this.id = id;
        this.username = username;
        this.tourId = tourId;
        this.tourName = tourName;
        this.billStt = billStt;
        this.buyDate = buyDate;
        this.buyQtt = buyQtt;
        this.price = price;
    }

    public BillDTO(String id, String tourId, String tourName, String buyDate, int buyQtt, float price) {
        this.id = id;
        this.tourId = tourId;
        this.tourName = tourName;
        this.buyDate = buyDate;
        this.buyQtt = buyQtt;
        this.price = price;
    }

    public BillDTO(String username, String tourId, String tourName, String billStt, String buyDate, int buyQtt, float price) {
        this.username = username;
        this.tourId = tourId;
        this.tourName = tourName;
        this.billStt = billStt;
        this.buyDate = buyDate;
        this.buyQtt = buyQtt;
        this.price = price;
    }

    public BillDTO(String tourId, String tourName, String buyDate, int buyQtt, float price) {
        this.tourId = tourId;
        this.tourName = tourName;
        this.buyDate = buyDate;
        this.buyQtt = buyQtt;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTourId() {
        return tourId;
    }

    public void setTourId(String tourId) {
        this.tourId = tourId;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getBillStt() {
        return billStt;
    }

    public void setBillStt(String billStt) {
        this.billStt = billStt;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public int getBuyQtt() {
        return buyQtt;
    }

    public void setBuyQtt(int buyQtt) {
        this.buyQtt = buyQtt;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BillDTO{" + "username=" + username + ", tourId=" + tourId + ", tourName=" + tourName + ", billStt=" + billStt + ", buyDate=" + buyDate + ", buyQtt=" + buyQtt + ", price=" + price + '}';
    }

}
