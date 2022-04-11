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
                  <div class="mc_play1">
                     <div class="mc_username1">
                        Kitana
                     </div>
                     <div class="mc_userrank1">
                        KAGE
                     </div>
                     <div class="mc_avatar1">
                        <img src="https://acreunited.github.io/avys/Misc/71.png"> <!---->
                     </div>
                  </div>
                  <!----> 
                  <div class="mc_control">
                     <div class="mc_bar_system">
                        <div class="mc_bar_back">
                           <div class="mc_bar_fill" style="width: 133.7px;"></div>
                        </div>
                        <div class="mc_bar_ready">
                           Opponent Turn...
                        </div>
                     </div>
                     <!---->
                  </div>
                  <div class="mc_play2">
                     <div class="mc_username2">
                        DRAVEN
                     </div>
                     <div class="mc_userrank2">
                        AKATSUKI
                     </div>
                     <div class="mc_avatar2">
                        <img src="https://acreunited.github.io/avys/Misc/205.png"> <!---->
                     </div>
                  </div>
                  <!---->
               </div>
               <div class="mc_combat">
                  <div class="mc_char_00">
                     <div class="mc_char_section">
                        <div class="mc_char_section_perg"></div>
                        <div class="mask">
                           <div class="mc_char_section_skill opp_turn">
                              <div class="mc_char_section_selected"></div>
                              <div class="mc_char_section_skills">
                                 <div class="skillimg0">
                                    <img src="https://media.discordapp.net/attachments/673748029052026882/746373250761883689/rlYKGQAAAABJRU5ErkJggg.png" class="disabled"> <!---->
                                 </div>
                                 <div class="skillimg1">
                                    <img src="https://media.discordapp.net/attachments/673748029052026882/746373581306593330/x8aLvM7tpK1QAAAABJRU5ErkJggg.png" class="disabled"> <!---->
                                 </div>
                                 <div class="skillimg2">
                                    <img src="https://media.discordapp.net/attachments/673748029052026882/746373331644711013/x9l94i9GinbigAAAABJRU5ErkJggg.png" class="disabled"> <!---->
                                 </div>
                                 <div class="skillimg3">
                                    <img src="https://media.discordapp.net/attachments/673748029052026882/746373665003667558/AFlWdwqMN4WuAAAAAElFTkSuQmCC.png" class="disabled"> <!---->
                                 </div>
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
                     <!----> 
                     <div class="mc_char_card_lifebar">
                        <div id="bar_00" style=" background-color: #3BDF3F; width: 100%"></div>
                        <div id="bar_text_00" class="mc_char_card_lifetext">
                           100/100
                        </div>
                     </div>
                     <div data-v-6b7c98d2="">
                        <div class="mc_char_card_avatar   pulse_avt">
                           <img style="border: none" id="dead_00" src="https://media.discordapp.net/attachments/673748029052026882/746373202699092068/7fu7VwyukhwMQabyDOKgkQ7QcvDfkHFU2PGdKIuFwckB1OsGbUpnYHj7hZ1h8pE7DFsevGIQERJmUBOXZxBgZ2JQ5gbN1PxmeP31.png">
                        </div>
                     </div>
                     <div class="effects"></div>
                  </div>
                  <div class="mc_char_01">
                     <div class="mc_char_section">
                        <div class="mc_char_section_perg"></div>
                        <div class="mask">
                           <div class="mc_char_section_skill opp_turn">
                              <div class="mc_char_section_selected"></div>
                              <div class="mc_char_section_skills">
                                 <div class="skillimg0">
                                    <img src="https://i.imgur.com/pp0GHJx.png" class="disabled"> <!---->
                                 </div>
                                 <div class="skillimg1">
                                    <img src="https://i.imgur.com/mkwy0lU.png" class="disabled"> <!---->
                                 </div>
                                 <div class="skillimg2">
                                    <img src="https://i.imgur.com/n9LppBJ.png" class="disabled"> <!---->
                                 </div>
                                 <div class="skillimg3">
                                    <img src="https://i.imgur.com/4u0sToA.png" class="disabled"> <!---->
                                 </div>
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
                           <img class="abs" id="dead_01" src="https://i.imgur.com/h033FpA.png">
                        </div>
                        <div class="mc_char_card_rank ">
                           <img src="https://naruto-arena.net/images/ranks/10.png">
                        </div>
                     </div>
                     <!----> 
                     <div class="mc_char_card_lifebar">
                        <div id="bar_01" style=" background-color: #3BDF3F; width: 100%"></div>
                        <div id="bar_text_01" class="mc_char_card_lifetext">
                           100/100
                        </div>
                     </div>
                     <div data-v-6b7c98d2="">
                        <div class="mc_char_card_avatar   pulse_avt">
                           <img style="border: none" id="dead_01" src="https://i.imgur.com/h033FpA.png">
                        </div>
                     </div>
                     <div class="effects"></div>
                  </div>
                  <div class="mc_char_02">
                     <div class="mc_char_section">
                        <div class="mc_char_section_perg"></div>
                        <div class="mask">
                           <div class="mc_char_section_skill opp_turn">
                              <div class="mc_char_section_selected"></div>
                              <div class="mc_char_section_skills">
                                 <div class="skillimg0">
                                    <img src="https://media.discordapp.net/attachments/673748029052026882/785505573700763698/unknown.png" class="disabled"> <!---->
                                 </div>
                                 <div class="skillimg1">
                                    <img src="https://media.discordapp.net/attachments/673748029052026882/785505640654700554/unknown.png" class="disabled"> <!---->
                                 </div>
                                 <div class="skillimg2">
                                    <img src="https://media.discordapp.net/attachments/673748029052026882/785505690227179540/unknown.png" class="disabled"> <!---->
                                 </div>
                                 <div class="skillimg3">
                                    <img src="https://media.discordapp.net/attachments/673748029052026882/785505741036322836/unknown.png" class="disabled"> <!---->
                                 </div>
                              </div>
                           </div>
                        </div>
                     </div>
                     <div class="mc_char_card">
                        <div class="mc_char_card_rank2">
                           <img src="https://naruto-arena.net/images/ranks/10v2.png">
                        </div>
                        <div class="mc_char_card_avatar  ">
                           <img class="abs " src="https://naruto-arena.net/images/dead.png">
                           <img class="abs" id="dead_02" src="https://media.discordapp.net/attachments/673748029052026882/785505521607901184/unknown.png">
                        </div>
                        <div class="mc_char_card_rank ">
                           <img src="https://naruto-arena.net/images/ranks/10.png">
                        </div>
                     </div>
                     <!----> 
                     <div class="mc_char_card_lifebar">
                        <div id="bar_02" style=" background-color: #3BDF3F; width: 100%"></div>
                        <div id="bar_text_02" class="mc_char_card_lifetext">
                           100/100
                        </div>
                     </div>
                     <div data-v-6b7c98d2="">
                        <div class="mc_char_card_avatar   pulse_avt">
                           <img style="border: none" id="dead_02" src="https://media.discordapp.net/attachments/673748029052026882/785505521607901184/unknown.png">
                        </div>
                     </div>
                     <div class="effects"></div>
                  </div>
                  <div class="mc_char_10">
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
                           <img style="border: none" id="dead_10" src="https://i.imgur.com/ZoEQafG.jpg">
                        </div>
                     </div>
                     <div class="effects1"></div>
                  </div>
                  <div class="mc_char_11">
                     <div class="mc_char_card">
                        <div class="mc_char_card_rank2 revert rankenemy">
                           <img src="https://naruto-arena.net/images/ranks/9v2.png">
                        </div>
                        <div class="mc_char_card_avatar revert mc_char_card_avatar_en">
                           <img class="abs revert" src="https://naruto-arena.net/images/dead.png">
                           <img class="abs" id="dead_11" src="https://media.discordapp.net/attachments/673748029052026882/847100835963994153/lkZT91h.png">
                        </div>
                        <div class="mc_char_card_rank revert">
                           <img src="https://naruto-arena.net/images/ranks/9.png">
                        </div>
                     </div>
                     <!----> 
                     <div class="mc_char_card_lifebar en">
                        <div id="bar_11" style=" background-color: #3BDF3F; width: 100%"></div>
                        <div id="bar_text_11" class="mc_char_card_lifetext">
                           100/100
                        </div>
                     </div>
                     <div class="revert pulseenemy">
                        <div class="mc_char_card_avatar   pulse_avt">
                           <img style="border: none" id="dead_11" src="https://media.discordapp.net/attachments/673748029052026882/847100835963994153/lkZT91h.png">
                        </div>
                     </div>
                     <div class="effects1"></div>
                  </div>
                  <div class="mc_char_12">
                     <div class="mc_char_card">
                        <div class="mc_char_card_rank2 revert rankenemy">
                           <img src="https://naruto-arena.net/images/ranks/9v2.png" >
                        </div>
                        <div class="mc_char_card_avatar revert mc_char_card_avatar_en">
                           <img class="abs revert" src="https://naruto-arena.net/images/dead.png">
                           <img class="abs" id="dead_12" src="https://media.discordapp.net/attachments/673748029052026882/759177784165007380/vXA6GPg.png">
                        </div>
                        <div class="mc_char_card_rank revert">
                           <img src="https://naruto-arena.net/images/ranks/9.png">
                        </div>
                     </div>
                     <!----> 
                     <div class="mc_char_card_lifebar en">
                        <div id="bar_12" style=" background-color: #3BDF3F; width: 100%"></div>
                        <div id="bar_text_12" class="mc_char_card_lifetext">
                           100/100
                        </div>
                     </div>
                     <div class="revert pulseenemy">
                        <div class="mc_char_card_avatar   pulse_avt">
                           <img style="border: none" id="dead_12" src="https://media.discordapp.net/attachments/673748029052026882/759177784165007380/vXA6GPg.png">
                        </div>
                     </div>
                     <div class="effects1"></div>
                  </div>
               </div>
               <div class="mc_footer">
                  <div class="mc_info">
                     <div class="mc_info_avatar">
                        <img src="https://acreunited.github.io/avys/Misc/205.png"> <!---->
                     </div>
                     <!----> 
                     <div class="mc_info_name">BEEPBOOP</div>
                     <div class="mc_info_desc" >
                     Rookie<br>
                     Clan: AC-R<br>
                     Level: 41<br>
                     Ladderrank: #2<br>
                     Ratio: 126-18 (+1)</div>
                     <div class="mc_info_team">
                        <div class="mc_info_team1"><img src="https://i.imgur.com/ZoEQafG.jpg"></div>
                        <div class="mc_info_team2"><img src="https://media.discordapp.net/attachments/673748029052026882/847100835963994153/lkZT91h.png"></div>
                        <div class="mc_info_team3"><img src="https://media.discordapp.net/attachments/673748029052026882/759177784165007380/vXA6GPg.png"></div>
                     </div>
                     <!----> <!----> <!---->
                  </div>
                  <div class="mc_menu">
                     <div class="mc_surrender"></div>
                     <div class="mc_chat"></div>
                     <div class="mc_volume"></div>
                     <div class="mc_render" style="background-image: url(battle/bottomrender.png);"></div>
                  </div>
               </div>
               <!----> <!---->
            </div>
            <!-- <div id="cursor" style="opacity: 1; left: 616px; top: 324px; background-image: url(&quot;../images/kunai.png&quot;);"></div>
            <div id="shuri" style="opacity: 0; left: 616px; top: 324px;"></div>-->
         </div>
      </div>

    
</body>
</html>

