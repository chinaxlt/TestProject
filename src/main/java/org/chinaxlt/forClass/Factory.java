package org.chinaxlt.forClass;

public class Factory {
    //getClass 产生Sample 一般可使用动态类装载装入类。
    public static Sample creator(int which) {
        if (which == 1) {
            return new SampleA();
        } else {
            return new SampleB();
        }
    }

    public Sample getInstance(int which) {
        return creator(which);
    }
}
