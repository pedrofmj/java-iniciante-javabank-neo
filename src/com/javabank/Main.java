package com.javabank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.javabank.gson.GsonSingleton;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

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
