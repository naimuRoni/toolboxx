package com.parkingkoi.service.toolbox.model;

public class OTP {
    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    String countyCode;
    String number;

    public OTP(String countyCode, String number) {
        this.countyCode = countyCode;
        this.number = number;
    }


}
