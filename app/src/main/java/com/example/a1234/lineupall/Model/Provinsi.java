package com.example.a1234.lineupall.Model;

public class Provinsi {
    String Id,Cl_username,Cl_phone;

    public Provinsi(String id,String cl_username,String cl_phone){
        Id=id;
        Cl_username=cl_username;
        Cl_phone=cl_phone;
    }

    public String getId(){
        return Id;
    }

    public String getCl_username(){
        return Cl_username;
    }

    public String getCl_phone(){
        return Cl_phone;
    }
}