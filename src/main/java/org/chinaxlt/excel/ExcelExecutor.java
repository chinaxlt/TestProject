package org.chinaxlt.excel;

public class ExcelExecutor {

    public static void main(String[] args) throws Exception {
        String path = "/Users/xianglingtao/Documents/MyWork/网上商城生成库所有表及表字段信息-初步筛选数据汇总-对外提供.xlsx";
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.readOneSheet(path);
    }
}
