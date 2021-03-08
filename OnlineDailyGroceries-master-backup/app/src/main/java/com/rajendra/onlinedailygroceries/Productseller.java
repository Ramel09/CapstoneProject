package com.rajendra.onlinedailygroceries;

public class Productseller {
    private int id;
    private String name;
    private  Double price;
    private byte[] image;
    private String description;

    public Productseller(int id, String name, Double price, byte[] image, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description=description;

    }

    public int getId(){
        return id;
}

    public void setId(int id) {
        this.id = id;
    }
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice(){
        return price;

    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
