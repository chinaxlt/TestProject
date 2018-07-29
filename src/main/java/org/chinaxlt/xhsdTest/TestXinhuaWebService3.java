package org.chinaxlt.xhsdTest;

import com.google.common.hash.Hashing;
import org.apache.axis.Constants;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.rpc.ParameterMode;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class TestXinhuaWebService3 {

    private static final String khid = "1101000000";
    private static final String keyid = "113884B69EC848AA3FFB7EE75AF18D42";
    private static final String endPoint = "http://xhapi.zxhsd.com/services/xinhuawebservice?wsdl";

    private static final String BOOK_TAG = "图书商品";
    private static final String FILE_ENCODING = "GBK";

    public static void main(String[] args) throws Exception {

        SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
        Date now = new Date();

        Service service = new Service();
        Call call = (Call) service.createCall();
        call.setTargetEndpointAddress(new URL(endPoint));
        //call.setOperationName("sv_service");
        call.setOperationName("sv_serviceToByte");
        call.addParameter("appMethod", Constants.XSD_STRING, ParameterMode.IN);
        call.addParameter("khid", Constants.XSD_STRING, ParameterMode.IN);
        call.addParameter("md5_value", Constants.XSD_STRING, ParameterMode.IN);
        call.addParameter("par_type", Constants.XSD_STRING, ParameterMode.IN);
        //call.addParameter("par_value", XMLType.XSD_STRING, ParameterMode.IN);
        call.addParameter("par_value", Constants.XSD_STRING, ParameterMode.IN);

        call.setReturnType(Constants.XSD_BASE64);
        //call.setReturnType(Constants.XSD_STRING);
        // 执行方法并带上参数
        String temp = khid + keyid + sf.format(now);
        String md5 = Hashing.md5().hashString(temp, Charset.forName("UTF-8")).toString();
        String par_value = "<params><spbss>\n" +
                "<spbs>2092173</spbs>\n" +
                "<spbs>3137877</spbs>\n" +
                "</spbss></params>";

        byte[] data = (byte[]) (call.invoke(new Object[]{"productInfoBySpbs", khid, md5, "xml", par_value}));

        if (getFileTypeByStream(data)) {
            // 返回的文件为zip文件，包含一个文件夹（images,用于存放书目的图片），一个xml文件
            System.out.println("=====success=====");
//            FileOutputStream fos = new FileOutputStream("/Users/xianglingtao/Documents/MyWork/新华书店/download" + new Date().getTime() + ".zip");
//            fos.write(data);
//            fos.flush();
//            fos.close();
            readZipFile(data);
            System.out.println("=====success=====");
        } else {
            // 直接抛出错误信息
            System.out.println("=====failed=====");
            System.out.println(new String(data));
            System.out.println("=====failed=====");
        }

//        String zipfile_dir = "/Users/xianglingtao/Documents/MyWork/新华书店/test2.xml.zip";
//        try {
//            readZipFile(zipfile_dir);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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

    public static void readZipFile(byte[] data) {
        File file = new File("/Users/xianglingtao/Documents/MyWork/新华书店/" + new Date().getTime());
        try {
            OutputStream output = new FileOutputStream(file);
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
            bufferedOutput.write(data);
            // 创建inputStream
            InputStream in = new ByteArrayInputStream(data);
            // 创建zip相关
            ZipFile zf = new ZipFile(file, Charset.forName(FILE_ENCODING));
            ZipInputStream zin = new ZipInputStream(in, Charset.forName(FILE_ENCODING));
            ZipEntry ze;
            while ((ze = zin.getNextEntry()) != null) {
                String name = ze.getName();
                Long size = ze.getSize();
                System.out.println("书目信息下载解析:file : " + name + " - " + size + " bytes");
                if (ze.toString().endsWith(".xml")) {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document document = builder.parse(zf.getInputStream(ze));
                    // 获取图书商品标签
                    NodeList nodeList = document.getElementsByTagName(BOOK_TAG);
                    for (int i = 0; i < nodeList.getLength(); i++) {
                        Node node = nodeList.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;
                            System.out.print("供货商商品ID=" + getTagValueByName(element, "供货商商品ID"));
                            System.out.println("书名=" + getTagValueByName(element, "书名"));
                        }
                    }
                }
                zin.closeEntry();
            }
        } catch (Exception e) {
            System.out.println("=====解析出错:" + e.getMessage());
        } finally {
//            file.delete();
        }
    }

    private static String getTagValueByName(Element element, String tagName) {
        NodeList authorList = element.getElementsByTagName(tagName);
        Integer length = authorList.getLength();
        if(length.equals(0)) {
            return null;
        }
        String data = "";
        for (int j = 0; j < length; j++) {
            data = data + authorList.item(j).getTextContent() + "/";
        }
        data = data.substring(0, data.length() - 1);
        return data;
    }
}
