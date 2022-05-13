let abilityClicked = null;
let charPosUsedSkill = null;
let abilityUsedPos = null;

let allAbilitiesUsed = [];
let allCharsUsedSkill = [];
let allTargets = [];
let allAllyEnemy = [];
let allAbilitiesID = [];


function defineTurns(turn) {
	
	var opp = document.getElementsByClassName ("opp_turn");
	var opp_text = document.getElementsByClassName ("opp_text");
	var my = document.getElementsByClassName ("my_turn");
	
	if (turn==true) {
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
			   document.getElementById("natures").innerHTML = x[6];
			   //document.getElementById("natures").innerHTML = this.responseText;
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
	//xhttp.open("POST", "InGame?metodo=unlock&allAbilitiesUsed="+allAbilitiesUsed+"&allCharsUsedSkill="+allCharsUsedSkill+
	//		"&allTargets="+allTargets+"&allAllyEnemy="+allAllyEnemy+"&allAbilitiesID="+allAbilitiesID, true);  // assincrono
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
		   defineTurns(false);
		   
		   var x = this.responseText.split("break");
		   
		   document.getElementById("hpAlly0").innerHTML = x[0];
		   document.getElementById("hpAlly1").innerHTML = x[1];
		   document.getElementById("hpAlly2").innerHTML = x[2];
	       document.getElementById("hpEnemy1").innerHTML = x[3];
		   document.getElementById("hpEnemy2").innerHTML = x[4];
		   document.getElementById("hpEnemy3").innerHTML = x[5];

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
	//console.log("AllyEnemy: "+ allyEnemy);
	//console.log("charPos: " + charPos);
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
				   
//				   for (let i = 0; i < allAbilitiesUsed.length; i++) {
//					   console.log("ability pos: "+allAbilitiesUsed[i]);
//					   console.log("char used skill: "+allCharsUsedSkill[i]);
//					   console.log("target: "+allTargets[i]);
//					   console.log("ally-enemy: "+allAllyEnemy[i]);
//					   console.log("----------------");
//				   }
				   
				   if (allyEnemy=="enemy") {
					   document.getElementById("effectsEnemy"+charPos).insertAdjacentHTML('beforeend', this.responseText);
				   }
				   else if (allyEnemy=="ally") {
					   document.getElementById("effectsAlly"+charPos).insertAdjacentHTML('beforeend', this.responseText);
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

function cancelAbility(pos) {
	
	var id = document.getElementById("selected"+pos).getElementsByTagName('img')[0].id;
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
	   
	 
}


function abilityClick(abilityID, selfChar, abilityPos) {
	
	var imgIDselected = $.map($("#selected"+selfChar+" > img"), div => div.id);
	//console.log(imgIDselected[0]);
	if(imgIDselected[0] == "selectedNone" || imgIDselected[0] == null) {
		
		const xhttp = new XMLHttpRequest();

		xhttp.onload = function() {
		   if (xhttp.status === 200 && xhttp.readyState === 4) {
			   removeAllTargetClick();
			   
			   var element = document.createElement("div");
			   element.classList.add("choose");
			   
			   if (this.responseText.trim()=="self") {
					document.getElementById("ally"+selfChar).appendChild(element);
			   }
			   else if (this.responseText.trim()=="ally") {
					document.getElementById("ally0").appendChild(element.cloneNode(true));
					document.getElementById("ally1").appendChild(element.cloneNode(true));
					document.getElementById("ally2").appendChild(element.cloneNode(true));
			   }
			   else if (this.responseText.trim()=="enemy") {
				   var elementEnemy = document.createElement("div");
				   elementEnemy.classList.add("chooseEnemy");
				  
				   document.getElementById("enemy0").appendChild(elementEnemy.cloneNode(true));
				   document.getElementById("enemy1").appendChild(elementEnemy.cloneNode(true));
				   document.getElementById("enemy2").appendChild(elementEnemy.cloneNode(true));
			   }
			   
			   abilityClicked = abilityID;
			   charPosUsedSkill = selfChar;
			   abilityUsedPos = abilityPos;
			} 
		}
		xhttp.open("POST", "AbilityActions?action=seeTarget&selfChar="+selfChar+"&abilityPos="+abilityPos, true);  // assincrono
		xhttp.send(null);
		
	}

	abilityFooterInfo(abilityID);
	
	
}

function abilityFooterInfo(abilityID) {

	displayNones();
	document.getElementById("ability"+abilityID).style.display="block";
	
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
	
	var id = "tooltiptext1"+activeSkill;
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




