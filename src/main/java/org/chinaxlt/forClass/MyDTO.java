package org.chinaxlt.forClass;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MyDTO {

    private List<String> dataList;

    private Map<String,String> dataMap;

}
