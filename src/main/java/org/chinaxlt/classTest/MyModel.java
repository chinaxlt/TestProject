package org.chinaxlt.classTest;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MyModel {

    private List<String> dataList;

    private Map<String,String> dataMap;

}
