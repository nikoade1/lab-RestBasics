package com.epam.esm.model;

import java.time.LocalDateTime;

public class GiftCertificate {

    private int id;
    private String name;
    private String Description;
    private double price;
    private int duration;
    private LocalDateTime create_date;
    private LocalDateTime last_update_date;

    private Tag tag;

    public GiftCertificate() {
    }

    public GiftCertificate(int id, String name, String description, double price, int duration, LocalDateTime create_date, LocalDateTime last_update_date) {
        this.id = id;
        this.name = name;
        Description = description;
        this.price = price;
        this.duration = duration;
        this.create_date = create_date;
        this.last_update_date = last_update_date;
        this.tag = null;
    }

    public GiftCertificate(int id, String name, String description, double price, int duration, LocalDateTime create_date, LocalDateTime last_update_date, Tag tag) {
        this(id, name, description, price, duration, create_date, last_update_date);
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDateTime create_date) {
        this.create_date = create_date;
    }

    public LocalDateTime getLast_update_date() {
        return last_update_date;
    }

    public void setLast_update_date(LocalDateTime last_update_date) {
        this.last_update_date = last_update_date;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
