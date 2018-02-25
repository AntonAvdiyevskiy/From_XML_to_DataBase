package com.xmlparsing;


import com.comparators.SortedByAmountOfPages;
import com.model.Periodical;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class SaxParser extends DefaultHandler implements Parser {
    @Override
    public  List<Periodical> parseDoc() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        List<Periodical> perList = null;
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            CustomHandler customHandler = new CustomHandler();
            saxParser.parse(new File("src/periodical.xml"), customHandler);
            perList = customHandler.getPerList();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(perList,new SortedByAmountOfPages());
        for (Periodical periodical : perList) {
            System.out.println(periodical);
        }
        return perList;
    }
}
