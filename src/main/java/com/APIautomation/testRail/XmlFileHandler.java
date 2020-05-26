package com.APIautomation.testRail;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlFileHandler {
	
	private static String file_path = "./src/main/java/com/APIautomation/testRail/config.xml";
	private static DocumentBuilder dBuilder;
	
	public static String getXmlXpathValue(String xpath) {
		String xpath_value = null;
		File xmlFile = new File(file_path);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			XPath xPath = XPathFactory.newInstance().newXPath();
			xpath_value = xPath.compile(xpath).evaluate(doc);
			
		} catch (ParserConfigurationException e) {
			System.out.println("Unable to build the new document.");
			e.printStackTrace();
		}
		catch(SAXException | IOException e) {
			System.out.println("Facing issue in xml parsing.");
			e.printStackTrace();
		}
		catch (XPathExpressionException e) {
			System.out.println("Invalid xpath expression in xml parser document.");
			e.printStackTrace();
		}
		
		return xpath_value;
	}
	
}
