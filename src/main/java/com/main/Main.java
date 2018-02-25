package com.main;


import com.model.Paper;
import com.model.Periodical;
import com.model.PeriodicalType;
import com.service.PeriodicalDaoImpl;
import com.service.PeriodicalTypeDaoImpl;
import com.xmlparsing.Parser;
import com.xmlparsing.ParserSelector;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static final String XSD_PATH = "src/Periodical.xsd";
    public static final String XML_PATH = "src/periodical.xml";
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {

        Set<PeriodicalType> types = new HashSet<>();
        PeriodicalTypeDaoImpl periodicalTypeDao = new PeriodicalTypeDaoImpl();

        ParserSelector parserSelector = new ParserSelector();
        XMLWork xmlWork = new XMLWork();
        System.out.println("choose parser you want parse xml enter 1 -DomParser,2-SaxParser,3-StAX Parser");
        boolean rightChoice = true;
        while (rightChoice) {
            int choice = sc.nextInt();
            Parser parser = parserSelector.getParser(choice);
            if (parser != null) {
                System.out.println("Collection of periodicals sorted by pages");
                List<Periodical> periodicals = parser.parseDoc();
                xmlWork.validateXMLSchema(XSD_PATH, XML_PATH);
                xmlWork.generateHTML();
                xmlWork.generateXMLWithChangedRoot();
                rightChoice = false;
                PeriodicalDaoImpl periodicalDao = new PeriodicalDaoImpl();
                for (Periodical periodical : periodicals) {

                    types.add(periodical.getType());

                }
                for (PeriodicalType periodicalType : types) {
                    periodicalTypeDao.insert(periodicalType);

                }
                for (Periodical periodical : periodicals) {
                    periodicalDao.insert(periodical);
                }
                log.info("Data from our xml saved to database");


            }


        }
    }
    public void justTest(Paper paper){
        try{
            System.out.println(paper.getPeriodicals());
        }catch (RuntimeException e){
            e.printStackTrace();
        }

    }
}
