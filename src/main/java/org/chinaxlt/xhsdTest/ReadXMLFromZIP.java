package org.chinaxlt.xhsdTest;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


public class ReadXMLFromZIP {

    private static final String BOOK_TAG = "图书商品";
    private static final String FILE_ENCODING = "GBK";

    public static void main(String[] args) throws IOException {
        //readXMLInZipTest(new File("/Users/xianglingtao/Documents/MyWork/新华书店/图书商品.xml.zip"));
        readXmlFromZip();
    }

    public static void readXmlFromZip() {
//        File file = new File("/Users/xianglingtao/Documents/MyWork/新华书店/xlt-" + new Date().getTime()+ ".zip") ;
        File file = new File("/Users/xianglingtao/Documents/MyWork/新华书店/xlt-1533176343461.zip");
        try {
            // 创建inputStream
            InputStream in = new FileInputStream(file);
            // 创建zip相关
            ZipFile zf = new ZipFile(file, Charset.forName(FILE_ENCODING));
            ZipInputStream zin = new ZipInputStream(in, Charset.forName(FILE_ENCODING));
            ZipEntry ze;
            while ((ze = zin.getNextEntry()) != null) {
                String name = ze.getName();
                Long size = ze.getSize();
                System.out.println("书目信息下载解析:file:" + name + " size:" + size + " bytes ");
                if (name.endsWith(".xml")) {
                    System.out.println("这个xml不用解析");
                }
                if (name.endsWith(".jpg")) {
                    name = name.replace("images/","");
                    System.out.println("这个" + name + "需要上传⏫");
                    File img = new File("/Users/xianglingtao/Documents/MyWork/新华书店/images/" + name);
                    InputStream inputStream = zf.getInputStream(ze);
                    OutputStream outputStream = new FileOutputStream(img);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                    int len = -1;
                    byte[] b = new byte[1024];
                    while ((len = bufferedInputStream.read(b)) != -1) {
                        bufferedOutputStream.write(b, 0, len);
                    }
                    bufferedInputStream.close();
                    bufferedOutputStream.close();
                }
                zin.closeEntry();
            }
        } catch (Exception e) {
            System.out.println("=====解析出错:" + e.getMessage());
        } finally {
//            file.delete();
        }
    }

}