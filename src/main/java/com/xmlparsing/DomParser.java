package com.xmlparsing;


import com.comparators.SortedByAmountOfPages;
import com.model.Periodical;
import com.model.PeriodicalType;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DomParser implements Parser {
    @Override
    public List<Periodical> parseDoc() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        int idType = 0;
        String nameOfType = null;
        Boolean isGlossy = true;
        List<Periodical> periodicalList = new ArrayList<Periodical>();
        try {
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File("src/periodical.xml"));
            NodeList periodicals = document.getElementsByTagName("periodical");
            Element element = document.getDocumentElement();

            for (int temp = 0; temp < periodicals.getLength(); temp++) {
                Node nNode = periodicals.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    int id = Integer.parseInt(eElement.getAttribute("id"));
                    String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                    NodeList type = document.getElementsByTagName("type");
                    for (int i = 0; i < periodicals.getLength(); i++) {
                        idType = Integer.parseInt(eElement.getElementsByTagName("type").item(0).getAttributes().getNamedItem("id_type").getNodeValue());
                        nameOfType = eElement.getElementsByTagName("name").item(0).getTextContent();
                        isGlossy = Boolean.parseBoolean(eElement.getElementsByTagName("glossy").item(0).
                                getTextContent());
                    }
                    boolean isMonthly = Boolean.parseBoolean(eElement.getElementsByTagName("monthly").item(0).
                            getTextContent());
                    String color = eElement.getElementsByTagName("color").item(0).getTextContent();
                    int pages = Integer.parseInt(eElement.getElementsByTagName("pages").item(0).getTextContent());
                    int index = Integer.parseInt(eElement.getElementsByTagName("index").item(0).getTextContent());
                    PeriodicalType periodicalType = new PeriodicalType(idType, nameOfType, isGlossy);
                    periodicalList.add(new Periodical(id, title, periodicalType, isMonthly, color, pages, index));
                }
            }
            Collections.sort(periodicalList, new SortedByAmountOfPages());
            for (Periodical periodical : periodicalList) {
                System.out.println(periodical);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return periodicalList;
    }

}
