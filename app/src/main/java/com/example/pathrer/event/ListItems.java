package com.example.pathrer.event;

import com.parse.ParseFile;

/**
 * Created by Pathrer on 16-03-16.
 */
public class ListItems {
    public String title;
    public String place;
    public String price;
    public String phno;
    public String email;
    public ParseFile image;

    public ListItems(){}

    public ListItems(String tit, String pla,String pri,String email,String phno,ParseFile im) {
        title = tit;
        place = pla;
        price = pri;
        image = im;
        this.email = email;
        this.phno = phno;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String s){
        email=s;
    }
    public void setPhno(String ph){
        phno = ph;
    }
    public String getPhno(){
        return phno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ParseFile getImage() {
        return image;
    }

    public void setImage(ParseFile image) {
        this.image = image;
    }
}