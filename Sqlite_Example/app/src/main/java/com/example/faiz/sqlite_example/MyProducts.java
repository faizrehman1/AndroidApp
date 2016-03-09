package com.example.faiz.sqlite_example;

public class MyProducts {

    private int product_id;
    private String productname;

    public MyProducts() {
    }

    public MyProducts(String productname) {

        this.productname = productname;
        this.product_id=product_id;
    }



    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }
}
