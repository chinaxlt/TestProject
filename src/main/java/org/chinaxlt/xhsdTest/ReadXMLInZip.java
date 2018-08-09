package org.chinaxlt.xhsdTest;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ReadXMLInZip {

    public static void main(String[] args) throws IOException {
        //readXMLInZipTest(new File("/Users/xianglingtao/Documents/MyWork/新华书店/图书商品.xml.zip"));
        readXMLInZipTest();
    }

    private static void readXMLInZipTest(File thefile) {
        InputStream inputstream = null;
        try {
            ZipFile zip = new ZipFile(thefile);
            ZipEntry entry = zip.getEntry("图书商品.xml");
            inputstream = zip.getInputStream(entry);
        } catch (ZipException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputstream));
        InputSource inputSource = new InputSource(bufferedReader);
        DocumentBuilder builder = null;
        Document doc = null;

        DocumentBuilderFactory dom = DocumentBuilderFactory.newInstance();
        try {
            builder = dom.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        try {
            doc = builder.parse(inputSource);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (doc != null) {
            NodeList nodeList = doc.getElementsByTagName("title");
            String fileName = nodeList.item(0).getFirstChild().getNodeValue();
            System.out.println(fileName);
            File newdir = new File("/Users/xianglingtao/Documents/MyWork/新华书店/" + fileName);
            newdir.mkdir();
        } else {
            System.out.println("doc is null !!!");
        }
    }

    private static void readXMLInZipTest() throws IOException {
        String path = "/Users/xianglingtao/Documents/MyWork/新华书店/图书商品.xml.zip";
        ZipFile zf = new ZipFile(path);
        InputStream in = new BufferedInputStream(new FileInputStream(path));
        Charset gbk = Charset.forName("utf-8");
        ZipInputStream zin = new ZipInputStream(in, gbk);
        ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {
            if (ze.toString().endsWith(".xml")) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(zf.getInputStream(ze)));
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line.toString());
                }
                br.close();
            }
            System.out.println();
        }
        zin.closeEntry();
    }

}
