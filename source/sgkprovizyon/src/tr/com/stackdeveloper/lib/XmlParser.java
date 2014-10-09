package tr.com.stackdeveloper.lib;

import java.io.IOException;
import java.io.StringReader;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlParser 
{

	public org.w3c.dom.Document parsedDocument;

	public org.w3c.dom.Document getDocument(String xml) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xml));
		org.w3c.dom.Document doc;
		try {
			doc = db.parse(is);
			this.parsedDocument = doc;
			return doc;
		} catch (SAXException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public NodeList getNodes(String nodeName) {
		NodeList nodes = this.parsedDocument.getElementsByTagName(nodeName);
		return nodes;
	}

	public static XmlParser create() {
		return new XmlParser();
	}

}
