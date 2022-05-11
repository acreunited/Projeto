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
<%@page import="game.InGame"%>
<%@page import="mechanics.Character"%>



<html lang="en" id="move">

<head>

  <meta charset="UTF-8">
  <meta name="viewport" content="minimum-scale=0.5">

  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Game Selection</title>
  
 <link href="css/ingameBattle.css" rel="stylesheet">
 
 <script type="text/javascript" src="js/battle.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


</head>
<body>


<script>

window.onload = function() {
	var turn = <%=session.getAttribute("turn")%>;
	defineTurns(turn);
};


</script>
<div id="app" class="central">
         <div id="root">
            <div class="mc_custom">
               
               <img src="battle/thebackground.png" style="width: 770px; height: 560px;">
               <div class="mc_top">
                 <%
                 	Class.forName(Connector.drv);
                 		try (Connection conn = Connector.getConnection();) {
                 			Statement stmt = conn.createStatement();
                 			
                 			ResultSet rs = stmt.executeQuery("select * from USERS where userID="+session.getAttribute("this_id")+";");
                 			
                 			if (rs.next()) {
                 				String userID = rs.getString("userID");
                 				String username = rs.getString("username");
                 %>
                  <div class="mc_play1">
                     <div class="mc_username1">
                        <%=username%>
                     </div>
                     <div class="mc_userrank1">
                        CABIN BOY
                     </div>
                     <div class="mc_avatar1">
                     
                       <img src="ViewAvatar?id=<%=userID%>" onclick="playerFooterInfo('my')" alt="" > 
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
                        <div class="mc_bar_ready opp_text" id="oppTurnDisable">
                           Opponent Turn...
                        </div>
                        <div class="mc_bar_ready my_turn" id="passTurn" onclick="endTurn()">
                           Press To End Turn
                        </div>
						<div class="mc_bar_ready" id="winnerTurn" style="display: none;">
                           WINNER
                        </div>
                        <div class="mc_bar_ready" id="loserTurn" style="display: none;">
                           LOSER
                        </div>
				
                        <div class="mc_energy_system my_turn">
			                <div class="mc_energy_bar"></div>
			                <div class="mc_energy_txt" id="natures">
			                  <strong class="energy0">x<%=session.getAttribute("taijutsu") %></strong>
			                  <strong class="energy1">x<%=session.getAttribute("heart") %> </strong>
			                  <strong class="energy2">x<%=session.getAttribute("energy") %></strong>
			                  <strong class="energy3">x<%=session.getAttribute("spirit") %></strong>
			                  <strong class="energy4">x<%=session.getAttribute("random") %></strong>
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
                			
                			ResultSet rs = stmt.executeQuery("select * from USERS where userID="+session.getAttribute("opp_id")+";");
                			
                			if (rs.next()) {
                				String userID = rs.getString("userID");
                				String username = rs.getString("username");
                %>
                  <div class="mc_play2">
                     <div class="mc_username2">
                        <%=username%>
                     </div>
                     <div class="mc_userrank2">
                        CABIN BOY
                     </div>
                     <div class="mc_avatar2">
                        <a><img src="ViewAvatar?id=<%=userID%>" onclick="playerFooterInfo('opp')"> </a>
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
                 			
                 			
                 			
                 			ResultSet rs = stmt.executeQuery(
                 					"select * from THEME_CHARACTER where themeID=1 and (characterID="+session.getAttribute("this_char1")+ 
                 					" or characterID="+ session.getAttribute("this_char2")+" or characterID="+session.getAttribute("this_char3")+")"+
                 					"order by case when characterID="+session.getAttribute("this_char1")+" then 1 when characterID="+session.getAttribute("this_char2")+" then 2 when characterID="+session.getAttribute("this_char3")+" then 3 else null end;");
                 			int countChars = 0;
                 			while (rs.next()) {
                 				String characterID = rs.getString("characterID");
                 				int count = 0;
                 				int count_my = 0;
                 %>
                  <div class="mc_char_0<%=countChars%>" id="ally<%=countChars%>">
                  	
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
                                    <a onclick="abilityFooterInfo(<%=abilityID%>)"><img src="ViewAbility?id=<%=abilityID%>" class="disabled" > </a>
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
                                    <a onclick="abilityClick(<%=abilityID_my%>, <%=countChars%>, <%=count_my%>)"><img src="ViewAbility?id=<%=abilityID_my%>"></a>
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
                    
                        <div class="mc_char_card_rank ">
                           <a onclick="characterFooterInfo(<%=characterID%>, 'ally', <%=countChars%>)"><img src="https://naruto-arena.net/images/ranks/10.png"></a>
                        </div> 
                         <div class="mc_char_card_avatar  ">
                         	<img class="abs " src="https://naruto-arena.net/images/dead.png">
                         	<img class="abs" id="dead_0<%=countChars%>" src="ViewCharacter?id=<%=characterID%>">
                        </div>
                     </div>
                     <!-- <div class="choose"></div> -->
                    
                     <div class="mc_char_card_lifebar">
                        <div id="bar_0<%=countChars%>" style=" background-color: #3BDF3F; width: 100%"></div>
                        <div id="bar_text_0<%=countChars%>" class="mc_char_card_lifetext">
                           100/100
                        </div>
                     </div>
                   <!--  <div>
                        <div class="mc_char_card_avatar pulse_avt" >
                           <img style="border: none" id="dead_00" >
                        </div>
                     </div>-->
                     
                      <div class="effects" id="effectsAlly<%=countChars%>">
                     </div>
                     
                     <!-- TODO CRIAR MANUALMENTE -->
                   <!-- <div class="effects">
                     	<div class="effects_border0 zindex1">
                  			<img src="ViewAbility?id=id" onmouseover="seeActiveSkill(id)" onmouseleave="hideActiveSkill()">
                  			<span class="tooltiptext" id="tooltiptextid">
	                    		<span class="tooltiptextname">SPRINKLING NEEDLES</span>
	                    		<span class="tooltiptextdesc">This character will take 10 damage.</span>
	                    		<span class="tooltiptextduration">1 TURN LEFT</span>
                    		</span>
                   		</div>
                    </div>
                      -->
                  </div>
                  
                    <%
                     	countChars++; 
                     			}
                     			rs.close();
                     			} catch (SQLException | IOException e) {
                     			System.out.println(e.getMessage());
                     			}
                     			
                     		Class.forName(Connector.drv);
                     		try (Connection conn = Connector.getConnection();) {
                     			Statement stmt = conn.createStatement();
                     			
                     			ResultSet rs = stmt.executeQuery(
                     					"select * from THEME_CHARACTER where themeID=1 and (characterID="+session.getAttribute("opp_char1")+ 
                     					" or characterID="+ session.getAttribute("opp_char2")+" or characterID="+session.getAttribute("opp_char3")+")"+
                     					"order by case when characterID="+session.getAttribute("opp_char1")+" then 1 when characterID="+session.getAttribute("opp_char2")+" then 2 when characterID="+session.getAttribute("opp_char3")+" then 3 else null end;");
                     			int countChars = 0;
                     			while (rs.next()) {
                     				String characterID = rs.getString("characterID");
                     				int count = 0;
                                      %>
                  <div class="mc_char_1<%=countChars%>" id="enemy<%=countChars%>">
                  	<%
                  		countChars++;
                  	%>
                     <div class="mc_char_card">
                        <div class="mc_char_card_rank2 revert rankenemy">
                           <img src="https://naruto-arena.net/images/ranks/9v2.png">
                        </div>
                        <div class="mc_char_card_avatar revert mc_char_card_avatar_en">
                          <!--  <img class="abs revert" src="https://naruto-arena.net/images/dead.png"> -->
                           <img class="abs" id="dead_1<%=countChars%>" src="ViewCharacter?id=<%=characterID%>">
                        </div>
                        <div class="mc_char_card_rank revert">
                           <a onclick="characterFooterInfo(<%=characterID%>, 'enemy', <%=countChars%>)"><img src="https://naruto-arena.net/images/ranks/9.png"></a>
                        </div>
                     </div>
                     
                     <!--<div class="nochoose en2"></div>--> 
                     <div class="mc_char_card_lifebar en">
                        <div id="bar_10" style=" background-color: #3BDF3F; width: 100%"></div>
                        <div id="bar_text_10" class="mc_char_card_lifetext">
                           100/100
                        </div>
                     </div>
                     <!-- <div class="revert pulseenemy">
                        <div class="mc_char_card_avatar   pulse_avt">
                          <img style="border: none" id="dead_10" >
                        </div>
                     </div> -->
                     
                    <!-- TODO CRIAR MANUALMENTE --> 
                     <div class="effects1" id="effectsEnemy<%=countChars%>">
                     </div>
                    <!--  <div class="effects1">
                     	<div class="effects_border1 zindex1">
                  			<img src="ViewAbility?id=id" onmouseover="seeActiveSkillEnemy(id)" onmouseleave="hideActiveSkillEnemy()">
                  			<span class="tooltiptext1" id="tooltiptext1id">
	                    		<span class="tooltiptextname">SPRINKLING NEEDLES</span>
	                    		<span class="tooltiptextdesc">This character will take 10 damage.</span>
	                    		<span class="tooltiptextduration">1 TURN LEFT</span>
                    		</span>
                   		</div>
                    </div>
                     -->
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
               				
               				ResultSet player0 = stmt.executeQuery("select * from USERS where userID="+session.getAttribute("this_id")+";");
               				
               				if (player0.next()) {
               					String userID = player0.getString("userID");
               					String username = player0.getString("username");
               					String xp = player0.getString("xp");
               					String wins = player0.getString("nWins");
               					String losses = player0.getString("nLosses");
               					String streak = player0.getString("streak");
               %>
					<div class="mc_info" id="player0" style="display:none;">
					 
                     <div class="mc_info_avatar">
                        <img src="ViewAvatar?id=<%=userID%>"> 
                     </div>
                     
                     <div class="mc_info_name"><%=username%></div>
                     <div class="mc_info_desc">
                     Cabin Boy<br>
                     Level: <%=UserInfo.getLevel(xp)%><br>
                     Ladderrank: <br>
                     Ratio: <%=wins%>-<%=losses%> (+<%=streak%>)</div>
                     <div class="mc_info_team">
                        <div class="mc_info_team1"><img src="ViewCharacter?id=<%=session.getAttribute("this_char1")%>"></div>
                        <div class="mc_info_team2"><img src="ViewCharacter?id=<%=session.getAttribute("this_char2")%>"></div>
                        <div class="mc_info_team3"><img src="ViewCharacter?id=<%=session.getAttribute("this_char3")%>"></div>
                     </div>
                  </div>
                  <%
                  	}
                  			player0.close();
                  			} catch (SQLException | IOException e) {
                  			System.out.println(e.getMessage());
                  			}	
                                
                  			Class.forName(Connector.drv);
                  			try (Connection conn = Connector.getConnection();) {
                  				Statement stmt = conn.createStatement();
                  				
                  				ResultSet player1 = stmt.executeQuery("select * from USERS where userID="+session.getAttribute("opp_id")+";");
                  				
                  				if (player1.next()) {
                  					String userID = player1.getString("userID");
                  					String username = player1.getString("username");
                  					String xp = player1.getString("xp");
                  					String wins = player1.getString("nWins");
                  					String losses = player1.getString("nLosses");
                  					String streak = player1.getString("streak");
                  %>
					<div class="mc_info" id="player1" style="display:block;">
					 
                     <div class="mc_info_avatar">
                        <img src="ViewAvatar?id=<%=userID%>"> 
                     </div>
                     
                     <div class="mc_info_name"><%=username%></div>
                     <div class="mc_info_desc">
                     Cabin Boy<br>
                     Level: <%=UserInfo.getLevel(xp)%><br>
                     Ladderrank: <br>
                     Ratio: <%=wins%>-<%=losses%> (+<%=streak%>)</div>
                     <div class="mc_info_team">
                        <div class="mc_info_team1"><img src="ViewCharacter?id=<%=session.getAttribute("opp_char1")%>"></div>
                        <div class="mc_info_team2"><img src="ViewCharacter?id=<%=session.getAttribute("opp_char2")%>"></div>
                        <div class="mc_info_team3"><img src="ViewCharacter?id=<%=session.getAttribute("opp_char3")%>"></div>
                     </div>
                  </div>
                  <%
                  	}
                  			player1.close();
                  			} catch (SQLException | IOException e) {
                  			System.out.println(e.getMessage());
                  			}
                  %>
                  
                  
                    <%
                  	Class.forName(Connector.drv);
                  			try (Connection conn = Connector.getConnection();) {
                  				Statement stmt = conn.createStatement();
                  				
                  				ResultSet characters = stmt.executeQuery(
                  						"select * from THEME_CHARACTER where themeID=1 and (characterID="+session.getAttribute("this_char1")+ 
                  						" or characterID="+session.getAttribute("this_char2")+ " or characterID="+session.getAttribute("this_char3")+ 
                  						" or characterID="+session.getAttribute("opp_char1")+"  or characterID="+session.getAttribute("opp_char2")+ 
                  						"  or characterID="+session.getAttribute("opp_char3")+");");
                  				
                  				while (characters.next()) {
                  					String characterID = characters.getString("characterID");
                  					String nome = characters.getString("nome");
                  					String descricao = characters.getString("descricao");
                  %>
					<div class="mc_info" id="character<%=characterID%>" style="display:none;">
					 
                     <div class="mc_info_avatar">
                        <img src="ViewCharacter?id=<%=characterID%>"> 
                     </div>
                     
                     <div class="char_info_name"><%=nome%></div>
                     <div class="char_info_desc"><%=descricao%></div>

                   </div>
                  
                  		<%
                  			ResultSet abilities = conn.createStatement().executeQuery("select * from ABILITY where characterID="+characterID+";");
                  						   while (abilities.next()) {
                  							   String abilityID = abilities.getString("abilityID");
                  							   
                  							   ResultSet abilit = conn.createStatement().executeQuery("select * from THEME_ABILITY where themeID=1 and abilityID="+abilityID+";");
                  							   while (abilit.next()) {
                  								   String nome_ab = abilit.getString("nome");
                  								   String descricao_ab = abilit.getString("descricao");
                  		%>
								   <div class="mc_info" id="ability<%=abilityID%>" style="display:none;">
					 
				                     <div class="mc_info_avatar">
				                        <img src="ViewAbility?id=<%=abilityID%>"> 
				                     </div>
				                     
				                     <div class="ability_info_name"><%=nome_ab%></div>
				                     <div class="ability_info_desc"><%=descricao_ab%></div>
				
				                   </div>
								 <%
								 	}
								 					   abilit.close();
								 				   }
								                  		abilities.close();
								 			}
								 		characters.close();
								 			} catch (SQLException | IOException e) {
								 			System.out.println(e.getMessage());
								 			}
								 %>
                  
                  <div class="mc_menu">
                     <div class="mc_surrender" id="surrenderClick"></div>
                     <div class="mc_chat"></div>
                     <div class="mc_volume">
                     	<input type="range" min="1" max="100" value="50" class="slider">
                     </div>
                     <div class="mc_render" style="background-image: url(battle/bottomrender.png);"></div>
                  </div>
               </div>
               
				<div class="holder holdanime" id="askingSurrender" style="display: none;">
					<div class="txtsurrender">Are you sure you wish to surrender?</div> 
					<img class="surrenderimg"> 
					<div class="btncancel" id="surrenderCancel"></div>
					<div class="btnok" id="surrenderConfirm"></div> 
				</div>
				
				<%
				Class.forName(Connector.drv);
         		try (Connection conn = Connector.getConnection();) {
         			Statement stmt = conn.createStatement();
         			
				ResultSet vs = conn.createStatement().executeQuery("select username from USERS where userID="+session.getAttribute("opp_id"));
				   if (vs.next()) {
					   String opp_username = vs.getString("username");
					   
				%>
				<div class="endgame holdanime" id="winnerQuick" style="display: none;">
					<img src="battle/winner.png"> 
					<div class="endtitle">WINNER</div> 
					<div class="txtendgame">
					You have won a Quick Game against <%=opp_username %>.<br><br>
					Quick Games count for missions.<br>Quick games do not count as Ladder Matches.
					</div> 
					<div class="btncontinue" onclick="redirectSelection()">
						<span>CONTINUE</span>
					</div>
				</div> 
				
				<div class="endgame holdanime" id="loserQuick" style="display: none;">
					<img src="battle/loser.png"> 
					<div class="endtitle">LOSER</div> 
					<div class="txtendgame">
					You have lost a Quick Game against <%=opp_username %>.<br><br>
					Quick Games count for missions.<br>Quick games do not count as Ladder Matches.
					</div> 
					<div class="btncontinue" onclick="redirectSelection()">
						<span>CONTINUE</span>
					</div>
				</div>  
				<%
				   }
         		}
         		catch (SQLException | IOException e) {
		 			System.out.println(e.getMessage());
		 			}
				%>
				             
            </div>
            <!-- <div id="cursor" style="opacity: 1; left: 616px; top: 324px; background-image: url(&quot;../images/kunai.png&quot;);"></div>
            <div id="shuri" style="opacity: 0; left: 616px; top: 324px;"></div>-->
            
            
            
         </div>
    
<script>
$('#surrenderClick').click(function() {
	$('#askingSurrender').css("display", "block");
});
$('#surrenderCancel').click(function() {
	$('#askingSurrender').css("display", "none");
});
$('#surrenderConfirm').click(function() {
	loser();
});




</script>
    
</body>
</html>

