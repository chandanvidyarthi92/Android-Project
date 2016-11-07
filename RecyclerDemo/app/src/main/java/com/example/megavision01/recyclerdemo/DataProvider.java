package com.example.megavision01.recyclerdemo;

/**
 * Created by MegaVision01 on 10/20/2016.
 */
public class DataProvider {
    public DataProvider(int img_res,String fname,String dname)
    {
        this.setImg_res(img_res);
        this.setFname(fname);
        this.setDname(dname);
    }
    private int img_res;
    private String fname,dname;

    public int getImg_res() {
        return img_res;
    }

    public void setImg_res(int img_res) {
        this.img_res = img_res;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}
