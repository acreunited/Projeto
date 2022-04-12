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
  
 <link href="css/ingameBattle.css" rel="stylesheet">

</head>
<body>

      <div id="app" class="central">
         <div id="root">
            <div class="mc_custom">
               
               <img src="battle/thebackground.png" style="width: 770px; height: 560px;"> <!----> 
               <div class="mc_top">
                 <%
				Class.forName(Connector.drv);
				try (Connection conn = Connector.getConnection();) {
					Statement stmt = conn.createStatement();
					
					ResultSet rs = stmt.executeQuery("select * from USERS where userID=0;");
					
					if (rs.next()) {
						String userID = rs.getString("userID");
						String username = rs.getString("username");
							
				%>
                  <div class="mc_play1">
                     <div class="mc_username1">
                        <%=username %>
                     </div>
                     <div class="mc_userrank1">
                        CABIN BOY
                     </div>
                     <div class="mc_avatar1">
                        <img src="ViewAvatar?id=<%=userID %>" onclick="functionTest()"> 
                     </div>
                  </div>
                  <%
					}
					rs.close();
					} catch (SQLException | IOException e) {
					System.out.println(e.getMessage());
					}
					%>
                  
                  
                  <!----> 
                  <div class="mc_control">
                     <div class="mc_bar_system">
                        <div class="mc_bar_back">
                           <div class="mc_bar_fill" style="width: 133.7px;"></div>
                        </div>
                        <div class="mc_bar_ready opp_turn" style="display:none;" onclick="changeTurnTo('my')">
                           Opponent Turn...
                        </div>
                        <div class="mc_bar_ready my_turn" onclick="changeTurnTo('opp')">
                           Press To End Turn
                        </div>
                        <div class="mc_energy_system" id="my_turn">
			                <div class="mc_energy_bar"></div>
			                <div class="mc_energy_txt">
			                  <strong class="energy0">x 1</strong>
			                  <strong class="energy1">x 2</strong>
			                  <strong class="energy2">x 0</strong>
			                  <strong class="energy3">x 0</strong>
			                  <strong class="energy4">x 3</strong>
			                </div>
			                <div class="mc_energy_exchange">EXCHANGE ENERGY</div>
			              </div>
                     </div>
                     <!---->
                  </div>
                <%
				Class.forName(Connector.drv);
				try (Connection conn = Connector.getConnection();) {
					Statement stmt = conn.createStatement();
					
					ResultSet rs = stmt.executeQuery("select * from USERS where userID=1;");
					
					if (rs.next()) {
						String userID = rs.getString("userID");
						String username = rs.getString("username");
							
				%>
                  <div class="mc_play2">
                     <div class="mc_username2">
                        <%=username %>
                     </div>
                     <div class="mc_userrank2">
                        CABIN BOY
                     </div>
                     <div class="mc_avatar2">
                        <img src="ViewAvatar?id=<%=userID %>" onclick="playerFooterInfo('opp', <%=userID %>)"> 
                     </div>
                  </div>
                      <%
					}
					rs.close();
					} catch (SQLException | IOException e) {
					System.out.println(e.getMessage());
					}
					%>
                  <!---->
               </div>
               <div class="mc_combat">
                 <%
				Class.forName(Connector.drv);
				try (Connection conn = Connector.getConnection();) {
					Statement stmt = conn.createStatement();
					
					ResultSet rs = stmt.executeQuery("select * from THEME_CHARACTER where themeID=1 and (characterID=0 or characterID=7 or characterID=6);");
					int countChars = 0;
					while (rs.next()) {
						String characterID = rs.getString("characterID");
						int count = 0;
						int count_my = 0;
						
				%>
                  <div class="mc_char_0<%=countChars%>">
                  	
                     <div class="mc_char_section">
                        <div class="mc_char_section_perg"></div>
                        <div class="mask">
                           <div class="mc_char_section_skill opp_turn" style="display:none;">
                              <div class="mc_char_section_selected"></div>
                              <div class="mc_char_section_skills">
                              
                        <%  
                        ResultSet abilities = conn.createStatement().executeQuery("select * from ABILITY where characterID="+characterID+";");
					    while (abilities.next()) {
									
							String abilityID = abilities.getString("abilityID");
							 %>
                                 <div class="skillimg<%=count%>">
                                    <img src="ViewAbility?id=<%=abilityID %>" class="disabled"> 
                                 </div>
  
                                  <%
                                  count++;
					   }
					   abilities.close();
                                  %>
                              </div>
                           </div>
                           
                           <div class="mc_char_section_skill my_turn">
                              <div class="mc_char_section_selected"></div>
                              <div class="mc_char_section_skills">
                              
                        <%  
                        ResultSet abilities_my = conn.createStatement().executeQuery("select * from ABILITY where characterID="+characterID+";");
					    while (abilities_my.next()) {
									
							String abilityID_my = abilities_my.getString("abilityID");
							 %>
                                 <div class="skillimg<%=count_my%>">
                                    <img src="ViewAbility?id=<%=abilityID_my %>" class="disabled"> 
                                 </div>
  
                                  <%
                                  count_my++;
					   }
					   abilities_my.close();
                                  %>
                              </div>
                           </div>
                        </div>
                 
                     </div>
                     
                     
                     
                     
                     <div class="mc_char_card">
                        <div class="mc_char_card_rank2  ">
                           <img src="https://naruto-arena.net/images/ranks/10v2.png">
                        </div>
                        <div class="mc_char_card_avatar  ">
                           <img class="abs " src="https://naruto-arena.net/images/dead.png">
                           <img class="abs" id="dead_00" src="https://media.discordapp.net/attachments/673748029052026882/746373202699092068/7fu7VwyukhwMQabyDOKgkQ7QcvDfkHFU2PGdKIuFwckB1OsGbUpnYHj7hZ1h8pE7DFsevGIQERJmUBOXZxBgZ2JQ5gbN1PxmeP31.png">
                        </div>
                        <div class="mc_char_card_rank ">
                           <img src="https://naruto-arena.net/images/ranks/10.png">
                        </div>
                     </div>
                    
                     <div class="mc_char_card_lifebar">
                        <div id="bar_0<%=countChars%>" style=" background-color: #3BDF3F; width: 100%"></div>
                        <div id="bar_text_0<%=countChars%>" class="mc_char_card_lifetext">
                           100/100
                        </div>
                     </div>
                     <div>
                        <div class="mc_char_card_avatar   pulse_avt">
                           <img style="border: none" id="dead_00" src="ViewCharacter?id=<%=characterID %>">
                        </div>
                     </div>
                     <div class="effects"></div>
                     
                  </div>
                  
                    <%
                    countChars++; 
					}
					rs.close();
					} catch (SQLException | IOException e) {
					System.out.println(e.getMessage());
					}
					%>
                  
                  
                <%
				Class.forName(Connector.drv);
				try (Connection conn = Connector.getConnection();) {
					Statement stmt = conn.createStatement();
					
					ResultSet rs = stmt.executeQuery("select * from THEME_CHARACTER where themeID=1 and (characterID=1 or characterID=4 or characterID=0);");
					int countChars = 0;
					while (rs.next()) {
						String characterID = rs.getString("characterID");
						int count = 0;
						
				%>
                  <div class="mc_char_1<%=countChars%>">
                  	<%countChars++; %>
                     <div class="mc_char_card">
                        <div class="mc_char_card_rank2 revert rankenemy">
                           <img src="https://naruto-arena.net/images/ranks/9v2.png">
                        </div>
                        <div class="mc_char_card_avatar revert mc_char_card_avatar_en">
                           <img class="abs revert" src="https://naruto-arena.net/images/dead.png">
                           <img class="abs" id="dead_10" src="https://i.imgur.com/ZoEQafG.jpg">
                        </div>
                        <div class="mc_char_card_rank revert">
                           <img src="https://naruto-arena.net/images/ranks/9.png">
                        </div>
                     </div>
                     <!----> 
                     <div class="mc_char_card_lifebar en">
                        <div id="bar_10" style=" background-color: #3BDF3F; width: 100%"></div>
                        <div id="bar_text_10" class="mc_char_card_lifetext">
                           100/100
                        </div>
                     </div>
                     <div class="revert pulseenemy">
                        <div class="mc_char_card_avatar   pulse_avt">
                           <img style="border: none" id="dead_10" src="ViewCharacter?id=<%=characterID %>">
                        </div>
                     </div>
                     <div class="effects1"></div>
                  </div>
                    <%
                    
					}
					rs.close();
					} catch (SQLException | IOException e) {
					System.out.println(e.getMessage());
					}
					%>
                 
                  </div>
               </div>
               <div class="mc_footer">
               <%
					Class.forName(Connector.drv);
					try (Connection conn = Connector.getConnection();) {
						Statement stmt = conn.createStatement();
						
						ResultSet player = stmt.executeQuery("select * from USERS where userID=0 or userID=1;");
						
						while (player.next()) {
							String userID = player.getString("userID");
							String username = player.getString("username");
							String xp = player.getString("xp");
							String wins = player.getString("nWins");
							String losses = player.getString("nLosses");
							String streak = player.getString("streak");
					%>
					<div class="mc_info" id="player<%=userID %>" style="display:none;">
					 
                     <div class="mc_info_avatar">
                        <img src="ViewAvatar?id=<%=userID%>"> 
                     </div>
                     <
                     <div class="mc_info_name"><%=username %></div>
                     <div class="mc_info_desc">
                     Cabin Boy<br>
                     Level: <%=UserInfo.getLevel(xp) %><br>
                     Ladderrank: #3<br>
                     Ratio: <%=wins %>-<%=losses %> (+<%=streak %>)</div>
                     <div class="mc_info_team" id="opponentTeam" style="display:none;">
                        <div class="mc_info_team1"><img src="ViewCharacter?id=0"></div>
                        <div class="mc_info_team2"><img src="ViewCharacter?id=1"></div>
                        <div class="mc_info_team3"><img src="ViewCharacter?id=4"></div>
                     </div>
                     <div class="mc_info_team" id="myTeam" style="display:none;">
                        <div class="mc_info_team1"><img src="ViewCharacter?id=0"></div>
                        <div class="mc_info_team2"><img src="ViewCharacter?id=6"></div>
                        <div class="mc_info_team3"><img src="ViewCharacter?id=7"></div>
                     </div>
                  </div>
                  <%
					}
					player.close();
					} catch (SQLException | IOException e) {
					System.out.println(e.getMessage());
					}	
                  %>
                  
                  
                  <div class="mc_menu">
                     <div class="mc_surrender"></div>
                     <div class="mc_chat"></div>
                     <div class="mc_volume"></div>
                     <div class="mc_render" style="background-image: url(battle/bottomrender.png);"></div>
                  </div>
               </div>
               
            </div>
            <!-- <div id="cursor" style="opacity: 1; left: 616px; top: 324px; background-image: url(&quot;../images/kunai.png&quot;);"></div>
            <div id="shuri" style="opacity: 0; left: 616px; top: 324px;"></div>-->
         </div>
    
<script  type="text/javascript">
function functionTest() {
	alert("button was clicked");
}

function displayNones() {
	document.getElementById("myTeam").style.display="block";
	document.getElementById("opponentTeam").style.display="block";
	
	var bothPlayers = document.getElementsByClassName("mc_info");
		
	for (var i = 0; i < bothPlayers.length; i++) {
		bothPlayers[i].style.display="none";
	}
}

function playerFooterInfo(my_opp, id) {
	console.log("yo");
	displayNones();
	
	document.getElementById("player"+id).style.display="block";
	
	if (my_opp=="my") {
		document.getElementById("myTeam").style.display="block";
	} 
	else if (my_opp=="opp") {
		document.getElementById("opponentTeam").style.display="block";
	}
	
}

function changeTurnTo(turn) {

	var opp = document.getElementsByClassName ("opp_turn");
	var my = document.getElementsByClassName ("my_turn");
	
	if (turn=="my") {
		
		for (var i = 0; i < opp.length; i++) {
			opp[i].style.display="none";
		}
		for (var i = 0; i < my.length; i++) {
			my[i].style.display="block";
		}
	}
	else if (turn=="opp") {

		for (var i = 0; i < opp.length; i++) {
			opp[i].style.display="block";
		}
		for (var i = 0; i < my.length; i++) {
			my[i].style.display="none";
		}
	}
}

</script>
    
</body>
</html>

