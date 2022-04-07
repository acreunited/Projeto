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
            
			<div class="topo">
				<div class="conteudo">
				   <div class="nome">
				      ABURAME SHINO
				   </div>
				   <img src="https://i.imgur.com/uaMaL3v.jpg" class="foto borda"> 
				   <div class="nomeskill">
				      ABURAME SHINO
				   </div>
				   <div class="nomeEnergy">
				      ENERGY:
				   </div>
				   <div class="desc" id="scroll">
				      A GENIN FROM TEAM 8, SHINO IS THE SUCCESSOR OF THE ABURAME CLAN, AND A VERY RESERVED AND TACTICAL FIGHTER. USING THE BUGS THAT LIVE INSIDE HIS BODY, SHINO IS ABLE TO LEECH THE CHAKRA OFF HIS ENEMIES, OR PROTECT HIS ENTIRE TEAM.
				   </div>
				   <div class="cooldown">
				   		Cooldown: 
				   </div>
				   <!---->
				</div>
			    <img src="https://i.imgur.com/uaMaL3v.jpg" class="fotoskill borda"> 
			    <nav>
			       <ul class="listaskills">
			          <li><img src="https://i.imgur.com/jEnjIRA.jpg" class="fotolista borda"></li>
			          <li><img src="https://i.imgur.com/oEjXE4N.jpg" class="fotolista borda"></li>
			          <li><img src="https://i.imgur.com/ZnI4UeN.jpg" class="fotolista borda"></li>
			          <li><img src="https://i.imgur.com/xmnDpJO.jpg" class="fotolista borda"></li>
			       </ul>
			    </nav>
			</div>
             
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
						
						ResultSet rs = stmt.executeQuery("select characterID from THEME_CHARACTER where themeID=1 LIMIT 12;");
						
						while (rs.next()) {
							
							String characterID = rs.getString("characterID");							
					%>
                        <li class="personagem_fundo borda"><img src="ViewCharacter?id=<%=characterID %>" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="ViewCharacter?id=<%=characterID %>" class="personagem"></li>
                        
                       <!--  <li class="personagem_fundo borda"><img src="https://i.imgur.com/PnQ7L54.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="https://i.imgur.com/1cTOEtq.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="https://i.imgur.com/wfrSWxS.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="https://i.imgur.com/uaMaL3v.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="https://i.imgur.com/7uh5923.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="https://i.imgur.com/18BCDpO.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="https://i.imgur.com/C4mQVyG.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="https://i.imgur.com/6Qrq4yN.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="https://i.imgur.com/LUA3QK2.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="https://i.imgur.com/8hnWrsS.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="https://i.imgur.com/SnXl3u9.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="https://i.imgur.com/Q7n6CS2.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="https://i.imgur.com/SYBuYD8.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="https://i.imgur.com/ytBdDmF.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="https://i.imgur.com/gDSGgOc.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="https://i.imgur.com/DbocPAq.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img src="https://i.imgur.com/vo5hkwb.jpg" class="personagem"></li> -->
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
            <!---->
            <!---->
        </div>
     
    </div>
</div>



</body>
</html>
