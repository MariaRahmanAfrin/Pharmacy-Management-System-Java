/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Medicine {
    private String id;
    private String name;
    private String genericName;
    private String category;
    private String strength;
    private double price;
    private int stock;
    private String imageName;

    // Constructor: নতুন একটি মেডিসিন তৈরি করার সময় এই তথ্যগুলো দিতে হবে
    public Medicine(String id, String name, String genericName, String category, String strength, double price, int stock, String imageName) {
        this.id = id;
        this.name = name;
        this.genericName = genericName;
        this.category = category;
        this.strength = strength;
        this.price = price;
        this.stock = stock;
        this.imageName = imageName;
    }

    // Getters & Setters (Encapsulation-এর জন্য)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGenericName() { return genericName; }
    public void setGenericName(String genericName) { this.genericName = genericName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getStrength() { return strength; }
    public void setStrength(String strength) { this.strength = strength; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getImageName() { return imageName; }
    public void setImageName(String imageName) { this.imageName = imageName; }
}