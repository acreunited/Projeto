package main;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class How {

	public static void main(String[] args) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			//document = builder.parse(this.getClass().getResourceAsStream("/resources/skeleton.xml"));

			Document document = documentBuilder.parse("abilities.xml");
			DOMSource source = new DOMSource(document);
			
			Element root = document.getDocumentElement();
			Element newAbility = document.createElement("ability");
			((Element) newAbility).setAttribute("abilityID", "fgggg");
			
			Element temporaryDamage = document.createElement("temporaryDamageIncrease");
			Element tempDamage = document.createElement("extraDamage");
			tempDamage.appendChild(document.createTextNode("ffs"));
			temporaryDamage.appendChild(tempDamage);
			Element tempDuration = document.createElement("duration");
			tempDuration.appendChild(document.createTextNode("ffs2"));
			temporaryDamage.appendChild(tempDuration);
			newAbility.appendChild(temporaryDamage);
			
			root.appendChild(newAbility);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			StreamResult result = new StreamResult("abilities.xml");
			transformer.transform(source, result);

			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
