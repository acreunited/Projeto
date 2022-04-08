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

<html lang="en" id="move">

<head>


  <meta charset="UTF-8">
  <meta name="viewport" content="minimum-scale=0.5">

  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Game Selection</title>
  
  <link href="css/ingameSelection.css" rel="stylesheet">
   <link href="css/stylesheet.css" rel="stylesheet">
</head>

<body style="padding: 0px;">


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
						   <img src="ViewCharacter?id=<%=characterID %>" class="foto borda"> 
						   <div class="nomeskill">
						      <%=name%>
						   </div>
						   <div class="nomeEnergy">
						      ENERGY:
						   </div>
						   
						   <div id="charDescription">
							   <div class="desc">
							   	<%=descricao%>
							   </div>
						   </div>
						   
						   <% 
					       ResultSet abilities_clicks = conn.createStatement().executeQuery("select * from ABILITY where characterID="+characterID+";");
						   while (abilities_clicks.next()) {
										
								String abilityID = abilities_clicks.getString("abilityID");
								
								ResultSet abilit = conn.createStatement().executeQuery(
										"select * from THEME_ABILITY where themeID=1 and abilityID="+abilityID+";");
								if (abilit.next()) {
									String descricao_ability = abilit.getString("descricao");
								
								%>
								
								<div id="ability<%=abilityID%>" style="display:none;">
								   <div class="desc">
								   	<%=descricao_ability%>
								   </div>
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
					    <img src="ViewCharacter?id=<%=characterID %>" class="fotoskill borda"> 
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
			     
			     
			     
			            
            <div class="menu">
                <ul>
                    <li><img src="selection/buttonLogout.png"></li>
                    <li><img src="selection/buttonLadder.png"></li>
                    <li style="margin-left: 3px;"><img src="selection/buttons_quick.png"></li>
                    <li style="margin-left: 2px;"><img src="selection/button_private.png"></li>
                </ul>
            </div>
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
                        <li class="personagem_fundo borda"><img src="ViewCharacter?id=<%=characterID %>" class="personagem" onclick="displayInfo(<%=characterID %>)"></li>
                        
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
                            <div data-v-41d77b00="">1</div>
                            <div data-v-41d77b00="">2</div>
                            <div data-v-41d77b00="">3</div>
                        </div>
                        <div class="containers">
                            <div class="items" style="background-image: url();"></div>
                            <div class="items" style="background-image: url();"></div>
                            <div class="items" style="background-image: url();"></div>
                        </div>
                    </div>
                
                </div>
            </div>
  
           
            <div class="arrow_left"></div>
            <div class="arrow_right"></div>

        </div>
     
    </div>
</div>

<script>
	function displayInfo(id) {
		console.log(id);
		
		for (let i = 0; i < 100; i++) {
			if (document.getElementById("displayCharacter"+i)!=null) {					
				document.getElementById("displayCharacter"+i).style.display="none";
			}
		} 
		if (document.getElementById("displayCharacter"+id)!=null) {		
			document.getElementById("displayCharacter"+id).style.display="block";
			document.getElementById("charDescription").style.display="block";
		}		
	}
	
	function displayAbilityDescription(id) {
		console.log(id);
		document.getElementById("charDescription").style.display="none";
		for (let i = 0; i < 100; i++) {
			if (document.getElementById("ability"+i)!=null) {					
				document.getElementById("ability"+i).style.display="none";
			}
		} 
		if (document.getElementById("ability"+id)!=null) {	
			document.getElementById("ability"+id).style.display="block";
			
		}		
	}
</script>



</body>
</html>
