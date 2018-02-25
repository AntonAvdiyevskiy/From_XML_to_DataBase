package com.xmlparsing;


import com.comparators.SortedByAmountOfPages;
import com.model.Periodical;
import com.model.PeriodicalType;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class StAXParser implements Parser {
    @Override
    public List<Periodical> parseDoc() {
        Periodical periodical = null;
        List<Periodical> periodicals = new ArrayList<>();
        PeriodicalType periodicalType = null;
        boolean bTitle = false;
        boolean bType = false;
        boolean bMonthly = false;
        boolean bColor = false;
        boolean bPages = false;
        boolean bIndex = false;
        boolean bName = false;
        boolean bGlossy = false;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader =
                null;
        try {
            eventReader = factory.createXMLEventReader(
                    new FileReader("src/periodical.xml"));
        } catch (XMLStreamException e1) {
            e1.printStackTrace();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        while (eventReader.hasNext()) {
            XMLEvent event = null;
            try {
                event = eventReader.nextEvent();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }


            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();

                    if (qName.equalsIgnoreCase("periodical")) {
                        periodical = new Periodical();
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        String id = attributes.next().getValue();
                        periodical.setId(Integer.parseInt(id));
                    } else if (qName.equalsIgnoreCase("title")) {
                        bTitle = true;
                    } else if (qName.equalsIgnoreCase("type")) {
                        periodicalType = new PeriodicalType();
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        int id = Integer.parseInt(attributes.next().getValue());
                        periodicalType.setId(id);
                    } else if (qName.equalsIgnoreCase("name")) {
                        bName = true;
                    } else if (qName.equalsIgnoreCase("glossy")) {
                        bGlossy = true;
                    } else if (qName.equalsIgnoreCase("monthly")) {
                        bMonthly = true;
                    } else if (qName.equalsIgnoreCase("color")) {
                        bColor = true;
                    } else if (qName.equalsIgnoreCase("pages")) {
                        bPages = true;
                    } else if (qName.equalsIgnoreCase("index")) {
                        bIndex = true;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    if (bTitle) {
                        periodical.setTitle(characters.getData());
                        bTitle = false;
                    }
                    if (bName) {
                        periodicalType.setName(characters.getData());
                        bName = false;
                    }
                    if (bGlossy) {
                        periodicalType.setGlossy(Boolean.parseBoolean(characters.getData()));
                        bGlossy = false;
                    }
                    if (bMonthly) {
                        periodical.setMonthly(Boolean.parseBoolean(characters.getData()));
                        bMonthly = false;
                    }
                    if (bColor) {
                        periodical.setColor(characters.getData());
                        bColor = false;
                    }
                    if (bPages) {
                        periodical.setPages(Integer.parseInt(characters.getData()));
                        bPages = false;
                    }
                    if (bIndex) {
                        periodical.setIndex(Integer.parseInt(characters.getData()));
                        bIndex = false;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equalsIgnoreCase("periodical")) {
                        periodical.setType(periodicalType);
                        periodicals.add(periodical);
                    }
                    break;
            }

        }
        Collections.sort(periodicals,new SortedByAmountOfPages());
        for (Periodical obj : periodicals) {
            System.out.println(obj);
        }
        return periodicals;
    }
}
