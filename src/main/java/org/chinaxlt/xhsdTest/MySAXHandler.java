package org.chinaxlt.xhsdTest;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class MySAXHandler extends DefaultHandler {
    public void startDocument() throws SAXException {
        System.out.println("文档开始打印了");
    }

    public void endDocument() throws SAXException {
        System.out.println("文档打印结束了");
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        System.out.println(qName + ":" + attributes.getValue(qName));
    }
}