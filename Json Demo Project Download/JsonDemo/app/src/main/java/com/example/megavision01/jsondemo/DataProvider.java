package com.example.megavision01.jsondemo;

/**
 * Created by Intel on 09-11-2016.
 */
public class DataProvider
{
    private String id;
    private String name;

    public DataProvider(String id,String name)
    {
        this.id=id;
        this.name=name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
