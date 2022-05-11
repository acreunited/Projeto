let abilityClicked = null;
let charPosUsedSkill = null;
let abilityUsedPos = null;

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
			   document.getElementById("natures").innerHTML = this.responseText;
			   defineTurns(true);
		   }
		   
		} 
	}
	xhttp.open("POST", "InGame?metodo=lock", true);  // assincrono
	xhttp.send(null);

}

function endTurn() {
	
	
	const xhttp = new XMLHttpRequest();

	xhttp.onload = function() {
	   if (xhttp.status === 200 && xhttp.readyState === 4) {
		   removeAllTargetClick();
		   defineTurns(false);
		   
		} 
	}
	xhttp.open("POST", "InGame?metodo=unlock", true);  // assincrono
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

function characterFooterInfo(id, allyEnemy, charPos) {
	
	const xhttp = new XMLHttpRequest();

	xhttp.onload = function() {
	   if (xhttp.status === 200 && xhttp.readyState === 4) {
		   removeAllTargetClick();
		   displayNones();
		   document.getElementById("character"+id).style.display="block";
		   
		   if (this.responseText.trim()!="nada") {
			  // alert("existe");
			   
			   if (allyEnemy=="enemy") {
				   document.getElementById("effectsEnemy"+charPos).innerHTML = this.responseText;
			   }
			   else if (allyEnemy=="ally") {
				   document.getElementById("effectsAlly"+charPos).innerHTML = this.responseText;
			   }
			   
			//   if(abilityClicked!==null) {
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
			 //  }
		   }
		  // else {
			//   alert("nada");
		  // }
	
		  
		   abilityClicked = null;
		   charPosUsedSkill = null;
		   abilityUsedPos = null;
		} 
	}
	xhttp.open("POST", "AbilityActions?action=applyAbility&abilityUsedID="+abilityClicked, true);  // assincrono
	xhttp.send(null);
	
}

function cancelAbility(pos) {
	
	//console.log("AB: "+idget.id);	
	var id = document.getElementById("selected"+pos).getElementsByTagName('img')[0].id;
	//console.log(id.getElementsByTagName('img')[0].id);
	//var currentID = 
	
	document.getElementById("selected"+pos).innerHTML = "<a><img src='battle/skillact.png'></a>";


	   if (pos==0) {
		  $('#allSkillsChar0 img').removeClass('disabled');
	   }
	   else if (pos==1) {
		   $('#allSkillsChar1 img').removeClass('disabled');
	   }
	   else if (pos==2) {
		   $('#allSkillsChar2 img').removeClass('disabled');
	   }
	   
	   var allActive = [];
	   
	   document.querySelectorAll('.effects_border0').forEach(function(e){
		   allActive.push(e.getElementsByTagName('img')[0].id);
	   });
	   
	   document.querySelectorAll('.effects_border0').forEach(function(e){
		  var actives = e.getElementsByTagName('img')[0].id;
		  console.log(actives.charAt(actives.length - 1) + " "+id.charAt(id.length - 1));
		  if (actives.charAt(actives.length - 1) == id.charAt(id.length - 1)) {
			  e.remove();
		  }
		  console.log("----");
	   });
	   
	 
}


function abilityClick(abilityID, selfChar, abilityPos) {
	const xhttp = new XMLHttpRequest();

	xhttp.onload = function() {
	   if (xhttp.status === 200 && xhttp.readyState === 4) {
		   removeAllTargetClick();
		   
		   abilityFooterInfo(abilityID);
		   
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




