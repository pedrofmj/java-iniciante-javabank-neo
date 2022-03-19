package com.javabank.core.account;

import com.javabank.data.json.JsonBankingRepository;

import java.io.IOException;
import java.util.Date;

public class Account {

    protected String brand = "0001";
    protected String name = "";
    protected String displayName = "";
    protected double ballance = 0.00;
    protected AccountStatement statement = null;

    protected transient JsonBankingRepository<Account> repository = null;

    public static boolean exists(String brand, String name) {
        return JsonBankingRepository.exists(String.format("account-%s-%s", brand, name));
    }

    public static Account get(String brand, String name) {
        if (!exists(brand, name)) {
            return null;
        }
        JsonBankingRepository<Account> repository = new JsonBankingRepository<Account>(String.format("account-%s-%s", brand, name), Account.class);
        Account account = repository.getData();
        account.setRepository(repository);
        return account;
    }

    public Account(String name, String displayName, double ballance) {
        this.name = name;
        this.displayName = displayName;
        this.ballance = 0.00;
        this.statement = new AccountStatement();
        if (ballance != 0.00) {
            addStatementEntry("Initial Ballance", ballance);
        }
        try {
            initialize();
        } catch (Throwable t) {
            // NOTHING
            t.printStackTrace();
            this.repository = null;
        }
    }

    public void initialize() throws IOException {
        this.repository = new JsonBankingRepository(String.format("account-%s-%s", this.brand, this.name), this, Account.class);
        this.repository.initialize();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public double getBallance() {
        return ballance;
    }

    public void setBallance(double ballance) {
        this.ballance = ballance;
    }

    public AccountStatement getStatement() {
        return statement;
    }

    public void setStatement(AccountStatement statement) {
        this.statement = statement;
    }

    public JsonBankingRepository<Account> getRepository() {
        return repository;
    }

    public void setRepository(JsonBankingRepository<Account> repository) {
        this.repository = repository;
    }

    public void operationWithdraw(double value) {
        addStatementEntry("WITHDRAW", -value);
    }

    public void operationDeposit(double value) {
        addStatementEntry("DEPOSIT", value);
    }

    public void operationTransferTEF(Account destinationAccount, double value) {
        Date now = new Date();
        addStatementEntry(now, String.format("TRANSFER TO BRAND %s ACCOUNT %s", destinationAccount.getBrand(), destinationAccount.getName()), -value);
        destinationAccount.addStatementEntry(now, String.format("TRANSFER FROM BRAND %s ACCOUNT %s", getBrand(), getName()), value);
    }

    public void addStatementEntry(String description, double value) {

        this.ballance += value;

        getStatement().addEntry(description, value);

        try {
            if (this.repository != null) {
                this.repository.save();
            }
        } catch (Throwable t) {
            t.printStackTrace();
            // NOTHING
        }

    }

    public void addStatementEntry(Date date, String description, double value) {

        this.ballance += value;

        getStatement().addEntry(date, description, value);

        try {
            if (this.repository != null) {
                this.repository.save();
            }
        } catch (Throwable t) {
            // NOTHING
        }

    }
}
