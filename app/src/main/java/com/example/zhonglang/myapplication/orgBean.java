package com.example.zhonglang.myapplication;

import java.io.Serializable;

public class orgBean    implements Serializable {

    /**
     * id : FEEC9C88-EF86-41F0-A912-ADF555DF0D8A
     * name :  六车间4班
     * pId : 3047D324-25AE-4D64-924B-00F6C2C27F2B
     * open : false
     * isDeparment : true
     * check : null
     */

    private String id;
    private String name;
    private String pId;
    private boolean open;
    private boolean isDeparment;
    private Object check;

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

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isIsDeparment() {
        return isDeparment;
    }

    public void setIsDeparment(boolean isDeparment) {
        this.isDeparment = isDeparment;
    }

    public Object getCheck() {
        return check;
    }

    public void setCheck(Object check) {
        this.check = check;
    }
}
