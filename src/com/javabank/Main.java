package com.javabank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.javabank.com.javabank.core.transaction.console.TransactionConsole;
import com.javabank.core.account.Account;
import com.javabank.data.json.JsonBankingRepository;
import com.javabank.gson.GsonSingleton;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        TransactionConsole.main(args);
    }

    public static void main_account(String[] args) {

        Account account1 = null;
        Account account2 = null;

        if (JsonBankingRepository.exists("account-0001-john-doe")) {
            JsonBankingRepository<Account> repository = new JsonBankingRepository<Account>("account-0001-john-doe", Account.class);
            account1 = repository.getData();
            account1.setRepository(repository);
        } else {
            account1 = new Account("john-doe", "John Doe", 100.00);
        }

        if (JsonBankingRepository.exists("account-0001-jane-doe")) {
            JsonBankingRepository<Account> repository = new JsonBankingRepository<Account>("account-0001-jane-doe", Account.class);
            account2 = repository.getData();
            account2.setRepository(repository);
        } else {
            account2 = new Account("jane-doe", "Jane Doe", 100.00);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        account1.addStatementEntry("Withdraw", -10.00);

    }

    public static void main_json_melhorado(String[] args) {

        Gson gson = GsonSingleton.getInstance().getGson();

        HashMap<String, String> map = new HashMap<>();

        map.put("chave1", "valor1");
        map.put("chave2", "valor2");
        map.put("chave3", "valor3");

        String str = gson.toJson(map);

        System.out.println(str);

    }

    public static void main_json(String[] args) {

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

        HashMap<String, String> map = new HashMap<>();

        map.put("chave1", "valor1");
        map.put("chave2", "valor2");
        map.put("chave3", "valor3");

        String str = gson.toJson(map);

        System.out.println(str);

    }

    public static void main_hello(String[] args) {

        System.out.println("hello");

    }
}
