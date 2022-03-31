package com.example.menutest.model;

import com.google.gson.annotations.SerializedName;

public class DataForUser {
    @SerializedName("customer_account")
    private CustomerAccount customerAccount;
    @SerializedName("token")
    private TokenModel tokenModel;

    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    public TokenModel getTokenModel() {
        return tokenModel;
    }

    public void setTokenModel(TokenModel tokenModel) {
        this.tokenModel = tokenModel;
    }
}
