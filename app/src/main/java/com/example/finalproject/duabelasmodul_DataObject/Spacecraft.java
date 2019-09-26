package com.example.finalproject.duabelasmodul_DataObject;

/**
 * Created by Oclemy on 6/6/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class Spacecraft {

    int id;
    String name,address,description,image,code,phone_number,region;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return name;
    }

    public void setNama(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return address;
    }

    public void setAlamat(String address) {
        this.address = address;
    }

    public String getDeskripsi() {
        return description;
    }

    public void setDeskripsi(String description) {
        this.description = description;
    }

    public String getFotourl() {
        return image;
    }

    public void setFotourl(String image) {
        this.image = image;
    }

    public String getKode() {
        return code;
    }

    public void setKode(String code) {
        this.code = code;
    }



    public String getNomortelepon() {
        return phone_number;
    }

    public void setNomortelepon(String phone_number) {
        this.phone_number = phone_number;
    }
}
