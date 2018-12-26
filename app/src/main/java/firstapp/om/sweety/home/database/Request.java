package firstapp.om.sweety.home.database;

import java.util.ArrayList;
import java.util.List;

import firstapp.om.sweety.home.cakes.cakegallery.ColdItem;

public class Request {
    private String phone;
    private String name;
    private String address;
    private String total;
    private List<ColdItem> list;  //list of cake order

    public Request() {
    }


    public Request(String phone, String name, String address, String total, List<ColdItem> list) {
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.total = total;
        this.list = list;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
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
