package com.fhh.pojo;

import java.util.Date;

public class Tenant {
    private String tenantId;

    private String tenantName;

    private String tenantLoginName;

    private Integer tenantAge;

    private String tenantPassword;

    private String tenantSex;

    private Date tenantBirthday;

    private Integer tenantRole;

    private String tenantTelphone;

    private Date tenantCreatetime;

    private Date tenantUpdatetime;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName == null ? null : tenantName.trim();
    }

    public String getTenantLoginName() {
        return tenantLoginName;
    }

    public void setTenantLoginName(String tenantLoginName) {
        this.tenantLoginName = tenantLoginName == null ? null : tenantLoginName.trim();
    }

    public Integer getTenantAge() {
        return tenantAge;
    }

    public void setTenantAge(Integer tenantAge) {
        this.tenantAge = tenantAge;
    }

    public String getTenantPassword() {
        return tenantPassword;
    }

    public void setTenantPassword(String tenantPassword) {
        this.tenantPassword = tenantPassword == null ? null : tenantPassword.trim();
    }

    public String getTenantSex() {
        return tenantSex;
    }

    public void setTenantSex(String tenantSex) {
        this.tenantSex = tenantSex == null ? null : tenantSex.trim();
    }

    public Date getTenantBirthday() {
        return tenantBirthday;
    }

    public void setTenantBirthday(Date tenantBirthday) {
        this.tenantBirthday = tenantBirthday;
    }

    public Integer getTenantRole() {
        return tenantRole;
    }

    public void setTenantRole(Integer tenantRole) {
        this.tenantRole = tenantRole;
    }

    public String getTenantTelphone() {
        return tenantTelphone;
    }

    public void setTenantTelphone(String tenantTelphone) {
        this.tenantTelphone = tenantTelphone == null ? null : tenantTelphone.trim();
    }

    public Date getTenantCreatetime() {
        return tenantCreatetime;
    }

    public void setTenantCreatetime(Date tenantCreatetime) {
        this.tenantCreatetime = tenantCreatetime;
    }

    public Date getTenantUpdatetime() {
        return tenantUpdatetime;
    }

    public void setTenantUpdatetime(Date tenantUpdatetime) {
        this.tenantUpdatetime = tenantUpdatetime;
    }
}