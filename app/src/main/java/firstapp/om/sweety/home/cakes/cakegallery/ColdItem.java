package firstapp.om.sweety.home.cakes.cakegallery;

import java.io.Serializable;

public class ColdItem implements Serializable {
    private String name;
    private String image;
    private String description;
    private String price;
    private int quantity;
    private int total;


//    public ColdItem(String name, String image, String description, String price, int quantity, String total, String status) {
//        this.name = name;
//        this.image = image;
//        this.description = description;
//        this.price = price;
//        this.quantity = quantity;
//        this.total = total;
//        this.status = "0";
//    }



    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
