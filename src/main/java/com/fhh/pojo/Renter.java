package com.fhh.pojo;

import java.io.Serializable;
import java.util.Date;

public class Renter implements Serializable {
    private String renterId;

    private String renterName;

    private String renterLoginName;

    private Integer renterAge;

    private String renterPassword;

    private String renterSex;

    private Date renterBirthday;

    private Integer renterRole;

    private String renterTelphone;

    private Date renterCreatetime;

    private Date renterUpdatetime;

    public String getRenterId() {
        return renterId;
    }

    public void setRenterId(String renterId) {
        this.renterId = renterId == null ? null : renterId.trim();
    }

    public String getRenterName() {
        return renterName;
    }

    public void setRenterName(String renterName) {
        this.renterName = renterName == null ? null : renterName.trim();
    }

    public String getRenterLoginName() {
        return renterLoginName;
    }

    public void setRenterLoginName(String renterLoginName) {
        this.renterLoginName = renterLoginName == null ? null : renterLoginName.trim();
    }

    public Integer getRenterAge() {
        return renterAge;
    }

    public void setRenterAge(Integer renterAge) {
        this.renterAge = renterAge;
    }

    public String getRenterPassword() {
        return renterPassword;
    }

    public void setRenterPassword(String renterPassword) {
        this.renterPassword = renterPassword == null ? null : renterPassword.trim();
    }

    public String getRenterSex() {
        return renterSex;
    }

    public void setRenterSex(String renterSex) {
        this.renterSex = renterSex == null ? null : renterSex.trim();
    }

    public Date getRenterBirthday() {
        return renterBirthday;
    }

    public void setRenterBirthday(Date renterBirthday) {
        this.renterBirthday = renterBirthday;
    }

    public Integer getRenterRole() {
        return renterRole;
    }

    public void setRenterRole(Integer renterRole) {
        this.renterRole = renterRole;
    }

    public String getRenterTelphone() {
        return renterTelphone;
    }

    public void setRenterTelphone(String renterTelphone) {
        this.renterTelphone = renterTelphone == null ? null : renterTelphone.trim();
    }

    public Date getRenterCreatetime() {
        return renterCreatetime;
    }

    public void setRenterCreatetime(Date renterCreatetime) {
        this.renterCreatetime = renterCreatetime;
    }

    public Date getRenterUpdatetime() {
        return renterUpdatetime;
    }

    public void setRenterUpdatetime(Date renterUpdatetime) {
        this.renterUpdatetime = renterUpdatetime;
    }
}