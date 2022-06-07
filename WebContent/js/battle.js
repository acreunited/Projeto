let abilityClicked = null;
let charPosUsedSkill = null;
let abilityUsedPos = null;

let allAbilitiesUsed = [];
let allCharsUsedSkill = [];
let allTargets = [];
let allAllyEnemy = [];
let allAbilitiesID = [];


function canUseAbilityNature() {
	const xhttp = new XMLHttpRequest();

	xhttp.onload = function() {
	   if (xhttp.status === 200 && xhttp.readyState === 4) {
		   
		   var response = this.responseText.split("-");
		   
//		   var skill0 = document.getElementsByClassName("skillimg0");
//		   var skill1 = document.getElementsByClassName("skillimg1");
//		   var skill2 = document.getElementsByClassName("skillimg2");
//		   var skill3 = document.getElementsByClassName("skillimg3");
		   
		   for(let i = 0; i<response.length; i++) {
			   if (i<4) { //char1
				   var x = document.getElementsByClassName("skillimg"+i);
				   if (response[i].trim()=="false") {
					   x[1].classList.add('disabledNature');
				   }
				   else {
					   x[1].classList.remove('disabledNature');
				   }
			   }
			   else if (i<8) {//char2
				   var x = document.getElementsByClassName("skillimg"+(i-4));
				   if (response[i].trim()=="false") {
					   x[3].classList.add('disabledNature');
				   }
				   else {
					   x[3].classList.remove('disabledNature');
				   }
			   }
			   else {//char3
				   var x = document.getElementsByClassName("skillimg"+(i-8));
				   if (response[i].trim()=="false") {
					   x[5].classList.add('disabledNature');
				   }
				   else {
					   x[5].classList.remove('disabledNature');
				   }
			   }
		   }

		} 
	}

	xhttp.open("POST", "AbilityActions?action=abilityHasNature", true);
	xhttp.send(null);
}

function defineTurns(turn) {
	
	var opp = document.getElementsByClassName ("opp_turn");
	var opp_text = document.getElementsByClassName ("opp_text");
	var my = document.getElementsByClassName ("my_turn");
	
	if (turn==true) {
		
		canUseAbilityNature();	
		
		
		for (var i = 0; i < opp.length; i++) {
			opp[i].style.display="none";
		}
		for (var i = 0; i < opp_text.length; i++) {
			opp_text[i].style.display="none";
		}
		for (var i = 0; i < my.length; i++) {
			my[i].style.display="block";
		}
	}
	else if (turn==false) {

		for (var i = 0; i < opp.length; i++) {
			opp[i].style.display="block";
		}
		for (var i = 0; i < opp_text.length; i++) {
			opp_text[i].style.display="block";
		}
		for (var i = 0; i < my.length; i++) {
			my[i].style.display="none";
		}
		
		lockSemaphore();
	}
	
}

function lockSemaphore() {
	
	const xhttp = new XMLHttpRequest();

	xhttp.onload = function() {
	   if (xhttp.status === 200 && xhttp.readyState === 4) {

		   if (this.responseText.trim()=="winner") {
			   winner();
		   }
		   else if (this.responseText.trim()=="loser") {
			   loser();
		   }
		   else {
			   var x = this.responseText.split("break");
			
			   document.getElementById("hpAlly0").innerHTML = x[0];
			   document.getElementById("hpAlly1").innerHTML = x[1];
			   document.getElementById("hpAlly2").innerHTML = x[2];
		       document.getElementById("hpEnemy1").innerHTML = x[3];
			   document.getElementById("hpEnemy2").innerHTML = x[4];
			   document.getElementById("hpEnemy3").innerHTML = x[5];
			   
			   document.getElementById("effectsAlly0").innerHTML = x[6];
			   document.getElementById("effectsAlly1").innerHTML = x[7];
			   document.getElementById("effectsAlly2").innerHTML = x[8];
			   document.getElementById("effectsEnemy1").innerHTML = x[9];
			   document.getElementById("effectsEnemy2").innerHTML = x[10];
			   document.getElementById("effectsEnemy3").innerHTML = x[11];
			   
			   document.getElementById("natures").innerHTML = x[12];
			   
			   var charIsStunned = x[13].split("-");
			   
			   if (charIsStunned[0].trim()=="true") {
				   $('#allSkillsChar0 img').addClass('disabled');
			   }
			   if (charIsStunned[1].trim()=="true") {
				   $('#allSkillsChar1 img').addClass('disabled');
			   }
			   if (charIsStunned[2].trim()=="true") {
				   $('#allSkillsChar2 img').addClass('disabled');
			   }
			   
			   var cooldown1 = x[14].split("-");
			   for (let i = 0; i<cooldown1.length; i++) {
				   document.getElementById("cooldown0-"+i).innerHTML = cooldown1[i].trim();
				   if (cooldown1[i].trim()!="0") {
					   document.getElementById("cooldown0-"+i).style.display = "block";
					   document.getElementById("cooldown0-"+i).parentElement.classList.add('disabled');
				   }
				   else {
					   document.getElementById("cooldown0-"+i).style.display = "none";
					   document.getElementById("cooldown0-"+i).parentElement.classList.remove('disabled');
				   }
				  
			   }
			   var cooldown2 = x[15].split("-");
			   for (let i = 0; i<cooldown2.length; i++) {
				   document.getElementById("cooldown1-"+i).innerHTML = cooldown2[i].trim();
				   if (cooldown2[i].trim()!="0") {
					   document.getElementById("cooldown1-"+i).style.display = "block";
					   document.getElementById("cooldown1-"+i).parentElement.classList.add('disabled');
				   }
				   else {
					   document.getElementById("cooldown1-"+i).style.display = "none";
					   document.getElementById("cooldown1-"+i).parentElement.classList.remove('disabled');
				   }
			   }
			   var cooldown3 = x[16].split("-");
			   for (let i = 0; i<cooldown3.length; i++) {
				   document.getElementById("cooldown2-"+i).innerHTML = cooldown3[i].trim();
				   if (cooldown3[i].trim()!="0") {
					   document.getElementById("cooldown2-"+i).style.display = "block";
					   document.getElementById("cooldown2-"+i).parentElement.classList.add('disabled');
				   }
				   else {
					   document.getElementById("cooldown2-"+i).style.display = "none";
					   document.getElementById("cooldown2-"+i).parentElement.classList.remove('disabled');
				   }
			   }

			  
			   defineTurns(true);
		   }
		   
		   allAbilitiesUsed = [];
		   allCharsUsedSkill = [];
		   allTargets = [];
		   allAllyEnemy = [];
		   allAbilitiesID = [];
		} 
	}
	xhttp.open("POST", "InGame?metodo=lock", true);  // assincrono
	xhttp.send(null);

}

function storeAbilities() {
	const xhttp = new XMLHttpRequest();

	xhttp.onload = function() {
	   if (xhttp.status === 200 && xhttp.readyState === 4) {
		   endTurn();
		} 
	}

	xhttp.open("POST", "AbilityActions?action=saveAbilities&allAbilitiesUsed="+allAbilitiesUsed+"&allCharsUsedSkill="+allCharsUsedSkill+
			"&allTargets="+allTargets+"&allAllyEnemy="+allAllyEnemy+"&allAbilitiesID="+allAbilitiesID, true);
	xhttp.send(null);
}

function endTurn() {
	
	const xhttp = new XMLHttpRequest();

	xhttp.onload = function() {
	   if (xhttp.status === 200 && xhttp.readyState === 4) {
		   removeAllTargetClick();
		   cancelAbility(0);
		   cancelAbility(1);
		   cancelAbility(2);
		   allAbilitiesUsed = [];
		   allCharsUsedSkill = [];
		   allTargets = [];
		   allAllyEnemy = [];
		   allAbilitiesID = [];
		   defineTurns(false);
		   
		   var x = this.responseText.split("break");
		  
		   document.getElementById("hpAlly0").innerHTML = x[0];
		   document.getElementById("hpAlly1").innerHTML = x[1];
		   document.getElementById("hpAlly2").innerHTML = x[2];
	       document.getElementById("hpEnemy1").innerHTML = x[3];
		   document.getElementById("hpEnemy2").innerHTML = x[4];
		   document.getElementById("hpEnemy3").innerHTML = x[5];
		   
		   document.getElementById("effectsAlly0").innerHTML = x[6];
		   document.getElementById("effectsAlly1").innerHTML = x[7];
		   document.getElementById("effectsAlly2").innerHTML = x[8];
		   document.getElementById("effectsEnemy1").innerHTML = x[9];
		   document.getElementById("effectsEnemy2").innerHTML = x[10];
		   document.getElementById("effectsEnemy3").innerHTML = x[11];

		   
		   
		} 
	}
	//xhttp.open("POST", "InGame?metodo=unlock&allAbilitiesUsed="+allAbilitiesUsed+"&allCharsUsedSkill="+allCharsUsedSkill+
	//		"&allTargets="+allTargets+"&allAllyEnemy="+allAllyEnemy+"&allAbilitiesID="+allAbilitiesID, true);  // assincrono
	xhttp.open("POST", "InGame?metodo=unlock", true);
	xhttp.send(null);
}

function loser() {
	const xhttp = new XMLHttpRequest();

	xhttp.onload = function() {
	   if (xhttp.status === 200 && xhttp.readyState === 4) {
		    document.getElementById("oppTurnDisable").style.display="none";
			document.getElementById("passTurn").style.display="none";
			document.getElementById("winnerTurn").style.display="none";
			document.getElementById("winnerQuick").style.display="none";
			document.getElementById("loserTurn").style.display="block";
			document.getElementById("loserQuick").style.display="block";
		} 
	}
	xhttp.open("POST", "InGame?metodo=loser", true);  // assincrono
	xhttp.send(null);
}


function winner() {
	document.getElementById("oppTurnDisable").style.display="none";
	document.getElementById("passTurn").style.display="none";
	document.getElementById("loserTurn").style.display="none";
	document.getElementById("loserQuick").style.display="none";
	document.getElementById("winnerTurn").style.display="block";
	document.getElementById("winnerQuick").style.display="block";
}

function redirectSelection() {
	const xhttp = new XMLHttpRequest();

	xhttp.onload = function() {
	   if (xhttp.status === 200 && xhttp.readyState === 4) {
		   window.location.href = "selection.jsp";
		} 
	}
	xhttp.open("POST", "InGame?metodo=remove", true);  // assincrono
	xhttp.send(null);
	
}


function displayNones() {
	document.getElementById("player0").style.display="none";
	document.getElementById("player1").style.display="none";
	
	for (let i = 0; i < 999; i++) {
		if (document.getElementById("character"+i)!=null) {
			document.getElementById("character"+i).style.display="none";
		}
		if (document.getElementById("ability"+i)!=null) {
			document.getElementById("ability"+i).style.display="none";
		}
	}
}

function hasEffect(allyEnemy, charPos) {
	
	if (allyEnemy=="enemy") {
		if (charPos==1) {
			if($('.mc_char_10').find('.chooseEnemy').length !== 0) {
				return true;
			}
			else {
				return false;
			}
		}
		if (charPos==2) {
			if($('.mc_char_11').find('.chooseEnemy').length !== 0) {
				return true;
			}
			else {
				return false;
			}
		}
		if (charPos==3) {
			if($('.mc_char_12').find('.chooseEnemy').length !== 0) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	else if (allyEnemy=="ally") {
		if (charPos==0) {
			if($('.mc_char_00').find('.choose').length !== 0) {
				return true;
			}
			else {
				return false;
			}
		}
		if (charPos==1) {
			if($('.mc_char_01').find('.choose').length !== 0) {
				return true;
			}
			else {
				return false;
			}
		}
		if (charPos==2) {
			if($('.mc_char_02').find('.choose').length !== 0) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	console.log("NAO VERIFICOU");
	return false;
}

function characterFooterInfo(id, allyEnemy, charPos) {

	if (hasEffect(allyEnemy, charPos)) {
		//console.log("TEM EFEITO");
		const xhttp = new XMLHttpRequest();

		xhttp.onload = function() {
		   if (xhttp.status === 200 && xhttp.readyState === 4) {
			   
			   if (this.responseText.trim()!="nada") {

				   allAbilitiesUsed.push(abilityUsedPos);
				   allCharsUsedSkill.push(charPosUsedSkill);
				   allAllyEnemy.push(allyEnemy);
				   allTargets.push(charPos);
				   allAbilitiesID.push(abilityClicked);
			
				   var response = this.responseText.split("break");
				   
				   if (allyEnemy=="enemy") {
					   document.getElementById("effectsEnemy"+charPos).insertAdjacentHTML('beforeend',response[0]);
				   }
				   else if (allyEnemy=="ally") {
					   document.getElementById("effectsAlly"+charPos).insertAdjacentHTML('beforeend', response[0]);
				   }
				   
				   document.getElementById("selected"+charPosUsedSkill).innerHTML = "<img src='ViewAbility?id="+abilityClicked+"' id='abilitySelected"+abilityClicked+"'>";
				   
				   if (charPosUsedSkill==0) {
					   $('#allSkillsChar0 img').addClass('disabled');
				   }
				   else if (charPosUsedSkill==1) {
					   $('#allSkillsChar1 img').addClass('disabled');
				   }
				   else if (charPosUsedSkill==2) {
					   $('#allSkillsChar2 img').addClass('disabled');
				   }
				   
				   document.getElementById("natures").innerHTML = response[1];
				   canUseAbilityNature();
				   
				   removeAllTargetClick();
				   abilityClicked = null;
				   charPosUsedSkill = null;
				   abilityUsedPos = null;
		
			   }
			} 
		}
		xhttp.open("POST", "AbilityActions?action=applyAbility&abilityUsedID="+abilityClicked+"&allyEnemy="+allyEnemy, true);  // assincrono
		xhttp.send(null);
	}
	else {
		displayNones();
		document.getElementById("character"+id).style.display="block";
		
		removeAllTargetClick();
		abilityClicked = null;
		charPosUsedSkill = null;
		abilityUsedPos = null;
	}
	
	
	
}


function cancelArrayFirst(pos, parent) {
	 var ability = parent.getElementsByTagName('img')[0].id;
	 for (let i = 0; i < allAbilitiesUsed.length; i++) {
		 if ("abilitySelected"+allAbilitiesID[i]==ability) {
			 allAbilitiesUsed.splice(i, 1);
			 allCharsUsedSkill.splice(i, 1);
			 allTargets.splice(i, 1);
			 allAllyEnemy.splice(i, 1);
			 allAbilitiesID.splice(i, 1);
		 }
	 }

	 cancelAbility(pos);
}

function cancelAbility(pos) {
	   
	var id = document.getElementById("selected"+pos).getElementsByTagName('img')[0].id;
	//console.log();
	document.getElementById("selected"+pos).innerHTML = "<a><img src='battle/skillact.png'  id='selectedNone'></a>";

   if (pos==0) {
	  $('#allSkillsChar0 img').removeClass('disabled');
   }
   else if (pos==1) {
	   $('#allSkillsChar1 img').removeClass('disabled');
   }
   else if (pos==2) {
	   $('#allSkillsChar2 img').removeClass('disabled');
   }

   document.querySelectorAll('.effects_border0').forEach(function(e){
	  var actives = e.getElementsByTagName('img')[0].id;
	  if (actives.split("activeSkill")[1] == id.split("abilitySelected")[1]) {
		  e.remove();
	  }
   });

   document.querySelectorAll('.effects_border1').forEach(function(e){
	  var actives = e.getElementsByTagName('img')[0].id;
	  if (actives.split("activeSkill")[1] == id.split("abilitySelected")[1]) {
		  e.remove();
	  }
   });

   if (id.split("Selected")[1]!="" && id.split("Selected")[1]!="undefined" &&
		   id.split("Selected")[1]!=null && id.split("Selected")[1]!="null") {
	   
	    const xhttp = new XMLHttpRequest();

		xhttp.onload = function() {
		   if (xhttp.status === 200 && xhttp.readyState === 4) {
			   
			   document.getElementById("natures").innerHTML = this.responseText;
			   canUseAbilityNature();
			} 
		}
		xhttp.open("POST", "AbilityActions?action=cancelAbility&id="+id.split("Selected")[1], true);  // assincrono
		xhttp.send(null);
		
   }
   
	 
}


function abilityClick(abilityID, selfChar, abilityPos) {
	
	if (
		!document.getElementById("imageClickMaybe"+abilityID).classList.contains('disabled') &&
		!document.getElementById("cooldown"+selfChar+"-"+abilityPos).parentElement.classList.contains('disabled') &&
		!document.getElementById("cooldown"+selfChar+"-"+abilityPos).parentElement.classList.contains('disabledNature')
	) {
		
		var imgIDselected = $.map($("#selected"+selfChar+" > img"), div => div.id);
		
		if(imgIDselected[0] == "selectedNone" || imgIDselected[0] == null) {
			
			const xhttp = new XMLHttpRequest();

			xhttp.onload = function() {
				
			   if (xhttp.status === 200 && xhttp.readyState === 4) {
				   
				   removeAllTargetClick();
				   
				   var answer = this.responseText.split("-");
				   var element = document.createElement("div");
				   element.classList.add("choose");
				   
				   if (answer[0].trim()=="self") {
						document.getElementById("ally"+selfChar).appendChild(element);
				   }
				   else if (answer[0].trim()=="ally") {
						document.getElementById("ally0").appendChild(element.cloneNode(true));
						document.getElementById("ally1").appendChild(element.cloneNode(true));
						document.getElementById("ally2").appendChild(element.cloneNode(true));
				   }
				   else if (answer[0].trim()=="enemy") {
					   var elementEnemy = document.createElement("div");
					   elementEnemy.classList.add("chooseEnemy");
					   console.log(answer[1]+"-"+answer[2]+"-"+answer[3]);
					   if (answer[1].trim()=="false") {
						   document.getElementById("enemy0").appendChild(elementEnemy.cloneNode(true));
					   }
					   if (answer[2].trim()=="false") {
						   document.getElementById("enemy1").appendChild(elementEnemy.cloneNode(true));
					   }
					   if (answer[3].trim()=="false") {
						   document.getElementById("enemy2").appendChild(elementEnemy.cloneNode(true));
					   }
				   }
				   
				   abilityClicked = abilityID;
				   charPosUsedSkill = selfChar;
				   abilityUsedPos = abilityPos;
				} 
			}
			xhttp.open("POST", "AbilityActions?action=seeTarget&selfChar="+selfChar+"&abilityPos="+abilityPos, true);  // assincrono
			xhttp.send(null);
			
		}
	}
	else {
		removeAllTargetClick();
	}
	

	abilityFooterInfo(abilityID, selfChar, abilityPos);
	
	
}

function abilityFooterInfo(abilityID, selfChar, abilityPos) {
	const xhttp = new XMLHttpRequest();

	xhttp.onload = function() {
	   if (xhttp.status === 200 && xhttp.readyState === 4) {

		   document.getElementById("natures"+abilityID).innerHTML = this.responseText;
		   displayNones();
		   document.getElementById("ability"+abilityID).style.display="block";
		} 
	}
	xhttp.open("POST", "AbilityActions?action=seeNatureCost&selfChar="+selfChar+"&abilityPos="+abilityPos, true);  // assincrono
	xhttp.send(null);

	
	
}

function removeAllTargetClick() {

	document.querySelectorAll('.choose').forEach(e => e.remove());
	document.querySelectorAll('.chooseEnemy').forEach(e => e.remove());

}

function playerFooterInfo(my_opp) {
	abilityClicked = null;
	removeAllTargetClick();
	displayNones();

	if (my_opp=="my") {
		document.getElementById("player0").style.display="block";
	} 
	else if (my_opp=="opp") {
		document.getElementById("player1").style.display="block";
	}
	//console.log(abilityClicked);
	
}

function seeActiveSkillEnemy(activeSkill) {
	
	var id = "tooltiptext"+activeSkill;
	var skill = document.getElementsByClassName ("tooltiptext1");
	for (var i = 0; i < skill.length; i++) {
		if (id==skill[i].id) {
			skill[i].style.visibility = "visible" ;
		}
	}
}

function hideActiveSkillEnemy() {
	var skill = document.getElementsByClassName ("tooltiptext1");
	for (var i = 0; i < skill.length; i++) {
		skill[i].style.visibility = "hidden" ;
	}
}

function seeActiveSkill(activeSkill) {
	
	var id = "tooltiptext"+activeSkill;
	var skill = document.getElementsByClassName ("tooltiptext");
	for (var i = 0; i < skill.length; i++) {
		if (id==skill[i].id) {
			skill[i].style.visibility = "visible" ;
		}
	}
}

function hideActiveSkill() {
	var skill = document.getElementsByClassName ("tooltiptext");
	for (var i = 0; i < skill.length; i++) {
		skill[i].style.visibility = "hidden" ;
	}
}




