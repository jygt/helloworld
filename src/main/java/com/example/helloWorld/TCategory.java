package com.example.helloWorld;

public class TCategory {
    private Integer f_id;
    private String f_name;
    private String f_crt_tm;
    private String f_mod_tm;
    private String f_desc;

    public Integer getF_id() {
        return f_id;
    }

    public String getF_crt_tm() {
        return f_crt_tm;
    }

    public String getF_desc() {
        return f_desc;
    }

    public String getF_mod_tm() {
        return f_mod_tm;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_crt_tm(String f_crt_tm) {
        this.f_crt_tm = f_crt_tm;
    }

    public void setF_desc(String f_desc) {
        this.f_desc = f_desc;
    }

    public void setF_id(Integer f_id) {
        this.f_id = f_id;
    }

    public void setF_mod_tm(String f_mod_tm) {
        this.f_mod_tm = f_mod_tm;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

}
