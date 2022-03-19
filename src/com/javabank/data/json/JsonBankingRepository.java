package com.javabank.data.json;

import com.google.gson.Gson;
import com.javabank.gson.GsonSingleton;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;

public class JsonBankingRepository<T> {

    protected String name = "data";
    protected String filepath = "data/data.json";
    protected T data = null;
    protected Type type = null;

    public static boolean exists(String name) {
        String filepath = String.format("data/%s.json", name);
        File file = new File(filepath);
        return file.exists();
    }

    public JsonBankingRepository(String name, T data, Type type) {
        this.name = name;
        this.filepath = String.format("data/%s.json", this.name);
        this.type = type;
        this.data = data;
    }

    public JsonBankingRepository(String name, Type type) {
        this(name, null, type);
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        this.filepath = String.format("data/%s.json", this.name);
    }

    public String getFilepath() {
        return this.filepath;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Gson getGson() {
        return GsonSingleton.getInstance().getGson();
    }

    public String toString() {
        return getGson().toJson(this.data);
    }

    public void fromString(String s) {
        this.data = (T) getGson().fromJson(s, this.type);
    }

    public void mkdirs() {
        File file = new File("data");
        file.mkdirs();
    }

    public void save() throws IOException {
        mkdirs();
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.filepath));
        writer.write(toString());
        writer.close();
    }

    public void load() throws IOException {
        mkdirs();
        File file = new File(this.filepath);
        if (!file.exists()) {
            save();
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(this.filepath));
            StringWriter sw = new StringWriter();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sw.write(String.format("%s\n", line));
            }
            String s = sw.toString();
            fromString(s);
        }
    }

    public void initialize() throws IOException {
        load();
    }

}
