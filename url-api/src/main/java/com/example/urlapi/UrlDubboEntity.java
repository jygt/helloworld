package com.example.urlapi;



public class UrlDubboEntity {

    private String id;

    private Integer f_cat;

    private String FName;

    private String f_url;
    private String f_acc;
    private String f_passwd;
    private String f_crt_tm;
    private String f_mod_tm;
    private String f_desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public void setF_mod_tm(String f_mod_tm) {
        this.f_mod_tm = f_mod_tm;
    }



    public void setF_desc(String f_desc) {
        this.f_desc = f_desc;
    }

    public void setF_crt_tm(String f_crt_tm) {
        this.f_crt_tm = f_crt_tm;
    }



    public String getF_mod_tm() {
        return f_mod_tm;
    }

    public String getF_desc() {
        return f_desc;
    }

    public String getF_crt_tm() {
        return f_crt_tm;
    }



    public Integer getF_cat() {
        return f_cat;
    }

    public String getF_acc() {
        return f_acc;
    }

    public String getF_passwd() {
        return f_passwd;
    }

    public String getF_url() {
        return f_url;
    }

    public void setF_acc(String f_acc) {
        this.f_acc = f_acc;
    }

    public void setF_cat(Integer f_cat) {
        this.f_cat = f_cat;
    }

    public void setF_passwd(String f_passwd) {
        this.f_passwd = f_passwd;
    }

    public void setF_url(String f_url) {
        this.f_url = f_url;
    }

}
