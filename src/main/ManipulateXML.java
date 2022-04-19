package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ManipulateXML {
	
	
	
	public boolean writeXML(String abilityID, String name, String description, String taijutsu, String heart, String energy, 
			String spirit, String random, String cooldown, String targetClick, String damageNumber, String damageDuration,
			String nTimesUsed, String ignoresInvul, String stunDuration, String becomesInvul, String damageIncreasePerUse,
			String destroyDD, String permanentDamageIncrease, String removeNatureNumber, String removeNatureDuration,
			String gainNatureNumber, String gainNatureDuration, String gainHPNumber, String gainHPDuration, String gainDDNumber,
			String gainDDDuration, String gainDRNumber, String gainDRDuration, String moreDamagePerHPLostDamage, 
			String moreDamagePerHPLostHP, String moreDamagePerEnemyHPLostDamage, String moreDamagePerEnemyHPLostHP, 
			String temporaryDamageIncreaseDamage, String temporaryDamageIncreaseDuration) {
		
		
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			//document = builder.parse(this.getClass().getResourceAsStream("/resources/skeleton.xml"));

			Document document = documentBuilder.parse("D:\\GitHub\\Projeto\\abilities.xml");
			DOMSource source = new DOMSource(document);
			
			Element root = document.getDocumentElement();
			Element newAbility = document.createElement("ability");
			((Element) newAbility).setAttribute("abilityID", abilityID);
			
			Element abilityName = document.createElement("name");
			abilityName.appendChild(document.createTextNode(name));
			newAbility.appendChild(abilityName);
			
			Element descricao = document.createElement("description");
			descricao.appendChild(document.createTextNode(description));
			newAbility.appendChild(descricao);
			
			Element nature = document.createElement("nature");
			Element tai = document.createElement("taijutsu");
			tai.appendChild(document.createTextNode(taijutsu));
			nature.appendChild(tai);
			Element hear = document.createElement("heart");
			hear.appendChild(document.createTextNode(heart));
			nature.appendChild(hear);
			Element ener = document.createElement("energy");
			ener.appendChild(document.createTextNode(energy));
			nature.appendChild(ener);
			Element spir = document.createElement("spirit");
			spir.appendChild(document.createTextNode(spirit));
			nature.appendChild(spir);
			Element ran = document.createElement("random");
			ran.appendChild(document.createTextNode(random));
			nature.appendChild(ran);
			newAbility.appendChild(nature);
			
			Element cool = document.createElement("cooldown");
			cool.appendChild(document.createTextNode(cooldown));
			newAbility.appendChild(cool);
			
			Element clickT = document.createElement("targetClick");
			clickT.appendChild(document.createTextNode(targetClick));
			newAbility.appendChild(clickT);
			
			Element dmg = document.createElement("damage");
			Element dmgNumber = document.createElement("number");
			dmgNumber.appendChild(document.createTextNode(damageNumber));
			dmg.appendChild(dmgNumber);
			Element dmgDuration = document.createElement("duration");
			dmgDuration.appendChild(document.createTextNode(damageDuration));
			dmg.appendChild(dmgDuration);
			newAbility.appendChild(dmg);
			
			Element nTimes = document.createElement("nTimesUsed");
			nTimes.appendChild(document.createTextNode(nTimesUsed));
			newAbility.appendChild(nTimes);
			
			Element ignoresInv = document.createElement("ignoresInvulnerability");
			ignoresInv.appendChild(document.createTextNode(ignoresInvul));
			newAbility.appendChild(ignoresInv);
			
			Element stun = document.createElement("stunDuration");
			stun.appendChild(document.createTextNode(stunDuration));
			newAbility.appendChild(stun);
			
			Element invul = document.createElement("becomeInvulnerable");
			invul.appendChild(document.createTextNode(becomesInvul));
			newAbility.appendChild(invul);
			
			Element damageIncreaseUse = document.createElement("damageIncreasePerUse");
			damageIncreaseUse.appendChild(document.createTextNode(damageIncreasePerUse));
			newAbility.appendChild(damageIncreaseUse);
			
			Element ddDestroy = document.createElement("destroyDD");
			ddDestroy.appendChild(document.createTextNode(destroyDD));
			newAbility.appendChild(ddDestroy);
			
			Element permDamageInc = document.createElement("permanentDamageIncrease");
			permDamageInc.appendChild(document.createTextNode(permanentDamageIncrease));
			newAbility.appendChild(permDamageInc);
			
			Element removeEner = document.createElement("removeNature");
			Element removeEnerNumber = document.createElement("number");
			removeEnerNumber.appendChild(document.createTextNode(removeNatureNumber));
			removeEner.appendChild(removeEnerNumber);
			Element removeEnerDuration = document.createElement("duration");
			removeEnerDuration.appendChild(document.createTextNode(removeNatureDuration));
			removeEner.appendChild(removeEnerDuration);
			newAbility.appendChild(removeEner);
			
			Element gainEner = document.createElement("gainNature");
			Element gainEnerNumber = document.createElement("number");
			gainEnerNumber.appendChild(document.createTextNode(gainNatureNumber));
			gainEner.appendChild(gainEnerNumber);
			Element gainEnerDuration = document.createElement("duration");
			gainEnerDuration.appendChild(document.createTextNode(gainNatureDuration));
			gainEner.appendChild(gainEnerDuration);
			newAbility.appendChild(gainEner);
			
			Element dd = document.createElement("gainDD");
			Element ddNumberdd = document.createElement("number");
			ddNumberdd.appendChild(document.createTextNode(gainDDNumber));
			dd.appendChild(ddNumberdd);
			Element ddDurationdd = document.createElement("duration");
			ddDurationdd.appendChild(document.createTextNode(gainDDDuration));
			dd.appendChild(ddDurationdd);
			newAbility.appendChild(dd);
			
			Element dr = document.createElement("gainDR");
			Element drNumber = document.createElement("number");
			drNumber.appendChild(document.createTextNode(gainDRNumber));
			dr.appendChild(drNumber);
			Element drDuration = document.createElement("duration");
			drDuration.appendChild(document.createTextNode(gainDRDuration));
			dr.appendChild(drDuration);
			newAbility.appendChild(dr);
			
			Element moreDmgHP = document.createElement("moreDamagePerHPLost");
			Element extraDmg = document.createElement("extraDamage");
			extraDmg.appendChild(document.createTextNode(moreDamagePerHPLostDamage));
			moreDmgHP.appendChild(extraDmg);
			Element extraDmgHP = document.createElement("hpLost");
			extraDmgHP.appendChild(document.createTextNode(moreDamagePerHPLostHP));
			moreDmgHP.appendChild(extraDmgHP);
			newAbility.appendChild(moreDmgHP);
			
			if (gainHPNumber==null || gainHPDuration==null) {
				gainHPNumber = "0";
				gainHPDuration = "0";
			}
			Element gainHP = document.createElement("gainHP");
			Element hpNumber = document.createElement("number");
			hpNumber.appendChild(document.createTextNode(gainHPNumber));
			gainHP.appendChild(hpNumber);
			Element hpDuration = document.createElement("duration");
			hpDuration.appendChild(document.createTextNode(gainHPDuration));
			gainHP.appendChild(hpDuration);
			newAbility.appendChild(gainHP);
			
			/*Element hp = document.createElement("gainHP");
			Element hpNumber = document.createElement("number");
			hpNumber.appendChild(document.createTextNode(gainHPNumber));
			hp.appendChild(hpNumber);
			Element hpDuration = document.createElement("duration");
			hpDuration.appendChild(document.createTextNode(gainHPDuration));
			hp.appendChild(hpDuration);
			newAbility.appendChild(hp);*/
			
			Element moreDmgHPenemy = document.createElement("moreDamageEnemyHPLost");
			Element extraDmgenemy = document.createElement("extraDamage");
			extraDmgenemy.appendChild(document.createTextNode(moreDamagePerEnemyHPLostDamage));
			moreDmgHPenemy.appendChild(extraDmgenemy);
			Element extraDmgHPenemy = document.createElement("hpLost");
			extraDmgHPenemy.appendChild(document.createTextNode(moreDamagePerEnemyHPLostHP));
			moreDmgHPenemy.appendChild(extraDmgHPenemy);
			newAbility.appendChild(moreDmgHPenemy);
			
			Element temporaryDamage = document.createElement("temporaryDamageIncrease");
			Element tempDamage = document.createElement("extraDamage");
			tempDamage.appendChild(document.createTextNode(damageNumber));
			temporaryDamage.appendChild(tempDamage);
			Element tempDuration = document.createElement("duration");
			tempDuration.appendChild(document.createTextNode(damageDuration));
			temporaryDamage.appendChild(tempDuration);
			newAbility.appendChild(temporaryDamage);
			
			root.appendChild(newAbility);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			StreamResult result = new StreamResult("D:\\GitHub\\Projeto\\abilities.xml");
			transformer.transform(source, result);

			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	


}
