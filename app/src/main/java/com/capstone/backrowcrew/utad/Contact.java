package com.capstone.backrowcrew.utad;

/**
 * Created by Russell on 5/12/2016.
 */
public class Contact {

    public Contact(){
        name = "";
        number = "";
    }

    public Contact(String nName, String nNumber){
        name = nName;
        number = nNumber;
    }

    public String getName(){
        return name;
    }

    public String getNumber(){
        return number;
    }

    public void setName(String nName){
        name = nName;
    }

    public void setNumber(String nNumber){
        number = nNumber;
    }

    public String toString(){return name + "\n" + number;}

    private String name;
    private String number;
}
