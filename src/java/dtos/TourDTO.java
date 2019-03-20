/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author Hoang Pham
 */
public class TourDTO implements Serializable {

    String id, name, desc, date, category, stt;
    int qtt, cancel, buyQtt;
    float price;

    public TourDTO(String id, String name, String desc, String date, String category, String stt, int qtt, int cancel, float price) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.category = category;
        this.stt = stt;
        this.qtt = qtt;
        this.cancel = cancel;
        this.price = price;
    }

    public TourDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public TourDTO(String id, String name, int buyQtt) {
        this.id = id;
        this.name = name;
        this.buyQtt = buyQtt;
    }

    public TourDTO(String id, String name, int buyQtt, float price) {
        this.id = id;
        this.name = name;
        this.buyQtt = buyQtt;
        this.price = price;
    }

    public int getBuyQtt() {
        return buyQtt;
    }

    public void setBuyQtt(int buyQtt) {
        this.buyQtt = buyQtt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public int getQtt() {
        return qtt;
    }

    public void setQtt(int qtt) {
        this.qtt = qtt;
    }

    public int getCancel() {
        return cancel;
    }

    public void setCancel(int cancel) {
        this.cancel = cancel;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TourDTO{" + "id=" + id + ", name=" + name + ", desc=" + desc + ", date=" + date + ", category=" + category + ", stt=" + stt + ", qtt=" + qtt + ", cancel=" + cancel + ", buyQtt=" + buyQtt + ", price=" + price + '}';
    }

}
