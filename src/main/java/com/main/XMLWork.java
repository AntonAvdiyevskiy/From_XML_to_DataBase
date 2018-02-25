package com.main;

import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLWork {
    private static final Logger log = Logger.getLogger(XMLWork.class);
    public  void generateHTML() {
        try {

            TransformerFactory tFactory = TransformerFactory.newInstance();

            Transformer transformer =
                    tFactory.newTransformer
                            (new javax.xml.transform.stream.StreamSource
                                    ("src/periodical.xsl"));

            transformer.transform
                    (new javax.xml.transform.stream.StreamSource
                                    ("src/periodical.xml"),
                            new javax.xml.transform.stream.StreamResult
                                    (new FileOutputStream("src/periodical.html")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("periodical.html has been generated");

    }

    public  boolean validateXMLSchema(String xsdPath, String xmlPath) {

        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
            log.info("validation successful");
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        } catch (SAXException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
        return true;
    }

    public  void generateXMLWithChangedRoot() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.parse("src/periodical.xml");
            // get root element
            Element element = doc.getDocumentElement();
            // Create an element with the new name
            Element newRootElementName = doc.createElement("newRootElementName");

            // Copy the attributes to the new element
            NamedNodeMap attrs = element.getAttributes();
            for (int i = 0; i < attrs.getLength(); i++) {
                Attr attr2 = (Attr) doc.importNode(attrs.item(i), true);
                newRootElementName.getAttributes().setNamedItem(attr2);
            }

           // Move all the children
            while (element.hasChildNodes()) {
                newRootElementName.appendChild(element.getFirstChild());
            }

            // Replace the old node with the new node
            element.getParentNode().replaceChild(newRootElementName, element);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/NewXml.xml"));
            transformer.transform(source, result);
            log.info("NewXml.xml file with new root element has been generated ");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
