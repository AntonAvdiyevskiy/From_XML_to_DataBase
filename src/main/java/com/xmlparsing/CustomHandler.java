package com.xmlparsing;


import com.model.Periodical;
import com.model.PeriodicalType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class CustomHandler extends DefaultHandler {
    //List to hold Employees object
    private List<Periodical> perList = null;
    private Periodical periodical = null;
    private PeriodicalType periodicalType = null;


    //getter method for employee list
    public List<Periodical> getPerList() {
        return perList;
    }

    boolean bTitle = false;
    boolean bType = false;
    boolean bMonthly = false;
    boolean bColor = false;
    boolean bPages = false;
    boolean bIndex = false;
    boolean bName = false;
    boolean bGlossy = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("periodical")) {
            //create a new Employee and put it in Map
            String id = attributes.getValue("id");
            //initialize Employee object and set id attribute
            periodical = new Periodical();
            periodical.setId(Integer.parseInt(id));
            //initialize list
            if (perList == null)
                perList = new ArrayList<Periodical>();
        } else if (qName.equalsIgnoreCase("title")) {
            //set boolean values for fields, will be used in setting  variables
            bTitle = true;
        } else if (qName.equalsIgnoreCase("type")) {
            String id = attributes.getValue("id_type");
            periodicalType = new PeriodicalType();
            periodicalType.setId(Integer.parseInt(id));

        } else if (qName.equalsIgnoreCase("name")) {
            bName = true;
        } else if (qName.equalsIgnoreCase("glossy")) {
            bGlossy = true;
        }else if (qName.equalsIgnoreCase("monthly")) {
            bMonthly = true;
        } else if (qName.equalsIgnoreCase("color")) {
            bColor = true;
        }else if (qName.equalsIgnoreCase("pages")) {
            bPages = true;
        } else if (qName.equalsIgnoreCase("index")) {
            bIndex = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("periodical")) {
            //add Employee object to list
            periodical.setType(periodicalType);
            perList.add(periodical);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bTitle) {
            //age element, set Employee age
            periodical.setTitle(new String(ch, start, length));
            bTitle = false;
        } else if (bName) {
            periodicalType.setName(new String(ch, start, length));
            bName = false;
        } else if (bGlossy) {
            periodicalType.setGlossy(Boolean.parseBoolean(new String(ch, start, length)));
            bGlossy = false;
        } else if (bMonthly) {
            periodical.setMonthly(Boolean.parseBoolean(new String(ch, start, length)));
            bMonthly = false;
        }else if (bColor) {
            periodical.setColor(new String(ch, start, length));
            bColor = false;
        } else if (bPages) {
            periodical.setPages(Integer.parseInt(new String(ch, start, length)));
            bPages = false;
        }else if (bIndex) {
            periodical.setIndex(Integer.parseInt(new String(ch, start, length)));
            bIndex = false;
        }
    }

}
