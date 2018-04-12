package org.chinaxlt.forClass;

public enum TypeEnum {

    TYPE_A("aaaaa"),
    TYPE_B("bbbbb"),
    TYPE_C("ccccc"),
    TYPE_D("ddddd"),
    TYPE_E("eeeee");

    private String status;

    TypeEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
