package org.chinaxlt.classTest;

import lombok.Data;

import java.util.List;

@Data
public class Bean {

    private String name;

    private Integer age;

    private Boolean isDead;

    private List<Bean> beans;

    public Bean() {
    }

    public Bean(String name, Integer age, Boolean isDead) {
        this.name = name;
        this.age = age;
        this.isDead = isDead;
    }


}
