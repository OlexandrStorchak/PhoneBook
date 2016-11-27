package com.example.alex.phonebook;

/**
 * Created by Alex on 23.11.2016.
 */

public class ContactItem {
    String name;
    String phone;

    public ContactItem(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
