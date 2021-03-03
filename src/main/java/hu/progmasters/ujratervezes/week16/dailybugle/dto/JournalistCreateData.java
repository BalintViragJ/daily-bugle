package hu.progmasters.ujratervezes.week16.dailybugle.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class JournalistCreateData {


    private String name;
    private String address;
    private String email;
    private String telephoneNumber;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }



}



