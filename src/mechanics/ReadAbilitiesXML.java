package mechanics;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadAbilitiesXML {
	
	public static int getCooldown(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='" + id + "']/cooldown/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

		return (value==null) ? -1 : Integer.parseInt(value);
		}
	public static String getTargetClick(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='" + id + "']/targetClick/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	
		return value;
	}
	public static boolean ignoresInvul(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/ignoresInvulnerability/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value.equalsIgnoreCase("yes")) ? true : false;
	}
	public static boolean destroyDD(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/destroyDD/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value.equalsIgnoreCase("yes")) ? true : false;
	}
	public static int stunDuration(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/stunDuration/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	public static int permanentDamageIncrease(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/permanentDamageIncrease/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	
	public static int becomeInvulnerable(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/becomeInvulnerable/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	public static int damageIncreasePerUse(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/damageIncreasePerUse/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	
	private static int damageNumber(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/damage/number/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	private static int damageDuration(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/damage/duration/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	
	public static int[] damage(int id) {
		int number = damageNumber(id);
		int duration = damageDuration(id);
		int[] arr = new int[2];
		arr[0] = number;
		arr[1] = duration;
		return arr;
	}
	
	private static int removeNatureNumber(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/removeNature/number/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	private static int removeNatureDuration(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/removeNature/duration/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	
	public static int[] removeNature(int id) {
		int number = removeNatureNumber(id);
		int duration = removeNatureDuration(id);
		int[] arr = new int[2];
		arr[0] = number;
		arr[1] = duration;
		return arr;
	}
	private static int gainNatureNumber(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/gainNature/number/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	private static int gainNatureDuration(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/gainNature/duration/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	
	public static int[] gainNature(int id) {
		int number = gainNatureNumber(id);
		int duration = gainNatureDuration(id);
		int[] arr = new int[2];
		arr[0] = number;
		arr[1] = duration;
		return arr;
	}
	
	private static int gainDDNumber(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/gainDD/number/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	private static int gainDDDuration(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/gainDD/duration/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	public static int[] gainDD(int id) {
		int number = gainDDNumber(id);
		int duration = gainDDDuration(id);
		int[] arr = new int[2];
		arr[0] = number;
		arr[1] = duration;
		return arr;
	}
	
	private static int gainDRNumber(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/gainDR/number/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	private static int gainDRDuration(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/gainDR/duration/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	public static int[] gainDR(int id) {
		int number = gainDRNumber(id);
		int duration = gainDRDuration(id);
		int[] arr = new int[2];
		arr[0] = number;
		arr[1] = duration;
		return arr;
	}
	
	private static int gainHPNumber(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/gainHP/number/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	private static int gainHPDuration(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/gainHP/duration/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	public static int[] gainHP(int id) {
		int number = gainHPNumber(id);
		int duration = gainHPDuration(id);
		int[] arr = new int[2];
		arr[0] = number;
		arr[1] = duration;
		return arr;
	}
	
	
	private static int moreDamagePerHPLostDamage(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/moreDamagePerHPLost/extraDamage/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	private static int moreDamagePerHPLostHP(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/moreDamagePerHPLost/hpLost/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	public static int[] moreDamagePerHPLost(int id) {
		int number = moreDamagePerHPLostDamage(id);
		int duration = moreDamagePerHPLostHP(id);
		int[] arr = new int[2];
		arr[0] = number;
		arr[1] = duration;
		return arr;
	}
	
	private static int moreDamageEnemyHPLostDamage(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/moreDamageEnemyHPLost/extraDamage/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	private static int moreDamageEnemyHPLostHP(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/moreDamageEnemyHPLost/hpLost/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	public static int[] moreDamageEnemyHPLost(int id) {
		int number = moreDamageEnemyHPLostDamage(id);
		int duration = moreDamageEnemyHPLostHP(id);
		int[] arr = new int[2];
		arr[0] = number;
		arr[1] = duration;
		return arr;
	}
	
	private static int temporaryDamageIncreaseDamage(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/temporaryDamageIncrease/extraDamage/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	private static int temporaryDamageIncreaseDuration(int id) {
		Document document = readDocument("abilities.xml");
		document.getDocumentElement().normalize();
		
		XPath xpath = XPathFactory.newInstance().newXPath();
		String cooldown = "//ability[@abilityID='"+id+"']/temporaryDamageIncrease/duration/text()";
		String value = null;
		
		try {
			value = (String) xpath.evaluate(cooldown, document, XPathConstants.STRING);

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return (value==null) ? -1 : Integer.parseInt(value);
	}
	public static int[] temporaryDamageIncrease(int id) {
		int number = temporaryDamageIncreaseDamage(id);
		int duration = temporaryDamageIncreaseDuration(id);
		int[] arr = new int[2];
		arr[0] = number;
		arr[1] = duration;
		return arr;
	}
	
	public static Document readDocument(String input) {
		// create a new DocumentBuilderFactory
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      Document doc=null;
	      try {
	         // use the factory to create a documentbuilder
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         doc = builder.parse(input);
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      }
	      return doc;
	}
	
	public static final Document readDocument(InputStream input) {
		// create a new DocumentBuilderFactory
	      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	      Document doc=null;
	      try {
	         // use the factory to create a documentbuilder
	         DocumentBuilder builder = factory.newDocumentBuilder();
	         doc = builder.parse(input);
	      } catch (Exception ex) {
	         ex.printStackTrace();
	      }
	      return doc;
	}

}