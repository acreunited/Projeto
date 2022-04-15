<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.io.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="main.Connector"%>
<%@page import="users.UserInfo"%>
<%@ page import="game.FindOpponent"%>

<html lang="en" id="move">

<head>


  <meta charset="UTF-8">
  <meta name="viewport" content="minimum-scale=0.5">

  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Game Selection</title>
  
 <link href="css/ingameSelection.css" rel="stylesheet">

</head>


<body style="padding: 0px;">
<form method="GET" id="form" name='searchingBattle' action='FindOpponent'>


          

<div id="app" class="central">
    <div id="root">
        <div class="mc_custom">
            
            <!-- <img src="https://media.discordapp.net/attachments/951508126518116432/957459505829007370/handsignbg.png" style="width: 770px; height: 560px;">
             -->
          
			   <img src="selection/background.png" style="width: 770px; height: 560px;">   
			  <%
			Class.forName(Connector.drv);
			try (Connection conn = Connector.getConnection();) {
				Statement stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery("select * from THEME_CHARACTER where themeID=1;");
				
				while (rs.next()) {
					String characterID = rs.getString("characterID");
					
					ResultSet especific =  conn.createStatement().executeQuery("select * from THEME_CHARACTER where themeID=1 and characterID="+characterID);
					if (especific.next()) {
						String name = especific.getString("nome");
						String descricao = especific.getString("descricao");
			%>
					<div class="topo" id="displayCharacter<%=characterID%>" style="display:none">
						<div class="conteudo">
						   <div class="nome">
						      <%=name%>
						   </div>
						   <img src="ViewCharacter?id=<%=characterID %>" class="foto borda" onclick="displayCharacterDescription(<%=characterID %>)"> 
						  
						   <div class="nomeEnergy">
						      ENERGY:
						   </div>
						   
						   <div id="charDescription<%=characterID %>">
							   <div class="desc">
							   	<%=descricao%>
							   </div>
							    <div class="nomeskill">
							      <%=name%>
							   </div>
							   <img src="ViewCharacter?id=<%=characterID %>" class="fotoskill borda"> 
						   </div>
						   
						   <% 
					       ResultSet abilities_clicks = conn.createStatement().executeQuery("select * from ABILITY where characterID="+characterID+";");
						   while (abilities_clicks.next()) {
										
								String abilityID = abilities_clicks.getString("abilityID");
								
								ResultSet abilit = conn.createStatement().executeQuery(
										"select * from THEME_ABILITY where themeID=1 and abilityID="+abilityID+";");
								if (abilit.next()) {
									String descricao_ability = abilit.getString("descricao");
									String name_ability = abilit.getString("nome");
								
								%>
								
								<div id="ability<%=abilityID%>" style="display:none;">
								   <div class="desc">
								   	<%=descricao_ability%>
								   </div>
								   <img src="ViewCharacter?id=<%=characterID %>" class="foto borda" onclick="displayCharacterDescription(<%=characterID %>)"> 
								   <div class="nomeskill">
								      <%=name_ability %>
								   </div>
								   <img src="ViewAbility?id=<%=abilityID %>" class="fotoskill borda"> 
							   </div>
							   
							   <%
							  
								}
								abilit.close();
						   }
						   abilities_clicks.close();
								
							   %>
						  
						   <div class="cooldown">
						   		Cooldown: 
						   </div>
						   
						</div>
					    
					    
					    <nav>
					       <ul class="listaskills">
					       <% 
					       ResultSet abilities = conn.createStatement().executeQuery("select * from ABILITY where characterID="+characterID+";");
						   while (abilities.next()) {
										
								String abilityID = abilities.getString("abilityID");
						   %>
					          <li><img src="ViewAbility?id=<%=abilityID %>" class="fotolista borda" onclick="displayAbilityDescription(<%=abilityID %>)"></li>
					         <!-- <li><img src="https://i.imgur.com/oEjXE4N.jpg" class="fotolista borda"></li>
					          <li><img src="https://i.imgur.com/ZnI4UeN.jpg" class="fotolista borda"></li>
					          <li><img src="https://i.imgur.com/xmnDpJO.jpg" class="fotolista borda"></li>-->
					           <%				
							}
						
						abilities.close();
						%>	
					       </ul>
					    </nav>
					
					</div>
					<%
					}
					especific.close();
					}
					rs.close();
					} catch (SQLException | IOException e) {
					System.out.println(e.getMessage());
					}
					%>
			     
			     
			     
			            
         
            <div>
                <div class="rodape">
                    <!-- <input type="text" placeholder="Search Group" class="searchbargroup">
                    <input type="text" placeholder="Search Character" class="searchbar"> -->
                    <ul>
                    <%
					Class.forName(Connector.drv);
					try (Connection conn = Connector.getConnection();) {
						Statement stmt = conn.createStatement();
						
						ResultSet rs = stmt.executeQuery("select characterID from THEME_CHARACTER where themeID=1 LIMIT 21;");
						
						while (rs.next()) {
							
							String characterID = rs.getString("characterID");							
					%>
                        <li class="personagem_fundo borda" ondrop="drop(event)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event)">
                        <img id="charpic<%=characterID %>" src="ViewCharacter?id=<%=characterID %>" class="personagem" onclick="displayInfo(<%=characterID %>)"
                        
                      
                        >
                        </li>
                       
                        <%
					}
						rs.close();
					} catch (SQLException | IOException e) {
						System.out.println(e.getMessage());
					}
					%>
            
                    </ul>
                    
					<%
					Class.forName(Connector.drv);
					try (Connection conn = Connector.getConnection();) {
						Statement stmt = conn.createStatement();
						
						ResultSet rs = stmt.executeQuery("select * from USERS where userID="+session.getAttribute("userID"));
						if (rs.next()) {
							String username = rs.getString("username");
							String userID = rs.getString("userID");
							String xp = rs.getString("xp");
							String nWins = rs.getString("nWins");
							String nLosses = rs.getString("nLosses");
							String streak = rs.getString("streak");
							String level = UserInfo.getLevel(xp);
					%>
                    <div class="perfil"><img src="ViewAvatar?id=<%=userID %>" class="avatar borda"><img class="mold">
                        <div class="texto" style="white-space: nowrap;">
                        	<span><%=username %></span><br>
                            <span>Cabin Boy</span>
                            <br><span>Level:</span> <%=level %> (<%=xp %> XP)
                            <br><span>Ladderrank:</span> #2
                            <br><span>Ratio:</span> <%=nWins %>-<%=nLosses %> (+<%=streak %>)</div>
                    </div>
                    <%
                    }
					rs.close();
					} catch (SQLException | IOException e) {
					System.out.println(e.getMessage());
					}
					%>
                    <div class="selecionados">
                        <div class="selectedsnum">
                            <div>1</div>
                            <div>2</div>
                            <div>3</div>
                        </div>
                        <div class="containers">
                        
                            <div id="items1" ondrop="drop(event)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event)"class="items"></div>
                            <div id="items2" ondrop="drop(event)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event)" class="items"></div>
                            <div id="items3" ondrop="drop(event)" ondragover="allowDrop(event)" draggable="true" ondragstart="drag(event)" class="items"></div>
                        	<input type="hidden" name="char1" id="inputChar1">
                        	<input type="hidden" name="char2" id="inputChar2">
                        	<input type="hidden" name="char3" id="inputChar3">
                        	<input type="hidden" id="battleType" name="battle"/>
                        	<input type="hidden" name="userID" value="<%=session.getAttribute("userID")%>"/>
              		
                        </div>
                    </div>
          
		
			<!-- <img id="drag1" src="selection/background.png" draggable="true" ondragstart="drag(event)" width="336" height="69" style="postion: absolute;">-->
                
                </div>
            </div>
            
               <div class="menu">
                <ul>
                    <li><img src="selection/buttonLogout.png"></li>
                    <li><img src="selection/buttonLadder.png"></li>
                    <li style="margin-left: 3px;">
                    
                    
              		<!-- <input type="hidden" id="inputChar1" name="inputChar1" value="getChar1()"/> -->
 					<img src="selection/buttons_quick.png" onclick="findOpp('quick')">
                   
  					       
                    </li>
                    <li style="margin-left: 2px;"><img src="selection/button_private.png"></li>
                    
                </ul>
            </div>
  
           
            <div class="arrow_left"></div>
            <div class="arrow_right"></div>
            
            

        </div>
     
    </div>
</div>

</form>

<script>
function displayInfo(id) {
	
	for (let i = 0; i < 100; i++) {
		if (document.getElementById("displayCharacter"+i)!=null) {					
			document.getElementById("displayCharacter"+i).style.display="none";
		}
	} 
	if (document.getElementById("displayCharacter"+id)!=null) {		
		document.getElementById("displayCharacter"+id).style.display="block";
		document.getElementById("charDescription"+id).style.display="block";
	}		
}

function findOpp(type) {
	var items = document.getElementsByClassName("items");
	let first = items[0].firstChild.id;
	let second = items[1].firstChild.id;
	let third = items[2].firstChild.id;
	
	document.getElementById("inputChar1").value = first.split("charpic")[1];
	document.getElementById("inputChar2").value = second.split("charpic")[1];
	document.getElementById("inputChar3").value = third.split("charpic")[1];
	
	document.getElementById("battleType").value = type;
	
	var form = document.getElementById("form");
	 
    form.submit();
		
}

function getChar1() {
	var items = document.getElementsByClassName("items");
	let first = items[0].firstChild.id;
	console.log(first);
	return first;
}
	
function displayAbilityDescription(id) {
	//document.getElementById("").style.display="none";
	for (let i = 0; i < 100; i++) {
		if (document.getElementById("ability"+i)!=null) {					
			document.getElementById("ability"+i).style.display="none";
		}
		if (document.getElementById("charDescription"+i)!=null) {					
			document.getElementById("charDescription"+i).style.display="none";
		}
	} 
	if (document.getElementById("ability"+id)!=null) {	
		document.getElementById("ability"+id).style.display="block";
	}		
}
function displayCharacterDescription(id) {
	
	for (let i = 0; i < 100; i++) {
		if (document.getElementById("ability"+i)!=null) {					
			document.getElementById("ability"+i).style.display="none";
		}
		if (document.getElementById("charDescription"+i)!=null) {					
			document.getElementById("charDescription"+i).style.display="none";
		}
	}
	if (document.getElementById("charDescription"+id)!=null) {	
		document.getElementById("charDescription"+id).style.display="block";
	}
	
}
	
function allowDrop(ev) {
  ev.preventDefault();
}

function drag(ev) {
  ev.dataTransfer.setData("img", ev.target.id);
}

function drop(ev) {
  ev.preventDefault();
  var data = ev.dataTransfer.getData("img");
  
  if (ev.target.id=="" || ev.target.id=="items1" || ev.target.id=="items2" || ev.target.id=="items3") {
	  ev.target.appendChild(document.getElementById(data));
	  //log();
  }

}
</script>



</body>
</html>
