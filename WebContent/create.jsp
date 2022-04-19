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
<%@ page import="main.CreateCharacterMission"%>

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
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" type="text/javascript"></script>
    

</head>

<body id="page-top">



    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
                <!--<div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>-->
                <div class="sidebar-brand-text mx-3">Welcome!</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="#">
                    <i class="fab fa-discord"></i>
                    <span>Discord</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading --> 
            <div class="sidebar-heading">
                Interface
            </div>   

            <!-- Nav Item - Utilities Collapse Menu -->
             <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseMode"
                    aria-expanded="true" aria-controls="collapseMode">
                    <i class="fas fa-fw fa-user"></i>
                    <span>Language</span>
                </a>
                <div id="collapseMode" class="collapse" aria-labelledby="headingMode"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="#">English</a>
                        <a class="collapse-item" href="#">Portuguese</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>Themes</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="custom-select">
                        <select>
					    <option value="0">No Theme</option>
					    <option value="1">Default</option>
					  </select>
                    </div>
                </div>
            </li>

            

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Game
            </div>

            <li class="nav-item active">
                <a class="nav-link" href="#">
                    <i class="fas fa-fw fa-circle"></i>
                    <span>Start Playing</span></a>
            </li>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                    aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>Pages</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="#">Annoucements</a>
                        <a class="collapse-item" href="ladder.jsp">Rankings</a>
                        <a class="collapse-item" href="missions.jsp">Missions</a>
                        <a class="collapse-item" href="help.jsp">Help</a>

                        <div class="collapse-divider"></div>
                    </div>
                </div>
            </li>

            <li class="nav-item active">
                <a class="nav-link" href="create.jsp">
                    <span>Create Character</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

            

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column" >

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                
					<!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto" id="players">
                    
                     <%
					Class.forName(Connector.drv);
					try (Connection conn = Connector.getConnection();) {
						Statement stmt = conn.createStatement();
						
						ResultSet rs = stmt.executeQuery("select * from USERS where userID="+session.getAttribute("userID"));
						if (rs.next()) {
							String username = rs.getString("username");
							String userID = rs.getString("userID");
					%>
                    
                    <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=username %></span>
                                <img class="img-profile rounded-circle"
                                    src="ViewAvatar?id=<%=userID %>">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="ViewProfile?username=<%=username %>">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="settings.jsp">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="logout.jsp">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>
                        
                        <%
                        }
						rs.close();
						} catch (SQLException | IOException e) {
						System.out.println(e.getMessage());
						}
						%>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <div class="container-fluid">
                
                <form class="modal-content animate" method="post"
					enctype="multipart/form-data"
					action="${pageContext.request.contextPath}/CreateCharacterMission"
				>
		
					<div class="container">
		
						<div class="container">
							<label for="characterName">
								<b>Character Name</b>
							</label>
							<input type="text" placeholder="Character Name" name="characterName"
								pattern="[A-Za-z]{1,16}" required
							/>
						</div>
						<div class="container">
							<label for="characterDescription">
								<b>Character Description</b>
							</label>
							<textarea placeholder="Write Character Description (max 5000chars)" name="characterDescription"
								style="min-height: 200px; width: 100%;" maxlength="5000" required
							></textarea>
						</div>
		
						<div class="container">
							<label for="charPic">Character Picture</label>
							<input type="file" accept="image/*" name="charPic" required />
						</div>
						<br>
						<br>
						
						<%for (int i = 1; i <= 4; i++) { %>
						
						<div class="container">
							<label for="abilityName<%=i%>">
								<b>Ability<%=i%> Name</b>
							</label>
							<input type="text" placeholder="Ability Name" name="ability<%=i%>"
								pattern="[A-Za-z]{1,16}" required
							/>
						</div>
						
						<div class="container">
							<label for="ability<%=i%>Description">
								<b>Ability<%=i%> Description</b>
							</label>
							<textarea placeholder="Write Ability <%=i%> Description (max 5000chars)" name="ability<%=i%>Description"
								style="min-height: 200px; width: 100%;" maxlength="5000" required
							></textarea>
						</div>
						
						<div class="container">
							<label for="ability<%=i%>target">Who is the target of this ability?</label>
							<select name="ability<%=i%>target" id="ability<%=i%>target" required>
								<option value="self">Self</option>
								<option value="enemy">Enemy</option>
								<option value="ally">Ally</option>
							</select>
						</div>
						<div class="container">
							<label for="ability<%=i%>damage">Does this ability do damage?</label>
							<select name="ability<%=i%>damage" id="ability<%=i%>damage" required onchange="ability<%=i%>DoesDamage(this)">
								<option value="no">No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability<%=i%>DoesDamage" style="display: none;">
								<label for="ability<%=i%>damageNumber">Damage Value:</label>
								<input type="number" name="ability<%=i%>damageNumber" min="0" max="1000">
								<label for="ability<%=i%>damageDuration">Turn duration:</label>
								<input type="number" name="ability<%=i%>damageDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability<%=i%>increaseAbilityDamage">Ability damage increase per use (0 if none)</label>
							<input type="number" name="ability<%=i%>increaseAbilityDamage" min="0" max="1000" required>
							<br>
							<label for="ability<%=i%>increasePermanentDamage">Permanent Character increase damage (0 if none)</label>
							<input type="number" name="ability<%=i%>increasePermanentDamage" min="0" max="1000" required>
							<br>
							<label for="ability<%=i%>stun">Stun Duration (0 if none)</label>
							<input type="number" name="ability<%=i%>stun" min="0" max="1000" required>
							<br>
							<label for="ability<%=i%>beInvul">How many turns this ability makes character invulnerable (0 if none)</label>
							<input type="number" name="ability<%=i%>beInvul" min="0" max="1000" required>
							<br>
							<label for="ability<%=i%>ignoreInvul">Does this ability ignore invulnerability?</label>
							<select name="ability<%=i%>ignoreInvul" id="ability<%=i%>ignoreInvul" required>
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
							<br>
							<label for="ability<%=i%>destroyDD">Does this ability destroy DD?</label>
							<select name="ability<%=i%>destroyDD" id="ability<%=i%>destroyDD" required>
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
							<br>
						
						</div>
				
						<div class="container">
							<label for="ability<%=i%>removeNature">Does this ability remove Natures?</label>
							<select name="ability<%=i%>removeNature" id="ability<%=i%>removeNature" required onchange="ability<%=i%>removesNature(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability<%=i%>removesNature" style="display: none;">
								<label for="ability<%=i%>removesNatureNumber">How Many?:</label>
								<input type="number" name="ability<%=i%>removesNatureNumber" min="0" max="1000">
								<label for="ability<%=i%>removesNatureDuration">Turn duration:</label>
								<input type="number" name="ability<%=i%>removesNatureDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability<%=i%>gainNature">Does this ability gain Natures?</label>
							<select name="ability<%=i%>gainNature" id="ability<%=i%>gainNature" required onchange="ability<%=i%>gainsNature(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability<%=i%>gainsNature" style="display: none;">
								<label for="ability<%=i%>gainNatureNumber">How Many?:</label>
								<input type="number" name="ability<%=i%>gainNatureNumber" min="0" max="1000">
								<label for="ability<%=i%>gainNatureDuration">Turn duration:</label>
								<input type="number" name="ability<%=i%>gainNatureDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability<%=i%>gainHP">Does this ability gain Health?</label>
							<select name="ability<%=i%>gainHP" id="ability<%=i%>gainHP" required onchange="ability<%=i%>gainsHP(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability<%=i%>gainsHP" style="display: none;">
								<label for="ability<%=i%>gainHPNumber">How Many?:</label>
								<input type="number" name="ability<%=i%>gainHPNumber" min="0" max="1000">
								<label for="ability<%=i%>gainHPDuration">Turn duration:</label>
								<input type="number" name="ability<%=i%>gainHPDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability<%=i%>gainDD">Does this ability gain Destructible Defense?</label>
							<select name="ability<%=i%>gainDD" id="ability<%=i%>gainDD" required onchange="ability<%=i%>gainsDD(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability<%=i%>gainsDD" style="display: none;">
								<label for="ability<%=i%>gainDDNumber">How Many?:</label>
								<input type="number" name="ability<%=i%>gainDDNumber" min="0" max="1000">
								<label for="ability<%=i%>gainDDDuration">Turn duration:</label>
								<input type="number" name="ability<%=i%>gainDDDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability<%=i%>gainDR">Does this ability gain Damage Reduction?</label>
							<select name="ability<%=i%>gainDR" id="ability<%=i%>gainDR" required onchange="ability<%=i%>gainsDR(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability<%=i%>gainsDR" style="display: none;">
								<label for="ability<%=i%>gainDRNumber">How Many?:</label>
								<input type="number" name="ability<%=i%>gainDRNumber" min="0" max="1000">
								<label for="ability<%=i%>gainDRDuration">Turn duration:</label>
								<input type="number" name="ability<%=i%>gainDRDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability<%=i%>extraDmamagePerSelfHPLost">Does this ability deal aditional damage per self HP lost?</label>
							<select name="ability<%=i%>extraDmamagePerSelfHPLost" id="ability<%=i%>extraDmamagePerSelfHPLost" onchange="ability<%=i%>DoesExtraDmamagePerSelfHPLost(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability<%=i%>DoesExtraDmamagePerSelfHPLost" style="display: none;">
								<label for="ability<%=i%>extraDmamagePerSelfHPLostNumber">How much damage?:</label>
								<input type="number" name="ability<%=i%>extraDmamagePerSelfHPLostNumber" min="0" max="1000">
								<label for="ability<%=i%>extraDmamagePerSelfHPLostHP">How much HP lost?:</label>
								<input type="number" name="ability<%=i%>extraDmamagePerSelfHPLostHP" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability<%=i%>extraDmamagePerEnemyHPLost">Does this ability deal aditional damage per Enemy HP lost?</label>
							<select name="ability<%=i%>extraDmamagePerEnemyHPLost" id="ability<%=i%>extraDmamagePerEnemyHPLost" onchange="ability<%=i%>DoesExtraDmamagePerEnemyHPLost(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability<%=i%>DoesExtraDmamagePerEnemyHPLost" style="display: none;">
								<label for="ability<%=i%>extraDmamagePerEnemyHPLostNumber">How much damage?:</label>
								<input type="number" name="ability<%=i%>extraDmamagePerEnemyHPLostNumber" min="0" max="1000">
								<label for="ability<%=i%>extraDmamagePerEnemyHPLostHP">How much HP lost?:</label>
								<input type="number" name="ability<%=i%>extraDmamagePerEnemyHPLostHP" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						<div class="container">
							<label for="ability<%=i%>extraDamageTemporary">Does this ability increase Character damage temporarly?</label>
							<select name="ability<%=i%>extraDamageTemporary" id="ability<%=i%>extraDamageTemporary" onchange="ability<%=i%>DoesExtraDmamageTemporary(this)">
								<option value="no" >No</option>
								<option value="yes">Yes</option>
							</select>
	
							<div id="ability<%=i%>DoesExtraDamageTemporary" style="display: none;">
								<label for="ability<%=i%>extraDamageTemporaryNumber">How much damage?:</label>
								<input type="number" name="ability<%=i%>extraDamageTemporaryNumber" min="0" max="1000">
								<label for="ability<%=i%>extraDamageTemporaryDuration">Duration:</label>
								<input type="number" name="ability<%=i%>extraDamageTemporaryDuration" min="0" max="1000">
							</div>	
							<br>
						</div>
						
						
						<div class="container">
							<label for="ability<%=i%>taijutsu">Taijutsu (minimum 0):</label>
							<input type="number" name="ability<%=i%>taijutsu" min="0" max="100" required><br>
							<label for="ability<%=i%>heart">Heart (minimum 0):</label>
							<input type="number" name="ability<%=i%>heart" min="0" max="100" required><br>
							<label for="ability<%=i%>energy">Energy (minimum 0):</label>
							<input type="number" name="ability<%=i%>energy" min="0" max="100" required><br>
							<label for="ability<%=i%>spirit">Spirit (minimum 0):</label>
							<input type="number" name="ability<%=i%>spirit" min="0" max="100" required><br>
							<label for="ability<%=i%>random">Random (minimum 0):</label>
							<input type="number" name="ability<%=i%>random" min="0" max="100" required><br>
						</div>
						<div class="container">
							<label for="ability<%=i%>cd">Cooldown (minimum 0):</label>
							<input type="number" name="ability<%=i%>cd" min="0" max="100" required>
						</div>	
						<div class="container">
							<label for="ability<%=i%>image">Ability<%=i%> Picture</label>
							<input type="file" accept="image/*" name="ability<%=i%>image" required />
						</div>
						<br><br>
						<%} %>
						
		
						<div class="container">
							<label for="charAnime">Character Anime</label>
							<select name="charAnime" id="characterAnime">
								<option value="Bleach">Bleach</option>
								<option value="DemonSlayer">Demon Slayer</option>
								<option value="OnePunchMan">One Punch Man</option>
								<option value="HunterXHunter">Hunter X Hunter</option>
								<option value="SAO">Sword Art Online</option>
							</select>
						</div>
						
						<div class="container">
							<label for="defaultmission">Default or Mission Character ?</label>
							<select name="defaultmission" id="defaultORmission" onchange="isMission(this)">
								<option value="Default">Default</option>
								<option value="Mission">Mission</option>
							</select>
						</div>
						
						<div id="showMission" style="display:none">
							<div class="container">
								<label for="missionName">
									<b>Mission Name</b>
								</label>
								<input type="text" placeholder="Mission Name" name="missionName"/>
							</div>
							<div class="container">
								<label for="missionDescription">
									<b>Mission Description</b>
								</label>
								<textarea placeholder="Write Mission Description (max 5000chars)" name="missionDescription"
									style="min-height: 200px; width: 100%;" 
								></textarea>
							</div>
							
							<div class="container">
								<label for="minLevel">Required Level (minimum 1):</label>
								<input type="number" name="minLevel" min="1" max="1000">
							</div>	
							
							<div class="container">
								<label for="missionImage">Mission Picture</label>
								<input type="file" accept="image/*" name="missionImage" />
							</div>
						</div>
						<br>
		
						<button type="submit">Submit</button>
					</div>
				</form>
                
                </div>
            </div>
            <!-- End of Main Content -->



        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

<script>
function isMission(m) {
	
    if (m.value=="Mission") {
        document.getElementById("showMission").style.display="block";
    }
    else if (m.value=="Default") {
        document.getElementById("showMission").style.display="none";
    }
}

function ability1DoesDamage(m) {
	
	if (m.value=="yes") {
		document.getElementById("ability1DoesDamage").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability1DoesDamage").style.display="none";
	}
}
function ability1removesNature(m) {

	if (m.value=="yes") {
		document.getElementById("ability1removesNature").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability1removesNature").style.display="none";
	}
}

function ability1gainsNature(m) {

	if (m.value=="yes") {
		document.getElementById("ability1gainsNature").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability1gainsNature").style.display="none";
	}
}

function ability1gainsHP(m) {

	if (m.value=="yes") {
		document.getElementById("ability1gainsHP").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability1gainsHP").style.display="none";
	}
}
function ability1gainsDD(m) {

	if (m.value=="yes") {
		document.getElementById("ability1gainsDD").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability1gainsDD").style.display="none";
	}
}

function ability1gainsDR(m) {

	if (m.value=="yes") {
		document.getElementById("ability1gainsDR").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability1gainsDR").style.display="none";
	}
}
function ability1DoesExtraDmamagePerSelfHPLost(m) {

	if (m.value=="yes") {
		document.getElementById("ability1DoesExtraDmamagePerSelfHPLost").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability1DoesExtraDmamagePerSelfHPLost").style.display="none";
	}
}

function ability1DoesExtraDmamagePerEnemyHPLost(m) {

	if (m.value=="yes") {
		document.getElementById("ability1DoesExtraDmamagePerEnemyHPLost").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability1DoesExtraDmamagePerEnemyHPLost").style.display="none";
	}
}
function ability1DoesExtraDmamageTemporary(m) {

	if (m.value=="yes") {
		document.getElementById("ability1DoesExtraDamageTemporary").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability1DoesExtraDamageTemporary").style.display="none";
	}
}
//2
function ability2DoesDamage(m) {
	
	if (m.value=="yes") {
		document.getElementById("ability2DoesDamage").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability2DoesDamage").style.display="none";
	}
}
function ability2removesNature(m) {

	if (m.value=="yes") {
		document.getElementById("ability2removesNature").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability2removesNature").style.display="none";
	}
}

function ability2gainsNature(m) {

	if (m.value=="yes") {
		document.getElementById("ability2gainsNature").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability2gainsNature").style.display="none";
	}
}

function ability2gainsHP(m) {

	if (m.value=="yes") {
		document.getElementById("ability2gainsHP").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability2gainsHP").style.display="none";
	}
}
function ability2gainsDD(m) {

	if (m.value=="yes") {
		document.getElementById("ability2gainsDD").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability2gainsDD").style.display="none";
	}
}

function ability2gainsDR(m) {

	if (m.value=="yes") {
		document.getElementById("ability2gainsDR").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability2gainsDR").style.display="none";
	}
}
function ability2DoesExtraDmamagePerSelfHPLost(m) {

	if (m.value=="yes") {
		document.getElementById("ability2DoesExtraDmamagePerSelfHPLost").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability2DoesExtraDmamagePerSelfHPLost").style.display="none";
	}
}

function ability2DoesExtraDmamagePerEnemyHPLost(m) {

	if (m.value=="yes") {
		document.getElementById("ability2DoesExtraDmamagePerEnemyHPLost").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability2DoesExtraDmamagePerEnemyHPLost").style.display="none";
	}
}
function ability2DoesExtraDmamageTemporary(m) {

	if (m.value=="yes") {
		document.getElementById("ability2DoesExtraDamageTemporary").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability2DoesExtraDamageTemporary").style.display="none";
	}
}
//3
function ability3DoesDamage(m) {
	
	if (m.value=="yes") {
		document.getElementById("ability3DoesDamage").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability3DoesDamage").style.display="none";
	}
}
function ability3removesNature(m) {

	if (m.value=="yes") {
		document.getElementById("ability3removesNature").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability3removesNature").style.display="none";
	}
}

function ability3gainsNature(m) {

	if (m.value=="yes") {
		document.getElementById("ability3gainsNature").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability3gainsNature").style.display="none";
	}
}

function ability3gainsHP(m) {

	if (m.value=="yes") {
		document.getElementById("ability3gainsHP").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability3gainsHP").style.display="none";
	}
}
function ability3gainsDD(m) {

	if (m.value=="yes") {
		document.getElementById("ability3gainsDD").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability3gainsDD").style.display="none";
	}
}

function ability3gainsDR(m) {

	if (m.value=="yes") {
		document.getElementById("ability3gainsDR").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability3gainsDR").style.display="none";
	}
}
function ability3DoesExtraDmamagePerSelfHPLost(m) {

	if (m.value=="yes") {
		document.getElementById("ability3DoesExtraDmamagePerSelfHPLost").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability3DoesExtraDmamagePerSelfHPLost").style.display="none";
	}
}

function ability3DoesExtraDmamagePerEnemyHPLost(m) {

	if (m.value=="yes") {
		document.getElementById("ability3DoesExtraDmamagePerEnemyHPLost").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability3DoesExtraDmamagePerEnemyHPLost").style.display="none";
	}
}
function ability3DoesExtraDmamageTemporary(m) {

	if (m.value=="yes") {
		document.getElementById("ability3DoesExtraDamageTemporary").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability3DoesExtraDamageTemporary").style.display="none";
	}
}
//4
function ability4DoesDamage(m) {
	
	if (m.value=="yes") {
		document.getElementById("ability4DoesDamage").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability4DoesDamage").style.display="none";
	}
}
function ability4removesNature(m) {

	if (m.value=="yes") {
		document.getElementById("ability4removesNature").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability4removesNature").style.display="none";
	}
}

function ability4gainsNature(m) {

	if (m.value=="yes") {
		document.getElementById("ability4gainsNature").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability4gainsNature").style.display="none";
	}
}

function ability4gainsHP(m) {

	if (m.value=="yes") {
		document.getElementById("ability4gainsHP").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability4gainsHP").style.display="none";
	}
}
function ability4gainsDD(m) {

	if (m.value=="yes") {
		document.getElementById("ability4gainsDD").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability4gainsDD").style.display="none";
	}
}

function ability4gainsDR(m) {

	if (m.value=="yes") {
		document.getElementById("ability4gainsDR").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability4gainsDR").style.display="none";
	}
}
function ability4DoesExtraDmamagePerSelfHPLost(m) {

	if (m.value=="yes") {
		document.getElementById("ability4DoesExtraDmamagePerSelfHPLost").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability4DoesExtraDmamagePerSelfHPLost").style.display="none";
	}
}

function ability4DoesExtraDmamagePerEnemyHPLost(m) {

	if (m.value=="yes") {
		document.getElementById("ability4DoesExtraDmamagePerEnemyHPLost").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability4DoesExtraDmamagePerEnemyHPLost").style.display="none";
	}
}
function ability4DoesExtraDmamageTemporary(m) {

	if (m.value=="yes") {
		document.getElementById("ability4DoesExtraDamageTemporary").style.display="block";
	}
	else if (m.value=="no"){
		document.getElementById("ability4DoesExtraDamageTemporary").style.display="none";
	}
}
</script>

    <!-- Bootstrap core JavaScript-->
    <script src="js/jquery.js"></script>
    <script src="extras/bootstrap/js/bootstrap.bundle.min.js"></script>

    <script src="js/jquery.easing.js"></script>

    <script src="js/interface.js"></script>

</body>

</html>