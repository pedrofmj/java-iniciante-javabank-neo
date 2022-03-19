package com.javabank.com.javabank.core.transaction;

public class Transaction {

    public static final String TYPE_WITHDRAW = "withdraw";
    public static final String TYPE_DEPOSIT = "deposit";
    public static final String TYPE_FEE = "fee";
    public static final String TYPE_INCOME = "income";
    public static final String TYPE_TRANSFER_TEF = "transfer_tef";
    public static final String TYPE_TRANSFER_PIX = "transfer_pix";

    protected String type;
    protected String accountBrand;
    protected String accountName;
    protected String objectAccountBrand;
    protected String objectAccountName;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountBrand() {
        return accountBrand;
    }

    public void setAccountBrand(String accountBrand) {
        this.accountBrand = accountBrand;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getObjectAccountBrand() {
        return objectAccountBrand;
    }

    public void setObjectAccountBrand(String objectAccountBrand) {
        this.objectAccountBrand = objectAccountBrand;
    }

    public String getObjectAccountName() {
        return objectAccountName;
    }

    public void setObjectAccountName(String objectAccountName) {
        this.objectAccountName = objectAccountName;
    }

}
