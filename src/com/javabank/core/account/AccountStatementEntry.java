package com.javabank.core.account;

import java.util.Date;

public class AccountStatementEntry implements Comparable<AccountStatementEntry> {

    protected Date date = null;
    protected String description = null;
    protected double value = 0.00;

    public AccountStatementEntry(Date date, String description, double value) {
        this.date = date;
        this.description = description;
        this.value = value;
    }

    public AccountStatementEntry(String description, double value) {
        this(new Date(), description, value);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public int compareTo(AccountStatementEntry accountStatementEntry) {
        return getDate().compareTo(accountStatementEntry.getDate());
    }
}
