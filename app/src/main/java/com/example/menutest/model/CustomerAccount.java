package com.example.menutest.model;

import com.google.gson.annotations.SerializedName;

class CustomerAccount {
    @SerializedName("id")
    private float id;
    @SerializedName("type_id")
    private float typeId;
    @SerializedName("reference_type")
    private String referenceType;
    @SerializedName("customer_id")
    private float customerId;
    @SerializedName("brand_id")
    private float brandId;
    @SerializedName("first_name")
    private String firstname;
    @SerializedName("last_name")
    private String lastname;
    @SerializedName("email")
    private String email;
    @SerializedName("confirmed")
    private boolean confirmed;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("locale")
    private String locale;
    @SerializedName("state")
    private float state;
    @SerializedName("optin_status_email")
    private float optinStatusEmail;
    @SerializedName("optin_status_pn")
    private float optinStatusPn;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("created_at")
    private String createdAt;

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public float getTypeId() {
        return typeId;
    }

    public void setTypeId(float typeId) {
        this.typeId = typeId;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public float getCustomerId() {
        return customerId;
    }

    public void setCustomerId(float customerId) {
        this.customerId = customerId;
    }

    public float getBrandId() {
        return brandId;
    }

    public void setBrandId(float brandId) {
        this.brandId = brandId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public float getState() {
        return state;
    }

    public void setState(float state) {
        this.state = state;
    }

    public float getOptinStatusEmail() {
        return optinStatusEmail;
    }

    public void setOptinStatusEmail(float optinStatusEmail) {
        this.optinStatusEmail = optinStatusEmail;
    }

    public float getOptinStatusPn() {
        return optinStatusPn;
    }

    public void setOptinStatusPn(float optinStatusPn) {
        this.optinStatusPn = optinStatusPn;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
