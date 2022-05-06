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
	xhttp.open("GET", "InGame?metodo=lock", true);  // assincrono
	xhttp.send(null);

}

function endTurn() {
	const xhttp = new XMLHttpRequest();

	xhttp.onload = function() {
	   if (xhttp.status === 200 && xhttp.readyState === 4) {
		   //document.getElementById("natures").innerHTML = this.responseText;
		   defineTurns(false);
		} 
	}
	xhttp.open("GET", "InGame?metodo=unlock", true);  // assincrono
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
	xhttp.open("GET", "InGame?metodo=loser", true);  // assincrono
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

function characterFooterInfo(id) {
	console.log(id);
	displayNones();
	document.getElementById("character"+id).style.display="block";
	
	abilityOnThisChar(id);
	
}
function abilityOnThisChar(charID) {
	/*let fullText = []; 
    $('#playerMoves').children('div').each(function () {
    	let childText = this.innerHTML;
      if(childText.trim().length)
      {
        fullText.push(childText);
      }
      else {
    	  console.log("ability sem target");
      }
    });
    console.log(fullText.join(";"));*/
    
	let fullText = []; 
    $('#playerMoves').children('div').each(function () {
    	let childText = this.innerHTML;
      if (childText.trim().length==0) {
        console.log($(childText).attr("id"));
      }
     
    });
    
}

function abilityFooterInfo(abilityID, selfChar) {
	//console.log(count);
	//mostrar no footer
	displayNones();
	document.getElementById("ability"+abilityID).style.display="block";
	
	//usar habilidade
	useAbilityCurrent(abilityID);
	
	//target da habilidade
	removeAllTargetClick()
	
	/*
	if (selfChar>-1) {
		var element = document.createElement("div");
		element.classList.add("choose");

		document.getElementById("ally"+selfChar).appendChild(element);
	}
	*/
}

function removeAllTargetClick() {

	//document.querySelectorAll('.choose').forEach(e => e.remove());

}

function useAbilityCurrent(abilityID) {
	var element = document.createElement("div");
	element.setAttribute('id', abilityID);
	document.getElementById("playerMoves").appendChild(element);
}


function playerFooterInfo(my_opp) {
	
	displayNones();

	if (my_opp=="my") {
		document.getElementById("player0").style.display="block";
	} 
	else if (my_opp=="opp") {
		document.getElementById("player1").style.display="block";
	}
	
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




