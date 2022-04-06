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
<%@page import="users.Login"%>
<%@page import="users.UserInfo"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Anime-Arena">
    <meta name="author" content="Pedro Dias">

    <title>Anime-Arena</title>

    <!-- Custom fonts for this template-->
    <link href="extras/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/main.css" rel="stylesheet">
    <link href="css/responsive.css" rel="stylesheet">
    <link href="css/ladderRank.css" rel="stylesheet">
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" type="text/javascript"></script>
    

</head>

<body id="page-top">

<div id="app" class="central">
    <div id="root">
        <div class="mc_custom">
            <img src="https://media.discordapp.net/attachments/829102407375388752/875174799096299530/blackclover.png" style="width: 770px; height: 560px;">
            <!---->
            <div class="menu">
                <ul >
                    <li><img src="https://naruto-arena.net/images/selection/logout.png"></li>
                    <li><img src="https://naruto-arena.net/images/selection/ladder.png"></li>
                    <li style="margin-left: 3px;"><img src="https://naruto-arena.net/images/selection/quick.png"></li>
                    <li style="margin-left: 2px;"><img src="https://naruto-arena.net/images/selection/private.png"></li>
                </ul>
            </div>
            <div >
                <div class="rodape">
                    <ul >
                        <li class="personagem_fundo borda"><img  src="https://i.imgur.com/mJ505HX.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img  src="https://i.imgur.com/PnQ7L54.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img  src="https://i.imgur.com/1cTOEtq.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img  src="https://i.imgur.com/wfrSWxS.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img  src="https://i.imgur.com/uaMaL3v.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img  src="https://i.imgur.com/7uh5923.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img  src="https://i.imgur.com/18BCDpO.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img  src="https://i.imgur.com/C4mQVyG.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img  src="https://i.imgur.com/6Qrq4yN.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img  src="https://i.imgur.com/LUA3QK2.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img  src="https://i.imgur.com/8hnWrsS.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img  src="https://i.imgur.com/SnXl3u9.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img  src="https://i.imgur.com/Q7n6CS2.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img  src="https://i.imgur.com/SYBuYD8.jpg" class="personagem"></li>
                        <li class="personagem_fundo borda"><img  src="https://i.imgur.com/ytBdDmF.jpg" class="personagem"></li>
                        <li  class="personagem_fundo borda"><img  src="https://i.imgur.com/gDSGgOc.jpg" class="personagem"></li>
                        <li  class="personagem_fundo borda"><img  src="https://i.imgur.com/DbocPAq.jpg" class="personagem"></li>
                        <li  class="personagem_fundo borda"><img  src="https://i.imgur.com/vo5hkwb.jpg" class="personagem"></li>
                        <li  class="personagem_fundo borda"><img  src="https://i.imgur.com/mlZ3sQZ.jpg" class="personagem"></li>
                        <li  class="personagem_fundo borda"><img  src="https://i.imgur.com/EJ6aPHZ.jpg" class="personagem"></li>
                        <li  class="personagem_fundo borda"><img  src="https://i.imgur.com/aL3HebP.jpg" class="personagem"></li>
                    </ul>
                    <div  class="perfil"><img  src="https://acreunited.github.io/avys/Misc/71.png" class="avatar borda"><img  class="mold">
                        <div  class="texto">KITANA
                            <br>KAGE
                            <br>CLAN: ACR
                            <br>LEVEL: 52 ( 53576 XP)
                            <br>LADDERRANK: 33
                            <br>RATIO: 2345 - 614 ( +1)</div>
                    </div>
                    <div  class="selecionados">
                        <div  class="selectedsnum">
                            <div >1</div>
                            <div >2</div>
                            <div >3</div>
                        </div>
                        <div  class="containers">
                            <div  class="items" style="background-image: url(&quot;https://media.discordapp.net/attachments/673748029052026882/746373202699092068/7fu7VwyukhwMQabyDOKgkQ7QcvDfkHFU2PGdKIuFwckB1OsGbUpnYHj7hZ1h8pE7DFsevGIQERJmUBOXZxBgZ2JQ5gbN1PxmeP31.png&quot;);"></div>
                            <div  class="items" style="background-image: url(&quot;https://i.imgur.com/h033FpA.png&quot;);"></div>
                            <div  class="items" style="background-image: url(&quot;https://media.discordapp.net/attachments/673748029052026882/785505521607901184/unknown.png&quot;);"></div>
                        </div>
                    </div>
                    <div  class="texto_selecionados">YOU ARE READY TO START A GAME</div>
                </div>
            </div>
            <div  class="pergrodape"></div>
            <div  class="arrow_left"></div>
            <div  class="arrow_right"></div>
            <!---->
            <!---->
            
        </div>
        <div  id="cursor" style="opacity: 1; left: 914px; top: 169px; background-image: url(&quot;../images/kunai.png&quot;);"></div>
        <div  id="shuri" style="opacity: 0; left: 914px; top: 169px;"></div>
    </div>
</div>

    <!-- Bootstrap core JavaScript-->
    <script src="js/jquery.js"></script>
    <script src="extras/bootstrap/js/bootstrap.bundle.min.js"></script>

    <script src="js/jquery.easing.js"></script>

    <script src="js/interface.js"></script>

  



</body>

</html>