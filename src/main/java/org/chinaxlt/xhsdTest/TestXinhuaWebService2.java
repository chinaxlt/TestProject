package org.chinaxlt.xhsdTest;

import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import org.apache.axis.Constants;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.lang3.StringUtils;
import org.chinaxlt.util.MD5Utils;

import javax.xml.rpc.ParameterMode;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestXinhuaWebService2 {

    private static final String khid = "1101000000";
    private static final String keyid = "113884B69EC848AA3FFB7EE75AF18D42";
    private static final String endPoint = "http://xhapi.zxhsd.com/services/xinhuawebservice?wsdl";

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
        Date now = new Date();
        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTimeout(1000 * 60 * 15);
        URL url = new URL(endPoint);
        call.setTargetEndpointAddress(url);
        call.setOperationName("sv_service");
        call.addParameter("appMethod", Constants.XSD_STRING, ParameterMode.IN);
        call.addParameter("khid", Constants.XSD_STRING, ParameterMode.IN);
        call.addParameter("md5_value", Constants.XSD_STRING, ParameterMode.IN);
        call.addParameter("par_type", Constants.XSD_STRING, ParameterMode.IN);
        call.addParameter("par_value", Constants.XSD_STRING, ParameterMode.IN);
        call.setReturnType(Constants.XSD_STRING);
        //执行方法并带上参数
        System.out.println("=========" + sf.format(now));
        String temp = khid + keyid + sf.format(now);
//        String md5 = Hashing.md5().hashString(temp, Charset.forName("UTF-8")).toString();
        String md5 = MD5Utils.EncoderByMd5(temp);

        String par_value = "<params>\n" +
                "<startDate>2018-07-11</startDate>\n" +
                "<endDate>2018-07-12</endDate>\n" +
                "</params>";

        String xml = (String) (call.invoke(new Object[]{"productInfo01Yxzpz", khid, md5, "xml", par_value}));
        System.out.println("=========");
        System.out.println(xml);
        System.out.println("=========");

        List<String> tagList = formateXML(xml);
        System.out.println("data size:" + tagList.size());

        call.setOperationName("sv_serviceToByte");
        call.setReturnType(Constants.XSD_BASE64);

        for (Integer i = 0; i < (tagList.size() / 10 + 1); i++) {

            String par_value_head = "<params><spbss>\n";
            String par_value_button = "</spbss></params>";
            String par_value_full = "";
            par_value_full += par_value_head;
            for (Integer j = 0; j < 10; j++) {
                Integer tag = i * 10 + j;
                if (tag <= tagList.size() - 1 && StringUtils.isNotBlank(tagList.get(tag))) {
                    par_value_full += tagList.get(tag) + "\n";
                }
            }
            par_value_full += par_value_button;
            System.out.println("=========" + i);
            System.out.println(par_value_full);
            System.out.println("=========" + i);

//            byte[] data = (byte[]) (call.invoke(new Object[]{"productInfoBySpbs", khid, md5, "xml", par_value_full}));
//
//            if (getFileTypeByStream(data)) {
//                // 返回的文件为zip文件，包含一个文件夹（images,用于存放书目的图片），一个xml文件
//                System.out.println("=====success=====");
//                TestXinhuaWebService3.readZipFile(data);
//                System.out.println("=====success=====");
//            } else {
//                // 直接抛出错误信息
//                System.out.println("=====failed=====");
//                System.out.println(new String(data));
//                System.out.println("=====failed=====");
//            }
        }


    }

    // 判断是否是zip文件
    private static boolean getFileTypeByStream(byte[] b) {
        String fileTypeHex = String.valueOf(getFileHexString(b));
        if (fileTypeHex.toUpperCase().startsWith("504B0304")) {
            return true;
        }
        return false;
    }

    private static String getFileHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b == null || b.length <= 0) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private static List<String> formateXML(String xml) {
//        xml = xml.replace("<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n", "");
//        xml = xml.replace("yxzpz", "params");
        List<String> dataList = Lists.newArrayList(xml.split("\n"));
        List<String> tagList = Lists.newArrayList();
        dataList.forEach(data -> {
            if (data.startsWith("<spbs>")) {
                tagList.add(data);
            }
        });
        return tagList;
    }

}
