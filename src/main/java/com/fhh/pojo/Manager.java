package com.fhh.pojo;

import java.util.Date;

public class Manager {
    private String managerId;

    private String managerName;

    private String managerLoginName;

    private Integer managerAge;

    private String managerPassword;

    private String managerSex;

    private Date managerBirthday;

    private Integer managerRole;

    private String managerTelphone;

    private Date managerCreatetime;

    private Date managerUpdatetime;

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getManagerLoginName() {
        return managerLoginName;
    }

    public void setManagerLoginName(String managerLoginName) {
        this.managerLoginName = managerLoginName == null ? null : managerLoginName.trim();
    }

    public Integer getManagerAge() {
        return managerAge;
    }

    public void setManagerAge(Integer managerAge) {
        this.managerAge = managerAge;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword == null ? null : managerPassword.trim();
    }

    public String getManagerSex() {
        return managerSex;
    }

    public void setManagerSex(String managerSex) {
        this.managerSex = managerSex == null ? null : managerSex.trim();
    }

    public Date getManagerBirthday() {
        return managerBirthday;
    }

    public void setManagerBirthday(Date managerBirthday) {
        this.managerBirthday = managerBirthday;
    }

    public Integer getManagerRole() {
        return managerRole;
    }

    public void setManagerRole(Integer managerRole) {
        this.managerRole = managerRole;
    }

    public String getManagerTelphone() {
        return managerTelphone;
    }

    public void setManagerTelphone(String managerTelphone) {
        this.managerTelphone = managerTelphone == null ? null : managerTelphone.trim();
    }

    public Date getManagerCreatetime() {
        return managerCreatetime;
    }

    public void setManagerCreatetime(Date managerCreatetime) {
        this.managerCreatetime = managerCreatetime;
    }

    public Date getManagerUpdatetime() {
        return managerUpdatetime;
    }

    public void setManagerUpdatetime(Date managerUpdatetime) {
        this.managerUpdatetime = managerUpdatetime;
    }
}