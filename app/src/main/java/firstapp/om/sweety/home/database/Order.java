package firstapp.om.sweety.home.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import firstapp.om.sweety.R;
import firstapp.om.sweety.home.cakes.cakegallery.ColdItem;

public class Order {
    private String name;
    private String phone;
    private String area;
    private String location;
    private String total;
    private List<ColdItem> list;

    public Order(String name, String phone, String area, String location, String total, List<ColdItem> list) {
        this.name = name;
        this.phone = phone;
        this.area = area;
        this.location = location;
        this.total = total;
        this.list = list;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTotal() {
        return this.total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ColdItem> getList() {
        return this.list;
    }

    public void setList(List<ColdItem> list) {
        this.list = list;
    }
}
