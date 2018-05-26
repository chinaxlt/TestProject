package org.chinaxlt.classTest;

public class Sample {
    private int a;
    private int b;
    private String value = "20";


    public void Sample(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void show() {
        System.out.println("a:" + this.a + ";b:" + this.b);
    }

    public String getValue() {
        return value;
    }
}
